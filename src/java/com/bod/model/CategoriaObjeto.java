/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
 * @author Proveedor intec
 */
@Entity
@Cacheable(true)
@Table(name = "CATEGORIA_OBJETO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaObjeto.findAll", query = "SELECT c FROM CategoriaObjeto c"),
    @NamedQuery(name = "CategoriaObjeto.findById", query = "SELECT c FROM CategoriaObjeto c WHERE c.id = :id"),
    @NamedQuery(name = "CategoriaObjeto.findByCodigo", query = "SELECT c FROM CategoriaObjeto c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CategoriaObjeto.findByNombre", query = "SELECT c FROM CategoriaObjeto c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CategoriaObjeto.findByDescripcion", query = "SELECT c FROM CategoriaObjeto c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CategoriaObjeto.findByAnchoImagen", query = "SELECT c FROM CategoriaObjeto c WHERE c.anchoImagen = :anchoImagen"),
    @NamedQuery(name = "CategoriaObjeto.findByAltoImagen", query = "SELECT c FROM CategoriaObjeto c WHERE c.altoImagen = :altoImagen")})
public class CategoriaObjeto implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 141)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANCHO_IMAGEN")
    private BigInteger anchoImagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALTO_IMAGEN")
    private BigInteger altoImagen;
    @OneToMany(mappedBy = "categoriaObjetoId")
    private Collection<Objetos> objetosCollection;

    public CategoriaObjeto() {
    }

    public CategoriaObjeto(BigDecimal id) {
        this.id = id;
    }

    public CategoriaObjeto(BigDecimal id, String codigo, String nombre, String descripcion, BigInteger anchoImagen, BigInteger altoImagen) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.anchoImagen = anchoImagen;
        this.altoImagen = altoImagen;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
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

    public BigInteger getAnchoImagen() {
        return anchoImagen;
    }

    public void setAnchoImagen(BigInteger anchoImagen) {
        this.anchoImagen = anchoImagen;
    }

    public BigInteger getAltoImagen() {
        return altoImagen;
    }

    public void setAltoImagen(BigInteger altoImagen) {
        this.altoImagen = altoImagen;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaObjeto)) {
            return false;
        }
        CategoriaObjeto other = (CategoriaObjeto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.CategoriaObjeto[ id=" + id + " ]";
    }
    
}
