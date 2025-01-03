module com.example.rejectionapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.logging;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.apache.commons.io;
    requires commons.math3;
    requires java.desktop;
    requires static lombok;
    requires jfreechart;
    requires jcommon;
    requires javafx.swing;
    requires annotations;

    opens com.example.rejectionapp to javafx.fxml;
    exports com.example.rejectionapp;
    exports com.example.rejectionapp.controllers;
    opens com.example.rejectionapp.controllers to javafx.fxml;
    exports com.example.rejectionapp.gistogreMethods;
    opens com.example.rejectionapp.gistogreMethods to javafx.fxml;
    exports com.example.rejectionapp.methods;
    opens com.example.rejectionapp.methods to javafx.fxml;
}