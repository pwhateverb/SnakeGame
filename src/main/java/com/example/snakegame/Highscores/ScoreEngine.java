package com.example.snakegame.Highscores;

import com.example.snakegame.Controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;


/**
 * Implementation of the post-game and highscore functionalities.
 * <p/>
 * Used through the interface {@link com.example.snakegame.Highscores.Scorable}.
 **/

public class ScoreEngine implements Scorable {

    /**
     * Saved highscores get placed in this file.
     */
    String fileName = "highscores.txt";

    /**
     * Reads the file related to the path string in the fileName attribute.
     *
     * @return An arrayList of Highscore objects
     */
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

    /**
     * Saves an arrayList of Highscore objects into the file related to the path string in the fileName attribute.
     */
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

    /**
     * Used to determine whether a new Highscore object should be created.
     *
     * @return boolean value of whether a certain score is high enough to belong to the Top 10.
     */
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

    /**
     * Creates a Highscore object and updates the file containing the top-10 highscores.
     */
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

    /**
     * Loads and inflates a given XML layout into a Scene.
     */
    private Scene loadLayout(String layoutName) {
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(layoutName));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 800);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return scene;
    }

    /**
     * Specified in the {@link com.example.snakegame.Highscores.Scorable} interface.
     */
    @Override
    public Scene displayHighscores(Stage initialStage, Scene initialScene) {

        Scene scene = loadLayout("display-highscores-layout.fxml");

        Text textLeft = (Text) scene.lookup("#textLeft");
        Text textRight = (Text) scene.lookup("#textRight");
        Button closeButton = (Button) scene.lookup("#buttonthing");

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

        textLeft.setText(String.valueOf(stringLeft));
        textRight.setText(String.valueOf(stringRight));

        closeButton.setOnAction(e -> {
            initialStage.setScene(initialScene);
            initialStage.show();
        });

        return scene;
    }


    /**
     * Specified in the {@link com.example.snakegame.Highscores.Scorable} interface.
     */
    @Override
    public Scene displayPostGame(Stage initialStage, Scene initialScene, int scoreAttained) {

        Scene scene = loadLayout("post-game-layout.fxml");

        Text finalScore = (Text) scene.lookup("#finalScore");
        TextField result = (TextField) scene.lookup("#result");
        Button saveHighscore = (Button) scene.lookup("#saveHighscore");
        Text submitText = (Text) scene.lookup("#submitText");
        Button closeButton = (Button) scene.lookup("#closeButton");

        /**
         * Java FX method to post this as a runnable event without blocking the rest of code execution
         * @see https://docs.oracle.com/javase/8/javafx/api/javafx/application/Platform.html#runLater-java.lang.Runnable-
         */
        Platform.runLater(result::requestFocus);

        finalScore.setText("FINAL SCORE: " + scoreAttained);

        if (highscoreCheck(scoreAttained)) {
            result.setManaged(true);
            saveHighscore.setManaged(true);
        } else {
            submitText.setText("NO HIGHSCORE ACHIEVED");
            saveHighscore.setManaged(false);
            result.setManaged(false);
        }


        result.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {

                if (saveHighscore.isDisabled()) {
                    initialStage.setScene(initialScene);

                } else if (highscoreCheck(scoreAttained)) {
                    if (!saveHighscore.isDisable()) {
                        if (result.getText().isBlank()) {
                            showBlankNameAlert(submitText);
                        } else {
                            saveHighscore(result.getText(), scoreAttained);
                            saveHighscore.setDisable(true);
                            submitText.setText(result.getText() + "'s highscore submitted (" + scoreAttained + " points)");
                        }
                    }
                } else {
                    initialStage.setScene(initialScene);
                }
            }
            if (event.getCode() == KeyCode.ESCAPE) {
                initialStage.setScene(initialScene);
            }
        });

        closeButton.setOnAction(e -> {
            initialStage.setScene(initialScene);
        });

        return scene;
    }

    private void showBlankNameAlert(Text text) {
        text.setText("NAME CAN'T BE BLANK. WRITE A VALID NAME");

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                if (!text.getText().equals("")) {
                    text.setText("");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });
        thread.start();

    }

}


