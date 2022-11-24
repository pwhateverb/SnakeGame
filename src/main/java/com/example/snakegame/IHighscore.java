package com.example.snakegame;

import javafx.scene.Scene;


/** This interface connects the Highscores to the rest of the game. */
public interface IHighscore {

    /** returns true if user achieved a highScore. If so, the gameOver scene should display a prompt like "Input your name to be added to the highscores" */
    boolean highscoreCheck(int score);
    //todo: call this method in the gameOver scene (when checking whether to display the insertNameForAchievement prompt)

    /** saves user's name and score in a file called "highscores.txt". */
    void saveHighscore(String name, int score);
    //todo: call this method when the user inputs a name (after being prompted to do so in the gameOver scene)

    /** returns a Scene displaying the 10 highest scores currently saved in a file called "highscores.txt". */
    void displayHighscores();
    //todo: call this method when user selects the 'HIGH SCORES' menu option, in the Menu scene.

}
