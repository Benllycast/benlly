/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.device;

import com.udec.model.ConfiguracionManager;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

/**
 *
 * @author windows7
 */
public final class ConfigurationDeviceManager {

    private SerialPort puerto = null;
    private static final byte CONF_TEST = '0';
    private static final byte CONF_SET_FECHA = '1';
    private static final byte CONF_GET_FECHA = '2';
    private static final byte CONF_SET_CANAL = '3';
    private static final byte CONF_GET_CANAL = '4';
    private static final byte CONF_SET_DATO = '5';
    private static final byte CONF_GET_DATO = '6';

    private static final byte CONF_DIR_CANAl = 0x08;
    private static final byte CONF_CCP_VEL_BIT = 0;
    private static final byte CONF_CCP_REV_BIT = 1;
    private static final byte CONF_CCP_BIT = 2;
    private static final byte CONF_AD_VEL_BIT = 3;
    private static final byte CONF_AD_REV_BIT = 4;

    public static final int GET_FECHA_BYTES = 9;
    public static final int SET_FECHA_BYTES = 1;
    public static final int GET_CANAL_BYTES = 3;
    public static final int SET_CANAL_BYTES = 1;
    public static final int SET_DATOS_BYTES = 1;
    public static final int GET_DATOS_BYTES = 2;

    public static final byte CONF_DIR_ID = 0x09;
    private static final byte CONF_ACK = 0X06;
    private static final byte CONF_NOACK = 0X15;
    public static final int TIME_OUT = 5000;

    public ConfigurationDeviceManager(SerialPort comm) {
        this.puerto = comm;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(SerialPort puerto) {
        this.puerto = puerto;
    }

    public boolean test() throws SerialPortTimeoutException {
        byte respuesta;
        try {
            if (puerto.writeByte(CONF_TEST)) {
                respuesta = puerto.readBytes(1, TIME_OUT)[0];
                LOG.log(Level.INFO, "---TEST_REPSUESTA: {0}", Byte.toString(respuesta));
                return (respuesta == CONF_NOACK);
            }
        } catch (SerialPortException ex) {
            LOG.log(Level.SEVERE, "prueba fallida {0}", ex);
        }
        return false;
    }

    public HashMap<String, Integer> getFecha() throws SerialPortException, SerialPortTimeoutException {
        int[] fecha;
        HashMap< String, Integer> map = null;
        if (this.puerto.writeByte(CONF_GET_FECHA)) {
            fecha = puerto.readIntArray(GET_FECHA_BYTES, TIME_OUT);
            if (fecha[0] == CONF_GET_FECHA && fecha[fecha.length - 1] == CONF_ACK) {
                map = new HashMap<>();
                map.put("dia", fecha[1]);
                map.put("mes", fecha[2]);
                map.put("anio", fecha[3]);
                map.put("vic", fecha[4]);
                map.put("hora", fecha[5]);
                map.put("min", fecha[6]);
                map.put("seg", fecha[7]);
            }
            LOG.log(Level.INFO, "cantidad de bytes leidos: {0}\nrespuesta {1}\n Dia:{2}\n Mes:{3}\n Anio:{4}\n Vic:{5}\n Hora:{6}\n Min:{7}\n Seg:{8}\n ACK:{9}",
                    new Object[]{fecha.length, fecha[0], fecha[1], fecha[2], fecha[3], fecha[4], fecha[5], fecha[6], fecha[7], fecha[8]});
        }
        return map;
    }

    // TODO: cambiar setFecha por setReloj
    public boolean setFecha(
            int dia, int mes, int anio, int vic,
            int hora, int min, int seg) throws SerialPortException, SerialPortTimeoutException {
        byte res = 0;
        boolean envio = false;
        if (puerto.writeByte(CONF_SET_FECHA)) {
            res = puerto.readBytes(SET_FECHA_BYTES, TIME_OUT)[0];
            if (res == CONF_SET_FECHA) {
                envio = (puerto.writeInt(dia)
                        && puerto.writeInt(mes)
                        && puerto.writeInt(anio)
                        && puerto.writeInt(vic)
                        && puerto.writeInt(hora)
                        && puerto.writeInt(min)
                        && puerto.writeInt(seg));
                res = puerto.readBytes(SET_FECHA_BYTES, TIME_OUT)[0];
                return (envio && (res == CONF_ACK));
            }
        }
        return false;
    }

    public HashMap<String, Boolean> getCanal() throws SerialPortException, SerialPortTimeoutException {
        HashMap<String, Boolean> map = null;

        if (this.puerto.writeByte(CONF_GET_CANAL)) {
            //TODO hacer la implementqacion de la fecha
            byte[] res = puerto.readBytes(GET_CANAL_BYTES, TIME_OUT);
            if (res[0] == CONF_GET_CANAL && res[res.length - 1] == CONF_ACK) {
                map = new HashMap<>();
                byte[] bits = {res[1]};
                BitSet set = BitSet.valueOf(bits);
                map.put("CCP_VEL", set.get(CONF_CCP_VEL_BIT));
                map.put("CCP_REV", set.get(CONF_CCP_REV_BIT));
                map.put("CCP_BIT", set.get(CONF_CCP_BIT));
                map.put("AD_VEL", set.get(CONF_AD_VEL_BIT));
                map.put("AD_REV", set.get(CONF_AD_REV_BIT));
                LOG.log(Level.INFO, "CCP_VEL: {0}\nCCP_REV: {1}\nCCP_BIT: {2}\nAD_VEL: {3}\nAD_REV: {4}",
                        new Object[]{set.get(CONF_CCP_VEL_BIT), set.get(CONF_CCP_REV_BIT), set.get(CONF_CCP_BIT), set.get(CONF_AD_VEL_BIT), set.get(CONF_AD_REV_BIT)});
            }
        }
        return map;
    }

    public boolean setCanal(HashMap<String, Boolean> map) throws SerialPortException, SerialPortTimeoutException {
        byte canales = 0x00;
        canales = (byte) ((map.get("CCP_VEL")) ? canales | (1 << CONF_CCP_VEL_BIT) : canales);
        canales = (byte) ((map.get("CCP_REV")) ? canales | (1 << CONF_CCP_REV_BIT) : canales);
        canales = (byte) ((map.get("CCP_BIT")) ? canales | (1 << CONF_CCP_BIT) : canales);
        canales = (byte) ((map.get("AD_VEL")) ? canales | (1 << CONF_AD_VEL_BIT) : canales);
        canales = (byte) ((map.get("AD_REV")) ? canales | (1 << CONF_AD_REV_BIT) : canales);
        LOG.log(Level.INFO, "Canales: {0}", Integer.toBinaryString((int) canales));
        if (this.puerto.writeByte(CONF_SET_CANAL)) {
            byte res = puerto.readBytes(SET_CANAL_BYTES, TIME_OUT)[0];
            if (res == CONF_SET_CANAL) {
                return (puerto.writeByte(canales) && (puerto.readBytes(SET_CANAL_BYTES, TIME_OUT)[0] == CONF_ACK));
            }
        }
        return false;
    }

    public byte readRAM(byte addres) throws SerialPortException, SerialPortTimeoutException {
        byte[] respuesta;
        byte res;
        if (!direccionValida(addres)) {
            LOG.log(Level.WARNING, "direccion no valida: {0}", Integer.toHexString(addres));
            return 0;
        }
        if (puerto.writeByte(CONF_GET_DATO)) {
            res = puerto.readBytes(1, TIME_OUT)[0];
            if (res == CONF_GET_DATO) {
                if (puerto.writeByte(addres)) {
                    respuesta = puerto.readBytes(GET_DATOS_BYTES, TIME_OUT);
                    LOG.log(Level.WARNING, "RD ADD: {0} VAL: {1}", new Object[]{Integer.toHexString(addres), respuesta[0]});
                    return (respuesta[respuesta.length - 1] == CONF_ACK) ? respuesta[0] : 0;
                }
            }
        }
        return 0;
    }

    public boolean writeRAM(byte addres, byte value) throws SerialPortException, SerialPortTimeoutException {
        if (!direccionValida(addres)) {
            LOG.log(Level.WARNING, "direccion no valida: {0}", Integer.toHexString(addres));
            return false;
        }
        if (puerto.writeByte(CONF_SET_DATO)) {
            if (puerto.readBytes(SET_DATOS_BYTES, TIME_OUT)[0] == CONF_SET_DATO) {
                if (puerto.writeByte(addres) && puerto.writeByte(value) && (puerto.readBytes(SET_DATOS_BYTES, TIME_OUT)[0] == CONF_ACK)){
                    LOG.log(Level.WARNING, "WR ADD: {0} VAL: {1}", new Object[]{Integer.toHexString(addres), value});
                    return true;
                }
            }
        }
        return false;
    }

    public long getID() throws SerialPortException, SerialPortTimeoutException {
        byte[] id = new byte[4];
        id[0] = readRAM((byte) (CONF_DIR_ID));
        id[1] = readRAM((byte) (CONF_DIR_ID + 1));
        id[2] = readRAM((byte) (CONF_DIR_ID + 2));
        id[3] = readRAM((byte) (CONF_DIR_ID + 3));
        ByteBuffer wrapeBuffer = ByteBuffer.wrap(id);
        return wrapeBuffer.getInt();
    }

    public boolean setID(int ID) throws SerialPortException, SerialPortTimeoutException {
        ByteBuffer dbuf = ByteBuffer.allocate(4);
        byte[] id = dbuf.putInt(ID).array();
        if(writeRAM((byte) (CONF_DIR_ID), id[0])
                && writeRAM((byte) (CONF_DIR_ID+1), id[1])
                && writeRAM((byte) (CONF_DIR_ID+2), id[2])
                && writeRAM((byte) (CONF_DIR_ID+3), id[3])){
            return true;
        }
        return false;
    }

    public boolean setOperation(byte op) {
        try {
            if (puerto.writeByte(op)) {
                byte respuesta = puerto.readBytes(1, 1000)[0];
                LOG.log(Level.WARNING, getWarninMensaje(op), String.format("%02X ", respuesta));
                return (respuesta == op);
            }
            LOG.log(Level.WARNING, "---operacion no configurada");
            return false;
        } catch (SerialPortException | SerialPortTimeoutException serialPortException) {
            LOG.log(Level.SEVERE, serialPortException.getMessage());
            return false;
        }
    }

    private static final Logger LOG = Logger.getLogger(ConfigurationDeviceManager.class.getName());

    private String getWarninMensaje(byte op) {
        switch (op) {
            case CONF_SET_FECHA:
                return ConfiguracionManager.getString("serial.deviceConfig.warning.setFecha");
            case CONF_GET_FECHA:
                return ConfiguracionManager.getString("serial.deviceConfig.warning.getFecha");
            case CONF_SET_CANAL:
                return ConfiguracionManager.getString("serial.deviceConfig.warning.setCanal");
            case CONF_GET_CANAL:
                return ConfiguracionManager.getString("serial.deviceConfig.warning.getCanal");
            case CONF_SET_DATO:
                return ConfiguracionManager.getString("serial.deviceConfig.warning.setDato");
            case CONF_GET_DATO:
                return ConfiguracionManager.getString("serial.deviceConfig.warning.getDato");
            default:
                break;
        }

        return "";
    }

    private boolean direccionValida(byte address) {
        if (address <= CONF_DIR_CANAl) {
            LOG.log(Level.SEVERE, "direccion invalidad: {0}", Integer.toHexString((int) address));
            return false;
        }
        return true;
    }

}
