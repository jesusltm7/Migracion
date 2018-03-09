package com.bod.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ernesto Cascante
 */
@Entity
@Table(name = "TMPAUTORIZACIONESSIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IBAutorizacionesSIP.findAll", query = "SELECT a FROM IBAutorizacionesSIP a"),
    @NamedQuery(name = "IBAutorizacionesSIP.finByCliente", query = "SELECT a FROM IBAutorizacionesSIP a WHERE a.numeroCliente = :numeroCliente"),
    @NamedQuery(name = "IBAutorizacionesSIP.finByClienteTipoContratoNombreUsuario", query = "SELECT a FROM IBAutorizacionesSIP a WHERE a.numeroCliente = :numeroCliente AND a.usuarioAsociado = :usuarioAsociado AND a.codigoTipoContrato = :codigoTipoContrato"),
    @NamedQuery(name = "IBAutorizacionesSIP.findAllByClienteANDUsuario", query = "SELECT a FROM IBAutorizacionesSIP a WHERE a.numeroCliente = :numeroCliente AND a.usuarioAsociado=:usuarioAsociado")
})
public class IBAutorizacionesSIP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDAUTORIZACIONESSIP")
    private Long id;
    @Column(name = "NUMCLIENTE")
    private int numeroCliente;
    @Column(name = "NOMUSUARIO")
    private String usuarioAsociado;
    @Column(name = "CODTIPOCONTRATOE2EW")
    private String codigoTipoContrato;
    @Column(name = "VALNIVELDEAUTORIZACION")
    private String nivelAutorizacion;
    @Column(name = "VALESTADOAUTORIZACION")
    private String estadoAutorizacion;
    @Transient
    public static final String CODIGO_CONTRATO_NOMINA = "NS";
    @Transient
    public static final String CODIGO_CONTRATO_PROVEEDORES = "OS";
    @Transient
    public static final String CODIGO_ROL_APROBADOR_SOLICITANTE = "SO";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getUsuarioAsociado() {
        return usuarioAsociado;
    }

    public void setUsuarioAsociado(String usuarioAsociado) {
        this.usuarioAsociado = usuarioAsociado;
    }

    public String getCodigoTipoContrato() {
        return codigoTipoContrato;
    }

    public void setCodigoTipoContrato(String codigoTipoContrato) {
        this.codigoTipoContrato = codigoTipoContrato;
    }

    public String getNivelAutorizacion() {
        return nivelAutorizacion;
    }

    public void setNivelAutorizacion(String nivelAutorizacion) {
        this.nivelAutorizacion = nivelAutorizacion;
    }

    public String getEstadoAutorizacion() {
        return estadoAutorizacion;
    }

    public void setEstadoAutorizacion(String estadoAutorizacion) {
        this.estadoAutorizacion = estadoAutorizacion;
    }

}