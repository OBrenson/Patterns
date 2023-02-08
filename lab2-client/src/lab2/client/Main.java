package lab2.client;

import lab2.client.calculator.Calculator;
import lab2.client.calculator.ProxyCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Calculator calc = new ProxyCalculator();
        while(true) {
            System.out.println("Write val1:");
            String val1 = br.readLine();
            System.out.println("Write val2:");
            String val2 = br.readLine();
            Double res = calc.multiply(Double.parseDouble(val1), Double.parseDouble(val2));
            if (res != null) {
                System.out.println("Result: " + res);
            } else {
                System.out.println("Something is wrong.");
            }
        }
    }
}
