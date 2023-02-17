package com.example.lab3state;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;

public class StudentState implements State{

    private final Pane pane;

    private final Rectangle session;
    private final Rectangle semester;
    private final Rectangle holidays;
    private Rectangle actual;

    private static final String SEP = FileSystems.getDefault().getSeparator();

    public StudentState(Pane pane) {
        this.pane = pane;
        semester = createElement("sleep.jpeg");
        session = createElement("work.png");
        holidays = createElement("happy.jpeg");
        actual = semester;
        pane.getChildren().add(actual);
    }

    @Override
    public void session() {
        changeTo(session);
    }

    @Override
    public void semester() {
        changeTo(semester);
    }

    @Override
    public void holidays() {
        changeTo(holidays);
    }

    private void changeTo(Rectangle newActual) {
        pane.getChildren().remove(actual);
        actual = newActual;
        pane.getChildren().add(actual);
    }

    private Rectangle createElement(String fileName) {
        Image map = null;
        try {
            map = new Image(new FileInputStream(
                    System.getProperty("user.dir") + SEP + "lab3-state" + SEP + fileName)
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImagePattern pattern = new ImagePattern(map);
        Rectangle node = new Rectangle(300, 300);
        node.setFill(pattern);
        node.setTranslateY(-50);
        return node;
    }

}
