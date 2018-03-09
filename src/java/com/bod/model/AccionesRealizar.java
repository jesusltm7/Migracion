package com.bod.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "ACCIONES_REALIZAR")
@XmlRootElement
@NamedQueries({
    //USUARIO_NBL_ID
    
    @NamedQuery(name = "AccionesRealizar.findAll", query = "SELECT a FROM AccionesRealizar a"),
    @NamedQuery(name = "AccionesRealizar.findById", query = "SELECT a FROM AccionesRealizar a WHERE a.id = :id"),
    @NamedQuery(name = "AccionesRealizar.findByUsuarioNblId", query = "SELECT a FROM AccionesRealizar a WHERE a.usuarioNblId = :usuarioNblId order by a.fechaAparicion desc"),
    @NamedQuery(name = "AccionesRealizar.findByDescripcion", query = "SELECT a FROM AccionesRealizar a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AccionesRealizar.findByNivel", query = "SELECT a FROM AccionesRealizar a WHERE a.nivel = :nivel"),
    @NamedQuery(name = "AccionesRealizar.findByEstatus", query = "SELECT a FROM AccionesRealizar a WHERE a.estatus = :estatus"),
    @NamedQuery(name = "AccionesRealizar.findByFechaRegistro", query = "SELECT a FROM AccionesRealizar a WHERE a.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "AccionesRealizar.findByFechaAparicion", query = "SELECT a FROM AccionesRealizar a WHERE a.fechaAparicion = :fechaAparicion"),
    @NamedQuery(name = "AccionesRealizar.findByNoLeido", query = "SELECT a FROM AccionesRealizar a WHERE a.flag_lectura = :flag_lectura and a.usuarioNblId = :usuarioNblId order by a.fechaAparicion desc"),
    @NamedQuery(name = "AccionesRealizar.findByFiltro", query = "SELECT a FROM AccionesRealizar a WHERE a.descripcion LIKE :descripcionParam or a.titulo LIKE :tituloParam and a.usuarioNblId = :usuarioNblIdParam order by a.fechaAparicion desc"),
    @NamedQuery(name = "AccionesRealizar.countByFiltro", query = "SELECT COUNT(a.id) conteo FROM AccionesRealizar a WHERE a.descripcion LIKE :descripcionParam or a.titulo LIKE :tituloParam and a.usuarioNblId = :usuarioNblIdParam")})
public class AccionesRealizar implements Serializable {
    @JoinTable(name = "DETACCIONESREALIZARUSUARIOSNBL", joinColumns = {
        @JoinColumn(name = "FK_IDACCIONESREALIZAR", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "FK_IDUSUARIOSNBL", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<UsuariosNbl> usuariosNblCollection;
    @JoinColumn(name = "USUARIO_NBL_ID", referencedColumnName = "ID")
    @ManyToOne
    private UsuariosNbl usuarioNblId;
    @JoinColumn(name = "PERFIL_NBL_ID", referencedColumnName = "ID")
    @ManyToOne
    private PerfilesNbl perfilNblId;
    @JoinColumn(name = "IDIOMAS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Idiomas idiomasId;
    @JoinColumn(name = "AMBIENTE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Ambientes ambienteId;
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "ACCION_REALIZAR_SEQ", sequenceName = "ACCION_REALIZAR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCION_REALIZAR_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "NIVEL")
    private BigDecimal nivel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS")
    private Character estatus;
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "FECHA_APARICION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAparicion;
    
    @Column (name = "TITULO")
    private String titulo;
    
    @Column (name = "URL_DESTINO")
    private String url_destino;
    
    @Column (name = "FLAG_LECTURA ")
    private char flag_lectura;
    
    @JoinColumn(name = "OPERACION_REQUERIDA", referencedColumnName = "ID")
    @ManyToOne
    private OperacionesNbl operacionRequerida;
    
     @JoinColumn(name = "OPERACION_SOLICITADA", referencedColumnName = "ID")
    @ManyToOne
    private OperacionesNbl operacionSolicitada;
        
    public AccionesRealizar() {
    }

    public AccionesRealizar(Long id) {
        this.id = id;
    }

    public AccionesRealizar(Long id, String descripcion, BigDecimal nivel, Character estatus) {
        this.id = id;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.estatus = estatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getNivel() {
        return nivel;
    }

    public void setNivel(BigDecimal nivel) {
        this.nivel = nivel;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaAparicion() {
        return fechaAparicion;
    }

    public void setFechaAparicion(Date fechaAparicion) {
        this.fechaAparicion = fechaAparicion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl_destino() {
        return url_destino;
    }

    public void setUrl_destino(String url_destino) {
        this.url_destino = url_destino;
    }

    public char getFlag_lectura() {
        return flag_lectura;
    }

    public void setFlag_lectura(char flag_lectura) {
        this.flag_lectura = flag_lectura;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionesRealizar)) {
            return false;
        }
        AccionesRealizar other = (AccionesRealizar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.AccionesRealizar[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<UsuariosNbl> getUsuariosNblCollection() {
        return usuariosNblCollection;
    }

    public void setUsuariosNblCollection(Collection<UsuariosNbl> usuariosNblCollection) {
        this.usuariosNblCollection = usuariosNblCollection;
    }

    public UsuariosNbl getUsuarioNblId() {
        return usuarioNblId;
    }

    public void setUsuarioNblId(UsuariosNbl usuarioNblId) {
        this.usuarioNblId = usuarioNblId;
    }

    public PerfilesNbl getPerfilNblId() {
        return perfilNblId;
    }

    public void setPerfilNblId(PerfilesNbl perfilNblId) {
        this.perfilNblId = perfilNblId;
    }

    public Idiomas getIdiomasId() {
        return idiomasId;
    }

    public void setIdiomasId(Idiomas idiomasId) {
        this.idiomasId = idiomasId;
    }

    public Ambientes getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Ambientes ambienteId) {
        this.ambienteId = ambienteId;
    }

    public OperacionesNbl getOperacionRequerida() {
        return operacionRequerida;
    }

    public void setOperacionRequerida(OperacionesNbl operacionRequerida) {
        this.operacionRequerida = operacionRequerida;
    }

    public OperacionesNbl getOperacionSolicitada() {
        return operacionSolicitada;
    }

    public void setOperacionSolicitada(OperacionesNbl operacionSolicitada) {
        this.operacionSolicitada = operacionSolicitada;
    }
    
    
    
}
