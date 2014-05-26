/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.vista;

import com.udec.persistencia.Conductor;
import com.udec.persistencia.Recorrido;
import com.udec.persistencia.Sensor;
import com.udec.persistencia.Vehiculo;
import com.udec.model.ConfiguracionManager;
import com.udec.model.Motor;
import com.udec.model.Valor;
import com.udec.model.exceptions.MotorException;
import com.udec.util.ModelFormater;
import com.udec.vista.graficos.Grafico;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author windows7
 */
public class AnalisisForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form AnalisisForm
     */
    public AnalisisForm() {

        init();
        initComponents();
        initListeners();
    }

    private ListSelectionListener selectionListener;
    private TableCellRenderer tableCellRenderer;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        //pre creacion
        entityManager = entityManager = java.beans.Beans.isDesignTime() ? null : ConfiguracionManager.getEntityManager();
        // pots crecion
        recorridoQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT r FROM Recorrido r");
        recorridoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : recorridoQuery.getResultList();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlInformacion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaRecorrido = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaVehiculo = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txaConductor = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jpnlGraficos = new javax.swing.JPanel();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setTitle("Analisi de recorridos");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Informacion de Recorrido:");

        txaRecorrido.setColumns(20);
        txaRecorrido.setRows(5);
        txaRecorrido.setTabSize(4);
        jScrollPane2.setViewportView(txaRecorrido);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Informacion del vehiculo");

        txaVehiculo.setColumns(20);
        txaVehiculo.setRows(5);
        txaVehiculo.setTabSize(4);
        jScrollPane3.setViewportView(txaVehiculo);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Informacion de Conductor");
        jLabel3.setToolTipText("");

        txaConductor.setColumns(20);
        txaConductor.setRows(5);
        txaConductor.setTabSize(4);
        jScrollPane4.setViewportView(txaConductor);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jpnlGraficos.setMaximumSize(new java.awt.Dimension(622, 32767));
        jpnlGraficos.setMinimumSize(new java.awt.Dimension(622, 10));
        jpnlGraficos.setPreferredSize(new java.awt.Dimension(622, 0));

        javax.swing.GroupLayout pnlInformacionLayout = new javax.swing.GroupLayout(pnlInformacion);
        pnlInformacion.setLayout(pnlInformacionLayout);
        pnlInformacionLayout.setHorizontalGroup(
            pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnlGraficos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlInformacionLayout.setVerticalGroup(
            pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnlGraficos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3)
                    .addGroup(pnlInformacionLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(pnlInformacion);

        masterTable.setColumnSelectionAllowed(true);
        masterTable.getTableHeader().setReorderingAllowed(false);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, recorridoList, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idRecorrido}"));
        columnBinding.setColumnName("Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fecha}"));
        columnBinding.setColumnName("Fecha");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${horaSalidaProgramada}"));
        columnBinding.setColumnName("Hora Salida Programada");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${horaLlegadaProgramada}"));
        columnBinding.setColumnName("Hora Llegada Programada");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${horaInicio}"));
        columnBinding.setColumnName("Hora Inicio");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${horaFinalizacion}"));
        columnBinding.setColumnName("Hora Finalizacion");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${vehiculoidVehiculo.placa}"));
        columnBinding.setColumnName("Vehiculo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${conductoridConductor.primerApellido}"));
        columnBinding.setColumnName("Conductor");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${otrosDatos}"));
        columnBinding.setColumnName("Otros Datos");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);
        masterTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(3).setCellRenderer(this.tableCellRenderer);
            masterTable.getColumnModel().getColumn(4).setCellRenderer(this.tableCellRenderer);
            masterTable.getColumnModel().getColumn(5).setCellRenderer(this.tableCellRenderer);
            masterTable.getColumnModel().getColumn(6).setCellRenderer(this.tableCellRenderer);
        }
        masterTable.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        masterTable.getSelectionModel().addListSelectionListener(this.selectionListener);

        jButton1.setText("Analizar Recorrido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterScrollPane)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(masterScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selected = masterTable.getSelectedRow();
        Recorrido recorrido = recorridoList.get(masterTable.convertRowIndexToModel(selected));
        Vehiculo vehiculo = recorrido.getVehiculoidVehiculo();
        List<Sensor> sensorList = vehiculo.getSensorList();
        for (Sensor sensor : sensorList) {
            try {
                Motor.setRecorrido(recorrido);
                Motor.init();
                Motor.clasificarValores();
                List<Valor> listValor = Motor.getListValorBySensor(sensor);
                Grafico grafico = new Grafico(listValor, sensor, recorrido);
                XYDataset dataset = grafico.createDataset();
                JFreeChart chart = grafico.createChart(dataset);
                JPanel panel = grafico.createCharPanel(chart);
                this.jpnlGraficos.add(panel);
                jpnlGraficos.validate();
                //break;
            } catch (MotorException ex) {
                Logger.getLogger(AnalisisForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel jpnlGraficos;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JPanel pnlInformacion;
    private java.util.List<com.udec.persistencia.Recorrido> recorridoList;
    private javax.persistence.Query recorridoQuery;
    private javax.swing.JTextArea txaConductor;
    private javax.swing.JTextArea txaRecorrido;
    private javax.swing.JTextArea txaVehiculo;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void initListeners() {

    }

    private void init() {
        this.tableCellRenderer = new DefaultTableCellRenderer() {

            SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                if (value instanceof Date) {
                    value = f.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
            }
        };

        this.selectionListener = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                Recorrido recorrido;
                String text = "";
                int index = masterTable.getSelectedRow();
                recorrido = recorridoList.get(masterTable.convertRowIndexToModel(index));
                Vehiculo vehiculo = recorrido.getVehiculoidVehiculo();
                Conductor conductor = recorrido.getConductoridConductor();
                text = ModelFormater.recorridoToString(recorrido);
                txaRecorrido.setText(text);
                text = ModelFormater.vehiculoToString(vehiculo);
                txaVehiculo.setText(text);
                text = ModelFormater.conductorToString(conductor);
                txaConductor.setText(text);
            }
        };
    }

}
