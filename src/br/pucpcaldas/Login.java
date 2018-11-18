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
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    private final Dimension screen;
    private final BorderPane borderPane;
    private final JFXButton btnLogin;
    private final JFXButton btnExit;
    private final JFXButton btnCadastrar;
    private final JFXTextField usuario;
    private final JFXPasswordField senha;
    private final Text lbl_usuario;
    private final Text lbl_senha;
    private final Text sceneTitle;
    private final int width;
    private final int height;

    public Login() {

        this.screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = (int) (this.screen.getWidth() * 0.40);
        this.height = (int) (this.screen.getHeight() * 0.50);
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
        this.lbl_usuario.setFill(Paint.valueOf("#000000"));
        this.lbl_senha.setFont(Font.font("Arial", 16));
        this.lbl_senha.setFill(Paint.valueOf("#000000"));

        this.usuario.setStyle("-fx-text-inner-color: black;");
        this.usuario.setFont(Font.font("Arial", 14));
        this.usuario.setPrefSize(300, 30);
        this.senha.setStyle("-fx-text-inner-color: black;");
        this.senha.setFont(Font.font("Arial", 14));
        this.senha.setPrefSize(300, 30);

        this.btnLogin.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnLogin.setPrefSize(this.width * 0.4, this.height * 0.08);
        this.btnLogin.setButtonType(ButtonType.RAISED);
        this.btnLogin.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnLogin.setTextFill(Paint.valueOf("#ffffff"));
        this.btnLogin.setStyle("-fx-background-radius: 5; -fx-background-color: #095860;");
        this.btnLogin.setOnAction((event) -> {

            Conexao conexao = Conexao.getConexao();
            conexao.abrirConexao();

            try {
                conexao.setStatement(conexao.getCon().createStatement());
                PreparedStatement st = conexao.getCon().prepareStatement("SELECT * FROM tbl_usuarios WHERE login = ? AND senha = ?");

                st.setString(1, this.usuario.getText());
                st.setString(2, this.senha.getText());

                conexao.setResultSet(st.executeQuery());
                if (conexao.getResultSet().next()) {
                    Sessao sessao = Sessao.getSessao();
                    sessao.setLogin(conexao.getResultSet().getString("login"));
                    sessao.setNota(Double.parseDouble(conexao.getResultSet().getString("nota")));
                    System.out.println("Nota: " + sessao.getNota());
                    //EnglishTutorExecute.novoPlano(new FirstTest().getSceneAudio());
                    EnglishTutorExecute.novoPlano(new Main().getScene());
                }
                else {
                    System.out.println("Usuario e senha incorretos!");
                }
            } catch(SQLException | HeadlessException e) {
                System.out.println("Erro na consulta!: " + e);
            }

        });

        this.btnExit.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnExit.setPrefSize(this.width * 0.4, this.height * 0.08);
        this.btnExit.setButtonType(ButtonType.RAISED);
        this.btnExit.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnExit.setTextFill(Paint.valueOf("#ffffff"));
        this.btnExit.setStyle("-fx-background-radius: 5; -fx-background-color: #991e1e;");
        this.btnExit.setOnAction((event) -> {
            System.exit(1);
        });

        this.btnCadastrar.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.btnCadastrar.setPrefSize(this.width * 0.4, this.height * 0.08);
        this.btnCadastrar.setButtonType(ButtonType.RAISED);
        this.btnCadastrar.setRipplerFill(Paint.valueOf("#ffffff"));
        this.btnCadastrar.setTextFill(Paint.valueOf("#ffffff"));
        this.btnCadastrar.setStyle("-fx-background-radius: 5; -fx-background-color: #1e5799;");

        this.grid.setAlignment(Pos.CENTER);
        this.grid.setHgap(20);
        this.grid.setVgap(20);
        this.grid.setPadding(new Insets(10, 10, 10, 10));
        this.grid.add(this.lbl_usuario, 0, 0, 1, 1);
        this.grid.add(this.lbl_senha, 0, 1, 1, 1);
        this.grid.add(this.usuario, 1, 0, 1, 1);
        this.grid.add(this.senha, 1, 1, 1, 1);
        this.grid.add(this.btnExit, 1, 4, 1, 1);
        this.grid.add(this.btnLogin, 1, 2, 1, 1);
        this.grid.add(this.btnCadastrar, 1, 3, 1, 1);
        this.grid.setStyle("-fx-background-radius: 15px 15px 15px 15px; -fx-background-color: #ffffff;");

        this.borderPane.setAlignment(this.sceneTitle, Pos.TOP_CENTER);
        this.borderPane.setTop(this.sceneTitle);
        this.borderPane.setPadding(new Insets(50, 50, 50, 50));
        this.borderPane.setCenter(this.grid);
        this.borderPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#0e4368"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.borderPane.setMargin(grid, new Insets(30, 100, 0, 100));

        this.scene = new Scene(borderPane, width, height);
    }

    public Scene getScene() {
        return this.scene;
    }
}
