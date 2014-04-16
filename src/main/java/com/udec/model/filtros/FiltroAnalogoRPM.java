/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.model.Valor;
import com.udec.model.wraperModels.WraperSensorAnalogo;

/**
 *
 * @author windows7
 */
public class FiltroAnalogoRPM extends FiltroAnalogo implements InterfaceFiltroRPM{

    @Override
    public float getRPM() {
        return this.getFrecuenciaDeGiro()* MINUTO;
    }
    
    float getFrecuenciaDeGiro() {
        WraperSensorAnalogo sensor = (WraperSensorAnalogo) this.getSensor();
        return super.getFrecuenciaDeEntrada()/sensor.getDivisorDeFrecuenciaRPM();
    }

    @Override
    public float getValor() {
        return getRPM();
    }
    
}
