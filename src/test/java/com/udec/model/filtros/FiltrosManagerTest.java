/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.benlly.Log;
import com.udec.benlly.Sensor;
import junit.framework.TestCase;

/**
 *
 * @author windows7
 */
public class FiltrosManagerTest extends TestCase {
    
    public FiltrosManagerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetFiltroAcceleracion() {
        System.out.println("getFiltro aceleracion");
        Sensor linea = new Sensor();
        
        FiltrosManager instance = new FiltrosManager();
        Filtro expResult = null;
        linea.setCanal((short)0);
        Filtro result = instance.crearFiltro(linea);
        assertTrue(result instanceof FiltroAnalogoAceleracion);
        linea.setCanal((short)1);
        result = instance.crearFiltro(linea);
        assertTrue(result instanceof FiltroAnalogoAceleracion);
        linea.setCanal((short)2);
        result = instance.crearFiltro(linea);
        assertTrue(result instanceof FiltroAnalogoAceleracion);
    }
    public void testGetFiltroAnalogoVelocidad() {
        System.out.println("getFiltro Analogo Velocidad");
        Sensor linea = new Sensor();
        linea.setCanal((short)3);
        FiltrosManager instance = new FiltrosManager();
        Filtro result = instance.crearFiltro(linea);
        assertTrue(result instanceof FiltroAnalogoVelocidad);
    }
    public void testGetFiltroAnalogoRPM() {
        System.out.println("getFiltro Analogo RPM");
        Sensor linea = new Sensor();
        linea.setCanal((short)4);
        FiltrosManager instance = new FiltrosManager();
        Filtro result = instance.crearFiltro(linea);
        assertTrue(result instanceof FiltroAnalogoRPM);
    }
    public void testGetFiltroDigitalVelocidad() {
        System.out.println("getFiltro Digital Velocidad");
        Sensor linea = new Sensor();
        linea.setCanal((short)5);
        FiltrosManager instance = new FiltrosManager();
        Filtro result = instance.crearFiltro(linea);
        assertTrue(result instanceof FiltroDigitalVelocidad);
    }
    public void testGetFiltroDigitalRPM() {
        System.out.println("getFiltro Digital RPM");
        Sensor linea = new Sensor();
        linea.setCanal((short)6);
        FiltrosManager instance = new FiltrosManager();
        Filtro result = instance.crearFiltro(linea);
        assertTrue(result instanceof FiltroDigitalRPM);
    }
    
}
