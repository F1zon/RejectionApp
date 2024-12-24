package com.example.rejectionapp.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.distribution.LogNormalDistribution;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

public class Sigm extends Methods{
    @Override
    public void start(double[] arr , String rasp) {
        this.arr = Arrays.stream(arr).sorted().toArray();
        coefficientVariation = selection(rasp);

        if (coefficientVariation > 0.33) {
            deleteValue(Arrays.stream(arr).min().getAsDouble());
        }

        clearingArray();

    }

    private double selection(String rasp) {
        double maxNumber = Arrays.stream(arr).max().getAsDouble();

        deleteValue(maxNumber);
        clearingArray();
        int n = arr.length;

        generateData(rasp);

        double inter1 = Math.abs(averageValue - 3 * standardDeviationValue);
        double inter2 = Math.abs(averageValue + 3 * standardDeviationValue);

        for (double i : arr) {
            if (i > inter2 || i < inter1) deleteValue(i);
        }

        clearingArray();
        generateData(rasp);
        return standardDeviation() / ((Arrays.stream(arr).sum() / arr.length));
    }

    private void generateData(String rasp) {
        if (rasp.equals("normal")) {
            averageValue = Arrays.stream(arr).sum() / arr.length;
            standardDeviationValue = standardDeviation();
        }
        if (rasp.equals("lognormal")) {
            // Среднее значение
            double[] arrLn = Arrays.stream(arr).map(Math ::log).toArray();
            double averageValueLn = Arrays.stream(arrLn).sum() / arrLn.length;
            double expAverageValue = Math.exp(averageValueLn);
            StandardDeviation stdDev = new StandardDeviation();
            Variance variance = new Variance();
            double dispersion = Math.pow(variance.evaluate(arrLn), 2);
            averageValue = expAverageValue + dispersion / 2;

            // Стандартное отклонение
            standardDeviationValue = Math.sqrt(Math.exp((2 * averageValueLn)) *
                    (Math.exp(2 * dispersion) -
                            Math.exp(dispersion)));
        }
    }
}
