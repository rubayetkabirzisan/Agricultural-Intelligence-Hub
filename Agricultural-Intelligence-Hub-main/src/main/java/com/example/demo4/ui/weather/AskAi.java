package com.example.demo4.ui.weather;

import com.example.demo4.Start;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class AskAi implements Initializable {

    @FXML private TextArea chatHistory;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    /**
     * Conversation memory — each entry is a {role, content} map.
     * role = "user" or "model"
     * Sent to the backend with every request so Gemini has full context.
     * Capped at MAX_HISTORY entries (20 turns = 10 exchanges) to stay within token limits.
     */
    private final List<Map<String, String>> conversationHistory = new ArrayList<>();
    private static final int MAX_HISTORY = 20;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chatHistory.appendText("AgriBot: Hello! I'm AgriBot, your Agricultural AI Expert. 🌿\n\n");
        chatHistory.appendText("I can help you with:\n");
        chatHistory.appendText("  • Crop selection and planning\n");
        chatHistory.appendText("  • Disease identification and treatment\n");
        chatHistory.appendText("  • Soil management and fertilizers\n");
        chatHistory.appendText("  • Weather impact on farming\n");
        chatHistory.appendText("  • Intercropping and yield optimization\n\n");
        chatHistory.appendText("What would you like to know?\n\n");
        chatHistory.appendText("─".repeat(50) + "\n\n");
    }

    @FXML
    public void sendMessage(ActionEvent event) {
        String message = userInput.getText().trim();
        if (message.isEmpty()) return;

        // Append user message to chat display
        chatHistory.appendText("You: " + message + "\n\n");
        userInput.clear();

        // Show loading state
        sendButton.setDisable(true);
        sendButton.setText("⏳ Thinking...");
        chatHistory.appendText("AgriBot: ⏳ Thinking...\n\n");

        // Send to backend with full conversation history for context
        com.example.demo4.service.ApiService.askAi(message, new ArrayList<>(conversationHistory))
            .thenAccept(jsonData -> {
                Platform.runLater(() -> {
                    try {
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode root = mapper.readTree(jsonData);

                        String reply;
                        if (root.has("candidates")) {
                            reply = root.path("candidates").get(0)
                                       .path("content").path("parts").get(0)
                                       .path("text").asText();
                            reply = reply.replaceAll("\\*\\*", "").replaceAll("\\*", "");
                        } else if (root.has("title")) {
                            reply = "[Server Error: " + root.get("detail").asText() + "]";
                        } else {
                            reply = "[Unexpected response format]";
                        }

                        // Replace the thinking text with the actual reply
                        String currentText = chatHistory.getText();
                        chatHistory.setText(currentText.replace("AgriBot: ⏳ Thinking...\n\n", "AgriBot: " + reply + "\n\n"));
                        chatHistory.appendText("─".repeat(50) + "\n\n");

                        // Add this exchange to conversation memory
                        addToHistory("user", message);
                        addToHistory("model", reply);

                    } catch (Exception e) {
                        String currentText = chatHistory.getText();
                        chatHistory.setText(currentText.replace("AgriBot: ⏳ Thinking...\n\n", "AgriBot: [Error parsing AI response]\n\n"));
                    } finally {
                        restoreSendButton();
                    }
                });
            })
            .exceptionally(ex -> {
                Platform.runLater(() -> {
                    String currentText = chatHistory.getText();
                    chatHistory.setText(currentText.replace("AgriBot: ⏳ Thinking...\n\n", "AgriBot: [Failed to connect to backend — is the server running?]\n\n"));
                    restoreSendButton();
                });
                return null;
            });
    }

    /** Adds a message to history and trims to MAX_HISTORY entries. */
    private void addToHistory(String role, String content) {
        Map<String, String> entry = new HashMap<>();
        entry.put("role", role);
        entry.put("content", content);
        conversationHistory.add(entry);
        // Keep only the most recent MAX_HISTORY entries to stay within token limits
        while (conversationHistory.size() > MAX_HISTORY) {
            conversationHistory.remove(0);
        }
    }

    private void restoreSendButton() {
        sendButton.setDisable(false);
        sendButton.setText("Send ▶");
    }

    /** Quick action: pre-fills the input with a common farming question */
    @FXML
    public void quickAsk(ActionEvent event) {
        Button btn = (Button) event.getSource();
        userInput.setText(btn.getUserData().toString());
        userInput.requestFocus();
        userInput.positionCaret(userInput.getText().length());
    }

    /** Back button — navigates to the main dashboard */
    @FXML
    public void goHome() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/Functions.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = com.example.demo4.state.AppState.getInstance().getPrimaryStage();
        stage.setTitle("Agri-Hub — Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    /** Called from functions.java — opens or navigates to the AskAi screen */
    public void showai() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/com/example/demo4/MOONRESOURCES/AskAi.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = com.example.demo4.state.AppState.getInstance().getPrimaryStage();
        stage.setTitle("Agri-Hub — AI Expert");
        stage.setScene(scene);
        stage.show();
    }
}
