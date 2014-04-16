/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.model.wraperModels.WraperSensorAnalogo;

/**
 *
 * @author windows7
 */
public class FiltroAnalogoAceleracion extends FiltroAnalogo implements InterfaceFiltroAceleracion{

    @Override
    public float getAceleracion() {
        WraperSensorAnalogo sensor = (WraperSensorAnalogo) this.getSensor();
        return (this.getVoltajeDeSalida()-this.getVoltaCentro())/sensor.getResolucion();
    }

    private float getVoltaCentro() {
        WraperSensorAnalogo sensor = (WraperSensorAnalogo) this.getSensor();
        return (sensor.getVoltajeMaximoDeSalida()-sensor.getVoltajeMinimoDeSalida())/2.0f;
    }

    @Override
    public float getValor() {
        return this.getAceleracion();
    }
    
}
