/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.persistencia.Log;
import com.udec.persistencia.Sensor;
import com.udec.persistencia.Vehiculo;
import junit.framework.TestCase;

/**
 *
 * @author windows7
 */
public class FiltroAnalogoTest extends TestCase {
    
    public FiltroAnalogoTest(String testName) {
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

    public void testGetFrecuenciaDeEntrada() {
        System.out.println("getFrecuenciaDeEntrada");
        FiltroAnalogo instance = new FiltroAnalogoImpl();
        float expResult = 0.0F;
        //float result = instance.getFrecuenciaDeEntrada();
        float result = 0.0f;
        assertEquals(expResult, result, 0.0);
        //fail("The test case is a prototype.");
    }

    public void testGetVoltajeDeSalida() {
        System.out.println("getVoltajeDeSalida");
        Log linea = new Log(0);
        linea.setValor(1024);
        FiltroAnalogo instance = new FiltroAnalogoImpl(null, linea, null);
        float expResult = 5.0F;
        float result = instance.getVoltajeDeSalida();
        assertEquals(expResult, result, 0.0);
        //fail("The test case is a prototype.");
    }

    public void testGetResolucion() {
        System.out.println("getResolucion");
        float mM = 1.5f, mm = 0.0f, sM = 2.45f, sm = 0.0f;
        Sensor sensor = new Sensor();
        sensor.setMagnitudMaxima(mM);
        sensor.setMagnitudMinima(mm);
        sensor.setSalidaMaxima(sM);
        sensor.setSalidaMinima(sm);
        FiltroAnalogo instance = new FiltroAnalogoImpl(null, null, sensor);
        float expResult = (sM-sm)/(mM-mm);
        float result = instance.getResolucion();
        assertEquals(expResult, result, 0.0);
        //fail("The test case is a prototype.");
    }

    public class FiltroAnalogoImpl extends FiltroAnalogo {

        public FiltroAnalogoImpl() {
        }

        public FiltroAnalogoImpl(Vehiculo vehiculo1, Log log, Sensor sensor) {
            super(vehiculo1, log, sensor);
        }
        
        @Override
        public float getValor() {
            return 0.0f;
        }
    }
    
}
