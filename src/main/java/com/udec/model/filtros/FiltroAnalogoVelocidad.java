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
public class FiltroAnalogoVelocidad extends FiltroAnalogo implements InterfaceFiltroVelocidad{

    @Override
    public float getVelocidad() {
        return super.getVehiculo().getRadioRueda()*this.getVelocidadAngular();
    }

    private float getVelocidadAngular() {
        return this.getFrecuenciaDeGiro()*2.0f*((float)Math.PI);
    }

    private float getFrecuenciaDeGiro() {
        return this.getFrecuenciaDeEntrada()/this.getVehiculo().getDivisorDeFrecuenciaVelocidad();
    }

    @Override
    public float getValor() {
        return this.getVelocidad();
    }
    
}
