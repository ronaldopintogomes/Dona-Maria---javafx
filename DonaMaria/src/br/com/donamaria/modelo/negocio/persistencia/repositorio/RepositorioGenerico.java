/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.persistencia.repositorio;

import br.com.donamaria.modelo.negocio.persistencia.util.UnidadeDePersistencia;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Ronaldo
 * @param <T>
 */
public class RepositorioGenerico<T extends Serializable> {
    
    private Class tipo;
    private EntityManager manager = null;
    
    public RepositorioGenerico() {
        this.tipo = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public void salvar(T t) {
        manager = UnidadeDePersistencia.getManager();
        try {
            manager.getTransaction().begin();
            manager.persist(t);
            manager.getTransaction().commit();
        } catch(Throwable e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }
    
    public void excluir(T t) {
        manager = UnidadeDePersistencia.getManager();
        try {
            manager.getTransaction().begin();
            manager.remove(manager.merge(t));
            manager.getTransaction().commit();
        } catch(Throwable e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }
    
    public void atualizar(T t) {
        manager = UnidadeDePersistencia.getManager();
        
        try {
             manager.getTransaction().begin();
             manager.merge(t);
             manager.getTransaction().commit();
        } catch(Throwable e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    public T get(long id) {
        T object = null;
        manager = UnidadeDePersistencia.getManager();
        
        try{
            object = (T)manager.find(tipo, id);
        } catch(Throwable e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return object;
    };
    
    public List<T> get() {  
        List<T> lista = null;
        manager = UnidadeDePersistencia.getManager();
        try {
            Query query = manager.createQuery("SELECT t FROM "+tipo.getSimpleName()+" t");
            lista = query.getResultList();
        } catch(Throwable e) {
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return lista;
    }
}