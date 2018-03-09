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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TEXTOS")
@Cacheable(true)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Textos.findAll", query = "SELECT t FROM Textos t"),
    @NamedQuery(name = "Textos.findById", query = "SELECT t FROM Textos t WHERE t.id = :id"),
    @NamedQuery(name = "Textos.findByCodigo", query = "SELECT t FROM Textos t WHERE t.codigo = :codigo"),
    @NamedQuery(name = "Textos.findByTexto", query = "SELECT t FROM Textos t WHERE t.texto = :texto")})
public class Textos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 2000)
    @Column(name = "TEXTO")
    private String texto;
    @JoinColumn(name = "TIPO_TEXTO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoTexto tipoTextoId;    
    @JoinColumn(name = "IDIOMAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Idiomas idiomasId;
    @JoinColumn(name = "AMBIENTES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ambientes ambientesId;

    public Textos() {
    }

    public Textos(Long id) {
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TipoTexto getTipoTextoId() {
        return tipoTextoId;
    }

    public void setTipoTextoId(TipoTexto tipoTextoId) {
        this.tipoTextoId = tipoTextoId;
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
        if (!(object instanceof Textos)) {
            return false;
        }
        Textos other = (Textos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Textos[ id=" + id + " ]";
    }
    
}
