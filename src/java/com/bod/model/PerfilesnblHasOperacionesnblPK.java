package com.bod.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mariesther
 */
@Embeddable
public class PerfilesnblHasOperacionesnblPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "PERFILES_NBL_ID")
    private long perfilesNblId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OPERACIONES_NBL_ID")
    private long operacionesNblId;

    public PerfilesnblHasOperacionesnblPK() {
    }

    public PerfilesnblHasOperacionesnblPK(long perfilesNblId, long operacionesNblId) {
        this.perfilesNblId = perfilesNblId;
        this.operacionesNblId = operacionesNblId;
    }

    public long getPerfilesNblId() {
        return perfilesNblId;
    }

    public void setPerfilesNblId(long perfilesNblId) {
        this.perfilesNblId = perfilesNblId;
    }

    public long getOperacionesNblId() {
        return operacionesNblId;
    }

    public void setOperacionesNblId(long operacionesNblId) {
        this.operacionesNblId = operacionesNblId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) perfilesNblId;
        hash += (int) operacionesNblId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PerfilesnblHasOperacionesnblPK)) {
            return false;
        }
        PerfilesnblHasOperacionesnblPK other = (PerfilesnblHasOperacionesnblPK) object;
        if (this.perfilesNblId != other.perfilesNblId) {
            return false;
        }
        if (this.operacionesNblId != other.operacionesNblId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.PerfilesnblHasOperacionesnblPK[ perfilesNblId=" + perfilesNblId + ", operacionesNblId=" + operacionesNblId + " ]";
    }
    
}
