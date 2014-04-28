/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.device;

import java.awt.HeadlessException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;
import com.udec.device.serial.CommManager;
import com.udec.device.serial.SerialConfigDialog;

/**
 *
 * @author windows7
 */
public class GuiTestConfigurationManager extends javax.swing.JFrame {

    ConfigurationDeviceManager manager = null;

    /**
     * Creates newGuiTestConfigurationDeviceManageranager
     */
    public GuiTestConfigurationManager() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bntConfigurarPuerto = new javax.swing.JButton();
        btnTestDevice = new javax.swing.JButton();
        btnGetFecha = new javax.swing.JButton();
        btnSetFecha = new javax.swing.JButton();
        btnGetCanal = new javax.swing.JButton();
        btnSetCanal = new javax.swing.JButton();
        chkAD_VEL = new javax.swing.JCheckBox();
        chkAD_REV = new javax.swing.JCheckBox();
        chkCCP = new javax.swing.JCheckBox();
        chkCCP_VEL = new javax.swing.JCheckBox();
        chkCCP_REV = new javax.swing.JCheckBox();
        btnLeerRam = new javax.swing.JButton();
        btnErcribirRam = new javax.swing.JButton();
        btnGetId = new javax.swing.JButton();
        btnSetId = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bntConfigurarPuerto.setText("Configurar Puerto");
        bntConfigurarPuerto.setEnabled(!CommManager.isCommReady());
        bntConfigurarPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntConfigurarPuertoActionPerformed(evt);
            }
        });

        btnTestDevice.setText("TEST");
        btnTestDevice.setEnabled((CommManager.isCommReady()));
        btnTestDevice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestDeviceActionPerformed(evt);
            }
        });

        btnGetFecha.setText("GET FECHA");
        btnGetFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetFechaActionPerformed(evt);
            }
        });

        btnSetFecha.setText("SET FECHA");
        btnSetFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetFechaActionPerformed(evt);
            }
        });

        btnGetCanal.setText("GET CANAL");
        btnGetCanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetCanalActionPerformed(evt);
            }
        });

        btnSetCanal.setText("SET CANAL");
        btnSetCanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetCanalActionPerformed(evt);
            }
        });

        chkAD_VEL.setText("AD VEL");

        chkAD_REV.setText("AD REV");

        chkCCP.setText("CCP");

        chkCCP_VEL.setText("CCP VEL");

        chkCCP_REV.setText("CCP REV");

        btnLeerRam.setText("LEER RAM");
        btnLeerRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeerRamActionPerformed(evt);
            }
        });

        btnErcribirRam.setText("ESCR RAM");
        btnErcribirRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnErcribirRamActionPerformed(evt);
            }
        });

        btnGetId.setText("GET ID");
        btnGetId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetIdActionPerformed(evt);
            }
        });

        btnSetId.setText("SET ID");
        btnSetId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSetCanal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAD_REV)
                        .addGap(2, 2, 2)
                        .addComponent(chkAD_VEL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkCCP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkCCP_REV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkCCP_VEL))
                    .addComponent(bntConfigurarPuerto)
                    .addComponent(btnTestDevice)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGetFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetFecha))
                    .addComponent(btnGetCanal)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLeerRam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnErcribirRam))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGetId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetId)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bntConfigurarPuerto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTestDevice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGetFecha)
                    .addComponent(btnSetFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGetCanal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSetCanal)
                    .addComponent(chkAD_VEL)
                    .addComponent(chkCCP)
                    .addComponent(chkCCP_VEL)
                    .addComponent(chkAD_REV)
                    .addComponent(chkCCP_REV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLeerRam)
                    .addComponent(btnErcribirRam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGetId)
                    .addComponent(btnSetId))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bntConfigurarPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntConfigurarPuertoActionPerformed
        // TODO add your handling code here:
        SerialConfigDialog dialog = new SerialConfigDialog(this, true);
        dialog.setVisible(true);
        try {
            this.manager = (CommManager.isCommReady())? new ConfigurationDeviceManager(CommManager.getComm()) : null;
            activarTodo();
        } catch (Exception ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bntConfigurarPuertoActionPerformed

    private void btnTestDeviceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestDeviceActionPerformed
        try {
            // TODO add your handling code here:
            if (this.manager.test()) {
                JOptionPane.showMessageDialog(this, "Conexion OK", "Conexion con el dispositivo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Conexion Fallida", "Conexion con el dispositivo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SerialPortTimeoutException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnTestDeviceActionPerformed

    private void btnGetFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetFechaActionPerformed
        try {
            // TODO add your handling code here:
            HashMap<String, Integer> fecha = this.manager.getFecha();
        } catch (SerialPortException | SerialPortTimeoutException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGetFechaActionPerformed

    private void btnSetFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetFechaActionPerformed
        // TODO add your handling code here:
        int dia = (int) (Math.floor(Math.random() * 30));
        int mes = (int) (Math.floor(Math.random() * 11));
        int anio = 14;
        int vic = 0;
        int hora = (int) (Math.floor(Math.random() * 23));
        int min = (int) (Math.floor(Math.random() * 59));
        int seg = (int) (Math.floor(Math.random() * 59));
        try {
            if (manager.setFecha(dia, mes, anio, vic, hora, min, seg)) {
                JOptionPane.showMessageDialog(this, "fecha configurada", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "fecha configurada", null, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SerialPortException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortTimeoutException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSetFechaActionPerformed

    private void btnGetCanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetCanalActionPerformed
        // TODO add your handling code here:
        try {
            if (manager.getCanal() != null) {
                JOptionPane.showConfirmDialog(this, "OK");
            } else {
                JOptionPane.showMessageDialog(this, "ERROR", null, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SerialPortException | SerialPortTimeoutException | HeadlessException ex) {
        }
    }//GEN-LAST:event_btnGetCanalActionPerformed

    private void btnSetCanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetCanalActionPerformed
        // TODO add your handling code here:
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("CCP_VEL", chkCCP_VEL.isSelected());
        map.put("CCP_REV", chkCCP_REV.isSelected());
        map.put("CCP_BIT", chkCCP.isSelected());
        map.put("AD_REV", chkAD_REV.isSelected());
        map.put("AD_VEL", chkAD_VEL.isSelected());
        try {
            if (manager.setCanal(map)) {
                JOptionPane.showMessageDialog(this, "Canales configurados", null, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro al configurar los canales", null, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SerialPortException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, "Error al enviar datos", ex);
        } catch (SerialPortTimeoutException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, "expiro el tiempo de espera", ex);
        }
    }//GEN-LAST:event_btnSetCanalActionPerformed

    private void btnLeerRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeerRamActionPerformed
        // TODO add your handling code here:
        byte buffer, addres = (byte) 0x09;
        try {
            buffer = manager.readRAM(addres);
            JOptionPane.showMessageDialog(this, "DIR: " + addres + " VAL: " + Byte.toString(buffer), null, JOptionPane.INFORMATION_MESSAGE);
        } catch (SerialPortException | SerialPortTimeoutException | HeadlessException exception) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, "ERROR:", exception);
        }
    }//GEN-LAST:event_btnLeerRamActionPerformed

    private void btnErcribirRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnErcribirRamActionPerformed
        // TODO add your handling code here:
        byte buffer = (byte) (Math.floor(Math.random() * 255)),
                addres = (byte) 0x09;
        try {
            if (!manager.writeRAM(addres, buffer)) {
                JOptionPane.showMessageDialog(this, "Error al escribir el byte", null, JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "DIR: " + addres + " VAL: " + Byte.toString(buffer), null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SerialPortException | SerialPortTimeoutException | HeadlessException exception) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, "ERROR:", exception);
        }
    }//GEN-LAST:event_btnErcribirRamActionPerformed

    private void btnGetIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetIdActionPerformed
        try {
            long ID = manager.getID();
            System.out.println("ID: "+(long)ID);
        } catch (SerialPortException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortTimeoutException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGetIdActionPerformed

    private void btnSetIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetIdActionPerformed
        try {
            int dia = (int) (Math.floor(Math.random() * Integer.MAX_VALUE));
            if(manager.setID(dia)){
                System.out.println("ID configurado: "+dia);
            }
        } catch (SerialPortException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortTimeoutException ex) {
            Logger.getLogger(GuiTestConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSetIdActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntConfigurarPuerto;
    private javax.swing.JButton btnErcribirRam;
    private javax.swing.JButton btnGetCanal;
    private javax.swing.JButton btnGetFecha;
    private javax.swing.JButton btnGetId;
    private javax.swing.JButton btnLeerRam;
    private javax.swing.JButton btnSetCanal;
    private javax.swing.JButton btnSetFecha;
    private javax.swing.JButton btnSetId;
    private javax.swing.JButton btnTestDevice;
    private javax.swing.JCheckBox chkAD_REV;
    private javax.swing.JCheckBox chkAD_VEL;
    private javax.swing.JCheckBox chkCCP;
    private javax.swing.JCheckBox chkCCP_REV;
    private javax.swing.JCheckBox chkCCP_VEL;
    // End of variables declaration//GEN-END:variables

    private void activarTodo() {
        //TODO activar el resto de botones
        if (this.manager == null) {
            return;
        }
        this.btnTestDevice.setEnabled(true);
    }
}
