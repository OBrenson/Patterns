package com.example.lab3observer;

import com.example.lab3observer.elements.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();


        root.getChildren().add(new Circle(250, Color.rgb(222,222, 20)));


        MouseClickPublisher publisher = new MouseClickPublisher(root);

        publisher.attach(new EyeElement(-100, -100, root));
        publisher.attach(new EyeElement(100, -100, root));
        publisher.attach(new NoseElement(0,0, root));
        publisher.attach(new MouthElement(0, 120, root));

        Scene scene = new Scene(root, 505, 505);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}