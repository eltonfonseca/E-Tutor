/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author al550545604
 */
public class EnglishTutorExecute extends Application {

    private static Stage plano;
    
    public static Stage getPlano() {
        return plano;
    }
    
    public static void novoPlano(Scene planoNovo) {
        Stage planoVelho = plano;
        plano = new Stage(StageStyle.UNDECORATED);
        plano.setScene(planoNovo);
        plano.centerOnScreen();
        plano.show();
        planoVelho.close();
    }
    
    private void init(Stage stage) {
        stage.setScene(new Login().getScene());
    }

    @Override
    public void start(Stage stage) throws Exception {
        EnglishTutorExecute.plano = new Stage(StageStyle.UNDECORATED);
        init(EnglishTutorExecute.plano);
        EnglishTutorExecute.plano.centerOnScreen();
        EnglishTutorExecute.plano.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
