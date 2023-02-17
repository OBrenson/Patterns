package com.example.lab3observer.elements;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class MouseClickPublisher {

    private final List<ElementObserver> observers;

    public MouseClickPublisher(Pane pane) {
        observers = new ArrayList<>();
        pane.setOnMouseClicked(mouseEvent -> {
            for(ElementObserver observer : observers) {
                observer.change(mouseEvent.getScreenX(), mouseEvent.getScreenY());
            }
        });
    }

    public void attach(ElementObserver observer) {
        observers.add(observer);
    }
}
