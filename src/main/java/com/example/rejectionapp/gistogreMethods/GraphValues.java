package com.example.rejectionapp.gistogreMethods;

import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Getter
public class GraphValues {
    // Массив данных
    private double[] arr;
    // Массив диапазонов
    private List<Integer> rangeValues;
    // Массив вхождений
    private List<Double> occurrences;

    public GraphValues(File file) {
        createData(file);
    }

    private void createData(File fileArr) {
        // Получить массив данных
        List<String> tmp = new ArrayList<>();

        try (Scanner scanner = new Scanner(fileArr)) {
            while (scanner.hasNextLine()) {
                tmp.add(scanner.nextLine());
            }
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

        arr = new double[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            arr[i] = Double.parseDouble(tmp.get(i));
        }

        // Сгенерировать интервалы
        int gap = (int) Math.ceil(Math.sqrt(arr.length)); // Промежуток между интервалами

        double minValue = Arrays.stream(arr).min().getAsDouble(); // Минимальное и максимальное число
        double maxValue = Arrays.stream(arr).max().getAsDouble();

        occurrences = new ArrayList<>();
        occurrences.add(minValue);
        double tmpSum = minValue;
        for (double i = minValue; i < maxValue; i++) {
            occurrences.add(tmpSum + gap);
            tmpSum += gap;
        }

        // Сгенерировать места вхождения
        rangeValues = new ArrayList<>();
        int numberOccurrences = 0;

        for (int i = 1; i < occurrences.size(); i++) {
            for (double v : arr) {
                if (v >= occurrences.get(i - 1) && v <= occurrences.get(i)) {
                    numberOccurrences++;
                }
            }
            rangeValues.add(numberOccurrences);
            numberOccurrences = 0;
        }
    }
}
