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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CF
 * @author Luis Alberto Olivares Pena <lolivares@finteccr.com>
 */
@Entity
@Table(name = "DetTransaccionNbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetTransaccionNbl.findAll", query = "SELECT d FROM DetTransaccionNbl d"),
    @NamedQuery(name = "DetTransaccionNbl.findByPkIdDetTransaccionNbl", query = "SELECT d FROM DetTransaccionNbl d WHERE d.pkIdDetTransaccionNbl = :pkIdDetTransaccionNbl"),
    @NamedQuery(name = "DetTransaccionNbl.findByEtiqueta", query = "SELECT d FROM DetTransaccionNbl d WHERE d.etiqueta = :etiqueta"),
    @NamedQuery(name = "DetTransaccionNbl.findByValor", query = "SELECT d FROM DetTransaccionNbl d WHERE d.valor = :valor")})
public class DetTransaccionNbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DETTRANSACCIONNBL_SEQ", sequenceName = "DETTRANSACCIONNBL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETTRANSACCIONNBL_SEQ")    
    @Id
    @Basic(optional = false)
    
    @Column(name = "pk_IdDetTransaccionNbl")
    private Long pkIdDetTransaccionNbl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Etiqueta",nullable=false, length = 50)
    private String etiqueta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Valor",nullable=false, length = 100)
    private String valor;
    
    @JoinColumn(name = "fk_IdTransaccionNbl", referencedColumnName = "pk_IdTransaccionNbl")
    @ManyToOne(optional = false)
    private MstTransaccionesNbl fkIdTransaccionNbl;
	
    @JoinColumn(name = "FK_IDPARAMETRO", referencedColumnName = "ID")
    @ManyToOne
    private Parametros fkIdparametro;

    public DetTransaccionNbl() {
    }

    public DetTransaccionNbl(Long pkIdDetTransaccionNbl) {
        this.pkIdDetTransaccionNbl = pkIdDetTransaccionNbl;
    }
    
    public DetTransaccionNbl(String etiqueta, String valor) {        
        this.etiqueta = etiqueta;
        this.valor = valor;
    }

    public DetTransaccionNbl(Long pkIdDetTransaccionNbl, String etiqueta, String valor) {
        this.pkIdDetTransaccionNbl = pkIdDetTransaccionNbl;
        this.etiqueta = etiqueta;
        this.valor = valor;
    }

    public Long getPkIdDetTransaccionNbl() {
        return pkIdDetTransaccionNbl;
    }

    public void setPkIdDetTransaccionNbl(Long pkIdDetTransaccionNbl) {
        this.pkIdDetTransaccionNbl = pkIdDetTransaccionNbl;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public MstTransaccionesNbl getFkIdTransaccionNbl() {
        return fkIdTransaccionNbl;
    }

    public void setFkIdTransaccionNbl(MstTransaccionesNbl fkIdTransaccionNbl) {
        this.fkIdTransaccionNbl = fkIdTransaccionNbl;
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
        hash += (pkIdDetTransaccionNbl != null ? pkIdDetTransaccionNbl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetTransaccionNbl)) {
            return false;
        }
        DetTransaccionNbl other = (DetTransaccionNbl) object;
        if ((this.pkIdDetTransaccionNbl == null && other.pkIdDetTransaccionNbl != null) || (this.pkIdDetTransaccionNbl != null && !this.pkIdDetTransaccionNbl.equals(other.pkIdDetTransaccionNbl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.DetTransaccionNbl[ pkIdDetTransaccionNbl=" + pkIdDetTransaccionNbl + " ]";
    }
    
}
