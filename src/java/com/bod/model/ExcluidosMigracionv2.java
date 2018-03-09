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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kaltec_103
 */
@Entity
@Table(name = "TMPEXCLUIDOSIB2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExcluidosMigracion.findAll", query = "SELECT e FROM ExcluidosMigracion e"),
    @NamedQuery(name = "ExcluidosMigracion.findById", query = "SELECT e FROM ExcluidosMigracion e WHERE e.id = :id"),
    @NamedQuery(name = "ExcluidosMigracion.findByTipoIdentificacion", query = "SELECT e FROM ExcluidosMigracion e WHERE e.tipoIdentificacion = :tipoIdentificacion"),
    @NamedQuery(name = "ExcluidosMigracion.findByIdentificacion", query = "SELECT e FROM ExcluidosMigracion e WHERE e.identificacion = :identificacion"),
    @NamedQuery(name = "ExcluidosMigracion.findByNumeroperfiles", query = "SELECT e FROM ExcluidosMigracion e WHERE e.numeroPerfiles = :numeroperfiles"),
    @NamedQuery(name = "ExcluidosMigracion.findByEstado", query = "SELECT e FROM ExcluidosMigracion e WHERE e.estado = :estado")})
public class ExcluidosMigracionv2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "PK_IDEXCLUIDO")
    private String id;
    @Column(name = "TIPIDENTIFICADOR")
    private Character tipoIdentificacion;
    @Size(max = 45)
    @Column(name = "CODIDENTIFICADOR")
    private String identificacion;
    @Column(name = "NUMPERFIL")
    private int numeroPerfiles;
    @Column(name = "ESTEXCLUIDO")
    private Character estado;

    public ExcluidosMigracionv2() {
    }

    public ExcluidosMigracionv2(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getNumeroPerfiles() {
        return numeroPerfiles;
    }

    public void setNumeroPerfiles(int numeroPerfiles) {
        this.numeroPerfiles = numeroPerfiles;
    }

   

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
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
        if (!(object instanceof ExcluidosMigracion)) {
            return false;
        }
        ExcluidosMigracionv2 other = (ExcluidosMigracionv2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.ExcluidosMigracion[ id=" + id + " ]";
    }
    
}
