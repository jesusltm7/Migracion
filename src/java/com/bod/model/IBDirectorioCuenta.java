package com.bod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ernesto Cascante Clase que mapea el directorio de cuentas de internet
 * Banking a incorporar en NBL.
 */
@Entity
@Table(name = "TMPE2BENEFI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IBDirectorioCuenta.findAll", query = "SELECT a FROM IBDirectorioCuenta a"),
    @NamedQuery(name = "IBDirectorioCuenta.findAllByNumeroCliente", query = "SELECT a FROM IBDirectorioCuenta a WHERE a.numeroCliente = :numeroCliente"),
    @NamedQuery(name = "IBDirectorioCuenta.obtenerRegistrosMigrar", query = "SELECT a FROM IBDirectorioCuenta a WHERE a.numeroCliente = :numeroCliente")
})
public class IBDirectorioCuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TMPE2BENEFI")
    private Long id;
    @Column(name = "E2EMPR")
    private int bancoCliente;
    @Column(name = "E2CUSC")
    private int numeroCliente;
    @Column(name = "E2USM2")
    private String nombreUsuario;
    @Column(name = "E2CLTR")
    private int tipoTransaccion;
    @Column(name = "E2CTAC")
    private String cuentaTransaccion;
    @Column(name = "E2BACU")
    private String bancoTransaccion;
    @Column(name = "E2NOMC")
    private String nombreBeneficiario;
    @Column(name = "E2IDEN")
    private BigDecimal identificacionBeneficiario;
    @Column(name = "E2BACO")
    private String nombreBancoBeneficiario;
    @Column(name = "E2ESTI")
    private String estado;
    @Column(name = "E2TIDB")
    private String tipoIdentificacionBeneficiario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBancoCliente() {
        return bancoCliente;
    }

    public void setBancoCliente(int bancoCliente) {
        this.bancoCliente = bancoCliente;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(int tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getCuentaTransaccion() {
        return cuentaTransaccion;
    }

    public void setCuentaTransaccion(String cuentaTransaccion) {
        this.cuentaTransaccion = cuentaTransaccion;
    }

    public String getBancoTransaccion() {
        return bancoTransaccion;
    }

    public void setBancoTransaccion(String bancoTransaccion) {
        this.bancoTransaccion = bancoTransaccion;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public BigDecimal getIdentificacionBeneficiario() {
        return identificacionBeneficiario;
    }

    public void setIdentificacionBeneficiario(BigDecimal identificacionBeneficiario) {
        this.identificacionBeneficiario = identificacionBeneficiario;
    }

    public String getNombreBancoBeneficiario() {
        return nombreBancoBeneficiario;
    }

    public void setNombreBancoBeneficiario(String nombreBancoBeneficiario) {
        this.nombreBancoBeneficiario = nombreBancoBeneficiario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoIdentificacionBeneficiario() {
        return tipoIdentificacionBeneficiario;
    }

    public void setTipoIdentificacionBeneficiario(String tipoIdentificacionBeneficiario) {
        this.tipoIdentificacionBeneficiario = tipoIdentificacionBeneficiario;
    }
}
