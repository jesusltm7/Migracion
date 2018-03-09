/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class CanalesHasParametrosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANALES_ID")
    private long canalesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARAMETROS_ID")
    private long parametrosId;

    public CanalesHasParametrosPK() {
    }

    public CanalesHasParametrosPK(long canalesId, long parametrosId) {
        this.canalesId = canalesId;
        this.parametrosId = parametrosId;
    }

    public long getCanalesId() {
        return canalesId;
    }

    public void setCanalesId(long canalesId) {
        this.canalesId = canalesId;
    }

    public long getParametrosId() {
        return parametrosId;
    }

    public void setParametrosId(long parametrosId) {
        this.parametrosId = parametrosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) canalesId;
        hash += (int) parametrosId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CanalesHasParametrosPK)) {
            return false;
        }
        CanalesHasParametrosPK other = (CanalesHasParametrosPK) object;
        if (this.canalesId != other.canalesId) {
            return false;
        }
        if (this.parametrosId != other.parametrosId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.CanalesHasParametrosPK[ canalesId=" + canalesId + ", parametrosId=" + parametrosId + " ]";
    }
    
}
