package com.example.rejectionapp.methods;

import java.util.Arrays;

public class Sigm extends Methods{
    @Override
    public void start(double... arr) {
        this.arr = arr;
        coefficientVariation = selection();

        if (coefficientVariation > 0.33) {
            deleteValue(Arrays.stream(arr).min().getAsDouble());
        }

        clearingArray();
    }

    private double selection() {
        double maxNumber = Arrays.stream(arr).max().getAsDouble();

        deleteValue(maxNumber);
        int n = arr.length;

        averageValue = Arrays.stream(arr).sum() / n;
        standardDeviationValue = standardDeviation();

        double inter1 = Math.abs(averageValue - 3 * standardDeviationValue);
        double inter2 = Math.abs(averageValue + 3 * standardDeviationValue);

        for (double i : arr) {
            if (i > inter2 || i < inter1) deleteValue(i);
        }

        clearingArray();
        return standardDeviation() / ((Arrays.stream(arr).sum() / arr.length));
    }
}
