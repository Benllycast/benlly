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
@Table(name = "sensoranalogo", catalog = "proyecto", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensoranalogo.findAll", query = "SELECT s FROM Sensoranalogo s"),
    @NamedQuery(name = "Sensoranalogo.findBySensoridsensor", query = "SELECT s FROM Sensoranalogo s WHERE s.sensoridsensor = :sensoridsensor"),
    @NamedQuery(name = "Sensoranalogo.findByMagnitudMaxima", query = "SELECT s FROM Sensoranalogo s WHERE s.magnitudMaxima = :magnitudMaxima"),
    @NamedQuery(name = "Sensoranalogo.findByMagnitudMinima", query = "SELECT s FROM Sensoranalogo s WHERE s.magnitudMinima = :magnitudMinima"),
    @NamedQuery(name = "Sensoranalogo.findBySalidaMaxima", query = "SELECT s FROM Sensoranalogo s WHERE s.salidaMaxima = :salidaMaxima"),
    @NamedQuery(name = "Sensoranalogo.findBySalidaMinima", query = "SELECT s FROM Sensoranalogo s WHERE s.salidaMinima = :salidaMinima"),
    @NamedQuery(name = "Sensoranalogo.findByDivisorFrecuencia", query = "SELECT s FROM Sensoranalogo s WHERE s.divisorFrecuencia = :divisorFrecuencia")})
public class Sensoranalogo implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Sensor_idsensor")
    private Integer sensoridsensor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "magnitud_maxima")
    private Float magnitudMaxima;
    @Column(name = "magnitud_minima")
    private Float magnitudMinima;
    @Column(name = "salida_maxima")
    private Float salidaMaxima;
    @Column(name = "salida_minima")
    private Float salidaMinima;
    @Column(name = "divisor_frecuencia")
    private Float divisorFrecuencia;
    @Lob
    @Column(name = "otros_datos")
    private String otrosDatos;
    @JoinColumn(name = "Sensor_idsensor", referencedColumnName = "idsensor", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Sensor sensor;

    public Sensoranalogo() {
    }

    public Sensoranalogo(Integer sensoridsensor) {
        this.sensoridsensor = sensoridsensor;
    }

    public Integer getSensoridsensor() {
        return sensoridsensor;
    }

    public void setSensoridsensor(Integer sensoridsensor) {
        Integer oldSensoridsensor = this.sensoridsensor;
        this.sensoridsensor = sensoridsensor;
        changeSupport.firePropertyChange("sensoridsensor", oldSensoridsensor, sensoridsensor);
    }

    public Float getMagnitudMaxima() {
        return magnitudMaxima;
    }

    public void setMagnitudMaxima(Float magnitudMaxima) {
        Float oldMagnitudMaxima = this.magnitudMaxima;
        this.magnitudMaxima = magnitudMaxima;
        changeSupport.firePropertyChange("magnitudMaxima", oldMagnitudMaxima, magnitudMaxima);
    }

    public Float getMagnitudMinima() {
        return magnitudMinima;
    }

    public void setMagnitudMinima(Float magnitudMinima) {
        Float oldMagnitudMinima = this.magnitudMinima;
        this.magnitudMinima = magnitudMinima;
        changeSupport.firePropertyChange("magnitudMinima", oldMagnitudMinima, magnitudMinima);
    }

    public Float getSalidaMaxima() {
        return salidaMaxima;
    }

    public void setSalidaMaxima(Float salidaMaxima) {
        Float oldSalidaMaxima = this.salidaMaxima;
        this.salidaMaxima = salidaMaxima;
        changeSupport.firePropertyChange("salidaMaxima", oldSalidaMaxima, salidaMaxima);
    }

    public Float getSalidaMinima() {
        return salidaMinima;
    }

    public void setSalidaMinima(Float salidaMinima) {
        Float oldSalidaMinima = this.salidaMinima;
        this.salidaMinima = salidaMinima;
        changeSupport.firePropertyChange("salidaMinima", oldSalidaMinima, salidaMinima);
    }

    public Float getDivisorFrecuencia() {
        return divisorFrecuencia;
    }

    public void setDivisorFrecuencia(Float divisorFrecuencia) {
        Float oldDivisorFrecuencia = this.divisorFrecuencia;
        this.divisorFrecuencia = divisorFrecuencia;
        changeSupport.firePropertyChange("divisorFrecuencia", oldDivisorFrecuencia, divisorFrecuencia);
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
        if (!(object instanceof Sensoranalogo)) {
            return false;
        }
        Sensoranalogo other = (Sensoranalogo) object;
        if ((this.sensoridsensor == null && other.sensoridsensor != null) || (this.sensoridsensor != null && !this.sensoridsensor.equals(other.sensoridsensor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.benlly.Sensoranalogo[ sensoridsensor=" + sensoridsensor + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
