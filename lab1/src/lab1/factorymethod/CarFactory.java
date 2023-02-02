package lab1.factorymethod;

import lab1.transport.Car;

public class CarFactory extends TransportFactory{

    @Override
    public Car createInstance(String name, int size) {
        return new Car(name, size);
    }
}
