package com.example.lab3observer.elements;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class UIElementHandler implements ElementState{

    protected double x;
    protected double y;
    protected Node firstElement;
    protected Node secondElement;
    protected Pane pane;
    private boolean isFirst = true;

    public UIElementHandler(double x, double y, Pane pane) {
        this.x = x;
        this.y = y;
        this.pane = pane;

        createFirstElement();
        createSecondElement();
        pane.getChildren().add(firstElement);
    }

    @Override
    public void change() {
        if (isFirst) {
            pane.getChildren().remove(firstElement);
            pane.getChildren().add(secondElement);
        } else {
            pane.getChildren().remove(secondElement);
            pane.getChildren().add(firstElement);
        }
        isFirst = !isFirst;
    }

    protected abstract void createFirstElement();

    protected abstract void createSecondElement();

    public Node getFirstElement() {
        return firstElement;
    }

    public Node getSecondElement() {
        return secondElement;
    }
}
