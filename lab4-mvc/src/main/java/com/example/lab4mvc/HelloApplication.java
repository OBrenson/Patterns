package com.example.lab4mvc;

import com.example.lab4mvc.controller.Controller;
import com.example.lab4mvc.view.PointsGraph;
import com.example.lab4mvc.view.PointsTable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FlowPane root = new FlowPane(10,10);

        Controller controller = new Controller();
        new PointsTable(root, controller);
        new PointsGraph(root, controller);
        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Graph");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}