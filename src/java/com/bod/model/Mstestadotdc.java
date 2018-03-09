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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yeniree Sanchez
 */
@Entity
@Table(name = "MSTESTADOTDC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mstestadotdc.findAll", query = "SELECT m FROM Mstestadotdc m"),
    @NamedQuery(name = "Mstestadotdc.findByPkIdestadotdc", query = "SELECT m FROM Mstestadotdc m WHERE m.pkIdestadotdc = :pkIdestadotdc"),
    @NamedQuery(name = "Mstestadotdc.findByCodbanco", query = "SELECT m FROM Mstestadotdc m WHERE m.codbanco = :codbanco"),
    @NamedQuery(name = "Mstestadotdc.findByCodunificado", query = "SELECT m FROM Mstestadotdc m WHERE m.codunificado = :codunificado"),
    @NamedQuery(name = "Mstestadotdc.findByCodsapf1021", query = "SELECT m FROM Mstestadotdc m WHERE m.codsapf1021 = :codsapf1021"),
    @NamedQuery(name = "Mstestadotdc.findByCodsapf1024", query = "SELECT m FROM Mstestadotdc m WHERE m.codsapf1024 = :codsapf1024"),
    @NamedQuery(name = "Mstestadotdc.findByDesestado", query = "SELECT m FROM Mstestadotdc m WHERE m.desestado = :desestado"),
    @NamedQuery(name = "Mstestadotdc.findByEstalerta", query = "SELECT m FROM Mstestadotdc m WHERE m.estalerta = :estalerta"),
    @NamedQuery(name = "Mstestadotdc.findAllOrderByDesestado", query = "SELECT m FROM Mstestadotdc m ORDER BY m.desestado")})
public class Mstestadotdc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDESTADOTDC")
    private Long pkIdestadotdc;
    @Column(name = "CODBANCO")
    private Short codbanco;
    @Size(max = 1)
    @Column(name = "CODUNIFICADO")
    private String codunificado;
    @Size(max = 2)
    @Column(name = "CODSAPF1021")
    private String codsapf1021;
    @Column(name = "CODSAPF1024")
    private Character codsapf1024;
    @Size(max = 30)
    @Column(name = "DESESTADO")
    private String desestado;
    @Column(name = "ESTALERTA")
    private Character estalerta;

    public Mstestadotdc() {
    }

    public Mstestadotdc(Long pkIdestadotdc) {
        this.pkIdestadotdc = pkIdestadotdc;
    }

    public Long getPkIdestadotdc() {
        return pkIdestadotdc;
    }

    public void setPkIdestadotdc(Long pkIdestadotdc) {
        this.pkIdestadotdc = pkIdestadotdc;
    }

    public Short getCodbanco() {
        return codbanco;
    }

    public void setCodbanco(Short codbanco) {
        this.codbanco = codbanco;
    }

    public String getCodunificado() {
        return codunificado;
    }

    public void setCodunificado(String codunificado) {
        this.codunificado = codunificado;
    }

    public String getCodsapf1021() {
        return codsapf1021;
    }

    public void setCodsapf1021(String codsapf1021) {
        this.codsapf1021 = codsapf1021;
    }

    public Character getCodsapf1024() {
        return codsapf1024;
    }

    public void setCodsapf1024(Character codsapf1024) {
        this.codsapf1024 = codsapf1024;
    }

    public String getDesestado() {
        return desestado;
    }

    public void setDesestado(String desestado) {
        this.desestado = desestado;
    }

    public Character getEstalerta() {
        return estalerta;
    }

    public void setEstalerta(Character estalerta) {
        this.estalerta = estalerta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdestadotdc != null ? pkIdestadotdc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mstestadotdc)) {
            return false;
        }
        Mstestadotdc other = (Mstestadotdc) object;
        if ((this.pkIdestadotdc == null && other.pkIdestadotdc != null) || (this.pkIdestadotdc != null && !this.pkIdestadotdc.equals(other.pkIdestadotdc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Mstestadotdc[ pkIdestadotdc=" + pkIdestadotdc + " ]";
    }
    
}
