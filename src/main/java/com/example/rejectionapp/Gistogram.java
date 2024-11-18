package com.example.rejectionapp;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static java.awt.Color.RED;

public class Gistogram extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int i = 2; i < 9; i++) {
            g2d.drawLine(50, 50 + 44 * i, 400, 50 + 44 * i);
            int vs = 80 - i * 10;
            g2d.drawString(vs + "", 30, 50 + 44 * i);
        }

        g2d.drawString("upread.ru", 100, 40);
        g2d.drawString("google.ru", 100, 60);
        g2d.drawString("yandex.ru", 100, 80);

        g2d.drawString("Январь", 60, 420);
        g2d.drawString("Февраль", 160, 420);
        g2d.drawString("Март", 260, 420);
        g2d.drawString("Апрель", 360, 420);

        g2d.setColor(Color.BLUE);
        g2d.fillRect(80, 30, 10, 10);
        g2d.setColor(RED);
        g2d.fillRect(80, 50, 10, 10);
        g2d.setColor(Color.GREEN);
        g2d.fillRect(80, 70, 10, 10);

        for (int i = 0; i < 4; i++) {
            Color color = RED;
            for (int j = 0; j < 3; j++) {
                try {
                    Field field = Class.forName("java.awt.Color").getField(GrGis.col[j].toLowerCase());
                    color = (Color) field.get(null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                g2d.setColor(color);
                int realY = (int) (400-44*GrGis.y[j][i]/10)+3;
                g2d.fillRect(50+20*j+100*i, realY, 20,(int) (GrGis.y[j][i]*4.4));
            }
        }
    }
}
