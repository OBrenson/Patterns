package lab1.transport;

import lab1.exceptions.DuplicateModelNameException;
import lab1.exceptions.ModelPriceOutOfBoundsException;
import lab1.exceptions.NoSuchModelNameException;
import lab3.command.PrintCommand;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class Car implements Transport, Serializable, Cloneable {

    public Car(String brand, int modelsLength) {
        this.brand = brand;
        this.models = new Model[modelsLength];
        IntStream.range(0, modelsLength).forEachOrdered(i -> models[i] = new Model());
    }

    private PrintCommand command;

    private String brand;

    private Model[] models;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        Optional<Model> nModel = findModelByName(newName);
        if (nModel.isPresent()) {
            throw new DuplicateModelNameException(newName);
        }
        Optional<Model> model = findModelByName(oldName);
        model.orElseThrow(() -> new NoSuchModelNameException(oldName)).name = newName;
    }

    public String[] getModelsNames() {
        return Arrays.stream(models).map(m -> m.name == null ? "" : m.name).toArray(String[]::new);
    }

    public double getModelPriceByName(String name) throws NoSuchModelNameException {
        Optional<Model> model = findModelByName(name);
        return model.orElseThrow(() -> new NoSuchModelNameException(name)).price;
    }

    public void setModelPriceByName(String name, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        Optional<Model> model = findModelByName(name);
        model.orElseThrow(() -> new NoSuchModelNameException(name)).price = price;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException();
        }
        if (Arrays.stream(this.models).anyMatch(m -> m.name != null && !m.name.equals("") && m.name.equals(name))) {
            throw new DuplicateModelNameException(name);
        }
        for (int i = 0; i < this.models.length; i++) {
            if (models[i].name == null) {
                models[i].name = name;
                models[i].price = price;
                return;
            }
        }
        Model[] models = Arrays.copyOf(this.models, this.models.length + 1);
        models[models.length - 1] = new Model(name, price);
        this.models = models;
    }

    public void deleteModel(String name) throws NoSuchModelNameException {
        OptionalInt modelIndex = IntStream.range(0, this.models.length)
                .filter(i -> {
                    if (this.models[i].name != null) {
                        return this.models[i].name.equals(name);
                    }
                    return false;
                })
                .findFirst();
        int delInd = modelIndex.orElseThrow(() -> new NoSuchModelNameException(name));
        Model[] models = new Model[this.models.length - 1];
        if (delInd != 0) {
            System.arraycopy(this.models, 0, models, 0, delInd);
        }
        if (delInd != this.models.length - 1) {
            System.arraycopy(this.models, delInd + 1, models, delInd, this.models.length - delInd - 1);
        }
        this.models = models;
    }

    public int getModelsNum() {
        return (int)Arrays.stream(this.models).filter(Objects::nonNull).count();
    }

    public Double[] getModelsPrices() {
        return Arrays.stream(models).map(m -> m.price).toArray(Double[]::new);
    }

    private Optional<Model> findModelByName(String name)  {
        return Arrays.stream(models).filter(m -> m.name != null).filter(m -> m.name.equals(name)).findFirst();
    }

    @Override
    public Car clone() {
        Car car = null;
        try {
            car = (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        for(Model model : this.models) {
            try {
                car.deleteModel(model.name);
                car.addModel(model.name, model.price);
            } catch (DuplicateModelNameException | NoSuchModelNameException e) {
                e.printStackTrace();
            }
        }
        return car;
    }

    public void print(OutputStream os) {
        if (command != null) {
            List<String> list = new ArrayList<>();
            list.add("Brand: " + brand);
            list.add("models: ");
            for (Model model : models) {
                list.add(String.format("Name: %s, Price: %s", model.name, model.price));
            }
            command.print(os, list.toArray(String[]::new));
        }
    }

    public void setPrintCommand(PrintCommand command) {
        this.command = command;
    }

    public Iterator<Model> iterator() {
        return new ModelsIterator(this);
    }

    private class ModelsIterator implements Iterator<Model> {

        private int index = -1;
        private final Car car;

        private ModelsIterator(Car car) {
            this.car = car;
        }

        @Override
        public boolean hasNext() {
            return index < models.length - 1;
        }

        @Override
        public Model next() {
            index ++;
            return car.models[index];
        }
    }

    public CarMemento createMemento() {
        CarMemento memento = new CarMemento();
        memento.setAuto(this);
        return memento;
    }

    public void setMemento(CarMemento memento) {
        this.models = memento.getAuto().models;
        this.brand = memento.getAuto().brand;
    }

    public static class CarMemento {

        private final ByteArrayOutputStream storage;

        public CarMemento() {
            storage = new ByteArrayOutputStream();
        }

        private void setAuto(Car car) {
            try (ObjectOutputStream oos = new ObjectOutputStream(storage)) {
                oos.writeObject(car);
                oos.flush();
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
        }

        private Car getAuto() {
            try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(storage.toByteArray()))){
                return (Car)ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Model implements Serializable {

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public Model() {
            price = 0.0;
        }

        private String name;

        private Double price;

        @Override
        public String toString() {
            return String.format("Name: %s, Price: %s", name, price);
        }
    }
}
