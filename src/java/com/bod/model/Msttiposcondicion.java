/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author intec
 */
@Entity
@Table(name = "MSTTIPOSCONDICION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Msttiposcondicion.findAll", query = "SELECT m FROM Msttiposcondicion m"),
    @NamedQuery(name = "Msttiposcondicion.findByPkIdtipocondicion", query = "SELECT m FROM Msttiposcondicion m WHERE m.pkIdtipocondicion = :pkIdtipocondicion"),
    @NamedQuery(name = "Msttiposcondicion.findByCodcondicion", query = "SELECT m FROM Msttiposcondicion m WHERE m.codcondicion = :codcondicion"),
    @NamedQuery(name = "Msttiposcondicion.findByDescondicion", query = "SELECT m FROM Msttiposcondicion m WHERE m.descondicion = :descondicion"),
    @NamedQuery(name = "Msttiposcondicion.findByEstcondicion", query = "SELECT m FROM Msttiposcondicion m WHERE m.estcondicion = :estcondicion")})
public class Msttiposcondicion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDTIPOCONDICION")
    private Long pkIdtipocondicion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CODCONDICION")
    private String codcondicion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DESCONDICION")
    private String descondicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTCONDICION")
    private short estcondicion;
    @OneToMany(mappedBy = "fkIdtipocondicion")
    private List<ClientesHasUsuariosNbl> clientesHasUsuariosNblList;

    public Msttiposcondicion() {
    }

    public Msttiposcondicion(Long pkIdtipocondicion) {
        this.pkIdtipocondicion = pkIdtipocondicion;
    }

    public Msttiposcondicion(Long pkIdtipocondicion, String codcondicion, String descondicion, short estcondicion) {
        this.pkIdtipocondicion = pkIdtipocondicion;
        this.codcondicion = codcondicion;
        this.descondicion = descondicion;
        this.estcondicion = estcondicion;
    }

    public Long getPkIdtipocondicion() {
        return pkIdtipocondicion;
    }

    public void setPkIdtipocondicion(Long pkIdtipocondicion) {
        this.pkIdtipocondicion = pkIdtipocondicion;
    }

    public String getCodcondicion() {
        return codcondicion;
    }

    public void setCodcondicion(String codcondicion) {
        this.codcondicion = codcondicion;
    }

    public String getDescondicion() {
        return descondicion;
    }

    public void setDescondicion(String descondicion) {
        this.descondicion = descondicion;
    }

    public short getEstcondicion() {
        return estcondicion;
    }

    public void setEstcondicion(short estcondicion) {
        this.estcondicion = estcondicion;
    }

    @XmlTransient
    public List<ClientesHasUsuariosNbl> getClientesHasUsuariosNblList() {
        return clientesHasUsuariosNblList;
    }

    public void setClientesHasUsuariosNblList(List<ClientesHasUsuariosNbl> clientesHasUsuariosNblList) {
        this.clientesHasUsuariosNblList = clientesHasUsuariosNblList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdtipocondicion != null ? pkIdtipocondicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Msttiposcondicion)) {
            return false;
        }
        Msttiposcondicion other = (Msttiposcondicion) object;
        if ((this.pkIdtipocondicion == null && other.pkIdtipocondicion != null) || (this.pkIdtipocondicion != null && !this.pkIdtipocondicion.equals(other.pkIdtipocondicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Msttiposcondicion[ pkIdtipocondicion=" + pkIdtipocondicion + " ]";
    }
    
}
