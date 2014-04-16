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

    public LineaLog(Integer idlog) {
        super(idlog);
    }
    
    public LineaLog(Log log) {
        super(log.getIdLog());
    }

    public float getNumeroTic() {
        return super.getValor().floatValue();
    }

    public int getValorDigital() {
        return super.getValor();
    }
}
