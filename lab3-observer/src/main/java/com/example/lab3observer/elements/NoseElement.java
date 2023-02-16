package com.example.lab3observer.elements;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;

public class NoseElement extends UIElementHandler{

    private static final String SEP = FileSystems.getDefault().getSeparator();

    public NoseElement(double x, double y, Pane pane) {
        super(x, y, pane);
    }

    @Override
    protected void createFirstElement() {
        Image map = null;
        try {
            map = new Image(new FileInputStream(
                    System.getProperty("user.dir") + SEP + "lab3-observer" + SEP + "firstNose.png")
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImagePattern pattern = new ImagePattern(map);
        firstElement = new Rectangle(100, 100);
        ((Rectangle)firstElement).setFill(pattern);
    }

    @Override
    protected void createSecondElement() {
        Image map = null;
        try {
            map = new Image(new FileInputStream(
                    System.getProperty("user.dir") + SEP + "lab3-observer" + SEP + "firstNose.png")
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImagePattern pattern = new ImagePattern(map);
        secondElement = new Rectangle();
        ((Rectangle)secondElement).setFill(pattern);
    }
}
