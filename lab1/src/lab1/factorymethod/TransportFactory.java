package lab1.factorymethod;

import lab1.transport.Transport;

public abstract class TransportFactory {

    public abstract Transport createInstance(String name, int size);

}
