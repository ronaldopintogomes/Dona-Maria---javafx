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
import br.com.donamaria.visao.recurso.util.DimensaoDeTela;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ronaldo
 */
public class LoginControle {
    
    private static Stage primaryStage;
    @FXML private TextField fieldNome;
    @FXML private PasswordField fieldSenha;
    @FXML private Button btLogin;
    private FuncionarioServico funcionarioServico;
    
    public LoginControle() {
        funcionarioServico = new FuncionarioServico();
    }
    
    public void display() {
        primaryStage = StageFactory.getStage();
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/donamaria/visao/fxml/LoginFxml.fxml"));
            Scene scene = new Scene(parent, DimensaoDeTela.getWidth(), DimensaoDeTela.getHeight());

            primaryStage.setTitle("Login");
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void login(ActionEvent event) {
        String nome = fieldNome.getText();
        String senha = fieldSenha.getText();
        
        if(nome.isEmpty() || senha.isEmpty()) {
            Mensagem.alerta("Digite seu nome e senha");
        } else {
            try {
                Funcionario funcionario = funcionarioServico.getFuncionario(nome, senha);
                if(funcionario != null) {
                    Sessao.setFuncionario(funcionario);
                    MenuInicialControle menuInicial = new MenuInicialControle();
                    menuInicial.display();
                }
            } catch(FuncionarioNaoCadastradoException e) {
                fieldNome.clear();
                fieldSenha.clear();
                Mensagem.error("Funcionario nao cadastrado no sistema");
            }
        }
    }
}
