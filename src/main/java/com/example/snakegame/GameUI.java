package com.example.snakegame;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class GameUI {
    private GameLogic gameLogic;
    private static GridPane grid;
    private final VBox vBox = new VBox();
    private final BorderPane borderPane = new BorderPane();
    public static Food foodTest = new Food();
    public static ArrayList<Rectangle> snakeParts = new ArrayList<>();
    static Color snakeColor = Color.web("#00FF00");

    public GameUI() {
        gameLogic = new GameLogic();
        grid = new GridPane();
    }

    Scene setupUI() {
        borderPane.setStyle("-fx-background-color: black");
        vBox.setPadding(new javafx.geometry.Insets(15, 15, 15, 15));
        vBox.setStyle("-fx-border-color: green; -fx-border-width: 0 0 1 0;");

        borderPane.setTop(vBox);
        borderPane.setCenter(grid);
        setUpBoard(17, 20, Color.BLACK, Color.web("#00FF00"));
        setupVbox();
        initializeSnakeParts(snakeColor);

        foodTest.generateFood(gameLogic.snakeTest);

        Scene scene = new Scene(borderPane);

        return scene;

    }

    private void setupVbox() {
        vBox.setPadding(new javafx.geometry.Insets(15, 15, 15, 15));
        vBox.setStyle("-fx-border-color: green; -fx-border-width: 0 0 1 0;");

        TextFlow textFlow = new TextFlow();
        Text text = new Text("SCORE: ");
        text.setFont(Font.font("Verdana", 30));
        text.setFill(Color.WHITE);
        Text text2 = new Text(String.valueOf(gameLogic.getPoints()));
        text2.setFont(Font.font("Verdana", 34));
        text2.setFill(snakeColor);
        textFlow.getChildren().addAll(text, text2);
        vBox.getChildren().add(textFlow);
    }

    private void setUpBoard(int rowNum, int colNum, Color color, Color snakeColor) {
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(40);
                rec.setHeight(40);
                rec.setFill(color);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                grid.getChildren().addAll(rec);
            }
        }
    }

    private void initializeSnakeParts(Color snakeColor) {
        // initialize snake parts
        for (int i = 0; i < gameLogic.snakeTest.getBody().size(); i++) {
            snakeParts.add(new Rectangle(39.8, 39.8, snakeColor));
            GridPane.setColumnIndex(snakeParts.get(i), gameLogic.snakeTest.getBody().get(i).getX());
            GridPane.setRowIndex(snakeParts.get(i), gameLogic.snakeTest.getBody().get(i).getY());
            grid.getChildren().add(snakeParts.get(i));
        }

    }

    public static void updateUI(Snake snakeTest2, Food food) {
        for (int i = 0; i < snakeParts.size(); i++) {
            GridPane.setColumnIndex(snakeParts.get(i), snakeTest2.getBody().get(i).getX());
            GridPane.setRowIndex(snakeParts.get(i), snakeTest2.getBody().get(i).getY());
        }
        GridPane.setColumnIndex(foodTest.getFood(), food.getFoodX());
        GridPane.setRowIndex(foodTest.getFood(), food.getFoodY());

    }

    public static void addSnakePart(Snake snakeTest) {
        snakeParts.add(new Rectangle(39.8, 39.8, snakeColor));
        GridPane.setColumnIndex(snakeParts.get(snakeParts.size() - 1), snakeTest.getTail().getX());
        GridPane.setRowIndex(snakeParts.get(snakeParts.size() - 1), snakeTest.getTail().getY());
        grid.getChildren().add(snakeParts.get(snakeParts.size() - 1));
    }
}
