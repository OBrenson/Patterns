package lab4.dao;

import lab1.transport.Transport;

import java.nio.file.Path;

public interface TransportDAO {

    Transport getTransport(Path path);

}
