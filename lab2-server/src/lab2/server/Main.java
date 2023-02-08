package lab2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5000);
        while (true) {
            Socket s = ss.accept();
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            double val1 = dis.readDouble();
            double val2 = dis.readDouble();
            double res = val1 * val2;
            System.out.println("Val1: " + val1 + " Val2: " + val2 + " Result: " + res);
            dos.writeDouble(res);
        }
    }

}
