package com.example.snakegame;

import com.example.snakegame.Highscores.ScoreEngine;
import com.example.snakegame.Highscores.Scorable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Controller {
    Timeline timeline;
    private final GridPane grid = new GridPane();
    private final VBox vBox = new VBox();
    private final BorderPane borderPane = new BorderPane();
    private final Snake snakeTest = new Snake(10, 10, "left");
    private final Food foodTest = new Food();
    private final ArrayList<Rectangle> snakeParts = new ArrayList<>();
    private int points = 0; // Show this number in the UI

    private boolean keyPressed = false;

    private final GameLogic gameLogic = new GameLogic();
    private final GameUI gameUI = new GameUI();


    Controller(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
        stage.setResizable(false);
        this.mainMenuScene = mainMenuScene;
    }

    Stage stage;
    Scene scene;
    Scene mainMenuScene;

    public Scene play() {

        Scene gameScene = gameUI.setupUI();

        // Start game loop
        gameLogic.startGameLoop();

        // handle key input
        gameScene.setOnKeyPressed(event -> gameLogic.handleKeyInput(event));

        return gameScene;

    }

    public void gameOver() {
        timeline.stop();
        Scorable scorable = new ScoreEngine();
        Scene postGameScreen = scorable.displayPostGame(stage, mainMenuScene, points);
        stage.setScene(postGameScreen);
        stage.show();
    }
}
