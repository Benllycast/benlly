/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.benlly;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "recorrido", catalog = "proyecto", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recorrido.findAll", query = "SELECT r FROM Recorrido r"),
    @NamedQuery(name = "Recorrido.findByIdRecorrido", query = "SELECT r FROM Recorrido r WHERE r.idRecorrido = :idRecorrido"),
    @NamedQuery(name = "Recorrido.findByNombre", query = "SELECT r FROM Recorrido r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Recorrido.findByFecha", query = "SELECT r FROM Recorrido r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "Recorrido.findByHoraSalidaProgramada", query = "SELECT r FROM Recorrido r WHERE r.horaSalidaProgramada = :horaSalidaProgramada"),
    @NamedQuery(name = "Recorrido.findByHoraLlegadaProgramada", query = "SELECT r FROM Recorrido r WHERE r.horaLlegadaProgramada = :horaLlegadaProgramada"),
    @NamedQuery(name = "Recorrido.findByHoraInicio", query = "SELECT r FROM Recorrido r WHERE r.horaInicio = :horaInicio"),
    @NamedQuery(name = "Recorrido.findByHoraFinalizacion", query = "SELECT r FROM Recorrido r WHERE r.horaFinalizacion = :horaFinalizacion")})
public class Recorrido implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRecorrido")
    private Integer idRecorrido;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora_salida_programada")
    @Temporal(TemporalType.TIME)
    private Date horaSalidaProgramada;
    @Column(name = "hora_llegada_programada")
    @Temporal(TemporalType.TIME)
    private Date horaLlegadaProgramada;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_finalizacion")
    @Temporal(TemporalType.TIME)
    private Date horaFinalizacion;
    @Lob
    @Column(name = "otros_datos")
    private String otrosDatos;
    @JoinColumn(name = "Conductor_idConductor", referencedColumnName = "idConductor")
    @ManyToOne(optional = false)
    private Conductor conductoridConductor;
    @JoinColumn(name = "Vehiculo_idVehiculo", referencedColumnName = "idVehiculo")
    @ManyToOne(optional = false)
    private Vehiculo vehiculoidVehiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recorridoidRecorrido")
    private List<Log> logList;

    public Recorrido() {
    }

    public Recorrido(Integer idRecorrido) {
        this.idRecorrido = idRecorrido;
    }

    public Integer getIdRecorrido() {
        return idRecorrido;
    }

    public void setIdRecorrido(Integer idRecorrido) {
        Integer oldIdRecorrido = this.idRecorrido;
        this.idRecorrido = idRecorrido;
        changeSupport.firePropertyChange("idRecorrido", oldIdRecorrido, idRecorrido);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        Date oldFecha = this.fecha;
        this.fecha = fecha;
        changeSupport.firePropertyChange("fecha", oldFecha, fecha);
    }

    public Date getHoraSalidaProgramada() {
        return horaSalidaProgramada;
    }

    public void setHoraSalidaProgramada(Date horaSalidaProgramada) {
        Date oldHoraSalidaProgramada = this.horaSalidaProgramada;
        this.horaSalidaProgramada = horaSalidaProgramada;
        changeSupport.firePropertyChange("horaSalidaProgramada", oldHoraSalidaProgramada, horaSalidaProgramada);
    }

    public Date getHoraLlegadaProgramada() {
        return horaLlegadaProgramada;
    }

    public void setHoraLlegadaProgramada(Date horaLlegadaProgramada) {
        Date oldHoraLlegadaProgramada = this.horaLlegadaProgramada;
        this.horaLlegadaProgramada = horaLlegadaProgramada;
        changeSupport.firePropertyChange("horaLlegadaProgramada", oldHoraLlegadaProgramada, horaLlegadaProgramada);
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        Date oldHoraInicio = this.horaInicio;
        this.horaInicio = horaInicio;
        changeSupport.firePropertyChange("horaInicio", oldHoraInicio, horaInicio);
    }

    public Date getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(Date horaFinalizacion) {
        Date oldHoraFinalizacion = this.horaFinalizacion;
        this.horaFinalizacion = horaFinalizacion;
        changeSupport.firePropertyChange("horaFinalizacion", oldHoraFinalizacion, horaFinalizacion);
    }

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        String oldOtrosDatos = this.otrosDatos;
        this.otrosDatos = otrosDatos;
        changeSupport.firePropertyChange("otrosDatos", oldOtrosDatos, otrosDatos);
    }

    public Conductor getConductoridConductor() {
        return conductoridConductor;
    }

    public void setConductoridConductor(Conductor conductoridConductor) {
        Conductor oldConductoridConductor = this.conductoridConductor;
        this.conductoridConductor = conductoridConductor;
        changeSupport.firePropertyChange("conductoridConductor", oldConductoridConductor, conductoridConductor);
    }

    public Vehiculo getVehiculoidVehiculo() {
        return vehiculoidVehiculo;
    }

    public void setVehiculoidVehiculo(Vehiculo vehiculoidVehiculo) {
        Vehiculo oldVehiculoidVehiculo = this.vehiculoidVehiculo;
        this.vehiculoidVehiculo = vehiculoidVehiculo;
        changeSupport.firePropertyChange("vehiculoidVehiculo", oldVehiculoidVehiculo, vehiculoidVehiculo);
    }

    @XmlTransient
    public List<Log> getLogList() {
        return logList;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecorrido != null ? idRecorrido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recorrido)) {
            return false;
        }
        Recorrido other = (Recorrido) object;
        if ((this.idRecorrido == null && other.idRecorrido != null) || (this.idRecorrido != null && !this.idRecorrido.equals(other.idRecorrido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.benlly.Recorrido[ idRecorrido=" + idRecorrido + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
