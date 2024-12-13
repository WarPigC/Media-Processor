package org.project.mediaprocessor;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
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
import java.net.URL;
import java.util.ArrayList;

import java.io.IOException;
import java.util.ResourceBundle;

public class VideoController implements Initializable {

    private ArrayList<String> commands = new ArrayList<String>();
    private boolean extractAudioCondition = false;
    private SpinnerValueFactory<Integer> factory;

    @FXML
    private CheckBox extractAudio;

    @FXML
    private Button backToMain,openFile,startProcess;

    @FXML
    private TextField bitrateField,maxSize,outputFile,inputFile;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private ChoiceBox<String> preset,format;

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

    // extract audio
    public void audioExtraction(ActionEvent event){
        extractAudioCondition = !(extractAudioCondition);
        extractAudio.setSelected(extractAudioCondition);
    }


    public void SwitchSceneToMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));

        stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,51);
        factory.setValue(28);
        spinner.setValueFactory(factory);

        ArrayList<String> list = new ArrayList<>();
        list.add("mp4");
        list.add("m4a");
        list.add("webp");
        list.add("avi");
        list.add("gif");

        format.getItems().setAll(list);     // format
        format.setValue("mp4");

        list = new ArrayList<>();
        list.add("ultrafast");
        list.add("fast");
        list.add("medium");
        list.add("slow");
        list.add("veryslow");

        preset.getItems().setAll(list);     // preset
        preset.setValue("medium");
    }

    // check if output file is not null
    // add max file

    public void Go(ActionEvent event){
        commands = new ArrayList <String>();

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

        commands.add("-crf");
        commands.add(spinner.getValue().toString());

        if (!bitrateField.getText().isBlank() || !bitrateField.getText().isEmpty()){
            commands.add("-b:v");
            commands.add(bitrateField.getText() + "k");
        }

        if (!preset.getValue().equals("medium")){
            commands.add("-preset");
            commands.add(preset.getValue());
        }
        else {
            commands.add("-preset");
            commands.add("medium");
        }

        commands.add(outputFile.getText() + "." + format.getValue());



        // switch scene to CompletionScene
        Processor p = new Processor();
        try{
            p.process(commands);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
