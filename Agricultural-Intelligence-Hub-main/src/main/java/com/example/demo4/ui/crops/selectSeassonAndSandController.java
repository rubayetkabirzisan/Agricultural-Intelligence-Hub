package com.example.demo4.ui.crops;

import com.example.demo4.Start;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class selectSeassonAndSandController implements Initializable {
    @FXML
    public ComboBox<String> season;
    @FXML
    private ImageView circle;

    @FXML
    public ComboBox<String> soilquality;
    @FXML
    Label showex;

    @FXML
    private Button but;
    ObservableList<String> l2 = FXCollections.observableArrayList("Sandy Soil", "Clay Soil", "Loamy Soil");
    ObservableList<String> l1 = FXCollections.observableArrayList("Monsoon", "Summer","Winter");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the ComboBoxes with your data (l1, l2)
        // For example, assuming l1 and l2 are ObservableLists<String>
        // l1 and l2 should be defined and initialized in your controller

         season.setItems(l1);
         soilquality.setItems(l2);

        // Start a circular rotation animation on the button
        RotateTransition rotateAnimation = new RotateTransition(Duration.seconds(3), but);
        rotateAnimation.setByAngle(360);
        rotateAnimation.setCycleCount(RotateTransition.INDEFINITE);
        rotateAnimation.play();
    }

    @FXML
    public void findcrop(ActionEvent event) throws IOException {
        String s1 = season.getValue();
        String s2 = soilquality.getValue();
        showex.setStyle("-fx-text-fill: #f85149;"); // text-error color

        if (s1 == null || s2 == null) {
            showex.setText("Please select both season and soil.");
            return;
        }

        String combination = s1 + " " + s2;
        switch (combination) {
            case "Monsoon Sandy Soil":
                new cropPlanningController().monsoonSandySoil(event);
                break;
            case "Monsoon Clay Soil":
                new cropPlanningController().monsoonClaySoil(event);
                break;
            case "Summer Sandy Soil":
                new cropPlanningController().summerSandySoil(event);
                break;
            case "Summer Clay Soil":
                new cropPlanningController().summerClaySoil(event);
                break;
            case "Winter Loamy Soil":
                new cropPlanningController().winterLoamySoil(event);
                break;
            case "Winter Clay Soil":
                new cropPlanningController().winterClaySoil(event);
                break;
            default:
                showex.setText("No crops found for: " + combination);
                break;
        }
    }


@FXML
    ImageView Logo;

 @FXML
 void onHomeBtnClk() throws IOException {
     FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/Functions.fxml"));
     Scene scene = new Scene(fxmlLoader.load());
     Stage stage = (Stage) Logo.getScene().getWindow();
     stage.setTitle("identify!");
     stage.setScene(scene);
     stage.show();
 }





}


