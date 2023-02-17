package com.example.lab3observer.elements;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class UIElementHandler implements ElementObserver {

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
    public void change(double mouseX, double mouseY) {
        Node element = isFirst ? firstElement : secondElement;
        boolean isClicked = element.localToScreen(element.getBoundsInLocal()).contains(mouseX, mouseY);
        if(isClicked) {
            if (isFirst) {
                pane.getChildren().remove(firstElement);
                pane.getChildren().add(secondElement);
            } else {
                pane.getChildren().remove(secondElement);
                pane.getChildren().add(firstElement);
            }
            isFirst = !isFirst;
        }
    }

    protected abstract void createFirstElement();

    protected abstract void createSecondElement();
}
