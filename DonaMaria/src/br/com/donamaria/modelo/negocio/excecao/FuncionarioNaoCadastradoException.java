/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.excecao;

/**
 *
 * @author Ronaldo
 */
public class FuncionarioNaoCadastradoException extends Exception {
    
    public FuncionarioNaoCadastradoException() {
        super();
    }
    public FuncionarioNaoCadastradoException(String mensagem) {
        super(mensagem);
    }
    public FuncionarioNaoCadastradoException(Exception exception) {
        super(exception);
    }
    public FuncionarioNaoCadastradoException(String mensagem, Exception exception) {
        super(mensagem, exception);
    }
}