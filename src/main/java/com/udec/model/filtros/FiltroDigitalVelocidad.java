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
public class FiltroDigitalVelocidad extends FiltroDigitalRPM implements InterfaceFiltroVelocidad {

    public FiltroDigitalVelocidad() {
    }

    public FiltroDigitalVelocidad(Vehiculo vehiculo1, Log log, Sensor sensor) {
        super(vehiculo1, log, sensor);
    }

    @Override
    public float getVelocidad() {
        float velocidad = super.getVehiculo().getRadioRueda() * 
                (this.getVelocidadAngular()/(this.getVehiculo().getRelacionCaja()*this.getVehiculo().getRelacionDiferencial()));
        return velocidad;
    }

    private float getVelocidadAngular() {
        return 2.0f*this.getRevolucionesPorSegundo()*((float)Math.PI);
    }

    private float getRevolucionesPorSegundo() {
        return getRPM()/MINUTO;
    }

}
