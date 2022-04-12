package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HelloApplication extends Application {

    public class Client {
        public BufferedWriter bufWrite;
        public Socket socket;

        public Client(String address, int portnum){
            try {
                System.out.println("Attempting to connect to server...");
                socket = new Socket(address,portnum);
                System.out.println("Connected to server!");
                bufWrite = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void write(String username, String message) {
            try {
                if (username.equals("Exit") && message.equals("")) {
                    bufWrite.write("Exit");
                    bufWrite.close();
                    socket.close();
                } else {
                    bufWrite.write(username + ": " + message);
                    bufWrite.newLine();
                    bufWrite.flush();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String currentUser;
    public String currentMessage;

    @Override
    public void start(Stage stage) throws IOException {
        Client client = new Client("localhost", 4356);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.add(new Label("UserName:"), 0,0);
        gridPane.add(new Label("Message:"), 0,1);

        TextField userNameInput = new TextField();
        gridPane.add(userNameInput, 1,0);
        userNameInput.setOnKeyTyped(e -> {
            currentUser = userNameInput.getText();
        });

        TextField messageInput = new TextField();
        gridPane.add(messageInput, 1,1);
        messageInput.setOnKeyTyped(e -> {
            currentMessage = messageInput.getText();
        });

        Button send = new Button("Send");
        send.setOnAction(e-> {
            userNameInput.clear();
            messageInput.clear();
            client.write(currentUser, currentMessage);
            currentUser = "";
            currentMessage = "";
        });

        Button exit = new Button("Exit");
        exit.setOnAction(e-> {
            stage.close();
            client.write("Exit", "");
        });

        gridPane.add(send, 0,2);
        gridPane.add(exit, 1, 2);

        VBox vBox = new VBox(gridPane);
        vBox.setPadding(new Insets(15,15,15,15));

        Scene scene = new Scene(vBox, 300, 150);
        stage.setScene(scene);
        stage.setTitle("Post-It!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}