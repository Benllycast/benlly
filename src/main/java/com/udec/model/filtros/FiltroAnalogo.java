/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.benlly.Log;
import com.udec.benlly.Sensor;
import com.udec.benlly.Vehiculo;
import com.udec.model.wraperModels.WraperSensorAnalogo;
/**
 *
 * @author windows7
 */
public abstract class FiltroAnalogo extends Filtro{
    private static final float VREF_POSITIVO = 5.0f;
    private static final float VREF_NEGATIVO = 0.0f;
    private static final int BITS = 10;
    private static final float RES_ADC = (VREF_POSITIVO-VREF_NEGATIVO)/((float)Math.pow(2.0d, BITS));

    public FiltroAnalogo() {
    }

    public FiltroAnalogo(Vehiculo vehiculo1, Log log, Sensor sensor) {
        super(vehiculo1, log, sensor);
    }

    float getFrecuenciaDeEntrada() {
        WraperSensorAnalogo sensor = (WraperSensorAnalogo) this.getSensor();
        return this.getVoltajeDeSalida()/sensor.getResolucion();
    }

    public float getVoltajeDeSalida() {
        return RES_ADC*super.getLineaLog().getValorDigital();
    }

    
    
}
