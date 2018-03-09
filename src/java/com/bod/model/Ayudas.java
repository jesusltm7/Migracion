/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "AYUDAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ayudas.findAll", query = "SELECT a FROM Ayudas a"),
    @NamedQuery(name = "Ayudas.findByOperacionesAmbienteBanca", query = "SELECT a FROM Ayudas a WHERE a.operacionesNblId = :operacionesNblId AND a.pkAmbientes = :pkAmbientes AND a.pkPerfilesNbl = :pkPerfilesNbl"),
    @NamedQuery(name = "Ayudas.findByOperacionesAmbienteBancaTipoAyudaIdioma", query = "SELECT a FROM Ayudas a WHERE a.operacionesNblId = :operacionesNblId AND a.pkAmbientes = :pkAmbientes AND a.pkPerfilesNbl = :pkPerfilesNbl AND a.tipoAyuda = :tipoAyuda AND a.idiomasId = :idiomasId"),
    @NamedQuery(name = "Ayudas.findById", query = "SELECT a FROM Ayudas a WHERE a.id = :id"),
    @NamedQuery(name = "Ayudas.findByOperacionesNblId", query = "SELECT a FROM Ayudas a WHERE a.operacionesNblId = :operacionesNblId"),
    @NamedQuery(name = "Ayudas.findByDescripcionAyudas", query = "SELECT a FROM Ayudas a WHERE a.descripcionAyudas = :descripcionAyudas")})

public class Ayudas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "OPERACIONES_NBL_ID")
    private long operacionesNblId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_PERFILES_NBL")
    private long pkPerfilesNbl;

    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_AMBIENTES")
    private long pkAmbientes;

    @Size(min = 1, max = 2000)
    @Column(name = "DESCRIPCION_AYUDAS")
    private String descripcionAyudas;

    @Lob
    @Column(name = "DATA")
    private byte[] data;
    @Size(max = 10)
    @Column(name = "FORIMAGEN")
    private String forimagen;
    @Size(max = 50)
    @Column(name = "NOMIMAGEN")
    private String nomimagen;

    @JoinColumn(name = "IDIOMAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Idiomas idiomasId;

    @JoinColumn(name = "FK_IDTIPOAYUDA", referencedColumnName = "PK_IDTIPOAYUDA")
    @ManyToOne(optional = false)
    private Msttipoayudas tipoAyuda;


    public Ayudas() {
    }

    public Ayudas(Long id) {
        this.id = id;
    }

    public Ayudas(Long id, String descripcionAyudas, long pkPerfilesNbl, long pkAmbientes) {
        this.id = id;
        this.descripcionAyudas = descripcionAyudas;
        this.pkPerfilesNbl = pkPerfilesNbl;
        this.pkAmbientes = pkAmbientes;
    }

    public Long getId() {
        return id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getForimagen() {
        return forimagen;
    }

    public void setForimagen(String forimagen) {
        this.forimagen = forimagen;
    }

    public String getNomimagen() {
        return nomimagen;
    }

    public void setNomimagen(String nomimagen) {
        this.nomimagen = nomimagen;
    }

    public Msttipoayudas getTipoAyuda() {
        return tipoAyuda;
    }

    public void setTipoAyuda(Msttipoayudas tipoAyuda) {
        this.tipoAyuda = tipoAyuda;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getOperacionesNblId() {
        return operacionesNblId;
    }

    public void setOperacionesNblId(long operacionesNblId) {
        this.operacionesNblId = operacionesNblId;
    }

    public String getDescripcionAyudas() {
        return descripcionAyudas;
    }

    public long getPkPerfilesNbl() {
        return pkPerfilesNbl;
    }

    public void setPkPerfilesNbl(long pkPerfilesNbl) {
        this.pkPerfilesNbl = pkPerfilesNbl;
    }

    public long getPkAmbientes() {
        return pkAmbientes;
    }

    public void setPkAmbientes(long pkAmbientes) {
        this.pkAmbientes = pkAmbientes;
    }

    public void setDescripcionAyudas(String descripcionAyudas) {
        this.descripcionAyudas = descripcionAyudas;
    }

    public Idiomas getIdiomasId() {
        return idiomasId;
    }

    public void setIdiomasId(Idiomas idiomasId) {
        this.idiomasId = idiomasId;
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
        if (!(object instanceof Ayudas)) {
            return false;
        }
        Ayudas other = (Ayudas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bod.com.model.Ayudas[ id=" + id + " ]";
    }
}
