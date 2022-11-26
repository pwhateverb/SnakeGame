package com.example.snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
                case UP -> snakeTest.setDirection("up");
                case RIGHT -> snakeTest.setDirection("right");
                case DOWN -> snakeTest.setDirection("down");
                case LEFT -> snakeTest.setDirection("left");
            }
        });

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            snakeTest.move(snakeTest.getDirection());
            if (snakeTest.getDirection().equals("left") || snakeTest.getDirection().equals("right")) {
                GridPane.setColumnIndex(snakeHead, snakeTest.getHead().getX());
            } else {
                GridPane.setRowIndex(snakeHead, snakeTest.getHead().getY());
            }
        }, 0, 200, TimeUnit.MILLISECONDS);

    }
}
