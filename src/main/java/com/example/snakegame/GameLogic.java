package com.example.snakegame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class GameLogic {
    final Snake snakeTest = new Snake(10, 10, "left");
    private final Food food = new Food();
    private int points = 0; // Show this number in the UI
    private boolean keyPressed = false;

    private boolean gameOver = false;

    public void handleKeyInput(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> {
                if (!keyPressed) {
                    snakeTest.setDirection("up");
                    keyPressed = true;
                }
            }
            case RIGHT -> {
                if (!keyPressed) {
                    snakeTest.setDirection("right");
                    keyPressed = true;
                }
            }
            case DOWN -> {
                if (!keyPressed) {
                    snakeTest.setDirection("down");
                    keyPressed = true;
                }
            }
            case LEFT -> {
                if (!keyPressed) {
                    snakeTest.setDirection("left");
                    keyPressed = true;
                }
            }
        }
    }

    void startGameLoop() {
        Timeline gameloop = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (!gameOver) {
                try {

                    // updates snake position
                    updateSnakePosition();
                    // checks if snake ate food
                    checkFoodCollision();
                    // checks if snake hit itself or wall
                    checkCollision();

                    // update UI every frame
                    GameUI.updateUI(snakeTest, food);

                } catch (Exception e) {
                    System.out.println("Game loop error");
                    return;

                }
            }

            // if game is over, stop game loop
            return;

        }));
        gameloop.setCycleCount(Animation.INDEFINITE);
        gameloop.play();

        return;

    }

    private void checkFoodCollision() {
        // check if head == food
        if (snakeTest.getHead().getX() == food.getFoodX() &&
                snakeTest.getHead().getY() == food.getFoodY()) {
            snakeTest.grow();
            food.generateFood(snakeTest);
            points += 1;

            // update ui to show one more snake part
            GameUI.addSnakePart(snakeTest);

        }
    }

    void updateSnakePosition() {
        snakeTest.move();
        keyPressed = false;
    }

    private void checkCollision() {
        // check if head == body
        for (int i = 1; i < snakeTest.getBody().size(); i++) {
            if (snakeTest.getHead().getX() == snakeTest.getBody().get(i).getX()
                    && snakeTest.getHead().getY() == snakeTest.getBody().get(i).getY()) {
                gameOver = true;
                System.out.println("Game over");

            }
        }
        // check if head == wall
        if (snakeTest.getHead().getX() < 0 || snakeTest.getHead().getX() > 19 || snakeTest.getHead().getY() < 0
                || snakeTest.getHead().getY() > 16) {
            gameOver = true;
            System.out.println("Game over");
        }
    }

    public int getPoints() {
        return points;
    }

}
