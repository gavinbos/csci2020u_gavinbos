package com.example.lab_05;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class StudentTable extends Application {

    public String currentID;
    public Float currentAssignment;
    public Float currentMidterm;
    public Float currentFinal;

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
        table.setItems(dataSource.getOriginalMarks());
        table.getColumns().addAll(idColumn, assignmentColumn, midtermColumn, finalColumn, totalColumn, letterColumn);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        Label SID = new Label("SID: ");
        TextField SIDInput = new TextField();
        SIDInput.setOnKeyTyped(e -> {
            currentID = SIDInput.getText();
        });

        Label Assign = new Label("Assignments Grade: ");
        TextField AssignInput = new TextField();
        AssignInput.setOnKeyTyped(e -> {
            currentAssignment = Float.parseFloat(AssignInput.getText());
            System.out.println(currentAssignment);
        });

        Label Midterm = new Label("Midterm Grade: ");
        TextField MidtermInput = new TextField();
        MidtermInput.setOnKeyTyped(e -> {
            currentMidterm =Float.parseFloat(MidtermInput.getText());
        });

        Label Final = new Label("Final Grade: ");
        TextField FinalInput = new TextField();
        FinalInput.setOnKeyTyped(e -> {
            currentFinal = Float.parseFloat(FinalInput.getText());
        });

        Button button = new Button("Add");
        button.setOnAction(e-> {
            dataSource.addRecord(currentID, currentAssignment, currentMidterm, currentFinal);
            table.setItems(dataSource.getMarks());
        });

        gridPane.add(SID, 0,0);
        gridPane.add(SIDInput, 1, 0);
        gridPane.add(Assign, 2,0);
        gridPane.add(AssignInput, 3,0);

        gridPane.add(Midterm, 0, 1);
        gridPane.add(MidtermInput, 1, 1);
        gridPane.add(Final, 2, 1);
        gridPane.add(FinalInput, 3, 1);

        gridPane.add(button, 0, 3);

        vbox.getChildren().addAll(table, gridPane);
        Scene scene = new Scene(vbox, 920, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

