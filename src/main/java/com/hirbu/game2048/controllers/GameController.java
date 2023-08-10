package com.hirbu.game2048.controllers;

import com.hirbu.game2048.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController {
    @FXML
    private Button switchToHomeButton;

    @FXML
    private Button restartButton;

    @FXML
    private void initialize() {
        ImageView homeIcon = initializeImage("home.png");
        ImageView restartIcon = initializeImage("restart.png");

        this.switchToHomeButton.setGraphic(homeIcon);
        this.restartButton.setGraphic(restartIcon);

        this.switchToHomeButton.toFront();
        this.restartButton.toFront();
    }

    private ImageView initializeImage(String path) {
        Image iconImage = new Image(getClass().getResourceAsStream("/com/hirbu/game2048/images/" + path));
        ImageView iconImageView = new ImageView(iconImage);

        iconImageView.setFitWidth(45);
        iconImageView.setFitHeight(45);

        return iconImageView;
    }

    @FXML
    private void switchToHome() {
        Application.switchToSceneHome();
    }

    @FXML
    private void restart() {
        System.out.println("test");
    }
}
