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
 * @author cfernandez
 */
@Entity
@Table(name = "MSTBINES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mstbines.findAll", query = "SELECT m FROM Mstbines m"),
    @NamedQuery(name = "Mstbines.findByPkIdbin", query = "SELECT m FROM Mstbines m WHERE m.pkIdbin = :pkIdbin"),
    @NamedQuery(name = "Mstbines.findByTipobanco", query = "SELECT m FROM Mstbines m WHERE m.tipobanco = :tipobanco"),
    @NamedQuery(name = "Mstbines.findByBintarjeta", query = "SELECT m FROM Mstbines m WHERE m.bintarjeta = :bintarjeta"),
    @NamedQuery(name = "Mstbines.findByDesbin", query = "SELECT m FROM Mstbines m WHERE m.desbin = :desbin")})
public class Mstbines implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDBIN")
    private Long pkIdbin;
    @Size(max = 1)
    @Column(name = "TIPOBANCO")
    private String tipobanco;
    @Column(name = "BINTARJETA")
    private Integer bintarjeta;
    @Size(max = 20)
    @Column(name = "DESBIN")
    private String desbin;
    @JoinColumn(name = "FK_IDBANCO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Bancos fkIdbanco;

    public Mstbines() {
    }

    public Mstbines(Long pkIdbin) {
        this.pkIdbin = pkIdbin;
    }

    public Long getPkIdbin() {
        return pkIdbin;
    }

    public void setPkIdbin(Long pkIdbin) {
        this.pkIdbin = pkIdbin;
    }

    public String getTipobanco() {
        return tipobanco;
    }

    public void setTipobanco(String tipobanco) {
        this.tipobanco = tipobanco;
    }

    public Integer getBintarjeta() {
        return bintarjeta;
    }

    public void setBintarjeta(Integer bintarjeta) {
        this.bintarjeta = bintarjeta;
    }

    public String getDesbin() {
        return desbin;
    }

    public void setDesbin(String desbin) {
        this.desbin = desbin;
    }

    public Bancos getFkIdbanco() {
        return fkIdbanco;
    }

    public void setFkIdbanco(Bancos fkIdbanco) {
        this.fkIdbanco = fkIdbanco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdbin != null ? pkIdbin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mstbines)) {
            return false;
        }
        Mstbines other = (Mstbines) object;
        if ((this.pkIdbin == null && other.pkIdbin != null) || (this.pkIdbin != null && !this.pkIdbin.equals(other.pkIdbin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Mstbines[ pkIdbin=" + pkIdbin + " ]";
    }
    
}
