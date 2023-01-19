package com.example.mediaplayer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;

public class HelloController {

    private MediaPlayer mediaPlayer;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider sceneSlider;

    @FXML
    private MediaView mediaView;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser
                .ExtensionFilter("Select file (.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        String filePath = file.toURI().toString();
        if (filePath != null) {
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            //mediaView.setFitWidth(500);
            //mediaView.setFitHeight(500);
            DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty hight = mediaView.fitHeightProperty();

            width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            hight.bind(Bindings.selectDouble(mediaView.sceneProperty(), "hight"));

            volumeSlider.setValue(mediaPlayer.getVolume() * 100);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable observable) {
                    mediaPlayer.setVolume(volumeSlider.getValue() / 100);
                }
            });

            sceneSlider.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(Duration.seconds(sceneSlider.getValue()));
                }
            });
            mediaPlayer.play();
        }
    }

    @FXML
    private void playMedia() {
        mediaPlayer.play();
        mediaPlayer.setRate(1);
    }

    @FXML
    private void stopMedia() {
        mediaPlayer.stop();
    }

    @FXML
    private void pauseMedia() {
        mediaPlayer.pause();
    }

    @FXML
    private void slowMedia() {
        mediaPlayer.setRate(0.75);
    }

    @FXML
    private void verySlowMedia() {
        mediaPlayer.setRate(0.5);
    }

    @FXML
    private void fastMedia() {
        mediaPlayer.setRate(1.5);
    }

    @FXML
    private void veryFastMedia() {
        mediaPlayer.setRate(2);
    }

    @FXML
    private void exitMedia() {
        System.exit(0);
    }
}