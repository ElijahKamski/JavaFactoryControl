module com.example.controlsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.controlsystem to javafx.fxml;
    exports com.example.controlsystem;
}