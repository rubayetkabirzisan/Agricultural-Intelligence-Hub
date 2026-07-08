package com.example.demo4.state;

/**
 * Singleton class to manage the global state of the JavaFX Application.
 * Replaces scattered 'static' variables in controllers.
 */
public class AppState {

    private static AppState instance;

    private String selectedCropName;
    private String jwtToken;

    private AppState() {
    }

    public static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    public String getSelectedCropName() {
        return selectedCropName;
    }

    public void setSelectedCropName(String selectedCropName) {
        this.selectedCropName = selectedCropName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
