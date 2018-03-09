/*
 * Decompiled with CFR 0_124.
 * 
 * Could not load the following classes:
 *  javax.persistence.Basic
 *  javax.persistence.Column
 *  javax.persistence.Entity
 *  javax.persistence.Id
 *  javax.persistence.NamedQueries
 *  javax.persistence.NamedQuery
 *  javax.persistence.Table
 *  javax.validation.constraints.NotNull
 *  javax.validation.constraints.Size
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

@Entity
@Table(name="TMPEXCLUIDOSIB")
@XmlRootElement
@NamedQueries(value = {
    @NamedQuery(name = "ExcluidosMigracion.findAll", query = "SELECT e FROM ExcluidosMigracion e"),
    @NamedQuery(name = "ExcluidosMigracion.findById", query = "SELECT e FROM ExcluidosMigracion e WHERE e.id = :id"),
    @NamedQuery(name = "ExcluidosMigracion.findByTipoIdentificacion", query = "SELECT e FROM ExcluidosMigracion e WHERE e.tipoIdentificacion = :tipoIdentificacion"),
    @NamedQuery(name = "ExcluidosMigracion.findByIdentificacion", query = "SELECT e FROM ExcluidosMigracion e WHERE e.identificacion = :identificacion"),
    @NamedQuery(name = "ExcluidosMigracion.findByNumeroperfiles", query = "SELECT e FROM ExcluidosMigracion e WHERE e.numeroPerfiles = :numeroperfiles"),
    @NamedQuery(name = "ExcluidosMigracion.findByEstado", query = "SELECT e FROM ExcluidosMigracion e WHERE e.estado = :estado")})
public class ExcluidosMigracion
implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional=false)
    @NotNull
    @Size(min=1, max=11)
    @Column(name="PK_IDEXCLUIDO")
    private String id;
    @Column(name="TIPIDENTIFICADOR")
    private Character tipoIdentificacion;
    @Size(max=45)
    @Column(name="CODIDENTIFICADOR")
    private String identificacion;
    @Column(name="NUMPERFIL")
    private int numeroPerfiles;
    @Column(name="ESTEXCLUIDO")
    private Character estado;
    @Column(name="CODPROCESO")
    private int codigoProceso;

    public ExcluidosMigracion() {
    }

    public ExcluidosMigracion(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Character getTipoIdentificacion() {
        return this.tipoIdentificacion;
    }

    public void setTipoIdentificacion(Character tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return this.identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public int getNumeroPerfiles() {
        return this.numeroPerfiles;
    }

    public void setNumeroPerfiles(int numeroPerfiles) {
        this.numeroPerfiles = numeroPerfiles;
    }

    public Character getEstado() {
        return this.estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public int getCodigoProceso() {
        return this.codigoProceso;
    }

    public void setCodigoProceso(int codigoProceso) {
        this.codigoProceso = codigoProceso;
    }

    public int hashCode() {
        int hash = 0;
        return hash += this.id != null ? this.id.hashCode() : 0;
    }

    public boolean equals(Object object) {
        if (!(object instanceof ExcluidosMigracion)) {
            return false;
        }
        ExcluidosMigracion other = (ExcluidosMigracion)object;
        if (this.id == null && other.id != null || this.id != null && !this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "com.bod.model.ExcluidosMigracion[ id=" + this.id + " ]";
    }
}
