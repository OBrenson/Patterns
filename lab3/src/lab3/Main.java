package lab3;

import lab1.exceptions.DuplicateModelNameException;
import lab1.transport.Car;
import lab1.transport.Transport;
import lab3.chain.FewModelsPrinterHandler;
import lab3.chain.ManyModelsPrinterHandler;
import lab3.chain.TransportPrinterHandler;
import lab3.strategy.CounterOccurrencesNumber;
import lab3.strategy.HashMapOccurrencesNumber;
import lab3.strategy.OccurrencesNumber;

import java.io.*;
import java.nio.file.*;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        testChain();
        testStrategy(args[0]);
    }

    private static void testStrategy(String path) throws IOException, ClassNotFoundException {
        createFile(path);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        int[] arr = (int[])ois.readObject();
        OccurrencesNumber on = new CounterOccurrencesNumber();
        Map<Integer, Integer> map = on.calculate(arr);
        System.out.println("COUNTER");
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        on = new HashMapOccurrencesNumber();
        map = on.calculate(arr);
        System.out.println("HASH MAP");
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    private static void createFile(String path) throws IOException {
        Path file = null;
        try {
            file = Files.createFile(Paths.get(path));
        } catch (FileAlreadyExistsException ignored) {
            file = Paths.get(path);
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()));
        oos.writeObject(new int[]{1, 23, -10, 20, 23, 89, 38, 38, 89, 100, 4, 4, -1, 0, 89, 25});
    }

    private static void testChain() {
        Transport transport = new Car("brand", 3);
        addModels(transport, 3);
        TransportPrinterHandler fewModelsHandler = new FewModelsPrinterHandler();
        fewModelsHandler.setNext(new ManyModelsPrinterHandler());
        fewModelsHandler.handlePrint(transport);
        addModels(transport, 10);
        fewModelsHandler.handlePrint(transport);
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
