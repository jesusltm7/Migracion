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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findById", query = "SELECT m FROM Menu m WHERE m.id = :id"),
    @NamedQuery(name = "Menu.findByOrden", query = "SELECT m FROM Menu m WHERE m.orden = :orden"),
    @NamedQuery(name = "Menu.findByEtiqueta", query = "SELECT m FROM Menu m WHERE m.etiqueta = :etiqueta"),
    @NamedQuery(name = "Menu.findByDescripcion", query = "SELECT m FROM Menu m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "Menu.findByCodigo", query = "SELECT m FROM Menu m WHERE m.codigo = :codigo")})
public class Menu implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDEN")
    private long orden;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "ETIQUETA")
    private String etiqueta;
    @Size(max = 2000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "CODIGO")
    private String codigo;
    @JoinColumn(name = "OPERACIONES_NBL_ID", referencedColumnName = "ID")
    @ManyToOne
    private OperacionesNbl operacionesNblId;
    @JoinColumn(name = "OBJETO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Objetos objetoId;
    @JoinColumn(name = "OBJETO2_ID", referencedColumnName = "ID")
    @ManyToOne
    private Objetos objetoId2;
    @OneToMany(mappedBy = "menuPadreId")
    @OrderBy("orden ASC")
    private Collection<Menu> menuCollection;
    @JoinColumn(name = "MENU_PADRE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Menu menuPadreId;
    @JoinColumn(name = "IDIOMAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Idiomas idiomasId;
    @JoinColumn(name = "AMBIENTES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ambientes ambientesId;

    public Menu() {
    }

    public Menu(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public OperacionesNbl getOperacionesNblId() {
        return operacionesNblId;
    }

    public void setOperacionesNblId(OperacionesNbl operacionesNblId) {
        this.operacionesNblId = operacionesNblId;
    }

    public Objetos getObjetoId() {
        return objetoId;
    }

    public void setObjetoId(Objetos objetoId) {
        this.objetoId = objetoId;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    public Menu getMenuPadreId() {
        return menuPadreId;
    }

    public void setMenuPadreId(Menu menuPadreId) {
        this.menuPadreId = menuPadreId;
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Menu[ id=" + id + " ]";
    }

    public long getOrden() {
        return orden;
    }

    public void setOrden(long orden) {
        this.orden = orden;
    }

    public Objetos getObjetoId2() {
        return objetoId2;
    }

    public void setObjetoId2(Objetos objetoId2) {
        this.objetoId2 = objetoId2;
    }
    
}
