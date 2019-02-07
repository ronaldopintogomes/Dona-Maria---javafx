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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ronaldo
 */
public class ExcluirFuncionarioControle implements Initializable {
    
    private Stage stage;
    @FXML
    private TableView tabelaFuncionarios;
    @FXML
    private TableColumn colunaIdentidade, colunaNome, colunaFuncao;
    private FuncionarioServico funcionarioServico;
    private ObservableList<Funcionario> dados;
    private List<Funcionario> listaDeFuncionarios;
    
    public ExcluirFuncionarioControle() {
        stage = new Stage();
        funcionarioServico = new FuncionarioServico();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaDeFuncionarios = funcionarioServico.getFuncionarios();
        
        if(listaDeFuncionarios == null) {
            listaDeFuncionarios = new ArrayList<Funcionario>();
        }
        colunaIdentidade.setCellValueFactory(new PropertyValueFactory<>("identidade"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
        dados = FXCollections.observableArrayList(listaDeFuncionarios);
        tabelaFuncionarios.setItems(dados);
    }
    
    public void display() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/donamaria/visao/fxml/ExcluirFuncionarioFxml.fxml"));
            Scene scene = new Scene(parent);
            
            stage.setTitle("Excluir Funcionario");
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
    private void excluir(ActionEvent event) {
        Funcionario funcionarioSessao = Sessao.getFuncionario();
        if(tabelaFuncionarios.getSelectionModel().isEmpty()) {
            Mensagem.alerta("Selecione o funcionario");
        } else {
            Funcionario funcionarioSelecionado = (Funcionario)tabelaFuncionarios.getSelectionModel().getSelectedItem();
            if(funcionarioSelecionado.getIdentidade() == funcionarioSessao.getIdentidade()) {
                Mensagem.error("Funcionario nao pode ser excluido no momento");
            } else {
                try {
                    funcionarioServico.excluir(String.valueOf(funcionarioSelecionado.getIdentidade()));
                    Mensagem.informacao("Operacao realizada com sucesso");
                    
                    listaDeFuncionarios = funcionarioServico.getFuncionarios();
        
                    if(listaDeFuncionarios == null) {
                        listaDeFuncionarios = new ArrayList<Funcionario>();
                    }
                    
                    dados = FXCollections.observableArrayList(listaDeFuncionarios);
                    
                    tabelaFuncionarios.getItems().clear();
                    tabelaFuncionarios.setItems(dados);
                    funcionarioSelecionado = null;
                } catch(FuncionarioNaoCadastradoException e) {
                    
                }
            }
        }
    }
}