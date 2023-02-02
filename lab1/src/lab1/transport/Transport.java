package lab1.transport;

import lab1.exceptions.DuplicateModelNameException;
import lab1.exceptions.NoSuchModelNameException;

public interface Transport {

    String s = "";

    void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;

    String[] getModelsNames();

    double getModelPriceByName(String name) throws NoSuchModelNameException;

    void setModelPriceByName(String name, double price) throws NoSuchModelNameException;

    void addModel(String name, double price) throws DuplicateModelNameException;

    void deleteModel(String name) throws NoSuchModelNameException ;

    int getModelsNum();

    Double[] getModelsPrices();

    void setBrand(String brand);

    String getBrand();

    Transport clone();
}
