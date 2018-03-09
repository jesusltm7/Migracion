/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
        @Table(name = "USUARIOS_NBL")
        @XmlRootElement
        @NamedQueries({
            @NamedQuery(name = "UsuariosNbl.findAll", query = "SELECT u FROM UsuariosNbl u"),
            @NamedQuery(name = "UsuariosNbl.findById", query = "SELECT u FROM UsuariosNbl u WHERE u.id = :id"),
            @NamedQuery(name = "UsuariosNbl.findByIdentificacion", query = "SELECT u FROM UsuariosNbl u WHERE u.identificacion = :identificacion"),
            @NamedQuery(name = "UsuariosNbl.findByNombre", query = "SELECT u FROM UsuariosNbl u WHERE u.nombre = :nombre"),
            @NamedQuery(name = "UsuariosNbl.findByLogin", query = "SELECT u FROM UsuariosNbl u WHERE u.login = :login"),
            @NamedQuery(name = "UsuariosNbl.findByUltimoLogin", query = "SELECT u FROM UsuariosNbl u WHERE u.ultimoLogin = :ultimoLogin"),
            @NamedQuery(name = "UsuariosNbl.findByEstatus", query = "SELECT u FROM UsuariosNbl u WHERE u.estatus = :estatus"),
            @NamedQuery(name = "UsuariosNbl.findByFechaAfiliacion", query = "SELECT u FROM UsuariosNbl u WHERE u.fechaAfiliacion = :fechaAfiliacion"),
            @NamedQuery(name = "UsuariosNbl.findLoginByList", query = "SELECT  LOWER(u.login) FROM UsuariosNbl u WHERE  LOWER(u.login) IN :login"),
            @NamedQuery(name = "UsuariosNbl.findLoginByValue", query = "SELECT  LOWER(u.login) FROM UsuariosNbl u WHERE  LOWER(u.login) = :value")})

public class UsuariosNbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "USUARIOS_NBL_SEQ", sequenceName = "USUARIOS_NBL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIOS_NBL_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "ULTIMO_LOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoLogin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS")
    private Character estatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_AFILIACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAfiliacion;
    @JoinColumn(name = "IDIOMA_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Idiomas idiomaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosNbl")
    private Collection<ClientesHasUsuariosNbl> clientesHasUsuariosNblCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosNbl")
    private Collection<ModulosUsuariosNbl> modulosUsuariosNblCollection;
    @JoinColumn(name = "PAIS_ID", referencedColumnName = "ID")
    @ManyToOne
    private Paises paisId;
    @JoinColumn(name = "BANCO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Bancos bancoId;
    @Basic(optional = false)
    @Size(min = 1, max = 60)
    @Column(name = "SHAREDKEY")
    private String sharedkey;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private Collection<Invitaciones> invitacionesCollection;
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioNblId")
    private Collection<DispositivosUsuariosNbl> dispositivosUsuariosNblCollection;

    @Basic(optional = false)
    @Size(min = 1, max = 60)
    @Column(name = "AZCRYPTOR_ID")
    private String azcriptor_id;

    @JoinColumn(name = "CANALES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Canales canalesId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "SESSIONEQUIPOFRECUENTE", nullable = false)
    private Character sessionEquipoFrecuente;
    
    
    @Column(name = "PERFILDEF")
    private Long perfilDef;
    

    public UsuariosNbl() {
    }

    public UsuariosNbl(Long id) {
        this.id = id;
    }

    public UsuariosNbl(Long id, String identificacion, String nombre, String login, Character estatus, Date fechaAfiliacion) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.login = login;
        this.estatus = estatus;
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public Collection<DispositivosUsuariosNbl> getDispositivosUsuariosNblCollection() {
        return dispositivosUsuariosNblCollection;
    }

    public void setDispositivosUsuariosNblCollection(Collection<DispositivosUsuariosNbl> dispositivosUsuariosNblCollection) {
        this.dispositivosUsuariosNblCollection = dispositivosUsuariosNblCollection;
    }

    public Collection<Invitaciones> getInvitacionesCollection() {
        return invitacionesCollection;
    }

    public void setInvitacionesCollection(Collection<Invitaciones> invitacionesCollection) {
        this.invitacionesCollection = invitacionesCollection;
    }

    public Canales getCanalesId() {
        return canalesId;
    }

    public void setCanalesId(Canales canalesId) {
        this.canalesId = canalesId;
    }

    public Bancos getBancoId() {
        return bancoId;
    }

    public void setBancoId(Bancos bancoId) {
        this.bancoId = bancoId;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getSharedkey() {
        return sharedkey;
    }

    public void setSharedkey(String sharedkey) {
        this.sharedkey = sharedkey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    public Date getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(Date fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public Idiomas getIdiomaId() {
        return idiomaId;
    }

    public void setIdiomaId(Idiomas idiomaId) {
        this.idiomaId = idiomaId;
    }

    public Collection<ClientesHasUsuariosNbl> getClientesHasUsuariosNblCollection() {
        return clientesHasUsuariosNblCollection;
    }
    
    public void remove(ClientesHasUsuariosNbl objeto){
       
        clientesHasUsuariosNblCollection.remove(objeto);
   
    }

    public void setClientesHasUsuariosNblCollection(Collection<ClientesHasUsuariosNbl> clientesHasUsuariosNblCollection) {
        this.clientesHasUsuariosNblCollection = clientesHasUsuariosNblCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsuariosNbl)) {
            return false;
        }
        UsuariosNbl other = (UsuariosNbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.UsuariosNbl[ id=" + id + " ]";
    }

    public Collection<ModulosUsuariosNbl> getModulosUsuariosNblCollection() {
        return modulosUsuariosNblCollection;
    }

    public void setModulosUsuariosNblCollection(Collection<ModulosUsuariosNbl> modulosUsuariosNblCollection) {
        this.modulosUsuariosNblCollection = modulosUsuariosNblCollection;
    }

    public Paises getPaisId() {
        return paisId;
    }

    public void setPaisId(Paises paisId) {
        this.paisId = paisId;
    }

    public String getAzcriptor_id() {
        return azcriptor_id;
    }

    public void setAzcriptor_id(String azcriptor_id) {
        this.azcriptor_id = azcriptor_id;
    }

    public Long getPerfilDef() {
        return perfilDef;
    }

    public void setPerfilDef(Long perfilDef) {
        this.perfilDef = perfilDef;
    }
    
    

    /**
     * Busca el cliente cuya identificación coincide con el usuario
     *
     * @return ClientesHasUsuariosNbl
     */
    public ClientesHasUsuariosNbl getClientePropio() {
        final Collection<ClientesHasUsuariosNbl> cilentes = this.getClientesHasUsuariosNblCollection();

        for (ClientesHasUsuariosNbl i : cilentes) {
            if (Objects.equals(i.getClientes().getTipoIdentificacion(), this.getTipoIdentificacion()) && Objects.equals(i.getClientes().getIdentificacion(), this.getIdentificacion())) {
                return i;
            }
        }

        return null;
    }

    /**
     * @return the sessionEquipoFrecuente
     */
    public Character getSessionEquipoFrecuente() {
        return sessionEquipoFrecuente;
    }

    /**
     * @param sessionEquipoFrecuente the sessionEquipoFrecuente to set
     */
    public void setSessionEquipoFrecuente(Character sessionEquipoFrecuente) {
        this.sessionEquipoFrecuente = sessionEquipoFrecuente;
    }

}
