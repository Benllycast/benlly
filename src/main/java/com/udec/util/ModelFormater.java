/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.util;

import com.udec.persistencia.Conductor;
import com.udec.persistencia.Recorrido;
import com.udec.persistencia.Vehiculo;

/**
 *
 * @author windows7
 */
public class ModelFormater {

    public static String recorridoToString(Recorrido recorrido) {
        String text = "";
        text += "ID: " + recorrido.getIdRecorrido();
        text += "\nNOMBRE: " + recorrido.getNombre();
        text += "\nFECHA: " + FechaHora.fechaDateToString(recorrido.getFecha());
        text += "\nSALIDA PROGRAMADA: " + FechaHora.timeDateToString(recorrido.getHoraSalidaProgramada());
        text += "\nLLEGADA PROGRAMADA: " + FechaHora.timeDateToString(recorrido.getHoraLlegadaProgramada());
        text += "\nINICIO: " + FechaHora.timeDateToString(recorrido.getHoraInicio());
        text += "\nFINALIZACION: " + FechaHora.timeDateToString(recorrido.getHoraFinalizacion());
        text += "\nOTROS DATOS: " + ((recorrido.getOtrosDatos()==null)? "N/A":recorrido.getOtrosDatos());
        return text;
    }
    
    public static String vehiculoToString(Vehiculo vehiculo){
        String text = "";
        text += "ID: "+vehiculo.getIdVehiculo();
        text += "\nPLACA: "+vehiculo.getPlaca();
        text += "\nMARCA: "+vehiculo.getMarca();
        text += "\nMODELO: "+vehiculo.getModelo();
        text += "\nOTROS DATOS: "+((vehiculo.getOtrosDatos()==null)? "N/A":vehiculo.getOtrosDatos());
        return text;
    }
    
    public static String conductorToString(Conductor conductor){
        String text = "";
        text += "ID: "+conductor.getIdConductor();
        text += "\nNOMBRE: "+conductor.getPrimerNombre()+" "+conductor.getSegundoNombre();
        text += "\nAPELLIDOS: "+conductor.getPrimerApellido()+" "+conductor.getSegundoApellido();
        text += "\nNº IDENTIFICACION: "+conductor.getIdentificacion();
        text += "\nNº LINCENCIA: "+conductor.getLicencia();
        return text;
    }
}
