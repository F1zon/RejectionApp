package com.example.rejectionapp.methods;

import java.util.Arrays;

public class Standard extends Methods {
    public void start(double[] arr) {
        this.arr = Arrays.stream(arr).sorted().toArray();
        calculateMean(this.arr);
        calculateStandardDeviation(this.arr, this.averageValue);
        coefficientVariation = standardDeviationValue / ((Arrays.stream(arr).sum() / arr.length));
    }

    // Метод для вычисления среднеквадратического отклонения
    private void calculateStandardDeviation(double[] data, double mean) {
        double sum = 0.0;
        for (double value : data) {
            sum += Math.pow(value - mean, 2);
        }
        standardDeviationValue = Math.sqrt(sum / (data.length - 1));
    }

    // Метод для вычисления среднего значения
    private void calculateMean(double[] data) {
        double sum = 0.0;
        for (double value : data) {
            sum += value;
        }
        averageValue = sum / data.length;
    }
}
