/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cfernandez
 */
@Entity
@Table(name = "CLIENTES_HAS_USUARIOS_NBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientesHasUsuariosNbl.findAll", query = "SELECT c FROM ClientesHasUsuariosNbl c"),
    @NamedQuery(name = "ClientesHasUsuariosNbl.findByClientesId", query = "SELECT c FROM ClientesHasUsuariosNbl c WHERE c.clientesHasUsuariosNblPK.clientesId = :clientesId"),
    @NamedQuery(name = "ClientesHasUsuariosNbl.findByUsuariosNblId", query = "SELECT c FROM ClientesHasUsuariosNbl c WHERE c.clientesHasUsuariosNblPK.usuariosNblId = :usuariosNblId"),
    @NamedQuery(name = "ClientesHasUsuariosNbl.findByPerfilesNblId", query = "SELECT c FROM ClientesHasUsuariosNbl c WHERE c.perfilesNblId = :perfilesNblId"),
    @NamedQuery(name = "ClientesHasUsuariosNbl.findByEstatus", query = "SELECT c FROM ClientesHasUsuariosNbl c WHERE c.estatus = :estatus")})
public class ClientesHasUsuariosNbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ClientesHasUsuariosNblPK clientesHasUsuariosNblPK;
    @JoinColumn(name = "FK_IDTIPOCONDICION", referencedColumnName = "PK_IDTIPOCONDICION")
    @ManyToOne
    private Msttiposcondicion fkIdtipocondicion;
    @Column(name = "ESTATUS")
    private Character estatus;
    @Lob
    @Column(name = "AVATAR")
    private byte[] avatar;
    @OneToMany(mappedBy = "clientesHasUsuariosNbl")
    private List<BitacoraNbl> bitacoraNblList;
    @JoinColumn(name = "USUARIOS_NBL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UsuariosNbl usuariosNbl;
    @JoinColumn(name = "PERFILES_NBL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PerfilesNbl perfilesNblId;
    @JoinColumn(name = "FK_IDTIPOENVIO", referencedColumnName = "PK_IDMSTMEDIO")
    @ManyToOne(optional = false)
    private Mstmedioenvio fkIdtipoenvio;

    @JoinColumn(name = "CLIENTES_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Clientes clientes;
    @OneToMany(mappedBy = "clientesHasUsuariosNbl")
    private List<Cnfflujos> cnfflujosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesHasUsuariosNbl")
    private List<DetEjecucionFlujo> detEjecucionFlujoList;
    @Column(name = "ZONASEGURA")
    private int zonaSegura;

    @Column(name = "EQUIPOFRECUENTE")
    private int equipoFrecuente;

    @Transient
    private List<DispositivosUsuariosNbl> listadoDispositivosUsuario;
    @Transient
    public static final char VALOR_ESTATUS_ACTIVO = 'A';
    @Transient
    public static final char VALOR_ESTATUS_INACTIVO = 'I';
    @Transient
    public static final char VALOR_ESTATUS_DESAFILIADO = 'D';

    /**
     * permite especificar si un usuario supero la validacion del equipo de uso
     * frecuente
     */
    @Transient
    private boolean equipoValidado;

    public ClientesHasUsuariosNbl() {
    }

    public ClientesHasUsuariosNbl(ClientesHasUsuariosNblPK clientesHasUsuariosNblPK) {
        this.clientesHasUsuariosNblPK = clientesHasUsuariosNblPK;
    }

    public void remove(ClientesHasUsuariosNbl usuario) {

    }

    public Msttiposcondicion getFkIdtipocondicion() {
        return fkIdtipocondicion;
    }

    public void setFkIdtipocondicion(Msttiposcondicion fkIdtipocondicion) {
        this.fkIdtipocondicion = fkIdtipocondicion;
    }

    public ClientesHasUsuariosNbl(ClientesHasUsuariosNblPK clientesHasUsuariosNblPK, Character estatus) {
        this.clientesHasUsuariosNblPK = clientesHasUsuariosNblPK;
        this.estatus = estatus;
    }

    public ClientesHasUsuariosNbl(long clientesId, long usuariosNblId) {
        this.clientesHasUsuariosNblPK = new ClientesHasUsuariosNblPK(clientesId, usuariosNblId);
    }

    public ClientesHasUsuariosNblPK getClientesHasUsuariosNblPK() {
        return clientesHasUsuariosNblPK;
    }

    public void setClientesHasUsuariosNblPK(ClientesHasUsuariosNblPK clientesHasUsuariosNblPK) {
        this.clientesHasUsuariosNblPK = clientesHasUsuariosNblPK;
    }

    public PerfilesNbl getPerfilesNblId() {
        return perfilesNblId;
    }

    public void setPerfilesNblId(PerfilesNbl perfilesNblId) {
        this.perfilesNblId = perfilesNblId;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    @XmlTransient
    public List<BitacoraNbl> getBitacoraNblList() {
        return bitacoraNblList;
    }

    public void setBitacoraNblList(List<BitacoraNbl> bitacoraNblList) {
        this.bitacoraNblList = bitacoraNblList;
    }

    public UsuariosNbl getUsuariosNbl() {
        return usuariosNbl;
    }

    public void setUsuariosNbl(UsuariosNbl usuariosNbl) {
        this.usuariosNbl = usuariosNbl;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @XmlTransient
    public List<Cnfflujos> getCnfflujosList() {
        return cnfflujosList;
    }

    public void setCnfflujosList(List<Cnfflujos> cnfflujosList) {
        this.cnfflujosList = cnfflujosList;
    }

    @XmlTransient
    public List<DetEjecucionFlujo> getDetEjecucionFlujoList() {
        return detEjecucionFlujoList;
    }

    public void setDetEjecucionFlujoList(List<DetEjecucionFlujo> detEjecucionFlujoList) {
        this.detEjecucionFlujoList = detEjecucionFlujoList;
    }

    public boolean isEquipoValidado() {
        return equipoValidado;
    }

    public void setEquipoValidado(boolean equipoValidado) {
        this.equipoValidado = equipoValidado;
    }

    public Mstmedioenvio getFkIdtipoenvio() {
        return fkIdtipoenvio;
    }

    public void setFkIdtipoenvio(Mstmedioenvio fkIdtipoenvio) {
        this.fkIdtipoenvio = fkIdtipoenvio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientesHasUsuariosNblPK != null ? clientesHasUsuariosNblPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientesHasUsuariosNbl)) {
            return false;
        }
        ClientesHasUsuariosNbl other = (ClientesHasUsuariosNbl) object;
        if ((this.clientesHasUsuariosNblPK == null && other.clientesHasUsuariosNblPK != null) || (this.clientesHasUsuariosNblPK != null && !this.clientesHasUsuariosNblPK.equals(other.clientesHasUsuariosNblPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.ClientesHasUsuariosNbl[ clientesHasUsuariosNblPK=" + clientesHasUsuariosNblPK + " ]";
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public int isZonaSegura() {
        return zonaSegura;
    }

    public boolean obtenerZonaSegura() {
        return zonaSegura == 1;
    }

    public void setZonaSegura(int zonaSegura) {
        this.zonaSegura = zonaSegura;
    }

    public List<DispositivosUsuariosNbl> getListadoDispositivosUsuario() {
        return listadoDispositivosUsuario;
    }

    public void setListadoDispositivosUsuario(List<DispositivosUsuariosNbl> listadoDispositivosUsuario) {
        this.listadoDispositivosUsuario = listadoDispositivosUsuario;
    }

    public int getEquipoFrecuente() {
        return equipoFrecuente;
    }

    public void setEquipoFrecuente(int equipoFrecuente) {
        this.equipoFrecuente = equipoFrecuente;
    }

}
