/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aoropeza
 */
@Entity
@Table(name = "INVITACIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitaciones.findAll", query = "SELECT i FROM Invitaciones i"),
    @NamedQuery(name = "Invitaciones.findById", query = "SELECT i FROM Invitaciones i WHERE i.id = :id"),
    @NamedQuery(name = "Invitaciones.findByTipoIdentificacion", query = "SELECT i FROM Invitaciones i WHERE i.tipoIdentificacion = :tipoIdentificacion"),
    @NamedQuery(name = "Invitaciones.findByIdentificacion", query = "SELECT i FROM Invitaciones i WHERE i.identificacion = :identificacion"),
    @NamedQuery(name = "Invitaciones.findByIdentificacionUnicaEstado", query = "SELECT i FROM Invitaciones i WHERE i.identificacion = :identificacion  AND i.tipoIdentificacion = :tipoIdentificacion AND i.estado = :estado"),
    @NamedQuery(name = "Invitaciones.findByNombres", query = "SELECT i FROM Invitaciones i WHERE i.nombres = :nombres"),
    @NamedQuery(name = "Invitaciones.findByIdentificacionCliente", query = "SELECT i FROM Invitaciones i WHERE i.identificacion = :identificacion  AND i.tipoIdentificacion = :tipoIdentificacion AND i.clienteId = :clienteId"),
    @NamedQuery(name = "Invitaciones.findByApellidos", query = "SELECT i FROM Invitaciones i WHERE i.apellidos = :apellidos"),
    @NamedQuery(name = "Invitaciones.findByEmail", query = "SELECT i FROM Invitaciones i WHERE i.email = :email"),
    @NamedQuery(name = "Invitaciones.findByEstado", query = "SELECT i FROM Invitaciones i WHERE i.estado = :estado"),
    @NamedQuery(name = "Invitaciones.findByClienteEstado", query = "SELECT i FROM Invitaciones i WHERE i.clienteId = :clienteId AND i.estado = :estado"),
    @NamedQuery(name = "Invitaciones.findByClienteUsuario", query = "SELECT i FROM Invitaciones i WHERE i.clienteId = :clienteId AND i.usuarioId = :usuarioId"),
    @NamedQuery(name = "Invitaciones.findByClientePerfilEstado", query = "SELECT i FROM Invitaciones i WHERE i.clienteId = :clienteId AND i.perfilId = :perfilId AND i.estado = :estado"),
    @NamedQuery(name = "Invitaciones.findByAlias", query = "SELECT i FROM Invitaciones i WHERE i.alias = :alias"),
    @NamedQuery(name = "Invitaciones.findByCodigoBt", query = "SELECT i FROM Invitaciones i WHERE i.codigoBt = :codigoBt"),
    @NamedQuery(name = "Invitaciones.findByFechaHoraInvitacion", query = "SELECT i FROM Invitaciones i WHERE i.fechaHoraInvitacion = :fechaHoraInvitacion"),
    @NamedQuery(name = "Invitaciones.findByFechaHoraRecepcion", query = "SELECT i FROM Invitaciones i WHERE i.fechaHoraRecepcion = :fechaHoraRecepcion"),
    @NamedQuery(name = "Invitaciones.findByPerfil", query = "SELECT i FROM Invitaciones i WHERE i.perfilId = :perfilId"),
    @NamedQuery(name = "Invitaciones.findByPerfilEstado", query = "SELECT i FROM Invitaciones i WHERE i.perfilId = :perfilId AND i.estado = :estado"),
    @NamedQuery(name = "Invitaciones.findByTelefono", query = "SELECT i FROM Invitaciones i WHERE i.telefono = :telefono")})
public class Invitaciones implements Serializable {

    @Column(name = "FECHA_HORA_CODIGO_BT")
    @Temporal(TemporalType.DATE)
    private Date fechaHoraCodigoBt;
    @Column(name = "INTENTOS_FALLIDOS")
    private BigInteger intentosFallidos;
    @Column(name = "ORDEN_ESTADO")
    private BigInteger ordenEstado;
    @Size(max = 512)
    @Column(name = "MOTIVO_RECHAZO")
    private String motivoRechazo;
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "INVITACIONES_SEQ", sequenceName = "INVITACIONES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVITACIONES_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "APELLIDOS")
    private String apellidos;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private Character estado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ALIAS")
    private String alias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CODIGO_BT")
    private String codigoBt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_HORA_INVITACION")
    @Temporal(TemporalType.DATE)
    private Date fechaHoraInvitacion;
    @Column(name = "FECHA_HORA_RECEPCION")
    @Temporal(TemporalType.DATE)
    private Date fechaHoraRecepcion;
    @Size(max = 15)
    @Column(name = "TELEFONO", length = 15)
    private String telefono;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UsuariosNbl usuarioId;
    @JoinColumn(name = "PERFIL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private PerfilesNbl perfilId;
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Clientes clienteId;
    @Transient
    public static final char VALOR_ESTADO_ACTIVO = 'A';
    @Transient
    public static final char VALOR_ESTADO_CANCELADO = 'C';
    @Transient
    public static final char VALOR_ESTADO_RECHAZADO = 'F';
    @Transient
    public static final char VALOR_ESTADO_INACTIVO = 'I';
    @Transient
    public static final char VALOR_ESTADO_PENDIENTE = 'P';
    @Transient
    public static final char VALOR_ESTADO_ANULADO = 'N';
    @Transient
    public static final char VALOR_ESTADO_TERMINADA = 'T';
    @Transient
    public static final char VALOR_ESTADO_REGENERADA = 'R';

    @JoinColumn(name = "FK_IDPAISES", referencedColumnName = "ID")
    @ManyToOne
    private Paises paisId;

    public Invitaciones() {
    }

    public Invitaciones(Long id) {
        this.id = id;
    }

    public Invitaciones(Long id, String tipoIdentificacion, String identificacion, String nombres, String apellidos, String email, Character estado, String alias, String codigoBt, Date fechaHoraInvitacion) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.estado = estado;
        this.alias = alias;
        this.codigoBt = codigoBt;
        this.fechaHoraInvitacion = fechaHoraInvitacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCodigoBt() {
        return codigoBt;
    }

    public void setCodigoBt(String codigoBt) {
        this.codigoBt = codigoBt;
    }

    public Date getFechaHoraInvitacion() {
        return fechaHoraInvitacion;
    }

    public void setFechaHoraInvitacion(Date fechaHoraInvitacion) {
        this.fechaHoraInvitacion = fechaHoraInvitacion;
    }

    public Date getFechaHoraRecepcion() {
        return fechaHoraRecepcion;
    }

    public void setFechaHoraRecepcion(Date fechaHoraRecepcion) {
        this.fechaHoraRecepcion = fechaHoraRecepcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public UsuariosNbl getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UsuariosNbl usuarioId) {
        this.usuarioId = usuarioId;
    }

    public PerfilesNbl getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(PerfilesNbl perfilId) {
        this.perfilId = perfilId;
    }

    public Clientes getClienteId() {
        return clienteId;
    }

    public void setClienteId(Clientes clienteId) {
        this.clienteId = clienteId;
    }

    public Paises getPaisId() {
        return paisId;
    }

    public void setPaisId(Paises paisId) {
        this.paisId = paisId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Invitaciones)) {
            return false;
        }
        Invitaciones other = (Invitaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Invitaciones[ id=" + id + " ]";
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    /**
     * @return the fechaHoraCodigoBt
     */
    public Date getFechaHoraCodigoBt() {
        return fechaHoraCodigoBt;
    }

    /**
     * @param fechaHoraCodigoBt the fechaHoraCodigoBt to set
     */
    public void setFechaHoraCodigoBt(Date fechaHoraCodigoBt) {
        this.fechaHoraCodigoBt = fechaHoraCodigoBt;
    }

    /**
     * @return the intentosFallidos
     */
    public BigInteger getIntentosFallidos() {
        return intentosFallidos;
    }

    /**
     * @param intentosFallidos the intentosFallidos to set
     */
    public void setIntentosFallidos(BigInteger intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    /**
     * @return the ordenEstado
     */
    public BigInteger getOrdenEstado() {
        return ordenEstado;
    }

    /**
     * @param ordenEstado the ordenEstado to set
     */
    public void setOrdenEstado(BigInteger ordenEstado) {
        this.ordenEstado = ordenEstado;
    }

}
