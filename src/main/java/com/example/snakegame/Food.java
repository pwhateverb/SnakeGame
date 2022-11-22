package com.example.snakegame;

public class Food {
    private Point location;

    public Food(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    // 2 foods are the same if they are on the same position
    public boolean equals(Object other) {
        if (other instanceof Food) {
            Food otherFood = (Food) other;
            return location.equals(otherFood.location);
        } else {
            return false;
        }
    }
}
