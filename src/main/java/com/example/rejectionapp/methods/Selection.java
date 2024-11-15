package com.example.rejectionapp.methods;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Selection {
    private double[] arr;
    private double g1;
    private double g2;
    private double gkr;
    private double coefficientVariation;
    private double averageValue;
    private double standardDeviationValue;

    public String[] start(String method, double... arr) {
        this.arr = arr;

        switch (method) {
            case "grabs":
                Grabs();
                break;
            case "sigm":
                Sigm();
                break;
        }

        String[] result = new String[3];
        result[0] = getCoefficientVariation();
        result[1] = getAverageValue();
        result[2] = getStandardDeviationValue();

        return result;
    }

    public String getCoefficientVariation() {
        return String.valueOf(new DecimalFormat("#0.00").format(coefficientVariation));
    }

    public String getAverageValue() {
        return String.valueOf(new DecimalFormat("#0.00").format(averageValue));
    }

    public String getStandardDeviationValue() {
        return String.valueOf(new DecimalFormat("#0.00").format(standardDeviationValue));
    }

    public void Grabs() {
        output();
        coefficientVariation = selectionGrabs();
        output();
        if (coefficientVariation > 0.33 && (g1 > gkr && g2 > gkr)) {
            while (coefficientVariation > 0.33) {
                coefficientVariation = selectionGrabs();
            }
        }
    }

    public void Sigm() {
        output();
        coefficientVariation = selectionSigm();
        output();

        if (coefficientVariation > 0.33) {
            deleteValue(Arrays.stream(arr).min().getAsDouble());
        }
    }

    private double selectionGrabs() {
        int n = arr.length;

        double maxNumber = Arrays.stream(arr).max().getAsDouble();
        double minNumber = Arrays.stream(arr).min().getAsDouble();
        averageValue = Arrays.stream(arr).sum() / n;

        double standardDeviationValue = standardDeviation();
        g1 = Math.abs(minNumber - averageValue) / standardDeviationValue;
        g2 = Math.abs(maxNumber - averageValue) / standardDeviationValue;

        System.out.println("g1 = " + g1 + " | g2 = " + g2);

        ValueGkr valueGkr = new ValueGkr();
        valueGkr.setGkr();
        gkr = valueGkr.getValue(n);

        System.out.println("gkr = " + gkr);
        System.out.println("min = " + minNumber + " | max = " + maxNumber);

        if (g1 > gkr) deleteValue(minNumber);
        if (g2 > gkr) deleteValue(maxNumber);

        n = arr.length;
        standardDeviationValue = standardDeviation();
        System.out.println("standardDeviationValue: " + standardDeviationValue);
        return standardDeviationValue / (Arrays.stream(arr).sum() / n);
    }

    private double selectionSigm() {
        System.out.println();
        output();

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

        return standardDeviation() / ((Arrays.stream(arr).sum() / arr.length));
    }

    private double standardDeviation() {
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

    private void deleteValue(double value) {
        List<Double> list = new ArrayList<>();
        double[] result = new double[arr.length - 1];

        for (double i : arr) if (i != value) list.add(i);
        for (int i = 0; i < list.size(); i++) result[i] = list.get(i);

        arr = result;
    }

    private void output() {
        System.out.println();
        for (double i : arr) System.out.print(i + ", ");
    }
}
