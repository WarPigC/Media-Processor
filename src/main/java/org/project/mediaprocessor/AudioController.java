package org.project.mediaprocessor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class AudioController {

    @FXML
    private Button backToMain, openFile, startProcess;

    @FXML
    private TextField bitrateField, maxSize, outputFile, inputFile;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private ComboBox<String> preset, format;

    @FXML
    private FileChooser inputChooser, audioChooser;

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void SwitchSceneToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}