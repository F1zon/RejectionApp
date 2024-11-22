package com.example.rejectionapp.gistogreMethods;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.image.BufferedImage;

@AllArgsConstructor
public class ConverFrameToImage {
    private GisFrame frame;

//    private static final  JPanel createPanel() {
//        int w = 600;
//        int h = 300;
//        JLabel label = new JLabel("This is a test");
//        JButton button = new JButton("Click me");
//        JPanel panel = new JPanel();
//        panel.add(label);
//        panel.add(button);
//        panel.setSize(w, h);
//
//        return panel;
//    }

    public BufferedImage getImage() {
        frame.setUndecorated(true);
        frame.pack();

        BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        g.dispose();
        frame.dispose();

        return image;
    }  
}
