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
@Table(name = "PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p"),
    @NamedQuery(name = "Parametros.findById", query = "SELECT p FROM Parametros p WHERE p.id = :id"),
    @NamedQuery(name = "Parametros.findByCodigo", query = "SELECT p FROM Parametros p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Parametros.findByNombre", query = "SELECT p FROM Parametros p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Parametros.findByValorPorDefecto", query = "SELECT p FROM Parametros p WHERE p.valorPorDefecto = :valorPorDefecto"),
    @NamedQuery(name = "Parametros.findByDescripcion", query = "SELECT p FROM Parametros p WHERE p.descripcion = :descripcion")})
public class Parametros implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 256)
    @Column(name = "VALOR_POR_DEFECTO")
    private String valorPorDefecto;
    @Size(max = 2000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametros")
    private Collection<AmbientesHasParametros> ambientesHasParametrosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametros")
    private Collection<PerfilesNblHasParametros> perfilesNblHasParametrosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parametros")
    private Collection<CanalesHasParametros> canalesHasParametrosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdparametro")
    private Collection<Detreglaparametros> detreglaparametrosCollection;
    @JoinColumn(name = "TIPO_PARAMETRO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoParametro tipoParametroId;
    @Transient
    public static final String PARAMETRO_TIPO_PERSONA = "wsparty.asigna.perfil.issuedIdentType";
    @Transient
    public static final String PARAMETRO_CODIGO_BANCA = "wsparty.asigna.perfil.bankID";

    public Parametros() {
    }

    public Parametros(Long id) {
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

    public String getValorPorDefecto() {
        return valorPorDefecto;
    }

    public void setValorPorDefecto(String valorPorDefecto) {
        this.valorPorDefecto = valorPorDefecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<AmbientesHasParametros> getAmbientesHasParametrosCollection() {
        return ambientesHasParametrosCollection;
    }

    public void setAmbientesHasParametrosCollection(Collection<AmbientesHasParametros> ambientesHasParametrosCollection) {
        this.ambientesHasParametrosCollection = ambientesHasParametrosCollection;
    }

    @XmlTransient
    public Collection<Detreglaparametros> getDetreglaparametrosCollection() {
        return detreglaparametrosCollection;
    }

    public void setDetreglaparametrosCollection(Collection<Detreglaparametros> detreglaparametrosCollection) {
        this.detreglaparametrosCollection = detreglaparametrosCollection;
    }

    @XmlTransient
    public Collection<PerfilesNblHasParametros> getPerfilesNblHasParametrosCollection() {
        return perfilesNblHasParametrosCollection;
    }

    public void setPerfilesNblHasParametrosCollection(Collection<PerfilesNblHasParametros> perfilesNblHasParametrosCollection) {
        this.perfilesNblHasParametrosCollection = perfilesNblHasParametrosCollection;
    }

    @XmlTransient
    public Collection<CanalesHasParametros> getCanalesHasParametrosCollection() {
        return canalesHasParametrosCollection;
    }

    public void setCanalesHasParametrosCollection(Collection<CanalesHasParametros> canalesHasParametrosCollection) {
        this.canalesHasParametrosCollection = canalesHasParametrosCollection;
    }

    public TipoParametro getTipoParametroId() {
        return tipoParametroId;
    }

    public void setTipoParametroId(TipoParametro tipoParametroId) {
        this.tipoParametroId = tipoParametroId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Parametros[ id=" + id + " ]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
