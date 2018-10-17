/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author al550545604
 */
public class Login {
    
    private final Scene scene;
    private final GridPane grid;
    private final BorderPane borderPane;
    private final JFXButton btnLogin;
    private final JFXButton btnExit;
    private final JFXButton btnCadastrar;
    private final JFXTextField usuario;
    private final JFXPasswordField senha;
    private final Text lbl_usuario;
    private final Text lbl_senha;
    private final Text sceneTitle;    
    
    public Login() {
       
        this.borderPane = new BorderPane();
        this.grid = new GridPane();
        this.btnLogin = new JFXButton("ENTRAR");
        this.btnExit = new JFXButton("SAIR");
        this.btnCadastrar = new JFXButton("CADASTRAR");
        this.usuario = new JFXTextField();
        this.senha = new JFXPasswordField();
        this.lbl_senha = new Text("Senha: ");
        this.lbl_usuario = new Text("Usuário: ");
        this.sceneTitle = new Text("EnglishTutor - Login");
                
        this.sceneTitle.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        this.sceneTitle.setFill(Paint.valueOf("#ffffff"));
        this.lbl_usuario.setFont(Font.font("Arial", 16));
        this.lbl_usuario.setFill(Paint.valueOf("#ffffff"));
        this.lbl_senha.setFont(Font.font("Arial", 16));
        this.lbl_senha.setFill(Paint.valueOf("#ffffff"));
        
        this.usuario.setStyle("-fx-text-inner-color: white;");
        this.usuario.setFont(Font.font("Arial", 14));
        this.usuario.setPrefSize(300, 30);
        this.senha.setStyle("-fx-text-inner-color: white;");
        this.senha.setFont(Font.font("Arial", 14));
        this.senha.setPrefSize(300, 30);
  
        this.btnLogin.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnLogin.setPrefSize(150, 40);
        this.btnLogin.setButtonType(ButtonType.RAISED);
        this.btnLogin.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnLogin.setTextFill(Paint.valueOf("#ffffff"));
        this.btnLogin.setStyle("-fx-border-radius: 5; -fx-background-color: #095860;");
        this.btnLogin.setOnAction((event) -> {
            EnglishTutorExecute.getPlano().setScene(new Main().getScene());
            EnglishTutorExecute.getPlano().setFullScreen(true);
            EnglishTutorExecute.getPlano().setFullScreenExitHint("");
            EnglishTutorExecute.getPlano().setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        });
        
        this.btnExit.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnExit.setPrefSize(150, 40);
        this.btnExit.setButtonType(ButtonType.RAISED);
        this.btnExit.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnExit.setTextFill(Paint.valueOf("#ffffff"));
        this.btnExit.setStyle("-fx-border-radius: 5; -fx-background-color: #991e1e;");
        this.btnExit.setOnAction((event) -> {
            System.exit(1);
        });
        
        this.btnCadastrar.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnCadastrar.setPrefSize(150, 40);
        this.btnCadastrar.setButtonType(ButtonType.RAISED);
        this.btnCadastrar.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnCadastrar.setTextFill(Paint.valueOf("#ffffff"));
        this.btnCadastrar.setStyle("-fx-border-radius: 5; -fx-background-color: #1e5799;");
        
        this.grid.setAlignment(Pos.CENTER);
        this.grid.setHgap(20);
        this.grid.setVgap(20);
        this.grid.setPadding(new Insets(10, 10, 10, 10));
        this.grid.add(this.lbl_usuario, 0, 0, 1, 1);
        this.grid.add(this.lbl_senha, 0, 1, 1, 1);
        this.grid.add(this.usuario, 1, 0, 1, 1);
        this.grid.add(this.senha, 1, 1, 1, 1);
        this.grid.add(this.btnExit, 1, 4, 1 ,1);
        this.grid.add(this.btnLogin, 1, 3, 1, 1);
        this.grid.add(this.btnCadastrar, 1, 2, 1, 1);
        
        this.borderPane.setAlignment(this.sceneTitle, Pos.TOP_CENTER);
        this.borderPane.setTop(this.sceneTitle);
        this.borderPane.setPadding(new Insets(100, 10, 10, 10));
        this.borderPane.setCenter(this.grid);
        this.borderPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#0e4368"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        this.scene = new Scene(borderPane);
    }
        
    public Scene getScene() {
        return this.scene;
    }
}
