/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import com.bod.beans.BeanServicios;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yeniree Sanchez
 */
@Entity
@Table(name = "PRODUCTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findById", query = "SELECT p FROM Productos p WHERE p.id = :id"),
    @NamedQuery(name = "Productos.findByNumero", query = "SELECT p FROM Productos p WHERE p.numero = :numero"),
    @NamedQuery(name = "Productos.findByEstatus", query = "SELECT p FROM Productos p WHERE p.estatus = :estatus"),
    @NamedQuery(name = "Productos.findByDirectorioGlobalId", query = "SELECT p FROM Productos p WHERE p.directorioGlobalId = :directorioGlobalId"),
    @NamedQuery(name = "Productos.findByCategoriaProductosId", query = "SELECT p FROM Productos p WHERE p.categoriaProductosId = :categoriaProductosId"),
    @NamedQuery(name = "Productos.findByBancosId", query = "SELECT p FROM Productos p WHERE p.bancosId = :bancosId"),
    @NamedQuery(name = "Productos.findByAlias", query = "SELECT p FROM Productos p WHERE p.alias = :alias"),
    @NamedQuery(name = "Productos.findByServicioId", query = "SELECT p FROM Productos p WHERE p.servicio = :servicio"),
    @NamedQuery(name = "Productos.findByCodadicional", query = "SELECT p FROM Productos p WHERE p.numeroAdicional = :numeroAdicional"),
    @NamedQuery(name = "Productos.findByNumeroDirGlobal", query = "SELECT p FROM Productos p WHERE p.numero = :numero and p.directorioGlobalId.id = :idDirGlobal")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "PRODUCTOS_SEQ", sequenceName = "PRODUCTOS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTOS_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "ESTATUS")
    private Character estatus;
    @JoinColumn(name = "DIRECTORIO_GLOBAL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private DirectorioGlobal directorioGlobalId;
    @JoinColumn(name = "CATEGORIA_PRODUCTOS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CategoriaProductos categoriaProductosId;
    @JoinColumn(name = "BANCOS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Bancos bancosId;
    @Size(max = 32)
    @Column(name = "ALIAS")
    private String alias;
    @Column(name = "SERVICIO_ID")
    private BeanServicios servicio;
    @Size(max = 45)
    @Column(name = "CODADICIONAL")
    private String numeroAdicional;
    @JoinColumn(name = "FK_IDMONEDA", referencedColumnName = "ID")
    @ManyToOne
    private Monedas codigoMoneda;

    @Transient
    public static final String CODIGO_PRODUCTO_CUENTA = "cta";
    @Transient
    public static final String CODIGO_PRODUCTO_TDC = "tdc";
    @Transient
    public static final String CODIGO_PRODUCTO_FIDEICOMISO = "fid";
    @Transient
    public static final String CODIGO_PRODUCTO_CREDITO = "cdt";
    @Transient
    public static final String CODIGO_PRODUCTO_SERVICIO = "srv";
    @Transient
    public static final String CODIGO_PRODUCTO_TRIBUTO = "trb";
    @Transient
    public static final String CODIGO_PRODUCTO_ID_BANCO = "0116";
    @Transient
    public static final String CODIGO_PRODUCTO_ESTATUS_ACTIVO = "A";

    public Productos() {
    }

    public Productos(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    public DirectorioGlobal getDirectorioGlobalId() {
        return directorioGlobalId;
    }

    public void setDirectorioGlobalId(DirectorioGlobal directorioGlobalId) {
        this.directorioGlobalId = directorioGlobalId;
    }

    public CategoriaProductos getCategoriaProductosId() {
        return categoriaProductosId;
    }

    public void setCategoriaProductosId(CategoriaProductos categoriaProductosId) {
        this.categoriaProductosId = categoriaProductosId;
    }

    public Bancos getBancosId() {
        return bancosId;
    }

    public void setBancosId(Bancos bancosId) {
        this.bancosId = bancosId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public boolean getTipoServicio() {
        return BeanServicios.SERVICIOS.equals(this.getCategoriaProductosId().getNombre());
    }

    public BeanServicios getServicio() {
        return servicio;
    }

    public void setServicio(BeanServicios servicio) {
        this.servicio = servicio;
    }

    public String getNumeroAdicional() {
        return numeroAdicional;
    }

    public void setNumeroAdicional(String numeroAdicional) {
        this.numeroAdicional = numeroAdicional;
    }

    public Monedas getCodigoMoneda() {
        return codigoMoneda;
    }

    public void setCodigoMoneda(Monedas codigoMoneda) {
        this.codigoMoneda = codigoMoneda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Productos[ id=" + id + " ]";
    }

    public String printInfo() {
        return "Productos{"
                + "id=" + id
                + ", numero=" + numero
                + ", estatus=" + estatus
                + ", directorioGlobalId=" + directorioGlobalId
                + ", categoriaProductosId=" + categoriaProductosId
                + ", bancosId=" + bancosId
                + ", alias=" + alias
                + ", servicio=" + servicio
                + ", codigoMoneda=" + codigoMoneda
                + '}';
    }
}
