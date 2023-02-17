package lab1.visitor;

import lab1.transport.Car;
import lab1.transport.Motorbike;
import lab1.transport.Transport;

public class PrintVisitor implements Visitor{

    @Override
    public void visit(Car car) {
        printTransport(car, " ");
    }

    @Override
    public void visit(Motorbike bike) {
        printTransport(bike, "\n");
    }

    private void printTransport(Transport transport, String sep) {
        String[] names = transport.getModelsNames();
        Double[] prices = transport.getModelsPrices();

        for(int i = 0; i < transport.getModelsNum(); i++) {
            System.out.printf("Name: %s, Price: %s%s", names[i], prices[i], sep);
        }
    }
}
