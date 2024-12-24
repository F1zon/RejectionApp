package com.example.rejectionapp.methods;

import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public class Selection {
    private double[] arr;
    private double g1;
    private double g2;
    private double gkr;
    private double coefficientVariation;
    private double averageValue;
    private double standardDeviationValue;

    public String[] start(String method, double[] arr, String rasp) {
        switch (method) {
            case "grabs":
                Grabbs grabs = new Grabbs();
                grabs.start(arr);
                setData(grabs);
                break;
            case "sigm":
                Sigm methodSigm = new Sigm();
                methodSigm.start(arr, rasp);
                setData(methodSigm);
                break;
        }

        String[] result = new String[3];
        result[0] = getCoefficientVariation();
        result[1] = getAverageValue();
        result[2] = getStandardDeviationValue();

        return result;
    }

    private String getStandardDeviationValue() {
        return new DecimalFormat("#0.00").format(standardDeviationValue);
    }

    public String getCoefficientVariation() {
        return String.valueOf(new DecimalFormat("#0.00").format(coefficientVariation));
    }

    public String getAverageValue() {
        return String.valueOf(new DecimalFormat("#0.00").format(averageValue));
    }

    private void setData(Methods methods) {
        this.arr = methods.getArr();
        this.coefficientVariation = methods.getCoefficientVariation();
        this.averageValue = methods.getAverageValue();
        this.standardDeviationValue = methods.getStandardDeviationValue();
    }

}
