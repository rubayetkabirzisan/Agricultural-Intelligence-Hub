package com.example.demo4.ui.weather;

import com.example.demo4.Start;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class AskAi implements Initializable {

    @FXML private TextArea chatHistory;
    @FXML private TextField userInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatHistory.appendText("AI: Hello! I am your Agricultural AI assistant. How can I help you today?\n\n");
    }

    @FXML
    public void sendMessage(ActionEvent event) {
        String message = userInput.getText().trim();
        if (message.isEmpty()) return;

        chatHistory.appendText("You: " + message + "\n\n");
        userInput.clear();

        // Send to backend async using ApiService
        com.example.demo4.service.ApiService.askAi(message)
            .thenAccept(jsonData -> {
                Platform.runLater(() -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode root = mapper.readTree(jsonData);
                        if (root.has("candidates")) {
                            String reply = root.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();
                            chatHistory.appendText("AI: " + reply + "\n\n");
                        } else if (root.has("title")) { // Error from GlobalExceptionHandler
                            chatHistory.appendText("AI: [Server Error: " + root.get("detail").asText() + "]\n\n");
                        }
                    } catch (Exception e) {
                        chatHistory.appendText("AI: [Error parsing AI response]\n\n");
                    }
                });
            })
            .exceptionally(ex -> {
                Platform.runLater(() -> chatHistory.appendText("AI: [Failed to connect to backend]\n\n"));
                return null;
            });
    }

    public void showai() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/MOONRESOURCES/AskAi.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("ASK AI");
        stage.setScene(scene);
        stage.show();
    }
}
