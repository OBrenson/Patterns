package com.example.lab3template.elements;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleElement extends AbstractElement{

    public CircleElement(Pane pane) {
        super(pane);
    }

    @Override
    protected Node getElement() {
        return new Circle(10, Color.RED);
    }
}
