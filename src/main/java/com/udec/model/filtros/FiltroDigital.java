/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.model.filtros;

import com.udec.benlly.Log;
import com.udec.benlly.Sensor;
import com.udec.benlly.Vehiculo;
import com.udec.model.wraperModels.LineaLog;
import com.udec.model.wraperModels.WraperSensorDigital;

/**
 *
 * @author windows7
 */
public abstract class FiltroDigital extends Filtro {

    private static final float FOSC = 16000000f;
    private static final float TOSC = 1.0f / FOSC;
    private static final float PREESCALER = 8.0f;
    private static final float MULTIPLICADOR = 4.0f;
    private final float tiempoTic = TOSC * MULTIPLICADOR * PREESCALER;
    private float periodoPulso = 0.0f;
    private float frecuenciaPulso = 0.0f;
    private float pulsosPorMinuto = 0.0f;

    public FiltroDigital() {
    }

    public FiltroDigital(Vehiculo vehiculo1, LineaLog log, Sensor sensor) {
        super(vehiculo1, log, sensor);
        this.periodoPulso = this.getLineaLog().getNumeroTic() * this.tiempoTic;
        this.frecuenciaPulso = 1.0f / this.getPeriodoDePulso();
        this.pulsosPorMinuto = this.getFrecuenciaDePulso() * MINUTO;
    }

    float getPulsosPorMinuto() {
        return pulsosPorMinuto;
    }

    private float getFrecuenciaDePulso() {
        return this.frecuenciaPulso;
    }

    private float getPeriodoDePulso() {
        return this.periodoPulso;
    }


}
