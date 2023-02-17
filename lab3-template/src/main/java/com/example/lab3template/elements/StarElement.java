package com.example.lab3template.elements;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class StarElement extends AbstractElement{

    public StarElement(Pane pane) {
        super(pane);
    }

    @Override
    protected Node getElement() {
        Polygon polygon = new Polygon();

        polygon.getPoints().addAll(0.0, -50.0,
                10.0, -10.0,
                50.0, 0.0,
                10.0, 10.0,
                0.0, 50.0,
                -10.0, 10.0,
                -50.0, 0.0
                -10.0, -10.0,
                -10.0, -10.0);

        polygon.setFill(Color.YELLOWGREEN);
        return new Group(polygon);
    }
}
