package com.example.lab_06;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lab06 extends Application {
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static String[] pieColours = {
            "PLUM", "LAWNGREEN", "DARKSALMON", "DARKORANGE",
            "GOLD", "AQUA"
    };

    @Override
    public void start(Stage stage) throws IOException {

        List<String> yearList = Arrays.asList("2013","2014","2015","2016","2017","2018","2019","2020");
        ObservableList<String> years = FXCollections.observableList(yearList);
        stage.setTitle("CSCI2020U - Lab06");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(years);
        xAxis.setLabel("Year");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price ($USD)");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> series_1 = new XYChart.Series<>();
        series_1.setName("Housing Prices");
        for(int i = 0; i < avgHousingPricesByYear.length; i++){
            series_1.getData().add(new XYChart.Data<>(years.get(i), avgHousingPricesByYear[i]));
        }

        XYChart.Series<String, Number> series_2 = new XYChart.Series<>();
        series_2.setName("Commercial Prices");
        for(int i = 0; i < avgCommercialPricesByYear.length; i++){
            series_2.getData().add(new XYChart.Data<>(years.get(i), avgCommercialPricesByYear[i]));
        }

        barChart.getData().addAll(series_1, series_2);
        barChart.setTitle("Housing and Commercial Prices by Year");

        List<String> ageGroupsList = Arrays.asList(ageGroups);
        List<PieChart.Data> data = new ArrayList<>();
        for(int i = 0; i < purchasesByAgeGroup.length; i++){
            data.add(new PieChart.Data(ageGroupsList.get(i), purchasesByAgeGroup[i]));
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Purchase by Age Group");
        pieChart.setLegendVisible(false);

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,10,10,10));
        VBox vbox = new VBox(barChart);
        VBox vbox2 = new VBox(pieChart);
        bp.setLeft(vbox);
        bp.setRight(vbox2);

        Scene scene = new Scene(bp, 1000, 450);
        stage.setScene(scene);
        stage.show();

        int i = 0;
        for (PieChart.Data d : pieChartData) {
            d.getNode().setStyle("-fx-pie-color: " + pieColours[i % pieColours.length] + ";");
            i++;
        }

    }

    public static void main(String[] args) {
        launch();
    }
}