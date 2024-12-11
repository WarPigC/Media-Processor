package org.project.mediaprocessor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class MainController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button Vproc,Aproc;


    public void SwitchSceneToVideo(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("VideoScene.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchSceneToAudio(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("AudioScene.fxml"));
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    public void SwitchScenToMain(ActionEvent event) throws IOException{
//        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
//        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    public void SwitchSceneCompletion(ActionEvent event) throws IOException{

    }

}