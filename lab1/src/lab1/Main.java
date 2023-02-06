package lab1;

import lab1.exceptions.DuplicateModelNameException;
import lab1.exceptions.ModelPriceOutOfBoundsException;
import lab1.exceptions.NoSuchModelNameException;
import lab1.factorymethod.CarFactory;
import lab1.factorymethod.MotorbikeFactory;
import lab1.singleton.SingletonProperties;
import lab1.transport.Car;
import lab1.transport.Motorbike;
import lab1.transport.Transport;
import lab1.transport.TransportUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {
        testSingleton();
        testTransport();
        testPrototype(new CarFactory().createInstance("brand", 2));
        testPrototype(new MotorbikeFactory().createInstance("brand", 2));
    }

    public static void testPrototype(Transport transport) throws CloneNotSupportedException,
            DuplicateModelNameException, NoSuchModelNameException {

        testVehicleByArrays(transport, "brand", 2, new String[]{"car1", "car2"},
                new Double[]{10000.0, 20000.0});
        Transport transportCopy = transport.clone();
        assert transportCopy.getBrand().equals(transport.getBrand());
        assert transportCopy.getModelsNum() == transport.getModelsNum();
        for(String model : transport.getModelsNames()) {
            assert transport.getModelPriceByName(model) == transportCopy.getModelPriceByName(model);
        }
        transport.setModelPriceByName("car1", 0);
        assert transport.getModelPriceByName("car1") != transportCopy.getModelPriceByName("car1");
    }

    public static void testSingleton() {
        SingletonProperties sp = SingletonProperties.getInstance();
        Properties props = sp.getProperties();
        assert "org.postgresql.Driver".equals(props.getProperty("jdbc.drivers"));

        System.out.println("config.properties");
        for (Object key : props.keySet()) {
            System.out.printf("%s : %s%n", key.toString(), props.getProperty(key.toString()));
        }
    }

    public static void testTransport() throws DuplicateModelNameException, NoSuchModelNameException {

        Transport motorbike = new CarFactory().createInstance("car", 1);
        testVehicle(motorbike);

        Transport motorbike1 = new MotorbikeFactory().createInstance("yamaha", 1);
        testVehicle(motorbike1);
    }

    private static void testVehicleFromArgs(String brand, int size, String[] names, Double[] prices)
            throws NoSuchModelNameException, DuplicateModelNameException {

        Car car = new CarFactory().createInstance(brand, size);
        testVehicleByArrays(car, brand, size, names, prices);

        Motorbike motorbike = new MotorbikeFactory().createInstance(brand, size);
        testVehicleByArrays(motorbike, brand, size, names, prices);
    }

    private static void testVehicleByArrays(Transport vehicle, String brand, int size,final String[] names, Double[] prices)
            throws NoSuchModelNameException,DuplicateModelNameException {

        System.out.println(vehicle instanceof Car ? "Car" : "Motorbike");
        assert vehicle.getModelsNum() == size;
        assert vehicle.getBrand().equals(brand);

        IntStream.range(0, names.length).forEachOrdered(i -> {
            try {
                vehicle.addModel(names[i], prices[i]);
            } catch (DuplicateModelNameException e) {
                e.printStackTrace();
            }
        });
        String[] vNames = vehicle.getModelsNames();
        Double[] vPrices = vehicle.getModelsPrices();
        IntStream.range(0, names.length).forEachOrdered(i -> {
            assert names[i].equals(vNames[i]);
            assert Objects.equals(vPrices[i], prices[i]);
        });

        double avr = Arrays.stream(prices).reduce(Double::sum).get()/size;
        double vAvr = TransportUtils.getAverage(vehicle);
        assert avr == vAvr;

        vehicle.addModel("kalina", 10.0);
        vehicle.setModelName("kalina", "kalina-plus");
        assert Arrays.asList(vehicle.getModelsNames()).contains("kalina-plus");
        assert !Arrays.asList(vehicle.getModelsNames()).contains("kalina");

        vehicle.setModelPriceByName("kalina-plus", 10000.0);
        assert vehicle.getModelPriceByName("kalina-plus") == 10000.0;

        vehicle.deleteModel("kalina-plus");
        assert !Arrays.asList(vehicle.getModelsNames()).contains("kalina-plus");

        TransportUtils.printModelsNamesAndPrices(vehicle);

        boolean isException = false;
        try {
            vehicle.setModelPriceByName(names[0] + "asd", 20000.0);
        } catch (NoSuchModelNameException e) {
            isException = true;
        }
        assert isException;
        isException = false;

        try {
            vehicle.getModelPriceByName("kalina");
        } catch (NoSuchModelNameException e) {
            isException = true;
        }
        assert isException;
        isException = false;

        try {
            vehicle.setModelPriceByName(names[0], -100);
        } catch (ModelPriceOutOfBoundsException e) {
            isException = true;
        }
        assert isException;
        isException = false;

        try {
            vehicle.addModel(names[0], 1000000.0);
        } catch (DuplicateModelNameException e) {
            isException = true;
        }
        assert isException;
    }

    private static void testVehicle(Transport vehicle) throws DuplicateModelNameException, NoSuchModelNameException {
        System.out.println("Testing vehicles " + vehicle.getBrand());
        vehicle.addModel("vesta", 100000.0);
        vehicle.addModel("x-ray", 150000.0);

        double avr = TransportUtils.getAverage(vehicle);
        //assert avr == 250000.0/2.0;

        String[] names = vehicle.getModelsNames();
        Double[] prices = vehicle.getModelsPrices();
        assert names[0].equals("vesta");
        assert names[1].equals("x-ray");
        assert prices[0] == 100000.0;
        assert prices[1] == 150000.0;

        vehicle.addModel("priora", 200000.0);
        vehicle.addModel("kalina", 250000.0);
        vehicle.addModel("kalina-sport", 300000.0);
        TransportUtils.printModelsNamesAndPrices(vehicle);

        //assert vehicle.getModelsNum() == 5;

        vehicle.setModelName("kalina", "kalina-plus");
        names = vehicle.getModelsNames();
        assert Arrays.asList(names).contains("kalina-plus");
        assert !Arrays.asList(names).contains("kalina");

        vehicle.setModelPriceByName("vesta", 120000.0);
        assert vehicle.getModelPriceByName("vesta") == 120000.0;

        boolean isException = false;
        try {
            vehicle.setModelPriceByName("kalina", 20000.0);
        } catch (NoSuchModelNameException e) {
            isException = true;
        }
        assert isException;
        isException = false;

        try {
            vehicle.getModelPriceByName("kalina");
        } catch (NoSuchModelNameException e) {
            isException = true;
        }
        assert isException;
        isException = false;

        try {
            vehicle.setModelPriceByName("kalina-plus", -100);
        } catch (ModelPriceOutOfBoundsException e) {
            isException = true;
        }
        assert isException;
        isException = false;

        try {
            vehicle.addModel("kalina-plus", 1000000.0);
        } catch (DuplicateModelNameException e) {
            isException = true;
        }
        assert isException;
        isException = false;

        vehicle.deleteModel("vesta");
        vehicle.deleteModel("priora");
        vehicle.deleteModel("kalina-sport");
        try {
            vehicle.deleteModel("vesta");
        } catch (NoSuchModelNameException e) {
            isException = true;
        }
        assert isException;

        System.out.println("After remove");
        TransportUtils.printModelsNamesAndPrices(vehicle);
    }
}
