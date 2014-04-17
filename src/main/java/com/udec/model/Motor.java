/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model;

import com.udec.benlly.Recorrido;
import com.udec.benlly.Vehiculo;
import com.udec.connection.jpaConnection;
import com.udec.controlador.VehiculoJpaController;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author windows7
 */
public class Motor {
    
    private static final EntityManager em;
    static {
        em = jpaConnection.getEntityManager();
    }
    Recorrido recorrido;
    Vehiculo vehiculo;
    

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }
    
    public void inicializar(){
        VehiculoJpaController con = new VehiculoJpaController(null);
    }
    
    
}
