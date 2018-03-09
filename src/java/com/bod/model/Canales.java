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
import javax.persistence.ManyToMany;
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
@Table(name = "CANALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canales.findAll", query = "SELECT c FROM Canales c"),
    @NamedQuery(name = "Canales.findAllOrderByNombre", query = "SELECT c FROM Canales c ORDER BY c.nombre ASC"),
    @NamedQuery(name = "Canales.findAllByNombreActive", query = "SELECT c FROM Canales c WHERE c.estatus = 'A' ORDER BY c.nombre ASC"),
    @NamedQuery(name = "Canales.findById", query = "SELECT c FROM Canales c WHERE c.id = :id"),
    @NamedQuery(name = "Canales.findByCodigo", query = "SELECT c FROM Canales c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Canales.findByNombre", query = "SELECT c FROM Canales c WHERE c.nombre = :nombre")})
public class Canales implements Serializable {
    @Size(max = 145)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "ETIQUETA")
    private String etiqueta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS")
    private Character estatus;

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
    //@ManyToMany(mappedBy = "canalesCollection")
    @Transient
    private Collection<Ambientes> ambientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "canalesId")
    private Collection<BitacoraNbl> bitacoraNblCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "canales")
    private Collection<CanalesHasParametros> canalesHasParametrosCollection;

    public Canales() {
    }

    public Canales(Long id) {
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
    public Collection<Ambientes> getAmbientesCollection() {
        return ambientesCollection;
    }

    public void setAmbientesCollection(Collection<Ambientes> ambientesCollection) {
        this.ambientesCollection = ambientesCollection;
    }

    @XmlTransient
    public Collection<BitacoraNbl> getBitacoraNblCollection() {
        return bitacoraNblCollection;
    }

    public void setBitacoraNblCollection(Collection<BitacoraNbl> bitacoraNblCollection) {
        this.bitacoraNblCollection = bitacoraNblCollection;
    }

    @XmlTransient
    public Collection<CanalesHasParametros> getCanalesHasParametrosCollection() {
        return canalesHasParametrosCollection;
    }

    public void setCanalesHasParametrosCollection(Collection<CanalesHasParametros> canalesHasParametrosCollection) {
        this.canalesHasParametrosCollection = canalesHasParametrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Canales)) {
            return false;
        }
        Canales other = (Canales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Canales[ id=" + id + " ]";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

}
