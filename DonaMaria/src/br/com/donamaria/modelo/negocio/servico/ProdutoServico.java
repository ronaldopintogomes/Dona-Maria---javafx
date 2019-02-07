/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.servico;

import br.com.donamaria.modelo.negocio.persistencia.entidade.Produto;
import br.com.donamaria.modelo.negocio.persistencia.repositorio.ProdutoRepositorio;
import br.com.donamaria.modelo.negocio.recurso.util.ProdutoDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ronaldo
 */
public class ProdutoServico {
    
    private ProdutoRepositorio produtoRepositorio;
    
    public ProdutoServico() {
        produtoRepositorio = new ProdutoRepositorio();
    }
 
    public void cadastrar(String descricao, String valor, String codigo, String quantidade) {
        for(int i = 0; i < Integer.parseInt(quantidade); i++) {
            Produto produto = new Produto();
            produto.setDescricao(descricao);
            produto.setValor(new BigDecimal(valor));
            produto.setCodigo(Long.parseLong(codigo.trim()));
            produtoRepositorio.salvar(produto);
        }
    }
    
    public void excluir(String codigo, String quantidade) {
        try {
            List<Produto> lista = produtoRepositorio.getProdutos(Long.parseLong(codigo));
            
            if(Integer.parseInt(quantidade) > lista.size()) {
                throw new IllegalArgumentException("Quantidade a ser excluida e maior que a cadastrada");
            } else {
                for(int i = 0; i < Integer.parseInt(quantidade); i++) {
                    produtoRepositorio.excluir(lista.get(i));
                }
            }
        } catch(NullPointerException e) {
            
        }
    }
    
    public void atualizar(String descricao, String codigo, String valor) {
        List<Produto> lista = produtoRepositorio.getProdutos(Long.parseLong(codigo));
        
        for(Produto p : lista) {
            p.setDescricao(descricao);
            p.setValor(BigDecimal.valueOf(Double.parseDouble(valor)));
            produtoRepositorio.atualizar(p);
        }
    }
    
    public List<Produto> getProdutos(String codigo) throws NullPointerException {
        List<Produto> produtos = null;
        
        produtos = produtoRepositorio.getProdutos(Long.parseLong(codigo));
        return produtos;
    }
    
    public List<Produto> getProdutos() {
        List<Produto> produtos = null;
        
        produtos = produtoRepositorio.get();
        
        if(produtos == null || produtos.isEmpty()) {
            produtos = new ArrayList<Produto>();
        } 
        return produtos;
    }
    
    public List<ProdutoDto> getProdutoDtos(List<Produto> listaProduto) {
        List<ProdutoDto> listaDto = new ArrayList<ProdutoDto>();
        Set<Produto> set = new HashSet<Produto>(listaProduto);
        
        for(Produto p : set) {
            int quantidade = 0;
            for(Produto pr : listaProduto) {
                if(p.getCodigo() == pr.getCodigo()) {
                    quantidade++;
                }
            }
            ProdutoDto produtoDto = new ProdutoDto();
            produtoDto.setDescricao(p.getDescricao());
            produtoDto.setValor(p.getValor());
            produtoDto.setCodigo(p.getCodigo());
            produtoDto.setQuantidade(quantidade);
            listaDto.add(produtoDto);
        }
        return listaDto;
    }
}