package com.example.snakegame;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.Random;

public class Food {

    private Rectangle food;

    public Food() {
        food = new Rectangle(39.9, 39.9, Color.RED);
    }

    public Rectangle getFood() {
        return food;
    }

    public void generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(20);
        int y = rand.nextInt(20);
        GridPane.setRowIndex(food, x);
        GridPane.setColumnIndex(food, y);
    }

    public int getFoodX() {
        return GridPane.getRowIndex(food);
    }

    public int getFoodY() {
        return GridPane.getColumnIndex(food);
    }

}
