package lab4.dao;

import lab1.exceptions.DuplicateModelNameException;
import lab1.transport.Car;
import lab1.transport.Motorbike;
import lab1.transport.Transport;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class ReaderDAO implements TransportDAO{

    private final DataSource dataSource;

    public ReaderDAO() {
        dataSource = new DataSource();
    }

    @Override
    public Transport getTransport(Path path) {
        try {
            ByteArrayInputStream bis = dataSource.getRawData(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(bis));
            return create(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Transport create(BufferedReader br) throws IOException {
        boolean isCar = Boolean.parseBoolean(br.readLine());
        String brand = br.readLine();
        int num = Integer.parseInt(br.readLine());

        Transport transport;
        if(isCar) {
            transport = new Car(brand, num);
        } else {
            transport = new Motorbike(brand, num);
        }

        String modelName = br.readLine();
        while (modelName != null) {
            try {
                transport.addModel(modelName, Double.parseDouble(br.readLine()));
                modelName = br.readLine();
            } catch (DuplicateModelNameException e) {
                throw new RuntimeException(e);
            }
        }
        return transport;
    }
}
