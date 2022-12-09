package com.example.snakegame.Highscores;


import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This interface connects the ScoreEngine to the rest of the game.
    **/

public interface Scorable {

    /** returns a Scene displaying the 10 highest scores currently saved in a file called "highscores.txt".
     * Call this way, inside a onAction/onClick function:
     *      Scorable iHighscores = new ScoreEngine();
     *      Scene displayHighscoresScene = iHighscores.displayHighscores(stage, menuScene);
     *      stage.setScene(displayHighscoresScene);
     * */
    Scene displayHighscores(Stage initialStage, Scene initialScene);



    /**
     * If user achieved a highScore, the gameOver scene displays a prompt like "Input your name to be added to the highscores"
     * It saves user's name and score in a file called "highscores.txt".
     * Call this way, inside a onAction/onClick function:
     *      Scorable iHighscores = new ScoreEngine();
     *      Scene postGameScreen = iHighscores.displayPostGame(stage, menuScene, randomNumber());
     *      stage.setScene(postGameScreen);
     * **/
    Scene displayPostGame(Stage initialStage, Scene menuScene, int score);
    //todo: create a new ScoreEngine object & call this method when user presses any key after losing the game, in the Game scene

}
