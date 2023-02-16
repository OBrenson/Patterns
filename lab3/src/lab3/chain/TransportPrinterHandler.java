package lab3.chain;

import lab1.transport.Transport;

public interface TransportPrinterHandler {

    void handlePrint(Transport transport);

    void setNext(TransportPrinterHandler handler);

}
