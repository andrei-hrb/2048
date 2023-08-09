package com.hirbu.game2048;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class HomeController {
    @FXML
    private HBox buttonWrapper;

    @FXML
    private void initialize() {
        buttonWrapper.toFront();
    }

    @FXML
    private void switchToGame() {
        System.out.println("test");
        Application.switchToSceneGame();
    }
}