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
@Table(name = "ESTILOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estilos.findAll", query = "SELECT e FROM Estilos e"),
    @NamedQuery(name = "Estilos.findById", query = "SELECT e FROM Estilos e WHERE e.id = :id"),
    @NamedQuery(name = "Estilos.findByNombre", query = "SELECT e FROM Estilos e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estilos.findByRuta", query = "SELECT e FROM Estilos e WHERE e.ruta = :ruta")})
public class Estilos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 45)
    @Column(name = "RUTA")
    private String ruta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estilosId")
    private Collection<PerfilesNbl> perfilesNblCollection;

    public Estilos() {
    }

    public Estilos(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @XmlTransient
    public Collection<PerfilesNbl> getPerfilesNblCollection() {
        return perfilesNblCollection;
    }

    public void setPerfilesNblCollection(Collection<PerfilesNbl> perfilesNblCollection) {
        this.perfilesNblCollection = perfilesNblCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Estilos)) {
            return false;
        }
        Estilos other = (Estilos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Estilos[ id=" + id + " ]";
    }
    
}
