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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "log", catalog = "benlly", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l"),
    @NamedQuery(name = "Log.findByIdlog", query = "SELECT l FROM Log l WHERE l.idlog = :idlog"),
    @NamedQuery(name = "Log.findByFecha", query = "SELECT l FROM Log l WHERE l.fecha = :fecha"),
    @NamedQuery(name = "Log.findByHora", query = "SELECT l FROM Log l WHERE l.hora = :hora"),
    @NamedQuery(name = "Log.findByNumeroDato", query = "SELECT l FROM Log l WHERE l.numeroDato = :numeroDato"),
    @NamedQuery(name = "Log.findByValorObbtenido", query = "SELECT l FROM Log l WHERE l.valorObbtenido = :valorObbtenido"),
    @NamedQuery(name = "Log.findByCrc", query = "SELECT l FROM Log l WHERE l.crc = :crc"),
    @NamedQuery(name = "Log.findByConsecutivo", query = "SELECT l FROM Log l WHERE l.consecutivo = :consecutivo")})
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlog")
    private Integer idlog;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "hora")
    @Temporal(TemporalType.DATE)
    private Date hora;
    @Column(name = "numero_dato")
    private Integer numeroDato;
    @Column(name = "valor_obbtenido")
    private Integer valorObbtenido;
    @Column(name = "crc")
    private String crc;
    @Column(name = "consecutivo")
    private Integer consecutivo;
    @JoinColumn(name = "recorridoorig_idrecorridoorig", referencedColumnName = "idrecorridoorig")
    @ManyToOne(optional = false)
    private Recorridoorig recorridoorigIdrecorridoorig;

    public Log() {
    }

    public Log(Integer idlog) {
        this.idlog = idlog;
    }

    public Integer getIdlog() {
        return idlog;
    }

    public void setIdlog(Integer idlog) {
        this.idlog = idlog;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getNumeroDato() {
        return numeroDato;
    }

    public void setNumeroDato(Integer numeroDato) {
        this.numeroDato = numeroDato;
    }

    public Integer getValorObbtenido() {
        return valorObbtenido;
    }

    public void setValorObbtenido(Integer valorObbtenido) {
        this.valorObbtenido = valorObbtenido;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Recorridoorig getRecorridoorigIdrecorridoorig() {
        return recorridoorigIdrecorridoorig;
    }

    public void setRecorridoorigIdrecorridoorig(Recorridoorig recorridoorigIdrecorridoorig) {
        this.recorridoorigIdrecorridoorig = recorridoorigIdrecorridoorig;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlog != null ? idlog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.idlog == null && other.idlog != null) || (this.idlog != null && !this.idlog.equals(other.idlog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.benlly.Log[ idlog=" + idlog + " ]";
    }
    
}
