/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model;

import java.util.ResourceBundle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author windows7
 */
public final class ConfiguracionManager {
    public static final short CANAL_ACC_1 = 0;
    public static final short CANAL_ACC_2 = 1;
    public static final short CANAL_ACC_3 = 2;
    public static final short CANAL_AD_VEL = 3;
    public static final short CANAL_AD_RPM = 4;
    public static final short CANAL_DIG_VEL = 5;
    public static final short CANAL_DIG_RPM = 6;
    public static final String CONEXION = "proyecto?zeroDateTimeBehavior=convertToNullPU";
    private static final ResourceBundle bundle = ResourceBundle.getBundle("config/config");
    
    private static EntityManagerFactory emf = null;

    public static void createEntityManagerFactory() {

        try {
            emf = Persistence.createEntityManagerFactory(ConfiguracionManager.CONEXION);
            // System.out.println("N O T A: EMF CREADO");
        } catch (Exception e) {
            System.err.println("E R R O R: ERROR AL CREAR EMF");
        }

    }

    public static EntityManager getEntityManager() {
        EntityManager em = null;
        if (emf == null) {
            createEntityManagerFactory();
        }
        try {
            em = emf.createEntityManager();
            // System.out.println("N O T A: EM CREADO");
        } catch (Exception e) {
            System.err.println("E R R O R:  ERROR AL CREAR EM");
        }
        return em;
    }
    
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            createEntityManagerFactory();
        }
        return emf;
    }
    
    public static String getString(String s){
        return ConfiguracionManager.bundle.getString(s);
    }
    
    public static String getTitleForGrafic(Short canal){
        switch(canal){
            case ConfiguracionManager.CANAL_ACC_1:
            case ConfiguracionManager.CANAL_ACC_2:
            case ConfiguracionManager.CANAL_ACC_3:
                return ConfiguracionManager.bundle.getString("com.config.graficos.titulo.canalAceleracion");
            case ConfiguracionManager.CANAL_AD_VEL:
                return ConfiguracionManager.bundle.getString("com.config.graficos.titulo.canalVelocidad");
            case ConfiguracionManager.CANAL_AD_RPM:
                return ConfiguracionManager.bundle.getString("com.config.graficos.titulo.canalRevoluciones");
            case ConfiguracionManager.CANAL_DIG_VEL:
                return ConfiguracionManager.bundle.getString("com.config.graficos.titulo.canalVelocidad");
            case ConfiguracionManager.CANAL_DIG_RPM:
                return ConfiguracionManager.bundle.getString("com.config.graficos.titulo.canalRevoluciones");
            default:
                return null;
        }
    }
}
