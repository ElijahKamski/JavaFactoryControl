package com.example.controlsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

import static com.example.controlsystem.utils.ReadFrom;

public class rabint extends Application {
    Stage stage=new Stage();
    private String login;


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        rabintCont controller = fxmlLoader.getController();
        String permission = ReadFrom("select status from workers where login = '" +
                login +
                "'");
        System.out.println(permission);
        controller.setPerm(permission, login);
        System.out.println("cont is set");
        Vector <String> a = utils.getDatesWindow(7);
        for (String i:a){
            System.out.println(i);
        }
        primaryStage.setTitle("HextOne!");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
    public void showWindow(String login1) throws Exception {
        login = login1;
        start(stage);
    }
}
