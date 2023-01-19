package com.example.mediaplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 520, 340);

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        primaryStage.setTitle("Media Player - ПИОЗ-21 eako***v@edu.hse.ru");

        Scene scene = new Scene(root);
        scene.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2){
                primaryStage.setFullScreen(true);
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}