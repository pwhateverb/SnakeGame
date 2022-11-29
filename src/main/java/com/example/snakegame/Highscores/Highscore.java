package com.example.snakegame.Highscores;

public class Highscore implements Comparable<Highscore>{
    final public String name;
    final public int score;

    public Highscore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return name + " got " + score + " points.";
    }

    @Override
    public int compareTo(Highscore otherHighscore) {
        return otherHighscore.score -this.score;
    }
}
