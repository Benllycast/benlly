/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.persistencia.Log;
import com.udec.persistencia.Sensor;
import com.udec.persistencia.Vehiculo;

/**
 *
 * @author windows7
 */
public class FiltroDigitalRPM extends FiltroDigital implements InterfaceFiltroRPM{

    public FiltroDigitalRPM() {
    }

    public FiltroDigitalRPM(Vehiculo vehiculo1, Log log, Sensor sensor) {
        super(vehiculo1, log, sensor);
    }

    @Override
    public float getRPM() {
        return this.getPulsosPorMinuto() / this.getSensor().getPulsosRevolucion();
    }

    @Override
    public float getValor() {
        return this.getRPM();
    }
    
}
