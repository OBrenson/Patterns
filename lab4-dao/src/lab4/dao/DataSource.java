package lab4.dao;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class DataSource {

    public ByteArrayInputStream getRawData(Path path) throws IOException {
        FileInputStream fis = new FileInputStream(path.toFile());
        return new ByteArrayInputStream(fis.readAllBytes());
    }

}
