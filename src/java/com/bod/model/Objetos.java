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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "OBJETOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Objetos.findAll", query = "SELECT o FROM Objetos o"),
    @NamedQuery(name = "Objetos.findById", query = "SELECT o FROM Objetos o WHERE o.id = :id"),
    @NamedQuery(name = "Objetos.findByCodigo", query = "SELECT o FROM Objetos o WHERE o.codigo = :codigo")})
public class Objetos implements Serializable {
    @Lob
    @Column(name = "DATA_OBJETO")
    private byte[] dataObjeto;
    @JoinColumn(name = "CATEGORIA_OBJETO_ID", referencedColumnName = "ID")
    @ManyToOne
    private CategoriaObjeto categoriaObjetoId;
    @Size(max = 512)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(mappedBy = "objetoId")
    private Collection<Idiomas> idiomasCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "CODIGO")
    private String codigo;
    @OneToMany(mappedBy = "objetoId")
    private Collection<Menu> menuCollection;
    @JoinColumn(name = "TIPO_OBJETO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoObjeto tipoObjetoId;
    @JoinColumn(name = "IDIOMAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Idiomas idiomasId;
    @JoinColumn(name = "AMBIENTES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ambientes ambientesId;

    public Objetos() {
    }

    public Objetos(Long id) {
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

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    public TipoObjeto getTipoObjetoId() {
        return tipoObjetoId;
    }

    public void setTipoObjetoId(TipoObjeto tipoObjetoId) {
        this.tipoObjetoId = tipoObjetoId;
    }

    public Idiomas getIdiomasId() {
        return idiomasId;
    }

    public void setIdiomasId(Idiomas idiomasId) {
        this.idiomasId = idiomasId;
    }

    public Ambientes getAmbientesId() {
        return ambientesId;
    }

    public void setAmbientesId(Ambientes ambientesId) {
        this.ambientesId = ambientesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Objetos)) {
            return false;
        }
        Objetos other = (Objetos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Objetos[ id=" + id + " ]";
    }

    public byte[] getDataObjeto() {
        return dataObjeto;
    }

    public void setDataObjeto(byte[] dataObjeto) {
        this.dataObjeto = dataObjeto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Idiomas> getIdiomasCollection() {
        return idiomasCollection;
    }

    public void setIdiomasCollection(Collection<Idiomas> idiomasCollection) {
        this.idiomasCollection = idiomasCollection;
    }


    public CategoriaObjeto getCategoriaObjetoId() {
        return categoriaObjetoId;
    }

    public void setCategoriaObjetoId(CategoriaObjeto categoriaObjetoId) {
        this.categoriaObjetoId = categoriaObjetoId;
    }
    
    
}
