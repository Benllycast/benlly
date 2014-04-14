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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "sensor", catalog = "benlly", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensor.findAll", query = "SELECT s FROM Sensor s"),
    @NamedQuery(name = "Sensor.findByIdsensor", query = "SELECT s FROM Sensor s WHERE s.idsensor = :idsensor"),
    @NamedQuery(name = "Sensor.findByValor", query = "SELECT s FROM Sensor s WHERE s.valor = :valor"),
    @NamedQuery(name = "Sensor.findByTipo", query = "SELECT s FROM Sensor s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "Sensor.findByConsecutivoVehiculo", query = "SELECT s FROM Sensor s WHERE s.consecutivoVehiculo = :consecutivoVehiculo"),
    @NamedQuery(name = "Sensor.findByFosc", query = "SELECT s FROM Sensor s WHERE s.fosc = :fosc"),
    @NamedQuery(name = "Sensor.findByPreescaler", query = "SELECT s FROM Sensor s WHERE s.preescaler = :preescaler")})
public class Sensor implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsensor")
    private Integer idsensor;
    @Column(name = "valor")
    private String valor;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "consecutivo_vehiculo")
    private Integer consecutivoVehiculo;
    @Column(name = "FOSC")
    private Integer fosc;
    @Column(name = "preescaler")
    private Integer preescaler;
    @JoinColumn(name = "vehiculo_idvehiculo", referencedColumnName = "idvehiculo")
    @ManyToOne(optional = false)
    private Vehiculo vehiculoIdvehiculo;

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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        String oldValor = this.valor;
        this.valor = valor;
        changeSupport.firePropertyChange("valor", oldValor, valor);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    public Integer getConsecutivoVehiculo() {
        return consecutivoVehiculo;
    }

    public void setConsecutivoVehiculo(Integer consecutivoVehiculo) {
        Integer oldConsecutivoVehiculo = this.consecutivoVehiculo;
        this.consecutivoVehiculo = consecutivoVehiculo;
        changeSupport.firePropertyChange("consecutivoVehiculo", oldConsecutivoVehiculo, consecutivoVehiculo);
    }

    public Integer getFosc() {
        return fosc;
    }

    public void setFosc(Integer fosc) {
        Integer oldFosc = this.fosc;
        this.fosc = fosc;
        changeSupport.firePropertyChange("fosc", oldFosc, fosc);
    }

    public Integer getPreescaler() {
        return preescaler;
    }

    public void setPreescaler(Integer preescaler) {
        Integer oldPreescaler = this.preescaler;
        this.preescaler = preescaler;
        changeSupport.firePropertyChange("preescaler", oldPreescaler, preescaler);
    }

    public Vehiculo getVehiculoIdvehiculo() {
        return vehiculoIdvehiculo;
    }

    public void setVehiculoIdvehiculo(Vehiculo vehiculoIdvehiculo) {
        Vehiculo oldVehiculoIdvehiculo = this.vehiculoIdvehiculo;
        this.vehiculoIdvehiculo = vehiculoIdvehiculo;
        changeSupport.firePropertyChange("vehiculoIdvehiculo", oldVehiculoIdvehiculo, vehiculoIdvehiculo);
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
