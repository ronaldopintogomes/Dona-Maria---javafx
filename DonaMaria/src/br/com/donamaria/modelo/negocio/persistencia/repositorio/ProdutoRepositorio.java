/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.persistencia.repositorio;

import br.com.donamaria.modelo.negocio.persistencia.entidade.Produto;
import br.com.donamaria.modelo.negocio.persistencia.util.UnidadeDePersistencia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ronaldo
 */
public class ProdutoRepositorio extends RepositorioGenerico<Produto> {
    
    public List<Produto> getProdutos(long codigo) throws NullPointerException {
        List<Produto> produtos = null;
        
        EntityManager manager = UnidadeDePersistencia.getManager();
        try {
            Query query = manager.createQuery("SELECT p FROM Produto p where p.codigo =:codigo");
            query.setParameter("codigo", codigo);
            produtos = (List<Produto>)query.getResultList();
            if(produtos.isEmpty()) {
                throw new NullPointerException();
            }
        } finally {
            manager.close();
        }
        return produtos;
    }
}