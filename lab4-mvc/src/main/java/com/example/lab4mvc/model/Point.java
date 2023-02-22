package com.example.lab4mvc.model;

public class Point {

    private double x;

    private double y;

    public Point() {

    }

    public Point(double x) {
        this.x = x;
        y = calculate(x);
    }

    private double calculate(double x) {
        return x * x + 2*x - 3;
    }

    public String getX() {
        return Double.toString(x);
    }

    public void setX(double x) {
        this.x = x;
    }

    public String getY() {
        return Double.toString(y);
    }

    public double getXDouble() {
        return x;
    }

    public double getYDouble() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
