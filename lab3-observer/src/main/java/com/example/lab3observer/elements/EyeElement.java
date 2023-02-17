package com.example.lab3observer.elements;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class EyeElement extends UIElementHandler{

    public EyeElement(double x, double y, Pane pane) {
        super(x, y, pane);
    }

    @Override
    protected void createFirstElement() {
        firstElement = new Circle(50.0, Color.WHITE);
        ((Circle)firstElement).setStroke(Color.BLACK);
        firstElement.setTranslateY(y);
        firstElement.setTranslateX(x);
    }

    @Override
    protected void createSecondElement() {
        secondElement = new Rectangle(100.0, 10.0);
        secondElement.setTranslateX(x);
        secondElement.setTranslateY(y);
    }
}
