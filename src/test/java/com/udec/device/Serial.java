/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.device;

import com.udec.device.serial.SerialConfigDialog;
import com.udec.device.serial.CommManager;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author windows7
 */
public class Serial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //test1();
        //test2();
        //test3();
        //test4();
        test5();
        CommManager.closePort();
    }

    private static void test1() {
        SerialPort serialPort = new SerialPort("COM4");
        try {
            System.out.println("Port opened: " + serialPort.openPort());
            System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
            System.out.println("\"Hello World!!!\" successfully writen to port: " + serialPort.writeBytes("Hello World!!!".getBytes()));
            System.out.println("Port closed: " + serialPort.closePort());
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public static void test2() {
        String[] lista;
        lista = CommManager.getListaDePuertos();
        for (String nom : lista) {
            System.out.println(nom);
        }
    }

    private static void test3() {
        byte[] buffer;
        buffer = "Hello World!!!".getBytes();
        SerialConfigDialog dialog = new SerialConfigDialog(null, true);
        dialog.setVisible(true);
        if (CommManager.isCommReady()) {
            try {
                SerialPort puerto = CommManager.getComm();
                puerto.writeBytes(buffer);

            } catch (Exception exception) {
                System.out.println("---- algo paso ----");
            }
        } else {
            System.out.println("conexcion no esta lista");
        }

    }

    private static void test4() {
        GuiTestConfigurationManager ventana = new GuiTestConfigurationManager();
        ventana.setVisible(true);
    }

    private static void test5() {

        JFrame v = new JFrame("Prueba JInternalFrame");
        JDesktopPane dp = new JDesktopPane();
        v.getContentPane().add(dp);

        SerialDeviceConfigForm internal = new SerialDeviceConfigForm();
        
		// Es importante darle tamaño -pack()- al JInternalFrame,
        // porque si no, tendrá tamaño 0,0 y no lo veremos.
        internal.pack();

		// Por defecto el JInternalFrame no es redimensionable ni
        // tiene el botón de cerrar, así que se lo ponemos.
        /*internal.setResizable(true);
        internal.setClosable(true);*/

        // Se mete el internal en el JDesktopPane
        dp.add(internal);

        // Se visualiza todo.
        v.setSize(500, 500);
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Se visualiza el JInternalFrame 
        internal.setVisible(true);

        
    }

}
