/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.model.wraperModels;

import com.udec.benlly.Vehiculo;

/**
 *
 * @author windows7
 */
public class WraperVehiculo extends Vehiculo {

    private float numeroPulsosPorRevolucion;
    private float relacionDeCaja;
    private float relacionDeDiferencial;
    private float radioRueda;    
    private float divisorDeFrecuenciaRPM = 1.0f;
    private float divisorDeFrecuenciaVelocidad = 1.0f;

    public float getDivisorDeFrecuenciaRPM() {
        return divisorDeFrecuenciaRPM;
    }

    public void setDivisorDeFrecuenciaRPM(float divisorDeFrecuenciaRPM) {
        this.divisorDeFrecuenciaRPM = divisorDeFrecuenciaRPM;
    }


    public WraperVehiculo() {
    }

    public WraperVehiculo(Integer idvehiculo) {
        super(idvehiculo);
        this.numeroPulsosPorRevolucion = super.getNp().floatValue();
    }

    public WraperVehiculo(Vehiculo vehiculo) {
        super(vehiculo.getIdvehiculo());
        this.numeroPulsosPorRevolucion = super.getNp().floatValue();
    }

    public float getNumeroPulsosPorRevolucion() {
        return numeroPulsosPorRevolucion;
    }

    public void setNumeroPulsosPorRevolucion(float numeroPulsosPorRevolucion) {
        this.numeroPulsosPorRevolucion = numeroPulsosPorRevolucion;
    }

    public float getRelacionDeCaja() {
        return relacionDeCaja;
    }

    public void setRelacionDeCaja(float relacionDeCaja) {
        this.relacionDeCaja = relacionDeCaja;
    }

    public float getRelacionDeDiferencial() {
        return this.relacionDeDiferencial;
    }

    public void setRelacionDeDiferencial(float relacionDeDiferencial) {
        this.relacionDeDiferencial = relacionDeDiferencial;
    }

    public float getRadioRueda() {
        return this.radioRueda;
    }

    public void setRadioRueda(float radioRueda) {
        this.radioRueda = radioRueda;
    }

    public float getDivisorDeFrecuenciaVelocidad() {
        return divisorDeFrecuenciaVelocidad;
    }

    public void setDivisorDeFrecuenciaVelocidad(float divisorDeFrecuenciaVelocidad) {
        this.divisorDeFrecuenciaVelocidad = divisorDeFrecuenciaVelocidad;
    }

    

}
