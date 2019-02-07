/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.excecao;

import java.io.IOException;

/**
 *
 * @author Ronaldo
 */
public class FuncionarioJaCadastradoException extends IOException {
    
    public FuncionarioJaCadastradoException() {
        super();
    }
    public FuncionarioJaCadastradoException(String mensagem) {
        super(mensagem);
    }
    public FuncionarioJaCadastradoException(Exception exception) {
        super(exception);
    }
    public FuncionarioJaCadastradoException(String mensagem, Exception exception) {
        super(mensagem, exception);
    }
}