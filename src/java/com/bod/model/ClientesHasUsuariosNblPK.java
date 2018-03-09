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
public class ClientesHasUsuariosNblPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENTES_ID")
    private long clientesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USUARIOS_NBL_ID")
    private long usuariosNblId;

    public ClientesHasUsuariosNblPK() {
    }

    public ClientesHasUsuariosNblPK(long clientesId, long usuariosNblId) {
        this.clientesId = clientesId;
        this.usuariosNblId = usuariosNblId;
    }

    public long getClientesId() {
        return clientesId;
    }

    public void setClientesId(long clientesId) {
        this.clientesId = clientesId;
    }

    public long getUsuariosNblId() {
        return usuariosNblId;
    }

    public void setUsuariosNblId(long usuariosNblId) {
        this.usuariosNblId = usuariosNblId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) clientesId;
        hash += (int) usuariosNblId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ClientesHasUsuariosNblPK)) {
            return false;
        }
        ClientesHasUsuariosNblPK other = (ClientesHasUsuariosNblPK) object;
        if (this.clientesId != other.clientesId) {
            return false;
        }
        if (this.usuariosNblId != other.usuariosNblId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.ClientesHasUsuariosNblPK[ clientesId=" + clientesId + ", usuariosNblId=" + usuariosNblId + " ]";
    }
    
}
