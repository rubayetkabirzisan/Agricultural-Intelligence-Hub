package com.example.demo4.MoonJAVAClass;
import com.example.demo4.Start;
import com.example.demo4.functions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherController {

@FXML
    private int currentDayIndex = 0;

@FXML
Label time;


    @FXML
    private Label city;

    @FXML
    private Label country;

    @FXML
    private Label date;

    @FXML
    private Label day;

    @FXML
    private Label humidity;

    @FXML
    private Label pressure;

    @FXML
    private TextField readCity;

    @FXML
    private Label temperature;

    @FXML
    private Label visibility;

    @FXML
    private Label wind;
    @FXML
    private Label description;
    @FXML
    private Label showerror;

    // Custom exception for city name validation
    public class InvalidCityNameException extends Exception {
        public InvalidCityNameException(String message) {
            super(message);
        }
    }



    private void validateCityName(String city) throws InvalidCityNameException {
        if (!Character.isUpperCase(city.charAt(0)) || !city.substring(1).equals(city.substring(1).toLowerCase())) {
            throw new InvalidCityNameException("City name must start with a capital letter and be followed by lowercase letters.'Dhaka'");
        }
    }



    @FXML
    void button(ActionEvent event) {
        String apiKey = "c89d4a791d1805c985913bf8d5268087";
        String city=readCity.getText();






        showerror.setText(null);

        try {


            validateCityName(city);

            // Construct the API URL for 30-day forecast
            String apiUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&cnt=50&appid=" + apiKey;

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // If the response code is OK, read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Save the response to a JSON file
                saveResponseToJsonFile(response.toString(), "next30days.json");

                System.out.println("API Response saved to 30_day_forecast.json");


            } else {
                System.out.println("Error: " + responseCode);
            }

            // Close the connection
            connection.disconnect();

        } catch (InvalidCityNameException e) {
            // Handle the custom exception
            showerror.setText("Error: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            showerror.setText("Error connecting to the weather API.");
        }
        nxt30days.WriteCsv();
        updateLabelsFromCsv("CSVFILES/MOONCSVFILES/30daysUpdate.csv");


    }

    //polymorphism
    private void updateLabelsFromCsv(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Read the header line (skip it for now)
           // String header = reader.readLine();

            // Read the first data line
            String line = reader.readLine();
            if (line != null) {
                String[] values = line.split(",");

                // Update labels with the values from CSV
                city.setText(values[11]); // Assuming city is at index 10
                country.setText(values[10]); // Assuming country is at index 9
                date.setText(values[1]); // Assuming date is at index 1
                day.setText(values[0]); // Assuming day is at index 0
                humidity.setText(values[7]); // Assuming humidity is at index 7
                pressure.setText(values[6]); // Assuming pressure is at index 6
                temperature.setText(values[2]); // Assuming temperature is at index 2
                visibility.setText(values[13]); // Assuming visibility is at index 11
                wind.setText(values[8]); // Assuming wind is at index 8
                description.setText(values[9]);
                time.setText(values[12]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }










    private static void saveResponseToJsonFile(String jsonResponse, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void nextday(ActionEvent event) {
        // Increment the current day index
        currentDayIndex++;

        // Load data from CSV and update labels
        updateLabelsFromCsv("CSVFILES/MOONCSVFILES/30daysUpdate.csv", currentDayIndex);
    }

    @FXML
    void prevday(ActionEvent event) {
        // Decrement the current day index
        currentDayIndex--;

        // Check if the index is less than 0 and reset it to the last day index
        if (currentDayIndex < 0) {
            currentDayIndex = getLastDayIndex();
        }

        // Load data from CSV and update labels
        updateLabelsFromCsv("CSVFILES/MOONCSVFILES/30daysUpdate.csv", currentDayIndex);
    }

    private int getLastDayIndex() {
        // Calculate and return the last day index based on your data
        // In this example, assuming your CSV file has data for each day without gaps,
        // you can count the number of lines in the CSV file and subtract 1.
        try (BufferedReader reader = new BufferedReader(new FileReader("CSVFILES/MOONCSVFILES/30daysUpdate.csv"))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            return lineCount - 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //polymorphism
    private void updateLabelsFromCsv(String csvFilePath, int dayIndex) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Read and discard the header line
            reader.readLine();

            // Loop to the specified day index
            for (int i = 0; i < dayIndex; i++) {
                reader.readLine(); // Skip lines until the desired day
            }

            // Read the data for the current day
            String line = reader.readLine();
            if (line != null) {
                String[] values = line.split(",");

                // Update labels with the values from CSV
                time.setText(values[12]);
                city.setText(values[11]); // Assuming city is at index 10
                country.setText(values[10]); // Assuming country is at index 9
                date.setText(values[1]); // Assuming date is at index 1
                day.setText(values[0]); // Assuming day is at index 0
                humidity.setText(values[7]); // Assuming humidity is at index 7
                pressure.setText(values[6]); // Assuming pressure is at index 6
                temperature.setText(values[2]); // Assuming temperature is at index 2
                visibility.setText(values[13]); // Assuming visibility is at index 11
                wind.setText(values[8]); // Assuming wind is at index 8
                description.setText(values[9]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    ImageView weatherHomelogo;
    @FXML
    public void homeBtn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/Functions.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) weatherHomelogo.getScene().getWindow();
        stage.setTitle("identify!");
        stage.setScene(scene);
        stage.show();
    }

}
