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
import javax.persistence.OneToOne;
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
@Table(name = "CATEGORIA_PRODUCTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaProductos.findAll", query = "SELECT c FROM CategoriaProductos c"),
    @NamedQuery(name = "CategoriaProductos.findById", query = "SELECT c FROM CategoriaProductos c WHERE c.id = :id"),
    @NamedQuery(name = "CategoriaProductos.findByCodigo", query = "SELECT c FROM CategoriaProductos c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CategoriaProductos.findByNombre", query = "SELECT c FROM CategoriaProductos c WHERE c.nombre = :nombre")})
public class CategoriaProductos implements Serializable, Comparable<CategoriaProductos> {
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
    @Column(name = "ESTCATEGORIA")
    private String estadoCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriaProductosId")
    private Collection<Productos> productosCollection;
    
    @Transient
    public static final String NOMBRE_CATEGORIA_PRODUCTO_CUENTA = "Cuentas";
    @Transient
    public static final String NOMBRE_CATEGORIA_PRODUCTO_TARJETA = "Tarjetas de crédito";
  

    public CategoriaProductos() {
    }

    public CategoriaProductos(Long id) {
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

    public String getEstadoCategoria() {
        return estadoCategoria;
    }

    public void setEstadoCategoria(String estadoCategoria) {
        this.estadoCategoria = estadoCategoria;
    }

    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        return productosCollection;
    }

    public void setProductosCollection(Collection<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CategoriaProductos)) {
            return false;
        }
        CategoriaProductos other = (CategoriaProductos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public int compareTo(CategoriaProductos o) {
        return this.getNombre().compareToIgnoreCase(o.getNombre());
    }
    
}
