/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.model.wraperModels;

import com.udec.benlly.Log;

/**
 *
 * @author windows7
 */
public class LineaLog extends Log {

    private float numeroTic = 0.0f;
    private int valorDigital = 0;
    

    public LineaLog(float numeroTic) {
        this.numeroTic = numeroTic;
    }

    public LineaLog(Integer idlog) {
        super(idlog);
        this.numeroTic = super.getValorObbtenido().floatValue();
    }
    
    public LineaLog(Log log) {
        super(log.getIdlog());
        this.numeroTic = super.getValorObbtenido().floatValue();
    }

    public float getNumeroTic() {
        return numeroTic;
    }

    public void setNumeroTic(float numeroTic) {
        this.numeroTic = numeroTic;
    }

    public int getValorDigital() {
        return valorDigital;
    }

    public void setValorDigital(int valorDigital) {
        this.valorDigital = valorDigital;
    }

}
