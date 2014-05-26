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
import static com.udec.connection.jpaConnection.getEntityManager;
import com.udec.model.exceptions.MotorException;
import com.udec.model.filtros.FiltrosManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 * analiza un recorrido de la base de datos
 *
 * @author windows7
 */
public class Motor {

    
    private static HashMap<Sensor, List<Valor>> listas;
    private static Recorrido recorrido = null;
    private static Vehiculo vehiculo = null;
    private static List<Sensor> sensorList = null;
    private static FiltrosManager manager = null;
    //private static List<Valor> listValor = null;


    /**
     * retorna el recorrido de analisis
     *
     * @return
     */
    public static Recorrido getRecorrido() {
        return Motor.recorrido;
    }

    /**
     * configura el reccorrido de analisis
     *
     * @param recorrido
     */
    public static void setRecorrido(Recorrido recorrido) {
        Motor.recorrido = recorrido;
    }

    public static Vehiculo getVehiculo() {
        return vehiculo;
    }

    public static void setVehiculo(Vehiculo vehiculo) {
        Motor.vehiculo = vehiculo;
    }

    public static FiltrosManager getManager() {
        return manager;
    }

    public static void setManager(FiltrosManager manager) {
        Motor.manager = manager;
    }

    public static List<Sensor> getSensorList() {
        return sensorList;
    }

    public static void setSensorList(List<Sensor> SensorList) {
        Motor.sensorList = SensorList;
    }

    public static HashMap<Sensor, List<Valor>> getListas() {
        return listas;
    }

    public static void setListas(HashMap<Sensor, List<Valor>> listas) {
        Motor.listas = listas;
    }

    public static List<Valor> getListValorBySensor(Sensor sensor) {
        if (Motor.listas != null && !Motor.listas.isEmpty() && Motor.listas.containsKey(sensor)) {
            return Motor.listas.get(sensor);
        }
        return null;
    }

    /**
     * Tareas: - obtiene el vehiculo - prepara el manager de filtros - obtiene
     * la lista de logs de un recorrido
     *
     * @return 
     * @throws com.udec.model.exceptions.MotorException
     */
    public static boolean init() throws MotorException {
        if (Motor.recorrido != null) {
            Motor.listas = new HashMap<>();
            Motor.manager = new FiltrosManager();
            Motor.vehiculo = recorrido.getVehiculoidVehiculo();
            Motor.sensorList = Motor.vehiculo.getSensorList();
            Motor.manager.configurarFiltros(Motor.vehiculo);
            return true;
        } else {
            throw new MotorException("E R R O R: no hay recorrido cofigurado");
        }
    }

    /**
     * Tareas: - convierte todos los Log en sus valores reales
     *
     * @param logList
     * @return List<Valor>
     * @throws com.udec.model.exceptions.MotorException
     */
    public static List<Valor> convertirLogs(List<Log> logList) throws MotorException {
        List<Valor> listValor = new ArrayList<>();
        for (Log linea : logList) {
            try {
                listValor.add(Motor.manager.aplicarFiltro(linea));
            } catch (Exception ex) {
                Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw new MotorException("E R R O R: No se puede convertir Log: " + linea.toString()+" ["+ex.getMessage()+"]");
            }
        }
        return listValor;
    }

    public static void clasificarValores() throws MotorException {
        for (Sensor sensor : Motor.sensorList) {
            List<Log> listLog = Motor.findByRecorridoAndSensorOrderFecha(Motor.recorrido, sensor.getCanal());
            if (!listLog.isEmpty()) {
                try {
                    List<Valor> listValor = Motor.convertirLogs(listLog);
                    Motor.listas.put(sensor, listValor);
                } catch (MotorException ex) {
                    Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, "E R R O R: al convertir los Log a Valores", ex);
                    throw ex;
                }
            }
        }
    }

    public static List<Log> findByRecorridoAndSensorOrderFecha(Object recorrido, Object sensor) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Log.class));
        Query q = getEntityManager().createQuery("SELECT c FROM " + Log.class.getSimpleName() + " c WHERE c.recorridoidRecorrido = :parametro1 and c.canal = :parametro2 ORDER BY c.fecha, c.tiempo, c.numeroDato ASC", Log.class);
        q.setParameter("parametro1", recorrido);
        q.setParameter("parametro2", sensor);
        return q.getResultList();
    }
    
    public static void reset(){
        Motor.listas = null;
        Motor.manager = null;
        Motor.recorrido = null;
        Motor.sensorList = null;
        Motor.vehiculo = null;
    }
}
