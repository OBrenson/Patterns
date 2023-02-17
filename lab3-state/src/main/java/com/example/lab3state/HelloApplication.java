package com.example.lab3state;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();

        State studentState = new StudentState(root);

        Button sessionBtn = new Button("СЕССИЯ");
        sessionBtn.setTranslateY(200);
        root.getChildren().add(sessionBtn);
        sessionBtn.setOnMouseClicked(mouseEvent -> {
            studentState.session();
        });

        Button semesterBtn = new Button("СЕМЕСТР");
        semesterBtn.setTranslateY(200);
        semesterBtn.setTranslateX(-100);
        root.getChildren().add(semesterBtn);
        semesterBtn.setOnMouseClicked(mouseEvent -> {
            studentState.semester();
        });

        Button holidaysBtn = new Button("КАНИКУЛЫ");
        holidaysBtn.setTranslateY(200);
        holidaysBtn.setTranslateX(100);
        root.getChildren().add(holidaysBtn);
        holidaysBtn.setOnMouseClicked(mouseEvent -> {
            studentState.holidays();
        });

        Scene scene = new Scene(root, 505, 505);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}