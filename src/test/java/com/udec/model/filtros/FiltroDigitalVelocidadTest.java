/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.controlador.LogJpaController;
import com.udec.controlador.SensorJpaController;
import com.udec.controlador.VehiculoJpaController;
import com.udec.model.ConfiguracionManager;
import com.udec.persistencia.Log;
import com.udec.persistencia.Sensor;
import com.udec.persistencia.Vehiculo;
import javax.persistence.EntityManagerFactory;
import junit.framework.TestCase;

/**
 *
 * @author windows7
 */
public class FiltroDigitalVelocidadTest extends TestCase {
    
    Vehiculo v;
    Log l;
    Sensor s;
    VehiculoJpaController vc;
    LogJpaController lc;
    SensorJpaController sc;
    
    public FiltroDigitalVelocidadTest(String testName) {
        super(testName);
        EntityManagerFactory emf = ConfiguracionManager.getEntityManagerFactory();
        vc = new VehiculoJpaController(emf);
        lc = new LogJpaController();
        sc = new SensorJpaController(emf);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        v = vc.findVehiculo(1);
        l = lc.findLog(9);
        s = sc.findSensor(5);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetVelocidad() {
        FiltroDigitalVelocidad filtro = new FiltroDigitalVelocidad(v, l, s);
        assertFalse("Velocidad = "+filtro.getVelocidad(), filtro.getVelocidad() == 0.0f);
    }
    
}
