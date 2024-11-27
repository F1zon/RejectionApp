package com.example.rejectionapp.methods;

import java.util.Arrays;

public class Grabs extends Methods {

    @Override
    public void start(double... arr) {
        this.arr = arr;
        coefficientVariation = selection();


        if (coefficientVariation > 0.33 && (g1 > gkr && g2 > gkr)) {
            while (coefficientVariation > 0.33) {
                coefficientVariation = selection();
            }
        }

        clearingArray();
    }

    private double selection() {
        int n = arr.length;

        double maxNumber = Arrays.stream(arr).max().getAsDouble();
        double minNumber = Arrays.stream(arr).min().getAsDouble();
        averageValue = Arrays.stream(arr).sum() / n;

        double standardDeviationValue = standardDeviation();
        g1 = Math.abs(minNumber - averageValue) / standardDeviationValue;
        g2 = Math.abs(maxNumber - averageValue) / standardDeviationValue;

        ValueGkr valueGkr = new ValueGkr();
        valueGkr.setGkr();
        gkr = valueGkr.getValue(n);

        if (g1 > gkr) deleteValue(minNumber);
        if (g2 > gkr) deleteValue(maxNumber);

        n = arr.length;
        standardDeviationValue = standardDeviation();
        return standardDeviationValue / (Arrays.stream(arr).sum() / n);
    }
}
