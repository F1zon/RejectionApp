package com.example.rejectionapp.gistogreMethods;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@Getter
public class GraphValues {
    // Список диапазонов
    private List<Integer> rangeValues;
    // Список вхождений
    private List<Double> occurrences;
    private final Logger logger = Logger.getLogger(GraphValues.class.getName());

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

        double[] arr = new double[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            arr[i] = Double.parseDouble(tmp.get(i));
        }

        // Сгенерировать интервалы
        int gap = (int) Math.ceil(Math.sqrt(arr.length)); // Промежуток между интервалами

        double minValue = Arrays.stream(arr).min().getAsDouble(); // Минимальное и максимальное число
        double maxValue = Arrays.stream(arr).max().getAsDouble();
        logger.info("maxValue: " + maxValue);

        occurrences = new ArrayList<>();
        occurrences.add(minValue);
        double tmpSum = minValue;
        for (double i = minValue; i < maxValue; i += gap) {
            occurrences.add(tmpSum + gap);
            if (tmpSum + gap * 2 <= maxValue) {
                tmpSum += gap;
            } else {
                continue;
            }
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
