/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import com.jfoenix.controls.JFXButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author al550545604
 */
public class Main {

    private final Scene scene;
    private final BorderPane borderPane;
    private final Dimension screen;
    private final int width;
    private final int height;
    private final VBox menu;
    private final String video;
    private final JFXButton btnExit;
    private final MediaPlayer player;
    private final Media media;
    private final MediaView playerView;
    private Thread tempoMonitor;

    public Main() {

        String path = System.getProperty("user.dir");
        path = path.replace("\\", "/");
        
        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = (int) (this.screen.getWidth() * 0.80);
        this.height = (int) (this.screen.getHeight() * 0.80);
        

        this.video = "file:///" + path + "/media/video.mp4";
        this.media = new Media(this.video);
        this.borderPane = new BorderPane();
        this.menu = new VBox();
        this.scene = new Scene(this.borderPane, width, height);
        this.btnExit = new JFXButton("SAIR");
        this.player = new MediaPlayer(this.media);
        this.playerView = new MediaView(this.player);

        this.menu.setStyle("-fx-background-color: black;");
        this.menu.setPrefSize(200, height);
        
        this.btnExit.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnExit.setPrefSize(150, 40);
        this.btnExit.setButtonType(JFXButton.ButtonType.RAISED);
        this.btnExit.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnExit.setTextFill(Paint.valueOf("#ffffff"));
        this.btnExit.setStyle("-fx-backgound-radius: 5; -fx-background-color: #991e1e;");
        this.btnExit.setOnAction((event) -> {
            this.player.stop();
            this.tempoMonitor.stop();
            EnglishTutorExecute.getPlano().close();
            Stage plano = new Stage(StageStyle.UNDECORATED);
            plano.setScene(new Login().getScene());
            EnglishTutorExecute.setPlano(plano);
        });

        this.player.setAutoPlay(true);
        this.playerView.setFitWidth(400);
        this.playerView.setFitHeight(200);
        this.playerView.setStyle("-fx-border-radius: 20px 20px 20px 20px;");
        
        this.borderPane.setAlignment(btnExit, Pos.CENTER);
        this.borderPane.setAlignment(menu, Pos.CENTER);
        this.borderPane.setLeft(menu);
        this.borderPane.setCenter(this.playerView);
        this.borderPane.setBottom(btnExit);
        play();
    }

    private void play() {

        this.player.setCycleCount(1);
        this.player.setOnEndOfMedia(() -> {
            player.stop();
            play();
        });
        this.tempoMonitor = new Thread() {
            @Override
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
        this.tempoMonitor.start();
        
        this.playerView.setMediaPlayer(this.player);
        this.player.play();
    }

    public Scene getScene() {
        return this.scene;
    }
}
