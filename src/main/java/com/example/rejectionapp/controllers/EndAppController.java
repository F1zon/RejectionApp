package com.example.rejectionapp.controllers;

import com.example.rejectionapp.ExelWork;
import com.example.rejectionapp.HelloApplication;
import com.example.rejectionapp.gistogreMethods.GisFrame;
import com.example.rejectionapp.gistogreMethods.GisPanel;
import com.example.rejectionapp.methods.Selection;
import javafx.embed.swing.SwingFXUtils;
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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private String vid;
    private final Selection selection = new Selection();
    private final Logger logger = Logger.getLogger(EndAppController.class.getName());
    private Window window;
    private Scene scene;
    private BufferedImage image;

    @FXML
    private void startWindow() {
        logger.info("Initialize EndAppController");
        File fileMethod = new File("Method.txt");
        File fileArr = new File("arr.txt");
        File fileVid = new File("Distribution.txt");
        List<String> list = new ArrayList<>();
//        GisFrame frame = new GisFrame(fileArr);

        try (Scanner sc = new Scanner(fileMethod);
            Scanner sc1 = new Scanner(fileArr);
            Scanner sc2 = new Scanner(fileVid)) {
            while (sc.hasNextLine()) {
                method = sc.nextLine();
            }

            while (sc2.hasNextLine()) {
                vid = sc2.nextLine();
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

        String[] info = selection.start(method, arr, vid);
        this.arr = selection.getArr();
        GisFrame frame = new GisFrame(this.arr);

        coefficientVariation.setText(info[0]);
        averageValue.setText(info[1]);
        standardDeviation.setText(info[2]);
        this.image = frame.getImage();
        GistogramImage.setImage(SwingFXUtils.toFXImage(image, null));
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

    @FXML
    private void downloadGis(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File gis = new File("gis.jpg");
        ImageIO.write(image, "JPG", gis);

        Window stage = Background.getScene().getWindow();
        fileChooser.setTitle("Save Gsitogram");
        fileChooser.setInitialFileName("GisResult");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG file", "*.jpg"));

        try {
            File file = fileChooser.showSaveDialog(stage);
            fileChooser.setInitialDirectory(file.getParentFile());
            Files.copy(gis.toPath(), file.toPath());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
