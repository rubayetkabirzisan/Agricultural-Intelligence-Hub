package com.example.demo4.ZishanJavaClass;


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
        if (s == "Rice") {
            ObservableList<String> l = FXCollections.observableArrayList("Soybean", "Pumpkins", "Cucumber");
            cropComboBox.setItems(l);
        } else if (s == "Jute") {
            ObservableList<String> l = FXCollections.observableArrayList("Mung Beans", "Cucumber ", "Pumpkins ");
            cropComboBox.setItems(l);

        } else if (s == "Rice.") {
            ObservableList<String> l = FXCollections.observableArrayList("Mung Beans ", "Soybean", "Ginger ");
            cropComboBox.setItems(l);
        }
        else if (s == "Taro") {
        ObservableList<String> l = FXCollections.observableArrayList("Ginger", "Spinach", "Turmeric" );
        cropComboBox.setItems(l);
    }
        else if (s == "Corn") {
            ObservableList<String> l = FXCollections.observableArrayList("Pumpkin ","Bean","Soybean ");
            cropComboBox.setItems(l);
        }
        else if (s == "Ground Nuts") {
            ObservableList<String> l = FXCollections.observableArrayList("Cowpeas","Corn ","Mung Beans  ");
            cropComboBox.setItems(l);
        }
        else if (s == "Lentils") {
            ObservableList<String> l = FXCollections.observableArrayList("Maize","Sunflowers","Sorghum");
            cropComboBox.setItems(l);
        }
        else if (s == "Sweet Potato") {
            ObservableList<String> l = FXCollections.observableArrayList("Squash","Eggplants","Bush Beans");
            cropComboBox.setItems(l);
        }
        else if (s == "Mustard") {
            ObservableList<String> l = FXCollections.observableArrayList("Chickpeas","Leafy Greens","Radishes");
            cropComboBox.setItems(l);
        }
        else if (s == "Spinach") {
            ObservableList<String> l = FXCollections.observableArrayList("Carrot","Garlic","Brassicas");
            cropComboBox.setItems(l);
        }
        else if (s == "Wheat") {
            ObservableList<String> l = FXCollections.observableArrayList("Legumes","Barley","Faba Beans");
            cropComboBox.setItems(l);
        }
        else if (s == "Carrot") {
            ObservableList<String> l = FXCollections.observableArrayList("Peas","Onions","Radishes ");
            cropComboBox.setItems(l);
        }


        //  cropComboBox.getItems().addAll("Pumpkins","Cucumber","Soybean","Mung Beans","Taro","Turmeric","Ginger","Spinach","Sweet Potato","Okra","Corn","Carrot","Legumes","Mustard Greens","Lettuce","Barley","Cabbage");
    }


    @FXML
    void ButtonOk(ActionEvent event) {
        String s = cropComboBox.getValue();
        System.out.println(s);

        if (s == "Soybean") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Soyabin.txt";
            VideoPath = "https://youtu.be/3-0IQPPMrgI?si=3J1ConeN2fEpfw98";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Maize") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Maize.txt";
            VideoPath = "https://youtu.be/KL8dUiahgj0?si=BJEpfDOF5FW7GfDd";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Peas") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Peas.txt";
            VideoPath = "https://youtu.be/D4fjOf1YjiE?si=ou6vJROnAijxHHlz";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Radishes ") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/radishess.txt";
            VideoPath = "https://youtube.com/shorts/BX1CEJo20yc?si=z3sE3j1ZtZBJYlfk";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Onions") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Onions.txt";
            VideoPath = "https://youtu.be/vhD1dMHJwBc?si=bqVQISmoErfihy5q";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Chickpeas") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Chickpeas.txt";
            VideoPath = "https://youtu.be/2wSoX_8MBEs?si=hzraMbWCAgh17Tsp";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Leafy Greens") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Leafy Greens.txt";
            VideoPath = "https://youtube.com/shorts/gAOggj5YUUQ?si=AscSENr4LzI9A7tW";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Radishes") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Radishes.txt";
            VideoPath = "https://youtu.be/tyYfR9wmhiw?si=wItab07HzfJgFNlU";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Squash") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Squash.txt";
            VideoPath = "https://youtu.be/d2EHGLnrCV8?si=lcP2A42g3tdRK3Lq";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Eggplants") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Eggplants.txt";
            VideoPath = "https://youtu.be/boyxjlRcjbQ?si=muaFMBrmKNbLM3k_";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Bush Beans") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Bush Beans.txt";
            VideoPath = "https://youtu.be/d2EHGLnrCV8?si=EDTjx34c13RIFk7u";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Sunflowers") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Sunflowers.txt";
            VideoPath = "https://youtu.be/b3K8LHjo9SA?si=kBiPgKoc7KllVsOy";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Sorghum") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Sorghum.txt";
            VideoPath = "https://youtu.be/Bt9XX5K7OX8?si=jyMpqKFd_3XmfHjO";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else  if(s=="Cowpeas"){
            System.out.println(s);
            path= "CSVFILES/ZishanFiles/ZishanCSVFILES/Cowpeas.txt";
            VideoPath="https://youtu.be/thcP8Snu9j0?si=QDRaGAqmO2XM00p4";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }

        else  if(s=="Corn "){
            System.out.println(s);
            path= "CSVFILES/ZishanFiles/ZishanCSVFILES/Corn .txt";
            VideoPath="https://youtu.be/thcP8Snu9j0?si=4if8drl0zOBxBlIw";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }

        else  if(s=="Mung Beans  "){
            System.out.println(s);
            path= "CSVFILES/ZishanFiles/ZishanCSVFILES/mungbeans.txt";
            VideoPath="https://youtu.be/thcP8Snu9j0?si=4if8drl0zOBxBlIw";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else  if(s=="Bean"){
            System.out.println(s);
            path= "CSVFILES/ZishanFiles/ZishanCSVFILES/Bean.txt";
            VideoPath="https://youtu.be/bTujZHu8TZ8?si=4f2oT3bZjnyFkK_M";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else  if(s=="Pumpkin "){
            System.out.println(s);
            path= "CSVFILES/ZishanFiles/ZishanCSVFILES/Pumpkin .txt";
            VideoPath="https://youtu.be/cDbBr5oZpLE?si=bBhZzqHxssilFlzQ";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }

        else  if(s=="Soybean "){
            System.out.println(s);
            path= "CSVFILES/ZishanFiles/ZishanCSVFILES/soybeans.txt";
            VideoPath="https://youtu.be/J5bdz03TSS0?si=c1SFP_Hcpd9OzVqe";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }

        else if (s == "Cucumber") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Cucumber.txt";
            VideoPath = "https://youtu.be/AprcXULHGmw?si=QsrsArLhFHzOXSUe";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else  if(s=="Cucumber "){
            System.out.println(s);
            path= "CSVFILES/ZishanFiles/ZishanCSVFILES/Cucumber .txt";
            VideoPath="https://youtu.be/cyQFzd44PPU?si=ltReq1G0kfsHcXox";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }

        else if (s == "Pumpkins") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Pumpkin.txt";
            VideoPath = "https://youtu.be/iKxw1mu3JQ8?si=myYuViE2Gahm56WB";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Pumpkins ") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Pumpkins .txt";
            VideoPath = "https://youtube.com/shorts/MminmMy5N0w?si=HXnAaFbJlKY6nT-t";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Mung Beans") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Mung Beans.txt";
            VideoPath = "https://youtu.be/uFV_TUAmvEY?si=OISV_g6yAc5IuMw2";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else  if(s=="Mung Beans "){
            System.out.println(s);
            path= "CSVFILES/ZishanFiles/ZishanCSVFILES/Mung Beans .txt";
            VideoPath="https://youtu.be/n4f-jtH7bvY?si=y92MbU67qoi48EpJ";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Ginger") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Ginger.txt";
            VideoPath = "https://youtu.be/3c7j7TzjOc8?si=Jb9n0Suf-VXO27SE";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else  if(s=="Ginger "){
            System.out.println(s);
            path= "CSVFILES/ZishanFiles/ZishanCSVFILES/Ginger .txt";
            VideoPath="https://youtube.com/shorts/wqMtDNHUdRY?si=7TuHM4ykfwc_Gn7C";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Turmeric") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Turmeric.txt";
            VideoPath = "https://youtube.com/shorts/jv-hd1xpmPM?si=lTYLc35lcsOeNRkR";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        } else if (s == "Spinach") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Spinach.txt";
            VideoPath = "https://youtube.com/shorts/bLRFhIm_KTY?si=3QXtVwgtRJlKFDJ2";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        } else if (s == "Sweet Potato") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Ground Nuts.txt";
            VideoPath = "https://youtube.com/shorts/lQx2vQS7D7Q?si=072wF5RQOEoWOLJb";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        } else if (s == "Okra") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Okra.txt";
            VideoPath = "https://youtu.be/BEPIRr-UoXE?si=RVf7maICdaleNsHl";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        } else if (s == "Corn") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Corn.txt";
            VideoPath = "https://youtu.be/KL8dUiahgj0?si=kiD5hXpJXIWPre9B";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        } else if (s == "Carrot") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Carrot.txt";
            VideoPath = "https://youtu.be/yMJNXQo1Mvs?si=LnHg-LOEXTE-05nI";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Brassicas") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Brassicas.txt";
            VideoPath = "https://youtu.be/yHpLi_TTn1A?si=43UL9RnZVY_Od8ON";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Garlic") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Garlic.txt";
            VideoPath = "https://youtu.be/vLaA_7wkux4?si=OSFAJR20SCdDZSmL";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Legumes") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Legumes.txt";
            VideoPath = "https://youtu.be/ahi1wmRUdcE?si=yCl3zOmD_s-Sb2Uk";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        } else if (s == "Mustard Greens") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Mustard Greens.txt";
            VideoPath = "https://youtu.be/pL2dtrhEjlE?si=Vnb8aZ00fBeh61Uy";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        } else if (s == "Lettuce") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Lettuce.txt";
            VideoPath = "https://youtu.be/yHpLi_TTn1A?si=7ESA9f8uUOWtgldh";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        } else if (s == "Barley") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Barley.txt";
            VideoPath = "https://youtu.be/jiB79tr2pXA?si=yAM30wjc9wc43Xyq";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Faba Beans") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Faba beans.txt";
            VideoPath = "https://youtu.be/o88pn57d3XE?si=9bX7JEzh18g7Jnff";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }
        else if (s == "Cabbage") {
            System.out.println(s);
            path = "CSVFILES/ZishanFiles/ZishanCSVFILES/Cabbage.txt";
            VideoPath = "https://youtu.be/yMJNXQo1Mvs?si=acJmcwU3lh-L3N72";
            // imageView.setImage(new Image(getClass().getResourceAsStream("/Photos/img.png")));
            intercroppingProcedureLabel.setText(read(path));

        }


    }

    @FXML
    void OnVideoLinkclk(ActionEvent event) throws IOException {
        VideoController vc = new VideoController();
        System.out.println(VideoPath);
        vc.showweb(VideoPath);
    }


    public String read(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
