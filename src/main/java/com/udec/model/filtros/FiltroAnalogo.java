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
        return this.getVoltajeDeSalida()/this.getResolucion();
    }

    public float getVoltajeDeSalida() {
        return RES_ADC*super.getLog().getValor();
    }
    
    public float getResolucion() {
        return (
                (this.getSensor().getSalidaMaxima()-this.getSensor().getSalidaMinima())/
                (this.getSensor().getMagnitudMaxima()-this.getSensor().getMagnitudMinima())
                );
    }
    
    @Override
    public void execute() {
    }
}
