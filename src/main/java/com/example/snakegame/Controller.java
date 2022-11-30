package com.example.snakegame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Controller extends Application {

    private final GridPane grid = new GridPane();
    private final Snake snakeTest = new Snake();
    private final Food foodTest = new Food();
    private final ArrayList<Rectangle> snakeParts= new ArrayList<>();
    private int points = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        int rowNum = 20;
        int colNum = 20;

        grid.setVgap(0.1);
        grid.setHgap(0.1);

        Color color = Color.BLACK;

        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(39.9);
                rec.setHeight(39.9);
                rec.setFill(color);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                grid.getChildren().addAll(rec);
            }
        }

        Scene scene = new Scene(grid, 800, 800);

        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();

        for (int i = 0; i < snakeTest.getBody().size(); i++) {
            snakeParts.add(new Rectangle(40, 40, Color.GREEN));
            GridPane.setColumnIndex(snakeParts.get(i), snakeTest.getBody().get(i).getX());
            GridPane.setRowIndex(snakeParts.get(i), snakeTest.getBody().get(i).getY());
            grid.getChildren().add(snakeParts.get(i));
        }
        foodTest.generateFood();
        grid.getChildren().add(foodTest.getFood());

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> snakeTest.setDirection("up");
                case RIGHT -> snakeTest.setDirection("right");
                case DOWN -> snakeTest.setDirection("down");
                case LEFT -> snakeTest.setDirection("left");
            }
        });

        int difficulty = 150;

        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(difficulty), event -> {
                    snakeTest.move();
                    for (int i = 0; i < snakeTest.getBody().size(); i++) {
                        GridPane.setColumnIndex(snakeParts.get(i), snakeTest.getBody().get(i).getX());
                        GridPane.setRowIndex(snakeParts.get(i), snakeTest.getBody().get(i).getY());
                    }
                    if (foodTest.getFoodX() == GridPane.getRowIndex(snakeParts.get(0))
                            && foodTest.getFoodY() == GridPane.getColumnIndex(snakeParts.get(0))) {
                        points++;
                        System.out.println("Current points: " + points);
                        foodTest.generateFood();
                        snakeTest.grow();
                        snakeParts.add(new Rectangle(40, 40, Color.GREEN));
                        GridPane.setColumnIndex(snakeParts.get(snakeParts.size() - 1), snakeTest.getTail().getX());
                        GridPane.setRowIndex(snakeParts.get(snakeParts.size() - 1), snakeTest.getTail().getY());
                        System.out.println(snakeParts.size() == snakeTest.getBody().size());
                        grid.getChildren().add(snakeParts.get(snakeParts.size() - 1));
                    }
                }
            )
        );
        timeline.setCycleCount(Animation.INDEFINITE );
        timeline.play();
    }
}
