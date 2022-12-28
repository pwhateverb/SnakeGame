package com.example.snakegame.Highscores;


/**
 * Abstraction of a highscore object: it has the player's name and the respective numeric score. Can be written to a
 * string or compared to other highscores.
 * <p/>
 * It does not have setters as <b>each object should only be created, not edited</b>.
 * <p/>
 * Used by the {@link com.example.snakegame.Highscores.ScoreEngine} class.
 * <p/>
 * <i>Can be refactored as a Record.</i>
 * @see <a href="https://www.baeldung.com/java-record-keyword">Records</a>
**/

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
