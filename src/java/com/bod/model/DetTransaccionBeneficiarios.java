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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CF
 */
@Entity
@Table(name = "DetTransaccionBeneficiarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetTransaccionBeneficiarios.findAll", query = "SELECT d FROM DetTransaccionBeneficiarios d"),
    @NamedQuery(name = "DetTransaccionBeneficiarios.findByPkIdTransaccionBeneficiario", query = "SELECT d FROM DetTransaccionBeneficiarios d WHERE d.pkIdTransaccionBeneficiario = :pkIdTransaccionBeneficiario")})
public class DetTransaccionBeneficiarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DETTRANSBENEF_SEQ", sequenceName = "DETTRANSBENEF_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETTRANSBENEF_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_IdTransaccionBeneficiario", nullable = false)
    private Long pkIdTransaccionBeneficiario;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "ListaBeneficiarios", nullable = false)
    private Serializable listaBeneficiarios;
    @Transient
    //@JoinColumn(name = "fk_IdTransaccionNbl", referencedColumnName = "pk_IdTransaccionNbl", nullable = false)
    //@ManyToOne(optional = false)
    private MstTransaccionesNbl fkIdTransaccionNbl;

    public DetTransaccionBeneficiarios() {
    }

    public DetTransaccionBeneficiarios(Long pkIdTransaccionBeneficiario) {
        this.pkIdTransaccionBeneficiario = pkIdTransaccionBeneficiario;
    }

    public DetTransaccionBeneficiarios(Long pkIdTransaccionBeneficiario, Serializable listaBeneficiarios) {
        this.pkIdTransaccionBeneficiario = pkIdTransaccionBeneficiario;
        this.listaBeneficiarios = listaBeneficiarios;
    }

    public Long getPkIdTransaccionBeneficiario() {
        return pkIdTransaccionBeneficiario;
    }

    public void setPkIdTransaccionBeneficiario(Long pkIdTransaccionBeneficiario) {
        this.pkIdTransaccionBeneficiario = pkIdTransaccionBeneficiario;
    }

    public Serializable getListaBeneficiarios() {
        return listaBeneficiarios;
    }

    public void setListaBeneficiarios(Serializable listaBeneficiarios) {
        this.listaBeneficiarios = listaBeneficiarios;
    }

    public MstTransaccionesNbl getFkIdTransaccionNbl() {
        return fkIdTransaccionNbl;
    }

    public void setFkIdTransaccionNbl(MstTransaccionesNbl fkIdTransaccionNbl) {
        this.fkIdTransaccionNbl = fkIdTransaccionNbl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdTransaccionBeneficiario != null ? pkIdTransaccionBeneficiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetTransaccionBeneficiarios)) {
            return false;
        }
        DetTransaccionBeneficiarios other = (DetTransaccionBeneficiarios) object;
        if ((this.pkIdTransaccionBeneficiario == null && other.pkIdTransaccionBeneficiario != null) || (this.pkIdTransaccionBeneficiario != null && !this.pkIdTransaccionBeneficiario.equals(other.pkIdTransaccionBeneficiario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.DetTransaccionBeneficiarios[ pkIdTransaccionBeneficiario=" + pkIdTransaccionBeneficiario + " ]";
    }
    
}
