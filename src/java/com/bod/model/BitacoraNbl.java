/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author KALTEC2
 */
@Entity
@Table(name = "BITACORA_NBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BitacoraNbl.findAll", query = "SELECT b FROM BitacoraNbl b"),
    @NamedQuery(name = "BitacoraNbl.findById", query = "SELECT b FROM BitacoraNbl b WHERE b.id = :id"),
    @NamedQuery(name = "BitacoraNbl.findByDescripcion", query = "SELECT b FROM BitacoraNbl b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "BitacoraNbl.findByReferencia", query = "SELECT b FROM BitacoraNbl b WHERE b.referencia = :referencia"),
    @NamedQuery(name = "BitacoraNbl.findByFechaHora", query = "SELECT b FROM BitacoraNbl b WHERE b.fechaHora = :fechaHora"),
    @NamedQuery(name = "BitacoraNbl.findByIp", query = "SELECT b FROM BitacoraNbl b WHERE b.ip = :ip"),
    @NamedQuery(name = "BitacoraNbl.findByUserAgent", query = "SELECT b FROM BitacoraNbl b WHERE b.userAgent = :userAgent"),
    @NamedQuery(name = "BitacoraNbl.findByMedio", query = "SELECT b FROM BitacoraNbl b WHERE b.medio = :medio"),
    @NamedQuery(name = "BitacoraNbl.findByDispositivoMedio", query = "SELECT b FROM BitacoraNbl b WHERE b.dispositivoMedio = :dispositivoMedio"),
    @NamedQuery(name = "BitacoraNbl.findByTransaccion", query = "SELECT b FROM BitacoraNbl b WHERE b.fkIdtrasaccion in :idsTransaccion")})

public class BitacoraNbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "BITACORA_NBL_SEQ", sequenceName = "BITACORA_NBL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BITACORA_NBL_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 140)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "REFERENCIA")
    private String referencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @Basic(optional = false)
    @NotNull
    @Size(max = 45)
    @Column(name = "IP")
    private String ip;
    @Basic(optional = false)
    @NotNull
    @Size(max = 512)
    @Column(name = "USER_AGENT")
    private String userAgent;
    @Size(max = 255)
    @Column(name = "MEDIO", length = 255)
    private String medio;
    @Size(max = 10)
    @Column(name = "DISPOSITIVO_MEDIO")
    private String dispositivoMedio;
    @Size(max = 500)
    @Column(name = "DESMVC")
    private String desmvc;
    @Column(name = "FK_IDTRASACCION")
    private Long fkIdtrasaccion;
    @Size(max = 25)
    @Column(name = "CODCOMPROBANTE")
    private String codigoComprobante;
    @JoinColumn(name = "OPERACIONES_NBL_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private OperacionesNbl operacionesNblId;
    @JoinColumns({
        @JoinColumn(name = "CLIENTE_ID_HAS", referencedColumnName = "CLIENTES_ID"),
        @JoinColumn(name = "USUARIOS_ID_HAS", referencedColumnName = "USUARIOS_NBL_ID")})
    @ManyToOne
    private ClientesHasUsuariosNbl clientesHasUsuariosNbl;
    @JoinColumn(name = "CANALES_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Canales canalesId;
    @JoinColumn(name = "AMBIENTES_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private Ambientes ambientesId;
    @Column(name = "IDHSTEVENTO")
    private Long fkIdhstevento;

    @Transient
    private String monto;

    @Transient
    private String origen;

    @Transient
    private String destino;

    public BitacoraNbl() {
    }

    public BitacoraNbl(Long id) {
        this.id = id;
    }

    public BitacoraNbl(Long id, Date fechaHora, String ip, String userAgent) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }

    public String getDispositivoMedio() {
        return dispositivoMedio;
    }

    public void setDispositivoMedio(String dispositivoMedio) {
        this.dispositivoMedio = dispositivoMedio;
    }

    public String getDesmvc() {
        return desmvc;
    }

    public void setDesmvc(String desmvc) {
        this.desmvc = desmvc;
    }

    public Long getFkIdtrasaccion() {
        return fkIdtrasaccion;
    }

    public void setFkIdtrasaccion(Long fkIdtrasaccion) {
        this.fkIdtrasaccion = fkIdtrasaccion;
    }

    public String getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(String codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public OperacionesNbl getOperacionesNblId() {
        return operacionesNblId;
    }

    public void setOperacionesNblId(OperacionesNbl operacionesNblId) {
        this.operacionesNblId = operacionesNblId;
    }

    public ClientesHasUsuariosNbl getClientesHasUsuariosNbl() {
        return clientesHasUsuariosNbl;
    }

    public void setClientesHasUsuariosNbl(ClientesHasUsuariosNbl clientesHasUsuariosNbl) {
        this.clientesHasUsuariosNbl = clientesHasUsuariosNbl;
    }

    public Canales getCanalesId() {
        return canalesId;
    }

    public void setCanalesId(Canales canalesId) {
        this.canalesId = canalesId;
    }

    public Ambientes getAmbientesId() {
        return ambientesId;
    }

    public void setAmbientesId(Ambientes ambientesId) {
        this.ambientesId = ambientesId;
    }

    public Long getFkIdhstevento() {
        return fkIdhstevento;
    }

    public void setFkIdhstevento(Long fkIdhstevento) {
        this.fkIdhstevento = fkIdhstevento;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BitacoraNbl)) {
            return false;
        }
        BitacoraNbl other = (BitacoraNbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.BitacoraNbl[ id=" + id + " ]";
    }

}