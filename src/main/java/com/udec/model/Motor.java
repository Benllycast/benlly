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
import static com.udec.connection.jpaConnection.getEntityManager;
import com.udec.model.exceptions.MotorException;
import com.udec.model.filtros.FiltrosManager;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * analiza un recorrido de la base de datos
 * @author windows7
 */
public class Motor {
    private static final EntityManagerFactory em;
    private static HashMap<Sensor, List<Valor>> listas;
    private static Recorrido recorrido = null;
    private static Vehiculo vehiculo = null;
    private static List<Sensor> sensorList = null;
    private static FiltrosManager manager = null;
    private static List<Valor> listValor = null;
    static {
        em = Persistence.createEntityManagerFactory("proyecto?zeroDateTimeBehavior=convertToNullPU");
    }


    /**
     * retorna el recorrido de analisis
     * @return
     */
    public static Recorrido getRecorrido() {
        return Motor.recorrido;
    }

    /**
     *  configura el reccorrido de analisis
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

    public static List<Valor> getListValor() {
        return listValor;
    }

    public static void setListValor(List<Valor> listValor) {
        Motor.listValor = listValor;
    }

    public static HashMap<Sensor, List<Valor>> getListas() {
        return listas;
    }

    public static void setListas(HashMap<Sensor, List<Valor>> listas) {
        Motor.listas = listas;
    }
    
    
    
    /**
     * Tareas:
     * - obtiene el vehiculo
     * - prepara el manager de filtros
     * - obtiene la lista de logs de un recorrido
     * @throws com.udec.model.exceptions.MotorException
     */
    public static boolean init() throws MotorException{
        if (Motor.recorrido != null) {
            Motor.manager = new FiltrosManager();
            Motor.vehiculo = recorrido.getVehiculoidVehiculo();
            Motor.sensorList = Motor.vehiculo.getSensorList();
            Motor.manager.configurarFiltros(Motor.vehiculo);
            return true;
        } else {
            throw  new MotorException("E R R O R: no hay recorrido cofigurado");
        }
    }
    
    /**
     * Tareas:
     * - convierte todos los Log en sus valores reales
     * @param logList
     * @return List<Valor>
     * @throws com.udec.model.exceptions.MotorException
     */
    public static List<Valor> convertirLogs(List<Log> logList) throws MotorException{
        for(Log linea : logList){
            try {
                Motor.manager.aplicarFiltro(linea);
            } catch (Exception ex) {
                Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                throw  new MotorException("E R R O R: No se puede convertir Log: "+linea.toString());
            }
        }
        return Motor.manager.getListaValores();
    }
    
    public static void clasificarValores(){
        List<Log> listLog;
        List<Valor> listValor;
        for(Sensor sensor: Motor.sensorList){
            listLog = Motor.findByRecorridoAndSensorOrderFecha(Motor.recorrido, sensor.getCanal());
            if(!listLog.isEmpty()){
                try {
                    listValor = Motor.convertirLogs(listLog);
                    Motor.listas.put(sensor, listValor);
                } catch (MotorException ex) {
                    Logger.getLogger(Motor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    public static List<Log> findByRecorridoAndSensorOrderFecha(Object recorrido, Object sensor) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Log.class));
        Query q = getEntityManager().createQuery("SELECT c FROM " + Log.class.getSimpleName() + " c WHERE c.recorridoidRecorrido = :parametro1 and c.canal = :parametro2 ORDER BY c.fecha, c.tiempo, c.numeroDato ASC" , Log.class);
        q.setParameter("parametro1", recorrido);
        q.setParameter("parametro2", sensor);
        return q.getResultList();
    }
}
