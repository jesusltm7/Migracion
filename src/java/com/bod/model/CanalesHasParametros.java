/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "CANALES_HAS_PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CanalesHasParametros.findAll", query = "SELECT c FROM CanalesHasParametros c"),
    @NamedQuery(name = "CanalesHasParametros.findByCanalesId", query = "SELECT c FROM CanalesHasParametros c WHERE c.canalesHasParametrosPK.canalesId = :canalesId"),
    @NamedQuery(name = "CanalesHasParametros.findByParametrosId", query = "SELECT c FROM CanalesHasParametros c WHERE c.canalesHasParametrosPK.parametrosId = :parametrosId"),
    @NamedQuery(name = "CanalesHasParametros.findByValor", query = "SELECT c FROM CanalesHasParametros c WHERE c.valor = :valor")})
public class CanalesHasParametros implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CanalesHasParametrosPK canalesHasParametrosPK;
    @Size(max = 256)
    @Column(name = "VALOR")
    private String valor;
    @JoinColumn(name = "PARAMETROS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Parametros parametros;
    @JoinColumn(name = "CANALES_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Canales canales;

    public CanalesHasParametros() {
    }

    public CanalesHasParametros(CanalesHasParametrosPK canalesHasParametrosPK) {
        this.canalesHasParametrosPK = canalesHasParametrosPK;
    }

    public CanalesHasParametros(long canalesId, long parametrosId) {
        this.canalesHasParametrosPK = new CanalesHasParametrosPK(canalesId, parametrosId);
    }

    public CanalesHasParametrosPK getCanalesHasParametrosPK() {
        return canalesHasParametrosPK;
    }

    public void setCanalesHasParametrosPK(CanalesHasParametrosPK canalesHasParametrosPK) {
        this.canalesHasParametrosPK = canalesHasParametrosPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

    public Canales getCanales() {
        return canales;
    }

    public void setCanales(Canales canales) {
        this.canales = canales;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (canalesHasParametrosPK != null ? canalesHasParametrosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CanalesHasParametros)) {
            return false;
        }
        CanalesHasParametros other = (CanalesHasParametros) object;
        if ((this.canalesHasParametrosPK == null && other.canalesHasParametrosPK != null) || (this.canalesHasParametrosPK != null && !this.canalesHasParametrosPK.equals(other.canalesHasParametrosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.CanalesHasParametros[ canalesHasParametrosPK=" + canalesHasParametrosPK + " ]";
    }
    
}
