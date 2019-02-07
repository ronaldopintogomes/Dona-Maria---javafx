/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.persistencia.repositorio;

import br.com.donamaria.modelo.negocio.persistencia.entidade.Funcionario;
import br.com.donamaria.modelo.negocio.persistencia.util.UnidadeDePersistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ronaldo
 */
public class FuncionarioRepositorio extends RepositorioGenerico<Funcionario> {
    
    public Funcionario getFuncionario(long identidade) throws NullPointerException {
        List<Funcionario> funcionarios = null;
        Funcionario funcionario = null;
        
        EntityManager manager = UnidadeDePersistencia.getManager();
        try {
            Query query = manager.createQuery("SELECT f FROM Funcionario f where f.identidade =:identidade");
            query.setParameter("identidade", identidade);
            funcionarios = (List<Funcionario>)query.getResultList();
            if(funcionarios.isEmpty()) {
                throw new NullPointerException();
            } else {
                funcionario = funcionarios.get(0);
            }
        } finally {
            manager.close();
        }
        return funcionario;
    }
    
    public Funcionario getFuncionario(String nome, String senha) throws NullPointerException {
        List<Funcionario> funcionarios = null;
        Funcionario funcionario = null;
        
        EntityManager manager = UnidadeDePersistencia.getManager();
        try {
            Query query = manager.createQuery("SELECT f FROM Funcionario f where f.nome =:nome and f.senha =:senha");
            query.setParameter("nome", nome);
            query.setParameter("senha", senha);
            funcionarios = (List<Funcionario>)query.getResultList();
            if(funcionarios.isEmpty()) {
                throw new NullPointerException();
            } else {
                funcionario = funcionarios.get(0);
            }
        } finally {
            manager.close();
        }
        return funcionario;
    }
}
