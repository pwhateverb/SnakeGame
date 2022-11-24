module com.example.snakegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.snakegame to javafx.fxml;
    exports com.example.snakegame;
}