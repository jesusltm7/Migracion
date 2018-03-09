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
@Table(name = "TIPO_TEXTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTexto.findAll", query = "SELECT t FROM TipoTexto t"),
    @NamedQuery(name = "TipoTexto.findById", query = "SELECT t FROM TipoTexto t WHERE t.id = :id"),
    @NamedQuery(name = "TipoTexto.findByCodigo", query = "SELECT t FROM TipoTexto t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoTexto.findByNombre", query = "SELECT t FROM TipoTexto t WHERE t.nombre = :nombre")})
public class TipoTexto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 3)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoTextoId")
    private Collection<Textos> textosCollection;

    public TipoTexto() {
    }

    public TipoTexto(Long id) {
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

    @XmlTransient
    public Collection<Textos> getTextosCollection() {
        return textosCollection;
    }

    public void setTextosCollection(Collection<Textos> textosCollection) {
        this.textosCollection = textosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoTexto)) {
            return false;
        }
        TipoTexto other = (TipoTexto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.TipoTexto[ id=" + id + " ]";
    }
    
}
