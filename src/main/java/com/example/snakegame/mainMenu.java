package com.example.snakegame;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.input.KeyEvent;

import javafx.stage.Stage;

public class mainMenu extends Application {

    private static final Rectangle menuPointer = new Rectangle();

    private static final String END_OF_LINE = System.lineSeparator();

    private static int pointPosition = -150;

    private static int menuOption = 1;
    private Scene mainMenuScene, instructionScene, multiPlayerScene, singlePlayerScene, highScoreScene;

    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        Image icon = new Image("C:/Users/Kalle/Desktop/snakes9.jpg");
        stage.getIcons().add(icon);

        Label mainMenu = new Label("SNAKE GAME" + END_OF_LINE + " MAIN MENU");
        mainMenu.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        mainMenu.setTranslateX(300);
        mainMenu.setTranslateY(30);
        mainMenu.setTextFill(Color.LIMEGREEN);

        menuPointer.setFill(Color.LIMEGREEN);
        menuPointer.setWidth(60);
        menuPointer.setHeight(30);
        menuPointer.setTranslateX(150);
        paintPointer(menuOption);

        Button singlePlayer = new Button("1. SINGLE PLAYER");
        singlePlayer.setTranslateX(250);
        singlePlayer.setTranslateY(310);
        singlePlayer.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        singlePlayer.setTextFill(Color.LIMEGREEN);
        singlePlayer.setStyle("-fx-background-color: #000000; ");
        /*
         * singlePlayer.setOnAction(e -> {
         * changeMenuOption(1);
         * activate(menuOption);
         * });
         */

        Button coOpMode = new Button("2. CO-OP MODE");
        coOpMode.setTranslateX(250);
        coOpMode.setTranslateY(110);
        coOpMode.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        coOpMode.setTextFill(Color.LIMEGREEN);
        coOpMode.setStyle("-fx-background-color: #000000; ");
        /*
         * coOpMode.setOnAction(e -> {
         * changeMenuOption(2);
         * activate(menuOption);
         * });
         */

        Button instructions = new Button("3. INSTRUCTIONS");
        instructions.setTranslateX(250);
        instructions.setTranslateY(320);
        instructions.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        instructions.setTextFill(Color.LIMEGREEN);
        instructions.setStyle("-fx-background-color: #000000; ");
        /*
         * instructions.setOnAction(e -> {
         * changeMenuOption(3);
         * activate(menuOption);
         * });
         */

        Button highScores = new Button("4. HIGH SCORES");
        highScores.setTranslateX(250);
        highScores.setTranslateY(40);
        highScores.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        highScores.setTextFill(Color.LIMEGREEN);
        highScores.setStyle("-fx-background-color: #000000; ");
        /*
         * highScores.setOnAction(e -> {
         * changeMenuOption(4);
         * activate(menuOption);
         * });
         */

        Button exitGame = new Button("5. EXIT GAME");
        exitGame.setTranslateX(250);
        exitGame.setTranslateY(160);
        exitGame.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        exitGame.setTextFill(Color.LIMEGREEN);
        exitGame.setStyle("-fx-background-color: #000000; ");
        /*
         * exitGame.setOnAction(e -> {
         * changeMenuOption(5);
         * activate(menuOption);
         * });
         */

        VBox layout1 = new VBox(20);
        layout1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        layout1.getChildren().addAll(singlePlayer, instructions, mainMenu, coOpMode, exitGame, highScores, menuPointer);
        mainMenuScene = new Scene(layout1, 800, 800);

        mainMenuScene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case DIGIT1:
                    changeMenuOption(1);
                    activate(menuOption);
                    break;
                case DIGIT2:
                    changeMenuOption(2);
                    activate(menuOption);
                    break;
                case DIGIT3:
                    changeMenuOption(3);
                    activate(menuOption);
                    break;
                case DIGIT4:
                    changeMenuOption(4);
                    activate(menuOption);
                    break;
                case DIGIT5:
                    changeMenuOption(5);
                    activate(menuOption);
                    break;
                case SHIFT:
                    activate(menuOption);
                    break;
                case W:
                    menuOption--;
                    if (menuOption == 0) {
                        menuOption = 5;
                    }
                    changeMenuOption(menuOption);
                    break;
                case S:
                    menuOption++;
                    if (menuOption == 6) {
                        menuOption = 1;
                    }
                    changeMenuOption(menuOption);
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

        Button goBack = new Button("1. GO BACK TO MAIN MENU");
        goBack.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        goBack.setTranslateX(10);
        goBack.setTranslateY(250);
        goBack.setStyle("-fx-background-color: #000000; ");
        goBack.setTextFill(Color.LIMEGREEN);
        goBack.setOnAction(e -> stage.setScene(mainMenuScene));

        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(instructionString, goBack);
        layout2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        instructionScene = new Scene(layout2, 800, 800);
        instructionScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DIGIT1) {
                stage.setScene(mainMenuScene);
            }
        });

        Text multiPlayer = new Text("Multi Player Mode");
        multiPlayer.setFont(Font.font(20));
        multiPlayer.setFill(Color.LIMEGREEN);
        multiPlayer.setTextAlignment(TextAlignment.CENTER);

        Text highScore = new Text("High Scores");
        multiPlayer.setFont(Font.font(20));
        multiPlayer.setFill(Color.LIMEGREEN);
        multiPlayer.setTextAlignment(TextAlignment.CENTER);

        StackPane layout3 = new StackPane();
        layout3.getChildren().add(multiPlayer);
        layout3.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        multiPlayerScene = new Scene(layout3, 800, 800);

        Text sPlayer = new Text("Single Player Mode");
        sPlayer.setFont(Font.font(20));
        sPlayer.setFill(Color.LIMEGREEN);
        sPlayer.setTextAlignment(TextAlignment.CENTER);

        StackPane layout4 = new StackPane();
        layout4.getChildren().add(sPlayer);
        layout4.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        singlePlayerScene = new Scene(layout4, 800, 800);

        StackPane layout5 = new StackPane();
        layout5.getChildren().add(highScore);
        layout5.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        highScoreScene = new Scene(layout5, 800, 800);

        stage.setScene(mainMenuScene);
        stage.setTitle("Snake Game");
        stage.setResizable(false);
        stage.show();
    }

    public void activate(int menuOption) {

        if (menuOption == 1) {
            stage.setScene(multiPlayerScene);
        } else if (menuOption == 2) {
            stage.setScene(singlePlayerScene);
        } else if (menuOption == 3) {
            stage.setScene(instructionScene);
        } else if (menuOption == 4) {
            stage.setScene(highScoreScene);
        } else if (menuOption == 5) {
            System.exit(0);
        }
    }

    public void paintPointer(int menuOption) {
        if (menuOption == 1) {
            pointPosition = -150;
        } else if (menuOption == 2) {
            pointPosition = -110;
        } else if (menuOption == 3) {
            pointPosition = -70;
        } else if (menuOption == 4) {
            pointPosition = -30;
        } else if (menuOption == 5) {
            pointPosition = 10;
        }
        menuPointer.setTranslateY(pointPosition);
    }

    public void changeMenuOption(int menuOption) {
        this.menuOption = menuOption;
        paintPointer(this.menuOption);
    }

    public static void main(String[] args) {
        launch();
    }
}