/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Cacheable(true)
@Table(name = "MONEDAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Monedas.findAll", query = "SELECT m FROM Monedas m"),
    @NamedQuery(name = "Monedas.findById", query = "SELECT m FROM Monedas m WHERE m.id = :id"),
    @NamedQuery(name = "Monedas.findByCodigo", query = "SELECT m FROM Monedas m WHERE m.codigo = :codigo"),
    @NamedQuery(name = "Monedas.findByNombre", query = "SELECT m FROM Monedas m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Monedas.findByDescripcion", query = "SELECT m FROM Monedas m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Monedas.findByEstado", query = "SELECT m FROM Monedas m WHERE m.estado = :estado")})

public class Monedas implements Serializable {
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
    @Size(max = 2000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ESTMONEDA")
    private String estado;

    public Monedas() {
    }

    public Monedas(Long id) {
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Monedas)) {
            return false;
        }
        Monedas other = (Monedas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Monedas[ id=" + id + " ]";
    }
    
}
