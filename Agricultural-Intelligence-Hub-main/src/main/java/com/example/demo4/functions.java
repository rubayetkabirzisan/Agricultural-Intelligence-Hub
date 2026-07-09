package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class functions implements Initializable {

    @FXML
    private Label userEmailLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String email = com.example.demo4.state.AppState.getInstance().getUserEmail();
        if (userEmailLabel != null) {
            userEmailLabel.setText(email.isEmpty() ? "Guest" : email);
        }
    }

    @FXML
    public void showDiseases(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/MOONRESOURCES/diseases.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Agri-Hub — Disease Identification");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void weatherBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(functions.class.getResource("/com/example/demo4/MOONRESOURCES/Weather.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Agri-Hub — Weather Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void cropPlanningClk(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/selectSeassonAndSand.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Agri-Hub — Crop Planning");
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
