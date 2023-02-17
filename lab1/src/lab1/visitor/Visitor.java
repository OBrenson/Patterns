package lab1.visitor;

import lab1.transport.Car;
import lab1.transport.Motorbike;

public interface Visitor {

    void visit(Car car);

    void visit(Motorbike bike);
}
