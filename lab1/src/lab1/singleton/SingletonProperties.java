package lab1.singleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Properties;

public class SingletonProperties {

    private static SingletonProperties uniqueInstance;

    private final Properties properties;

    private static final String sep = FileSystems.getDefault().getSeparator();
    private static final String CONFIG_PATH = String.format("lab1%sresources%sconfig.properties", sep, sep);
    private SingletonProperties() {
        properties = new Properties();
        try(FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Can not create properties. Reason: " + e.getMessage());
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public static synchronized SingletonProperties getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SingletonProperties();
        }
        return uniqueInstance;
    }
}
