/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Intec
 */
@Entity
@Table(name = "DETLIMITES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detlimites.findAll", query = "SELECT d FROM Detlimites d"),
    @NamedQuery(name = "Detlimites.findByPkIddetlimite", query = "SELECT d FROM Detlimites d WHERE d.pkIddetlimite = :pkIddetlimite"),
    @NamedQuery(name = "Detlimites.findByValmontodiario", query = "SELECT d FROM Detlimites d WHERE d.valmontodiario = :valmontodiario"),
    @NamedQuery(name = "Detlimites.findByValtransdiario", query = "SELECT d FROM Detlimites d WHERE d.valtransdiario = :valtransdiario"),
    @NamedQuery(name = "Detlimites.findByValmontomensual", query = "SELECT d FROM Detlimites d WHERE d.valmontomensual = :valmontomensual"),
    @NamedQuery(name = "Detlimites.findByPerfil", query = "SELECT d FROM Detlimites d WHERE d.fkIdperfil = :fkIdperfil"),
    @NamedQuery(name = "Detlimites.findByPerfilesProductos", query = "SELECT d FROM Detlimites d WHERE d.fkIdperfil = :fkIdperfil AND d.fkIdproducto = :fkIdproducto "),
    @NamedQuery(name = "Detlimites.findByProductoPerfilyOperacion", query = "SELECT d FROM Detlimites d WHERE d.fkIdperfil = :fkIdperfil AND d.fkIdproducto = :fkIdproducto AND d.fkIdoperacion = :fkIdoperacion "),
    @NamedQuery(name = "Detlimites.findByPerfilOperacion", query = "SELECT d FROM Detlimites d WHERE d.fkIdperfil = :fkIdperfil AND d.fkIdoperacion = :fkIdoperacion "),
    @NamedQuery(name = "Detlimites.findByValtransmensual", query = "SELECT d FROM Detlimites d WHERE d.valtransmensual = :valtransmensual")})
public class Detlimites implements Serializable {

    private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "DETLIMITES_SEQ", sequenceName = "DETLIMITES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETLIMITES_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDDETLIMITE")
    private Long pkIddetlimite;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALMONTODIARIO")
    private BigDecimal valmontodiario;
    @Column(name = "VALTRANSDIARIO")
    private Long valtransdiario;
    @Column(name = "VALMONTOMENSUAL")
    private BigDecimal valmontomensual;
    @Column(name = "VALTRANSMENSUAL")
    private Long valtransmensual;
    @JoinColumn(name = "FK_IDAMBIENTE", referencedColumnName = "ID")
    @ManyToOne
    private Ambientes fkIdambiente;
    @JoinColumn(name = "FK_IDCANAL", referencedColumnName = "ID")
    @ManyToOne
    private Canales fkIdcanal;
    @JoinColumn(name = "FK_IDOPERACION", referencedColumnName = "ID")
    @ManyToOne
    private OperacionesNbl fkIdoperacion;
    @JoinColumn(name = "FK_IDPERFIL", referencedColumnName = "ID")
    @ManyToOne
    private PerfilesNbl fkIdperfil;
    @JoinColumn(name = "FK_IDPRODUCTO", referencedColumnName = "ID")
    @ManyToOne
    private Productos fkIdproducto;

    public Detlimites() {
    }

    public Detlimites(Long pkIddetlimite) {
        this.pkIddetlimite = pkIddetlimite;
    }

    public Long getPkIddetlimite() {
        return pkIddetlimite;
    }

    public void setPkIddetlimite(Long pkIddetlimite) {
        this.pkIddetlimite = pkIddetlimite;
    }

    public BigDecimal getValmontodiario() {
        return valmontodiario;
    }

    public void setValmontodiario(BigDecimal valmontodiario) {
        this.valmontodiario = valmontodiario;
    }

    public Long getValtransdiario() {
        return valtransdiario;
    }

    public void setValtransdiario(Long valtransdiario) {
        this.valtransdiario = valtransdiario;
    }

    public BigDecimal getValmontomensual() {
        return valmontomensual;
    }

    public void setValmontomensual(BigDecimal valmontomensual) {
        this.valmontomensual = valmontomensual;
    }

    public Long getValtransmensual() {
        return valtransmensual;
    }

    public void setValtransmensual(Long valtransmensual) {
        this.valtransmensual = valtransmensual;
    }

    public Ambientes getFkIdambiente() {
        return fkIdambiente;
    }

    public void setFkIdambiente(Ambientes fkIdambiente) {
        this.fkIdambiente = fkIdambiente;
    }

    public Canales getFkIdcanal() {
        return fkIdcanal;
    }

    public void setFkIdcanal(Canales fkIdcanal) {
        this.fkIdcanal = fkIdcanal;
    }

    public OperacionesNbl getFkIdoperacion() {
        return fkIdoperacion;
    }

    public void setFkIdoperacion(OperacionesNbl fkIdoperacion) {
        this.fkIdoperacion = fkIdoperacion;
    }

    public PerfilesNbl getFkIdperfil() {
        return fkIdperfil;
    }

    public void setFkIdperfil(PerfilesNbl fkIdperfil) {
        this.fkIdperfil = fkIdperfil;
    }

    public Productos getFkIdproducto() {
        return fkIdproducto;
    }

    public void setFkIdproducto(Productos fkIdproducto) {
        this.fkIdproducto = fkIdproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIddetlimite != null ? pkIddetlimite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detlimites)) {
            return false;
        }
        Detlimites other = (Detlimites) object;
        if ((this.pkIddetlimite == null && other.pkIddetlimite != null) || (this.pkIddetlimite != null && !this.pkIddetlimite.equals(other.pkIddetlimite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model2.Detlimites[ pkIddetlimite=" + pkIddetlimite + " ]";
    }

}
