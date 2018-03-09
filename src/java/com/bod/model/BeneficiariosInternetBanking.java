package com.bod.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author intec
 */
@Entity
@Table(name = "TMPBENEFICIARIOSIB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BeneficiariosInternetBanking.findAll", query = "SELECT a FROM BeneficiariosInternetBanking a"),
    @NamedQuery(name = "BeneficiariosInternetBanking.findAllByNumeroClienteWitoutPK", query = "SELECT a.numeroCliente,a.numeroTarjeta,a.nombreUsuario,a.codigoClaseTransferencia,a.numeroCuenta,a.bancoAsociado,a.nombreBeneficiario,a.idBeneficiario,a.nombreBancoAsociaciado,a.estadoCuenta,a.usuarioAdicion,a.cuenta,a.origenTarjeta,a.titularTarjeta,a.tipoPagoTarjeta,a.estadoTarjeta,a.tipoTarjeta,a.notificacion,a.correoElectronicoTarjeta,a.numInternoTarjeta FROM BeneficiariosInternetBanking a WHERE a.numeroCliente = :numeroCliente"),
    @NamedQuery(name = "BeneficiariosInternetBanking.findAllByNumeroCliente", query = "SELECT a FROM BeneficiariosInternetBanking a WHERE a.numeroCliente = :numeroCliente")
})
public class BeneficiariosInternetBanking implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private IBEmbeddedKey id;
    @Column(name = "BANCO")
    private int banco;

    @Column(name = "NUMCLIENTE")
    private int numeroCliente;

    @Column(name = "NUMTARBENEFICIARIO")
    private String numeroTarjeta;

    @Column(name = "NOMUSUARIO")
    private String nombreUsuario;

    @Column(name = "CODCLASETRANS")
    private String codigoClaseTransferencia;

    @Column(name = "CUENTABENEFICIARIO")
    private String numeroCuenta;

    @Column(name = "CODBANCOPRODUCTO")
    private String bancoAsociado;

    @Column(name = "NOMCLIENTE")
    private String nombreBeneficiario;

    @Column(name = "IDBENEFICIARIO")
    private int idBeneficiario;

    @Column(name = "NOMBANCOPRODUCTO")
    private String nombreBancoAsociaciado;

    @Column(name = "ESTADOCUENTA")
    private String estadoCuenta;
    
    @Column(name = "USUADICION")
    private String usuarioAdicion;
    
    @Column(name = "CUENTA")
    private int cuenta;
    
    @Column(name = "ORIGENTARJETA")
    private String origenTarjeta;
    
    @Column(name = "TITULARTARJETA")
    private String titularTarjeta;
    
    @Column(name = "TIPOPAGOTARJETA")
    private String tipoPagoTarjeta;
    
    @Column(name = "ESTADOTARJETA")
    private String estadoTarjeta;
    
    @Column(name = "TIPOTARJETA")
    private String tipoTarjeta;
    
    @Column(name = "NOTIFICACION")
    private String notificacion;
    
    @Column(name = "CORREOTARJETA")
    private String correoElectronicoTarjeta;
    
    @Column(name = "NUMINTERNOTARJETA")
    private String numInternoTarjeta;

    public IBEmbeddedKey getId() {
        return id;
    }

    public void setId(IBEmbeddedKey id) {
        this.id = id;
    }

    public int getBanco() {
        return banco;
    }

    public void setBanco(int banco) {
        this.banco = banco;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCodigoClaseTransferencia() {
        return codigoClaseTransferencia;
    }

    public void setCodigoClaseTransferencia(String codigoClaseTransferencia) {
        this.codigoClaseTransferencia = codigoClaseTransferencia;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getBancoAsociado() {
        return bancoAsociado;
    }

    public void setBancoAsociado(String bancoAsociado) {
        this.bancoAsociado = bancoAsociado;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    public String getNombreBancoAsociaciado() {
        return nombreBancoAsociaciado;
    }

    public void setNombreBancoAsociaciado(String nombreBancoAsociaciado) {
        this.nombreBancoAsociaciado = nombreBancoAsociaciado;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    public String getUsuarioAdicion() {
        return usuarioAdicion;
    }

    public void setUsuarioAdicion(String usuarioAdicion) {
        this.usuarioAdicion = usuarioAdicion;
    }

    public int getCuenta() {
        return cuenta;
    }

    public void setCuenta(int cuenta) {
        this.cuenta = cuenta;
    }

    public String getOrigenTarjeta() {
        return origenTarjeta;
    }

    public void setOrigenTarjeta(String origenTarjeta) {
        this.origenTarjeta = origenTarjeta;
    }

    public String getTitularTarjeta() {
        return titularTarjeta;
    }

    public void setTitularTarjeta(String titularTarjeta) {
        this.titularTarjeta = titularTarjeta;
    }

    public String getTipoPagoTarjeta() {
        return tipoPagoTarjeta;
    }

    public void setTipoPagoTarjeta(String tipoPagoTarjeta) {
        this.tipoPagoTarjeta = tipoPagoTarjeta;
    }

    public String getEstadoTarjeta() {
        return estadoTarjeta;
    }

    public void setEstadoTarjeta(String estadoTarjeta) {
        this.estadoTarjeta = estadoTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public String getCorreoElectronicoTarjeta() {
        return correoElectronicoTarjeta;
    }

    public void setCorreoElectronicoTarjeta(String correoElectronicoTarjeta) {
        this.correoElectronicoTarjeta = correoElectronicoTarjeta;
    }

    public String getNumInternoTarjeta() {
        return numInternoTarjeta;
    }

    public void setNumInternoTarjeta(String numInternoTarjeta) {
        this.numInternoTarjeta = numInternoTarjeta;
    }
    
   
    
}
