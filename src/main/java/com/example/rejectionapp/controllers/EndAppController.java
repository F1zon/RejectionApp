package com.example.rejectionapp.controllers;

import com.example.rejectionapp.ExelWork;
import com.example.rejectionapp.GrGis;
import com.example.rejectionapp.HelloApplication;
import com.example.rejectionapp.methods.Selection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class EndAppController {

    @FXML
    public Button getResult;

    @FXML
    private Pane Background;

    @FXML
    private Button downloadGis;

    @FXML
    private Button buttonStart;

    @FXML
    private Button OnloadDock;

    @FXML
    private Text asymmetry;

    @FXML
    private Text averageValue;

    @FXML
    private Text coefficientVariation;

    @FXML
    private Text excel;

    @FXML
    private Text standardDeviation;

    @FXML
    private ImageView GistogramImage;

    double[] arr;
    private String method;
    private final Selection selection = new Selection();
    private final Logger logger = Logger.getLogger(EndAppController.class.getName());
    private Window window;
    private Scene scene;

    @FXML
    private void startWindow() {
        logger.info("Initialize EndAppController");
        File fileMethod = new File("Method.txt");
        File fileArr = new File("arr.txt");
        List<String> list = new ArrayList<>();

        try (Scanner sc = new Scanner(fileMethod);
            Scanner sc1 = new Scanner(fileArr)) {
            while (sc.hasNextLine()) {
                method = sc.nextLine();
            }

            while (sc1.hasNextLine()) {
                list.add(sc1.nextLine() + "\n");
            }

            Path path = Paths.get("Method.txt");
            Files.writeString(path, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.arr = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = Double.parseDouble(list.get(i));
        }

        String[] info = selection.start(method, arr);

        coefficientVariation.setText(info[0]);
        averageValue.setText(info[1]);
        standardDeviation.setText(info[2]);
//        GistogramImage.setImage(new GrGis().getImageGis());

    }

    @FXML
    private void backMain(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 1300, 800);
            stage.setTitle("Rejection App");
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
    }

    @FXML
    private void downloadExel(ActionEvent event) {
        ExelWork exelWork = new ExelWork();
        File result = exelWork.CreateAndSetFileExel(this.arr);

        FileChooser fileChooser = new FileChooser();
        Window stage = Background.getScene().getWindow();
        fileChooser.setTitle("Save result");
        fileChooser.setInitialFileName("Result");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Exel file", "*.xlsx"));

        try {
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            Files.copy(result.toPath(), file.toPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
