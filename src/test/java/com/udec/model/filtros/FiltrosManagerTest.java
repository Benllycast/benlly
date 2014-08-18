/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.model.ConfiguracionManager;
import com.udec.persistencia.Log;
import com.udec.persistencia.Sensor;
import javax.persistence.EntityManager;
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
        Sensor sensor = new Sensor();
        
        FiltrosManager instance = new FiltrosManager();
        Filtro expResult = null;
        sensor.setCanal((short)0);
        Filtro result = instance.crearFiltro(sensor);
        assertTrue(result instanceof FiltroAnalogoAceleracion);
        sensor.setCanal((short)1);
        result = instance.crearFiltro(sensor);
        assertTrue(result instanceof FiltroAnalogoAceleracion);
        sensor.setCanal((short)2);
        result = instance.crearFiltro(sensor);
        assertTrue(result instanceof FiltroAnalogoAceleracion);
    }
    public void testGetFiltroAnalogoVelocidad() {
        System.out.println("getFiltro Analogo Velocidad");
        Sensor sensor = new Sensor();
        sensor.setCanal((short)3);
        FiltrosManager instance = new FiltrosManager();
        Filtro result = instance.crearFiltro(sensor);
        assertTrue(result instanceof FiltroAnalogoVelocidad);
    }
    public void testGetFiltroAnalogoRPM() {
        System.out.println("getFiltro Analogo RPM");
        Sensor sensor = new Sensor();
        sensor.setCanal((short)4);
        FiltrosManager instance = new FiltrosManager();
        Filtro result = instance.crearFiltro(sensor);
        assertTrue(result instanceof FiltroAnalogoRPM);
    }
    public void testGetFiltroDigitalVelocidad() {
        System.out.println("getFiltro Digital Velocidad");
        Sensor sensor = new Sensor();
        sensor.setCanal((short)5);
        FiltrosManager instance = new FiltrosManager();
        Filtro result = instance.crearFiltro(sensor);
        assertTrue(result instanceof FiltroDigitalVelocidad);
    }
    public void testGetFiltroDigitalRPM() {
        System.out.println("getFiltro Digital RPM");
        Sensor sensor = new Sensor();
        sensor.setCanal((short)6);
        FiltrosManager fm = new FiltrosManager();
        Filtro result = fm.crearFiltro(sensor);
        assertTrue(result instanceof FiltroDigitalRPM);
    }
    
}
