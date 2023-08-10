package com.hirbu.game2048.controllers;

import com.hirbu.game2048.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import java.util.*;

public class GameController {
    @FXML
    private StackPane rootPane;

    private int[][] gameBoard;

    @FXML
    private GridPane gameBoardPane;

    @FXML
    private Button switchToHomeButton;

    @FXML
    private Button restartButton;

    @FXML
    private void initialize() {
        this.initializeGameBoard();
        this.initializeButtons();
        this.initializeKeyPressEvents();
    }

    private void initializeKeyPressEvents() {
        this.rootPane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W -> {
                    this.move(0);
                    System.out.println("UP");
                }
                case D -> {
                    this.move(1);
                    System.out.println("RIGHT");
                }
                case S -> {
                    this.move(2);
                    System.out.println("DOWN");
                }
                case A -> {
                    this.move(3);
                    System.out.println("LEFT");
                }
            }
        });
    }

    private void updateGameBoardUI() {
        for (Node child : gameBoardPane.getChildren()) {
            if (child instanceof StackPane cellPane) {
                int rowIndex = GridPane.getRowIndex(cellPane);
                int columnIndex = GridPane.getColumnIndex(cellPane);
                Label cellLabel = (Label) cellPane.getChildren().get(0); // Assuming the label is the first child
                cellLabel.setText(Integer.toString(gameBoard[rowIndex][columnIndex]));
            }
        }
    }

    private void initializeGameBoard() {
        this.gameBoard = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Pair<Integer, Integer> spot = new Pair<>(i, j);

                this.setValueOfSpot(spot, 0);
            }
        }

        Pair<Integer, Integer> spot = this.generateRandomEmptySpot();
        int value = this.generateRandomTwoOrFour();
        this.setValueOfSpot(spot, value);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                StackPane cellPane = new StackPane();
                Label cellLabel = new Label(Integer.toString(gameBoard[i][j]));
                cellLabel.setPadding(new Insets(20));
                cellPane.getChildren().add(cellLabel);
                gameBoardPane.add(cellPane, j, i);
            }
        }
    }

    private void move(int direction) {
        boolean moved = false;

        switch (direction) {
            case 0 -> { // up
                for (int i = 1; i < 4; i++) {
                    Vector<Pair<Integer, Integer>> rowToBeModified = this.getAllFilledSpotsOfRow(i);

                    for (Pair<Integer, Integer> spot : rowToBeModified) {
                        Pair<Integer, Integer> spotUp = this.getSpotUp(spot);

                        while (this.checkIfSpotIsValid(spotUp) && this.checkIfEmptySpot(spotUp)) {
                            this.setValueOfSpot(spotUp, this.getValueOfSpot(spot));
                            this.setValueOfSpot(spot, 0);

                            spot = spotUp;
                            spotUp = this.getSpotUp(spot);

                            moved = true;
                        }

                        if (this.checkIfSpotIsValid(spotUp) && this.checkIfSpotsAreEqual(spot, spotUp)) {
                            this.setValueOfSpot(spotUp, this.getValueOfSpot(spotUp) * 2);
                            this.setValueOfSpot(spot, 0);

                            moved = true;
                        }
                    }
                }
            }
            case 1 -> { // right
                for (int i = 2; i >= 0; i--) {
                    Vector<Pair<Integer, Integer>> columnToBeModified = this.getAllFilledSpotsOfColumn(i);

                    for (Pair<Integer, Integer> spot : columnToBeModified) {
                        Pair<Integer, Integer> spotRight = this.getSpotRight(spot);

                        while (this.checkIfSpotIsValid(spotRight) && this.checkIfEmptySpot(spotRight)) {
                            this.setValueOfSpot(spotRight, this.getValueOfSpot(spot));
                            this.setValueOfSpot(spot, 0);

                            spot = spotRight;
                            spotRight = this.getSpotRight(spot);

                            moved = true;
                        }

                        if (this.checkIfSpotIsValid(spotRight) && this.checkIfSpotsAreEqual(spot, spotRight)) {
                            this.setValueOfSpot(spotRight, this.getValueOfSpot(spotRight) * 2);
                            this.setValueOfSpot(spot, 0);

                            moved = true;
                        }
                    }
                }
            }
            case 2 -> { // down
                for (int i = 2; i >= 0; i--) {
                    Vector<Pair<Integer, Integer>> rowToBeModified = this.getAllSpotsOfRow(i);

                    for (Pair<Integer, Integer> spot : rowToBeModified) {
                        Pair<Integer, Integer> spotDown = this.getSpotDown(spot);

                        while (this.checkIfSpotIsValid(spotDown) && this.checkIfEmptySpot(spotDown)) {
                            this.setValueOfSpot(spotDown, this.getValueOfSpot(spot));
                            this.setValueOfSpot(spot, 0);

                            spot = spotDown;
                            spotDown = this.getSpotDown(spot);

                            moved = true;
                        }

                        if (this.checkIfSpotIsValid(spotDown) && this.checkIfSpotsAreEqual(spot, spotDown)) {
                            this.setValueOfSpot(spotDown, this.getValueOfSpot(spotDown) * 2);
                            this.setValueOfSpot(spot, 0);

                            moved = true;
                        }
                    }
                }
            }
            case 3 -> { // left
                for (int i = 1; i < 4; i++) {
                    Vector<Pair<Integer, Integer>> columnToBeModified = this.getAllFilledSpotsOfColumn(i);

                    for (Pair<Integer, Integer> spot : columnToBeModified) {
                        Pair<Integer, Integer> spotLeft = this.getSpotLeft(spot);

                        while (this.checkIfSpotIsValid(spotLeft) && this.checkIfEmptySpot(spotLeft)) {
                            this.setValueOfSpot(spotLeft, this.getValueOfSpot(spot));
                            this.setValueOfSpot(spot, 0);

                            spot = spotLeft;
                            spotLeft = this.getSpotLeft(spot);

                            moved = true;
                        }

                        if (this.checkIfSpotIsValid(spotLeft) && this.checkIfSpotsAreEqual(spot, spotLeft)) {
                            this.setValueOfSpot(spotLeft, this.getValueOfSpot(spotLeft) * 2);
                            this.setValueOfSpot(spot, 0);

                            moved = true;
                        }
                    }
                }
            }
        }

        if (this.checkIfThereAreAnyEmptySpots() && moved) {
            Pair<Integer, Integer> newSpot = this.generateRandomEmptySpot();
            int newValue = this.generateRandomTwoOrFour();
            this.setValueOfSpot(newSpot, newValue);
        }

        updateGameBoardUI();
    }

    private int getValueOfSpot(Pair<Integer, Integer> spot) {
        return this.gameBoard[spot.getKey()][spot.getValue()];
    }

    private void setValueOfSpot(Pair<Integer, Integer> spot, int value) {
        this.gameBoard[spot.getKey()][spot.getValue()] = value;
    }

    private Pair<Integer, Integer> getSpotUp(Pair<Integer, Integer> spot) {
        return new Pair<>(spot.getKey() - 1, spot.getValue());
    }

    private Pair<Integer, Integer> getSpotRight(Pair<Integer, Integer> spot) {
        return new Pair<>(spot.getKey(), spot.getValue() + 1);
    }

    private Pair<Integer, Integer> getSpotDown(Pair<Integer, Integer> spot) {
        return new Pair<>(spot.getKey() + 1, spot.getValue());
    }

    private Pair<Integer, Integer> getSpotLeft(Pair<Integer, Integer> spot) {
        return new Pair<>(spot.getKey(), spot.getValue() - 1);
    }

    private Vector<Pair<Integer, Integer>> getAllEmptySpots() {
        Vector<Pair<Integer, Integer>> spots = new Vector<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Pair<Integer, Integer> spot = new Pair<>(i, j);

                if (this.checkIfEmptySpot(spot)) {
                    spots.add(spot);
                }
            }
        }

        return spots;
    }

    private Vector<Pair<Integer, Integer>> getAllFilledSpots() {
        Vector<Pair<Integer, Integer>> spots = new Vector<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Pair<Integer, Integer> spot = new Pair<>(i, j);

                if (this.checkIfFilledSpot(spot)) {
                    spots.add(spot);
                }
            }
        }

        return spots;
    }

    private boolean checkIfSpotsAreEqual(Pair<Integer, Integer> spot1, Pair<Integer, Integer> spot2) {
        return this.getValueOfSpot(spot1) == this.getValueOfSpot(spot2);
    }

    private Vector<Pair<Integer, Integer>> getAllSpotsOfColumn(int column) {
        Vector<Pair<Integer, Integer>> spots = new Vector<>();

        for (int i = 0; i < 4; i++) {
            Pair<Integer, Integer> spot = new Pair<>(i, column);
            spots.add(spot);
        }

        return spots;
    }

    private Vector<Pair<Integer, Integer>> getAllEmptySpotsOfColumn(int column) {
        Vector<Pair<Integer, Integer>> spots = new Vector<>();

        for (int i = 0; i < 4; i++) {
            Pair<Integer, Integer> spot = new Pair<>(i, column);

            if (this.checkIfEmptySpot(spot)) {
                spots.add(spot);
            }
        }

        return spots;
    }

    private Vector<Pair<Integer, Integer>> getAllFilledSpotsOfColumn(int column) {
        Vector<Pair<Integer, Integer>> spots = new Vector<>();

        for (int i = 0; i < 4; i++) {
            Pair<Integer, Integer> spot = new Pair<>(i, column);

            if (this.checkIfFilledSpot(spot)) {
                spots.add(spot);
            }
        }

        return spots;
    }

    private Vector<Pair<Integer, Integer>> getAllSpotsOfRow(int row) {
        Vector<Pair<Integer, Integer>> spots = new Vector<>();

        for (int j = 0; j < 4; j++) {
            Pair<Integer, Integer> spot = new Pair<>(row, j);
            spots.add(spot);
        }

        return spots;
    }

    private Vector<Pair<Integer, Integer>> getAllEmptySpotsOfRow(int row) {
        Vector<Pair<Integer, Integer>> spots = new Vector<>();

        for (int j = 0; j < 4; j++) {
            Pair<Integer, Integer> spot = new Pair<>(row, j);

            if (this.checkIfEmptySpot(spot)) {
                spots.add(spot);
            }
        }

        return spots;
    }

    private Vector<Pair<Integer, Integer>> getAllFilledSpotsOfRow(int row) {
        Vector<Pair<Integer, Integer>> spots = new Vector<>();

        for (int j = 0; j < 4; j++) {
            Pair<Integer, Integer> spot = new Pair<>(row, j);

            if (this.checkIfFilledSpot(spot)) {
                spots.add(spot);
            }
        }

        return spots;
    }

    private boolean checkIfEmptySpot(Pair<Integer, Integer> spot) {
        return this.getValueOfSpot(spot) == 0;
    }

    private boolean checkIfSpotIsValid(Pair<Integer, Integer> spot) {
        return spot.getKey() >= 0 && spot.getKey() < 4 && spot.getValue() >= 0 && spot.getValue() < 4;
    }

    private boolean checkIfFilledSpot(Pair<Integer, Integer> spot) {
        return this.getValueOfSpot(spot) != 0;
    }

    private boolean checkIfThereAreAnyEmptySpots() {
        return !this.getAllEmptySpots().isEmpty();
    }

    private Pair<Integer, Integer> generateRandomEmptySpot() {
        Vector<Pair<Integer, Integer>> spots = this.getAllEmptySpots();

        return spots.elementAt(this.generateRandomNumber(spots.size()));
    }

    private int generateRandomTwoOrFour() {
        return this.generateRandomNumber(2) == 0 ? 2 : 4;
    }

    private int generateRandomNumber(int limit) {
        Random random = new Random();

        return random.nextInt(limit);
    }

    private void initializeButtons() {
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

        iconImageView.setFitWidth(40);
        iconImageView.setFitHeight(40);

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
