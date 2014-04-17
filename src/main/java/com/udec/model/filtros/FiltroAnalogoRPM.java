/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;


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
        return super.getFrecuenciaDeEntrada()/this.getSensor().getDivisorFrecuencia();
    }

    @Override
    public float getValor() {
        return getRPM();
    }
    
}
