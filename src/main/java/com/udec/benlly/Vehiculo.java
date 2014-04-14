/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.benlly;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "vehiculo", catalog = "benlly", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByIdvehiculo", query = "SELECT v FROM Vehiculo v WHERE v.idvehiculo = :idvehiculo"),
    @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa"),
    @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca"),
    @NamedQuery(name = "Vehiculo.findByModelo", query = "SELECT v FROM Vehiculo v WHERE v.modelo = :modelo"),
    @NamedQuery(name = "Vehiculo.findByNp", query = "SELECT v FROM Vehiculo v WHERE v.np = :np"),
    @NamedQuery(name = "Vehiculo.findByRc", query = "SELECT v FROM Vehiculo v WHERE v.rc = :rc"),
    @NamedQuery(name = "Vehiculo.findByRd", query = "SELECT v FROM Vehiculo v WHERE v.rd = :rd"),
    @NamedQuery(name = "Vehiculo.findByRr", query = "SELECT v FROM Vehiculo v WHERE v.rr = :rr")})
public class Vehiculo implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvehiculo")
    private Integer idvehiculo;
    @Column(name = "placa")
    private String placa;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "np")
    private Integer np;
    @Column(name = "rc")
    private Integer rc;
    @Column(name = "rd")
    private Integer rd;
    @Column(name = "rr")
    private Integer rr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculoIdvehiculo")
    private List<Sensor> sensorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculoIdvehiculo")
    private List<Recorridoorig> recorridoorigList;

    public Vehiculo() {
    }

    public Vehiculo(Integer idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public Integer getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Integer idvehiculo) {
        Integer oldIdvehiculo = this.idvehiculo;
        this.idvehiculo = idvehiculo;
        changeSupport.firePropertyChange("idvehiculo", oldIdvehiculo, idvehiculo);
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        String oldPlaca = this.placa;
        this.placa = placa;
        changeSupport.firePropertyChange("placa", oldPlaca, placa);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        String oldMarca = this.marca;
        this.marca = marca;
        changeSupport.firePropertyChange("marca", oldMarca, marca);
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        String oldModelo = this.modelo;
        this.modelo = modelo;
        changeSupport.firePropertyChange("modelo", oldModelo, modelo);
    }

    public Integer getNp() {
        return np;
    }

    public void setNp(Integer np) {
        Integer oldNp = this.np;
        this.np = np;
        changeSupport.firePropertyChange("np", oldNp, np);
    }

    public Integer getRc() {
        return rc;
    }

    public void setRc(Integer rc) {
        Integer oldRc = this.rc;
        this.rc = rc;
        changeSupport.firePropertyChange("rc", oldRc, rc);
    }

    public Integer getRd() {
        return rd;
    }

    public void setRd(Integer rd) {
        Integer oldRd = this.rd;
        this.rd = rd;
        changeSupport.firePropertyChange("rd", oldRd, rd);
    }

    public Integer getRr() {
        return rr;
    }

    public void setRr(Integer rr) {
        Integer oldRr = this.rr;
        this.rr = rr;
        changeSupport.firePropertyChange("rr", oldRr, rr);
    }

    @XmlTransient
    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

    @XmlTransient
    public List<Recorridoorig> getRecorridoorigList() {
        return recorridoorigList;
    }

    public void setRecorridoorigList(List<Recorridoorig> recorridoorigList) {
        this.recorridoorigList = recorridoorigList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvehiculo != null ? idvehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idvehiculo == null && other.idvehiculo != null) || (this.idvehiculo != null && !this.idvehiculo.equals(other.idvehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.benlly.Vehiculo[ idvehiculo=" + idvehiculo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
