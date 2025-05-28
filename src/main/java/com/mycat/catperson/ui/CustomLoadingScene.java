package com.mycat.catperson.ui;

import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CustomLoadingScene extends LoadingScene {

    private StackPane root;
    private Rectangle progressBar;
    private Text loadingText;

    public CustomLoadingScene() {
        root = new StackPane();
        root.setPrefWidth(FXGL.getAppWidth());
        root.setPrefHeight(FXGL.getAppHeight());

        Image image = new Image("BACKGROUND1.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        root.setBackground(new Background(backgroundImage));

        // Loading Text
        loadingText = new Text("");
        loadingText.setFill(Color.WHITE);
        loadingText.setFont(Font.font(24)); // Set a font
        StackPane.setAlignment(loadingText, Pos.CENTER);

        // Progress Bar
        progressBar = new Rectangle(0, 20, Color.GREEN);
        StackPane.setAlignment(progressBar, Pos.BOTTOM_CENTER);

        root.getChildren().addAll(loadingText, progressBar);
        getContentRoot().getChildren().add(root);
    }

    public void onProgress(double progress) {
        progressBar.setWidth(FXGL.getAppWidth() * progress);
    }
}
