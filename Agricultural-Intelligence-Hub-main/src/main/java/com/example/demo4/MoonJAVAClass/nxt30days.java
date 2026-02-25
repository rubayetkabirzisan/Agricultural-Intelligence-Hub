package com.example.demo4.MoonJAVAClass;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class nxt30days {
    static void WriteCsv() {

        try {
            // Replace "path/to/your/file.json" with the actual path to your JSON file
            File inputFile = new File("next30days.json");
            File outputFile = new File("CSVFILES/MOONCSVFILES/30daysUpdate.csv");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputFile);

            JsonNode cityNode = rootNode.path("city");
            String country = cityNode.path("country").asText();
            String city = cityNode.path("name").asText();

            // Prepare CSV header
            StringBuilder csvHeader = new StringBuilder();
            //csvHeader.append("Day,Date,Temperature(°C),Feels Like(°C),Temp Min(°C),Temp Max(°C),Pressure,Humidity,Wind Speed,Main Description,Country,City,Visibility\n");

            // Write CSV header to file
            writeToFile(outputFile, csvHeader.toString());
            csvHeader.append("Day,Date,Temperature(°C),Feels Like(°C),Temp Min(°C),Temp Max(°C),Pressure,Humidity,Wind Speed,Main Description,Country,City,Visibility\n");

            JsonNode listNode = rootNode.path("list");
            int i = 1;

            try (FileWriter fileWriter = new FileWriter(outputFile, false)) { // Set the second parameter to false for overwriting
                for (JsonNode listItem : listNode) {
                    String dateStr = listItem.path("dt_txt").asText().split(" ")[0];
                    Date date = parseDateString(dateStr);
                    String dayOfWeek = calculateDayOfWeek(date);
                    BufferedReader reader = new BufferedReader(new FileReader("CSVFILES/MOONCSVFILES/30daysUpdate.csv"));
                    String time = listItem.path("dt_txt").asText().split(" ")[1];








                    double tempKelvin = listItem.path("main").path("temp").asDouble();
                    double feelsLikeKelvin = listItem.path("main").path("feels_like").asDouble();
                    double tempMinKelvin = listItem.path("main").path("temp_min").asDouble();
                    double tempMaxKelvin = listItem.path("main").path("temp_max").asDouble();

                    // Convert temperatures to Celsius with two decimal points
                    double tempCelsius = round(tempKelvin - 273.15, 2);
                    double feelsLikeCelsius = round(feelsLikeKelvin - 273.15, 2);
                    double tempMinCelsius = round(tempMinKelvin - 273.15, 2);
                    double tempMaxCelsius = round(tempMaxKelvin - 273.15, 2);

                    int pressure = listItem.path("main").path("pressure").asInt();
                    int humidity = listItem.path("main").path("humidity").asInt();
                    double windSpeed = listItem.path("wind").path("speed").asDouble();
                    String mainDescription = listItem.path("weather").get(0).path("main").asText();
                    int visibility = listItem.path("visibility").asInt();

                    // Prepare CSV line
                    StringBuilder csvLine = new StringBuilder();
                    csvLine.append(dayOfWeek).append(",")
                            .append(dateStr).append(",")
                            .append(tempCelsius).append(",")
                            .append(feelsLikeCelsius).append(",")
                            .append(tempMinCelsius).append(",")
                            .append(tempMaxCelsius).append(",")
                            .append(pressure).append(",")
                            .append(humidity).append(",")
                            .append(windSpeed).append(",")
                            .append(mainDescription).append(",")
                            .append(country).append(",")
                            .append(city).append(",")
                            .append(time).append(",")
                            .append(visibility).append("\n");

                    // Write CSV line to file (in append mode)
                    fileWriter.write(csvLine.toString());
                }
            }

            System.out.println("CSV file '30daysUpdate.csv' created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String parseTimeString(Date date) {
        // Format the time from the date (HH:mm:ss)
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(date);
    }

    private static Date parseDateString(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String calculateDayOfWeek(Date date) {
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        return dayFormat.format(date);
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private static void writeToFile(File file, String data) {
        try (FileWriter fileWriter = new FileWriter(file, true)) { // Set the second parameter to false for overwriting
            fileWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
