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
@Table(name = "TMP_E2USCTF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IBPerfilCuentas.findAll", query = "SELECT a FROM IBPerfilCuentas a"),
    @NamedQuery(name = "IBPerfilCuentas.findAllByNumeroCliente", query = "SELECT a FROM IBPerfilCuentas a WHERE a.numeroCliente = :numeroCliente"),
    @NamedQuery(name = "IBPerfilCuentas.obtenerRegistrosMigrar", query = "SELECT a FROM IBPerfilCuentas a WHERE a.numeroCliente=:numeroCliente AND a.usuario=:usuario"),
    @NamedQuery(name = "IBPerfilCuentas.cargarPermisosProducto", query = "SELECT a FROM IBPerfilCuentas a WHERE a.numeroCliente=:numeroCliente AND a.usuario=:usuario AND a.numeroCuenta=:numeroProducto")
})
public class IBPerfilCuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_E2USCTF")
    private Long id;
    @Column(name = "E2EMPR")
    private int codigoEmpresa;
    @Column(name = "E2CUSC")
    private int numeroCliente;
    @Column(name = "E2USM2")
    private String usuario;
    @Column(name = "E2MACC")
    private BigDecimal numeroCuenta;
    @Column(name = "E2MODE")
    private BigDecimal montoMaximo;
    @Column(name = "E2BUDB")
    private String debitos;
    @Column(name = "E2CRED")
    private char creditoCuenta;
    @Column(name = "E2BUSA")
    private char nivelSaldos;
    @Column(name = "E2BUMO")
    private char nivelDeMovimiento;
    @Column(name = "E2BUAU")
    private char nivelDeAuditoria;
    @Column(name = "E2BUAR")
    private char nivelDeArchivos;
    @Column(name = "E2BUCH")
    private char chequera;
    @Column(name = "E2BUDI")
    private char divisas;
    @Column(name = "E2BUNO")
    private char nomina;
    @Column(name = "E2BUOP")
    private char ordenesDePago;
    @Column(name = "E2FEAD")
    private int fechaAdicion;
    @Column(name = "E2USAD")
    private String usuarioAdicion;
    @Column(name = "E2HOAD")
    private int horaAdicion;
    @Column(name = "E2BUOT")
    private char desconocidoUno;
    @Column(name = "E2BUCG")
    private char desconocidoDos;
    @Column(name = "E2BUIN")
    private char desconocidoTres;
    @Column(name = "E2BUPA")
    private char desconocidoCuatro;
    @Column(name = "E2BU01")
    private char desconocidoCinco;
    @Column(name = "E2BU02")
    private char desconocidoSeis;
    @Column(name = "E2BU03")
    private char desconocidoSiente;
    @Column(name = "E2BU04")
    private char desconocidoOcho;
    @Column(name = "E2BU05")
    private char desconocidoNueve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(BigDecimal numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getDebitos() {
        return debitos;
    }

    public void setDebitos(String debitos) {
        this.debitos = debitos;
    }

    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public char getCreditoCuenta() {
        return creditoCuenta;
    }

    public void setCreditoCuenta(char creditoCuenta) {
        this.creditoCuenta = creditoCuenta;
    }

    public char getNivelSaldos() {
        return nivelSaldos;
    }

    public void setNivelSaldos(char nivelSaldos) {
        this.nivelSaldos = nivelSaldos;
    }

    public char getNivelDeMovimiento() {
        return nivelDeMovimiento;
    }

    public void setNivelDeMovimiento(char nivelDeMovimiento) {
        this.nivelDeMovimiento = nivelDeMovimiento;
    }

    public char getNivelDeAuditoria() {
        return nivelDeAuditoria;
    }

    public void setNivelDeAuditoria(char nivelDeAuditoria) {
        this.nivelDeAuditoria = nivelDeAuditoria;
    }

    public char getNivelDeArchivos() {
        return nivelDeArchivos;
    }

    public void setNivelDeArchivos(char nivelDeArchivos) {
        this.nivelDeArchivos = nivelDeArchivos;
    }

    public char getChequera() {
        return chequera;
    }

    public void setChequera(char chequera) {
        this.chequera = chequera;
    }

    public char getDivisas() {
        return divisas;
    }

    public void setDivisas(char divisas) {
        this.divisas = divisas;
    }

    public char getNomina() {
        return nomina;
    }

    public void setNomina(char nomina) {
        this.nomina = nomina;
    }

    public char getOrdenesDePago() {
        return ordenesDePago;
    }

    public void setOrdenesDePago(char ordenesDePago) {
        this.ordenesDePago = ordenesDePago;
    }

    public int getFechaAdicion() {
        return fechaAdicion;
    }

    public void setFechaAdicion(int fechaAdicion) {
        this.fechaAdicion = fechaAdicion;
    }

    public String getUsuarioAdicion() {
        return usuarioAdicion;
    }

    public void setUsuarioAdicion(String usuarioAdicion) {
        this.usuarioAdicion = usuarioAdicion;
    }

    public int getHoraAdicion() {
        return horaAdicion;
    }

    public void setHoraAdicion(int horaAdicion) {
        this.horaAdicion = horaAdicion;
    }

    public char getDesconocidoUno() {
        return desconocidoUno;
    }

    public void setDesconocidoUno(char desconocidoUno) {
        this.desconocidoUno = desconocidoUno;
    }

    public char getDesconocidoDos() {
        return desconocidoDos;
    }

    public void setDesconocidoDos(char desconocidoDos) {
        this.desconocidoDos = desconocidoDos;
    }

    public char getDesconocidoTres() {
        return desconocidoTres;
    }

    public void setDesconocidoTres(char desconocidoTres) {
        this.desconocidoTres = desconocidoTres;
    }

    public char getDesconocidoCuatro() {
        return desconocidoCuatro;
    }

    public void setDesconocidoCuatro(char desconocidoCuatro) {
        this.desconocidoCuatro = desconocidoCuatro;
    }

    public char getDesconocidoCinco() {
        return desconocidoCinco;
    }

    public void setDesconocidoCinco(char desconocidoCinco) {
        this.desconocidoCinco = desconocidoCinco;
    }

    public char getDesconocidoSeis() {
        return desconocidoSeis;
    }

    public void setDesconocidoSeis(char desconocidoSeis) {
        this.desconocidoSeis = desconocidoSeis;
    }

    public char getDesconocidoSiente() {
        return desconocidoSiente;
    }

    public void setDesconocidoSiente(char desconocidoSiente) {
        this.desconocidoSiente = desconocidoSiente;
    }

    public char getDesconocidoOcho() {
        return desconocidoOcho;
    }

    public void setDesconocidoOcho(char desconocidoOcho) {
        this.desconocidoOcho = desconocidoOcho;
    }

    public char getDesconocidoNueve() {
        return desconocidoNueve;
    }

    public void setDesconocidoNueve(char desconocidoNueve) {
        this.desconocidoNueve = desconocidoNueve;
    }

}
