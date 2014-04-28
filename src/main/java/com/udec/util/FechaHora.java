/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author windows7
 */
public class FechaHora {
    
    private static SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yy/MM/dd");
    private static SimpleDateFormat formatoDeTiempo = new SimpleDateFormat("HH:mm:ss");
    
    public static Date fechaStringToDate(String fecha){
        Date valor = null;
        try {
            valor = formatoDelTexto.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(FechaHora.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            valor = new Date();
        }
        return valor;
    }
    
    public static String fechaDateToString(Date fecha){
        String valor = "**/**/**";
        valor = formatoDelTexto.format(fecha);
        return valor;
    }
    
    public static Date timeStringToDate(String tiempo){
        Date valor = null;
        try {
            valor = formatoDeTiempo.parse(tiempo);
        } catch (ParseException ex) {
            Logger.getLogger(FechaHora.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            valor = new Date();
        }
        return valor;
    }
    
    public static String timeDateToString(Date time){
        String valor = "**:**:**";
        valor = formatoDeTiempo.format(time);
        return valor;
    }
    
}
