/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.model.wraperModels.FakeSensor;

/**
 *
 * @author windows7
 */
public class FiltroAnalogoAceleracion extends FiltroAnalogo implements InterfaceFiltroAceleracion{

    @Override
    public float getAceleracion() {
        FakeSensor sensor = (FakeSensor) this.getSensor();
        return (this.getVoltajeDeSalida()-this.getVoltaCentro())/sensor.getResolucion();
    }

    private float getVoltaCentro() {
        FakeSensor sensor = (FakeSensor) this.getSensor();
        return (sensor.getVoltajeMaximoDeSalida()-sensor.getVoltajeMinimoDeSalida())/2.0f;
    }

    @Override
    public float getValor() {
        return this.getAceleracion();
    }
    
}
