package com.example.rejectionapp.methods;

import java.util.Arrays;
import java.util.logging.Logger;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;;


public class Grabs extends Methods {
    private final Logger logger = Logger.getLogger(Grabs.class.getName());

    @Override
    public void start(double... arr) {
        this.arr = arr;
        double tmp = 0;

        do {
            coefficientVariation = selectionTest();
            if (coefficientVariation == tmp) break;
            tmp = coefficientVariation;
        } while (coefficientVariation > 0.33);
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

        clearingArray();
        n = arr.length;
        averageValue = Arrays.stream(arr).sum() / n;
        standardDeviationValue = standardDeviation();
        return standardDeviationValue / (Arrays.stream(arr).sum() / n);
    }

    private double selectionTest() {
        int n = arr.length;
        // п1 Выстраиваем все элементы выборки по возрастанию (от меньшего к большему)
        this.arr = Arrays.stream(this.arr).sorted().toArray();

        double maxNumber = Arrays.stream(arr).max().getAsDouble();
        double minNumber = Arrays.stream(arr).min().getAsDouble();

        averageValue = Arrays.stream(arr).sum() / n;

        StandardDeviation standardDeviation = new StandardDeviation();
        standardDeviationValue = standardDeviation.evaluate(this.arr);

        g1 = Math.abs(averageValue - minNumber) / standardDeviationValue;
        g2 = Math.abs(averageValue - maxNumber) / standardDeviationValue;

        ValueGkr valueGkr = new ValueGkr();
        valueGkr.setGkr();
        gkr = valueGkr.getValue(n);

        if (g1 > gkr) deleteValue(minNumber);
        if (g2 > gkr) deleteValue(maxNumber);
        clearingArray();
        standardDeviationValue = standardDeviation.evaluate(this.arr);

        return standardDeviationValue;
    }
}
