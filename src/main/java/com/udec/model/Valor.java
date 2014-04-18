/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model;

import com.udec.benlly.Log;
import com.udec.benlly.Sensor;
import com.udec.benlly.Vehiculo;
import com.udec.model.filtros.Filtro;

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

    
}
