package train.calculator;

import java.util.Arrays;

public class CalculationList {
    private final int maxMemorySize;
    private final double[] resultList;
    private int size = 0;

    public CalculationList(int maxMemorySize) {
        this.maxMemorySize = maxMemorySize;
        resultList = new double[maxMemorySize];
    }

    public double addition(double first, double second) {
        double result = first + second;
        insertResult(result);
        return result;
    }

    public double multiplication(double first, double second) {
        double result = first * second;
        insertResult(result);
        return result;
    }

    public double subtraction(double first, double second) {
        double result = first - second;
        insertResult(result);
        return result;
    }

    public double division(double first, double second) {
        if (!(second == 0)) {
            double result = first / second;
            insertResult(result);
            return result;
        } throw new RuntimeException("Divide by zero not impossible ");
    }

    private void insertResult(double result) {
        if (size < maxMemorySize) {
            resultList[size++] = result;
        } else {
            throw new RuntimeException("Out of memory");
        }
    }

    public int getSize() {
        return size;
    }

    public void printingResultList() {
        System.out.println(Arrays.toString(resultList));
    }

}
