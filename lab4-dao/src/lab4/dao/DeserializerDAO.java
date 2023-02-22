package lab4.dao;

import lab1.transport.Transport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;

public class DeserializerDAO implements TransportDAO {

    private final DataSource dataSource;

    public DeserializerDAO() {
        dataSource = new DataSource();
    }

    @Override
    public Transport getTransport(Path path) {
        try {
            ByteArrayInputStream bis = dataSource.getRawData(path);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Transport) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
