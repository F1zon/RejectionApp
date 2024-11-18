package com.example.rejectionapp.controllers;

import com.example.rejectionapp.ExelWork;
import com.example.rejectionapp.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class DesigController {

    @FXML
    private Pane Background;

    @FXML
    private Button ButtonStart;

    @FXML
    private Pane Info;

    @FXML
    private CheckBox Metod3Sigm;

    @FXML
    private CheckBox MetodGrabbsa;

    @FXML
    private Button OnloadDock;

    @FXML
    private CheckBox VidLogonormalnoe;

    @FXML
    private CheckBox VidNormal;

    @FXML
    private Text fileName;

    private Logger logger = Logger.getLogger("DesigController");
    private Stage stage;
    private File file;

    public void init(Stage stage) {
        this.stage = stage;
    }

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
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            System.out.println("File selected: " + file);
            fileName.setText(file.getName());
        }
    }

    @FXML
    private void activeButtonStart(ActionEvent event) {
        logger = Logger.getLogger("DesigController");
        if (file == null) {
            return;
        }

        if (FilenameUtils.getExtension(fileName.getText()).equals("txt")) {
            try (Scanner sc = new Scanner(file);
                 FileWriter fw = new FileWriter("arr.txt");) {
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

        try (FileWriter fw = new FileWriter("Method.txt", true)) {
            if (MetodGrabbsa.isSelected()) {
                fw.write("grabs\n");
            }
            if (Metod3Sigm.isSelected()) {
                fw.write("sigm");
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

}
