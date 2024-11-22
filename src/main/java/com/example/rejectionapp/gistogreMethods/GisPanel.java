package com.example.rejectionapp.gistogreMethods;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

import static java.awt.Color.RED;

public class GisPanel extends JPanel {

    public GisPanel() {
        setSize(500, 500);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //горизонтальные линии и обозначения
        for (int i=2; i<9; i++) {
            g.drawLine(50, 50+44*i, 400, 50+44*i);
            int vs = 80 - i*10;
            g.drawString(vs+"", 30, 50+44*i);
        }

        g.drawString("upread.ru", 100, 40);
        g.drawString("google.ru", 100, 60);
        g.drawString("yandex.ru", 100, 80);

        g.drawString("Январь", 60, 420);
        g.drawString("Февраль", 160, 420);
        g.drawString("Март", 260, 420);
        g.drawString("Апрель", 360, 420);

        g.setColor(Color.BLUE);
        g.fillRect(80, 30, 10, 10);
        g.setColor(RED);
        g.fillRect(80, 50, 10, 10);
        g.setColor(Color.GREEN);
        g.fillRect(80, 70, 10, 10);

        for (int i=0; i<4; i++) {
            //строим саму гистограмму
            //извлекаем цвет для каждого графика
            Color color = RED;
            for (int j=0;j<3;j++) {
                try {
                    Field field = Class.forName("java.awt.Color").getField(GisFrame.col[j].toLowerCase());
                    color = (Color)field.get(null);
                } catch (Exception e) {}
                g.setColor(color);
                //переводим полученные данные в реальные координаты
                int realY = (int) (400-44*GisFrame.y[j][i]/10)+3;
                g.fillRect(50+20*j+100*i, realY, 20,(int) (GisFrame.y[j][i]*4.4));
            }
        }
    }
}
