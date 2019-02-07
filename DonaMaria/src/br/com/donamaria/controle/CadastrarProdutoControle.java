/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle;

import br.com.donamaria.controle.recurso.Mensagem;
import br.com.donamaria.modelo.negocio.servico.ProdutoServico;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ronaldo
 */
public class CadastrarProdutoControle {
    
    private Stage stage;
    @FXML
    private TextField fieldDescricao, fieldValor, fieldCodigo, fieldQuantidade;
    @FXML
    private Button btCadastrar;
    private ProdutoServico produtoServico;
    
    public CadastrarProdutoControle() {
        stage = new Stage();
        produtoServico = new ProdutoServico();
    }
    
    public void display() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/donamaria/visao/fxml/CadastrarProdutoFxml.fxml"));
            Scene scene = new Scene(parent);
            
            stage.setTitle("Cadastrar Produto");
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initOwner(StageFactory.getStage());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void cadastrar(ActionEvent event) {
        String descricao = fieldDescricao.getText();
        String valor = fieldValor.getText();
        String codigo = fieldCodigo.getText();
        String quantidade = fieldQuantidade.getText();
        
        try {
            if(!valor.matches("(\\d|\\.)+")) {
                throw new IllegalArgumentException("Valor so pode conter numeros");
            }
            if(!codigo.matches("\\d{13}")) {
                throw new IllegalArgumentException("Codigo incorreto");
            }
            if(!quantidade.matches("\\d+")) {
                throw new IllegalArgumentException("Quantidade incorreta");
            }
            produtoServico.cadastrar(descricao, valor, codigo, quantidade);
            resetarCampos();
            Mensagem.informacao("Operacao realizada com sucesso");
        } catch(IllegalArgumentException e) {
            Mensagem.alerta(e.getMessage());
        }
    }
    
    private void resetarCampos() {
        fieldDescricao.clear();
        fieldValor.clear();
        fieldCodigo.clear();
        fieldQuantidade.clear();
    }
}