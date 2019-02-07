/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle;

import javafx.stage.Stage;

/**
 *
 * @author Ronaldo
 */
public class StageFactory {
 
    private static Stage stagePrincipal = null;
    
    public static void setStage(Stage stage) {
        stagePrincipal = stage;
    }
    
    public static Stage getStage() {
        return stagePrincipal;
    }
}