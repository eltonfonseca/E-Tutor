/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import com.jfoenix.controls.JFXButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Elton
 */
public class FirstTest {

    private Scene sceneVideo, sceneAudio;
    private StackPane stackAudio, stackVideo;
    private BorderPane rootPaneAudio, rootPaneVideo;
    private HBox hboxAudio, hboxVideo;
    private Dimension screen;
    private Text audioText;
    private int width;
    private int height;
    private MediaPlayer playerVideo;
    private MediaPlayer playerAudio;
    private Media media;
    private MediaView playerView;
    private JFXButton playButtonAudio, playButtonVideo;
    private Thread monitorTempoVideo, monitorTempoAudio;
    private String file;
    private double nota;
    private boolean terminou;
    private Sessao sessao;

    public FirstTest() {
        this.terminou = false;
        ajustResolution();
        buildButtons();
        buildSceneVideo();
        buildSceneAudio();
        handleButtonsClick();
        disparaThreadMonitorVideo();
        disparaThreadMonitorAudio();
    }

    public Scene getSceneVideo() {
        return this.sceneVideo;
    }

    public Scene getSceneAudio() {
        return this.sceneAudio;
    }

    private void buildSceneVideo() {
        String path = System.getProperty("user.dir");

        path = path.replace("\\", "/");
        this.file = "file:///" + path + "/media/videos/test.mp4";
        this.media = new Media(this.file);
        this.playerVideo = new MediaPlayer(this.media);
        this.playerView = new MediaView(this.playerVideo);
        this.playerView.setFitWidth(width * 0.8);
        this.playerView.setFitHeight((width * 0.9) * 2);
        this.playerView.setStyle("-fx-border-radius: 20px 20px 20px 20px;");
        this.playerView.setMediaPlayer(this.playerVideo);

        this.rootPaneVideo = new BorderPane();
        this.hboxVideo = new HBox(30);
        this.hboxVideo.setAlignment(Pos.CENTER);
        this.hboxVideo.setPadding(new Insets(0, 0, 20, 0));
        this.hboxVideo.getChildren().addAll(this.playButtonVideo);
        this.stackVideo = new StackPane();
        this.stackVideo.getChildren().addAll(this.playerView);
        this.rootPaneVideo.setCenter(stackVideo);
        this.rootPaneVideo.setBottom(this.hboxVideo);
        this.sceneVideo = new Scene(this.rootPaneVideo, width, height);
    }

    private void buildSceneAudio() {
        String path = System.getProperty("user.dir");
        path = path.replace("\\", "/");

        this.file = "file:///" + path + "/media/audios/test.mp3";
        this.media = new Media(this.file);
        this.playerAudio = new MediaPlayer(this.media);

        this.rootPaneAudio = new BorderPane();
        this.hboxAudio = new HBox(30);
        this.hboxAudio.setAlignment(Pos.CENTER);
        this.hboxAudio.setPadding(new Insets(0, 0, 20, 0));
        this.hboxAudio.getChildren().addAll(this.playButtonAudio);
        this.stackAudio = new StackPane();
        this.audioText = new Text("Click on PLAY AUDIO");
        this.audioText.setFill(Color.BLUE);
        this.audioText.setFont(Font.font("Arial", 20));
        this.stackAudio.getChildren().addAll(this.audioText);
        this.rootPaneAudio.setCenter(stackAudio);
        this.rootPaneAudio.setBottom(this.hboxAudio);
        this.sceneAudio = new Scene(this.rootPaneAudio, width, height);
    }

    private void playVideo() {
        this.playerVideo.setStartTime(Duration.seconds(10));
        this.playerVideo.setOnEndOfMedia(() -> {
            playerVideo.stop();
            this.terminou = true;
            alerta("Nota final: " + nota);
            this.sessao = Sessao.getSessao();
            this.sessao.setNota(nota);
            EnglishTutorExecute.novoPlano(new Main().getScene());
        });
        this.playerVideo.play();
    }

    private void playAudio() {
        this.playerAudio.setStartTime(Duration.seconds(2));
        this.playerAudio.setOnEndOfMedia(() -> {
            playerAudio.stop();
            EnglishTutorExecute.getPlano().setScene(sceneVideo);
        });
        this.playerAudio.play();
    }

    private void ajustResolution() {
        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = (int) (this.screen.getWidth() * 0.50);
        this.height = (int) (this.screen.getHeight() * 0.60);
    }

    private void buildButtons() {
        this.playButtonVideo = new JFXButton("PLAY VIDEO");
        this.playButtonVideo.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.playButtonVideo.setPrefSize(width * 0.15, this.height * 0.08);
        this.playButtonVideo.setButtonType(JFXButton.ButtonType.RAISED);
        this.playButtonVideo.setRipplerFill(Paint.valueOf("#ffffff"));
        this.playButtonVideo.setTextFill(Paint.valueOf("#ffffff"));
        this.playButtonVideo.setStyle("-fx-background-radius: 20px 20px 20px 20px; -fx-background-color: #022614;");

        this.playButtonAudio = new JFXButton("PLAY AUDIO");
        this.playButtonAudio.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.playButtonAudio.setPrefSize(width * 0.15, this.height * 0.08);
        this.playButtonAudio.setButtonType(JFXButton.ButtonType.RAISED);
        this.playButtonAudio.setRipplerFill(Paint.valueOf("#ffffff"));
        this.playButtonAudio.setTextFill(Paint.valueOf("#ffffff"));
        this.playButtonAudio.setStyle("-fx-background-radius: 20px 20px 20px 20px; -fx-background-color: #022614;");
    }

    private void handleButtonsClick() {
        this.playButtonAudio.setOnMouseClicked((event) -> {
            playButtonAudio.setDisable(true);
            this.audioText.setText("Playing Audio...");
            playAudio();
        });

        this.playButtonVideo.setOnMouseClicked((event) -> {
            playButtonVideo.setDisable(true);
            playVideo();
        });
    }

    private void disparaThreadMonitorVideo() {
        this.monitorTempoVideo = new Thread() {
            @Override
            public void run() {
                while (!terminou) {
                    if (playerVideo.getStatus() == Status.PLAYING) {
                        System.out.println("Tocando Vídeo - Tempo: " + playerVideo.getCurrentTime().toSeconds());
                        if ((int) playerVideo.getCurrentTime().toSeconds() == 23) {
                            playerVideo.pause();
                            Platform.runLater(() -> {
                                String resposta = pergunta("What's her name?");
                                if (resposta.toLowerCase().equals("cecile")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                resposta = pergunta("Where her from?");
                                if (resposta.toLowerCase().equals("california")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                playerVideo.setStartTime(Duration.seconds(24.01));
                                playerVideo.play();
                            });
                        } else if ((int) playerVideo.getCurrentTime().toSeconds() == 38) {
                            playerVideo.pause();
                            Platform.runLater(() -> {
                                String resposta = pergunta("What's his name?");
                                if (resposta.toLowerCase().equals("andy")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                resposta = pergunta("Where his from?");
                                if (resposta.toLowerCase().equals("new carson")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                playerVideo.setStartTime(Duration.seconds(39.01));
                                playerVideo.play();
                            });
                        } else if ((int) playerVideo.getCurrentTime().toSeconds() == 55) {
                            playerVideo.pause();
                            Platform.runLater(() -> {
                                String resposta = pergunta("What's his name?");
                                if (resposta.toLowerCase().equals("david")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                resposta = pergunta("Where his from?");
                                if (resposta.toLowerCase().equals("paris")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                playerVideo.setStartTime(Duration.seconds(56.01));
                                playerVideo.play();
                            });
                        } else if ((int) playerVideo.getCurrentTime().toSeconds() == 67) {
                            playerVideo.pause();
                            Platform.runLater(() -> {
                                String resposta = pergunta("What's her name?");
                                if (resposta.toLowerCase().equals("arja")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                resposta = pergunta("Where her from?");
                                if (resposta.toLowerCase().equals("finland")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                playerVideo.setStartTime(Duration.seconds(68.01));
                                playerVideo.play();
                            });
                        } else if ((int) playerVideo.getCurrentTime().toSeconds() == 81) {
                            playerVideo.pause();
                            Platform.runLater(() -> {
                                String resposta = pergunta("What's her name?");
                                if (resposta.toLowerCase().equals("karen")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                resposta = pergunta("Where her from?");
                                if (resposta.toLowerCase().equals("switzerland")) {
                                    nota++;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                playerVideo.setStartTime(Duration.seconds(82.01));
                                playerVideo.play();
                            });
                        }
                    } else {
                        System.out.println("Vídeo parado...");
                    }
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException ex) {
                    }
                }
            }
        };

        this.monitorTempoVideo.start();
    }

    private void disparaThreadMonitorAudio() {
        this.monitorTempoAudio = new Thread() {
            @Override
            public void run() {
                while (!terminou) {
                    if (playerAudio.getStatus() == Status.PLAYING) {
                        System.out.println("Tocando Audio - Tempo: " + playerAudio.getCurrentTime().toSeconds());
                        if ((int) playerAudio.getCurrentTime().toSeconds() == 13) {
                            playerAudio.pause();
                            Platform.runLater(() -> {
                                String resposta = pergunta("What's country his from?");
                                if (resposta.toLowerCase().equals("scotland")) {
                                    nota += 2;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                playerAudio.setStartTime(Duration.seconds(14.01));
                                playerAudio.play();
                            });
                        } else if ((int) playerAudio.getCurrentTime().toSeconds() == 31) {
                            playerAudio.pause();
                            Platform.runLater(() -> {
                                String resposta = pergunta("What's country her from?");
                                if (resposta.toLowerCase().equals("australia")) {
                                    nota += 2;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                playerAudio.setStartTime(Duration.seconds(32.01));
                                playerAudio.play();
                            });
                        } else if ((int) playerAudio.getCurrentTime().toSeconds() == 46) {
                            playerAudio.pause();
                            Platform.runLater(() -> {
                                String resposta = pergunta("What's country they from?");
                                if (resposta.toLowerCase().equals("usa")) {
                                    nota += 0.5;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                resposta = pergunta("What do they do?");
                                if (resposta.toLowerCase().equals("they are students")) {
                                    nota += 0.5;
                                    alerta("You're right!");
                                }
                                else {
                                    alerta("You missed!");
                                }
                                System.out.println("Nota Total: "+ nota);
                                playerAudio.setStartTime(Duration.seconds(47.01));
                                playerAudio.play();
                            });
                        }
                    } else {
                        System.out.println("Áudio parado...");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };

        this.monitorTempoAudio.start();
    }

    private String pergunta(String pergunta) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Answer the Question!");
        dialog.setHeaderText(pergunta);

        Optional<String> result = dialog.showAndWait();
        return result.get();
    }

    private void alerta(String info) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }
}
