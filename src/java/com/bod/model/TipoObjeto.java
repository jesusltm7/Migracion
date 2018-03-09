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
@Table(name = "TIPO_OBJETO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoObjeto.findAll", query = "SELECT t FROM TipoObjeto t"),
    @NamedQuery(name = "TipoObjeto.findById", query = "SELECT t FROM TipoObjeto t WHERE t.id = :id"),
    @NamedQuery(name = "TipoObjeto.findByCodigo", query = "SELECT t FROM TipoObjeto t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoObjeto.findByNombre", query = "SELECT t FROM TipoObjeto t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoObjeto.findByMime", query = "SELECT t FROM TipoObjeto t WHERE t.mime = :mime")})
public class TipoObjeto implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "EXTENSIONES")
    private String extensiones;
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
    @Size(max = 45)
    @Column(name = "MIME")
    private String mime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoObjetoId")
    private Collection<Objetos> objetosCollection;

    public TipoObjeto() {
    }

    public TipoObjeto(Long id) {
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

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    @XmlTransient
    public Collection<Objetos> getObjetosCollection() {
        return objetosCollection;
    }

    public void setObjetosCollection(Collection<Objetos> objetosCollection) {
        this.objetosCollection = objetosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoObjeto)) {
            return false;
        }
        TipoObjeto other = (TipoObjeto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.TipoObjeto[ id=" + id + " ]";
    }

    public String getExtensiones() {
        return extensiones;
    }

    public void setExtensiones(String extensiones) {
        this.extensiones = extensiones;
    }
    
}
