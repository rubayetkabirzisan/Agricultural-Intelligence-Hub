package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class functions {
    @FXML
    public void showDiseases(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/MOONRESOURCES/diseases.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("identyfy!");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void weatherBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(functions.class.getResource("/com/example/demo4/MOONRESOURCES/Weather.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Diseases Identification");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void cropPlanningClk(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/selectSeassonAndSand.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("identyfy!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void askAiBtn(ActionEvent event) {
        try {
            com.example.demo4.ui.weather.AskAi ai = new com.example.demo4.ui.weather.AskAi();
            ai.showai();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
