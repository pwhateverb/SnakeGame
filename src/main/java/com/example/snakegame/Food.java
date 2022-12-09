package com.example.snakegame;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.Random;

public class Food {

    private Rectangle food;
    Random rand = new Random();

    public Food() {
        food = new Rectangle(40, 40, Color.ORANGERED);
        // set food to random position
        int x = rand.nextInt(19);
        int y = rand.nextInt(19);
        GridPane.setColumnIndex(food, x);
        GridPane.setRowIndex(food, y);
    }

    public Rectangle getFood() {
        return food;
    }

    public void generateFood(Snake snake) {
        boolean foodOnSnake = true;
        while (foodOnSnake) {
            int x = rand.nextInt(19);
            int y = rand.nextInt(19);
            for (int i = 0; i < snake.getBody().size(); i++) {
                if (snake.getBody().get(i).getX() == x && snake.getBody().get(i).getY() == y) {
                    foodOnSnake = true;
                    break;
                } else {
                    foodOnSnake = false;
                }
            }
            if (!foodOnSnake) {
                GridPane.setColumnIndex(food, x);
                GridPane.setRowIndex(food, y);
            }
        }
    }

    public int getFoodX() {
        return GridPane.getColumnIndex(food);
    }

    public int getFoodY() {
        return GridPane.getRowIndex(food);
    }

}
