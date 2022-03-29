package com.example.lab_05;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.*;


public class StudentTable extends Application {

    public String currentFileName = null;
    public String currentFilePath = null;
    public String currentID;
    public Float currentAssignment;
    public Float currentMidterm;
    public Float currentFinal;
    public DataSource source = new DataSource();

    public void SaveAs(FileChooser fileChooser, Stage stage) {

        fileChooser.setInitialFileName("MyCSV.csv");
        File file = fileChooser.showSaveDialog(stage);

        currentFileName = file.getName();
        currentFilePath = file.getAbsolutePath();
        System.out.println(currentFileName);
        System.out.println(currentFilePath);

        Save(fileChooser, stage);
    }

    public void Save(FileChooser fileChooser, Stage stage) {

        if (currentFileName == null) {
            SaveAs(fileChooser, stage);
        }

        try {
            File file = new File(currentFilePath);
            PrintWriter printWriter = new PrintWriter(file);

            ObservableList data = source.getMarks();
            String line = null;
            printWriter.println("SID,Assignments,Midterm,FinalExam");

            for (int i = 0; i < data.size(); i++){
                StudentRecord student = (StudentRecord) data.get(i);
                line = student.getId() + "," +  Float.toString(student.getAssignmentGrade()) + "," +
                        Float.toString(student.getMidtermGrade()) + "," + Float.toString(student.getFinalExam());
                printWriter.println(line);
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Load(FileChooser fileChooser, Stage stage) {

        File file = fileChooser.showOpenDialog(stage);
        currentFileName = file.getName();
        currentFilePath = file.getAbsolutePath();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
            String splitBy = ",";
            br.readLine();

            while((line = br.readLine()) != null) {
                String [] student = line.split(splitBy);
                source.addRecord(student[0], Float.parseFloat(student[1]),
                        Float.parseFloat(student[2]), Float.parseFloat(student[3]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setTitle("CSCI2020U - Lab 08");

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("CSV Files (*.csv)","*.csv");
        fileChooser.getExtensionFilters().add(extensionFilter);

        // main layout
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10,10,10,10));

        // Setting up the table columns
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
        table.setItems(source.getOriginalMarks());
        table.getColumns().addAll(idColumn, assignmentColumn, midtermColumn, finalColumn, totalColumn, letterColumn);

        // Creating the menu and its event handling
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        MenuButton menu = new MenuButton("Menu");

        MenuItem newFile = new MenuItem("New");
        newFile.setOnAction(e -> {
            table.getItems().clear();
            source = new DataSource();
            currentFileName = null;
            currentFilePath = null;
        });

        MenuItem openFile = new MenuItem("Open");
        openFile.setOnAction(e -> {
            table.getItems().clear();
            source = new DataSource();
            Load(fileChooser, primaryStage);
            table.getItems().addAll(source.getMarks());
        });

        MenuItem saveFile = new MenuItem("Save");
        saveFile.setOnAction(e -> {
            Save(fileChooser, primaryStage);
        });

        MenuItem saveFileAs = new MenuItem("Save As");
        saveFileAs.setOnAction(e -> {
            SaveAs(fileChooser, primaryStage);
        });

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            primaryStage.close();
        });

        menu.getItems().addAll(newFile, openFile, saveFile, saveFileAs, exit);
        hBox.getChildren().addAll(menu);

        // layout holding the I/O attributes
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);

        // creating I/O fields and event handling
        Label SID = new Label("SID: ");
        TextField SIDInput = new TextField();
        SIDInput.setOnKeyTyped(e -> {
            currentID = SIDInput.getText();
        });

        Label Assign = new Label("Assignments Grade: ");
        TextField AssignInput = new TextField();
        AssignInput.setOnKeyTyped(e -> {
            currentAssignment = Float.parseFloat(AssignInput.getText());
        });

        Label Midterm = new Label("Midterm Grade: ");
        TextField MidtermInput = new TextField();
        MidtermInput.setOnKeyTyped(e -> {
            currentMidterm = Float.parseFloat(MidtermInput.getText());
        });

        Label Final = new Label("Final Grade: ");
        TextField FinalInput = new TextField();
        FinalInput.setOnKeyTyped(e -> {
            currentFinal = Float.parseFloat(FinalInput.getText());
        });

        Button button = new Button("Add");
        button.setOnAction(e-> {
            source.addRecord(currentID, currentAssignment, currentMidterm, currentFinal);
            table.setItems(source.getMarks());
            SIDInput.clear();
            AssignInput.clear();
            MidtermInput.clear();
            FinalInput.clear();
        });

        // adding above elements to gridPane
        gridPane.add(SID, 0,0);
        gridPane.add(SIDInput, 1, 0);
        gridPane.add(Assign, 2,0);
        gridPane.add(AssignInput, 3,0);

        gridPane.add(Midterm, 0, 1);
        gridPane.add(MidtermInput, 1, 1);
        gridPane.add(Final, 2, 1);
        gridPane.add(FinalInput, 3, 1);

        gridPane.add(button, 0, 3);

        vbox.getChildren().addAll(hBox, table, gridPane);
        Scene scene = new Scene(vbox, 920, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

