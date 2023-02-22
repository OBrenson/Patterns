package com.example.lab4mvc.controller;

import com.example.lab4mvc.model.Point;

import java.util.function.Consumer;

public class Controller {

    private Consumer<Point> addListener;
    private Consumer<Point> removeListener;

    public Point addPoint(double x) {
        Point point = new Point(x);
        if (addListener != null) {
            addListener.accept(point);
        }
        return point;
    }

    public void remove(Point point) {
        if (removeListener != null) {
            removeListener.accept(point);
        }
    }

    public void setAddListener(Consumer<Point> listener) {
        this.addListener = listener;
    }

    public void setRemoveListener(Consumer<Point> listener) {
        this.removeListener = listener;
    }
}
