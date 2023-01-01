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

    public void generateFoodForMultiplayer(Snake snake1, Snake snake2) {
        // Generates a new food in a random position on the grid.
        boolean foodOnSnake1 = true;
        boolean foodOnSnake2 = true;

        while (foodOnSnake1||foodOnSnake2) {
            int x = rand.nextInt(19);
            int y = rand.nextInt(16);
            // Checks if the food is placed on/under the snake. If so, repeat the process.
            for (int i = 0; i < snake1.getBody().size(); i++) {
                if (snake1.getBody().get(i).getX() == x && snake1.getBody().get(i).getY() == y) {
                    foodOnSnake1 = true;
                    break;
                } else {
                    foodOnSnake1 = false;
                }
            }
            for (int i = 0; i < snake2.getBody().size(); i++){
                if (snake2.getBody().get(i).getX() == x && snake2.getBody().get(i).getY() == y){
                    foodOnSnake2 = true;
                    break;
                } else{
                    foodOnSnake2 = false;
                }
            }

            if (!foodOnSnake1 && !foodOnSnake2) {
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
