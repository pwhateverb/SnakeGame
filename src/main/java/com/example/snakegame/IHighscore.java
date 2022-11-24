package com.example.snakegame;

import javafx.scene.Scene;


/** This interface connects the Highscores to the rest of the game. */
public interface IHighscore {

    /** returns true if user achieved a highScore. If so, the gameOver scene should display a prompt like "Input your name to be added to the highscores" */
    boolean highscoreCheck(int score); // checks if highSco returns true if the score was a highscore
    //todo: call in the gameOver screen (when user crashes the snake)

    /** saves user's name and score in a file called "highscores.txt". */
    void saveHighscore(int score, String name);
    //todo: call when the user inputs a name after being prompted in the gameOver scene

    /** returns a Scene displaying the 10 highest scores currently saved in a file called "highscores.txt". */
    Scene displayHighscores();
    //todo: call in the menu, when user selects "See Highscores".

}
