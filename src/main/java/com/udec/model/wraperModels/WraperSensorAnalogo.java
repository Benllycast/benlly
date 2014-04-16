/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.model.wraperModels;

import com.udec.benlly.Sensor;

/**
 *
 * @author windows7
 */
public class WraperSensorAnalogo extends WraperSensor {

    private float magnitudMaxima = 0.0f;
    private float magnitudMinima = 0.0f;    
    private float voltajeMaximoDeSalida = 0.0f;
    private float voltajeMinimoDeSalida = 0.0f;

    public float getVoltajeMinimoDeSalida() {
        return voltajeMinimoDeSalida;
    }

    public void setVoltajeMinimoDeSalida(float voltajeMinimoDeSalida) {
        this.voltajeMinimoDeSalida = voltajeMinimoDeSalida;
    }


    public float getVoltajeMaximoDeSalida() {
        return voltajeMaximoDeSalida;
    }

    public void setVoltajeMaximoDeSalida(float voltajeMaximoDeSalida) {
        this.voltajeMaximoDeSalida = voltajeMaximoDeSalida;
    }


    public float getMagnitudMinima() {
        return magnitudMinima;
    }

    public void setMagnitudMinima(float magnitudMinima) {
        this.magnitudMinima = magnitudMinima;
    }

    public float getMagnitudMaxima() {
        return magnitudMaxima;
    }

    public void setMagnitudMaxima(float magnitudMaxima) {
        this.magnitudMaxima = magnitudMaxima;
    }

    public WraperSensorAnalogo() {
    }

    public WraperSensorAnalogo(Integer idsensor) {
        super(idsensor);
    }

    public WraperSensorAnalogo(Sensor sensor) {
        super(sensor);
    }

    public float getResolucion() {
        return (this.getVoltajeMaximoDeSalida()-this.getVoltajeMinimoDeSalida())/
                (this.getMagnitudMaxima()-this.getMagnitudMinima());
    }

}
