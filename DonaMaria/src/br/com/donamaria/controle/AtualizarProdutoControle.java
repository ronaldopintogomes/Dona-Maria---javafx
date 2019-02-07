/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle;

import br.com.donamaria.controle.recurso.Mensagem;
import br.com.donamaria.modelo.negocio.persistencia.entidade.Produto;
import br.com.donamaria.modelo.negocio.servico.ProdutoServico;
import java.io.IOException;
import java.util.List;
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
public class AtualizarProdutoControle {
 
    private Stage stage;
    @FXML
    private TextField fieldDescricao, fieldCodigo, fieldValor, fieldQuantidade;
    @FXML
    private Button btAtualizar, btPesquisar;
    private ProdutoServico produtoServico;
    
    public AtualizarProdutoControle() {
        stage = new Stage();
        produtoServico = new ProdutoServico();
    }
    
    public void display() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/donamaria/visao/fxml/AtualizarProdutoFxml.fxml"));
            Scene scene = new Scene(parent);
            
            stage.setTitle("Atualizar Produto");
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
    private void atualizar(ActionEvent event) {
        String descricao = fieldDescricao.getText();
        String codigo = fieldCodigo.getText();
        String valor = fieldValor.getText();
        
        try {
            if(descricao.isEmpty() || codigo.isEmpty() || valor.isEmpty()) {
                throw new NullPointerException("Descricao, codigo e valor sao obrigatorios");
            } else {
                if(!codigo.matches("\\d{13}")) {
                    fieldCodigo.clear();
                    throw new IllegalArgumentException("Codigo incorreto");
                }
                if(!valor.matches("(\\d|\\.)+")) {
                    fieldValor.clear();
                    throw new IllegalArgumentException("Valor incorreto");
                }
                produtoServico.atualizar(descricao, codigo, valor);
                Mensagem.informacao("Operacao realizada com sucesso");
                resetarCampos();
            }
        } catch(NullPointerException e) {
            Mensagem.alerta(e.getMessage());
        } catch(IllegalArgumentException e) {
            Mensagem.alerta(e.getMessage());
        }
    }
    
    @FXML
    private void pesquisar(ActionEvent event) {
        String codigo = fieldCodigo.getText();
        
        try {
            if(codigo.isEmpty()) {
                throw new NullPointerException("Insira o codigo do produto");
            } else {
                if(!codigo.matches("\\d{13}")) {
                    fieldCodigo.clear();
                    throw new IllegalArgumentException("Codigo incorreto");
                } else {
                    List<Produto> listaProdutos = produtoServico.getProdutos(codigo.trim());
                    Produto produto = listaProdutos.get(0);
                    fieldDescricao.setText(produto.getDescricao());
                    fieldValor.setText(String.valueOf(produto.getValor()));
                    fieldQuantidade.setText(String.valueOf(listaProdutos.size()));
                }
            }
        } catch(IllegalArgumentException e) {
            Mensagem.alerta(e.getMessage());
            resetarCampos();
        } catch(NullPointerException e) {
            Mensagem.alerta(e.getMessage());
            resetarCampos();
        }
    }
    
    private void resetarCampos() {
        fieldDescricao.clear();
        fieldCodigo.clear();
        fieldValor.clear();
    }
}