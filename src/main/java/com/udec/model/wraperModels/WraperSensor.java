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
public class WraperSensor extends Sensor{

    public WraperSensor() {
    }

    public WraperSensor(Integer idsensor) {
        super(idsensor);
    }
    
    public WraperSensor(Sensor sensor) {
        super(sensor.getIdsensor());
    }
    
}
