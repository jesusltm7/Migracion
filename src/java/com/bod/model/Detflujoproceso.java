/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KALTEC2
 */
@Entity
@Table(name = "DETFLUJOPROCESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detflujoproceso.findAll", query = "SELECT d FROM Detflujoproceso d"),
    @NamedQuery(name = "Detflujoproceso.findByPkIdflujoproceso", query = "SELECT d FROM Detflujoproceso d WHERE d.pkIdflujoproceso = :pkIdflujoproceso"),
    @NamedQuery(name = "Detflujoproceso.findByNumproceso", query = "SELECT d FROM Detflujoproceso d WHERE d.numproceso = :numproceso"),
    @NamedQuery(name = "Detflujoproceso.findByTipofirma", query = "SELECT d FROM Detflujoproceso d WHERE d.tipofirma = :tipofirma")})
public class Detflujoproceso implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DETFLUJOPROCESO_SEQ", sequenceName = "DETFLUJOPROCESO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETFLUJOPROCESO_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDFLUJOPROCESO")
    private Long pkIdflujoproceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMPROCESO")
    private BigInteger numproceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPOFIRMA")
    private Character tipofirma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdFlujoProceso")
    @OrderBy("ordenEjecucion ASC")
    private List<MstOrdenFlujo> mstOrdenFlujoList;
    @JoinColumn(name = "FK_IDFLUJO", referencedColumnName = "PK_IDFLUJO")
    @ManyToOne(optional = false)
    private Cnfflujos fkIdflujo;
    
    @Column(name = "VALSUSTITO")
    private int sustitucion;

    public Detflujoproceso() {
    }

    public Detflujoproceso(Long pkIdflujoproceso) {
        this.pkIdflujoproceso = pkIdflujoproceso;
    }

    public Detflujoproceso(Long pkIdflujoproceso, BigInteger numproceso, Character tipofirma) {
        this.pkIdflujoproceso = pkIdflujoproceso;
        this.numproceso = numproceso;
        this.tipofirma = tipofirma;
    }

    public Long getPkIdflujoproceso() {
        return pkIdflujoproceso;
    }

    public void setPkIdflujoproceso(Long pkIdflujoproceso) {
        this.pkIdflujoproceso = pkIdflujoproceso;
    }

    public BigInteger getNumproceso() {
        return numproceso;
    }

    public void setNumproceso(BigInteger numproceso) {
        this.numproceso = numproceso;
    }
    
    public int getSustitucion() {
        return sustitucion;
    }

    public void setSustitucion(int sustitucion) {
        this.sustitucion = sustitucion;
    }

    
    
    
    public Character getTipofirma() {
        return tipofirma;
    }

    public void setTipofirma(Character tipofirma) {
        this.tipofirma = tipofirma;
    }

    @XmlTransient
    public List<MstOrdenFlujo> getMstOrdenFlujoList() {
        return mstOrdenFlujoList;
    }

    public void setMstOrdenFlujoList(List<MstOrdenFlujo> mstOrdenFlujoList) {
        this.mstOrdenFlujoList = mstOrdenFlujoList;
    }

    public Cnfflujos getFkIdflujo() {
        return fkIdflujo;
    }

    public void setFkIdflujo(Cnfflujos fkIdflujo) {
        this.fkIdflujo = fkIdflujo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdflujoproceso != null ? pkIdflujoproceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detflujoproceso)) {
            return false;
        }
        Detflujoproceso other = (Detflujoproceso) object;
        if ((this.pkIdflujoproceso == null && other.pkIdflujoproceso != null) || (this.pkIdflujoproceso != null && !this.pkIdflujoproceso.equals(other.pkIdflujoproceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Detflujoproceso[ pkIdflujoproceso=" + pkIdflujoproceso + " ]";
    }
    
}
