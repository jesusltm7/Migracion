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
public class ModulosUsuariosNblPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_MODULO")
    private long idModulo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO_NBL")
    private long idUsuarioNbl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OPERACIONES_NBL_ID")
    private long idOperacionesNblId;

    public ModulosUsuariosNblPK() {
    }

    public ModulosUsuariosNblPK(long idModulo, long idUsuarioNbl, long idOperacionesNblId) {
        this.idModulo = idModulo;
        this.idUsuarioNbl = idUsuarioNbl;
        this.idOperacionesNblId = idOperacionesNblId;
    }

    public long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(long idModulo) {
        this.idModulo = idModulo;
    }

    public long getIdUsuarioNbl() {
        return idUsuarioNbl;
    }

    public void setIdUsuarioNbl(long idUsuarioNbl) {
        this.idUsuarioNbl = idUsuarioNbl;
    }

    public long getIdOperacionesNblId() {
        return idOperacionesNblId;
    }

    public void setIdOperacionesNblId(long idOperacionesNblId) {
        this.idOperacionesNblId = idOperacionesNblId;
    }
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idModulo;
        hash += (int) idUsuarioNbl;
        hash += (int) idOperacionesNblId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ModulosUsuariosNblPK)) {
            return false;
        }
        ModulosUsuariosNblPK other = (ModulosUsuariosNblPK) object;
        if (this.idOperacionesNblId != other.idOperacionesNblId) {
            return false;
        }
        if (this.idModulo != other.idModulo) {
            return false;
        }
        if (this.idUsuarioNbl != other.idUsuarioNbl) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.ModulosUsuariosNblPK[ idModulo=" + idModulo + ", idUsuarioNbl=" + idUsuarioNbl + ", idOperacionesNblId= "+idOperacionesNblId+" ]";
    }
    
}
