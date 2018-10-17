/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import com.jfoenix.controls.JFXButton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javax.swing.JOptionPane;

/**
 *
 * @author al550545604
 */
public class Main {

    private final Scene scene;
    private final BorderPane borderPane;
    private String video;
    private final JFXButton btnExit;
    private final MediaPlayer player;
    private final Media media;
    private final MediaView playerView;

    public Main() {

        String path = System.getProperty("user.dir");
        path = path.replace("\\", "/");

        this.video = "file:///" + path + "/media/video.mp4";
        this.media = new Media(this.video);
        this.borderPane = new BorderPane();
        this.scene = new Scene(this.borderPane);
        this.btnExit = new JFXButton();
        this.player = new MediaPlayer(this.media);
        this.playerView = new MediaView(this.player);

        this.btnExit.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnExit.setPrefSize(150, 40);
        this.btnExit.setButtonType(JFXButton.ButtonType.RAISED);
        this.btnExit.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnExit.setTextFill(Paint.valueOf("#ffffff"));
        this.btnExit.setStyle("-fx-border-radius: 5; -fx-background-color: #991e1e;");
        this.btnExit.setOnAction((event) -> {
            this.player.stop();
            EnglishTutorExecute.getPlano().setScene(new Login().getScene());
            EnglishTutorExecute.getPlano().setFullScreen(true);
            EnglishTutorExecute.getPlano().setFullScreenExitHint("");
            EnglishTutorExecute.getPlano().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        });

        this.player.setAutoPlay(true);
        this.playerView.setFitWidth(800);
        this.playerView.setFitHeight(600);
        this.playerView.setStyle("-fx-border-radius: 20px 20px 20px 20px;");

        this.borderPane.setCenter(this.playerView);
        this.borderPane.setBottom(btnExit);
        play();
    }

    private void play() {

        this.player.setCycleCount(1);
        this.player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player.stop();
                play();
            }
        });
        Thread tempo = new Thread() {
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(player.getStatus() == Status.PLAYING) {
                        System.out.println((int) player.getCurrentTime().toSeconds());
                        if((int) player.getCurrentTime().toSeconds() == 51) {
                            player.pause();
                        }
                    }
                    else
                        System.out.println("Player não tocando"); 
                }
            }
        };
        tempo.start();
        
        this.playerView.setMediaPlayer(this.player);
        this.player.play();
    }

    public Scene getScene() {
        return this.scene;
    }
}
