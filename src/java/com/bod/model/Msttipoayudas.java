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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Romero
 */
@Entity
@Table(name = "MSTTIPOAYUDAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Msttipoayudas.findAll", query = "SELECT m FROM Msttipoayudas m"),
    @NamedQuery(name = "Msttipoayudas.findByPkIdtipoayuda", query = "SELECT m FROM Msttipoayudas m WHERE m.pkIdtipoayuda = :pkIdtipoayuda"),
    @NamedQuery(name = "Msttipoayudas.findByDestipoayuda", query = "SELECT m FROM Msttipoayudas m WHERE m.destipoayuda = :destipoayuda"),
    @NamedQuery(name = "Msttipoayudas.findByCodtipoayuda", query = "SELECT m FROM Msttipoayudas m WHERE m.codtipoayuda = :codtipoayuda")})
public class Msttipoayudas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDTIPOAYUDA")
    private Long pkIdtipoayuda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DESTIPOAYUDA")
    private String destipoayuda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CODTIPOAYUDA")
    private String codtipoayuda;
    @Transient
    public static final String CODIGO_TIPO_AYUDA_INTERNA = "AI";
    @Transient
    public static final String CODIGO_TIPO_AYUDA_LEGAL = "AL";
    
    

    public Msttipoayudas() {
    }

    public Msttipoayudas(Long pkIdtipoayuda) {
        this.pkIdtipoayuda = pkIdtipoayuda;
    }

    public Msttipoayudas(Long pkIdtipoayuda, String destipoayuda, String codtipoayuda) {
        this.pkIdtipoayuda = pkIdtipoayuda;
        this.destipoayuda = destipoayuda;
        this.codtipoayuda = codtipoayuda;
    }

    public Long getPkIdtipoayuda() {
        return pkIdtipoayuda;
    }

    public void setPkIdtipoayuda(Long pkIdtipoayuda) {
        this.pkIdtipoayuda = pkIdtipoayuda;
    }

    public String getDestipoayuda() {
        return destipoayuda;
    }

    public void setDestipoayuda(String destipoayuda) {
        this.destipoayuda = destipoayuda;
    }

    public String getCodtipoayuda() {
        return codtipoayuda;
    }

    public void setCodtipoayuda(String codtipoayuda) {
        this.codtipoayuda = codtipoayuda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdtipoayuda != null ? pkIdtipoayuda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Msttipoayudas)) {
            return false;
        }
        Msttipoayudas other = (Msttipoayudas) object;
        if ((this.pkIdtipoayuda == null && other.pkIdtipoayuda != null) || (this.pkIdtipoayuda != null && !this.pkIdtipoayuda.equals(other.pkIdtipoayuda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Msttipoayudas[ pkIdtipoayuda=" + pkIdtipoayuda + " ]";
    }

}
