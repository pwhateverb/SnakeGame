package com.example.snakegame;

import com.example.snakegame.Highscores.Highscores;
import com.example.snakegame.Highscores.IHighscores;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class mainMenu extends Application {

    private static final Rectangle menuPointer = new Rectangle();

    private static int menuOption = 1;
    private static int pointPosition = -87;
    private static final String END_OF_LINE = System.lineSeparator();

    Scene mainMenuScene, instructionScene;
    Stage stage;
    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;
        Text mainMenu = new Text("SNAKE GAME" + END_OF_LINE + "MAIN MENU");
        mainMenu.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        mainMenu.setTextAlignment(TextAlignment.CENTER);
        mainMenu.setTranslateY(-180);
        mainMenu.setFill(Color.LIMEGREEN);

        menuPointer.setFill(Color.LIMEGREEN);
        menuPointer.setWidth(60);
        menuPointer.setHeight(30);
        menuPointer.setTranslateX(-220);
        paintPointer(menuOption);

        Text singlePlayer = new Text("SINGLE PLAYER");
        singlePlayer.setTranslateY(-90);
        singlePlayer.setTextAlignment(TextAlignment.CENTER);
        singlePlayer.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        singlePlayer.setFill(Color.LIMEGREEN);

        Text coOpMode = new Text("CO-OP MODE");
        coOpMode.setTextAlignment(TextAlignment.CENTER);
        coOpMode.setTranslateY(-50);
        coOpMode.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        coOpMode.setFill(Color.LIMEGREEN);

        Text instructions = new Text("INSTRUCTIONS");
        instructions.setTranslateY(-10);
        instructions.setTextAlignment(TextAlignment.CENTER);
        instructions.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        instructions.setFill(Color.LIMEGREEN);

        Text highScores = new Text("HIGH SCORES");
        highScores.setTextAlignment(TextAlignment.CENTER);
        highScores.setTranslateY(30);
        highScores.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        highScores.setFill(Color.LIMEGREEN);

        Text exitGame = new Text("EXIT GAME");
        exitGame.setTextAlignment(TextAlignment.CENTER);
        exitGame.setTranslateY(70);
        exitGame.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        exitGame.setFill(Color.LIMEGREEN);

        StackPane mainMenuLayout = new StackPane();
        mainMenuLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        mainMenuLayout.getChildren().addAll(mainMenu, singlePlayer, coOpMode, instructions, highScores, exitGame, menuPointer);

        mainMenuScene = new Scene(mainMenuLayout, 800, 800);
        mainMenuScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    menuOption--;
                    if (menuOption == 0) {
                        menuOption = 5;
                    }
                    changeMenuOption(menuOption);
                    break;
                case DOWN:
                    menuOption++;
                    if (menuOption == 6) {
                        menuOption = 1;
                    }
                    changeMenuOption(menuOption);
                    break;
                case ENTER:
                    activate(menuOption);
                    break;
            }
        });

        Text instructionString = new Text("Welcome!" + END_OF_LINE +
                END_OF_LINE + "This game will let you control a Snake that eats food to grow bigger" +
                END_OF_LINE + "You control the Snake by pressing the UP, DOWN, LEFT, RIGHT keys on your keyboard" +
                END_OF_LINE + "The food will appear randomly on your screen. " +
                END_OF_LINE + "If the Snake Head touches the food the snake will grow" +
                END_OF_LINE + "You will also gain points the larger your snake grow" +
                END_OF_LINE + "The Snake dies if it touches the edges of the window, or if it touches its own body" +
                END_OF_LINE + "When the Snake dies you can save your score by entering your name" +
                END_OF_LINE + END_OF_LINE + "Enjoy the game!");

        instructionString.setFont(Font.font("Verdana", 16));
        instructionString.setFill(Color.LIMEGREEN);
        instructionString.setTextAlignment(TextAlignment.CENTER);

        Text goBack = new Text("GO BACK TO MAIN MENU");
        goBack.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        goBack.setTextAlignment(TextAlignment.CENTER);
        goBack.setTranslateY(350);
        goBack.setFill(Color.LIMEGREEN);

        StackPane instructionLayout = new StackPane();
        instructionLayout.getChildren().addAll(instructionString, goBack);
        instructionLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        instructionScene = new Scene(instructionLayout, 800, 800);
        instructionScene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                stage.setScene(mainMenuScene);
            }
        });

        stage.setTitle("Snake Game");
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public void paintPointer(int menuOption) {
        if (menuOption == 1) {
            pointPosition = -87;
        } else if (menuOption == 2) {
            pointPosition = -47;
        } else if (menuOption == 3) {
            pointPosition = -7;
        } else if (menuOption == 4) {
            pointPosition = 37;
        } else if (menuOption == 5) {
            pointPosition = 77;
        }
        menuPointer.setTranslateY(pointPosition);
    }

    public void changeMenuOption(int menuOption) {
        this.menuOption = menuOption;
        paintPointer(this.menuOption);
    }

    //method for changing scene in the menu
    public void activate(int menuOption) {
        if (menuOption == 1) {
            stage.setScene(instructionScene);
        } else if (menuOption == 2) {
            stage.setScene(instructionScene);
        } else if (menuOption == 3) {
            stage.setScene(instructionScene);
        } else if (menuOption == 4) {
            IHighscores iHighscores = new Highscores();
            Scene displayHighscoresScene = iHighscores.displayHighscores(stage, mainMenuScene);
            stage.setScene(displayHighscoresScene);
        } else if (menuOption == 5) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}