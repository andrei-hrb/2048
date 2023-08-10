package com.hirbu.game2048;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Stage stage;

    private static Scene sceneHome;

    private static Scene sceneGame;

    private static Scene sceneLeaderboard;

    @Override
    public void start(Stage stage) throws IOException {
        Application.stage = stage;

        Application.sceneHome = this.createNewScene("home-view.fxml");
        Application.sceneGame = this.createNewScene("game-view.fxml");

        Application.stage.setScene(sceneHome);
        Application.stage.setTitle("2048");
        Application.stage.show();
    }

    private Scene createNewScene(String view) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/" + view));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);

        scene.getStylesheets().add(getClass().getResource("/com/hirbu/game2048/styles/style.css").toExternalForm());

        return scene;
    }

    public static void switchToSceneHome() {
        Application.stage.setScene(Application.sceneHome);
    }

    public static void switchToSceneGame() {
        Application.stage.setScene(Application.sceneGame);
    }

    public static void main(String[] args) {
        launch();
    }
}