package com.example.lab_07;
import java.io.*;
import java.util.HashMap;

public class CSVReader {

    public HashMap readCSV() throws IOException {

        HashMap<String, Double> weatherWarnings = new HashMap<>();
        FileReader fileReader = null;

        try {
            fileReader = new FileReader("C:\\Users\\gavin\\Desktop\\School\\Software Dev\\csci2020u_gavinbos\\Lab_07\\src\\main\\java\\com\\example\\lab_07\\weatherwarnings-2015.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            String[] arr = line.split(",");
            if (weatherWarnings.get(arr[5]) == null) {
                weatherWarnings.put(arr[5], 1.0);
            }
            else {
                double temp = weatherWarnings.get(arr[5]);
                weatherWarnings.replace(arr[5], temp + 1.0);
            }
        }

        return weatherWarnings;
    }

}
