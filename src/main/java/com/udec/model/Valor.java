/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model;

import com.udec.persistencia.Log;
import com.udec.persistencia.Sensor;
import com.udec.persistencia.Vehiculo;
import com.udec.model.filtros.Filtro;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author windows7
 */
public class Valor {

    private float valorReal = 0.0f;
    private Vehiculo vehiculo;
    private Sensor sensor;
    private Log log;

    public Valor() {
    }

    public Valor(Filtro filtro) {
        this.valorReal = filtro.getValor();
        this.vehiculo = filtro.getVehiculo();
        this.sensor = filtro.getSensor();
        this.log = filtro.getLog();
    }
    
    

    public float getValorReal() {
        return valorReal;
    }

    public void setValorReal(float valorReal) {
        this.valorReal = valorReal;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    @Override
    public String toString() {
        SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat fecha = new SimpleDateFormat("yy/MM/dd");
        return "["+
                this.log.getIdLog()+
                "-"+fecha.format(this.log.getFecha())
                +"-"+hora.format(this.log.getTiempo())
                +"-"+this.getLog().getCanal()
                +"-"+this.getValorReal()
                +" ("+log.getValor()+")"
                +"]";
    }
    
    public Object[] getPoint(){
        Object [] point = new Object[2];
        Date fecha = this.log.getFecha();
        Date tiempo = this.log.getTiempo();
        Calendar calFecha = Calendar.getInstance();
        Calendar calHora = Calendar.getInstance();
        calFecha.setTime(fecha);
        calHora.setTime(tiempo);
        calFecha.set(Calendar.HOUR_OF_DAY, calHora.get(Calendar.HOUR_OF_DAY));
        calFecha.set(Calendar.MINUTE, calHora.get(Calendar.MINUTE));
        calFecha.set(Calendar.SECOND, calHora.get(Calendar.SECOND));
        calFecha.set(Calendar.MILLISECOND, log.getNumeroDato());
        point[0] = calFecha.getTime();
        point[1] = this.getValorReal();
        return point;
    }
    
}
