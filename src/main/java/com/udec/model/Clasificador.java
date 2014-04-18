/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model;

import com.udec.benlly.Sensor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author windows7
 */
public class Clasificador {
    private List<Valor> listValor;
    HashMap<Sensor, List<Valor>> listas = new HashMap<>();

    void setListValor(List<Valor> listValor) {
        this.listValor = listValor;
    }

    public HashMap<Sensor, List<Valor>> getListas() {
        return listas;
    }

    public void setListas(HashMap<Sensor, List<Valor>> listas) {
        this.listas = listas;
    }
    
    void clasificar() {
        Sensor sensor;
        for(Valor v : listValor){
            sensor = v.getSensor();
            this.pushVector(sensor, v);
        }
    }

    private void pushVector(Sensor key, Valor v) {
        List<Valor> lts;
        if(listas.containsKey(key)){
            listas.get(key).add(v);
        }else{
            lts = new ArrayList<>();
            lts.add(v);
            listas.put(key, lts);
        }
    }
    
}
