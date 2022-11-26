package com.example.snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Controller extends Application {

    GridPane grid = new GridPane();

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

        Snake snakeTest = new Snake();

        Rectangle snakeHead = new Rectangle(39.9, 39.9, Color.GREEN);
        GridPane.setRowIndex(snakeHead, snakeTest.getHead().getY());
        GridPane.setColumnIndex(snakeHead, snakeTest.getHead().getX());
        grid.getChildren().addAll(snakeHead);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    snakeTest.move("up");
                    GridPane.setRowIndex(snakeHead, snakeTest.getHead().getY());
                }
                case RIGHT -> {
                    snakeTest.move("right");
                    GridPane.setColumnIndex(snakeHead, snakeTest.getHead().getX());
                }
                case DOWN -> {
                    snakeTest.move("down");
                    GridPane.setRowIndex(snakeHead, snakeTest.getHead().getY());
                }
                case LEFT -> {
                    snakeTest.move("left");
                    GridPane.setColumnIndex(snakeHead, snakeTest.getHead().getX());
                }
            }
        });
    }
}
