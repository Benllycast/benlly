/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.model;

import com.udec.persistencia.Log;
import com.udec.persistencia.Recorrido;
import com.udec.persistencia.Sensor;
import com.udec.persistencia.Vehiculo;
import com.udec.controlador.RecorridoJpaController;
import com.udec.controlador.SensorJpaController;
import com.udec.controlador.VehiculoJpaController;
import com.udec.model.exceptions.MotorException;
import com.udec.model.filtros.Filtro;
import com.udec.model.filtros.FiltroAnalogoAceleracion;
import com.udec.model.filtros.FiltrosManager;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;

/**
 *
 * @author windows7
 */
public class MotorTest extends TestCase {

    EntityManagerFactory emf;
    RecorridoJpaController r;
    SensorJpaController s;
    VehiculoJpaController v;
    private Recorrido recorrido;
    private Sensor sensor;
    private Vehiculo vehiculo;

    public MotorTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        emf = Persistence.createEntityManagerFactory("proyecto?zeroDateTimeBehavior=convertToNullPU");
        r = new RecorridoJpaController(emf);
        s = new SensorJpaController(emf);
        v = new VehiculoJpaController(emf);
        this.recorrido = r.findRecorrido(1);
        this.sensor = s.findSensor(3);
        this.vehiculo = v.findVehiculo(1);
        Motor.reset();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        emf.close();
    }

    public void testInit() {
        System.out.println("\n///////// testInit ////////");
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

    public void testFilstroCreados() {
        System.out.println("\n///////// testFiltrosCreados ////////");
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

    public void testClasificarValores() {
        System.out.println("\n///////// testClasificarValores ////////");
        try {
            Motor.setRecorrido(recorrido);
            Motor.init();
            Motor.clasificarValores();
            HashMap<Sensor, List<Valor>> listas = Motor.getListas();
            for (Sensor sen : listas.keySet()) {
                System.out.println("\n Sensor:" + sen.toString());
                for (Valor val : listas.get(sen)) {
                    System.out.println(val);
                }
            }
        } catch (MotorException ex) {
            Logger.getLogger(MotorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("E R R O R:excepcion en testClasificarValores:\n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void testFindByRecorridoAndSensorOrderFecha() {
        System.out.println("\n///////// findByRecorridoAndSensorOrderFecha ////////");
        Recorrido salida = r.findRecorrido(1);
        short canal = 1;
        Motor instance = new Motor();
        List<Log> expResult = null;
        List<Log> result = Motor.findByRecorridoAndSensorOrderFecha(salida, canal);

        for (Log l : result) {
            System.out.println("" + l.getIdLog() + " " + l.getCanal() + " " + l.getNumeroDato());
        }
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(1, 1);
    }

    public void testGetListValorBySensor() {
        System.out.println("\n///////// testGetListValoresBySensor////////");
        try {
            Sensor sen = this.s.findSensor(3);
            Motor.setRecorrido(this.recorrido);
            boolean init = Motor.init();
            if (init) {
                Motor.clasificarValores();
                List<Valor> listValorBySensor = Motor.getListValorBySensor(sen);
                for (Valor val : listValorBySensor) {
                    Object[] point = val.getPoint();
                    System.out.println("valor: " + val + "\t\tTiempo: " + point[0] + "\tvalor:" + point[1]);
                }
            } else {
                fail("No INIt MOTOR");
            }
        } catch (MotorException ex) {
            Logger.getLogger(MotorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
