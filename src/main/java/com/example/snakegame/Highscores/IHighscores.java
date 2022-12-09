package com.example.snakegame.Highscores;


import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This interface connects the Highscores to the rest of the game.
 * Refer to IHighscoresSample for an example on how to use it.
    **/

public interface IHighscores {

    /** returns a Scene displaying the 10 highest scores currently saved in a file called "highscores.txt". */
    Scene displayHighscores(Stage initialStage, Scene initialScene);
    //todo: create a new Highscores object & call this method when user selects the 'HIGH SCORES' menu option, in the Menu scene.


    /**
     * If user achieved a highScore, the gameOver scene displays a prompt like "Input your name to be added to the highscores"
     * It saves user's name and score in a file called "highscores.txt". **/
    Scene displayPostGame(Stage initialStage, Scene menuScene, int score);
    //todo: create a new Highscores object & call this method when user presses any key after losing the game, in the Game scene

}
