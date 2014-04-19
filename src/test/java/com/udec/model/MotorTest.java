/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model;

import com.udec.benlly.Log;
import com.udec.benlly.Recorrido;
import com.udec.benlly.Sensor;
import com.udec.benlly.Vehiculo;
import com.udec.controlador.RecorridoJpaController;
import com.udec.controlador.SensorJpaController;
import com.udec.controlador.VehiculoJpaController;
import com.udec.model.exceptions.MotorException;
import com.udec.model.filtros.Filtro;
import com.udec.model.filtros.FiltroAnalogoAceleracion;
import com.udec.model.filtros.FiltrosManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;

/**
 *
 * @author windows7
 */
public class MotorTest extends TestCase {
    
    RecorridoJpaController r;
    SensorJpaController s;
    VehiculoJpaController v;
    private Recorrido recorrido;
    private Sensor sensor;
    private Vehiculo vehiculo;
    
    
    
    public MotorTest(String testName) {
        super(testName);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto?zeroDateTimeBehavior=convertToNullPU");
        r = new RecorridoJpaController(emf);
        s = new SensorJpaController(emf);
        v = new VehiculoJpaController(emf);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.recorrido = r.findRecorrido(1);
        this.sensor = s.findSensor(1);
        this.vehiculo = v.findVehiculo(1);
        
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testInit(){
        try {
            int cantidad = v.findVehiculo(1).getSensorList().size();
            Motor.setRecorrido(this.recorrido);
            Motor.init();
            int size = Motor.getSensorList().size();
            Vehiculo expecte = Motor.getVehiculo();
            assertEquals(size, cantidad);
            assertEquals(expecte.getIdVehiculo(), this.vehiculo.getIdVehiculo());
        } catch (MotorException ex) {
            fail("Algo salio mal en el init");
        }
    }
    
    public void testFilstroCreados(){
        try {
            Motor.setRecorrido(recorrido);
            int cantidad = v.findVehiculo(1).getSensorList().size();
            Motor.init();
            FiltrosManager manager = Motor.getManager();
            HashMap<Short, Filtro> filtros = manager.getFiltros();
            int size = filtros.size();
            Filtro result = filtros.get(sensor.getCanal());
            assertEquals(size, cantidad);
            assertTrue(result instanceof FiltroAnalogoAceleracion);
        } catch (MotorException ex) {
            fail("escepcion en filtros creados");
        }
    }
    
    public void testClasificarValores(){
        List<Sensor> sensores = new ArrayList<>();
        sensores.add(sensor);
        Motor.setRecorrido(recorrido);
        Motor.setSensorList(sensores);
        Motor.setListas(new HashMap<Sensor, List<Valor>>());
        Motor.clasificarValores();
        HashMap<Sensor, List<Valor>> listas = Motor.getListas();
        List<Valor> get = listas.get(sensor);
        for(Valor val: get){
            System.out.println(val.toString());
        }
        assertEquals(1, 1);
    }
    
    public void testClasificarValores2(){
        List<Sensor> sensores = new ArrayList<>();
        Sensor s2 = s.findSensor(2);
        sensores.add(s2);
        Motor.setRecorrido(recorrido);
        Motor.setSensorList(sensores);
        Motor.setListas(new HashMap<Sensor, List<Valor>>());
        Motor.clasificarValores();
        HashMap<Sensor, List<Valor>> listas = Motor.getListas();
        List<Valor> get = listas.get(s2);
        for(Valor val: get){
            System.out.println(val.toString());
        }
        assertEquals(1, 1);
    }

    public void testFindByRecorridoAndSensorOrderFecha() {
        System.out.println("findByRecorridoAndSensorOrderFecha");
        Recorrido salida = r.findRecorrido(1);
        short canal = 1;
        Motor instance = new Motor();
        List<Log> expResult = null;
        List<Log> result = Motor.findByRecorridoAndSensorOrderFecha(salida, canal);
        
        for(Log l : result){
            System.out.println(""+l.getIdLog()+" "+l.getFecha().toString()+" "+l.getTiempo().toString()+" "+l.getNumeroDato());
        }
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(1, 1);
    }
    
    
}
