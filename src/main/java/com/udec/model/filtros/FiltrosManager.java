/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.model.filtros;

import com.udec.persistencia.Log;
import com.udec.persistencia.Sensor;
import com.udec.persistencia.Vehiculo;
import com.udec.model.ConfiguracionManager;
import com.udec.model.Valor;
import java.util.HashMap;
import java.util.List;

/**
 * Esta clase aplica los filstro para obtener los valores de los log
 * @author windows7
 */
public class FiltrosManager {
    HashMap<Short, Filtro> filtros;
    List<Valor> listaValores;

    public FiltrosManager() {
        this.filtros = new HashMap<>();
    }

    public HashMap<Short, Filtro> getFiltros() {
        return filtros;
    }

    public void setFiltros(HashMap<Short, Filtro> filtros) {
        this.filtros = filtros;
    }

    /**
     * coloca un filtro en la lista de filtros cclasificados por canal
     * @param canal
     * @param filtro
     */
    public void pushFiltro(Short canal, Filtro filtro){
        this.filtros.put(canal, filtro);
    }
    
    /**
     *  obtiene el filtro predeterminado para el canal que venga de la linea de log
     * @param canal
     * @return
     */
    public Filtro pullFiltro(Short canal) throws Exception{
        Filtro filtro = null;
        if(this.filtros.containsKey(canal)){
            filtro = this.filtros.get(canal);
        }else
            throw new Exception("ERROR: no hay filtro para el canal"+canal);
        return filtro;
    }

    /**
     * crea un filtro espesifico a partir del canal de un sensor
     * @param sensor
     * @return
     */
    Filtro crearFiltro(Sensor sensor){
        switch(sensor.getCanal()){
            case ConfiguracionManager.CANAL_ACC_1:
            case ConfiguracionManager.CANAL_ACC_2:
            case ConfiguracionManager.CANAL_ACC_3:
                return new FiltroAnalogoAceleracion();
            case ConfiguracionManager.CANAL_AD_VEL:
                return new FiltroAnalogoVelocidad();
            case ConfiguracionManager.CANAL_AD_RPM:
                return new FiltroAnalogoRPM();
            case ConfiguracionManager.CANAL_DIG_VEL:
                return new FiltroDigitalVelocidad();
            case ConfiguracionManager.CANAL_DIG_RPM:
                return new FiltroDigitalRPM();
            default:
                return null;
        }
    }
    
    /**
     *  obtiene la lista de sensores que contiene un vehiculo y crea los filtros
     * para esos sensores
     * @param vehiculo
     */
    public void configurarFiltros(Vehiculo vehiculo){
        List<Sensor> sensorList = vehiculo.getSensorList();
        for(Sensor sensor : sensorList){
            Filtro filtro = this.crearFiltro(sensor);
            filtro.setVehiculo(vehiculo);
            filtro.setSensor(sensor);
            this.pushFiltro(sensor.getCanal(), filtro);
        }
    }
    
    /**
     * aplica un filtro previamente a la linea de log correspondiente.
     * el filtro se obtiene a apartir del canal del log y debe coincidir con uno
     * de la lista de filtros
     * @param linea
     * @throws Exception
     */
    public Valor aplicarFiltro(Log linea) throws Exception{
        Filtro filtro;
        if (this.filtros.size()>0) {
            filtro = this.pullFiltro(linea.getCanal());
            if (filtro == null) {
                throw new Exception("No hay filtro para la linea: " + linea.toString());
            }
            filtro.setLog(linea);
            Valor valor = new Valor(filtro);
            return valor;
        }else
            throw new Exception("no ahy filtros configurados");
    }
}
