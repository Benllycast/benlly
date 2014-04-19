/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.benlly.Log;
import com.udec.benlly.Recorrido;
import com.udec.benlly.Sensor;
import com.udec.benlly.Vehiculo;
import com.udec.controlador.LogJpaController;
import com.udec.controlador.RecorridoJpaController;
import com.udec.controlador.SensorJpaController;
import com.udec.controlador.VehiculoJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;

/**
 *
 * @author windows7
 */
public class FiltroAnalogoRPMTest extends TestCase {
    
    RecorridoJpaController r;
    SensorJpaController s;
    VehiculoJpaController v;
    LogJpaController l;
    private Recorrido recorrido;
    private Sensor sensor;
    private Vehiculo vehiculo;
    private Log log;
    
    public FiltroAnalogoRPMTest(String testName) {
        super(testName);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto?zeroDateTimeBehavior=convertToNullPU");
        r = new RecorridoJpaController(emf);
        s = new SensorJpaController(emf);
        v = new VehiculoJpaController(emf);
        l = new LogJpaController();
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetRPM() {
        System.out.println("getRPM");
        FiltroAnalogoRPM instance = new FiltroAnalogoRPM();
        float expResult = 0.0F;
        float result = instance.getRPM();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public void testGetFrecuenciaDeGiro() {
        System.out.println("getFrecuenciaDeGiro");
        FiltroAnalogoRPM instance = new FiltroAnalogoRPM();
        float expResult = 0.0F;
        float result = instance.getFrecuenciaDeGiro();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public void testGetValor() {
        System.out.println("getValor");
        FiltroAnalogoRPM instance = new FiltroAnalogoRPM();
        float expResult = 0.0F;
        float result = instance.getValor();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
