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
@Table(name = "PAISES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paises.findAll", query = "SELECT p FROM Paises p"),
    @NamedQuery(name = "Paises.findById", query = "SELECT p FROM Paises p WHERE p.id = :id"),
    @NamedQuery(name = "Paises.findByNombre", query = "SELECT p FROM Paises p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Paises.findByNacionalidadMasculino", query = "SELECT p FROM Paises p WHERE p.nacionalidadMasculino = :nacionalidadMasculino"),
    @NamedQuery(name = "Paises.findByNacionalidadFemenino", query = "SELECT p FROM Paises p WHERE p.nacionalidadFemenino = :nacionalidadFemenino"),
    @NamedQuery(name = "Paises.findByIdioma", query = "SELECT p FROM Paises p WHERE p.idioma = :idioma"),
    @NamedQuery(name = "Paises.findByCodigoCore", query = "SELECT p FROM Paises p WHERE p.codigoCore = :codigoCore")})
public class Paises implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 140)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 140)
    @Column(name = "NACIONALIDAD_MASCULINO")
    private String nacionalidadMasculino;
    @Size(max = 140)
    @Column(name = "NACIONALIDAD_FEMENINO")
    private String nacionalidadFemenino;
    @Size(max = 140)
    @Column(name = "IDIOMA")
    private String idioma;
    @Size(max = 4)
    @Column(name = "CODIGO_CORE")
    private String codigoCore;
    @OneToMany(mappedBy = "paisId")
    private Collection<UsuariosNbl> usuariosNblCollection;

    public Paises() {
    }

    public Paises(Long id) {
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

    public String getNacionalidadMasculino() {
        return nacionalidadMasculino;
    }

    public void setNacionalidadMasculino(String nacionalidadMasculino) {
        this.nacionalidadMasculino = nacionalidadMasculino;
    }

    public String getNacionalidadFemenino() {
        return nacionalidadFemenino;
    }

    public void setNacionalidadFemenino(String nacionalidadFemenino) {
        this.nacionalidadFemenino = nacionalidadFemenino;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getCodigoCore() {
        return codigoCore;
    }

    public void setCodigoCore(String codigoCore) {
        this.codigoCore = codigoCore;
    }

    @XmlTransient
    public Collection<UsuariosNbl> getUsuariosNblCollection() {
        return usuariosNblCollection;
    }

    public void setUsuariosNblCollection(Collection<UsuariosNbl> usuariosNblCollection) {
        this.usuariosNblCollection = usuariosNblCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Paises)) {
            return false;
        }
        Paises other = (Paises) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Paises[ id=" + id + " ]";
    }

}
