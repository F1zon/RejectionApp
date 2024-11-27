package com.example.rejectionapp.gistogreMethods;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@Getter
public class GraphValues {
    // Список диапазонов
    private List<Integer> lineY;
    // Отсортированный список дипозонов
    private List<Integer> sortedLineY;
    // Список вхождений
    private List<Double> lineX;
    private final Logger logger = Logger.getLogger(GraphValues.class.getName());

    public GraphValues(double... arr) {
        createData(arr);
    }

    private void createData(double... arr) {
        // Сгенерировать интервалы
        int numberIntervals = (int) Math.sqrt(arr.length) + 1; // Кол-во интервалов

        double minValue = Arrays.stream(arr).min().getAsDouble(); // Минимальное и максимальное число
        double maxValue = Arrays.stream(arr).max().getAsDouble();

        double step = (maxValue - minValue) / numberIntervals; // Шаг разбиения
        step = Math.round(step * 10) / 10.0;
//        logger.info("minValue: " + minValue);
//        logger.info("maxValue: " + maxValue);
//        logger.info("step: " + step);
//        logger.info("numberIntervals: " + numberIntervals);

        createDataLineX(minValue, maxValue, step, arr);
//        occurrences = new ArrayList<>();
//        occurrences.add(minValue);
//        double tmpSum = minValue;
//        for (double i = minValue; i < maxValue; i += step) {
//            occurrences.add(tmpSum + step);
//            if (tmpSum + step * 2 <= maxValue) {
//                tmpSum += step;
//            } else {
//                continue;
//            }
//        }

        // Сгенерировать места вхождения
//        rangeValues = new ArrayList<>();
//        int numberOccurrences = 0;
//
//        for (int i = 1; i < occurrences.size(); i++) {
//            for (double v : arr) {
//                if (v >= occurrences.get(i - 1) && v <= occurrences.get(i)) {
//                    numberOccurrences++;
//                }
//            }
//            rangeValues.add(numberOccurrences);
//            numberOccurrences = 0;
//        }
    }

//  График по Y
    private void createDataLineY(double[] arr) {
        int coll = 0;
        lineY = new ArrayList<>();
        int end = lineX.size() - 1;

        // Интервалы
        for (int  x1 = 1; x1 <= end - 1; x1++) {
            for (double num : arr) {
                if (num >= lineX.get(x1) && num <= lineX.get(x1 + 1)) {
                    coll++;
                }
            }

            lineY.add(coll);
            coll = 0;
        }

        sortedDataLineY();
    }

    // Сортровка графика по Y
    private void sortedDataLineY() {
        lineY.add(0);
        sortedLineY = lineY.stream().mapToInt(i -> i).sorted().boxed().toList();
        lineY.removeLast();

        logger.info("lineY: " + Arrays.toString(lineY.toArray()));
        logger.info("lineX: " + Arrays.toString(lineX.toArray()));
    }

//  График по X
    private void createDataLineX(double minValue, double maxValue, double step, double[] arr) {
        // Добавляем все значения от min до max с шагом step
        lineX = new ArrayList<>();
        for (double i = minValue; i < maxValue; i += step) {
            lineX.add(i);
        }
        if (!lineX.contains(maxValue)) lineX.add(maxValue);

        createDataLineY(arr);
    }
}
