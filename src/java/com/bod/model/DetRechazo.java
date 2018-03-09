/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CF
 */
@Entity
@Table(name = "DetRechazo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetRechazo.findAll", query = "SELECT d FROM DetRechazo d"),
    @NamedQuery(name = "DetRechazo.findByPkIdDetRechazo", query = "SELECT d FROM DetRechazo d WHERE d.pkIdDetRechazo = :pkIdDetRechazo"),
    @NamedQuery(name = "DetRechazo.findByCodigoRespuesta", query = "SELECT d FROM DetRechazo d WHERE d.codigoRespuesta = :codigoRespuesta"),
    @NamedQuery(name = "DetRechazo.findByDescripcion", query = "SELECT d FROM DetRechazo d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DetRechazo.findByAcciones", query = "SELECT d FROM DetRechazo d WHERE d.acciones = :acciones")})
public class DetRechazo implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DETRECHAZO_SEQ", sequenceName = "DETRECHAZO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETRECHAZO_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_IdDetRechazo", nullable = false)
    private Long pkIdDetRechazo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "codigoRespuesta", nullable = false, length = 11)
    private String codigoRespuesta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "descripcion", nullable = false, length = 2000)
    private String descripcion;
    @Size(max = 11)
    @Column(name = "acciones", length = 11)
    private String acciones;
    @JoinColumn(name = "fk_IdTransaccion", referencedColumnName = "pk_IdTransaccionNbl")
    @ManyToOne(optional = false)    
    private MstTransaccionesNbl fkIdTransaccion;

    public DetRechazo() {
    }

    public DetRechazo(Long pkIdDetRechazo) {
        this.pkIdDetRechazo = pkIdDetRechazo;
    }

    public DetRechazo(Long pkIdDetRechazo, String codigoRespuesta, String descripcion, String acciones) {
        this.pkIdDetRechazo = pkIdDetRechazo;
        this.codigoRespuesta = codigoRespuesta;
        this.descripcion = descripcion;
        this.acciones = acciones;
    }

    public Long getPkIdDetRechazo() {
        return pkIdDetRechazo;
    }

    public void setPkIdDetRechazo(Long pkIdDetRechazo) {
        this.pkIdDetRechazo = pkIdDetRechazo;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public MstTransaccionesNbl getFkIdTransaccion() {
        return fkIdTransaccion;
    }

    public void setFkIdTransaccion(MstTransaccionesNbl fkIdTransaccion) {
        this.fkIdTransaccion = fkIdTransaccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdDetRechazo != null ? pkIdDetRechazo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetRechazo)) {
            return false;
        }
        DetRechazo other = (DetRechazo) object;
        if ((this.pkIdDetRechazo == null && other.pkIdDetRechazo != null) || (this.pkIdDetRechazo != null && !this.pkIdDetRechazo.equals(other.pkIdDetRechazo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.DetRechazo[ pkIdDetRechazo=" + pkIdDetRechazo + " ]";
    }

}
