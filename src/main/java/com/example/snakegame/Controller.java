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

    Controller(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
        stage.setResizable(false);
        this.mainMenuScene = mainMenuScene;
        timeline = null;
    }

    Stage stage;
    Scene scene;
    Scene mainMenuScene;

    public Scene play() {

        // Setting up stage
        int rowNum = 20;
        int colNum = 20;

        borderPane.setCenter(grid);
        borderPane.setTop(vBox);

        Color color = Color.BLACK;
        Color snakeColor = Color.web("#00FF00");

        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(40);
                rec.setHeight(40);
                rec.setFill(color);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                grid.getChildren().addAll(rec);
            }
        }

        // Styling
        borderPane.setStyle("-fx-background-color: black");
        vBox.setPadding(new javafx.geometry.Insets(20, 20, 20, 20));
        grid.setStyle("-fx-border-color: limegreen; -fx-border-width: 1;");

        // Score label
        TextFlow score = new TextFlow();
        Text scoreText = new Text("SCORE: ");
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(new Font("Verdana", 30));

        Text scoreNumber = new Text(String.valueOf(points));
        scoreNumber.setFill(snakeColor);
        scoreNumber.setFont(new Font("Verdana", 34));

        score.getChildren().addAll(scoreText, scoreNumber);
        vBox.getChildren().add(score);

        scene = new Scene(borderPane);

        // initialize snake parts
        for (int i = 0; i < snakeTest.getBody().size(); i++) {
            snakeParts.add(new Rectangle(39.8, 39.8, snakeColor));
            GridPane.setColumnIndex(snakeParts.get(i), snakeTest.getBody().get(i).getX());
            GridPane.setRowIndex(snakeParts.get(i), snakeTest.getBody().get(i).getY());
            grid.getChildren().add(snakeParts.get(i));
        }
        grid.getChildren().add(foodTest.getFood());

        // Gather key inputs
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    if (!keyPressed) {
                        snakeTest.setDirection("up");
                        keyPressed = true;
                    }
                }
                case RIGHT -> {
                    if (!keyPressed) {
                        snakeTest.setDirection("right");
                        keyPressed = true;
                    }
                }
                case DOWN -> {
                    if (!keyPressed) {
                        snakeTest.setDirection("down");
                        keyPressed = true;
                    }
                }
                case LEFT -> {
                    if (!keyPressed) {
                        snakeTest.setDirection("left");
                        keyPressed = true;
                    }
                }
            }
        });

        // ms between each frame update (lower number == harder game)
        int difficulty = 120;

        // Frame updater
        timeline = new Timeline(
                new KeyFrame(Duration.millis(difficulty), event -> {
                    snakeTest.move();

                    // if snake is out of bounds aka hitting the walls => game over
                    if (snakeTest.isSnakeOutOfMap()) {
                        gameOver();
                        return;
                    }

                    // Move snake every frame
                    for (int i = 0; i < snakeTest.getBody().size(); i++) {
                        GridPane.setColumnIndex(snakeParts.get(i), snakeTest.getBody().get(i).getX());
                        GridPane.setRowIndex(snakeParts.get(i), snakeTest.getBody().get(i).getY());
                    }
                    // Check if head == food
                    if (foodTest.getFoodY() == GridPane.getRowIndex(snakeParts.get(0))
                            && foodTest.getFoodX() == GridPane.getColumnIndex(snakeParts.get(0))) {
                        points++;
                        scoreNumber.setText(String.valueOf(points));
                        snakeTest.grow();

                        // Grow snake by 1
                        snakeParts.add(new Rectangle(39.8, 39.8, snakeColor));
                        GridPane.setColumnIndex(snakeParts.get(snakeParts.size() - 1), snakeTest.getTail().getX());
                        GridPane.setRowIndex(snakeParts.get(snakeParts.size() - 1), snakeTest.getTail().getY());
                        grid.getChildren().add(snakeParts.get(snakeParts.size() - 1));
                        // Generate new food
                        foodTest.generateFood(snakeTest);
                    }

                    // If snake hits its own body, game over
                    for (int i = 1; i < snakeTest.getBody().size(); i++) {
                        if (snakeTest.getHead().getX() == snakeTest.getBody().get(i).getX()
                                && snakeTest.getHead().getY() == snakeTest.getBody().get(i).getY()) {
                            gameOver();
                            return;
                        }
                    }

                    keyPressed = false;
                }));
        // Play frames
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        return scene;
    }

    public void gameOver() {
        timeline.stop();
        Scorable scorable = new ScoreEngine();
        Scene postGameScreen = scorable.displayPostGame(stage, mainMenuScene, points);
        stage.setScene(postGameScreen);
        stage.show();
    }
}
