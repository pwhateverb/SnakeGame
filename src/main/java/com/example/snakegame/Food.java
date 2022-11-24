package com.example.snakegame;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.util.Random;

public class Food {

    // creates food Rectangle scene object, not sure which size we are going with
    // tho!
    private Rectangle food = new Rectangle(10, 10, Color.RED);

    // first food is specified in main
    public Food(int x, int y) {
        food.setX(x);
        food.setY(y);
    }

    public Rectangle getFood() {
        return food;
    }

    // generates a random food on the "grid", just call this when the snake eats
    public void generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(40) * 10;
        int y = rand.nextInt(40) * 10;
        food.setX(x);
        food.setY(y);
    }

    // get food x value
    public double getFoodLocationX() {
        return food.getX();
    }

    // get food y value
    public double getFoodLocationY() {
        return food.getY();
    }
}
