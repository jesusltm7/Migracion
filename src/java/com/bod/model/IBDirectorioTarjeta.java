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
 * @author Ernesto Cascante Clase que mapea el directorio de tarjetas de
 * internet Banking a incorporar en NBL.
 */
@Entity
@Table(name = "TMPE2TARJE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IBDirectorioTarjeta.findAll", query = "SELECT a FROM IBDirectorioTarjeta a"),
    @NamedQuery(name = "IBDirectorioTarjeta.findAllByNumeroCliente", query = "SELECT a FROM IBDirectorioTarjeta a WHERE a.numeroCliente = :numeroCliente"),
    @NamedQuery(name = "IBDirectorioTarjeta.obtenerRegistrosMigrar", query = "SELECT a FROM IBDirectorioTarjeta a WHERE a.numeroCliente = :numeroCliente")
})
public class IBDirectorioTarjeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TMPE2TARJE")
    private Long id;
    @Column(name = "E2EMPR")
    private int bancoCliente;
    @Column(name = "E2CUSC")
    private int numeroCliente;
    @Column(name = "E2USM2")
    private String nombreUsuario;
    @Column(name = "E2CLTR")
    private int tipoTransaccion;
    @Column(name = "E2TARJ")
    private String tarjetaBeneficiario;
    @Column(name = "E2ESSO")
    private String estado;
    @Column(name = "E2NOMC")
    private String nombreBeneficiario;
    @Column(name = "E2IDEN")
    private BigDecimal identificacionBeneficiario;
    @Column(name = "E2BANC")
    private String codigoBancoBeneficiario;
    @Column(name = "E2PAIS")
    private String nombrePais;
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

    public String getTarjetaBeneficiario() {
        return tarjetaBeneficiario;
    }

    public void setTarjetaBeneficiario(String tarjetaBeneficiario) {
        this.tarjetaBeneficiario = tarjetaBeneficiario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getCodigoBancoBeneficiario() {
        return codigoBancoBeneficiario;
    }

    public void setCodigoBancoBeneficiario(String codigoBancoBeneficiario) {
        this.codigoBancoBeneficiario = codigoBancoBeneficiario;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public String getTipoIdentificacionBeneficiario() {
        return tipoIdentificacionBeneficiario;
    }

    public void setTipoIdentificacionBeneficiario(String tipoIdentificacionBeneficiario) {
        this.tipoIdentificacionBeneficiario = tipoIdentificacionBeneficiario;
    }

}
