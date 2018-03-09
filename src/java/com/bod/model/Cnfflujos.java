/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bod.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Table(name = "CNFFLUJOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cnfflujos.findAll", query = "SELECT c FROM Cnfflujos c"),
    @NamedQuery(name = "Cnfflujos.findByPkIdflujo", query = "SELECT c FROM Cnfflujos c WHERE c.pkIdflujo = :pkIdflujo"),
    @NamedQuery(name = "Cnfflujos.findByDesflujo", query = "SELECT c FROM Cnfflujos c WHERE c.desflujo = :desflujo"),
    @NamedQuery(name = "Cnfflujos.findByValestadoflujo", query = "SELECT c FROM Cnfflujos c WHERE c.valestadoflujo = :valestadoflujo"),
    @NamedQuery(name = "Cnfflujos.findByValmontomin", query = "SELECT c FROM Cnfflujos c WHERE c.valmontomin = :valmontomin"),
    @NamedQuery(name = "Cnfflujos.findByValmontomax", query = "SELECT c FROM Cnfflujos c WHERE c.valmontomax = :valmontomax"),
    @NamedQuery(name = "Cnfflujos.findByVersion", query = "SELECT c FROM Cnfflujos c WHERE c.version = :version"),
    @NamedQuery(name = "Cnfflujos.findByOperacionEstadoMontos", query = "SELECT c FROM Cnfflujos c WHERE c.clientesHasUsuariosNbl = :clientesHasUsuariosNbl AND c.valestadoflujo = :estado AND c.fkIdoperacionnbl = :operacionNbl and :monto BETWEEN c.valmontomin AND c.valmontomax and c.version=1"),
    @NamedQuery(name = "Cnfflujos.findByOperacionEstado", query = "SELECT c FROM Cnfflujos c WHERE c.clientesHasUsuariosNbl = :clientesHasUsuariosNbl AND c.valestadoflujo = :estado AND c.fkIdoperacionnbl = :operacionNbl and c.version=1")})
public class Cnfflujos implements Serializable {
    private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "CNFFLUJOS_SEQ", sequenceName = "CNFFLUJOS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CNFFLUJOS_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDFLUJO")
    private Long pkIdflujo;
    @Size(max = 140)
    @Column(name = "DESFLUJO")
    private String desflujo;
    @Column(name = "VALESTADOFLUJO")
    private Character valestadoflujo;
    @Column(name = "VALMONTOMIN")
    private BigDecimal valmontomin;
    @Column(name = "VALMONTOMAX")
    private BigDecimal valmontomax;
    @Column(name = "VERSION")
    private Long version;
    @Transient
    //@OneToMany(mappedBy = "fkIdFlujo")
    private List<MstTransaccionesNbl> mstTransaccionesNblList;
    @JoinColumn(name = "FK_IDOPERACIONNBL", referencedColumnName = "ID")
    @ManyToOne
    private OperacionesNbl fkIdoperacionnbl;
    @JoinColumns({
        @JoinColumn(name = "FK_IDCLIENTE", referencedColumnName = "CLIENTES_ID"),
        @JoinColumn(name = "fk_IdUsuarioNbl", referencedColumnName = "USUARIOS_NBL_ID")})
    @ManyToOne
    private ClientesHasUsuariosNbl clientesHasUsuariosNbl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdflujo")
    @OrderBy("numproceso ASC")
    private List<Detflujoproceso> detflujoprocesoList;
    
    @Transient
    private List<DetEjecucionFlujo> flujoCrearTemporal;
    
    @Transient
    private int numeroFlujo;

    public Cnfflujos() {
    }

    public Cnfflujos(Long pkIdflujo) {
        this.pkIdflujo = pkIdflujo;
    }

    public Long getPkIdflujo() {
        return pkIdflujo;
    }

    public void setPkIdflujo(Long pkIdflujo) {
        this.pkIdflujo = pkIdflujo;
    }

    public String getDesflujo() {
        return desflujo;
    }

    public void setDesflujo(String desflujo) {
        this.desflujo = desflujo;
    }

    public Character getValestadoflujo() {
        return valestadoflujo;
    }

    public void setValestadoflujo(Character valestadoflujo) {
        this.valestadoflujo = valestadoflujo;
    }

    public BigDecimal getValmontomin() {
        return valmontomin;
    }

    public void setValmontomin(BigDecimal valmontomin) {
        this.valmontomin = valmontomin;
    }

    public BigDecimal getValmontomax() {
        return valmontomax;
    }

    public void setValmontomax(BigDecimal valmontomax) {
        this.valmontomax = valmontomax;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @XmlTransient
    public List<MstTransaccionesNbl> getMstTransaccionesNblList() {
        return mstTransaccionesNblList;
    }

    public void setMstTransaccionesNblList(List<MstTransaccionesNbl> mstTransaccionesNblList) {
        this.mstTransaccionesNblList = mstTransaccionesNblList;
    }

    public OperacionesNbl getFkIdoperacionnbl() {
        return fkIdoperacionnbl;
    }

    public void setFkIdoperacionnbl(OperacionesNbl fkIdoperacionnbl) {
        this.fkIdoperacionnbl = fkIdoperacionnbl;
    }

    @XmlTransient
    public List<Detflujoproceso> getDetflujoprocesoList() {
        return detflujoprocesoList;
    }

    public void setDetflujoprocesoList(List<Detflujoproceso> detflujoprocesoList) {
        this.detflujoprocesoList = detflujoprocesoList;
    }

    public int getNumeroFlujo() {
        return numeroFlujo;
    }

    public void setNumeroFlujo(int numeroFlujo) {
        this.numeroFlujo = numeroFlujo;
    }

    /*@XmlTransient
    public List<DetEjecucionFlujo> getDetEjecucionFlujoList() {
        return detEjecucionFlujoList;
    }

    public void setDetEjecucionFlujoList(List<DetEjecucionFlujo> detEjecucionFlujoList) {
        this.detEjecucionFlujoList = detEjecucionFlujoList;
    }*/

    public ClientesHasUsuariosNbl getClientesHasUsuariosNbl() {
        return clientesHasUsuariosNbl;
    }

    public void setClientesHasUsuariosNbl(ClientesHasUsuariosNbl clientesHasUsuariosNbl) {
        this.clientesHasUsuariosNbl = clientesHasUsuariosNbl;
    }

    public List<DetEjecucionFlujo> getFlujoCrearTemporal() {
        return flujoCrearTemporal;
    }

    public void setFlujoCrearTemporal(List<DetEjecucionFlujo> flujoCrearTemporal) {
        this.flujoCrearTemporal = flujoCrearTemporal;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdflujo != null ? pkIdflujo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cnfflujos)) {
            return false;
        }
        Cnfflujos other = (Cnfflujos) object;
        if ((this.pkIdflujo == null && other.pkIdflujo != null) || (this.pkIdflujo != null && !this.pkIdflujo.equals(other.pkIdflujo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Cnfflujos[ pkIdflujo=" + pkIdflujo + " ]";
    }
    
}
