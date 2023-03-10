package lab1.factorymethod;

import lab1.transport.Motorbike;

public class MotorbikeFactory implements TransportFactory {

    @Override
    public Motorbike createInstance(String name, int size) {
        return new Motorbike(name, size);
    }
}
