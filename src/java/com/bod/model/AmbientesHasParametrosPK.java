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
public class AmbientesHasParametrosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMBIENTES_ID")
    private long ambientesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PARAMETROS_ID")
    private long parametrosId;

    public AmbientesHasParametrosPK() {
    }

    public AmbientesHasParametrosPK(long ambientesId, long parametrosId) {
        this.ambientesId = ambientesId;
        this.parametrosId = parametrosId;
    }

    public long getAmbientesId() {
        return ambientesId;
    }

    public void setAmbientesId(long ambientesId) {
        this.ambientesId = ambientesId;
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
        hash += (int) ambientesId;
        hash += (int) parametrosId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AmbientesHasParametrosPK)) {
            return false;
        }
        AmbientesHasParametrosPK other = (AmbientesHasParametrosPK) object;
        if (this.ambientesId != other.ambientesId) {
            return false;
        }
        if (this.parametrosId != other.parametrosId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.AmbientesHasParametrosPK[ ambientesId=" + ambientesId + ", parametrosId=" + parametrosId + " ]";
    }
    
}
