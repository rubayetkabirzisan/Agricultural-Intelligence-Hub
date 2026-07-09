package com.example.demo4.ui.crops;

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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class cropPlanningController implements projectInterface {

    static String PathVideoRice;
    static String PathHyperlinkRice;
    static String PathVideoJute;
    static String PathHyperlinkJute;
    static Image img1;
    static Image img2;


    @FXML
    Label season;
    @FXML
    Label soilQuality;
    @FXML
    Label frst;
    @FXML
    Label Sec;


    @FXML
    Hyperlink name1;
    @FXML
    Hyperlink name2;

    @FXML
    ImageView imageView1;
    @FXML
    ImageView imageView2;

    static String interCroping1;
    static String interCroping2;



    @FXML
    public void playvideo(String path) throws IOException {
        ZishanvideoController wc=new ZishanvideoController();

        wc.showweb(path);


    }



    public void openFx(FXMLLoader fxmlLoader,ActionEvent event) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cropPlanning.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("identify!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public String read(String path) { return ""; }

    public void fetchAndSetCropData(String cropName, Label targetLabel) {
        targetLabel.setText("Loading...");
        com.example.demo4.service.ApiService.getCropData(cropName)
            .thenAccept(jsonData -> {
                try {
                    com.fasterxml.jackson.databind.JsonNode root = new com.fasterxml.jackson.databind.ObjectMapper().readTree(jsonData);
                    String description = root.has("description") ? root.get("description").asText() : "Data not found";
                    // Strip markdown formatting for cleaner UI
                    description = description.replace("**", "").replace("*", "").replace("##", "").replace("#", "");
                    String finalDesc = description;
                    javafx.application.Platform.runLater(() -> targetLabel.setText(finalDesc));
                } catch (Exception e) {
                    javafx.application.Platform.runLater(() -> targetLabel.setText("Failed to parse data."));
                }
            })
            .exceptionally(ex -> {
                javafx.application.Platform.runLater(() -> targetLabel.setText("Failed to fetch data."));
                return null;
            });
    }


    public void monsoonSandySoil(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/cropPlanning.fxml"));
        openFx(fxmlLoader,event);
        cropPlanningController controller= fxmlLoader.getController();
        PathVideoRice="https://www.youtube.com/watch?v=qHIKRwYe3Cs";
        PathHyperlinkRice="/com/example/demo4/ZishanResources/Zishanhtmll/T.html";
        PathVideoJute="https://www.youtube.com/watch?v=P-PrAS--qGA";
        PathHyperlinkJute="/com/example/demo4/ZishanResources/Zishanhtmll/jute.html";




        controller.imageView1.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_1.png")));
        controller.imageView2.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_2.png")));


        controller.name1.setText("Rice");
        controller.name2.setText("Jute");



        controller.season.setText("Monsoon");
        controller.soilQuality.setText("Sandy soil");

        controller.fetchAndSetCropData("Rice Summary", controller.frst);
        controller.fetchAndSetCropData("jute", controller.Sec);

        //initialize intercroping
        interCroping1="Rice";
        interCroping2="Jute";


    }





    public void monsoonClaySoil(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/cropPlanning.fxml"));
        openFx(fxmlLoader,event);
        cropPlanningController controller= fxmlLoader.getController();
        PathVideoRice="https://www.youtube.com/watch?v=qHIKRwYe3Cs";
        PathHyperlinkRice="/com/example/demo4/ZishanResources/Zishanhtmll/T.html";
        PathVideoJute="https://www.youtube.com/watch?v=l_wX9pdDfkA";
        PathHyperlinkJute="/com/example/demo4/ZishanResources/Zishanhtmll/taro.html";




        controller.imageView1.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_1.png")));
        controller.imageView2.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_3.png")));


        controller.name1.setText("Rice");
        controller.name2.setText("Taro");



        controller.season.setText("Monsoon");
        controller.soilQuality.setText("Clay soil");

        controller.fetchAndSetCropData("Rice Summary", controller.frst);
        controller.fetchAndSetCropData("taro", controller.Sec);

        interCroping1="Rice.";
        interCroping2="Taro";


    }

    public void summerSandySoil(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/cropPlanning.fxml"));
        openFx(fxmlLoader,event);
        cropPlanningController controller= fxmlLoader.getController();
        PathVideoRice="https://www.youtube.com/watch?v=O0KSZn5QuX0";
        PathHyperlinkRice="/com/example/demo4/ZishanResources/Zishanhtmll/corn.html";
        PathVideoJute="https://www.youtube.com/watch?v=hs24iV8DA1U";
        PathHyperlinkJute="/com/example/demo4/ZishanResources/Zishanhtmll/groundnuts.html";




        controller.imageView1.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_5.png")));
        controller.imageView2.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_4.png")));


        controller.name1.setText("Corn");
        controller.name2.setText("Groundnuts");



        controller.season.setText("Summer");
        controller.soilQuality.setText("Sandy soil");

        controller.fetchAndSetCropData("corn", controller.frst);
        controller.fetchAndSetCropData("groundnut", controller.Sec);

        interCroping1="Corn";
        interCroping2="Ground Nuts";

    }
    public void summerClaySoil(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/cropPlanning.fxml"));
        openFx(fxmlLoader,event);
        cropPlanningController controller= fxmlLoader.getController();
        PathVideoRice="https://www.youtube.com/watch?v=JpOtTh2z5CA";
        PathHyperlinkRice="/com/example/demo4/ZishanResources/Zishanhtmll/lentils.html";
        PathVideoJute="https://www.youtube.com/watch?v=vXVSw7wTsso";
        PathHyperlinkJute="/com/example/demo4/ZishanResources/Zishanhtmll/Sweet POtato.html";




        controller.imageView1.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_6.png")));
        controller.imageView2.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_7.png")));


        controller.name1.setText("Lentils");
        controller.name2.setText("Sweet Potato");



        controller.season.setText("Summer");
        controller.soilQuality.setText("Clay Soil");

        controller.fetchAndSetCropData("lentils", controller.frst);
        controller.fetchAndSetCropData("sweetpotato", controller.Sec);
        interCroping1="Lentils";
        interCroping2="Sweet Potato";

    }
    public void winterLoamySoil(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/cropPlanning.fxml"));
        openFx(fxmlLoader,event);
        cropPlanningController controller= fxmlLoader.getController();
        PathVideoRice="https://www.youtube.com/watch?v=Lzvld_ict9g";
        PathHyperlinkRice="/com/example/demo4/ZishanResources/Zishanhtmll/wheat.html";
        PathVideoJute="https://www.youtube.com/watch?v=WJN6ZVV4LCo";
        PathHyperlinkJute="/com/example/demo4/ZishanResources/Zishanhtmll/carrot.html";




        controller.imageView1.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_8.png")));
        controller.imageView2.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_9.png")));


        controller.name1.setText("Wheat");
        controller.name2.setText("Carrot");



        controller.season.setText("Winter");
        controller.soilQuality.setText("Loamy soil");

        controller.fetchAndSetCropData("wheat", controller.frst);
        controller.fetchAndSetCropData("carrot", controller.Sec);

        interCroping1="Wheat";
        interCroping2="Carrot";

    }
    public void winterClaySoil(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/cropPlanning.fxml"));
        openFx(fxmlLoader,event);
        cropPlanningController controller= fxmlLoader.getController();
        PathVideoRice="https://www.youtube.com/watch?v=ghBI0Kv7yWI";
        PathHyperlinkRice="/com/example/demo4/ZishanResources/Zishanhtmll/mustard.html";
        PathVideoJute="https://www.youtube.com/watch?v=ubY_gUm5VA8";
        PathHyperlinkJute="/com/example/demo4/ZishanResources/Zishanhtmll/spinach.html";




        controller.imageView1.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_10.png")));
        controller.imageView2.setImage(new Image(getClass().getResourceAsStream("/com/example/demo4/ZishanResources/Image rice and jute/img_11.png")));


        controller.name1.setText("Mustard");
        controller.name2.setText("Spinach");



        controller.season.setText("Winter");
        controller.soilQuality.setText("Clay soil");

        controller.fetchAndSetCropData("mustard", controller.frst);
        controller.fetchAndSetCropData("spinach", controller.Sec);
        interCroping1="Mustard";
        interCroping2="Spinach";

    }
    @FXML
    public void name1clk(ActionEvent event) throws IOException {
        playvideo(PathHyperlinkRice);
    }
    @FXML
    public void onimg1clk()throws IOException
    {
        playvideo('v'+PathVideoRice);
    }
    @FXML
    public void name2clk(ActionEvent event) throws IOException {
        playvideo(PathHyperlinkJute);
    }
    @FXML
    public void onimg2clk()throws IOException
    {
        playvideo('v'+PathVideoJute);
    }

    @FXML
    public void interCropingBtn1(ActionEvent event) throws IOException {
        com.example.demo4.state.AppState.getInstance().setSelectedCropName(interCroping1);

        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/Intercropping.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = com.example.demo4.state.AppState.getInstance().getPrimaryStage();
        stage.setTitle("Agri-Hub — Intercropping Guide");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void interCropingBtn2(ActionEvent event) throws IOException {
        com.example.demo4.state.AppState.getInstance().setSelectedCropName(interCroping2);

        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/Intercropping.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = com.example.demo4.state.AppState.getInstance().getPrimaryStage();
        stage.setTitle("Agri-Hub — Intercropping Guide");
        stage.setScene(scene);
        stage.show();
    }


    static public String getMainCrop(){
        return com.example.demo4.state.AppState.getInstance().getSelectedCropName();
    }




    @FXML
    public void onbackbtnclk(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/ZishanResources/selectSeassonAndSand.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("identify!");
        stage.setScene(scene);
        stage.show();
    }











    //EMPTY METHODS MOON
    @Override
    public void showWheatDiseases(ActionEvent event) {

    }

    @Override
    public void showRiceDiseases(ActionEvent event) {

    }

    @Override
    public void showJuteDiseases(ActionEvent event) {

    }

    @Override
    public void showMaizeDiseases(ActionEvent event) {

    }

    @Override
    public void onName1Clk(ActionEvent event) throws IOException {

    }

    @Override
    public void onName2Clk(ActionEvent event) throws IOException {

    }

    @Override
    public void onName3Clk(ActionEvent event) throws IOException {

    }

    @Override
    public void onName4Clk(ActionEvent event) throws IOException {

    }

    @Override
    public void prevBtn(ActionEvent event) throws IOException {

    }

    //overwritten method arif

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