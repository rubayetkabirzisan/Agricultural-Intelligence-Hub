package com.example.demo4.ui.crops;


import com.example.demo4.Start;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;




public class IntercroppingController {

    static String path;
    static String VideoPath;
    static Image img;
    @FXML
    private Label selectedCropLabel;

    @FXML
    private ComboBox<String> cropComboBox;

    @FXML
    private Label intercroppingProcedureLabel;
    @FXML
    ImageView imageView;

    @FXML
    public void initialize() {
        // You can initialize components here or add event handlers
        // For example:
        String s = cropPlanningController.getMainCrop();
        if ("Rice".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Soybean", "Pumpkins", "Cucumber");
            cropComboBox.setItems(l);
        } else if ("Jute".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Mung Beans", "Cucumber ", "Pumpkins ");
            cropComboBox.setItems(l);

        } else if ("Rice.".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Mung Beans ", "Soybean", "Ginger ");
            cropComboBox.setItems(l);
        }
        else if ("Taro".equals(s)) {
        ObservableList<String> l = FXCollections.observableArrayList("Ginger", "Spinach", "Turmeric" );
        cropComboBox.setItems(l);
    }
        else if ("Corn".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Pumpkin ","Bean","Soybean ");
            cropComboBox.setItems(l);
        }
        else if ("Ground Nuts".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Cowpeas","Corn ","Mung Beans  ");
            cropComboBox.setItems(l);
        }
        else if ("Lentils".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Maize","Sunflowers","Sorghum");
            cropComboBox.setItems(l);
        }
        else if ("Sweet Potato".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Squash","Eggplants","Bush Beans");
            cropComboBox.setItems(l);
        }
        else if ("Mustard".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Chickpeas","Leafy Greens","Radishes");
            cropComboBox.setItems(l);
        }
        else if ("Spinach".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Carrot","Garlic","Brassicas");
            cropComboBox.setItems(l);
        }
        else if ("Wheat".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Legumes","Barley","Faba Beans");
            cropComboBox.setItems(l);
        }
        else if ("Carrot".equals(s)) {
            ObservableList<String> l = FXCollections.observableArrayList("Peas","Onions","Radishes ");
            cropComboBox.setItems(l);
        }


        //  cropComboBox.getItems().addAll("Pumpkins","Cucumber","Soybean","Mung Beans","Taro","Turmeric","Ginger","Spinach","Sweet Potato","Okra","Corn","Carrot","Legumes","Mustard Greens","Lettuce","Barley","Cabbage");
    }


    @FXML
    void ButtonOk(ActionEvent event) {
        String s = cropComboBox.getValue();
        System.out.println(s);

        if ("Soybean".equals(s)) {
            System.out.println(s);
            String dbName = "Soyabin";
            VideoPath = "https://youtu.be/3-0IQPPMrgI?si=3J1ConeN2fEpfw98";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Maize".equals(s)) {
            System.out.println(s);
            String dbName = "Maize";
            VideoPath = "https://youtu.be/KL8dUiahgj0?si=BJEpfDOF5FW7GfDd";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Peas".equals(s)) {
            System.out.println(s);
            String dbName = "Peas";
            VideoPath = "https://youtu.be/D4fjOf1YjiE?si=ou6vJROnAijxHHlz";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Radishes ".equals(s)) {
            System.out.println(s);
            String dbName = "radishess";
            VideoPath = "https://youtube.com/shorts/BX1CEJo20yc?si=z3sE3j1ZtZBJYlfk";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Onions".equals(s)) {
            System.out.println(s);
            String dbName = "Onions";
            VideoPath = "https://youtu.be/vhD1dMHJwBc?si=bqVQISmoErfihy5q";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Chickpeas".equals(s)) {
            System.out.println(s);
            String dbName = "Chickpeas";
            VideoPath = "https://youtu.be/2wSoX_8MBEs?si=hzraMbWCAgh17Tsp";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Leafy Greens".equals(s)) {
            System.out.println(s);
            String dbName = "Leafy Greens";
            VideoPath = "https://youtube.com/shorts/gAOggj5YUUQ?si=AscSENr4LzI9A7tW";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Radishes".equals(s)) {
            System.out.println(s);
            String dbName = "Radishes";
            VideoPath = "https://youtu.be/tyYfR9wmhiw?si=wItab07HzfJgFNlU";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Squash".equals(s)) {
            System.out.println(s);
            String dbName = "Squash";
            VideoPath = "https://youtu.be/d2EHGLnrCV8?si=lcP2A42g3tdRK3Lq";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Eggplants".equals(s)) {
            System.out.println(s);
            String dbName = "Eggplants";
            VideoPath = "https://youtu.be/boyxjlRcjbQ?si=muaFMBrmKNbLM3k_";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Bush Beans".equals(s)) {
            System.out.println(s);
            String dbName = "Bush Beans";
            VideoPath = "https://youtu.be/d2EHGLnrCV8?si=EDTjx34c13RIFk7u";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Sunflowers".equals(s)) {
            System.out.println(s);
            String dbName = "Sunflowers";
            VideoPath = "https://youtu.be/b3K8LHjo9SA?si=kBiPgKoc7KllVsOy";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Sorghum".equals(s)) {
            System.out.println(s);
            String dbName = "Sorghum";
            VideoPath = "https://youtu.be/Bt9XX5K7OX8?si=jyMpqKFd_3XmfHjO";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else  if("Cowpeas".equals(s)){
            System.out.println(s);
            String dbName = "Cowpeas";
            VideoPath="https://youtu.be/thcP8Snu9j0?si=QDRaGAqmO2XM00p4";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }

        else  if("Corn ".equals(s)){
            System.out.println(s);
            String dbName = "Corn ";
            VideoPath="https://youtu.be/thcP8Snu9j0?si=4if8drl0zOBxBlIw";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }

        else  if("Mung Beans  ".equals(s)){
            System.out.println(s);
            String dbName = "mungbeans";
            VideoPath="https://youtu.be/thcP8Snu9j0?si=4if8drl0zOBxBlIw";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else  if("Bean".equals(s)){
            System.out.println(s);
            String dbName = "Bean";
            VideoPath="https://youtu.be/bTujZHu8TZ8?si=4f2oT3bZjnyFkK_M";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else  if("Pumpkin ".equals(s)){
            System.out.println(s);
            String dbName = "Pumpkin ";
            VideoPath="https://youtu.be/cDbBr5oZpLE?si=bBhZzqHxssilFlzQ";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }

        else  if("Soybean ".equals(s)){
            System.out.println(s);
            String dbName = "soybeans";
            VideoPath="https://youtu.be/J5bdz03TSS0?si=c1SFP_Hcpd9OzVqe";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }

        else if ("Cucumber".equals(s)) {
            System.out.println(s);
            String dbName = "Cucumber";
            VideoPath = "https://youtu.be/AprcXULHGmw?si=QsrsArLhFHzOXSUe";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else  if("Cucumber ".equals(s)){
            System.out.println(s);
            String dbName = "Cucumber ";
            VideoPath="https://youtu.be/cyQFzd44PPU?si=ltReq1G0kfsHcXox";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }

        else if ("Pumpkins".equals(s)) {
            System.out.println(s);
            String dbName = "Pumpkin";
            VideoPath = "https://youtu.be/iKxw1mu3JQ8?si=myYuViE2Gahm56WB";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Pumpkins ".equals(s)) {
            System.out.println(s);
            String dbName = "Pumpkins ";
            VideoPath = "https://youtube.com/shorts/MminmMy5N0w?si=HXnAaFbJlKY6nT-t";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Mung Beans".equals(s)) {
            System.out.println(s);
            String dbName = "Mung Beans";
            VideoPath = "https://youtu.be/uFV_TUAmvEY?si=OISV_g6yAc5IuMw2";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else  if("Mung Beans ".equals(s)){
            System.out.println(s);
            String dbName = "Mung Beans ";
            VideoPath="https://youtu.be/n4f-jtH7bvY?si=y92MbU67qoi48EpJ";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Ginger".equals(s)) {
            System.out.println(s);
            String dbName = "Ginger";
            VideoPath = "https://youtu.be/3c7j7TzjOc8?si=Jb9n0Suf-VXO27SE";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else  if("Ginger ".equals(s)){
            System.out.println(s);
            String dbName = "Ginger ";
            VideoPath="https://youtube.com/shorts/wqMtDNHUdRY?si=7TuHM4ykfwc_Gn7C";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Turmeric".equals(s)) {
            System.out.println(s);
            String dbName = "Turmeric";
            VideoPath = "https://youtube.com/shorts/jv-hd1xpmPM?si=lTYLc35lcsOeNRkR";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        } else if ("Spinach".equals(s)) {
            System.out.println(s);
            String dbName = "Spinach";
            VideoPath = "https://youtube.com/shorts/bLRFhIm_KTY?si=3QXtVwgtRJlKFDJ2";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        } else if ("Sweet Potato".equals(s)) {
            System.out.println(s);
            String dbName = "Ground Nuts";
            VideoPath = "https://youtube.com/shorts/lQx2vQS7D7Q?si=072wF5RQOEoWOLJb";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        } else if ("Okra".equals(s)) {
            System.out.println(s);
            String dbName = "Okra";
            VideoPath = "https://youtu.be/BEPIRr-UoXE?si=RVf7maICdaleNsHl";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        } else if ("Corn".equals(s)) {
            System.out.println(s);
            String dbName = "Corn";
            VideoPath = "https://youtu.be/KL8dUiahgj0?si=kiD5hXpJXIWPre9B";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        } else if ("Carrot".equals(s)) {
            System.out.println(s);
            String dbName = "Carrot";
            VideoPath = "https://youtu.be/yMJNXQo1Mvs?si=LnHg-LOEXTE-05nI";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Brassicas".equals(s)) {
            System.out.println(s);
            String dbName = "Brassicas";
            VideoPath = "https://youtu.be/yHpLi_TTn1A?si=43UL9RnZVY_Od8ON";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Garlic".equals(s)) {
            System.out.println(s);
            String dbName = "Garlic";
            VideoPath = "https://youtu.be/vLaA_7wkux4?si=OSFAJR20SCdDZSmL";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Legumes".equals(s)) {
            System.out.println(s);
            String dbName = "Legumes";
            VideoPath = "https://youtu.be/ahi1wmRUdcE?si=yCl3zOmD_s-Sb2Uk";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        } else if ("Mustard Greens".equals(s)) {
            System.out.println(s);
            String dbName = "Mustard Greens";
            VideoPath = "https://youtu.be/pL2dtrhEjlE?si=Vnb8aZ00fBeh61Uy";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        } else if ("Lettuce".equals(s)) {
            System.out.println(s);
            String dbName = "Lettuce";
            VideoPath = "https://youtu.be/yHpLi_TTn1A?si=7ESA9f8uUOWtgldh";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        } else if ("Barley".equals(s)) {
            System.out.println(s);
            String dbName = "Barley";
            VideoPath = "https://youtu.be/jiB79tr2pXA?si=yAM30wjc9wc43Xyq";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Faba Beans".equals(s)) {
            System.out.println(s);
            String dbName = "Faba beans";
            VideoPath = "https://youtu.be/o88pn57d3XE?si=9bX7JEzh18g7Jnff";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }
        else if ("Cabbage".equals(s)) {
            System.out.println(s);
            String dbName = "Cabbage";
            VideoPath = "https://youtu.be/yMJNXQo1Mvs?si=acJmcwU3lh-L3N72";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(dbName));

        }


    }

    @FXML
    void OnVideoLinkclk(ActionEvent event) throws IOException {
        VideoController vc = new VideoController();
        System.out.println(VideoPath);
        vc.showweb(VideoPath);
    }


    public String read(String cropName) {
        try {
            java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
            java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder()
                    .uri(java.net.URI.create("http://localhost:8080/api/crops/" + cropName.replace(" ", "%20")))
                    .GET()
                    .build();
            java.net.http.HttpResponse<String> response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                com.fasterxml.jackson.databind.JsonNode root = new com.fasterxml.jackson.databind.ObjectMapper().readTree(response.body());
                return root.get("description").asText();
            } else {
                return "Data not found for: " + cropName;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to fetch data from backend.";
        }
    }

    @FXML
    void onBtnClk(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/cropPlanning.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("identyfy!");
        stage.setScene(scene);
        stage.show();
    }
}

