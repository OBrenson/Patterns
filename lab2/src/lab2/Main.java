package lab2;

import lab1.exceptions.DuplicateModelNameException;
import lab1.transport.Car;
import lab1.transport.Transport;
import lab2.adapter.StringOutputStream;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws IOException {
//        checkAdapter();
        checkDecorator();
    }

    public static void checkDecorator() {
        Car car = new Car("test", 0);
        Transport st = ExtendedTransportUtils.synchronizedTransport(car);
        AtomicBoolean t1Ended = new AtomicBoolean(false);
        Thread t1 = new Thread(() -> {
            for(int i = 0; i< 1000; i++) {
                try {
                    st.addModel("thread1" + i, 100);
                } catch (DuplicateModelNameException e) {
                    e.printStackTrace();
                }
            }
            t1Ended.set(true);
        });

        AtomicBoolean t2Ended = new AtomicBoolean(false);
        Thread t2 = new Thread(() -> {
            for(int i = 0; i< 1000; i++) {
                try {
                    st.addModel("thread2" + i, 100);
                } catch (DuplicateModelNameException e) {
                    e.printStackTrace();
                }
            }
            t2Ended.set(true);
        });
        t1.start();
        t2.start();

        while (!t1Ended.get() || !t2Ended.get()) {

        }
        System.out.println("Количество моделей после параллельного добавления: " + st.getModelsNum());
    }

    public static void checkAdapter() throws IOException {
        StringOutputStream sos = new StringOutputStream(System.out);
        sos.write(new String[]{"ЗАПИСАЛ", "\n","СТРОЧКИ", "\n", "ЧЕРЕЗ АДАПТЕР"});
    }
}
