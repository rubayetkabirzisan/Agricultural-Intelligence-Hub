package com.example.demo4;

import com.example.demo4.state.AppState;
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

        // AtlantaFX PrimerDark as the base theme layer
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/Auth.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Load our custom design system on top of PrimerDark
        scene.getStylesheets().add(
            Start.class.getResource("/com/example/demo4/styles/application.css").toExternalForm()
        );

        // Store the primary stage globally — all controllers navigate through this
        AppState.getInstance().setPrimaryStage(stage);

        // Canonical window dimensions — consistent across ALL screens
        stage.setTitle("Agri-Hub — Agricultural Intelligence Hub");
        stage.setWidth(1200);
        stage.setHeight(760);
        stage.setMinWidth(900);
        stage.setMinHeight(620);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}