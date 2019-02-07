/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.donamaria.modelo.negocio.persistencia.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ronaldo
 */
public class UnidadeDePersistencia {
 
    private static final EntityManagerFactory entityManagerFactory;
    
    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("DonaMaria");
    }
    
    public static void init() {};
    
    public static EntityManager getManager() {
        return entityManagerFactory.createEntityManager();
    }
}