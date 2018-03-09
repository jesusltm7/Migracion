/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "MODULOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulos.findAll", query = "SELECT m FROM Modulos m"),
    @NamedQuery(name = "Modulos.findById", query = "SELECT m FROM Modulos m WHERE m.id = :id"),
    @NamedQuery(name = "Modulos.findByNombre", query = "SELECT m FROM Modulos m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Modulos.findByDescripcion", query = "SELECT m FROM Modulos m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Modulos.findByFragmento", query = "SELECT m FROM Modulos m WHERE m.fragmento = :fragmento")})
public class Modulos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 512)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 140)
    @Column(name = "FRAGMENTO")
    private String fragmento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modulos")
    private Collection<ModulosUsuariosNbl> modulosUsuariosNblCollection;
    @JoinColumn(name = "IDIOMA_ID", referencedColumnName = "ID")
    @ManyToOne
    private Idiomas idiomaId;
    
    /*@Transient
    @Column(name = "CANTIDAD_SECCIONES")
    private Integer cantidadSecciones;*/

    public Modulos() {
    }

    public Modulos(Long id) {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFragmento() {
        return fragmento;
    }

    public void setFragmento(String fragmento) {
        this.fragmento = fragmento;
    }

   /* public Integer getCantidadSecciones() {
        return cantidadSecciones;
    }

    public void setCantidadSecciones(Integer cantidadSecciones) {
        this.cantidadSecciones = cantidadSecciones;
    }*/

    @XmlTransient
    public Collection<ModulosUsuariosNbl> getModulosUsuariosNblCollection() {
        return modulosUsuariosNblCollection;
    }

    public void setModulosUsuariosNblCollection(Collection<ModulosUsuariosNbl> modulosUsuariosNblCollection) {
        this.modulosUsuariosNblCollection = modulosUsuariosNblCollection;
    }

    public Idiomas getIdiomaId() {
        return idiomaId;
    }

    public void setIdiomaId(Idiomas idiomaId) {
        this.idiomaId = idiomaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Modulos)) {
            return false;
        }
        Modulos other = (Modulos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Modulos[ id=" + id + " ]";
    }
    
}
