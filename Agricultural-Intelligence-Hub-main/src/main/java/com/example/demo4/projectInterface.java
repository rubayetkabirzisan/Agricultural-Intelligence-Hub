package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

// Define an interface for the crop planning controller
public interface projectInterface {


    //ZISHANS PART
    void openFx(FXMLLoader fxmlLoader, ActionEvent event) throws IOException;

    String read(String path);

    void monsoonSandySoil(ActionEvent event) throws IOException;

    void monsoonClaySoil(ActionEvent event) throws IOException;

    void summerSandySoil(ActionEvent event) throws IOException;

    void summerClaySoil(ActionEvent event) throws IOException;

    void winterLoamySoil(ActionEvent event) throws IOException;

    void winterClaySoil(ActionEvent event) throws IOException;

    void name1clk(ActionEvent event) throws IOException;

    void onimg1clk() throws IOException;

    void name2clk(ActionEvent event) throws IOException;

    void onimg2clk() throws IOException;

    void interCropingBtn1(ActionEvent event) throws IOException;

    void interCropingBtn2(ActionEvent event) throws IOException;



    void onbackbtnclk(ActionEvent event) throws IOException;





    //moons part


    void showWheatDiseases(ActionEvent event);

    void showRiceDiseases(ActionEvent event);

    void showJuteDiseases(ActionEvent event);

    void showMaizeDiseases(ActionEvent event);

    void onName1Clk(ActionEvent event) throws IOException;

    void onName2Clk(ActionEvent event) throws IOException;

    void onName3Clk(ActionEvent event) throws IOException;

    void onName4Clk(ActionEvent event) throws IOException;

    void prevBtn(ActionEvent event) throws IOException;





    //ARIFS PART

    void connect(ActionEvent event);

    void saveContactInfo(String email, String phone, String messageContent);

    void sendEmail(String recipient);

    void onHomeBtnClk(ActionEvent event) throws IOException;




}
