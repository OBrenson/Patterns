package lab3.chain;

import lab1.transport.Transport;
import lab2.adapter.StringOutputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class ModelsPrinterHandler implements TransportPrinterHandler {

    protected TransportPrinterHandler handler;

    private StringOutputStream streamAdapter;

    public ModelsPrinterHandler() {
        try {
            Path path = Files.createTempFile(Paths.get(System.getProperty("user.dir")), "test", "test");
            streamAdapter = new StringOutputStream(new FileOutputStream(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNext(TransportPrinterHandler handler) {
        this.handler = handler;
    }

    protected void printModels(Transport transport, String delimiter) throws IOException {
        String[] names = transport.getModelsNames();
        Double[] prices = transport.getModelsPrices();

        for (int i = 0; i < transport.getModelsNum(); i++) {
            streamAdapter.write(new String[]{names[i] + " "});
            streamAdapter.write(new String[]{prices[i] + delimiter});
        }
    }
}
