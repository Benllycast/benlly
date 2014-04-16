/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.model.filtros;

import com.udec.benlly.Log;
import com.udec.benlly.Sensor;
import com.udec.benlly.Vehiculo;
import com.udec.model.Valor;
import com.udec.model.wraperModels.LineaLog;
import com.udec.model.wraperModels.WraperSensor;
import com.udec.model.wraperModels.WraperVehiculo;

/**
 *
 * @author windows7
 */
public abstract class Filtro {

    public static final float MINUTO = 60.0f;
    private LineaLog lineaLog = null;
    private WraperVehiculo vehiculo = null;
    private WraperSensor sensor = null;

    public Filtro() {
    }

    public Filtro(Vehiculo vehiculo1, Log log, Sensor sensor){
        this.vehiculo = (WraperVehiculo) vehiculo1;
        this.lineaLog = (LineaLog) log;
        this.sensor = (WraperSensor) sensor;
    }
    
    public WraperSensor getSensor() {
        return sensor;
    }

    public void setSensor(WraperSensor sensor) {
        this.sensor = sensor;
    }

    public WraperVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(WraperVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LineaLog getLineaLog() {
        return lineaLog;
    }

    public void setLineaLog(LineaLog lineaLog) {
        this.lineaLog = lineaLog;
    }
    
    public abstract float getValor();
}
