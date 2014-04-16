/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.wraperModels;

import com.udec.benlly.Sensor;

/**
 *
 * @author windows7
 */
public class WraperSensorDigital extends WraperSensor{

    public WraperSensorDigital() {
    }

    public WraperSensorDigital(Integer idsensor) {
        super(idsensor);
    }

    public WraperSensorDigital(Sensor sensor) {
        super(sensor);
    }
    
}
