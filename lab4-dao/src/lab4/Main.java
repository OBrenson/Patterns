package lab4;

import lab1.exceptions.DuplicateModelNameException;
import lab1.transport.Car;
import lab1.transport.Transport;
import lab1.transport.TransportUtils;
import lab4.dao.DeserializerDAO;
import lab4.dao.ReaderDAO;
import lab4.dao.TransportDAO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private final static String SER_FILE = "/Users/19722883-mobile/Desktop/labs/Patterns/lab4-dao/serial";
    private final static String STR_FILE = "/Users/19722883-mobile/Desktop/labs/Patterns/lab4-dao/data.txt";

    public static void main(String[] args) throws IOException {
        createFiles();

        TransportDAO serDao = new DeserializerDAO();
        Transport car = serDao.getTransport(Paths.get(SER_FILE));
        System.out.println("SER");
        System.out.println(car.getClass().getName());
        System.out.println(car.getBrand());
        TransportUtils.printModelsNamesAndPrices(car);

        TransportDAO strDao = new ReaderDAO();
        Transport bike = strDao.getTransport(Paths.get(STR_FILE));
        System.out.println("STR");
        System.out.println(bike.getClass().getName());
        System.out.println(bike.getBrand());
        TransportUtils.printModelsNamesAndPrices(bike);
    }

    private static void createFiles() throws IOException {
        Transport car = new Car("brand", 3);
        addModels(car, 3);
        Path path;
        if (Files.exists(Paths.get(SER_FILE))) {
            path = Paths.get(SER_FILE);
        } else {
            path = Files.createFile(Paths.get(SER_FILE));
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()));
        oos.writeObject(car);
    }

    private static void addModels(Transport transport, int num) {
        for(int i = 0; i < num; i++) {
            try {
                transport.addModel("car" + i, 100 + i);
            } catch (DuplicateModelNameException ignored) {

            }
        }
    }
}
