package lab2.decorator.lab2decorator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lab2.decorator.lab2decorator.facade.Car;
import lab2.decorator.lab2decorator.facade.MovementFacade;
import lab2.decorator.lab2decorator.facade.TrafficLight;

import java.io.IOException;
import java.util.Set;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {

        StackPane root = new StackPane();
        MovementFacade facade = new MovementFacade(root);

        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();

        new Thread(() -> {
           while (true) {
               try {
                   facade.move();
                   Thread.sleep(5000);
                   facade.stop();
                   Thread.sleep(5000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }).start();
    }

    public static void main(String[] args) {
        launch();
    }
}