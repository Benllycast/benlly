/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.device.serial;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author windows7
 */
public final class CommManager {
    private static SerialPort comm = null;

    public static int init(String portname, int baudRate,
                         int dataBits,
                         int stopBits,
                         int parity) throws Exception{
        if(comm == null){
            try {
                comm = new SerialPort(portname);
                if (comm.openPort()) {
                    comm.setParams(baudRate, dataBits, stopBits, parity);
                    return 0;
                }
            } catch (SerialPortException serialPortException) {
                System.out.println("CommManager - "+ serialPortException.getExceptionType()+" - "+serialPortException.getMessage());
            }
        }else{
            throw new Exception("CommManger - ya hay una conexion echa");
        }
        
        return (-1);
    }
    
    public static boolean isCommReady(){
        return (comm != null && comm.isOpened());
    }
    
    public static SerialPort getComm () throws Exception{
        if (comm == null) throw new Exception("CommManager - La conexion no se ha iniciado");
        return comm;
    }
    
    public static boolean closePort(){
        boolean respuesta = false;
        try {
            if (comm != null) {
                respuesta = comm.closePort();
                comm = (respuesta)? null: comm;
                return respuesta;
            }
        } catch (SerialPortException ex) {
            Logger.getLogger(CommManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Comm Manager - No se puede cerrar el puerto");
        } 
         return respuesta;
    }

    public static String[] getListaDePuertos() {
        return SerialPortList.getPortNames();
    }

    public static boolean hasPorts() {
        return (CommManager.getListaDePuertos().length > 0);
    }
}
