package com.example.lab_05;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.IOException;

public class StudentTable extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        DataSource dataSource = new DataSource();
        primaryStage.setTitle("CSCI2020U - Lab 05");

        //Student ID column
        TableColumn<StudentRecord, String> idColumn = new TableColumn<>("SID");
        idColumn.setMinWidth(150);
        idColumn.setStyle("-fx-alignment: CENTER");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<StudentRecord, Float> assignmentColumn = new TableColumn<>("Assignments");
        assignmentColumn.setMinWidth(150);
        assignmentColumn.setStyle("-fx-alignment: CENTER");
        assignmentColumn.setCellValueFactory(new PropertyValueFactory<>("assignmentGrade"));

        TableColumn<StudentRecord, Float> midtermColumn = new TableColumn<>("Midterm");
        midtermColumn.setMinWidth(150);
        midtermColumn.setStyle("-fx-alignment: CENTER");
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midtermGrade"));

        TableColumn<StudentRecord, Float> finalColumn = new TableColumn<>("Final Exam");
        finalColumn.setMinWidth(150);
        finalColumn.setStyle("-fx-alignment: CENTER");
        finalColumn.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        TableColumn<StudentRecord, Float> totalColumn = new TableColumn<>("Final Grade");
        totalColumn.setMinWidth(150);
        totalColumn.setStyle("-fx-alignment: CENTER");
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalGrade"));

        TableColumn<StudentRecord, Character> letterColumn = new TableColumn<>("Letter Grade");
        letterColumn.setMinWidth(150);
        letterColumn.setStyle("-fx-alignment: CENTER");
        letterColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));


        TableView<StudentRecord> table = new TableView<>();
        table.setItems(dataSource.getAllMarks());
        table.getColumns().addAll(idColumn, assignmentColumn, midtermColumn, finalColumn, totalColumn, letterColumn);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.getChildren().addAll(table);

        Scene scene = new Scene(vbox, 920, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

