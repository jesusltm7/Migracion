/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KALTEC04
 */
@Entity
@Table(name = "USUARIOS_TOMADOS_AFILIACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosTomadosAfiliacion.findAll", query = "SELECT u FROM UsuariosTomadosAfiliacion u"),
    @NamedQuery(name = "UsuariosTomadosAfiliacion.findById", query = "SELECT u FROM UsuariosTomadosAfiliacion u WHERE u.id = :id"),
    @NamedQuery(name = "UsuariosTomadosAfiliacion.findByLogin", query = "SELECT u FROM UsuariosTomadosAfiliacion u WHERE u.login = :login"),
    @NamedQuery(name = "UsuariosTomadosAfiliacion.findByTipoIdentificacion", query = "SELECT u FROM UsuariosTomadosAfiliacion u WHERE u.tipoIdentificacion = :tipoIdentificacion"),
    @NamedQuery(name = "UsuariosTomadosAfiliacion.findByIdentificacion", query = "SELECT u FROM UsuariosTomadosAfiliacion u WHERE u.identificacion = :identificacion"),
    @NamedQuery(name = "UsuariosTomadosAfiliacion.findByFechaHora", query = "SELECT u FROM UsuariosTomadosAfiliacion u WHERE u.fechaHora = :fechaHora"),
    @NamedQuery(name = "UsuariosTomadosAfiliacion.findByIp", query = "SELECT u FROM UsuariosTomadosAfiliacion u WHERE u.ip = :ip"),
    @NamedQuery(name = "UsuariosTomadosAfiliacion.findBySharedkey", query = "SELECT u FROM UsuariosTomadosAfiliacion u WHERE u.sharedkey = :sharedkey")})
public class UsuariosTomadosAfiliacion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @SequenceGenerator(name = "USUARIOS_TOMADOS_A_SEQ", sequenceName = "USUARIOS_TOMADOS_A_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_TOMADOS_A_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_IDENTIFICACION")
    private Character tipoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Basic(optional = false)
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Size(max = 45)
    @Column(name = "IP")
    private String ip;
    @Size(max = 60)
    @Column(name = "SHAREDKEY")
    private String sharedkey;

    public UsuariosTomadosAfiliacion() {
    }

    public UsuariosTomadosAfiliacion(BigDecimal id) {
        this.id = id;
    }

    public UsuariosTomadosAfiliacion(BigDecimal id, String login, Character tipoIdentificacion, String identificacion, Date fechaHora) {
        this.id = id;
        this.login = login;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.fechaHora = fechaHora;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Character getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Character tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSharedkey() {
        return sharedkey;
    }

    public void setSharedkey(String sharedkey) {
        this.sharedkey = sharedkey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosTomadosAfiliacion)) {
            return false;
        }
        UsuariosTomadosAfiliacion other = (UsuariosTomadosAfiliacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.UsuariosTomadosAfiliacion[ id=" + id + " ]";
    }
    
}
