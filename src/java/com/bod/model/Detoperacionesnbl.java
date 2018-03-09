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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author intec
 */
@Entity
@Table(name = "DETOPERACIONESNBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detoperacionesnbl.findAll", query = "SELECT d FROM Detoperacionesnbl d"),
    @NamedQuery(name = "Detoperacionesnbl.findByPkIddetoperacion", query = "SELECT d FROM Detoperacionesnbl d WHERE d.pkIddetoperacion = :pkIddetoperacion"),
    @NamedQuery(name = "Detoperacionesnbl.findByCategoriaProducto", query = "SELECT d FROM Detoperacionesnbl d WHERE d.fkIdtipoproducto = :fkIdtipoproducto")})

public class Detoperacionesnbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "DETOPERACIONESNBL_SEQ", sequenceName = "DETOPERACIONESNBL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETOPERACIONESNBL_SEQ")
    @Column(name = "PK_IDDETOPERACION")
    private Long pkIddetoperacion;
    @JoinColumn(name = "FK_IDOPERACIONBL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private OperacionesNbl fkIdoperacionbl;
    @JoinColumn(name = "FK_IDTIPOPRODUCTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private CategoriaProductos fkIdtipoproducto;

    public Detoperacionesnbl() {
    }

    public Detoperacionesnbl(Long pkIddetoperacion) {
        this.pkIddetoperacion = pkIddetoperacion;
    }

    public Long getPkIddetoperacion() {
        return pkIddetoperacion;
    }

    public void setPkIddetoperacion(Long pkIddetoperacion) {
        this.pkIddetoperacion = pkIddetoperacion;
    }

    public OperacionesNbl getFkIdoperacionbl() {
        return fkIdoperacionbl;
    }

    public void setFkIdoperacionbl(OperacionesNbl fkIdoperacionbl) {
        this.fkIdoperacionbl = fkIdoperacionbl;
    }

    public CategoriaProductos getFkIdtipoproducto() {
        return fkIdtipoproducto;
    }

    public void setFkIdtipoproducto(CategoriaProductos fkIdtipoproducto) {
        this.fkIdtipoproducto = fkIdtipoproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIddetoperacion != null ? pkIddetoperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detoperacionesnbl)) {
            return false;
        }
        Detoperacionesnbl other = (Detoperacionesnbl) object;
        if ((this.pkIddetoperacion == null && other.pkIddetoperacion != null) || (this.pkIddetoperacion != null && !this.pkIddetoperacion.equals(other.pkIddetoperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Detoperacionesnbl[ pkIddetoperacion=" + pkIddetoperacion + " ]";
    }

}
