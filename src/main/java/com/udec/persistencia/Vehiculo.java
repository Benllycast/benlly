/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.persistencia;

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
import javax.persistence.Lob;
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
@Table(name = "vehiculo", catalog = "proyecto", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v"),
    @NamedQuery(name = "Vehiculo.findByIdVehiculo", query = "SELECT v FROM Vehiculo v WHERE v.idVehiculo = :idVehiculo"),
    @NamedQuery(name = "Vehiculo.findByPlaca", query = "SELECT v FROM Vehiculo v WHERE v.placa = :placa"),
    @NamedQuery(name = "Vehiculo.findByMarca", query = "SELECT v FROM Vehiculo v WHERE v.marca = :marca"),
    @NamedQuery(name = "Vehiculo.findByModelo", query = "SELECT v FROM Vehiculo v WHERE v.modelo = :modelo"),
    @NamedQuery(name = "Vehiculo.findByRadioRueda", query = "SELECT v FROM Vehiculo v WHERE v.radioRueda = :radioRueda"),
    @NamedQuery(name = "Vehiculo.findByRelacionCaja", query = "SELECT v FROM Vehiculo v WHERE v.relacionCaja = :relacionCaja"),
    @NamedQuery(name = "Vehiculo.findByRelacionDiferencial", query = "SELECT v FROM Vehiculo v WHERE v.relacionDiferencial = :relacionDiferencial")})
public class Vehiculo implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVehiculo")
    private Integer idVehiculo;
    @Column(name = "placa")
    private String placa;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private Short modelo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "radio_rueda")
    private Float radioRueda;
    @Column(name = "relacion_caja")
    private Float relacionCaja;
    @Column(name = "relacion_diferencial")
    private Float relacionDiferencial;
    @Lob
    @Column(name = "otros_datos")
    private String otrosDatos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculoidVehiculo")
    private List<Sensor> sensorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehiculoidVehiculo")
    private List<Recorrido> recorridoList;

    public Vehiculo() {
    }

    public Vehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        Integer oldIdVehiculo = this.idVehiculo;
        this.idVehiculo = idVehiculo;
        changeSupport.firePropertyChange("idVehiculo", oldIdVehiculo, idVehiculo);
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

    public Short getModelo() {
        return modelo;
    }

    public void setModelo(Short modelo) {
        Short oldModelo = this.modelo;
        this.modelo = modelo;
        changeSupport.firePropertyChange("modelo", oldModelo, modelo);
    }

    public Float getRadioRueda() {
        return radioRueda;
    }

    public void setRadioRueda(Float radioRueda) {
        Float oldRadioRueda = this.radioRueda;
        this.radioRueda = radioRueda;
        changeSupport.firePropertyChange("radioRueda", oldRadioRueda, radioRueda);
    }

    public Float getRelacionCaja() {
        return relacionCaja;
    }

    public void setRelacionCaja(Float relacionCaja) {
        Float oldRelacionCaja = this.relacionCaja;
        this.relacionCaja = relacionCaja;
        changeSupport.firePropertyChange("relacionCaja", oldRelacionCaja, relacionCaja);
    }

    public Float getRelacionDiferencial() {
        return relacionDiferencial;
    }

    public void setRelacionDiferencial(Float relacionDiferencial) {
        Float oldRelacionDiferencial = this.relacionDiferencial;
        this.relacionDiferencial = relacionDiferencial;
        changeSupport.firePropertyChange("relacionDiferencial", oldRelacionDiferencial, relacionDiferencial);
    }

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        String oldOtrosDatos = this.otrosDatos;
        this.otrosDatos = otrosDatos;
        changeSupport.firePropertyChange("otrosDatos", oldOtrosDatos, otrosDatos);
    }

    @XmlTransient
    public List<Sensor> getSensorList() {
        return sensorList;
    }

    public void setSensorList(List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

    @XmlTransient
    public List<Recorrido> getRecorridoList() {
        return recorridoList;
    }

    public void setRecorridoList(List<Recorrido> recorridoList) {
        this.recorridoList = recorridoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.benlly.Vehiculo[ idVehiculo=" + idVehiculo + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
