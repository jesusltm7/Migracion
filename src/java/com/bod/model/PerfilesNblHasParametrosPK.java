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
public class PerfilesNblHasParametrosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERFILES_NBL_ID")
    private long perfilesNblId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARAMETROS_ID")
    private long parametrosId;

    public PerfilesNblHasParametrosPK() {
    }

    public PerfilesNblHasParametrosPK(long perfilesNblId, long parametrosId) {
        this.perfilesNblId = perfilesNblId;
        this.parametrosId = parametrosId;
    }

    public long getPerfilesNblId() {
        return perfilesNblId;
    }

    public void setPerfilesNblId(long perfilesNblId) {
        this.perfilesNblId = perfilesNblId;
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
        hash += (int) perfilesNblId;
        hash += (int) parametrosId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PerfilesNblHasParametrosPK)) {
            return false;
        }
        PerfilesNblHasParametrosPK other = (PerfilesNblHasParametrosPK) object;
        if (this.perfilesNblId != other.perfilesNblId) {
            return false;
        }
        if (this.parametrosId != other.parametrosId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.PerfilesNblHasParametrosPK[ perfilesNblId=" + perfilesNblId + ", parametrosId=" + parametrosId + " ]";
    }
    
}
