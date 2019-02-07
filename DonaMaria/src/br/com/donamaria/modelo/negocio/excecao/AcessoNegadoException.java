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
public class AcessoNegadoException extends Exception {
    
    public AcessoNegadoException() {
        super();
    }
    public AcessoNegadoException(String mensagem) {
        super(mensagem);
    }
    public AcessoNegadoException(Exception exception) {
        super(exception);
    }
    public AcessoNegadoException(String mensagem, Exception exception) {
        super(mensagem, exception);
    }
}