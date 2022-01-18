package com.example.controlsystem;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static com.example.controlsystem.utils.ReadFrom;
import static com.example.controlsystem.utils.getDatesWindow;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private Label welcomeText;

    @FXML
    void onHelloButtonClick(ActionEvent event) throws Exception {
        String name = login_field.getText();
        if (Objects.equals(ReadFrom("select coalesce((select status from workers where login='" +
                name +
                "'), '0') as res1"), "0")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Пользователь с таким логином не существует", ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                login_field.clear();
            }
            return;
        }
        rabint sec = new rabint();
        sec.showWindow(name);
    }

    @FXML
    void initialize() {
        assert login_field != null : "fx:id=\"login_field\" was not injected: check your FXML file 'login.fxml'.";
        assert welcomeText != null : "fx:id=\"welcomeText\" was not injected: check your FXML file 'login.fxml'.";

    }

}
