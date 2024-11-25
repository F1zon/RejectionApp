package com.example.rejectionapp.gistogreMethods;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

import static java.awt.Color.*;

public class GisPanel extends JPanel {
    private GraphValues values;
    private final Logger logger = Logger.getLogger(GisPanel.class.getName());

    public GisPanel(File file) {
        setSize(1600, 500);
        setBackground(WHITE);
//        setFont(this.getFont().deriveFont(16f));
        values = new GraphValues(file);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        List<Double> occurrences = values.getOccurrences();
        List<Integer> GraphrangeValues = values.getRangeValues().stream().distinct().sorted().toList();
        List<Integer> rangeValues = values.getRangeValues();
        Map<Integer, Integer> coordinatesRangeY = new HashMap<>();

        //горизонтальные линии и обозначения
        int horizontalLineY = 175;
        int verticalLineY = 35;
        int parametr = 0;
        int endY = 0;
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20f));
        for (int i = 0; i < GraphrangeValues.size(); i++) {
            g.drawLine(38, verticalLineY + horizontalLineY * i, 1550, verticalLineY + horizontalLineY * i);
            parametr = GraphrangeValues.get(GraphrangeValues.size() - 1 - i);

            for (Integer rangeValue : rangeValues) {
                if (rangeValue == parametr) {
                    coordinatesRangeY.put(parametr, verticalLineY + horizontalLineY * i);
                }
            }

            g.drawString(parametr + "", 20, verticalLineY + horizontalLineY * i);
            if (i == GraphrangeValues.size() - 2) {
                endY = verticalLineY + horizontalLineY * i;
            }
        }

        g.setFont(g.getFont().deriveFont(Font.ITALIC, 16f));
//      Промежутки
        int tmp = 35;
        for (Double occurrence : occurrences) {
            g.drawString(occurrence + "", tmp, 420);
            tmp += 48;
        }

//      Сам график
        int realY = 0;
        int value = 0;
        tmp = 38;
        int fillWidth = 48;
        for (Integer rangeValue : rangeValues) {
            g.setColor(BLUE);
            value = rangeValue;
            realY = coordinatesRangeY.get(rangeValue);

            if (value == 0) {
                tmp += fillWidth;
                continue;
            }
            if (value > 1) {
                g.fillRect(tmp, endY - horizontalLineY, 48, realY + 315);
                //Рисуем границы 1-го промежута
                g.setColor(BLACK);
                g.drawRect(tmp, endY - horizontalLineY, 48, realY + 315);
                tmp += fillWidth;
                continue;
            }

            g.fillRect(tmp, endY / value, 48, realY - 35);

//          Рисуем границы 1-го промежутка
            g.setColor(BLACK);
            g.drawRect(tmp, endY / value, 48, realY - 35);
            tmp += fillWidth;
        }
    }
}
