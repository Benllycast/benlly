/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.vista.graficos;

import com.udec.persistencia.Conductor;
import com.udec.persistencia.Recorrido;
import com.udec.persistencia.Sensor;
import com.udec.persistencia.Vehiculo;
import com.udec.model.ConfiguracionManager;
import com.udec.model.Valor;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.border.Border;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author windows7
 */
public class Grafico {

    List<Valor> listValor;
    Sensor sensor;
    Recorrido recorrido;
    Vehiculo vehiculo;
    Conductor conductor;

    public Grafico(List<Valor> listValor, Sensor sensor, Recorrido recorrido) {
        this.listValor = listValor;
        this.sensor = sensor;
        this.recorrido = recorrido;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }

    public List<Valor> getListValor() {
        return listValor;
    }

    public void setListValor(List<Valor> listValor) {
        this.listValor = listValor;
    }

    public XYDataset createDataset() {
        TimeSeries s1 = new TimeSeries(this.sensor.getMagnitud(), Millisecond.class);
        for(Valor val :listValor){
            Object[] point = val.getPoint();
            Date fecha = (Date) point[0];
            double y = ((Float)point[1]).doubleValue();
            s1.add(new Millisecond(fecha), y);
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.setDomainIsPointsInTime(true);
        return dataset;

    }

    public JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                ConfiguracionManager.getTitleForGrafic(sensor.getCanal()), // title
                "Tiempo", // x-axis label
                this.sensor.getMagnitud(), // y-axis label
                dataset, // data
                true, // create legend?
                true, // generate tooltips?
                false // generate URLs?
        );
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        XYItemRenderer r = plot.getRenderer();
        r.setSeriesPaint(0, Color.BLUE);
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(false);
            renderer.setBaseShapesFilled(true);
        }
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        return chart;

    }

    public JPanel createCharPanel(JFreeChart chart) {
        ChartPanel charPanel = new ChartPanel(chart);
        charPanel.setPreferredSize(new java.awt.Dimension(600, 270));
        charPanel.setMouseZoomable(true, false);
        //this.frame.setContentPane(charPanel);
        return charPanel;
    }

}
