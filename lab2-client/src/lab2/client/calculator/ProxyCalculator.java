package lab2.client.calculator;

public class ProxyCalculator implements Calculator {

    private NetCalculator calculator;

    public ProxyCalculator() {
        calculator = new NetCalculator();
    }

    @Override
    public Double multiply(double val1, double val2) {
        return calculator.multiply(val1, val2);
    }
}
