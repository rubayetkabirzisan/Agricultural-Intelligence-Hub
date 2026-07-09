package com.example.demo4.ui.weather;

import com.example.demo4.Start;
import com.example.demo4.projectInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class SelectDiseasesController implements projectInterface {

    @FXML
    public Label lb1;
    @FXML
    public Label lb2;
    @FXML
    public Label lb3;
    @FXML
    public Label lb4;
    @FXML
    Hyperlink name1;
    @FXML
    Hyperlink name11;
    @FXML
    Hyperlink name2;
    @FXML
    Hyperlink name22;
    @FXML
    Hyperlink name3;
    @FXML
    Hyperlink name33;
    @FXML
    Hyperlink name4;
    @FXML
    Hyperlink name44;

    @FXML
     ImageView imgView1;
    @FXML
     ImageView imgView2;
    @FXML
     ImageView imgView3;
    @FXML
     ImageView imgView4;


    static Image img1;
    static Image img2;
    static Image img3;
    static Image img4;










    static String webAddressName1;
    static String webAddressName2;
    static String webAddressName3;
    static String webAddressName4;

    public String read(String path,int a) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line;
            int itr=a;
            while ((line = br.readLine()) != null) {
                if(itr>a)
                    content.append(line).append("\n");
                itr++;
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void openFx(FXMLLoader fxmlLoader, ActionEvent event) throws IOException {

    }

    public String read(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line;

            line = br.readLine();
            content.append(line).append("\n");
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }












    private void fetchAndShowDiseases(ActionEvent event, String cropName) {
        com.example.demo4.service.ApiService.getDiseaseData(cropName)
            .thenAccept(jsonData -> {
                javafx.application.Platform.runLater(() -> {
                    try {
                        com.fasterxml.jackson.databind.JsonNode root = new com.fasterxml.jackson.databind.ObjectMapper().readTree(jsonData);
                        if (root.isArray() && root.size() >= 4) {
                            String name1 = root.get(0).get("name").asText();
                            String desc1 = root.get(0).get("description").asText();
                            String name2 = root.get(1).get("name").asText();
                            String desc2 = root.get(1).get("description").asText();
                            String name3 = root.get(2).get("name").asText();
                            String desc3 = root.get(2).get("description").asText();
                            String name4 = root.get(3).get("name").asText();
                            String desc4 = root.get(3).get("description").asText();
                            
                            loadFXMLAndSetLabels(event, desc1, desc2, desc3, desc4, name1, name2, name3, name4);
                        } else {
                            System.out.println("Error: Backend did not return expected disease array.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            })
            .exceptionally(ex -> {
                ex.printStackTrace();
                return null;
            });
    }

    @FXML
    public void showWheatDiseases(ActionEvent event) {
        fetchAndShowDiseases(event, "Wheat");
    }

    public void showRiceDiseases(ActionEvent event) {
        img1=new Image(getClass().getResourceAsStream("/com/example/demo4/MOONRESOURCES/Rice/bacterial_blight.jpg"));
        img2=new Image(getClass().getResourceAsStream("/com/example/demo4/MOONRESOURCES/Rice/bacterial-leaf-streak.jpg"));
        img3=new Image(getClass().getResourceAsStream("/com/example/demo4/MOONRESOURCES/Rice/blast-leaf-4.jpg"));
        img4=new Image(getClass().getResourceAsStream("/com/example/demo4/MOONRESOURCES/Rice/blast-neck-panicle.jpg"));
        webAddressName1= "/com/example/demo4/MOONRESOURCES/Rice/riceHtml/Bacterial_blight - IRRI Rice Knowledge Bank.html";
        webAddressName2= "/com/example/demo4/MOONRESOURCES/Rice/riceHtml/Bacterial_leaf_streak.html";
        webAddressName3= "/com/example/demo4/MOONRESOURCES/Rice/riceHtml/Blast_leaf_and_collar.html";
        webAddressName4= "/com/example/demo4/MOONRESOURCES/Rice/riceHtml/Blast_node_and_neck.html";
        fetchAndShowDiseases(event, "Rice");
    }

    public void showJuteDiseases(ActionEvent event) {
        img1=new Image(getClass().getResourceAsStream("/com/example/demo4/MOONRESOURCES/Jute/Jute Anthracnose_files/anth1.jpeg"));
        img2=new Image(getClass().getResourceAsStream("/com/example/demo4/MOONRESOURCES/Jute/Jute Red Rot_files/rr1.jpeg"));
        img3=new Image(getClass().getResourceAsStream("/com/example/demo4/MOONRESOURCES/Jute/JuteStemRot_files/jsr3.jpeg"));
        img4=new Image(getClass().getResourceAsStream("/com/example/demo4/MOONRESOURCES/Jute/JuteYelloMosaicFinal_files/JYM2.jpeg"));
        webAddressName1= "/com/example/demo4/MOONRESOURCES/Jute/Jute Anthracnose.html";
        webAddressName2= "/com/example/demo4/MOONRESOURCES/Jute/Jute Red Rot.html";
        webAddressName3= "/com/example/demo4/MOONRESOURCES/Jute/JuteStemRot.html";
        webAddressName4= "/com/example/demo4/MOONRESOURCES/Jute/JuteYelloMosaicFinal.html";
        fetchAndShowDiseases(event, "Jute");
    }

    public void showMaizeDiseases(ActionEvent event) {
        fetchAndShowDiseases(event, "Maize");
    }

    private void loadFXMLAndSetLabels(ActionEvent event, String lb1Text, String lb2Text, String lb3Text, String lb4Text, String Name1, String Name2, String Name3, String Name4) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/MOONRESOURCES/SelectDiseases.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            SelectDiseasesController controller = fxmlLoader.getController();
            if (controller != null) {
                controller.name1.setText(Name1);
                controller.name11.setText(Name1);
                controller.name2.setText(Name2);
                controller.name22.setText(Name2);
                controller.name3.setText(Name3);
                controller.name33.setText(Name3);
                controller.name4.setText(Name4);
                controller.name44.setText(Name4);




                controller.imgView1.setImage(img1);
                controller.imgView2.setImage(img2);
                controller.imgView3.setImage(img3);
                controller.imgView4.setImage(img4);




                controller.lb1.setText(lb1Text);
                controller.lb2.setText(lb2Text);
                controller.lb3.setText(lb3Text);
                controller.lb4.setText(lb4Text);
            } else {
                System.out.println("Label or controller is null");
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("WHEAT");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   private void playvideo(String path) throws IOException {
       if (path == null) return;
       com.example.demo4.ui.crops.ZishanvideoController wc = new com.example.demo4.ui.crops.ZishanvideoController();
       wc.showweb(path);
   }

   @FXML
   public void onName1Clk(ActionEvent event) throws IOException {
       playvideo(webAddressName1);
   }

   @FXML
   public void onName2Clk(ActionEvent event) throws IOException {
       playvideo(webAddressName2);
   }

   @FXML
   public void onName3Clk(ActionEvent event) throws IOException {
       playvideo(webAddressName3);
   }

   @FXML
   public void onName4Clk(ActionEvent event) throws IOException {
       playvideo(webAddressName4);
   }



    @FXML
    public void prevBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/MOONRESOURCES/diseases.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("identyfy!");
        stage.setScene(scene);
        stage.show();
    }


    //OVERRIDE METHOD OF ZISHAN

    @Override
    public void monsoonSandySoil(ActionEvent event) throws IOException {

    }

    @Override
    public void monsoonClaySoil(ActionEvent event) throws IOException {

    }

    @Override
    public void summerSandySoil(ActionEvent event) throws IOException {

    }

    @Override
    public void summerClaySoil(ActionEvent event) throws IOException {

    }

    @Override
    public void winterLoamySoil(ActionEvent event) throws IOException {

    }

    @Override
    public void winterClaySoil(ActionEvent event) throws IOException {

    }

    @Override
    public void name1clk(ActionEvent event) throws IOException {

    }

    @Override
    public void onimg1clk() throws IOException {

    }

    @Override
    public void name2clk(ActionEvent event) throws IOException {

    }

    @Override
    public void onimg2clk() throws IOException {

    }

    @Override
    public void interCropingBtn1(ActionEvent event) throws IOException {

    }

    @Override
    public void interCropingBtn2(ActionEvent event) throws IOException {

    }

    @Override
    public void onbackbtnclk(ActionEvent event) throws IOException {

    }




    //OverWritten methods ARIF

    @Override
    public void connect(ActionEvent event) {

    }

    @Override
    public void saveContactInfo(String email, String phone, String messageContent) {

    }

    @Override
    public void sendEmail(String recipient) {

    }

    @Override
    public void onHomeBtnClk(ActionEvent event) throws IOException {

    }
}
