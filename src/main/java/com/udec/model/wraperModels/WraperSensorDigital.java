/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.wraperModels;

import com.udec.benlly.Sensor;
import com.udec.benlly.Sensordigital;

/**
 *
 * @author windows7
 */
public class WraperSensorDigital extends Sensor {
    
        private Sensordigital sensorDigital;

    public WraperSensorDigital(Sensordigital sensorDigital) {
        this.sensorDigital = sensorDigital;
    }

    public WraperSensorDigital(Sensordigital sensorDigital, Integer idsensor) {
        super(idsensor);
        this.sensorDigital = sensorDigital;
    }

    public Sensordigital getSensorDigital() {
        return sensorDigital;
    }

    public void setSensorDigital(Sensordigital sensorDigital) {
        this.sensorDigital = sensorDigital;
    }

    public float getNumeroPulsosPorRevolucion() {
        return this.sensorDigital.getPulsosRevolucion();
    }
    
}
