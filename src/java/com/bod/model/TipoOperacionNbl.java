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
@Table(name = "TIPO_OPERACION_NBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoOperacionNbl.findAll", query = "SELECT t FROM TipoOperacionNbl t"),
    @NamedQuery(name = "TipoOperacionNbl.findById", query = "SELECT t FROM TipoOperacionNbl t WHERE t.id = :id"),
    @NamedQuery(name = "TipoOperacionNbl.findByCodigo", query = "SELECT t FROM TipoOperacionNbl t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoOperacionNbl.findByNombre", query = "SELECT t FROM TipoOperacionNbl t WHERE t.nombre = :nombre")})
public class TipoOperacionNbl implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoOperacionNblId")
    private Collection<OperacionesNbl> operacionesNblCollection;
    @Transient
    public static final String TIPO_OPERACION_FINANCIERA_CODIGO = "fin";
    
    
    
    public TipoOperacionNbl() {
    }

    public TipoOperacionNbl(Long id) {
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
    public Collection<OperacionesNbl> getOperacionesNblCollection() {
        return operacionesNblCollection;
    }

    public void setOperacionesNblCollection(Collection<OperacionesNbl> operacionesNblCollection) {
        this.operacionesNblCollection = operacionesNblCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoOperacionNbl)) {
            return false;
        }
        TipoOperacionNbl other = (TipoOperacionNbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.TipoOperacionNbl[ id=" + id + " ]";
    }

    
}
