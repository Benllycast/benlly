/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.benlly.Sensor;
import com.udec.benlly.Vehiculo;
import com.udec.model.wraperModels.FakeSensor;
import com.udec.model.wraperModels.LineaLog;

/**
 *
 * @author windows7
 */
public class FiltroDigitalRPM extends FiltroDigital implements InterfaceFiltroRPM{

    public FiltroDigitalRPM() {
    }

    public FiltroDigitalRPM(Vehiculo vehiculo1, LineaLog log, Sensor sensor) {
        super(vehiculo1, log, sensor);
    }

    @Override
    public float getRPM() {
        FakeSensor sensor = (FakeSensor)this.getSensor();
        return this.getPulsosPorMinuto() / sensor.getNumeroPulsosPorRevolucion();
    }

    @Override
    public float getValor() {
        return this.getRPM();
    }
    
}
