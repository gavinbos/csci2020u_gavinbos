package com.example.lab_07;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    CSVReader csvReader = new CSVReader();

    @Override
    public void start(Stage stage) throws IOException {
        HashMap hashMap = csvReader.readCSV();
        String[] categoryArr = {"FLASH FLOOD", "SEVERE THUNDERSTORM", "SPECIAL MARINE", "TORNADO"};

        List<String> categoryList = Arrays.asList(categoryArr);
        List<PieChart.Data> data = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++){
            String s = categoryList.get(i);
            double t = (double) hashMap.get(s);
            data.add(new PieChart.Data(categoryList.get(i), t));
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Weather Warnings (USA - 2015)");
        pieChart.setLegendSide(Side.LEFT);

        VBox vBox = new VBox(pieChart);
        vBox.setAlignment(Pos.CENTER_RIGHT);
        vBox.setPadding(new Insets(15,15,15,15));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 900, 400);
        stage.setScene(scene);
        stage.setTitle("CSCI2020U - Lab 07");
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}