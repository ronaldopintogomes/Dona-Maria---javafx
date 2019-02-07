/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle;

import br.com.donamaria.modelo.negocio.persistencia.entidade.Funcionario;
import br.com.donamaria.modelo.negocio.servico.FuncionarioServico;
import br.com.donamaria.visao.recurso.util.DimensaoDeTela;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Ronaldo
 */
public class MenuFuncionarioControle implements Initializable {
    
    private static Stage primaryStage;
    @FXML private Button btCadastro, btLogon;
    @FXML private Label lbFuncionarioSessao;
    private FuncionarioServico funcionarioServico;
    private Funcionario funcionarioSessao;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        funcionarioServico = new FuncionarioServico();
        funcionarioSessao = Sessao.getFuncionario();
        if(funcionarioSessao != null) {
            lbFuncionarioSessao.setText("Funcionario: "+funcionarioSessao.getNome());
        }
    }
    
    public void display() {
        primaryStage = StageFactory.getStage();
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/donamaria/visao/fxml/MenuFuncionarioFxml.fxml"));
            Scene scene = new Scene(parent, DimensaoDeTela.getWidth(), DimensaoDeTela.getHeight());

            primaryStage.setTitle("Menu Funcionario");
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void cadastro(ActionEvent event) {
        CadastrarFuncionarioControle cadastrarFuncionario = new CadastrarFuncionarioControle();
        cadastrarFuncionario.display();
    }
    
    @FXML
    private void excluir(ActionEvent event) {
        ExcluirFuncionarioControle excluirFuncionario = new ExcluirFuncionarioControle();
        excluirFuncionario.display();
    }
    
    @FXML
    private void atualizar(ActionEvent event) {
        AtualizarSenhaFuncionarioControle atualizarSenha = new AtualizarSenhaFuncionarioControle();
        atualizarSenha.display();
    }
    
    @FXML
    private void listar(ActionEvent event) {
        ListarFuncionariosControle listaDeFuncionarios = new ListarFuncionariosControle();
        listaDeFuncionarios.display();
    }
    
    @FXML
    private void voltar(ActionEvent event) {
        MenuAdministradorControle menuAdministrador = new MenuAdministradorControle();
        menuAdministrador.display();
    }
    
    @FXML
    private void logon(ActionEvent event) {
        Sessao.setFuncionario(null);
        LoginControle login = new LoginControle();
        login.display();
    }
}