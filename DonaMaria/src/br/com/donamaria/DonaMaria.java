/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria;

import br.com.donamaria.controle.LoginControle;
import br.com.donamaria.controle.StageFactory;
import br.com.donamaria.modelo.negocio.persistencia.util.UnidadeDePersistencia;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Ronaldo
 */
public class DonaMaria extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        UnidadeDePersistencia.init();
        StageFactory.setStage(primaryStage);
        LoginControle login = new LoginControle();
        login.display();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}