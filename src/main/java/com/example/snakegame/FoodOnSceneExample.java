// package com.example.snakegame;

// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import javafx.scene.Group;
// import javafx.scene.paint.Color;

// import java.io.IOException;

// public class FoodOnSceneExample extends Application {

// @Override
// public void start(Stage stage) throws IOException {

// Group root = new Group();
// Scene scene = new Scene(root, 500, 500, Color.BLACK);
// stage.setScene(scene);
// stage.show();
// Food food = new Food(10, 10);
// root.getChildren().add(food.getFood());

// // on key press generate food
// scene.setOnKeyPressed(event -> {
// food.generateFood();
// });

// }

// public static void main(String[] args) {
// launch();
// }
// }
