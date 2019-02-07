/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle;

import br.com.donamaria.controle.recurso.Mensagem;
import br.com.donamaria.modelo.negocio.enumeracao.SolicitacaoDeAcessoEnum;
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
public class MenuInicialControle implements Initializable {
    
    private static Stage primaryStage;
    @FXML private Button btAdministrador, btEstoque, btCaixa, btLogon;
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
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/donamaria/visao/fxml/MenuInicialFxml.fxml"));
            Scene scene = new Scene(parent, DimensaoDeTela.getWidth(), DimensaoDeTela.getHeight());

            primaryStage.setTitle("Menu Inicial");
            primaryStage.centerOnScreen();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void verificarAutorizacao(ActionEvent event) {
        Button bt = (Button)event.getSource();
        if(bt.getId().equals("btAdministrador")) {
            if(funcionarioServico.verificarAutorizacao(funcionarioSessao, SolicitacaoDeAcessoEnum.MENU_ADMINISTRADOR)) {
                MenuAdministradorControle menuAdministrador = new MenuAdministradorControle();
                menuAdministrador.display();
            } else {
                Mensagem.error("Acesso negado");
            }
        } else if(bt.getId().equals("btEstoque")) {
            if(funcionarioServico.verificarAutorizacao(funcionarioSessao, SolicitacaoDeAcessoEnum.MENU_ESTOQUE)) {
                MenuEstoqueControle menuEstoque = new MenuEstoqueControle();
                menuEstoque.display();
            } else {
                Mensagem.error("Acesso negado");
            }
        } else if(bt.getId().equals("btCaixa")) {
            if(funcionarioServico.verificarAutorizacao(funcionarioSessao, SolicitacaoDeAcessoEnum.MENU_CAIXA)) {
                    MenuCaixaControle menuCaixa = new MenuCaixaControle();
                    menuCaixa.display();
            } else {
                Mensagem.error("Acesso negado");
            }
        }
    }
    
    @FXML
    private void logon(ActionEvent event) {
        Sessao.setFuncionario(null);
        LoginControle login = new LoginControle();
        login.display();
    }
}