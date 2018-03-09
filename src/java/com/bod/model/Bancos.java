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
import javax.persistence.Lob;
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
@Table(name = "BANCOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bancos.findAll", query = "SELECT b FROM Bancos b order by b.nombre"),
    @NamedQuery(name = "Bancos.findById", query = "SELECT b FROM Bancos b WHERE b.id = :id  order by b.nombre"),
    @NamedQuery(name = "Bancos.findByNombre", query = "SELECT b FROM Bancos b WHERE b.nombre = :nombre  order by b.nombre"),
    @NamedQuery(name = "Bancos.findByCodigo", query = "SELECT b FROM Bancos b WHERE b.codigo = :codigo  order by b.nombre"),
    @NamedQuery(name = "Bancos.findByBin", query = "SELECT b FROM Bancos b WHERE b.bin = :bin  order by b.nombre")})
public class Bancos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "BIN")
    private String bin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "CODIGO")
    private String codigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bancosId")
    private Collection<Productos> productosCollection;
    @Lob
    @Column(name = "ICONO")
    private byte[] icono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdbanco")
    private Collection<Mstbines> mstbinesCollection; 

    public Bancos() {
    }

    public Bancos(Long id) {
        this.id = id;
    }

    public Bancos(Long id, String nombre, String bin) {
        this.id = id;
        this.nombre = nombre;
        this.bin = bin;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public byte[] getIcono() {
        return icono;
    }

    public void setIcono(byte[] icono) {
        this.icono = icono;
    }

    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }

    @XmlTransient
    public Collection<Mstbines> getMstbinesCollection() {
        return mstbinesCollection;
    }

    public void setMstbinesCollection(Collection<Mstbines> mstbinesCollection) {
        this.mstbinesCollection = mstbinesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Bancos)) {
            return false;
        }
        Bancos other = (Bancos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }



}
