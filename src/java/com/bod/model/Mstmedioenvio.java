/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maria Gutierrez
 */
@Entity
@Cacheable(true)
@Table(name = "MSTMEDIOENVIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mstmedioenvio.findAll", query = "SELECT m FROM Mstmedioenvio m"),
    @NamedQuery(name = "Mstmedioenvio.findByPkIdmstmedio", query = "SELECT m FROM Mstmedioenvio m WHERE m.pkIdmstmedio = :pkIdmstmedio"),
    @NamedQuery(name = "Mstmedioenvio.findByNommedio", query = "SELECT m FROM Mstmedioenvio m WHERE m.nommedio = :nommedio"),
    @NamedQuery(name = "Mstmedioenvio.findByDesmedio", query = "SELECT m FROM Mstmedioenvio m WHERE m.desmedio = :desmedio"),
    @NamedQuery(name = "Mstmedioenvio.findByEstatus", query = "SELECT m FROM Mstmedioenvio m WHERE m.estatus = :estatus"),
    @NamedQuery(name = "Mstmedioenvio.findByCodmedio", query = "SELECT m FROM Mstmedioenvio m WHERE m.codmedio = :codmedio")})
public class Mstmedioenvio implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDMSTMEDIO")
    private BigDecimal pkIdmstmedio;
    @Size(max = 45)
    @Column(name = "NOMMEDIO")
    private String nommedio;
    @Size(max = 145)
    @Column(name = "DESMEDIO")
    private String desmedio;
    @Column(name = "ESTATUS")
    private Character estatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODMEDIO")
    private short codmedio;

    public Mstmedioenvio() {
    }

    public Mstmedioenvio(BigDecimal pkIdmstmedio) {
        this.pkIdmstmedio = pkIdmstmedio;
    }

    public Mstmedioenvio(BigDecimal pkIdmstmedio, short codmedio) {
        this.pkIdmstmedio = pkIdmstmedio;
        this.codmedio = codmedio;
    }

    public BigDecimal getPkIdmstmedio() {
        return pkIdmstmedio;
    }

    public void setPkIdmstmedio(BigDecimal pkIdmstmedio) {
        this.pkIdmstmedio = pkIdmstmedio;
    }

    public String getNommedio() {
        return nommedio;
    }

    public void setNommedio(String nommedio) {
        this.nommedio = nommedio;
    }

    public String getDesmedio() {
        return desmedio;
    }

    public void setDesmedio(String desmedio) {
        this.desmedio = desmedio;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    public short getCodmedio() {
        return codmedio;
    }

    public void setCodmedio(short codmedio) {
        this.codmedio = codmedio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdmstmedio != null ? pkIdmstmedio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mstmedioenvio)) {
            return false;
        }
        Mstmedioenvio other = (Mstmedioenvio) object;
        if ((this.pkIdmstmedio == null && other.pkIdmstmedio != null) || (this.pkIdmstmedio != null && !this.pkIdmstmedio.equals(other.pkIdmstmedio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Mstmedioenvio[ pkIdmstmedio=" + pkIdmstmedio + " ]";
    }
    
}
