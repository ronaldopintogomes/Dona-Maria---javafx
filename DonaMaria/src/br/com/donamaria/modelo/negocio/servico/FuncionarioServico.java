/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.servico;

import br.com.donamaria.modelo.negocio.enumeracao.FuncaoEnum;
import br.com.donamaria.modelo.negocio.enumeracao.SolicitacaoDeAcessoEnum;
import br.com.donamaria.modelo.negocio.excecao.FuncionarioJaCadastradoException;
import br.com.donamaria.modelo.negocio.excecao.FuncionarioNaoCadastradoException;
import br.com.donamaria.modelo.negocio.persistencia.entidade.Funcionario;
import br.com.donamaria.modelo.negocio.persistencia.repositorio.FuncionarioRepositorio;
import br.com.donamaria.modelo.negocio.recurso.util.Criptografia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ronaldo
 */
public class FuncionarioServico {
    
    private FuncionarioRepositorio funcionarioRepositorio;
    
    public FuncionarioServico() {
        funcionarioRepositorio = new FuncionarioRepositorio();
    }
    
    public void salvar(String nome, String identidade, String senha, String funcao) throws FuncionarioJaCadastradoException {
        try {
            Funcionario funcionarioProcurado = funcionarioRepositorio.getFuncionario(Long.parseLong(identidade));
            if(funcionarioProcurado != null) {
                throw new FuncionarioJaCadastradoException();
            }
        } catch(NullPointerException e) {
            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setIdentidade(Integer.parseInt(identidade));
            funcionario.setSenha(Criptografia.encriptar(senha));
            funcionario.setFuncao(FuncaoEnum.valueOf(funcao));

            funcionarioRepositorio.salvar(funcionario);
        }
    }
    
    public void excluir(String identidade) throws FuncionarioNaoCadastradoException {
        Funcionario funcionarioProcurado = funcionarioRepositorio.getFuncionario(Long.parseLong(identidade));
        if(funcionarioProcurado != null) {
            if(funcionarioProcurado.getIdentidade() == Long.parseLong(identidade)) {
                funcionarioRepositorio.excluir(funcionarioProcurado);
            }
        } else {
            throw new FuncionarioNaoCadastradoException();
        }
    }
    
    public void atualizarSenha(String identidade, String senha) {
        Funcionario funcionarioProcurado = funcionarioRepositorio.getFuncionario(Long.parseLong(identidade));
        funcionarioProcurado.setSenha(Criptografia.encriptar(senha));
        funcionarioRepositorio.atualizar(funcionarioProcurado);
    }
    
    public Funcionario getFuncionario(String identidade) throws FuncionarioNaoCadastradoException {
        Funcionario funcionario = null;
        try {
            funcionario = funcionarioRepositorio.getFuncionario(Long.parseLong(identidade));
        } catch(NullPointerException e) {
            throw new FuncionarioNaoCadastradoException();
        }
        
        return funcionario;
    }
    public Funcionario getFuncionario(String nome, String senha) throws FuncionarioNaoCadastradoException {
        Funcionario funcionario = null;
        try {
            funcionario = funcionarioRepositorio.getFuncionario(nome, Criptografia.encriptar(senha));
        } catch(NullPointerException e) {
            throw new FuncionarioNaoCadastradoException();
        }
        
        return funcionario;
    }
    
    public List<Funcionario> getFuncionarios() {
        List<Funcionario> funcionarios = null;
        
        funcionarios = funcionarioRepositorio.get();
        
        if(funcionarios == null || funcionarios.isEmpty()) {
            funcionarios = new ArrayList<Funcionario>();
        } 
        return funcionarios;
    }
    
    public boolean verificarAutorizacao(Funcionario funcionario, SolicitacaoDeAcessoEnum acessoSolicitado) {
        boolean acesso = false;
        
        switch(funcionario.getFuncao()) {
            case ADMINISTRADOR:
                acesso = true;
                break;
            case ESTOQUISTA:
                if(acessoSolicitado == SolicitacaoDeAcessoEnum.MENU_ESTOQUE) {
                    acesso = true;
                }
                break;
            case CAIXA:
                if(acessoSolicitado == SolicitacaoDeAcessoEnum.MENU_CAIXA) {
                    acesso = true;
                }
                break;
            default:
                break;
        }
        
        return acesso;
    }
}