package com.example.snakegame.Highscores;


import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This interface uses the {@link com.example.snakegame.Highscores.ScoreEngine} class to provide post-game and
 * highscore functionality to the game.
**/

public interface Scorable {

    /** This method returns a Scene displaying the 10 highest scores currently saved in a file called "highscores.txt".
     * <p/>
     * To be called inside a onAction/onClick function, with the following snippet:
     *      <pre> {@code
     *      Scorable iHighscores = new ScoreEngine();
     *      Scene displayHighscoresScene = iHighscores.displayHighscores(stage, menuScene);
     *      stage.setScene(displayHighscoresScene);
     *      }</pre>
     *
     *  @param initialStage pass the JavaFX stage where the whole application / game is happening
     *  @param initialScene pass a <b>new</b> JavaFX {@link com.example.snakegame.mainMenu} scene
     *
     *  @return returns a JavaFX scene that displays the top-10 highscores
     *  @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html">JavaFX Scene</a>
    **/

    Scene displayHighscores(Stage initialStage, Scene initialScene);


    /**
     * This method returns a Scene displaying the post-game screen. If user achieved a highScore, a prompt to submit a
     * highscore is included. A submission places the user's name and scoreAttained in a file called "highscores.txt".
     * <p/>
     * To be called inside a onAction/onClick function, with the following snippet:
     *      <pre> {@code
     *      Scorable iHighscores = new ScoreEngine();
     *      Scene postGameScreen = iHighscores.displayPostGame(stage, initialScene, scoreAttained);
     *      stage.setScene(postGameScreen);
     *      }</pre>
     *
     *      @param initialStage pass the JavaFX stage where the whole application / game is happening
     *      @param initialScene pass a <b>new</b> JavaFX {@link com.example.snakegame.mainMenu} scene
     *      @param scoreAttained pass the score just attained by the user
     *
     *      @return returns a JavaFX scene that displays the post-game screen
     *      @see <a href="https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html">JavaFX Scene</a>
    **/

    Scene displayPostGame(Stage initialStage, Scene initialScene, int scoreAttained);
}
