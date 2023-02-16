package com.example.lab3observer;

import com.example.lab3observer.elements.EyeElement;
import com.example.lab3observer.elements.NoseElement;
import com.example.lab3observer.elements.UIElementHandler;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();


        root.getChildren().add(new Circle(250, Color.rgb(222,222, 20)));

        List<UIElementHandler> elements = new ArrayList<>();

        elements.add(new EyeElement(-100, -100, root));
        elements.add(new EyeElement(100, -100, root));
        elements.add(new NoseElement(0,0, root));

        elements.forEach(el -> {
            el.getFirstElement().addEventHandler(MouseEvent.MOUSE_CLICKED,
                mouseEvent -> {
                    el.change();
                });
            el.getSecondElement().addEventHandler(MouseEvent.MOUSE_CLICKED,
                mouseEvent -> {
                    el.change();
                });
        });

        Scene scene = new Scene(root, 505, 505);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}