package com.example.snakegame;

import java.util.ArrayList;

public class Snake {

    private ArrayList<Point> bodyParts;
    private Point headOfSnake;
    private String direction = "left";

    public Snake(){
        bodyParts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            bodyParts.add(new Point(10, 10));
        }

        headOfSnake = bodyParts.get(0);
    }

    public void move(String direction){
        int x = headOfSnake.getX();
        int y = headOfSnake.getY();

        switch (direction) {
            case "right" : x++;
                break;
            case "left" : x--;
                break;
            case "up" : y--;
                break;
            case "down" : y++;
                break;
        }

        bodyParts.add(0, new Point(x, y));
        bodyParts.remove(bodyParts.size() - 1);
        headOfSnake = bodyParts.get(0);
    }

    public void grow(){
        bodyParts.add(new Point(getTail().getX(), getTail().getY()));
    }

    public Point getHead(){
        return headOfSnake;
    }

    public ArrayList<Point> getBody(){
        return bodyParts;
    }

    public String getDirection() {
        return direction;
    }

    public Point getTail() {
        return bodyParts.get(bodyParts.size() - 1);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Snake snake = (Snake) object;

        if (!bodyParts.equals(snake.bodyParts)) return false;
        return headOfSnake.equals(snake.headOfSnake);
    }

}
