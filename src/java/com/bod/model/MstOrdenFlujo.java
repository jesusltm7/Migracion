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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CF
 */
@Entity
@Table(name = "MstOrdenFlujo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MstOrdenFlujo.findAll", query = "SELECT m FROM MstOrdenFlujo m"),
    @NamedQuery(name = "MstOrdenFlujo.findByPkIdOrdenFlujo", query = "SELECT m FROM MstOrdenFlujo m WHERE m.pkIdOrdenFlujo = :pkIdOrdenFlujo"),
    @NamedQuery(name = "MstOrdenFlujo.findByOrdenEjecucion", query = "SELECT m FROM MstOrdenFlujo m WHERE m.ordenEjecucion = :ordenEjecucion"),
    @NamedQuery(name = "MstOrdenFlujo.findByRol", query = "SELECT m FROM MstOrdenFlujo m WHERE m.rol = :rol")})
public class MstOrdenFlujo implements Serializable {
    private static final long serialVersionUID = 1L;
	//@SequenceGenerator(name = "MSTORDENFLUJO_SEQ", sequenceName = "MSTORDENFLUJO_SEQ", allocationSize = 1)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSTORDENFLUJO_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_IdOrdenFlujo", nullable = false)
    private Long pkIdOrdenFlujo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrdenEjecucion", nullable = false)
    private long ordenEjecucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Rol", nullable = false, length = 2)
    private String rol;
    @JoinColumn(name = "fk_IdFlujoProceso", referencedColumnName = "PK_IDFLUJOPROCESO", nullable = false)
    @ManyToOne(optional = false)
    private Detflujoproceso fkIdFlujoProceso;
    
    @Transient
    private int aprobado;

    public MstOrdenFlujo() {
    }

    public MstOrdenFlujo(Long pkIdOrdenFlujo) {
        this.pkIdOrdenFlujo = pkIdOrdenFlujo;
    }

    public MstOrdenFlujo(Long pkIdOrdenFlujo, long ordenEjecucion, String rol) {
        this.pkIdOrdenFlujo = pkIdOrdenFlujo;
        this.ordenEjecucion = ordenEjecucion;
        this.rol = rol;
    }

    public Long getPkIdOrdenFlujo() {
        return pkIdOrdenFlujo;
    }

    public void setPkIdOrdenFlujo(Long pkIdOrdenFlujo) {
        this.pkIdOrdenFlujo = pkIdOrdenFlujo;
    }

    public long getOrdenEjecucion() {
        return ordenEjecucion;
    }

    public void setOrdenEjecucion(long ordenEjecucion) {
        this.ordenEjecucion = ordenEjecucion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Detflujoproceso getFkIdFlujoProceso() {
        return fkIdFlujoProceso;
    }

    public void setFkIdFlujoProceso(Detflujoproceso fkIdFlujoProceso) {
        this.fkIdFlujoProceso = fkIdFlujoProceso;
    }

    public int getAprobado() {
        return aprobado;
    }

    public void setAprobado(int aprobado) {
        this.aprobado = aprobado;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdOrdenFlujo != null ? pkIdOrdenFlujo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MstOrdenFlujo)) {
            return false;
        }
        MstOrdenFlujo other = (MstOrdenFlujo) object;
        if ((this.pkIdOrdenFlujo == null && other.pkIdOrdenFlujo != null) || (this.pkIdOrdenFlujo != null && !this.pkIdOrdenFlujo.equals(other.pkIdOrdenFlujo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.MstOrdenFlujo[ pkIdOrdenFlujo=" + pkIdOrdenFlujo + " ]";
    }

}
