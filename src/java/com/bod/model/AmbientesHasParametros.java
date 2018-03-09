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
@Table(name = "AMBIENTES_HAS_PARAMETROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AmbientesHasParametros.findAll", query = "SELECT a FROM AmbientesHasParametros a"),
    @NamedQuery(name = "AmbientesHasParametros.findByAmbientesId", query = "SELECT a FROM AmbientesHasParametros a WHERE a.ambientesHasParametrosPK.ambientesId = :ambientesId"),
    @NamedQuery(name = "AmbientesHasParametros.findByParametrosId", query = "SELECT a FROM AmbientesHasParametros a WHERE a.ambientesHasParametrosPK.parametrosId = :parametrosId"),
    @NamedQuery(name = "AmbientesHasParametros.findByValor", query = "SELECT a FROM AmbientesHasParametros a WHERE a.valor = :valor")})
public class AmbientesHasParametros implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AmbientesHasParametrosPK ambientesHasParametrosPK;
    @Size(max = 256)
    @Column(name = "VALOR")
    private String valor;
    @JoinColumn(name = "PARAMETROS_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Parametros parametros;
    @JoinColumn(name = "AMBIENTES_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ambientes ambientes;

    public AmbientesHasParametros() {
    }

    public AmbientesHasParametros(AmbientesHasParametrosPK ambientesHasParametrosPK) {
        this.ambientesHasParametrosPK = ambientesHasParametrosPK;
    }

    public AmbientesHasParametros(long ambientesId, long parametrosId) {
        this.ambientesHasParametrosPK = new AmbientesHasParametrosPK(ambientesId, parametrosId);
    }

    public AmbientesHasParametrosPK getAmbientesHasParametrosPK() {
        return ambientesHasParametrosPK;
    }

    public void setAmbientesHasParametrosPK(AmbientesHasParametrosPK ambientesHasParametrosPK) {
        this.ambientesHasParametrosPK = ambientesHasParametrosPK;
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

    public Ambientes getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(Ambientes ambientes) {
        this.ambientes = ambientes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ambientesHasParametrosPK != null ? ambientesHasParametrosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AmbientesHasParametros)) {
            return false;
        }
        AmbientesHasParametros other = (AmbientesHasParametros) object;
        if ((this.ambientesHasParametrosPK == null && other.ambientesHasParametrosPK != null) || (this.ambientesHasParametrosPK != null && !this.ambientesHasParametrosPK.equals(other.ambientesHasParametrosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.AmbientesHasParametros[ ambientesHasParametrosPK=" + ambientesHasParametrosPK + " ]";
    }
    
}
