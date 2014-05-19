/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author munifgebarajunior
 */
public class Persistencia {

    
    //Singleton
    private static final Persistencia instancia=new Persistencia();

    public static Persistencia getInstancia() {
        return instancia;
    }
    
    private EntityManagerFactory emf;

    private Persistencia() {
        emf = Persistence.createEntityManagerFactory("UnidadeDePersistencia");
        //PersistenciaUtil.criaUsuarioPadrao();
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
    
    
    
    
    public static void main(String args[]){
        System.out.println("Teste");
    }

}
