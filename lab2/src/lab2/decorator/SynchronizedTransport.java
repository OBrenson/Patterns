package lab2.decorator;

import lab1.exceptions.DuplicateModelNameException;
import lab1.exceptions.NoSuchModelNameException;
import lab1.transport.Transport;

public class SynchronizedTransport implements Transport {

    private volatile Transport transport;

    public SynchronizedTransport(Transport transport) {
        this.transport = transport;
    }

    @Override
    public synchronized void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setModelName(oldName, newName);
    }

    @Override
    public synchronized String[] getModelsNames() {
        return transport.getModelsNames();
    }

    @Override
    public synchronized double getModelPriceByName(String name) throws NoSuchModelNameException {
        return transport.getModelPriceByName(name);
    }

    @Override
    public synchronized void setModelPriceByName(String name, double price) throws NoSuchModelNameException {
        transport.setModelPriceByName(name, price);
    }

    @Override
    public synchronized void addModel(String name, double price) throws DuplicateModelNameException {
        transport.addModel(name, price);
    }

    @Override
    public synchronized void deleteModel(String name) throws NoSuchModelNameException {
        transport.deleteModel(name);
    }

    @Override
    public synchronized int getModelsNum() {
        return transport.getModelsNum();
    }

    @Override
    public synchronized Double[] getModelsPrices() {
        return transport.getModelsPrices();
    }

    @Override
    public synchronized void setBrand(String brand) {
        transport.setBrand(brand);
    }

    @Override
    public synchronized String getBrand() {
        return transport.getBrand();
    }

    @Override
    public synchronized Transport clone() {
        return transport.clone();
    }
}
