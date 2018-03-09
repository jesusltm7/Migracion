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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "AMBIENTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ambientes.findAll", query = "SELECT a FROM Ambientes a"),
    @NamedQuery(name = "Ambientes.findById", query = "SELECT a FROM Ambientes a WHERE a.id = :id"),
    @NamedQuery(name = "Ambientes.findByCodigo", query = "SELECT a FROM Ambientes a WHERE a.codigo = :codigo"),
    @NamedQuery(name = "Ambientes.findByNombre", query = "SELECT a FROM Ambientes a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Ambientes.findByDescripcion", query = "SELECT a FROM Ambientes a WHERE a.descripcion = :descripcion")})
public class Ambientes implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS")
    private Character estatus;
    //@JoinColumn(name = "IDIOMAS_ID", referencedColumnName = "ID")
    //@ManyToOne
//    @Transient
//    private Idiomas idiomasId;
    //@JoinColumn(name = "CANAL_ID", referencedColumnName = "ID")
    //@ManyToOne
//    @Transient
//    private Canales canalId;
    private static final long serialVersionUID = 1L;

    @Size(max = 3)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 2000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @JoinTable(name = "AMBIENTES_HAS_CANALES", joinColumns = {
        @JoinColumn(name = "AMBIENTES_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "CANALES_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Canales> canalesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ambientesId")
    private Collection<BitacoraNbl> bitacoraNblCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ambientes")
    private Collection<AmbientesHasParametros> ambientesHasParametrosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ambientesId")
    private Collection<Textos> textosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ambientesId")
    private Collection<Menu> menuCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ambientesId")
    private Collection<PerfilesNbl> perfilesNblCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ambientesId")
    private Collection<Objetos> objetosCollection;
    @NotNull
    @Column(name = "CODTIPOBASE", nullable = false)
    private Integer codtipobase;
    @Transient
    public static final String CODIGO_JURIDICO = "jrd";
    @Transient
    public static final String CODIGO_NATURAL = "nat";
    @Transient
    public static final String CODIGO_TODOS = "tod";

    public Ambientes() {
    }

    public Ambientes(Long id) {
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
    public Collection<Canales> getCanalesCollection() {
        return canalesCollection;
    }

    public void setCanalesCollection(Collection<Canales> canalesCollection) {
        this.canalesCollection = canalesCollection;
    }

    @XmlTransient
    public Collection<BitacoraNbl> getBitacoraNblCollection() {
        return bitacoraNblCollection;
    }

    public void setBitacoraNblCollection(Collection<BitacoraNbl> bitacoraNblCollection) {
        this.bitacoraNblCollection = bitacoraNblCollection;
    }

    @XmlTransient
    public Collection<AmbientesHasParametros> getAmbientesHasParametrosCollection() {
        return ambientesHasParametrosCollection;
    }

    public void setAmbientesHasParametrosCollection(Collection<AmbientesHasParametros> ambientesHasParametrosCollection) {
        this.ambientesHasParametrosCollection = ambientesHasParametrosCollection;
    }

    @XmlTransient
    public Collection<Textos> getTextosCollection() {
        return textosCollection;
    }

    public void setTextosCollection(Collection<Textos> textosCollection) {
        this.textosCollection = textosCollection;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    @XmlTransient
    public Collection<PerfilesNbl> getPerfilesNblCollection() {
        return perfilesNblCollection;
    }

    public void setPerfilesNblCollection(Collection<PerfilesNbl> perfilesNblCollection) {
        this.perfilesNblCollection = perfilesNblCollection;
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
        if (!(object instanceof Ambientes)) {
            return false;
        }
        Ambientes other = (Ambientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Ambientes[ id=" + id + " ]";
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

//    @Transient
//    public Idiomas getIdiomasId() {
//        return idiomasId;
//    }
//
//    @Transient
//    public void setIdiomasId(Idiomas idiomasId) {
//        this.idiomasId = idiomasId;
//    }
//
//    @Transient
//    public Canales getCanalId() {
//        return canalId;
//    }
//
//    @Transient
//    public void setCanalId(Canales canalId) {
//        this.canalId = canalId;
//    }
    public Integer getCodtipobase() {
        return codtipobase;
    }

    public void setCodtipobase(Integer codtipobase) {
        this.codtipobase = codtipobase;
    }

}
