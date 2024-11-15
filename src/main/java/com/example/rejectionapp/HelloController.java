package com.example.rejectionapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import java.util.logging.Logger;
import java.util.logging.LoggingPermission;

public class HelloController {
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

    private Logger logger = Logger.getLogger("HelloController");

    @FXML
    protected void onHelloButtonClick() {
        logger.info("Button clicked");

    }
}