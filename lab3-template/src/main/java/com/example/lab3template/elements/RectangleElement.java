package com.example.lab3template.elements;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectangleElement extends AbstractElement{

    public RectangleElement(Pane pane) {
        super(pane);
    }

    @Override
    protected Node getElement() {
        return new Rectangle(20, 20, Color.RED);
    }
}
