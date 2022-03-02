package com.example.lab_04;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class RegisterGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("CSCI2020U - Lab 04");

        // Formatting the window
        GridPane gp = new GridPane();
        gp.setHgap(20);
        gp.setVgap(10);
        gp.setPadding(new Insets(10,10,10,10));

        TextField usernameIn = new TextField();
        PasswordField passwordIn = new PasswordField();
        TextField nameIn = new TextField();
        TextField emailIn =  new TextField();
        TextField phoneIn = new TextField();
        DatePicker dobIn = new DatePicker();
        Button register = new Button("Register");

        Label username = new Label("Username: ");
        Label password = new Label("Password: ");
        Label fullName = new Label("Full Name: ");
        Label email = new Label("E-mail: ");
        Label phoneNum =  new Label("Phone #: ");
        Label dateOBirth = new Label("Date Of Birth: ");
        Label regConfirm = new Label (" ");

        // handles the event of the register button being pressed
        EventHandler<ActionEvent> buttonPress = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(usernameIn.getText().isEmpty() || passwordIn.getText().isEmpty() || nameIn.getText().isEmpty()
                || emailIn.getText().isEmpty() || phoneIn.getText().isEmpty() || dobIn.getValue().equals(null)) {
                    regConfirm.setText("Please Fill In All Fields Before Registering");
                } else{
                    System.out.println(username.getText() + usernameIn.getText());
                    System.out.println(password.getText() + passwordIn.getText());
                    System.out.println(fullName.getText() + nameIn.getText());
                    System.out.println(email.getText() + emailIn.getText());
                    System.out.println(phoneNum.getText() + phoneIn.getText());
                    System.out.println(dateOBirth.getText() + dobIn.getValue());

                    regConfirm.setText("Thank You For Registering");
                }
            }
        };

        register.setOnAction(buttonPress);

        gp.add(username, 0, 0, 1, 1);
        gp.add(usernameIn, 1, 0, 1, 1);
        gp.add(password, 0, 1, 1, 1);
        gp.add(passwordIn, 1, 1, 1, 1);
        gp.add(fullName, 0, 2, 1, 1);
        gp.add(nameIn, 1, 2, 1, 1);
        gp.add(email, 0, 3, 1, 1);
        gp.add(emailIn, 1, 3, 1, 1);
        gp.add(phoneNum, 0, 4, 1, 1);
        gp.add(phoneIn, 1, 4, 1, 1);
        gp.add(dateOBirth, 0, 5, 1, 1);
        gp.add(dobIn, 1, 5, 1, 1);
        gp.add(register, 1, 6, 1, 1);
        gp.add(regConfirm, 1, 7, 1, 1);
        //adding the UI elements to the Layout

        Scene scene = new Scene(gp, 400, 350);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}