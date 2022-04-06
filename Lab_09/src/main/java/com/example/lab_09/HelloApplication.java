package com.example.lab_09;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HelloApplication extends Application {

    public List<String> dates_1 = new ArrayList<>();
    public List<String> dates_2 = new ArrayList<>();
    public List<Float> closingPrice_1 = new ArrayList<>();
    public List<Float> closingPrice_2 = new ArrayList<>();

    public void DownloadStockPrices(String symbol, List<Float> closePrice, List<String> dates) {

        try {
            /*
            Https link format

            https://query1.finance.yahoo.com/v7/finance/download/<YOUR STOCK TICKER LABEL>
            ?period1=1262322000&period2=1451538000&interval=1mo&eve
            nts=history&includeAdjustedClose=true
             */

            URL url = new URL("https://query1.finance.yahoo.com/v7/finance/download/" + symbol +
                    "?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inLine = in.readLine();
            while((inLine = in.readLine()) != null) {
                String[] temp = inLine.split(",");
                dates.add(temp[0]);
                closePrice.add(Float.parseFloat(temp[4]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public XYChart.Series drawLinePlot(String name, List<Float> stock, List<String> dates) {

        XYChart.Series series = new XYChart.Series<>();
        series.setName(name);
        for(int i = 0; i < stock.size(); i++) {
            series.getData().add(new XYChart.Data<>(dates.get(i), stock.get(i)));
        }

        return series;
    }

    @Override
    public void start(Stage stage) throws IOException {
        String stockName1 = "FB";
        String stockName2 = "TSLA";

        DownloadStockPrices(stockName1, closingPrice_1, dates_1);
        DownloadStockPrices(stockName2, closingPrice_2, dates_2);

        XYChart.Series series_1 = drawLinePlot(stockName1, closingPrice_1, dates_1);
        XYChart.Series series_2 = drawLinePlot(stockName2, closingPrice_2, dates_2);

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("USD ($)");
        LineChart lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.getData().addAll(series_1, series_2);
        lineChart.setCreateSymbols(false);
        lineChart.setTitle(stockName1 + " Vs. " + stockName2 + " Stock Price");
        VBox vBox = new VBox(lineChart);

        Scene scene = new Scene(vBox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}