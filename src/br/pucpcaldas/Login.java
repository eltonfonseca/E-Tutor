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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author al550545604
 */
public class Login {
    
    private static Login login;
    private final Scene scene;
    private final BorderPane borderPane;
    private final BorderPane borderPaneButtons;
    private final Dimension screen;
    private final JFXButton btnLogin;
    private final JFXButton btnExit;
    private final JFXButton btnCadastrar;
    private final int width;
    private final int height;
    
    
    public Login() {
        
        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.borderPane = new BorderPane();
        this.borderPaneButtons = new BorderPane();
        this.btnLogin = new JFXButton("ENTRAR");
        this.btnExit = new JFXButton("SAIR");
        this.btnCadastrar = new JFXButton("CADASTRAR");
        
        width = (int)(screen.width * 0.40);
        height = (int)(screen.height * 0.60);
        this.btnLogin.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnLogin.setPrefSize(100, 40);
        this.btnLogin.setButtonType(ButtonType.RAISED);
        this.btnLogin.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnLogin.setTextFill(Paint.valueOf("#ffffff"));
        this.btnLogin.setStyle("-fx-border-radius: 5; -fx-background-color: #095860;");
        
        this.btnExit.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnExit.setPrefSize(100, 40);
        this.btnExit.setButtonType(ButtonType.RAISED);
        this.btnExit.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnExit.setTextFill(Paint.valueOf("#ffffff"));
        this.btnExit.setStyle("-fx-border-radius: 5; -fx-background-color: #991e1e;");
        this.btnExit.setOnAction((event) -> {
            System.exit(1);
        });
        
        this.btnCadastrar.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnCadastrar.setPrefSize(100, 40);
        this.btnCadastrar.setButtonType(ButtonType.RAISED);
        this.btnCadastrar.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnCadastrar.setTextFill(Paint.valueOf("#ffffff"));
        this.btnCadastrar.setStyle("-fx-border-radius: 5; -fx-background-color: #1e5799;");
        
        this.borderPaneButtons.setLeft(this.btnExit);
        this.borderPaneButtons.setCenter(this.btnLogin);
        this.borderPaneButtons.setRight(this.btnCadastrar);
        this.borderPaneButtons.setPadding(new Insets(10, 100, 50, 100));
        
        this.borderPane.setBottom(this.borderPaneButtons);
        this.scene = new Scene(this.borderPane, width, height, Color.OLIVEDRAB);
    }
    
    public static Login getInstance() {
        if(login == null)
            login = new Login();
        return login;
    }
    
    public Scene getScene(){
        return scene;
    }
}
