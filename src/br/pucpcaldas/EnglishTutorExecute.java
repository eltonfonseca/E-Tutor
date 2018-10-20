/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.pucpcaldas;

import javafx.application.Application;
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
    
    public static void setPlano(Stage planoNovo) {
        plano = planoNovo;
        plano.centerOnScreen();
        plano.show();
    }
    
    private void init(Stage stage) {
        stage.setScene(new Login().getScene());
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.plano = new Stage(StageStyle.UNDECORATED);
        init(this.plano);
        this.plano.centerOnScreen();
        this.plano.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
