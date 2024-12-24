package com.example.rejectionapp.methods;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
public abstract class Methods {
    double[] arr;
    protected double g1;
    protected double g2;
    protected double gkr;
    protected double coefficientVariation;
    protected double averageValue;
    protected double standardDeviationValue;

    protected void start(double[] arr, String rasp) {
        this.arr = arr;
    }

    protected double standardDeviation() {
        double sum = Arrays.stream(arr).sum();
        double standardDeviation = 0.0;
        double mean = 0.0;
        double res = 0.0;
        double sq = 0.0;
        int n = arr.length;

        mean = sum / (n);

        for (double v : arr) {
            standardDeviation
                    = standardDeviation + Math.pow((v - mean), 2);
        }

        sq = standardDeviation / (n - 1);
        res = Math.sqrt(sq);
        return res;
    }

    protected void deleteValue(double value) {
        List<Double> list = new ArrayList<>();
        double[] result = new double[arr.length];

        for (double i : arr) if (i != value) list.add(i);
        for (int i = 0; i < list.size(); i++) result[i] = list.get(i);

        arr = result;
    }

    protected void clearingArray() {
        this.arr = Arrays.stream(this.arr).filter(i -> i != 0).toArray();
    }
}
