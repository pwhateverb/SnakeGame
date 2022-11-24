package com.example.snakegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Random;

public class GameScene extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        int rowNum = 20;
        int colNum = 20;

        GridPane grid = new GridPane();
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

        stage.setTitle("Snake Game Grid");
        stage.setScene(scene);
        stage.show();

        Rectangle food = new Rectangle(39.9, 39.9, Color.RED);
        GridPane.setRowIndex(food, 10);
        GridPane.setColumnIndex(food, 10);
        grid.getChildren().addAll(food);

        scene.setOnKeyPressed(event -> {
            Random rand = new Random();
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);
            GridPane.setRowIndex(food, x);
            GridPane.setColumnIndex(food, y);

            // PRINT foods current position
            System.out.println("X: " + x + " Y: " + y);
        });
    }
}
