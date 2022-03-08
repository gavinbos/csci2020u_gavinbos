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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentTable extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

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
        table.setItems(DataSource.getAllMarks());
        table.getColumns().addAll(idColumn, assignmentColumn, midtermColumn, finalColumn, totalColumn, letterColumn);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.getChildren().addAll(table);

        Scene scene = new Scene(vbox, 920, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class DataSource {
    public static ObservableList<StudentRecord> getAllMarks() {
        ObservableList<StudentRecord> marks =
                FXCollections.observableArrayList();

// Student ID, Assignments, Midterm, Final exam
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f, 54.25f));
        marks.add(new StudentRecord("100100101", 70.0f, 69.25f, 51.5f));
        marks.add(new StudentRecord("100100102", 100.0f, 97.0f, 92.5f));
        marks.add(new StudentRecord("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new StudentRecord("100100104", 72.25f, 74.75f, 58.25f));
        marks.add(new StudentRecord("100100105", 85.0f, 56.0f, 62.5f));
        marks.add(new StudentRecord("100100106", 70.0f, 66.5f, 61.75f));
        marks.add(new StudentRecord("100100107", 55.0f, 47.0f, 50.5f));
        marks.add(new StudentRecord("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new StudentRecord("100100109", 82.5f, 77.0f, 74.25f));

        return marks;
    }
}