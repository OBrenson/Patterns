package lab3.chain;

import lab1.transport.Transport;

import java.io.IOException;

public class FewModelsPrinterHandler extends ModelsPrinterHandler {


    @Override
    public void handlePrint(Transport transport) {
        if (transport.getModelsNum() <= 3) {
            try {
                printModels(transport, " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if(handler != null) {
                handler.handlePrint(transport);
            }
        }
    }

}
