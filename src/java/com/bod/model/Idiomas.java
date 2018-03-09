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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@Table(name = "IDIOMAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Idiomas.findAll", query = "SELECT i FROM Idiomas i"),
    @NamedQuery(name = "Idiomas.findById", query = "SELECT i FROM Idiomas i WHERE i.id = :id"),
    @NamedQuery(name = "Idiomas.findByCodigoIso", query = "SELECT i FROM Idiomas i WHERE i.codigoIso = :codigoIso"),
    @NamedQuery(name = "Idiomas.findByNombre", query = "SELECT i FROM Idiomas i WHERE i.nombre = :nombre")})
public class Idiomas implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS")
    private Character estatus;
    //@OneToMany(mappedBy = "idiomasId")
    @Transient
    private Collection<Ambientes> ambientesCollection;
    @JoinColumn(name = "OBJETO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Objetos objetoId;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "CODIGO_ISO")
    private String codigoIso;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Size(max = 140)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    
    @Column(name = "ORDEN")
    private Short orden;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiomasId")
    private Collection<Textos> textosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiomasId")
    private Collection<Ayudas> ayudasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiomasId")
    private Collection<Menu> menuCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiomasId")
    private Collection<Objetos> objetosCollection;
    
    @Transient
    public static final String CODIGO_ESPANOL = "es";

    public Idiomas() {
    }

    public Idiomas(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoIso() {
        return codigoIso;
    }

    public void setCodigoIso(String codigoIso) {
        this.codigoIso = codigoIso;
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

    @XmlTransient
    public Collection<Ayudas> getAyudasCollection() {
        return ayudasCollection;
    }

    public void setAyudasCollection(Collection<Ayudas> ayudasCollection) {
        this.ayudasCollection = ayudasCollection;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
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
    
     public Short getOrden() {
        return orden;
    }

    public void setOrden(Short orden) {
        this.orden = orden;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Idiomas)) {
            return false;
        }
        Idiomas other = (Idiomas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Idiomas[ id=" + id + " ]";
    }

    public Objetos getObjetoId() {
        return objetoId;
    }

    public void setObjetoId(Objetos objetoId) {
        this.objetoId = objetoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    @XmlTransient
    public Collection<Ambientes> getAmbientesCollection() {
        return ambientesCollection;
    }

    public void setAmbientesCollection(Collection<Ambientes> ambientesCollection) {
        this.ambientesCollection = ambientesCollection;
    }
           
}
