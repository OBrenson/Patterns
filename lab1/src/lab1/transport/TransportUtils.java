package lab1.transport;

import lab1.factorymethod.CarFactory;
import lab1.factorymethod.TransportFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TransportUtils {

    private static TransportFactory factory = new CarFactory();

    public static double getAverage(Transport vehicle) {
        return Arrays.stream(vehicle.getModelsPrices()).reduce(0.0, Double::sum)/(double)vehicle.getModelsNum();
    }

    public static void printModelsNamesAndPrices(Transport vehicle) {
        Double[] prices = vehicle.getModelsPrices();
        String[] names = vehicle.getModelsNames();
        IntStream.range(0, names.length).forEachOrdered(i ->
                System.out.printf("Name: %s, Price: %f%n", names[i], prices[i]));
    }

    public static Transport createInstance(String name, int size) {
        return factory.createInstance(name, size);
    }

    public static void setFactory(TransportFactory factory) {
        TransportUtils.factory = factory;
    }
}
