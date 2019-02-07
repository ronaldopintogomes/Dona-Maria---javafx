/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.controle;

import br.com.donamaria.modelo.negocio.persistencia.entidade.Funcionario;

/**
 *
 * @author Ronaldo
 */
public class Sessao {
 
    private static Funcionario funcionario;
    
    public static void setFuncionario(Funcionario parametro) {
        funcionario = parametro;
    }
    
    public static Funcionario getFuncionario() {
        return funcionario;
    }
}