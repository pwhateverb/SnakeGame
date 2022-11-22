package com.example.snakegame;

import java.util.ArrayList;

public class Snake {

    // arraylist to store snake body parts locations
    private ArrayList<Point> bodyParts;
    private Point headOfSnake;
    private String direction;

    public Snake(int x, int y, String direction) {
        bodyParts = new ArrayList<>();
        // each snake starts with 4 parts spawned at the same location
        for (int i = 0; i < 4; i++) {
            bodyParts.add(new Point(x, y));
        }

        this.direction = direction;
        headOfSnake = bodyParts.get(0);
    }

    /* the move() method moves the snake by one block by removing the tail and
       adding a new head on a location based on the direction and the previous head's location */
    public void move() {
        int x = headOfSnake.getX();
        int y = headOfSnake.getY();

        switch (this.direction) {
            case "right":
                x++;
                break;
            case "left":
                x--;
                break;
            case "up":
                y--;
                break;
            case "down":
                y++;
                break;
        }

        bodyParts.add(0, new Point(x, y));
        bodyParts.remove(bodyParts.size() - 1);
        headOfSnake = bodyParts.get(0);

    }

    public boolean isSnakeOutOfMap() {
        // if snake is out of bounds aka hitting the walls => game over
        return headOfSnake.getX() < 0 || headOfSnake.getX() > 19 || headOfSnake.getY() < 0 || headOfSnake.getY() > 16;
    }

    // the grow() method grows the snake by one by adding a new body part to the same location as the tail
    public void grow() {
        bodyParts.add(new Point(getTail().getX(), getTail().getY()));
    }

    public Point getHead() {
        return headOfSnake;
    }

    public ArrayList<Point> getBody() {
        return bodyParts;
    }

    public String getDirection() {
        return direction;
    }

    public Point getTail() {
        return bodyParts.get(bodyParts.size() - 1);
    }

    /* setter for direction that checks if the new direction isn't the opposite
       of the previous one to make sure the snake doesn't move backwards */
    public void setDirection(String direction) {
        if ((this.direction.equals("right") && direction.equals("left"))
                || (this.direction.equals("left") && direction.equals("right"))
                || (this.direction.equals("up") && direction.equals("down"))
                || (this.direction.equals("down") && direction.equals("up"))) {
        } else {
            this.direction = direction;
        }
    }
}
