package com.example.rejectionapp.gistogreMethods;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GisFrame extends JFrame {
    public static double y[][] = new double[3][4];
    public static String col[] = {"BLUE", "RED", "GREEN"};//массив цветов
    @Getter
    private BufferedImage image;

    public GisFrame() {
        super("GisFrame");
        y[0][0]=5;
        y[0][1]=10;
        y[0][2]=12;
        y[0][3]=8;

        y[1][0]=24;
        y[1][1]=44;
        y[1][2]=18;
        y[1][3]=28;

        y[2][0]=20;
        y[2][1]=20;
        y[2][2]=30;
        y[2][3]=50;

//        JPanel jcp = new JPanel(new BorderLayout());
//        setContentPane(jcp);
//        jcp.add(new GisPanel(), BorderLayout.CENTER);
//        jcp.setBackground(Color.gray);
//        setSize(500, 500);
//        setLocationRelativeTo(null);
//        setUndecorated(true);
//        pack();

        GisPanel panel = new GisPanel();

        setBackground(Color.WHITE);
        setUndecorated(true);
        getContentPane().add(new GisPanel());
        pack();

        image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        panel.print(graphics2D);
        graphics2D.dispose();
        dispose();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
    }
}
