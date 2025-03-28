package com.example.rejectionapp;

public class SortedArr {
    public double[] sort(double[] arr) {
        double tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
                i = 0;
            }
        }

        return arr;
    }
}
