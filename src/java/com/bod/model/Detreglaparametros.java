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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intec
 */
@Entity
@Table(name = "DETREGLAPARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detreglaparametros.findAll", query = "SELECT d FROM Detreglaparametros d"),
    @NamedQuery(name = "Detreglaparametros.findByPkReglaparametro", query = "SELECT d FROM Detreglaparametros d WHERE d.pkReglaparametro = :pkReglaparametro"),
    @NamedQuery(name = "Detreglaparametros.findByValparametro", query = "SELECT d FROM Detreglaparametros d WHERE d.valparametro = :valparametro"),
    @NamedQuery(name = "Detreglaparametros.findByDesparametro", query = "SELECT d FROM Detreglaparametros d WHERE d.desparametro = :desparametro")})
public class Detreglaparametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_REGLAPARAMETRO")
    private Long pkReglaparametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    private String valparametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    private String desparametro;
    @JoinColumn(name = "FK_IDREGLAPERFIL", referencedColumnName = "PK_IDMSTREGLASPERFIL")
    @ManyToOne(optional = false)
    private Mstreglasperfil fkIdreglaperfil;
    @JoinColumn(name = "FK_IDPARAMETRO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Parametros fkIdparametro;

    public Detreglaparametros() {
    }

    public Detreglaparametros(Long pkReglaparametro) {
        this.pkReglaparametro = pkReglaparametro;
    }

    public Detreglaparametros(Long pkReglaparametro, String valparametro, String desparametro) {
        this.pkReglaparametro = pkReglaparametro;
        this.valparametro = valparametro;
        this.desparametro = desparametro;
    }

    public Long getPkReglaparametro() {
        return pkReglaparametro;
    }

    public void setPkReglaparametro(Long pkReglaparametro) {
        this.pkReglaparametro = pkReglaparametro;
    }

    public String getValparametro() {
        return valparametro;
    }

    public void setValparametro(String valparametro) {
        this.valparametro = valparametro;
    }

    public String getDesparametro() {
        return desparametro;
    }

    public void setDesparametro(String desparametro) {
        this.desparametro = desparametro;
    }

    public Mstreglasperfil getFkIdreglaperfil() {
        return fkIdreglaperfil;
    }

    public void setFkIdreglaperfil(Mstreglasperfil fkIdreglaperfil) {
        this.fkIdreglaperfil = fkIdreglaperfil;
    }

    public Parametros getFkIdparametro() {
        return fkIdparametro;
    }

    public void setFkIdparametro(Parametros fkIdparametro) {
        this.fkIdparametro = fkIdparametro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkReglaparametro != null ? pkReglaparametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detreglaparametros)) {
            return false;
        }
        Detreglaparametros other = (Detreglaparametros) object;
        if ((this.pkReglaparametro == null && other.pkReglaparametro != null) || (this.pkReglaparametro != null && !this.pkReglaparametro.equals(other.pkReglaparametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Detreglaparametros[ pkReglaparametro=" + pkReglaparametro + " ]";
    }

}
