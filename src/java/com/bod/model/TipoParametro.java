/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Cacheable(true)
@Table(name = "TIPO_PARAMETRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoParametro.findAll", query = "SELECT t FROM TipoParametro t"),
    @NamedQuery(name = "TipoParametro.findById", query = "SELECT t FROM TipoParametro t WHERE t.id = :id"),
    @NamedQuery(name = "TipoParametro.findByCodigo", query = "SELECT t FROM TipoParametro t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoParametro.findByNombre", query = "SELECT t FROM TipoParametro t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoParametro.findByDescripcion", query = "SELECT t FROM TipoParametro t WHERE t.descripcion = :descripcion")})
public class TipoParametro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 45)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoParametroId")
    private Collection<Parametros> parametrosCollection;

    public TipoParametro() {
    }

    public TipoParametro(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Parametros> getParametrosCollection() {
        return parametrosCollection;
    }

    public void setParametrosCollection(Collection<Parametros> parametrosCollection) {
        this.parametrosCollection = parametrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoParametro)) {
            return false;
        }
        TipoParametro other = (TipoParametro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.TipoParametro[ id=" + id + " ]";
    }
    
}
