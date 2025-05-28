package com.mycat.catperson.ui;

import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.dsl.FXGL;
import com.mycat.catperson.Map;
import com.mycat.catperson.Timer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;

public class FailSubScene extends SubScene {

    private static final double MOVES_DISPLAY_X = 350;
    private static final double MOVES_DISPLAY_Y = 500;
    private static final double MAIN_MENU_BUTTON_X = 450;
    private static final double MAIN_MENU_BUTTON_Y = 750;

    private MediaPlayer mediaPlayer;
    private MediaPlayer buttonSoundPlayer;
    private static final String BUTTON_SOUND_FILE = "src/main/resources/cat.mp3";

    public FailSubScene() {
        Pane root = new Pane();
        root.setPrefWidth(FXGL.getAppWidth());
        root.setPrefHeight(FXGL.getAppHeight());

        Image image = new Image("Fail.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        root.setBackground(new Background(backgroundImage));

        int moves = Map.getCount();
        HBox movesDisplay = createMovesDisplay(moves);
        movesDisplay.setLayoutX(MOVES_DISPLAY_X);
        movesDisplay.setLayoutY(MOVES_DISPLAY_Y);

        Button mainMenuButton = createButtonWithImage("", "WINBACK.png", 128, 128, e -> {
            FXGL.getSceneService().popSubScene();
            FXGL.getSceneService().pushSubScene(new TestMainMenu());
            Map.clearhistory();
            Map.setCount(0);
        });
        mainMenuButton.setLayoutX(MAIN_MENU_BUTTON_X);
        mainMenuButton.setLayoutY(MAIN_MENU_BUTTON_Y);

        root.getChildren().addAll(movesDisplay, mainMenuButton);
        getContentRoot().getChildren().add(root);
    }

    private HBox createMovesDisplay(int moves) {
        HBox hbox = new HBox(5);
        hbox.setAlignment(Pos.CENTER);

        String movesStr = String.format("%03d", moves);

        for (int i = 0; i < movesStr.length(); i++) {
            int digit = Character.getNumericValue(movesStr.charAt(i));
            Texture digitTexture = Timer.getTexture(digit);
            ImageView digitView = new ImageView(digitTexture.getImage());
            digitView.setFitWidth(64);
            digitView.setFitHeight(128);
            hbox.getChildren().add(digitView);
        }

        return hbox;
    }

    private Button createButtonWithImage(String buttonText, String imagePath, double width, double height, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);

        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        button.setOnAction(action);

        return button;
    }

    private void loadAndPlayMusic(String musicFile) {
        try {
            Media sound = new Media(Paths.get(musicFile).toUri().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading music: " + musicFile + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadButtonSound(String soundFile) {
        try {
            Media sound = new Media(Paths.get(soundFile).toUri().toString());
            buttonSoundPlayer = new MediaPlayer(sound);
        } catch (Exception e) {
            System.err.println("Error loading button sound: " + soundFile + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void playButtonSound() {
        if (buttonSoundPlayer != null) {
            buttonSoundPlayer.stop();
            buttonSoundPlayer.play();
        }
    }

}