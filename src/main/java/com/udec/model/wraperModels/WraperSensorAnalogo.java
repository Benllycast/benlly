/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.model.wraperModels;

import com.udec.benlly.Sensor;
import com.udec.benlly.Sensoranalogo;

/**
 *
 * @author windows7
 */
public class WraperSensorAnalogo extends Sensor {

    private Sensoranalogo sensorAnalogo;

    public WraperSensorAnalogo(Sensoranalogo sensorAnalogo) {
        this.sensorAnalogo = sensorAnalogo;
    }

    public WraperSensorAnalogo(Sensoranalogo sensorAnalogo, Integer idsensor) {
        super(idsensor);
        this.sensorAnalogo = sensorAnalogo;
    }

    public float getVoltajeMinimoDeSalida() {
        return this.sensorAnalogo.getSalidaMinima();
    }

    public void setVoltajeMinimoDeSalida(float voltajeMinimoDeSalida) {
        this.sensorAnalogo.setSalidaMinima(voltajeMinimoDeSalida);
    }

    public float getVoltajeMaximoDeSalida() {
        return this.sensorAnalogo.getSalidaMaxima();
    }

    public void setVoltajeMaximoDeSalida(float voltajeMaximoDeSalida) {
        this.sensorAnalogo.setSalidaMaxima(voltajeMaximoDeSalida);
    }

    public float getMagnitudMinima() {
        return sensorAnalogo.getMagnitudMinima();
    }

    public void setMagnitudMinima(float magnitudMinima) {
        this.sensorAnalogo.setMagnitudMinima(magnitudMinima);
    }

    public float getMagnitudMaxima() {
        return sensorAnalogo.getMagnitudMaxima();
    }

    public void setMagnitudMaxima(float magnitudMaxima) {
        this.sensorAnalogo.setMagnitudMaxima(magnitudMaxima);
    }

    public float getResolucion() {
        return (this.getVoltajeMaximoDeSalida() - this.getVoltajeMinimoDeSalida())
                / (this.getMagnitudMaxima() - this.getMagnitudMinima());
    }

    public Sensoranalogo getSensorAnalogo() {
        return sensorAnalogo;
    }

    public void setSensorAnalogo(Sensoranalogo sensorAnalogo) {
        this.sensorAnalogo = sensorAnalogo;
    }

    public float getDivisorDeFrecuenciaVelocidad() {
        return sensorAnalogo.getDivisorFrecuencia();
    }

    public float getDivisorDeFrecuenciaRPM() {
        return sensorAnalogo.getDivisorFrecuencia();
    }

}
