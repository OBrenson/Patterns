package lab2;

import lab1.transport.Transport;
import lab1.transport.TransportUtils;
import lab2.decorator.SynchronizedTransport;

public class ExtendedTransportUtils extends TransportUtils {

    public static Transport synchronizedTransport(Transport transport) {
        return new SynchronizedTransport(transport);
    }
}
