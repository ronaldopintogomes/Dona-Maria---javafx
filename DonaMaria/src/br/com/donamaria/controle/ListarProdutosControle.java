/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle;

import br.com.donamaria.modelo.negocio.persistencia.entidade.Produto;
import br.com.donamaria.modelo.negocio.recurso.util.ProdutoDto;
import br.com.donamaria.modelo.negocio.servico.ProdutoServico;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ListarProdutosControle implements Initializable {
 
    private Stage stage;
    @FXML
    private TableView tabelaProdutos;
    @FXML
    private TableColumn colunaCodigo, colunaDescricao, colunaValor, colunaQuantidade;
    private ProdutoServico produtoServico;
    private ObservableList<ProdutoDto> dados;
    private List<Produto> listaDeProdutos;
    
    public ListarProdutosControle() {
        stage = new Stage();
        produtoServico = new ProdutoServico();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaDeProdutos = produtoServico.getProdutos();
        
        if(listaDeProdutos == null) {
            listaDeProdutos = new ArrayList<Produto>();
        }
        colunaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        dados = FXCollections.observableArrayList(produtoServico.getProdutoDtos(listaDeProdutos));
        tabelaProdutos.setItems(dados);
    }
    
    public void display() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/br/com/donamaria/visao/fxml/ListarProdutosFxml.fxml"));
            Scene scene = new Scene(parent);
            
            stage.setTitle("Lista de Produtos");
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
}