package com.example.demo4.ZishanJavaClass;

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

    public class InvalidCombinationException extends Exception {
        public InvalidCombinationException(String message) {
            super(message);
        }
    }

    @FXML
    public void findcrop(ActionEvent event) throws IOException {
        String s1 = season.getValue();
        String s2 = soilquality.getValue();
        try {
            validateCombination(s1, s2);


            switch (s1+" "+s2) {
            case "Monsoon Sandy Soil":
               cropPlanningController obj=new cropPlanningController();
                obj.monsoonSandySoil(event);
                break;
            case "Monsoon Clay Soil":
                cropPlanningController obj1=new cropPlanningController();
                obj1.monsoonClaySoil(event);
                break;
            case "Summer Sandy Soil":
                cropPlanningController obj2=new cropPlanningController();
                obj2.summerSandySoil(event);
                break;
            case "Summer Clay Soil":
                cropPlanningController obj3=new cropPlanningController();
                obj3.summerClaySoil(event);
                break;
            case "Winter Loamy Soil":
                cropPlanningController obj4=new cropPlanningController();
                obj4.winterLoamySoil(event);
                break;
            case "Winter Clay Soil":
                cropPlanningController obj5=new cropPlanningController();
                obj5.winterClaySoil(event);
                break;
            default:
                System.out.println("not found");
                //showError.setText("Not In List");
        }} catch (InvalidCombinationException e) {
                // Handle the exception (e.g., show an error message or log it)


                e.printStackTrace();
            }}


    private void validateCombination(String s1, String s2) throws InvalidCombinationException {
        String combination = s1 + " " + s2;
        ObservableList<String> allowedCombinations = FXCollections.observableArrayList(
                "Monsoon Sandy Soil", "Monsoon Clay Soil",
                "Summer Sandy Soil", "Summer Clay Soil",
                "Winter Loamy Soil", "Winter Clay Soil"
        );

        if (!allowedCombinations.contains(combination)) {
            showex.setText("Invalid combination selected: " + combination);
            throw new InvalidCombinationException("Invalid combination selected: " + combination);

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


