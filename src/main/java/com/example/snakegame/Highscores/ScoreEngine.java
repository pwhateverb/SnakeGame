package com.example.snakegame.Highscores;

import com.example.snakegame.Controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


public class ScoreEngine implements Scorable {

    String fileName = "highscores.txt";

    private ArrayList<Highscore> readFromFile() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Highscore>>() {
        }.getType();
        ArrayList<Highscore> highscoreList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            highscoreList = gson.fromJson(fileReader, type);
            fileReader.close();
            if (highscoreList == null) {
                highscoreList = new ArrayList<>();
            }
            return highscoreList;
        } catch (FileNotFoundException e) {
            return highscoreList;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return highscoreList;
        }
    }

    private void saveToFile(ArrayList<Highscore> highscoreList) {
        Gson gson = new Gson();
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            gson.toJson(highscoreList, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean highscoreCheck(int score) {
        ArrayList<Highscore> highscoreList = readFromFile();
        int amountOfHighscoresSaved = 10;
        if (highscoreList.size() < amountOfHighscoresSaved) {
            return true;
        } else {
            Highscore smallestHighscore = highscoreList.get(0);
            for (Highscore highscore : highscoreList) {
                if (highscore.getScore() < smallestHighscore.getScore()) {
                    smallestHighscore = highscore;
                }
            }
            if (score > smallestHighscore.getScore()) {
                return true;
            } else {
                return false;
            }
        }
    }


    private void saveHighscore(String name, int score) {
        ArrayList<Highscore> highscoreList = readFromFile();
        Highscore newHighscore = new Highscore(name, score);
        int amountOfHighscoresSaved = 10;

        if (highscoreList.size() < amountOfHighscoresSaved) {
            highscoreList.add(newHighscore);
        } else {
            Highscore smallestHighscore = highscoreList.get(0);
            for (Highscore highscore : highscoreList) {
                if (highscore.getScore() < smallestHighscore.getScore()) {
                    smallestHighscore = highscore;
                }
            }
            if (newHighscore.getScore() > smallestHighscore.getScore()) {
                highscoreList.remove(smallestHighscore);
                highscoreList.add(newHighscore);
            } else {
                //return; //no need to sort
            }
        }

        Collections.sort(highscoreList);
        saveToFile(highscoreList);
    }

    @Override
    public Scene displayHighscores(Stage initialStage, Scene initialScene) {

        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("display-highscores-layout.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 800);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        ArrayList<Highscore> highscoreList = readFromFile();
        if (highscoreList == null) {
            highscoreList = new ArrayList<>();
        }

        StringBuilder stringLeft = new StringBuilder();
        StringBuilder stringRight = new StringBuilder();


        for (Highscore highscore : highscoreList) {
            stringLeft.append(highscore.getName()).append(System.lineSeparator());
            stringRight.append(highscore.getScore()).append(System.lineSeparator());
        }
        Text textLeft = (Text) scene.lookup("#textLeft");
        textLeft.setText(String.valueOf(stringLeft));
        textLeft.setFont(Font.font("Verdana", FontWeight.BOLD, 28));

        Text textRight = (Text) scene.lookup("#textRight");

        textRight.setText(String.valueOf(stringRight));
        textRight.setFont(Font.font("Verdana", FontWeight.BOLD, 28));

        StackPane stackPane = (StackPane) scene.lookup("#stackPane");

        BorderPane borderPane = (BorderPane) scene.lookup("#borderPane");
        borderPane.setMaxSize(300, 200);
        borderPane.setLeft(textLeft);
        borderPane.setRight(textRight);
        borderPane.setPadding(new Insets(30, 50, 30, 50));

        Button closeButton = (Button) scene.lookup("#buttonthing");

        closeButton.setOnAction(e -> {
            initialStage.setScene(initialScene);
            initialStage.show();
        });

        stackPane.setPadding(new Insets(20));
        return scene;
    }


    @Override
    public Scene displayPostGame(Stage initialStage, Scene menuScene, int score) {

        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("post-game-layout.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 800);
//todo            scene.getStylesheets().add("path/post-game-stylesheet.css");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        Button closeButton = (Button) scene.lookup("#closeButton");


        closeButton.setOnAction(e -> {
            initialStage.setScene(menuScene);
        });


        Text finalScore = (Text) scene.lookup("#finalScore");
        finalScore.setText("FINAL SCORE: " + score);
        finalScore.setFont(Font.font("Verdana", FontWeight.BOLD, 28));


        TextField result = (TextField) scene.lookup("#result");
        result.setFont(Font.font("Verdana", FontWeight.BOLD, 28));

        String styles =
                "-fx-background-color: black;" +
                        "-fx-background-insets: 0;" +
                        "-fx-padding: 1 3 1 3;" +
                        "-fx-text-fill: #40bc52";
        result.setStyle(styles);


        result.setMaxSize(200, 50);
        Button saveHighscore = (Button) scene.lookup("#saveHighscore");

        Platform.runLater(result::requestFocus);

        //result.requestFocus();


        Text submitLabel = (Text) scene.lookup("#submitLabel");
        submitLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 28));

        if (highscoreCheck(score)) {
            result.setManaged(true);
            saveHighscore.setManaged(true);
        } else {
            submitLabel.setText("NO HIGHSCORE ACHIEVED");
            saveHighscore.setManaged(false);
            result.setManaged(false);
        }

        result.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (!saveHighscore.isDisable()) {
                    if (result.getText().equals("")) {
                        submitLabel.setText("NAME CAN'T BE BLANK. WRITE A VALID NAME");

                        Thread thread = new Thread(() -> {
                            try {
                                Thread.sleep(1000);
                                if(!submitLabel.getText().equals("")){
                                    submitLabel.setText("");
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                System.out.println(e.getMessage());
                            }
                        });
                        thread.start();

                    } else {
                        saveHighscore(result.getText(), score);
                        saveHighscore.setDisable(true);
                        submitLabel.setText(result.getText() + "'s highscore submitted (" + score + " points)");
                    }
                }
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                initialStage.setScene(menuScene);
            }

        });

        /*saveHighscore.setOnAction(e -> {
            if(result.getText().equals("")){
                submitLabel.setText("NAME CAN'T BE BLANK. WRITE A VALID NAME");
            } else {
                saveHighscore(result.getText(), score);
                saveHighscore.setDisable(true);
                submitLabel.setText(result.getText()+"'s highscore submitted ("+ score +" points)");
            }
        });*/
        StackPane stackPane2 = (StackPane) scene.lookup("#stackPane2");
        stackPane2.setPadding(new Insets(20));

        return scene;
    }

}

