package com.example.snakegame.Highscores;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class IHighscoresSample extends Application {

    /**
     * Sample class to showcase the use of the IHighscores interface.
     * Each button setOnAction shows how to call the respective interface method.
     **/

    Random r = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)  {
        stage.setTitle("Highscores Demo");
        Label label = new Label("Welcome to the highscores implementation demo. \nChoose a functionality:");

        VBox vBox = new VBox(20);

        Button buttonDisplay = new Button("Display Highscores");
        Button buttonPost = new Button("Post-game (random score)");

        vBox.getChildren().addAll(label, buttonDisplay, buttonPost);
        Scene menuScene = new Scene(vBox, 800,800);

        buttonDisplay.setOnAction(e->{
            IHighscores iHighscores = new Highscores();
            Scene displayHighscoresScene = iHighscores.displayHighscores(stage, menuScene);
            stage.setScene(displayHighscoresScene);
            stage.show();
        } );

        buttonPost.setOnAction(e->{
            IHighscores iHighscores = new Highscores();
            Scene postGameScreen = iHighscores.displayPostGame(stage, menuScene, menuScene, randomNumber());
            stage.setScene(postGameScreen);
            stage.show();
        } );

        stage.setScene(menuScene);
        stage.show();
    }

    int randomNumber() {
        return r.nextInt(10000);
    }

}