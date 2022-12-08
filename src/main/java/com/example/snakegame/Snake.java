package com.example.snakegame;

import java.util.ArrayList;

public class Snake {

    private ArrayList<Point> bodyParts;
    private Point headOfSnake;
    private String direction = "left";



    public Snake() {
        bodyParts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            bodyParts.add(new Point(10, 10));
        }

        headOfSnake = bodyParts.get(0);
    }

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

    public boolean isSnakeOutOfMap(){
        // if snake is out of bounds aka hitting the walls => game over
        return headOfSnake.getX() < 0 || headOfSnake.getX() > 19 || headOfSnake.getY() < 0 || headOfSnake.getY() > 19;
    }

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

    public void setDirection(String direction) {
        // making sure the snake doesn't move backwards
        if ((this.direction.equals("right") && direction.equals("left"))
                || (this.direction.equals("left") && direction.equals("right"))
                || (this.direction.equals("up") && direction.equals("down"))
                || (this.direction.equals("down") && direction.equals("up"))) {
            ;
        } else {
            this.direction = direction;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        Snake snake = (Snake) object;

        if (!bodyParts.equals(snake.bodyParts))
            return false;
        return headOfSnake.equals(snake.headOfSnake);
    }

}
