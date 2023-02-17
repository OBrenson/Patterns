package com.example.lab3observer.elements;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;

public class MouthElement extends UIElementHandler{

    private static final String SEP = FileSystems.getDefault().getSeparator();

    public MouthElement(double x, double y, Pane pane) {
        super(x, y, pane);
    }

    @Override
    protected void createFirstElement() {
        Image map = null;
        try {
            map = new Image(new FileInputStream(
                    System.getProperty("user.dir") + SEP + "lab3-observer" + SEP + "smile.png")
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImagePattern pattern = new ImagePattern(map);
        firstElement = new Rectangle(150, 150);
        ((Rectangle)firstElement).setFill(pattern);
        firstElement.setTranslateY(y);
        firstElement.setTranslateX(x);
    }

    @Override
    protected void createSecondElement() {
        secondElement = new Rectangle(200.0, 10.0);
        secondElement.setTranslateX(x);
        secondElement.setTranslateY(y);
    }
}
