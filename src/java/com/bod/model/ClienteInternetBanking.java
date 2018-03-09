package com.bod.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ernesto Cascante. Grupo Intec. Clase para el mapeo de los clientes
 * temporales de Internet Banking
 */
@Entity
@Table(name = "TMPCLIENTESIB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteInternetBanking.findAll", query = "SELECT a FROM ClienteInternetBanking a"),
    @NamedQuery(name = "ClienteInternetBanking.findAllByTipoIdAndIdentificacion", query = "SELECT a FROM ClienteInternetBanking a WHERE a.tipoIdentificacion = :tipoId AND TRIM(a.numeroIdentificacion) = :numeroIdentificacion"),
    @NamedQuery(name = "ClienteInternetBanking.findPendientesByTipoIdAndIdentificacion", query = "SELECT a FROM ClienteInternetBanking a WHERE (a.estadoMigracion = 'A' OR a.estadoMigracion = 'B') AND a.tipoIdentificacion = :tipoId AND TRIM(a.numeroIdentificacion) = :numeroIdentificacion"),
    @NamedQuery(name = "ClienteInternetBanking.findAprobadosByTipoIdAndIdentificacion", query = "SELECT a FROM ClienteInternetBanking a WHERE a.estadoMigracion = 'A' AND a.tipoIdentificacion = :tipoId AND TRIM(a.numeroIdentificacion) = :numeroIdentificacion"),
    @NamedQuery(name = "ClienteInternetBanking.findAllByNumeroCliente", query = "SELECT a FROM ClienteInternetBanking a WHERE a.numeroCliente = :clienteID"),
    @NamedQuery(name = "ClienteInternetBanking.findAllByNumeroClienteNombreUsuario", query = "SELECT a FROM ClienteInternetBanking a WHERE a.numeroCliente = :clienteID AND a.login = :login AND a.estadoMigracion = :estadoMigracion"),
    @NamedQuery(name = "ClienteInternetBanking.findAllByAutorizacionAndEstado", query = "SELECT a FROM ClienteInternetBanking a WHERE a.nivelAutorizacion = :nivelAutorizacion AND a.estadoMigracion= :estadoMigracion"),
    @NamedQuery(name = "ClienteInternetBanking.findByClienteIdentificacion", query = "SELECT a FROM ClienteInternetBanking a WHERE a.numeroCliente = :numeroCliente AND a.numeroIdentificacion = :numeroIdentificacion AND a.tipoIdentificacion = :tipoIdentificacion"),
    @NamedQuery(name = "ClienteInternetBanking.findAllByNumeroClienteAndAutorizacion", query = "SELECT a FROM ClienteInternetBanking a WHERE a.numeroCliente = :clienteID AND a.nivelAutorizacion= :nivelAutorizacion"),
    @NamedQuery(name = "ClienteInternetBanking.findAllByIdentificacionAndAutorizacion", query = "SELECT a FROM ClienteInternetBanking a WHERE a.tipoIdentificacion = :tipoID AND TRIM(a.numeroIdentificacion)= :numeroID AND  a.nivelAutorizacion= :nivelAutorizacion")
})
public class ClienteInternetBanking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDREGISTRO")
    private Long id;
    @Column(name = "NumCliente")
    private int numeroCliente;
    @Column(name = "NomUsuario")
    private String login;
    @Column(name = "CodTipoIdentificacion")
    private String tipoIdentificacion;
    @Column(name = "NumIdentificacion")
    private String numeroIdentificacion;
    @Column(name = "NomCliente")
    private String nombreCliente;
    @Column(name = "CodTipoCliente")
    private String tipoCliente;
    @Column(name = "ValEstadoRegistro")
    private String estadoRegistro;
    @Column(name = "CodNivelAutorizacion")
    private String nivelAutorizacion;
    @Column(name = "feccreacion")
    private int fechaCreacion;
    @Column(name = "DirCorreoElectronico")
    private String correoElectronico;
    @Column(name = "numTelefonoMovil")
    private String numTelefono;
    @Column(name = "ValUsuarioMarcado")
    private String valUsuarioMarcado;
    @Column(name = "codnacionalidad")
    private String nacionalidad;
    @Column(name = "estMigracion")
    private String estadoMigracion;
    @Column(name = "FecCambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;
    @Transient
    int habilitado = 1;
    @Transient
    private String usuarioMaster;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estado) {
        this.estadoRegistro = estado;
    }

    public String getNivelAutorizacion() {
        return nivelAutorizacion;
    }

    public void setNivelAutorizacion(String nivelAutorizacion) {
        this.nivelAutorizacion = nivelAutorizacion;
    }

    public int getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(int fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public String getValUsuarioMarcado() {
        return valUsuarioMarcado;
    }

    public void setValUsuarioMarcado(String valUsuarioMarcado) {
        this.valUsuarioMarcado = valUsuarioMarcado;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstadoMigracion() {
        return estadoMigracion;
    }

    public void setEstadoMigracion(String estadoMigracion) {
        this.estadoMigracion = estadoMigracion;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public boolean esClienteMaster() {
        return getNivelAutorizacion().equalsIgnoreCase("MA");
    }

    public boolean esClienteNatural() {
        return getTipoCliente().equalsIgnoreCase("2");
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public String getUsuarioMaster() {
        return usuarioMaster;
    }

    public void setUsuarioMaster(String usuarioMaster) {
        this.usuarioMaster = usuarioMaster;
    }

    public String generarCadenaImpresion() {
        StringBuilder str = new StringBuilder("");
        str.append("Login ").append(login);
        str.append(" Tipo Id ").append(tipoIdentificacion);
        str.append(" Id ").append(numeroIdentificacion);
        str.append(" Nacionalidad ").append(nacionalidad);
        str.append(" Habilitado ").append(habilitado);
        return str.toString();
    }

}
