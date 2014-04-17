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
public class FiltroAnalogoAceleracion extends FiltroAnalogo implements InterfaceFiltroAceleracion{

    @Override
    public float getAceleracion() {
        return (this.getVoltajeDeSalida()-this.getVoltaCentro())/super.getResolucion();
    }

    private float getVoltaCentro() {
        return (this.getSensor().getSalidaMaxima()-this.getSensor().getSalidaMinima())/2.0f;
    }

    @Override
    public float getValor() {
        return this.getAceleracion();
    }

    
    
}
