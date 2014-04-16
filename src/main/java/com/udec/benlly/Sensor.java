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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "sensor", catalog = "proyecto", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensor.findAll", query = "SELECT s FROM Sensor s"),
    @NamedQuery(name = "Sensor.findByIdsensor", query = "SELECT s FROM Sensor s WHERE s.idsensor = :idsensor"),
    @NamedQuery(name = "Sensor.findBySerial", query = "SELECT s FROM Sensor s WHERE s.serial = :serial"),
    @NamedQuery(name = "Sensor.findByTipo", query = "SELECT s FROM Sensor s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "Sensor.findByCanal", query = "SELECT s FROM Sensor s WHERE s.canal = :canal"),
    @NamedQuery(name = "Sensor.findByMagnitud", query = "SELECT s FROM Sensor s WHERE s.magnitud = :magnitud")})
public class Sensor implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsensor")
    private Integer idsensor;
    @Column(name = "serial")
    private Long serial;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "canal")
    private Short canal;
    @Column(name = "magnitud")
    private String magnitud;
    @Lob
    @Column(name = "uvicacion")
    private String uvicacion;
    @Lob
    @Column(name = "otros_datos")
    private String otrosDatos;
    @JoinColumn(name = "Vehiculo_idVehiculo", referencedColumnName = "idVehiculo")
    @ManyToOne(optional = false)
    private Vehiculo vehiculoidVehiculo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sensor")
    private Sensoranalogo sensoranalogo;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sensor")
    private Sensordigital sensordigital;

    public Sensor() {
    }

    public Sensor(Integer idsensor) {
        this.idsensor = idsensor;
    }

    public Integer getIdsensor() {
        return idsensor;
    }

    public void setIdsensor(Integer idsensor) {
        Integer oldIdsensor = this.idsensor;
        this.idsensor = idsensor;
        changeSupport.firePropertyChange("idsensor", oldIdsensor, idsensor);
    }

    public Long getSerial() {
        return serial;
    }

    public void setSerial(Long serial) {
        Long oldSerial = this.serial;
        this.serial = serial;
        changeSupport.firePropertyChange("serial", oldSerial, serial);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    public Short getCanal() {
        return canal;
    }

    public void setCanal(Short canal) {
        Short oldCanal = this.canal;
        this.canal = canal;
        changeSupport.firePropertyChange("canal", oldCanal, canal);
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        String oldMagnitud = this.magnitud;
        this.magnitud = magnitud;
        changeSupport.firePropertyChange("magnitud", oldMagnitud, magnitud);
    }

    public String getUvicacion() {
        return uvicacion;
    }

    public void setUvicacion(String uvicacion) {
        String oldUvicacion = this.uvicacion;
        this.uvicacion = uvicacion;
        changeSupport.firePropertyChange("uvicacion", oldUvicacion, uvicacion);
    }

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        String oldOtrosDatos = this.otrosDatos;
        this.otrosDatos = otrosDatos;
        changeSupport.firePropertyChange("otrosDatos", oldOtrosDatos, otrosDatos);
    }

    public Vehiculo getVehiculoidVehiculo() {
        return vehiculoidVehiculo;
    }

    public void setVehiculoidVehiculo(Vehiculo vehiculoidVehiculo) {
        Vehiculo oldVehiculoidVehiculo = this.vehiculoidVehiculo;
        this.vehiculoidVehiculo = vehiculoidVehiculo;
        changeSupport.firePropertyChange("vehiculoidVehiculo", oldVehiculoidVehiculo, vehiculoidVehiculo);
    }

    public Sensoranalogo getSensoranalogo() {
        return sensoranalogo;
    }

    public void setSensoranalogo(Sensoranalogo sensoranalogo) {
        this.sensoranalogo = sensoranalogo;
    }

    public Sensordigital getSensordigital() {
        return sensordigital;
    }

    public void setSensordigital(Sensordigital sensordigital) {
        this.sensordigital = sensordigital;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsensor != null ? idsensor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensor)) {
            return false;
        }
        Sensor other = (Sensor) object;
        if ((this.idsensor == null && other.idsensor != null) || (this.idsensor != null && !this.idsensor.equals(other.idsensor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.benlly.Sensor[ idsensor=" + idsensor + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
