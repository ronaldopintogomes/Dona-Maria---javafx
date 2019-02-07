/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle.recurso;

import br.com.donamaria.controle.StageFactory;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

/**
 *
 * @author Ronaldo
 */
public class Mensagem {
 
    public static Optional<ButtonType> confirmacao(String mensagem) {
        Optional<ButtonType> opcao = null;
        
        Alert dialogoConfirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoConfirmacao.setTitle("Mensagem");
        dialogoConfirmacao.setHeaderText(null);
        dialogoConfirmacao.setContentText(mensagem);
        dialogoConfirmacao.initOwner(StageFactory.getStage());
        opcao = dialogoConfirmacao.showAndWait();
        
        return opcao;
    }
 
    public static void informacao(String mensagem) {
        Alert dialogoInformacao = new Alert(Alert.AlertType.INFORMATION);
        dialogoInformacao.setTitle("Mensagem");
        dialogoInformacao.setHeaderText(null);
        dialogoInformacao.setContentText(mensagem);
        dialogoInformacao.initOwner(StageFactory.getStage());
        dialogoInformacao.showAndWait();
    }
    
    public static void alerta(String mensagem) {
        Alert dialogoAlerta = new Alert(Alert.AlertType.WARNING);
        dialogoAlerta.setTitle("Mensagem");
        dialogoAlerta.setHeaderText(null);
        dialogoAlerta.setContentText(mensagem);
        dialogoAlerta.initOwner(StageFactory.getStage());
        dialogoAlerta.showAndWait();
    }
    
    public static void error(String mensagem) {
        Alert dialogoError = new Alert(Alert.AlertType.ERROR);
        dialogoError.setTitle("Mensagem");
        dialogoError.setHeaderText(null);
        dialogoError.setContentText(mensagem);
        dialogoError.initOwner(StageFactory.getStage());
        dialogoError.showAndWait();
    }
}