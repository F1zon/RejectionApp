package com.example.rejectionapp.gistogreMethods;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@Getter
public class GisFrame extends JFrame {
    private final BufferedImage image;

    public GisFrame(File file) {
        super("GisFrame");
        GisPanel panel = new GisPanel(file);

        setBackground(Color.WHITE);
        setUndecorated(true);
        getContentPane().add(new GisPanel(file));
        pack();

        image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics();
        panel.print(graphics2D);
        graphics2D.dispose();
        dispose();
    }
}
