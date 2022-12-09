package com.example.snakegame;

import com.example.snakegame.Highscores.Highscores;
import com.example.snakegame.Highscores.IHighscores;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Controller {
    Timeline timeline;
    private final GridPane grid = new GridPane();
    private final Snake snakeTest = new Snake();
    private final Food foodTest = new Food();
    private final ArrayList<Rectangle> snakeParts = new ArrayList<>();
    private int points = 0; // Show this number in the UI

    private boolean keyPressed = false;


    Controller(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
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

        // Add this below for a "grid structure" in the game and see comment below in for loop as well.
        //grid.setVgap(0.1);
        //grid.setHgap(0.1);

        Color color = Color.BLACK;
        Color snakeColor = Color.web("#00FF00");

        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                Rectangle rec = new Rectangle();
                // Set to 39.9 for a "grid structure" in the game
                rec.setWidth(40);
                rec.setHeight(40);
                rec.setFill(color);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                grid.getChildren().addAll(rec);
            }
        }

        Label score = new Label("Score: " + points);
        score.setWrapText(true);
        GridPane.setColumnSpan(score, 3);
        GridPane.setColumnIndex(score, 1);
        GridPane.setRowIndex(score, 0);
        score.setFont(new Font("Verdana", 28));
        score.setStyle("-fx-font: 100px Verdana; -fx-font-weight: bold; -fx-text-fill: #32CD32; -fx-font-size: 25;");
        grid.getChildren().add(score);

        scene = new Scene(grid, 800, 800);

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
                    if(!keyPressed){ snakeTest.setDirection("up");
                        keyPressed = true;}
                }
                case RIGHT -> {
                    if(!keyPressed){ snakeTest.setDirection("right");
                        keyPressed = true;}
                }
                case DOWN -> {
                    if(!keyPressed){ snakeTest.setDirection("down");
                        keyPressed = true;}
                }
                case LEFT -> {
                    if(!keyPressed){ snakeTest.setDirection("left");
                        keyPressed = true;}
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
                        score.setText("Score: " + points);
                        System.out.println("Current points: " + points);
                        snakeTest.grow();

                        // Grow snake by 1
                        snakeParts.add(new Rectangle(39.8, 39.8, snakeColor));
                        GridPane.setColumnIndex(snakeParts.get(snakeParts.size() - 1), snakeTest.getTail().getX());
                        GridPane.setRowIndex(snakeParts.get(snakeParts.size() - 1), snakeTest.getTail().getY());
                        System.out.println(snakeParts.size() == snakeTest.getBody().size());
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
        IHighscores iHighscores = new Highscores();
        Controller controller = new Controller(stage,mainMenuScene);
        Scene postGameScreen = iHighscores.displayPostGame(stage, mainMenuScene, controller.play(), 1000);
        stage.setScene(postGameScreen);
        stage.show();
    }
}
