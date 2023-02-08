package lab2.decorator.lab2decorator.facade;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable{

    private final Rectangle rectangle = new Rectangle(70,30, Color.CHOCOLATE);

    private volatile AtomicBoolean canMove;

    public Car(Pane pane) {
        rectangle.setTranslateX(210);
        pane.getChildren().add(rectangle);
        canMove= new AtomicBoolean(true);
    }

    public void setCanMove(boolean canMove) {
        this.canMove.set(canMove);
    }

    @Override
    public void run() {
        int i = -210;
        boolean lToR = true;
        while (true) {
            if (i > -30 && i < 30 && !canMove.get()) {
                System.out.println(LocalDateTime.now().toString() + " " + i);
            } else {
                if (lToR) {
                    i++;
                } else {
                    i--;
                }
                rectangle.setTranslateX(i);
                if (i == 210 || i == -210) {
                    lToR = !lToR;
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
