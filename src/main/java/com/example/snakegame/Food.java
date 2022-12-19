package com.example.snakegame;

import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.util.Random;

public class Food {
    private Rectangle food;
    Random rand = new Random();

    public Food() {
        // Food is placed in a random position on the grid when instantiated.
        food = new Rectangle(40, 40, Color.ORANGERED);
        int x = rand.nextInt(19);
        int y = rand.nextInt(16);
        GridPane.setColumnIndex(food, x);
        GridPane.setRowIndex(food, y);
    }

    public Rectangle getFood() {
        return food;
    }

    public void generateFood(Snake snake) {
        // Generates a new food in a random position on the grid.
        boolean foodOnSnake = true;
        while (foodOnSnake) {
            int x = rand.nextInt(19);
            int y = rand.nextInt(16);
            // Checks if the food is placed on/under the snake. If so, repeat the process.
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
        // returns the x position of the food on the grid
        return GridPane.getColumnIndex(food);
    }

    public int getFoodY() {
        // returns the y position of the food on the grid
        return GridPane.getRowIndex(food);
    }

}
