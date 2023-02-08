package lab2.decorator.lab2decorator.facade;

import javafx.scene.layout.Pane;

public class MovementFacade {

    private final Car car;
    private final TrafficLight tl;

    public MovementFacade(Pane pane) {
        tl = new TrafficLight(pane);
        car = new Car(pane);
        new Thread(car).start();
    }

    public void move() {
        tl.setYellow();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tl.setGreen();
        car.setCanMove(true);
    }

    public void stop() {
        tl.setYellow();
        car.setCanMove(false);
        try {
            Thread.sleep(1500);
            car.setCanMove(false);
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tl.setRed();
    }
}
