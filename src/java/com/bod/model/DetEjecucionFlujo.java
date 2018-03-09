/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CF
 */
@Entity
@Table(name = "DetEjecucionFlujo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetEjecucionFlujo.findAll", query = "SELECT d FROM DetEjecucionFlujo d"),
    @NamedQuery(name = "DetEjecucionFlujo.findByPkIdEjecucionFlujo", query = "SELECT d FROM DetEjecucionFlujo d WHERE d.pkIdEjecucionFlujo = :pkIdEjecucionFlujo"),
    @NamedQuery(name = "DetEjecucionFlujo.findByEstado", query = "SELECT d FROM DetEjecucionFlujo d WHERE d.estado = :estado"),
    @NamedQuery(name = "DetEjecucionFlujo.findByDescripcion", query = "SELECT d FROM DetEjecucionFlujo d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DetEjecucionFlujo.findByFecRegistro", query = "SELECT d FROM DetEjecucionFlujo d WHERE d.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "DetEjecucionFlujo.findByUsuario", query = "SELECT d FROM DetEjecucionFlujo d WHERE d.clientesHasUsuariosNbl.usuariosNbl.id = :idUsuario")})
public class DetEjecucionFlujo implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DETEJECUCIONFLUJO_SEQ", sequenceName = "DETEJECUCIONFLUJO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETEJECUCIONFLUJO_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_IdEjecucionFlujo", nullable = false)
    private Long pkIdEjecucionFlujo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "Estado", nullable = false, length = 1)
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "Descripcion", nullable = false, length = 250)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FecRegistro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    /*@JoinColumn(name = "fk_IdFlujo", referencedColumnName = "PK_IDFLUJO", nullable = false)
    @ManyToOne(optional = false)
    private Cnfflujos fkIdFlujo;*/
    @JoinColumns({
        @JoinColumn(name = "fk_IdCliente", referencedColumnName = "CLIENTES_ID"),
        @JoinColumn(name = "fk_IdUsuarioNbl", referencedColumnName = "USUARIOS_NBL_ID", nullable = false)})
    @ManyToOne(optional = false)
    private ClientesHasUsuariosNbl clientesHasUsuariosNbl;
    @JoinColumn(name = "FK_IDTRANSACCIONNBL", referencedColumnName = "PK_IDTRANSACCIONNBL", nullable = true)
    @ManyToOne(optional = false)
    private MstTransaccionesNbl fkIdTransaccionesNbl;
    
    @Column(name = "FK_IDTMPPROCESOSIP")
    private BigDecimal fkProcesoSip;
    
    public DetEjecucionFlujo() {
    }

    public DetEjecucionFlujo(Long pkIdEjecucionFlujo) {
        this.pkIdEjecucionFlujo = pkIdEjecucionFlujo;
    }

    public DetEjecucionFlujo(Long pkIdEjecucionFlujo, String estado, String descripcion, Date fecRegistro) {
        this.pkIdEjecucionFlujo = pkIdEjecucionFlujo;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fecRegistro = fecRegistro;
    }

    public Long getPkIdEjecucionFlujo() {
        return pkIdEjecucionFlujo;
    }

    public void setPkIdEjecucionFlujo(Long pkIdEjecucionFlujo) {
        this.pkIdEjecucionFlujo = pkIdEjecucionFlujo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    /*public Cnfflujos getFkIdFlujo() {
        return fkIdFlujo;
    }

    public void setFkIdFlujo(Cnfflujos fkIdFlujo) {
        this.fkIdFlujo = fkIdFlujo;
    }*/

    public ClientesHasUsuariosNbl getClientesHasUsuariosNbl() {
        return clientesHasUsuariosNbl;
    }

    public void setClientesHasUsuariosNbl(ClientesHasUsuariosNbl clientesHasUsuariosNbl) {
        this.clientesHasUsuariosNbl = clientesHasUsuariosNbl;
    }

    public MstTransaccionesNbl getFkIdTransaccionesNbl() {
        return fkIdTransaccionesNbl;
    }

    public void setFkIdTransaccionesNbl(MstTransaccionesNbl fkIdTransaccionesNbl) {
        this.fkIdTransaccionesNbl = fkIdTransaccionesNbl;
    }

    public BigDecimal getFkProcesoSip() {
        return fkProcesoSip;
    }

    public void setFkProcesoSip(BigDecimal fkProcesoSip) {
        this.fkProcesoSip = fkProcesoSip;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdEjecucionFlujo != null ? pkIdEjecucionFlujo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetEjecucionFlujo)) {
            return false;
        }
        DetEjecucionFlujo other = (DetEjecucionFlujo) object;
        if ((this.pkIdEjecucionFlujo == null && other.pkIdEjecucionFlujo != null) || (this.pkIdEjecucionFlujo != null && !this.pkIdEjecucionFlujo.equals(other.pkIdEjecucionFlujo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.DetEjecucionFlujo[ pkIdEjecucionFlujo=" + pkIdEjecucionFlujo + " ]";
    }
    
}
