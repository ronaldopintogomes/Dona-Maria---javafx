/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.persistencia.entidade;

import br.com.donamaria.modelo.negocio.enumeracao.FuncaoEnum;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Ronaldo
 */
@Entity
public class Funcionario implements Serializable {
    
    @Id
    @GeneratedValue
    private long id;
    private long identidade;
    private String nome;
    private String senha;
    @Enumerated(EnumType.STRING)
    private FuncaoEnum funcao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdentidade() {
        return identidade;
    }

    public void setIdentidade(long identidade) {
        this.identidade = identidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public FuncaoEnum getFuncao() {
        return funcao;
    }

    public void setFuncao(FuncaoEnum funcao) {
        this.funcao = funcao;
    }
}