/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author Intec
 */
@Entity
@Table(name = "MSTREGLASPERFIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mstreglasperfil.findAll", query = "SELECT m FROM Mstreglasperfil m"),
    @NamedQuery(name = "Mstreglasperfil.findByPkIdmstreglasperfil", query = "SELECT m FROM Mstreglasperfil m WHERE m.pkIdmstreglasperfil = :pkIdmstreglasperfil"),
    @NamedQuery(name = "Mstreglasperfil.findByDesreglaperfil", query = "SELECT m FROM Mstreglasperfil m WHERE m.desreglaperfil = :desreglaperfil"),
    @NamedQuery(name = "Mstreglasperfil.findByEstreglaperfil", query = "SELECT m FROM Mstreglasperfil m WHERE m.estreglaperfil = :estreglaperfil"),
    @NamedQuery(name = "Mstreglasperfil.findByCodreglaperfil", query = "SELECT m FROM Mstreglasperfil m WHERE m.codreglaperfil = :codreglaperfil")})
public class Mstreglasperfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDMSTREGLASPERFIL")
    private Long pkIdmstreglasperfil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    private String desreglaperfil;
    @Basic(optional = false)
    @NotNull
    private short estreglaperfil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    private String codreglaperfil;
    @JoinColumn(name = "FK_IDPERFIL", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PerfilesNbl fkIdperfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdreglaperfil")
    private Collection<Detreglaparametros> detreglaparametrosCollection;
    @Transient
    public static final String CODIGO_PERFIL_BANCA_NATURAL_COMERCIAL = "bcn";
    @Transient
    public static final String CODIGO_PERFIL_BANCA_JURIDCA_COMERCIAL = "bcj";

    public Mstreglasperfil() {
    }

    public Mstreglasperfil(Long pkIdmstreglasperfil) {
        this.pkIdmstreglasperfil = pkIdmstreglasperfil;
    }

    public Mstreglasperfil(Long pkIdmstreglasperfil, String desreglaperfil, short estreglaperfil, String codreglaperfil) {
        this.pkIdmstreglasperfil = pkIdmstreglasperfil;
        this.desreglaperfil = desreglaperfil;
        this.estreglaperfil = estreglaperfil;
        this.codreglaperfil = codreglaperfil;
    }

    public Long getPkIdmstreglasperfil() {
        return pkIdmstreglasperfil;
    }

    public void setPkIdmstreglasperfil(Long pkIdmstreglasperfil) {
        this.pkIdmstreglasperfil = pkIdmstreglasperfil;
    }

    public String getDesreglaperfil() {
        return desreglaperfil;
    }

    public void setDesreglaperfil(String desreglaperfil) {
        this.desreglaperfil = desreglaperfil;
    }

    public short getEstreglaperfil() {
        return estreglaperfil;
    }

    public void setEstreglaperfil(short estreglaperfil) {
        this.estreglaperfil = estreglaperfil;
    }

    public String getCodreglaperfil() {
        return codreglaperfil;
    }

    public void setCodreglaperfil(String codreglaperfil) {
        this.codreglaperfil = codreglaperfil;
    }

    public PerfilesNbl getFkIdperfil() {
        return fkIdperfil;
    }

    public void setFkIdperfil(PerfilesNbl fkIdperfil) {
        this.fkIdperfil = fkIdperfil;
    }

    @XmlTransient
    public Collection<Detreglaparametros> getDetreglaparametrosCollection() {
        return detreglaparametrosCollection;
    }

    public void setDetreglaparametrosCollection(Collection<Detreglaparametros> detreglaparametrosCollection) {
        this.detreglaparametrosCollection = detreglaparametrosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdmstreglasperfil != null ? pkIdmstreglasperfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mstreglasperfil)) {
            return false;
        }
        Mstreglasperfil other = (Mstreglasperfil) object;
        if ((this.pkIdmstreglasperfil == null && other.pkIdmstreglasperfil != null) || (this.pkIdmstreglasperfil != null && !this.pkIdmstreglasperfil.equals(other.pkIdmstreglasperfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Mstreglasperfil[ pkIdmstreglasperfil=" + pkIdmstreglasperfil + " ]";
    }

}
