package com.example.rejectionapp.gistogreMethods;

import lombok.AllArgsConstructor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;

import static java.awt.Color.RED;

public class CreateGistogramFrame {
    private File fileArr;

    public CreateGistogramFrame(File fileArr) {
        this.fileArr = fileArr;
        GraphValues graphValues = new GraphValues(fileArr);
    }

    public CategoryDataset createGistogramDataSet() {
        GraphValues gv = new GraphValues(fileArr);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(123, "Первый", "Стлобец");
        return dataset;
    }



    public JFreeChart createGistogram(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Тестовый график",
                null,
                "Тестовое значение",
                dataset
        );

        chart.addSubtitle(new TextTitle("Тестовый доход"));
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }
}
