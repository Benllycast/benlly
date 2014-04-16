/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.benlly.Log;
import com.udec.benlly.Sensor;
import com.udec.benlly.Vehiculo;

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
        return super.getPulsosPorMinuto() / super.getNumeroPulsosPorRevolucion();
    }

    @Override
    public float getValor() {
        return this.getRPM();
    }
    
}
