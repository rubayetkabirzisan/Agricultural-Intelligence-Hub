package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

public class functions implements Initializable {

    @FXML private Label userEmailLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String email = com.example.demo4.state.AppState.getInstance().getUserEmail();
        if (userEmailLabel != null) {
            userEmailLabel.setText(email.isEmpty() ? "Guest" : email);
        }
    }

    @FXML
    public void weatherBtn(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneTransition.navigateTo(stage, "/com/example/demo4/MOONRESOURCES/Weather.fxml", "Agri-Hub — Weather Dashboard");
    }

    @FXML
    public void askAiBtn(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneTransition.navigateTo(stage, "/com/example/demo4/MOONRESOURCES/AskAi.fxml", "Agri-Hub — AI Assistant Hub");
    }

    @FXML
    public void farmProfileBtn(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneTransition.navigateTo(stage, "/com/example/demo4/MOONRESOURCES/FarmProfile.fxml", "Agri-Hub — My Farm Profile");
    }

    @FXML
    public void analyticsBtn(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneTransition.navigateTo(stage, "/com/example/demo4/MOONRESOURCES/Analytics.fxml", "Agri-Hub — Yield Analytics");
    }
}
