package lab2.client.calculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetCalculator implements Calculator{

    NetCalculator() {
    }

    @Override
    public Double multiply(double val1, double val2) {
        Double result = null;
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeDouble(val1);
            dataOutputStream.writeDouble(val2);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            result = dataInputStream.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
