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
 * @author Ernesto Cascante
 */
@Entity
@Table(name = "TMPFLUJOSAPROBACIONCABECERA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IBCabeceraFlujo.findAll", query = "SELECT a FROM IBCabeceraFlujo a"),
    @NamedQuery(name = "IBCabeceraFlujo.findAllByNumeroCliente", query = "SELECT a FROM IBCabeceraFlujo a WHERE a.numeroCliente = :numeroCliente AND a.consecutivoNumeroContrato > 0 ORDER BY a.consecutivoNumeroContrato ASC")
})
public class IBCabeceraFlujo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDFLUJOAPROBACIONCABECERA")
    private Long id;
    @Column(name = "CODEMPRESA")
    private int bancoCliente;
    @Column(name = "NUMCLIENTE")
    private int numeroCliente;
    @Column(name = "TIPONUMCONTRATO")
    private String tipoContrato;
    @Column(name = "NUMCONTRATO")
    private BigDecimal numeroContrato;
    @Column(name = "CONSECUTIVONUMCONTRATO")
    private int consecutivoNumeroContrato;
    @Column(name = "CONSECUTIVODETALLE")
    private int consecutivoDetalle;
    @Column(name = "NUMRANGOINICIAL")
    private BigDecimal montoInicial;
    @Column(name = "NUMRANGOFINAL")
    private BigDecimal montoFinal;
    @Column(name = "VALESTADOFLUJO")
    private String estadoFlujo;
    @Column(name = "TIPOPERACION")
    private String tipoOperacion;
    @Column(name = "OGNREGLA")
    private String origenRegla;
    @Column(name = "EXPVALIDADO")
    private String expresionValidado;
    @Column(name = "EXPUSUARIA")
    private String expresionUsuario;
    @Column(name = "NOMUSUARIO")
    private String nombreUsuario;

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

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public BigDecimal getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(BigDecimal numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public int getConsecutivoNumeroContrato() {
        return consecutivoNumeroContrato;
    }

    public void setConsecutivoNumeroContrato(int consecutivoNumeroContrato) {
        this.consecutivoNumeroContrato = consecutivoNumeroContrato;
    }

    public int getConsecutivoDetalle() {
        return consecutivoDetalle;
    }

    public void setConsecutivoDetalle(int consecutivoDetalle) {
        this.consecutivoDetalle = consecutivoDetalle;
    }

    public BigDecimal getMontoInicial() {
        return montoInicial;
    }

    public void setMontoInicial(BigDecimal montoInicial) {
        this.montoInicial = montoInicial;
    }

    public BigDecimal getMontoFinal() {
        return montoFinal;
    }

    public void setMontoFinal(BigDecimal montoFinal) {
        this.montoFinal = montoFinal;
    }

    public String getEstadoFlujo() {
        return estadoFlujo;
    }

    public void setEstadoFlujo(String estadoFlujo) {
        this.estadoFlujo = estadoFlujo;
    }

    public String getOrigenRegla() {
        return origenRegla;
    }

    public void setOrigenRegla(String origenRegla) {
        this.origenRegla = origenRegla;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getExpresionValidado() {
        return expresionValidado;
    }

    public void setExpresionValidado(String expresionValidado) {
        this.expresionValidado = expresionValidado;
    }

    public String getExpresionUsuario() {
        return expresionUsuario;
    }

    public void setExpresionUsuario(String expresionUsuario) {
        this.expresionUsuario = expresionUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
