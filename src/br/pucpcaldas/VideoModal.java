/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import com.jfoenix.controls.JFXButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Elton
 */
public class VideoModal {
    
    public static void displayVideo(MediaPlayer player) {
        Stage window = new Stage();
        JFXButton botao = new JFXButton();
        VBox vbox = new VBox();
        
        vbox.getChildren().addAll(botao);
        window.setScene(new Scene(vbox));
        window.showAndWait();
    }
    
    
}
