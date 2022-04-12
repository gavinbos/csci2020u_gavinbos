package com.example.server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    public class Server {
        public List<BufferedReader> bufReads = new ArrayList<>();
        public ServerSocket serverSocket;
        public List<Socket> sockets = new ArrayList<>();

        public Server(int portnum) {
            try {
                serverSocket = new ServerSocket(portnum);
                System.out.println("Waiting for client connection request...");
                Socket s = serverSocket.accept();
                System.out.println("Connected to new client!");
                sockets.add(s);
                BufferedReader bufRead = new BufferedReader(new InputStreamReader(s.getInputStream()));
                listenForInput(bufRead);

                Thread thread = new Thread(() -> {
                    while(!serverSocket.isClosed()) {
                        try {
                            Socket socket = serverSocket.accept();
                            System.out.println("Connected to new client!");
                            sockets.add(socket);
                            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            listenForInput(br);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Stage stage;
    public ScrollPane scrollPane;
    public TextArea textArea;
    public Server server = new Server(4356);

    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;
        Label label = new Label("Message Board");
        label.setFont(new Font("Serif", 18));
        HBox hBox = new HBox(label);
        hBox.setAlignment(Pos.CENTER);

        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setFont(new Font("SansSerif", 12));

        scrollPane = new ScrollPane(textArea);
        scrollPane.setMaxWidth(500);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox,scrollPane);
        vBox.setPadding(new Insets(15,15,15,15));

        Scene scene = new Scene(vBox, 500,300);
        this.stage.setScene(scene);
        this.stage.setTitle("Post-It Board");
        this.stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

    public void listenForInput(BufferedReader br) {
        Thread thread = new Thread(() -> {
            String in = "";

            try {
                while (!(in = br.readLine()).equals("Exit")) {
                    textArea.appendText(in + "\n");
                }
            } catch (IOException e){}

            try {
                System.out.println("Closing server");
                server.serverSocket.close();
                stage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

}