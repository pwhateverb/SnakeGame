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

public class Multiplayer {
    Timeline timeline;
    private final GridPane grid = new GridPane();
    private final VBox vBox = new VBox();
    private final BorderPane borderPane = new BorderPane();
    private final Snake snake1 = new Snake(15, 5, "down");
    private final Snake snake2 = new Snake (5,15,"up");

    private final Food food1 = new Food();
    private final ArrayList<Rectangle> snakeParts1 = new ArrayList<>();
    private final ArrayList<Rectangle> snakeParts2 = new ArrayList<>();
    private int points = 0;

    private boolean keyPressed1 = false;
    private boolean keyPressed2 = false;

    Multiplayer(Stage stage, Scene mainMenuScene) {
        this.stage = stage;
        stage.setResizable(false);
        this.mainMenuScene = mainMenuScene;
        timeline = null;
    }

    Stage stage;
    Scene scene;
    Scene mainMenuScene;

    Scene play() {

        // Setting up stage
        int rowNum = 17;
        int colNum = 20;

        borderPane.setCenter(grid);
        borderPane.setTop(vBox);

        Color color = Color.BLACK;
        Color snakeColor1 = Color.web("#00FF00");
        Color snakeColor2 = Color.web("FFFF00");

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
        vBox.setPadding(new javafx.geometry.Insets(15, 15, 15, 15));
        vBox.setStyle("-fx-border-color: green; -fx-border-width: 0 0 1 0;");

        // Score label
        TextFlow score = new TextFlow();
        Text scoreText = new Text("SCORE: ");
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(new Font("Verdana", 30));

        Text scoreNumber = new Text(String.valueOf(points));
        scoreNumber.setFill(snakeColor1);
        scoreNumber.setFont(new Font("Verdana", 34));

        score.getChildren().addAll(scoreText, scoreNumber);
        vBox.getChildren().add(score);

        scene = new Scene(borderPane);

        // initialize snake parts
        for (int i = 0; i < snake1.getBody().size(); i++) {
            snakeParts1.add(new Rectangle(39.8, 39.8, snakeColor1));
            GridPane.setColumnIndex(snakeParts1.get(i), snake1.getBody().get(i).getX());
            GridPane.setRowIndex(snakeParts1.get(i), snake1.getBody().get(i).getY());
            grid.getChildren().add(snakeParts1.get(i));
        }


        for (int i = 0; i < snake2.getBody().size(); i++) {
            snakeParts2.add(new Rectangle(39.8, 39.8, snakeColor2));
            GridPane.setColumnIndex(snakeParts2.get(i), snake2.getBody().get(i).getX());
            GridPane.setRowIndex(snakeParts2.get(i), snake2.getBody().get(i).getY());
            grid.getChildren().add(snakeParts2.get(i));
        }
        grid.getChildren().add(food1.getFood());


        // Gather key inputs
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    if (!keyPressed1) {
                        snake1.setDirection("up");
                        keyPressed1 = true;
                    }
                }
                case RIGHT -> {
                    if (!keyPressed1) {
                        snake1.setDirection("right");
                        keyPressed1 = true;
                    }
                }
                case DOWN -> {
                    if (!keyPressed1) {
                        snake1.setDirection("down");
                        keyPressed1 = true;
                    }
                }
                case LEFT -> {
                    if (!keyPressed1) {
                        snake1.setDirection("left");
                        keyPressed1 = true;
                    }
                }
                case W -> {
                    if (!keyPressed2) {
                        snake2.setDirection("up");
                        keyPressed2 = true;
                    }
                }
                case D -> {
                    if (!keyPressed2) {
                        snake2.setDirection("right");
                        keyPressed2 = true;
                    }
                }
                case S -> {
                    if (!keyPressed2) {
                        snake2.setDirection("down");
                        keyPressed2 = true;
                    }
                }
                case A -> {
                    if (!keyPressed2) {
                        snake2.setDirection("left");
                        keyPressed2 = true;
                    }
                }
            }
        });

        // ms between each frame update (lower number == harder game)
        int difficulty = 140;

        // Frame updater
        timeline = new Timeline(
                new KeyFrame(Duration.millis(difficulty), event -> {
                    snake1.move();
                    snake2.move();

                    // if snake is out of bounds aka hitting the walls => game over
                    if ((snake1.isSnakeOutOfMap())||(snake2.isSnakeOutOfMap())) {
                        gameOver();
                        return;
                    }

                    // Move snake every frame
                    for (int i = 0; i < snake1.getBody().size(); i++) {
                        GridPane.setColumnIndex(snakeParts1.get(i), snake1.getBody().get(i).getX());
                        GridPane.setRowIndex(snakeParts1.get(i), snake1.getBody().get(i).getY());
                    }
                    for (int i = 0; i < snake2.getBody().size(); i++) {
                        GridPane.setColumnIndex(snakeParts2.get(i), snake2.getBody().get(i).getX());
                        GridPane.setRowIndex(snakeParts2.get(i), snake2.getBody().get(i).getY());
                    }
                    if (food1.getFoodY() == GridPane.getRowIndex(snakeParts1.get(0))
                            && food1.getFoodX() == GridPane.getColumnIndex(snakeParts1.get(0))) {
                        points++;
                        scoreNumber.setText(String.valueOf(points));
                        snake1.grow();

                        // Grow snake by 1
                        snakeParts1.add(new Rectangle(39.8, 39.8, snakeColor1));
                        GridPane.setColumnIndex(snakeParts1.get(snakeParts1.size() - 1), snake1.getTail().getX());
                        GridPane.setRowIndex(snakeParts1.get(snakeParts1.size() - 1), snake1.getTail().getY());
                        grid.getChildren().add(snakeParts1.get(snakeParts1.size() - 1));
                        // Generate new food
                        food1.generateFood(snake1);
                    }
                    if (food1.getFoodY() == GridPane.getRowIndex(snakeParts2.get(0))
                            && food1.getFoodX() == GridPane.getColumnIndex(snakeParts2.get(0))) {
                        points++;
                        scoreNumber.setText(String.valueOf(points));
                        snake2.grow();

                        // Grow snake by 1
                        snakeParts2.add(new Rectangle(39.8, 39.8, snakeColor2));
                        GridPane.setColumnIndex(snakeParts2.get(snakeParts2.size() - 1), snake2.getTail().getX());
                        GridPane.setRowIndex(snakeParts2.get(snakeParts2.size() - 1), snake2.getTail().getY());
                        grid.getChildren().add(snakeParts2.get(snakeParts2.size() - 1));
                        // Generate new food
                        food1.generateFood(snake2);
                    }
                    for (int i = 1; i < snake2.getBody().size(); i++) {
                        if ((snake2.getHead().getX() == snake2.getBody().get(i).getX()
                                && snake2.getHead().getY() == snake2.getBody().get(i).getY())
                        || (snake2.getHead().getX() == snake1.getBody().get(i).getX())
                                && (snake2.getHead().getY() == snake1.getBody().get(i).getY()))
                        {
                            gameOver();
                            return;
                        }
                    }



                    keyPressed2 = false;


                    for (int i = 1; i < snake1.getBody().size(); i++) {
                        if ((snake1.getHead().getX() == snake1.getBody().get(i).getX()
                                && snake1.getHead().getY() == snake1.getBody().get(i).getY())
                                || (snake1.getHead().getX() == snake2.getBody().get(i).getX())
                                && (snake1.getHead().getY() == snake2.getBody().get(i).getY())){
                            gameOver();
                            return;
                        }
                    }

                    keyPressed1 = false;
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


