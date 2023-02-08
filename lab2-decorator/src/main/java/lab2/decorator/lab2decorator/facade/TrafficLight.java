package lab2.decorator.lab2decorator.facade;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TrafficLight {

    private final Circle red = new Circle(19.0f, Color.GRAY);
    private final Circle yellow = new Circle(19.0f, Color.GRAY);
    private final Circle green = new Circle(19.0f, Color.GRAY);

    public TrafficLight(Pane pane) {
        Rectangle rectangle = new Rectangle(40, 120, Color.BLACK);
        rectangle.setTranslateY(-180);
        red.setTranslateY(-220);
        yellow.setTranslateY(-180);
        green.setTranslateY(-140);
        pane.getChildren().add(rectangle);
        pane.getChildren().add(red);
        pane.getChildren().add(yellow);
        pane.getChildren().add(green);
    }

    public void setYellow() {
        yellow.setFill(Color.YELLOW);
        green.setFill(Color.GRAY);
        red.setFill(Color.GRAY);
    }

    public void setGreen() {
        yellow.setFill(Color.GRAY);
        green.setFill(Color.GREEN);
        red.setFill(Color.GRAY);
    }

    public void setRed() {
        yellow.setFill(Color.GRAY);
        green.setFill(Color.GRAY);
        red.setFill(Color.RED);
    }
}
