package com.example.rejectionapp.gistogreMethods;

import lombok.AllArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

@AllArgsConstructor
public class GrGis {
    private File fileArr;

    public JPanel createPanel() {
        int w = 600;
        int h = 300;
        JLabel label = new JLabel("This is a test");
        JButton button = new JButton("Click me");
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(button);
        panel.setSize(w, h);

        return panel;
    }

    public BufferedImage getImageGis(Component component) {

        JFrame frame = new JFrame();
        frame.setBackground(Color.DARK_GRAY);
        frame.setUndecorated(true);
        frame.getContentPane().add(component);
        frame.pack();
//        frame.setVisible(true);

        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.createGraphics();
        component.print(g);
        g.dispose();
        frame.dispose();

        return image;
    }  
}
