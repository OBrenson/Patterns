package com.example.lab3template;

import com.example.lab3template.elements.AbstractElement;
import com.example.lab3template.elements.CircleElement;
import com.example.lab3template.elements.RectangleElement;
import com.example.lab3template.elements.StarElement;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.function.Function;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = new StackPane();

        Function<Pane, AbstractElement> f = StarElement::new;
        AbstractElement element = f.apply(root);

        Button btn = new Button("ПУСК");
        btn.setTranslateX(-200);
        btn.setTranslateY(-200);
        btn.setOnMouseClicked(mouseEvent -> {
            new Thread(f.apply(root)).start();
        });

        Button btnClose = new Button("ЗАКРЫТЬ");
        btnClose.setTranslateX(-100);
        btnClose.setTranslateY(-200);
        btnClose.setOnMouseClicked(mouseEvent -> {
            stage.close();
        });

        root.getChildren().add(btn);
        root.getChildren().add(btnClose);
        Scene scene = new Scene(root, 505, 505);
        stage.setScene(scene);
        stage.show();
        new Thread(element).start();
    }

    public static void main(String[] args) {
        launch();
    }
}