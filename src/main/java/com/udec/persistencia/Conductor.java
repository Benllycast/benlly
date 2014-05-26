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
@Table(name = "conductor", catalog = "proyecto", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conductor.findAll", query = "SELECT c FROM Conductor c"),
    @NamedQuery(name = "Conductor.findByIdConductor", query = "SELECT c FROM Conductor c WHERE c.idConductor = :idConductor"),
    @NamedQuery(name = "Conductor.findByIdentificacion", query = "SELECT c FROM Conductor c WHERE c.identificacion = :identificacion"),
    @NamedQuery(name = "Conductor.findByPrimerNombre", query = "SELECT c FROM Conductor c WHERE c.primerNombre = :primerNombre"),
    @NamedQuery(name = "Conductor.findBySegundoNombre", query = "SELECT c FROM Conductor c WHERE c.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "Conductor.findByPrimerApellido", query = "SELECT c FROM Conductor c WHERE c.primerApellido = :primerApellido"),
    @NamedQuery(name = "Conductor.findBySegundoApellido", query = "SELECT c FROM Conductor c WHERE c.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "Conductor.findByLicencia", query = "SELECT c FROM Conductor c WHERE c.licencia = :licencia")})
public class Conductor implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConductor")
    private Integer idConductor;
    @Basic(optional = false)
    @Column(name = "identificacion")
    private int identificacion;
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Column(name = "licencia")
    private Integer licencia;
    @Lob
    @Column(name = "otros_datos")
    private String otrosDatos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conductoridConductor")
    private List<Recorrido> recorridoList;

    public Conductor() {
    }

    public Conductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public Conductor(Integer idConductor, int identificacion) {
        this.idConductor = idConductor;
        this.identificacion = identificacion;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Integer idConductor) {
        Integer oldIdConductor = this.idConductor;
        this.idConductor = idConductor;
        changeSupport.firePropertyChange("idConductor", oldIdConductor, idConductor);
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        int oldIdentificacion = this.identificacion;
        this.identificacion = identificacion;
        changeSupport.firePropertyChange("identificacion", oldIdentificacion, identificacion);
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        String oldPrimerNombre = this.primerNombre;
        this.primerNombre = primerNombre;
        changeSupport.firePropertyChange("primerNombre", oldPrimerNombre, primerNombre);
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        String oldSegundoNombre = this.segundoNombre;
        this.segundoNombre = segundoNombre;
        changeSupport.firePropertyChange("segundoNombre", oldSegundoNombre, segundoNombre);
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        String oldPrimerApellido = this.primerApellido;
        this.primerApellido = primerApellido;
        changeSupport.firePropertyChange("primerApellido", oldPrimerApellido, primerApellido);
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        String oldSegundoApellido = this.segundoApellido;
        this.segundoApellido = segundoApellido;
        changeSupport.firePropertyChange("segundoApellido", oldSegundoApellido, segundoApellido);
    }

    public Integer getLicencia() {
        return licencia;
    }

    public void setLicencia(Integer licencia) {
        Integer oldLicencia = this.licencia;
        this.licencia = licencia;
        changeSupport.firePropertyChange("licencia", oldLicencia, licencia);
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
    public List<Recorrido> getRecorridoList() {
        return recorridoList;
    }

    public void setRecorridoList(List<Recorrido> recorridoList) {
        this.recorridoList = recorridoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConductor != null ? idConductor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conductor)) {
            return false;
        }
        Conductor other = (Conductor) object;
        if ((this.idConductor == null && other.idConductor != null) || (this.idConductor != null && !this.idConductor.equals(other.idConductor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.benlly.Conductor[ idConductor=" + idConductor + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
