/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
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
 * @author cfernandez
 */
@Entity
@Table(name = "MstTransaccionesNbl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MstTransaccionesNbl.findAll", query = "SELECT m FROM MstTransaccionesNbl m"),
    @NamedQuery(name = "MstTransaccionesNbl.findByPkIdTransaccionNbl", query = "SELECT m FROM MstTransaccionesNbl m WHERE m.pkIdTransaccionNbl = :pkIdTransaccionNbl"),
    @NamedQuery(name = "MstTransaccionesNbl.findByFecRegistro", query = "SELECT m FROM MstTransaccionesNbl m WHERE m.fecRegistro = :fecRegistro"),
    @NamedQuery(name = "MstTransaccionesNbl.findByFecEjecucion", query = "SELECT m FROM MstTransaccionesNbl m WHERE m.fecEjecucion = :fecEjecucion"),
    @NamedQuery(name = "MstTransaccionesNbl.findByEstado", query = "SELECT m FROM MstTransaccionesNbl m WHERE m.estado = :estado"),
    @NamedQuery(name = "MstTransaccionesNbl.findByRquid", query = "SELECT m FROM MstTransaccionesNbl m WHERE m.rquid = :rquid"),
    @NamedQuery(name = "MstTransaccionesNbl.findBySequence", query = "SELECT m FROM MstTransaccionesNbl m WHERE m.sequence = :sequence")})
@NamedNativeQuery(
        name = "MstTransaccionesNbl.listarFlujosAprobacion",
        query = "SELECT * FROM MSTTRANSACCIONESNBL WHERE FK_IDFLUJO IS NOT NULL AND FK_IDCLIENTES=? ORDER BY estado asc, fecRegistro desc",
        resultClass = MstTransaccionesNbl.class
)
public class MstTransaccionesNbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "MSTTRANSACCIONESNBL_SEQ", sequenceName = "MSTTRANSACCIONESNBL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSTTRANSACCIONESNBL_SEQ")
    @Id
    @Basic(optional = false)
    @Column(name = "pk_IdTransaccionNbl")
    private Long pkIdTransaccionNbl;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FecRegistro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecRegistro;
    @Basic(optional = false)

    @Column(name = "FecEjecucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecEjecucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Estado", nullable = false, length = 2)
    private String estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Rquid", nullable = false, length = 100)
    private String rquid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Sequence", nullable = false, length = 50)
    private String sequence;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO")
    private BigDecimal monto;

    @JoinColumn(name = "FK_IDOPERACION", referencedColumnName = "ID")
    @ManyToOne
    private OperacionesNbl fkIdoperacion;

    @JoinColumns({
        @JoinColumn(name = "FK_IDCLIENTES", referencedColumnName = "CLIENTES_ID"),
        @JoinColumn(name = "FK_IDUSUARIOS_NBL", referencedColumnName = "USUARIOS_NBL_ID")})
    @ManyToOne
    private ClientesHasUsuariosNbl clientesHasUsuariosNbl;
    @JoinColumn(name = "FK_IDCANAL", referencedColumnName = "ID")
    @ManyToOne
    private Canales fkIdcanal;

    @OneToMany(mappedBy = "fkIdTransaccionNbl", cascade = CascadeType.PERSIST)
    private List<DetTransaccionNbl> detTransaccionNblList;

    @Transient
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdTransaccionNbl")
    private List<DetTransaccionBeneficiarios> detTransaccionBeneficiariosList;

    @JoinColumn(name = "fk_IdFlujo", referencedColumnName = "PK_IDFLUJO")
    @ManyToOne
    private Cnfflujos fkIdFlujo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdTransaccion")
    private List<DetRechazo> detRechazoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdTransaccionesNbl")
    private List<DetEjecucionFlujo> detEjecucionFlujoList;

    @Transient
    private String estadoUsuario;
    
    @Transient
    private String tipoConsulta;

    public MstTransaccionesNbl() {
    }

    public MstTransaccionesNbl(Long pkIdTransaccionNbl) {
        this.pkIdTransaccionNbl = pkIdTransaccionNbl;
    }

    public MstTransaccionesNbl(Date fecRegistro, String estado, String rquid, String sequence, BigDecimal monto, Date fechaEjecucion) {
        this.fecRegistro = fecRegistro;
        this.estado = estado;
        this.rquid = rquid;
        this.sequence = sequence;
        this.monto = monto;
        this.fecEjecucion = fechaEjecucion;
    }

    public MstTransaccionesNbl(Long pkIdTransaccionNbl, Date fecRegistro, Date fecEjecucion, String estado, String rquid, String sequence) {
        this.pkIdTransaccionNbl = pkIdTransaccionNbl;
        this.fecRegistro = fecRegistro;
        this.fecEjecucion = fecEjecucion;
        this.estado = estado;
        this.rquid = rquid;
        this.sequence = sequence;
    }

    public Long getPkIdTransaccionNbl() {
        return pkIdTransaccionNbl;
    }

    public void setPkIdTransaccionNbl(Long pkIdTransaccionNbl) {
        this.pkIdTransaccionNbl = pkIdTransaccionNbl;
    }

    public Date getFecRegistro() {
        return fecRegistro;
    }

    public void setFecRegistro(Date fecRegistro) {
        this.fecRegistro = fecRegistro;
    }

    public Date getFecEjecucion() {
        return fecEjecucion;
    }

    public void setFecEjecucion(Date fecEjecucion) {
        this.fecEjecucion = fecEjecucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRquid() {
        return rquid;
    }

    public void setRquid(String rquid) {
        this.rquid = rquid;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    @XmlTransient
    public List<DetTransaccionNbl> getDetTransaccionNblList() {
        return detTransaccionNblList;
    }

    public void setDetTransaccionNblList(List<DetTransaccionNbl> detTransaccionNblList) {
        this.detTransaccionNblList = detTransaccionNblList;
    }

    @XmlTransient
    public List<DetTransaccionBeneficiarios> getDetTransaccionBeneficiariosList() {
        return detTransaccionBeneficiariosList;
    }

    public void setDetTransaccionBeneficiariosList(List<DetTransaccionBeneficiarios> detTransaccionBeneficiariosList) {
        this.detTransaccionBeneficiariosList = detTransaccionBeneficiariosList;
    }

    public Cnfflujos getFkIdFlujo() {
        return fkIdFlujo;
    }

    public void setFkIdFlujo(Cnfflujos fkIdFlujo) {
        this.fkIdFlujo = fkIdFlujo;
    }

    @XmlTransient
    public List<DetRechazo> getDetRechazoList() {
        return detRechazoList;
    }

    public void setDetRechazoList(List<DetRechazo> detRechazoList) {
        this.detRechazoList = detRechazoList;
    }

    @XmlTransient
    public List<DetEjecucionFlujo> getDetEjecucionFlujoList() {
        return detEjecucionFlujoList;
    }

    public OperacionesNbl getFkIdoperacion() {
        return fkIdoperacion;
    }

    public void setFkIdoperacion(OperacionesNbl fkIdoperacion) {
        this.fkIdoperacion = fkIdoperacion;
    }

    public ClientesHasUsuariosNbl getClientesHasUsuariosNbl() {
        return clientesHasUsuariosNbl;
    }

    public void setClientesHasUsuariosNbl(ClientesHasUsuariosNbl clientesHasUsuariosNbl) {
        this.clientesHasUsuariosNbl = clientesHasUsuariosNbl;
    }

    public Canales getFkIdcanal() {
        return fkIdcanal;
    }

    public void setFkIdcanal(Canales fkIdcanal) {
        this.fkIdcanal = fkIdcanal;
    }

    public void setDetEjecucionFlujoList(List<DetEjecucionFlujo> detEjecucionFlujoList) {
        this.detEjecucionFlujoList = detEjecucionFlujoList;
    }

    public String getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(String estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdTransaccionNbl != null ? pkIdTransaccionNbl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MstTransaccionesNbl)) {
            return false;
        }
        MstTransaccionesNbl other = (MstTransaccionesNbl) object;
        if ((this.pkIdTransaccionNbl == null && other.pkIdTransaccionNbl != null) || (this.pkIdTransaccionNbl != null && !this.pkIdTransaccionNbl.equals(other.pkIdTransaccionNbl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.MstTransaccionesNbl[ pkIdTransaccionNbl=" + pkIdTransaccionNbl + " ]";
    }

    public void addDetTransaccionNbl(DetTransaccionNbl detTransaccionNbl) {
        if (detTransaccionNblList == null) {
            detTransaccionNblList = new ArrayList();
        }
        detTransaccionNblList.add(detTransaccionNbl);
    }

}
