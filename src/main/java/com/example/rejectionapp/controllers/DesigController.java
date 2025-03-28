package com.example.rejectionapp.controllers;

import com.example.rejectionapp.ExelWork;
import com.example.rejectionapp.HelloApplication;
import com.example.rejectionapp.gistogreMethods.GisFrame;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class DesigController {

    @FXML
    private CheckBox Metod3Sigm;

    @FXML
    private CheckBox MetodGrabbsa;

    @FXML
    private CheckBox VidLogonormalnoe;

    @FXML
    private CheckBox VidNormal;

    @FXML
    private Text fileName;

    @FXML
    private ImageView testGraph;

    private File file;

    @FXML
    private void isActiveSigm() {
        if (Metod3Sigm.isSelected()) {
            MetodGrabbsa.setSelected(false);
        }
    }

    @FXML
    private void isActiveGrabs() {
        if (MetodGrabbsa.isSelected()) {
            Metod3Sigm.setSelected(false);
        }
    }

    @FXML
    private void isActiveNormal() {
        if (VidNormal.isSelected()) {
            VidLogonormalnoe.setSelected(false);
        }
    }

    @FXML
    private void isActiveLog() {
        if (VidLogonormalnoe.isSelected()) {
            VidNormal.setSelected(false);
        }
    }

    @FXML
    private void activeButtonOnload() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            System.out.println("File selected: " + file);
            fileName.setText(file.getName());
        }
    }

    @FXML
    private void testData() {
        getDataInFile();
        File fileArr = new File("arr.txt");
        List<String> list = new ArrayList<>();

        try (Scanner sc = new Scanner(fileArr)) {
            while (sc.hasNextLine()) {
                list.add(sc.nextLine() + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double[] arr = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = Double.parseDouble(list.get(i));
        }

        GisFrame frame = new GisFrame(arr);
        BufferedImage image = frame.getImage();

        this.testGraph.setImage(SwingFXUtils.toFXImage(image, null));
    }

    @FXML
    private void activeButtonStart(ActionEvent event) {
        Logger logger = Logger.getLogger("DesigController");

        if (file == null) {
            return;
        }

        getDataInFile();

        try (FileWriter fw = new FileWriter("Method.txt", true);
            FileWriter fwRasp = new FileWriter("Distribution.txt")) {
            if (MetodGrabbsa.isSelected()) {
                fw.write("grabs\n");
            }
            if (Metod3Sigm.isSelected()) {
                fw.write("sigm");
            }

            if (VidNormal.isSelected()) {
                fwRasp.write("normal");
            }
            if (VidLogonormalnoe.isSelected()) {
                fwRasp.write("lognormal");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("End-Window.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 1300, 800);
            stage.setTitle("Rejection App end");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    private void getDataInFile() {
        Logger logger = Logger.getLogger("DesigController");
        if (FilenameUtils.getExtension(fileName.getText()).equals("txt")) {
            try (Scanner sc = new Scanner(file);
                 FileWriter fw = new FileWriter("arr.txt")) {
                while (sc.hasNextLine()) {
                    fw.write(sc.nextLine() + "\n");
                }
            } catch (Exception e) {
                logger.warning(e.getMessage());
            }
        }

        if (FilenameUtils.getExtension(fileName.getText()).equals("xlsx")) {
            ExelWork exelWork = new ExelWork(file);
            exelWork.setArrForTXT();
        }
    }

}
