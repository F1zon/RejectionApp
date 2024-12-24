package com.example.rejectionapp.methods;

import lombok.Getter;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Grabbs extends Methods {

    public void start(double[] arr) {
        this.arr = Arrays.stream(arr).sorted().toArray();
        double tmp = 0;
        do {
            grubbsTest(this.arr);
            if (tmp == coefficientVariation) break;
            tmp = coefficientVariation;
        } while (coefficientVariation > 0.33);
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

    // Метод Граббса для выявления выбросов
    private void grubbsTest(double[] data) {
        calculateMean(data);
        calculateStandardDeviation(data, averageValue);
        int n = data.length;
        List<Integer> outlierIndexs = new ArrayList<>();
        double maxZ = 0.0;
        int outlierIndex = -1;
        double z = 0;
        StandardDeviation stdDev = new StandardDeviation();
        standardDeviationValue = stdDev.evaluate(data);

        for (int i = 0; i < n; i++) {
            z = Math.abs(data[i] - averageValue) / standardDeviationValue;
            if (z > maxZ) {
                maxZ = z;
                outlierIndex = i;
                outlierIndexs.add(outlierIndex);
            }
        }

        if (maxZ > calculateCriticalValue(n)) {
            this.arr = deleteValue(data, outlierIndexs);
            calculateMean(data);
            calculateStandardDeviation(data, averageValue);
            standardDeviationValue = stdDev.evaluate(arr);
            coefficientVariation = standardDeviationValue / ((Arrays.stream(arr).sum() / arr.length));
        }
        standardDeviationValue = stdDev.evaluate(arr);
    }

    // Вычисление критического значения для тестирования Граббса
    private double calculateCriticalValue(int n) {
        ValueGkr valueGkr = new ValueGkr();
        valueGkr.setGkr();

        return valueGkr.getValue(n);
    }

    @NotNull
    private double[] deleteValue(double[] arr, List<Integer> indexRemove) {
        List<Double> list = new ArrayList<>(Arrays.stream(arr).boxed().toList());
        List<Double> resultList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (!indexRemove.contains(i)) {
                resultList.add(list.get(i));
            }
        }

        double[] result = new double[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) result[i] = resultList.get(i);

        return result;
    }
}
