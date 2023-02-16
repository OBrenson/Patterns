package lab3;

import lab1.exceptions.DuplicateModelNameException;
import lab1.transport.Car;
import lab1.transport.Transport;
import lab3.chain.FewModelsPrinterHandler;
import lab3.chain.ManyModelsPrinterHandler;
import lab3.chain.TransportPrinterHandler;

public class Main {

    public static void main(String[] args) {
        testChain();
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
