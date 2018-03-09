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
@Table(name = "PERFILES_NBL_HAS_PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilesNblHasParametros.findAll", query = "SELECT p FROM PerfilesNblHasParametros p"),
    @NamedQuery(name = "PerfilesNblHasParametros.findByPerfilesNblId", query = "SELECT p FROM PerfilesNblHasParametros p WHERE p.perfilesNblHasParametrosPK.perfilesNblId = :perfilesNblId"),
    @NamedQuery(name = "PerfilesNblHasParametros.findByParametrosId", query = "SELECT p FROM PerfilesNblHasParametros p WHERE p.perfilesNblHasParametrosPK.parametrosId = :parametrosId"),
    @NamedQuery(name = "PerfilesNblHasParametros.findByValor", query = "SELECT p FROM PerfilesNblHasParametros p WHERE p.valor = :valor")})
public class PerfilesNblHasParametros implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PerfilesNblHasParametrosPK perfilesNblHasParametrosPK;
    @Size(max = 256)
    @Column(name = "VALOR")
    private String valor;
    @JoinColumn(name = "PERFILES_NBL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PerfilesNbl perfilesNbl;
    @JoinColumn(name = "PARAMETROS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Parametros parametros;

    public PerfilesNblHasParametros() {
    }

    public PerfilesNblHasParametros(PerfilesNblHasParametrosPK perfilesNblHasParametrosPK) {
        this.perfilesNblHasParametrosPK = perfilesNblHasParametrosPK;
    }

    public PerfilesNblHasParametros(long perfilesNblId, long parametrosId) {
        this.perfilesNblHasParametrosPK = new PerfilesNblHasParametrosPK(perfilesNblId, parametrosId);
    }

    public PerfilesNblHasParametrosPK getPerfilesNblHasParametrosPK() {
        return perfilesNblHasParametrosPK;
    }

    public void setPerfilesNblHasParametrosPK(PerfilesNblHasParametrosPK perfilesNblHasParametrosPK) {
        this.perfilesNblHasParametrosPK = perfilesNblHasParametrosPK;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public PerfilesNbl getPerfilesNbl() {
        return perfilesNbl;
    }

    public void setPerfilesNbl(PerfilesNbl perfilesNbl) {
        this.perfilesNbl = perfilesNbl;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilesNblHasParametrosPK != null ? perfilesNblHasParametrosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PerfilesNblHasParametros)) {
            return false;
        }
        PerfilesNblHasParametros other = (PerfilesNblHasParametros) object;
        if ((this.perfilesNblHasParametrosPK == null && other.perfilesNblHasParametrosPK != null) || (this.perfilesNblHasParametrosPK != null && !this.perfilesNblHasParametrosPK.equals(other.perfilesNblHasParametrosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.PerfilesNblHasParametros[ perfilesNblHasParametrosPK=" + perfilesNblHasParametrosPK + " ]";
    }
    
}
