module com.example.demo4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    requires com.dlsc.formsfx;
    requires jakarta.mail;
    requires com.fasterxml.jackson.databind;
    requires atlantafx.base;
    requires java.sql;
    requires java.net.http;

    opens com.example.demo4 to javafx.fxml;
    opens com.example.demo4.ui.weather to javafx.fxml;
    opens com.example.demo4.ui.auth to javafx.fxml;
    opens com.example.demo4.ui.profile to javafx.fxml;
    opens com.example.demo4.ui.analytics to javafx.fxml;

    exports com.example.demo4;
    exports com.example.demo4.ui.weather;
    exports com.example.demo4.ui.auth;
    exports com.example.demo4.ui.profile;
    exports com.example.demo4.ui.analytics;
}