package com.hirbu.game2048.controllers;

import com.hirbu.game2048.Application;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class HomeController {
    @FXML
    private HBox buttonWrapper;

    @FXML
    private void initialize() {
        this.buttonWrapper.toFront();
    }

    @FXML
    private void switchToGame() {
        Application.switchToSceneGame();
    }
}