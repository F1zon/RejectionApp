package com.example.rejectionapp.gistogreMethods;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

import static java.awt.Color.*;

public class GisPanel extends JPanel {
    private GraphValues values;
    private final Logger logger = Logger.getLogger(GisPanel.class.getName());
    int MaxLengthX = 800;
    int MaxLengthY = 1600;

    public GisPanel(double... arr) {
        setSize(1000, 800);
        setBackground(WHITE);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        values = new GraphValues(arr);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        List<Double> lineX = values.getLineX();
        List<Integer> lineY = values.getLineY();
        List<Integer> sortLineY = values.getSortedLineY();

        Map<Integer, Integer> coordinatesRangeY = new HashMap<>();

        // Разметка по Y #######################################################################
        int horizontalLineY = 175;
        int verticalLineY = 35;
        int parametr = 0;
        int endY = 0;
        g.setFont(g.getFont().deriveFont(Font.BOLD, 30f));

        for (int i = 0; i < sortLineY.size(); i++) {
            g.drawLine(38, verticalLineY + horizontalLineY * i, 1550, verticalLineY + horizontalLineY * i);
            parametr = sortLineY.get(sortLineY.size() - 1 - i);
            coordinatesRangeY.put(parametr, verticalLineY + horizontalLineY * i);
            logger.info("parameter: " + parametr);
            logger.info("coordinatesY: " + verticalLineY + horizontalLineY * i);

            g.drawString(sortLineY.get(sortLineY.size() - 1 - i) + "", 20, verticalLineY + horizontalLineY * i);
            if (i == sortLineY.size() - 1) {
                endY = verticalLineY + horizontalLineY * i;
            }
        }

        // Рзаметка по X ##############################################################################################
        g.setFont(g.getFont().deriveFont(Font.ITALIC, 26f));
        int tmp = 70;
        int step = (MaxLengthX / lineX.size());
        for (Double x : lineX) {
            g.drawString(x + "", tmp, endY + 35);
            tmp += step;
        }
        // ############################################################################################################

//      Сам график
        int realY = 0;
        int value = 0;
        tmp = 72;
        for (int x = 0; x < lineY.size(); x++) {
            g.setColor(DARK_GRAY);
            // Значение вхождения
            value = lineY.get(x);
            // Координа Y для текущего вхождения
            realY = coordinatesRangeY.get(value);

            g.fillRect(tmp, realY, step, endY - realY);

//          Рисуем границы 1-го промежутка
            g.setColor(BLACK);
            g.drawRect(tmp, realY, step, endY - realY);
            tmp += step;
        }
    }
}
