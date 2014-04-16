/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.benlly;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "sensordigital", catalog = "proyecto", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensordigital.findAll", query = "SELECT s FROM Sensordigital s"),
    @NamedQuery(name = "Sensordigital.findBySensoridsensor", query = "SELECT s FROM Sensordigital s WHERE s.sensoridsensor = :sensoridsensor"),
    @NamedQuery(name = "Sensordigital.findByPulsosRevolucion", query = "SELECT s FROM Sensordigital s WHERE s.pulsosRevolucion = :pulsosRevolucion")})
public class Sensordigital implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Sensor_idsensor")
    private Integer sensoridsensor;
    @Basic(optional = false)
    @Column(name = "pulsos_revolucion")
    private float pulsosRevolucion;
    @Lob
    @Column(name = "otros_datos")
    private String otrosDatos;
    @JoinColumn(name = "Sensor_idsensor", referencedColumnName = "idsensor", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Sensor sensor;

    public Sensordigital() {
    }

    public Sensordigital(Integer sensoridsensor) {
        this.sensoridsensor = sensoridsensor;
    }

    public Sensordigital(Integer sensoridsensor, float pulsosRevolucion) {
        this.sensoridsensor = sensoridsensor;
        this.pulsosRevolucion = pulsosRevolucion;
    }

    public Integer getSensoridsensor() {
        return sensoridsensor;
    }

    public void setSensoridsensor(Integer sensoridsensor) {
        Integer oldSensoridsensor = this.sensoridsensor;
        this.sensoridsensor = sensoridsensor;
        changeSupport.firePropertyChange("sensoridsensor", oldSensoridsensor, sensoridsensor);
    }

    public float getPulsosRevolucion() {
        return pulsosRevolucion;
    }

    public void setPulsosRevolucion(float pulsosRevolucion) {
        float oldPulsosRevolucion = this.pulsosRevolucion;
        this.pulsosRevolucion = pulsosRevolucion;
        changeSupport.firePropertyChange("pulsosRevolucion", oldPulsosRevolucion, pulsosRevolucion);
    }

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        String oldOtrosDatos = this.otrosDatos;
        this.otrosDatos = otrosDatos;
        changeSupport.firePropertyChange("otrosDatos", oldOtrosDatos, otrosDatos);
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sensoridsensor != null ? sensoridsensor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensordigital)) {
            return false;
        }
        Sensordigital other = (Sensordigital) object;
        if ((this.sensoridsensor == null && other.sensoridsensor != null) || (this.sensoridsensor != null && !this.sensoridsensor.equals(other.sensoridsensor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.benlly.Sensordigital[ sensoridsensor=" + sensoridsensor + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
