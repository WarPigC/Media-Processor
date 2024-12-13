package org.project.mediaprocessor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AudioController implements Initializable {

    ArrayList<String> commands = new ArrayList<>();

    @FXML
    private Button backToMain, openFile, startProcess;

    @FXML
    private TextField bitrateField, maxSize, outputFile, inputFile;


    @FXML
    private ChoiceBox<String> channel, format;

    @FXML
    private FileChooser inputChooser;

    private Parent root;
    private Stage stage;
    private Scene scene;


    // input file
    public void fileInput(ActionEvent event) throws IOException {
        inputChooser = new FileChooser();
        File file = inputChooser.showOpenDialog((Stage)((Node) event.getSource()).getScene().getWindow());
        inputFile.setText(file.getAbsoluteFile().toString());
    }

    // output file

    // max file size

    // output format

    // channel

    // bitrate


    public void SwitchSceneToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArrayList<String> list = new ArrayList<>();
        list.add("m4a");
        list.add("webp");
        list.add("opus");
        list.add("mp3");
        format.getItems().setAll(list);
        format.setValue("mp3");

       list = new ArrayList<String>();
       list.add("None");
       list.add("Mono to Stereo");
       list.add("Stereo to Mono");
       channel.setValue("None");
       channel.getItems().setAll(list);
    }

    public void Go(ActionEvent event) throws IOException {
        commands = new ArrayList<>();

        if (outputFile.getText().isEmpty() || outputFile.getText().isBlank() ){
            outputFile.setText(null);
            outputFile.setPromptText("EMPTY");
            return;
        }
        if (!inputFile.getText().isEmpty()){
            commands.add(inputFile.getText());
        }
        // max size prompt
        if (!maxSize.getText().isBlank() || !maxSize.getText().isEmpty()){
            commands.add("-fs");
            commands.add(maxSize.getText() + "M");
        }

        if (!bitrateField.getText().isBlank() || !bitrateField.getText().isEmpty()){
            commands.add("-b:a");
            commands.add(bitrateField.getText() + "k");
        }

        //channel
        if (channel.getValue().equals("Mono to Stereo")){
            commands.add("-ac");
            commands.add("2");
        }
        else if(channel.getValue().equals("Stereo to Mono")){
            commands.add("-ac");
            commands.add("1");
        }

        commands.add("-vn");

        commands.add(outputFile.getText() + "." + format.getValue());


        Processor p = new Processor();
        try{
            p.process(commands);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}