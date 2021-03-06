/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import com.jfoenix.controls.JFXButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author al550545604
 */
public class Main {

    private Scene scene;
    private BorderPane rootPane;
    private Dimension screen;
    private int width;
    private int height;
    private VBox menu;
    private VBox vboxCenter;
    private Map<String, String> videos;
    private Map<String, String> audios;
    private JFXButton btnExit;
    private JFXButton btnInglesBasico;
    private JFXButton btnInglesAvancado;
    private JFXButton btnInglesExercicioBasico;
    private JFXButton btnInglesExercicioAvancado;
    private Label title;
    private MediaPlayer player;
    private Media media;
    private MediaView playerView;
    private Thread tempoMonitor;
    private Sessao sessao;

    public Main() {
        ajustResolution();
        this.videos = buildListMedias("videos");
        this.audios = buildListMedias("audios");
        buildButtons();
        handleButtonsClick();
        buildPlayer();
        buildLayout();
        this.sessao = Sessao.getSessao();
        if (sessao.getNota() >= 9) {
            this.btnInglesBasico.setDisable(true);
            this.btnInglesExercicioBasico.setDisable(true);
        }
        /*
        //this.media = new Media(this.video);
        this.player = new MediaPlayer(this.media);
        this.playerView = new MediaView(this.player);

        
       

        this.player.setAutoPlay(true);
        this.playerView.setFitWidth(width * 0.5);
        this.playerView.setFitHeight((width * 0.5) * 2);
        this.playerView.setStyle("-fx-border-radius: 20px 20px 20px 20px;");
        
        // Set layout
        
        this.menu = new VBox();
        this.menu.setStyle("-fx-background-color: red;");
        this.menu.setPrefSize(width * 0.15, height);
        
        this.vboxCenter = new VBox();
        this.vboxCenter.setAlignment(Pos.CENTER);
        this.vboxCenter.getChildren().addAll(playerView, btnExit);
                
        this.rootPane = new BorderPane();
        this.rootPane.setLeft(menu);
        this.rootPane.setCenter(this.vboxCenter);
        this.scene = new Scene(this.rootPane, width, height);
        play();*/
    }

    private void buildLayout() {
        this.menu = new VBox();
        this.menu.setStyle("-fx-background-color: #022614;");
        this.menu.setPrefSize(width * 0.15, height);
        this.menu.getChildren().addAll(this.btnInglesBasico, this.btnInglesAvancado, this.btnInglesExercicioBasico, this.btnInglesExercicioAvancado, this.btnExit);

        this.rootPane = new BorderPane();
        this.rootPane.setLeft(menu);
        this.scene = new Scene(this.rootPane, width, height);
    }

    private void buildPlayer() {

    }

    public Scene getScene() {
        return this.scene;
    }

    private Map<String, String> buildListMedias(String folder) {

        Map<String, String> midias = new HashMap<>();

        // Set user's path, for example: C:/Users/elton
        String path = System.getProperty("user.dir");
        path = path.replace("\\", "/");
        String[] paths = new File(path + "/media/" + folder).list();

        for (String media : paths) {
            midias.put(media, "file:///" + path + "/media/" + folder + "/" + media);
        }
        return midias;
    }

    private String getMidia(String type, String key) {
        if (type.equals("audio")) {
            if (this.audios.containsKey(key)) {
                return this.audios.get(key);
            } else {
                System.out.println("Não existe áudio");
                return null;
            }
        } else {
            if (this.videos.containsKey(key)) {
                return this.videos.get(key);
            } else {
                System.out.println("Não existe vídeo");
                return null;
            }
        }
    }

    private void ajustResolution() {

        // Set Screen Resolution with 80% of Window
        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = (int) (this.screen.getWidth() * 0.80);
        this.height = (int) (this.screen.getHeight() * 0.80);
    }
    
    private void buildBeginnerTutorial() {
        VBox v = new VBox();
        JFXButton botao = new JFXButton("iniciante tutorial");
        v.getChildren().add(botao);
        v.setStyle("-fx-background-color: green;");
        this.rootPane.setCenter(v);
    }
    
    private void buildAdvancedTutorial() {
        VBox v = new VBox();
        JFXButton botao = new JFXButton("avançado tutorial");
        v.getChildren().add(botao);
        v.setStyle("-fx-background-color: red;");
        this.rootPane.setCenter(v);
    }
    
    private void buildBeginnerExercise() {
        VBox v = new VBox();
        JFXButton botao = new JFXButton("Botao exercicio iniciante");
        v.getChildren().add(botao);
        v.setStyle("-fx-background-color: blue;");
        this.rootPane.setCenter(v);
    }
    
    private void buildAdvancedExercise() {
        VBox v = new VBox();
        JFXButton botao = new JFXButton("botao exercicio avançado");
        v.getChildren().add(botao);
        v.setStyle("-fx-background-color: black;");
        this.rootPane.setCenter(v);
    }

    private void buildButtons() {

        // Botões do menu
        this.btnInglesBasico = new JFXButton("BEGINNER");
        this.btnInglesBasico.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnInglesBasico.setPrefSize(width * 0.15, height / 5);
        this.btnInglesBasico.setButtonType(JFXButton.ButtonType.RAISED);
        this.btnInglesBasico.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnInglesBasico.setTextFill(Paint.valueOf("#ffffff"));
        this.btnInglesBasico.setBackground(new Background(new BackgroundFill(Paint.valueOf("#022614"), CornerRadii.EMPTY, Insets.EMPTY)));

        this.btnInglesAvancado = new JFXButton("ADVANCED");
        this.btnInglesAvancado.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnInglesAvancado.setPrefSize(width * 0.15, height / 5);
        this.btnInglesAvancado.setButtonType(JFXButton.ButtonType.RAISED);
        this.btnInglesAvancado.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnInglesAvancado.setTextFill(Paint.valueOf("#ffffff"));
        this.btnInglesAvancado.setBackground(new Background(new BackgroundFill(Paint.valueOf("#022614"), CornerRadii.EMPTY, Insets.EMPTY)));

        this.btnInglesExercicioBasico = new JFXButton("EXERCISE BEGINNER");
        this.btnInglesExercicioBasico.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnInglesExercicioBasico.setPrefSize(width * 0.15, height / 5);
        this.btnInglesExercicioBasico.setButtonType(JFXButton.ButtonType.RAISED);
        this.btnInglesExercicioBasico.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnInglesExercicioBasico.setTextFill(Paint.valueOf("#ffffff"));
        this.btnInglesExercicioBasico.setBackground(new Background(new BackgroundFill(Paint.valueOf("#022614"), CornerRadii.EMPTY, Insets.EMPTY)));

        this.btnInglesExercicioAvancado = new JFXButton("EXERCISE ADVANCED");
        this.btnInglesExercicioAvancado.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnInglesExercicioAvancado.setPrefSize(width * 0.15, height / 5);
        this.btnInglesExercicioAvancado.setButtonType(JFXButton.ButtonType.RAISED);
        this.btnInglesExercicioAvancado.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnInglesExercicioAvancado.setTextFill(Paint.valueOf("#ffffff"));
        this.btnInglesExercicioAvancado.setBackground(new Background(new BackgroundFill(Paint.valueOf("#022614"), CornerRadii.EMPTY, Insets.EMPTY)));

        this.btnExit = new JFXButton("EXIT");
        this.btnExit.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnExit.setPrefSize(width * 0.15, height / 5);
        this.btnExit.setButtonType(JFXButton.ButtonType.RAISED);
        this.btnExit.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnExit.setTextFill(Paint.valueOf("#ffffff"));
        this.btnExit.setBackground(new Background(new BackgroundFill(Paint.valueOf("#5e1e1e"), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    private void handleButtonsClick() {

        this.btnExit.setOnMouseClicked((event) -> {
            //this.player.stop();
            //this.tempoMonitor.stop();
            EnglishTutorExecute.novoPlano(new Login().getScene());
        });
        
        this.btnInglesBasico.setOnMouseClicked((event) -> {
            buildBeginnerTutorial();
        });
        
        this.btnInglesAvancado.setOnMouseClicked((event) -> {
            buildAdvancedTutorial();
        });
        
        this.btnInglesExercicioBasico.setOnMouseClicked((event) -> {
            buildBeginnerExercise();
        });
        
        this.btnInglesExercicioAvancado.setOnMouseClicked((event) -> {
            buildAdvancedExercise();
        });

        this.btnExit.setOnMouseEntered((event) -> {
            this.btnExit.setBackground(new Background(new BackgroundFill(Paint.valueOf("#7f5656"), CornerRadii.EMPTY, Insets.EMPTY)));
            this.scene.setCursor(Cursor.HAND);
        });

        this.btnExit.setOnMouseExited((event) -> {
            this.btnExit.setBackground(new Background(new BackgroundFill(Paint.valueOf("#5e1e1e"), CornerRadii.EMPTY, Insets.EMPTY)));
            this.scene.setCursor(Cursor.DEFAULT);
        });

        this.btnInglesBasico.setOnMouseEntered((event) -> {
            this.btnInglesBasico.setBackground(new Background(new BackgroundFill(Paint.valueOf("#337253"), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        this.btnInglesBasico.setOnMouseExited((event) -> {
            this.btnInglesBasico.setBackground(new Background(new BackgroundFill(Paint.valueOf("#022614"), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        this.btnInglesAvancado.setOnMouseEntered((event) -> {
            this.btnInglesAvancado.setBackground(new Background(new BackgroundFill(Paint.valueOf("#337253"), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        this.btnInglesAvancado.setOnMouseExited((event) -> {
            this.btnInglesAvancado.setBackground(new Background(new BackgroundFill(Paint.valueOf("#022614"), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        this.btnInglesExercicioBasico.setOnMouseEntered((event) -> {
            this.btnInglesExercicioBasico.setBackground(new Background(new BackgroundFill(Paint.valueOf("#337253"), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        this.btnInglesExercicioBasico.setOnMouseExited((event) -> {
            this.btnInglesExercicioBasico.setBackground(new Background(new BackgroundFill(Paint.valueOf("#022614"), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        this.btnInglesExercicioAvancado.setOnMouseEntered((event) -> {
            this.btnInglesExercicioAvancado.setBackground(new Background(new BackgroundFill(Paint.valueOf("#337253"), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        this.btnInglesExercicioAvancado.setOnMouseExited((event) -> {
            this.btnInglesExercicioAvancado.setBackground(new Background(new BackgroundFill(Paint.valueOf("#022614"), CornerRadii.EMPTY, Insets.EMPTY)));
        });
    }
}
