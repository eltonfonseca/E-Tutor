/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author al550545604
 */
public class Login extends Application {
    
    private Dimension screen;
    private StackPane stackPane;
    private BorderPane borderPane;
    private Scene scene;
    private JFXButton botao;
    private int width;
    private int height;
    
    @Override
    public void start(Stage primaryStage) {
        
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int)(screen.width * 0.40);
        height = (int)(screen.height * 0.50);
        
        stackPane = new StackPane();
        borderPane = new BorderPane();
        botao = new JFXButton();
        
        botao.setText("LOGIN");
        botao.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        botao.setPrefSize(100, 40);
        botao.setButtonType(ButtonType.RAISED);
        botao.setRipplerFill(Paint.valueOf("#ffffff"));
        botao.setTextFill(Paint.valueOf("#ffffff"));
        
        botao.setBackground(new Background(new BackgroundFill(Paint.valueOf("#095860"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        borderPane.setAlignment(botao, Pos.TOP_CENTER);
        borderPane.setTop(botao);
        borderPane.setPadding(new Insets(50, 10, 10, 10));
        stackPane.getChildren().add(borderPane);
        
        Scene scene = new Scene(stackPane, width, height);
        
        primaryStage.setTitle("English Tutor - Login");
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
