/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle;

import br.com.donamaria.controle.recurso.Mensagem;
import br.com.donamaria.modelo.negocio.excecao.FuncionarioNaoCadastradoException;
import br.com.donamaria.modelo.negocio.persistencia.entidade.Funcionario;
import br.com.donamaria.modelo.negocio.servico.FuncionarioServico;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ronaldo
 */
public class AtualizarSenhaFuncionarioControle {
 
    private Stage stage;
    @FXML
    private TextField fieldNome, fieldIdentidade, fieldFuncao;
    @FXML
    private PasswordField fieldNovaSenha;
    private Button btAtualizarSenha;
    private FuncionarioServico funcionarioServico;
    
    public AtualizarSenhaFuncionarioControle() {
        stage = new Stage();
        funcionarioServico = new FuncionarioServico();
    }
    
    public void display() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/donamaria/visao/fxml/AtualizarSenhaFuncionarioFxml.fxml"));
            Scene scene = new Scene(parent);
            
            stage.setTitle("Atualizar senha");
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
        String identidade = fieldIdentidade.getText();
        String senha = fieldNovaSenha.getText();
        
        if(identidade.isEmpty()) {
            Mensagem.alerta("Identidade obrigatoria");
        } else {
            if(Long.parseLong(identidade) == Sessao.getFuncionario().getIdentidade()) {
                Mensagem.alerta("Vc nao pode atualizar a senha deste funcionario no momento");
            } else {
                funcionarioServico.atualizarSenha(identidade, senha);
                resetarCampos();
                Mensagem.informacao("Operacao realizada com sucesso");
            }
        }
    }
    
    @FXML
    private void pesquisar(ActionEvent event) {
        String identidade = fieldIdentidade.getText();
        
        try {
            Funcionario funcionario = funcionarioServico.getFuncionario(identidade);
            fieldNome.setText(funcionario.getNome());
            fieldFuncao.setText(funcionario.getFuncao().name());
        } catch(FuncionarioNaoCadastradoException e) {
            Mensagem.error("Funcionario nao encontrado");
        }
    }
    
    private void resetarCampos() {
        fieldIdentidade.clear();
        fieldNome.clear();
        fieldFuncao.clear();
        fieldNovaSenha.clear();
    }
}