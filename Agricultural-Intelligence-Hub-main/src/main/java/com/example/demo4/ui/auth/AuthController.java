package com.example.demo4.ui.auth;

import com.example.demo4.Start;
import com.example.demo4.service.ApiService;
import com.example.demo4.state.AppState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {

    @FXML private TextField loginEmailField;
    @FXML private PasswordField loginPasswordField;
    @FXML private Button loginBtn;

    @FXML private TextField regEmailField;
    @FXML private PasswordField regPasswordField;
    @FXML private Button regBtn;

    @FXML private Label errorLabel;

    private final ApiService apiService = new ApiService();

    @FXML
    public void handleLogin(ActionEvent event) {
        String email = loginEmailField.getText();
        String password = loginPasswordField.getText();
        
        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter both email and password.");
            return;
        }

        loginBtn.setDisable(true);
        loginBtn.setText("Logging in...");
        errorLabel.setText("");

        apiService.login(email, password).thenAccept(token -> {
            Platform.runLater(() -> {
                if (token != null && !token.isEmpty()) {
                    AppState.getInstance().setJwtToken(token);
                    navigateToMain(event);
                } else {
                    errorLabel.setText("Invalid email or password.");
                    loginBtn.setDisable(false);
                    loginBtn.setText("Login");
                }
            });
        }).exceptionally(e -> {
            Platform.runLater(() -> {
                errorLabel.setText("Connection error or unauthorized.");
                loginBtn.setDisable(false);
                loginBtn.setText("Login");
            });
            return null;
        });
    }

    @FXML
    public void handleRegister(ActionEvent event) {
        String email = regEmailField.getText();
        String password = regPasswordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter both email and password.");
            return;
        }

        regBtn.setDisable(true);
        regBtn.setText("Registering...");
        errorLabel.setText("");

        apiService.register(email, password).thenAccept(token -> {
            Platform.runLater(() -> {
                if (token != null && !token.isEmpty()) {
                    AppState.getInstance().setJwtToken(token);
                    navigateToMain(event);
                } else {
                    errorLabel.setText("Registration failed. Email might be in use.");
                    regBtn.setDisable(false);
                    regBtn.setText("Create Account");
                }
            });
        }).exceptionally(e -> {
            Platform.runLater(() -> {
                errorLabel.setText("Connection error during registration.");
                regBtn.setDisable(false);
                regBtn.setText("Create Account");
            });
            return null;
        });
    }

    private void navigateToMain(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/Functions.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Agri-Hub Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Error loading dashboard.");
        }
    }
}
