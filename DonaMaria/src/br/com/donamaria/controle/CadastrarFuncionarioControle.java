/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle;

import br.com.donamaria.controle.recurso.Mensagem;
import br.com.donamaria.modelo.negocio.enumeracao.FuncaoEnum;
import br.com.donamaria.modelo.negocio.excecao.FuncionarioJaCadastradoException;
import br.com.donamaria.modelo.negocio.servico.FuncionarioServico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ronaldo
 */
public class CadastrarFuncionarioControle implements Initializable {
    
    private Stage stage;
    @FXML
    private TextField fieldNome, fieldIdentidade;
    @FXML
    private PasswordField fieldSenha;
    @FXML
    private ComboBox comboFuncao;
    private FuncionarioServico funcionarioServico;
    
    public CadastrarFuncionarioControle() {
        stage = new Stage();
        funcionarioServico = new FuncionarioServico();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> funcoes = FXCollections.observableArrayList(FuncaoEnum.ADMINISTRADOR.name(), FuncaoEnum.ESTOQUISTA.name(), FuncaoEnum.CAIXA.name());
        comboFuncao.setItems(funcoes);
    }
    
    public void display() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/donamaria/visao/fxml/CadastrarFuncionarioFxml.fxml"));
            Scene scene = new Scene(parent);
            
            stage.setTitle("Cadastrar Funcionario");
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
        String nome = fieldNome.getText();
        String identidade = fieldIdentidade.getText();
        String senha = fieldSenha.getText();
        String funcao = comboFuncao.getSelectionModel().getSelectedItem().toString();
        
        if(nome.isEmpty() || identidade.isEmpty() || senha.isEmpty() || funcao.equals("Selecione")) {
            Mensagem.alerta("Todos os campos sao obrigatorios");
        } else {
            try {
                funcionarioServico.salvar(nome, identidade, senha, funcao);
                limparCampos();
                Mensagem.informacao("Operacao realizada com sucesso");
            } catch(FuncionarioJaCadastradoException e) {
                Mensagem.error("Funcionario ja cadastrado no sistema");
            }
        }
    }
    
    private void limparCampos() {
        fieldNome.clear();
        fieldIdentidade.clear();
        fieldSenha.clear();
        comboFuncao.getSelectionModel().clearSelection();
    }
}