package com.bod.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
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
 * @author KALTEC3
 */
@Entity
@Cacheable(true)
@Table(name = "TIPO_PERFILES_NBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPerfilesNbl.findAll", query = "SELECT t FROM TipoPerfilesNbl t"),
    @NamedQuery(name = "TipoPerfilesNbl.findById", query = "SELECT t FROM TipoPerfilesNbl t WHERE t.id = :id"),
    @NamedQuery(name = "TipoPerfilesNbl.findByCodigo", query = "SELECT t FROM TipoPerfilesNbl t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "TipoPerfilesNbl.findByNombre", query = "SELECT t FROM TipoPerfilesNbl t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "TipoPerfilesNbl.findByDescripcion", query = "SELECT t FROM TipoPerfilesNbl t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoPerfilesNbl.findByModo", query = "SELECT t FROM TipoPerfilesNbl t WHERE t.modo = :modo"),
    @NamedQuery(name = "TipoPerfilesNbl.findByEstatus", query = "SELECT t FROM TipoPerfilesNbl t WHERE t.estatus = :estatus")})
public class TipoPerfilesNbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MODO")
    private Character modo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS")
    private Character estatus;
    @OneToMany(mappedBy = "tipoPerfilesNblId")
    private List<PerfilesNbl> perfilesNblList;
    @Transient
    public static final String TIPO_PERFIL_CODIGO_MASTER = "bod.base.asociado.master";
    @Transient
    public static final String TIPO_PERFIL_CODIGO_NATURAL = "bod.base.natural";
    @Transient
    public static final String TIPO_PERFIL_CODIGO_JURIDICO = "bod.base.juridico";
    @Transient
    public static final String CODIGO_PERFIL_REGULAR = "bod.base.asociado.regular";
    @Transient
    public static final char VALOR_ESTADO_ACTIVO = 'A';
    @Transient
    public static final char VALOR_ESTADO_INACTIVO = 'I';

    public TipoPerfilesNbl() {
    }

    public TipoPerfilesNbl(Long id) {
        this.id = id;
    }

    public TipoPerfilesNbl(Long id, String codigo, String nombre, Character modo, Character estatus) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.modo = modo;
        this.estatus = estatus;
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

    public Character getModo() {
        return modo;
    }

    public void setModo(Character modo) {
        this.modo = modo;
    }

    public Character getEstatus() {
        return estatus;
    }

    @XmlTransient
    public List<PerfilesNbl> getPerfilesNblList() {
        return perfilesNblList;
    }

    public void setPerfilesNblList(List<PerfilesNbl> perfilesNblList) {
        this.perfilesNblList = perfilesNblList;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
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
        if (!(object instanceof TipoPerfilesNbl)) {
            return false;
        }
        TipoPerfilesNbl other = (TipoPerfilesNbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.entities.TipoPerfilesNbl[ id=" + id + " ]";
    }

}
