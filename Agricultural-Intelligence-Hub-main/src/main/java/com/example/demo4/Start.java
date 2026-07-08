package com.example.demo4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import atlantafx.base.theme.PrimerDark;
import java.io.IOException;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseManager.initializeDatabase();
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());
        
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/Auth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("identyfy!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}