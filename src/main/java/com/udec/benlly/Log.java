/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.benlly;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "log", catalog = "proyecto", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l"),
    @NamedQuery(name = "Log.findByIdLog", query = "SELECT l FROM Log l WHERE l.idLog = :idLog"),
    @NamedQuery(name = "Log.findByFecha", query = "SELECT l FROM Log l WHERE l.fecha = :fecha"),
    @NamedQuery(name = "Log.findByTiempo", query = "SELECT l FROM Log l WHERE l.tiempo = :tiempo"),
    @NamedQuery(name = "Log.findByNumeroDato", query = "SELECT l FROM Log l WHERE l.numeroDato = :numeroDato"),
    @NamedQuery(name = "Log.findByCanal", query = "SELECT l FROM Log l WHERE l.canal = :canal"),
    @NamedQuery(name = "Log.findByValor", query = "SELECT l FROM Log l WHERE l.valor = :valor"),
    @NamedQuery(name = "Log.findByCrc", query = "SELECT l FROM Log l WHERE l.crc = :crc")})
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idLog")
    private Integer idLog;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "tiempo")
    @Temporal(TemporalType.TIME)
    private Date tiempo;
    @Column(name = "numero_dato")
    private Short numeroDato;
    @Column(name = "canal")
    private Short canal;
    @Column(name = "valor")
    private Integer valor;
    @Column(name = "crc")
    private Integer crc;
    @Lob
    @Column(name = "otros_datos")
    private String otrosDatos;
    @JoinColumn(name = "Recorrido_idRecorrido", referencedColumnName = "idRecorrido")
    @ManyToOne(optional = false)
    private Recorrido recorridoidRecorrido;

    public Log() {
    }

    public Log(Integer idLog) {
        this.idLog = idLog;
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public Short getNumeroDato() {
        return numeroDato;
    }

    public void setNumeroDato(Short numeroDato) {
        this.numeroDato = numeroDato;
    }

    public Short getCanal() {
        return canal;
    }

    public void setCanal(Short canal) {
        this.canal = canal;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getCrc() {
        return crc;
    }

    public void setCrc(Integer crc) {
        this.crc = crc;
    }

    public String getOtrosDatos() {
        return otrosDatos;
    }

    public void setOtrosDatos(String otrosDatos) {
        this.otrosDatos = otrosDatos;
    }

    public Recorrido getRecorridoidRecorrido() {
        return recorridoidRecorrido;
    }

    public void setRecorridoidRecorrido(Recorrido recorridoidRecorrido) {
        this.recorridoidRecorrido = recorridoidRecorrido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLog != null ? idLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.idLog == null && other.idLog != null) || (this.idLog != null && !this.idLog.equals(other.idLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.benlly.Log[ idLog=" + idLog + " ]";
    }
    
}
