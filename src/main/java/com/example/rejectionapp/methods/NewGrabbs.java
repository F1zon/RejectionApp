package com.example.rejectionapp.methods;

import lombok.Getter;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class NewGrabbs extends Methods {

    public NewGrabbs(double[] arr) {
        this.arr = Arrays.stream(arr).sorted().toArray();
    }

    public void start() {
        double alpha = 0.05;
        double tmp = 0;
        do {
            grubbsTest(this.arr, alpha);
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
    private void grubbsTest(double[] data, double alpha) {
        calculateMean(data);
        calculateStandardDeviation(data, averageValue);
        int n = data.length;
        List<Integer> outlierIndexs = new ArrayList<Integer>();
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

        if (maxZ > calculateCriticalValue(n, alpha)) {
            this.arr = deleteValue(data, outlierIndexs);
            calculateMean(data);
            calculateStandardDeviation(data, averageValue);
            standardDeviationValue = stdDev.evaluate(arr);
            coefficientVariation = standardDeviationValue / ((Arrays.stream(arr).sum() / arr.length));
        }
        standardDeviationValue = stdDev.evaluate(arr);
    }

    // Вычисление критического значения для тестирования Граббса
    private double calculateCriticalValue(int n, double alpha) {
        // Критические значения могут быть получены из таблицы критических значений Граббса
        // Для упрощения можно использовать заранее известные значения или формулу
        // Можно использовать в качестве примера: Tn = (n - 1) / Math.sqrt(n) * Math.sqrt((1.96 * 1.96) / (n - 2 + 1.96 * 1.96));
        ValueGkr valueGkr = new ValueGkr();
        valueGkr.setGkr();

        return valueGkr.getValue(n); // Пример: используем фиксированное значение для двухсторонней проверки на уровне значимости 0.05
    }

    private double[] deleteValue(double[] arr, List<Integer> indexRemove) {
        List<Double> list = new ArrayList<>(Arrays.stream(arr).boxed().toList());
        List<Double> resultList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (!indexRemove.contains(i)) {
                resultList.add(list.get(i));
            }
        }

        double[] result = new double[resultList.size()];
//        for (double i : arr) if (i != value) list.add(i);
        for (int i = 0; i < resultList.size(); i++) result[i] = resultList.get(i);

        return result;
    }
}
