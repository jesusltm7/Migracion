package com.bod.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "PERFILES_NBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilesNbl.findByNombrePerfilBase", query = "SELECT p FROM PerfilesNbl p WHERE p.perfilBaseId = :perfilBaseId AND p.nombre = :nombre"),
    @NamedQuery(name = "PerfilesNbl.findByNombrePerfilBaseEstado", query = "SELECT p FROM PerfilesNbl p WHERE p.perfilBaseId = :perfilBaseId AND UPPER(p.nombre) = :nombre AND p.estado = :estado"),
    @NamedQuery(name = "PerfilesNbl.findByPerfilBaseTipoPerfilNbl", query = "SELECT p FROM PerfilesNbl p WHERE p.perfilBaseId = :perfilBaseId AND p.tipoPerfilesNblId = :tipoPerfilesNblId"),
    @NamedQuery(name = "PerfilesNbl.findByPerfilBase", query = "SELECT p FROM PerfilesNbl p WHERE p.perfilBaseId = :perfilBaseId"),
    @NamedQuery(name = "PerfilesNbl.findAll", query = "SELECT p FROM PerfilesNbl p"),
    @NamedQuery(name = "PerfilesNbl.findByTipoPerfilNbl", query = "SELECT p FROM PerfilesNbl p WHERE p.tipoPerfilesNblId = :tipoPerfilesNblId"),
    @NamedQuery(name = "PerfilesNbl.findById", query = "SELECT p FROM PerfilesNbl p WHERE p.id = :id"),
    @NamedQuery(name = "PerfilesNbl.findByRol", query = "SELECT p FROM PerfilesNbl p WHERE p.codigoRol = p.codigoRol"),
    @NamedQuery(name = "PerfilesNbl.findByNombre", query = "SELECT p FROM PerfilesNbl p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PerfilesNbl.findByNombreEstado", query = "SELECT p FROM PerfilesNbl p WHERE p.nombre = :nombre AND p.estado = :estado"),
    @NamedQuery(name = "PerfilesNbl.findByClientePerfilBaseEstado", query = "SELECT p FROM PerfilesNbl p WHERE p.clientesId = :clientesId AND p.perfilBaseId = :perfilBaseId AND p.estado = :estado"),
    @NamedQuery(name = "PerfilesNbl.findByPerfilBaseEstadoTipo", query = "SELECT p FROM PerfilesNbl p WHERE p.perfilBaseId = :perfilBaseId AND p.tipoPerfilesNblId = :tipoPerfilesNblId AND p.estado = :estado"),
    @NamedQuery(name = "PerfilesNbl.findByPerfilBaseEstado", query = "SELECT p FROM PerfilesNbl p WHERE p.perfilBaseId = :perfilBaseId AND p.estado = :estado"),
    @NamedQuery(name = "PerfilesNbl.findByClienteTipoEstado", query = "SELECT p FROM PerfilesNbl p WHERE p.clientesId = :clientesId AND p.tipoPerfilesNblId = :tipoPerfilesNblId AND p.estado = :estado"),
    @NamedQuery(name = "PerfilesNbl.findByDescripcion", query = "SELECT p FROM PerfilesNbl p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PerfilesNbl.findByClientePerfilBase", query = "SELECT p FROM PerfilesNbl p WHERE p.clientesId = :clientesId AND p.perfilBaseId = :perfilBaseId"),
    @NamedQuery(name = "PerfilesNbl.findByCliente", query = "SELECT p FROM PerfilesNbl p WHERE p.clientesId = :clientesId AND p.estado = 'A'")})
public class PerfilesNbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "PERFILES_NBL_SEQ", sequenceName = "PERFILES_NBL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERFILES_NBL_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 80)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 2000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @NotNull
    @Column(name = "ESTADO")
    private Character estado;
    @JoinTable(name = "PERFILESNBL_HAS_OPERACIONESNBL", joinColumns = {
        @JoinColumn(name = "PERFILES_NBL_ID", referencedColumnName = "ID")
    }, inverseJoinColumns = {
        @JoinColumn(name = "OPERACIONES_NBL_ID", referencedColumnName = "ID")
    })
    @ManyToMany
    private Collection<OperacionesNbl> operacionesNblCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdperfil")
    private Collection<Mstreglasperfil> mstreglasperfilCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfilesNbl")
    private Collection<PerfilesNblHasParametros> perfilesNblHasParametrosCollection;
    @JoinColumn(name = "ESTILOS_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Estilos estilosId;
    @JoinColumn(name = "CLIENTES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Clientes clientesId;
    @JoinColumn(name = "AMBIENTES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Ambientes ambientesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfilId")
    private Collection<Invitaciones> invitacionesCollection;
    @JoinColumn(name = "TIPO_PERFILES_NBL_ID", referencedColumnName = "ID")
    @ManyToOne
    private TipoPerfilesNbl tipoPerfilesNblId;
    @JoinColumn(name = "CANAL_ID", referencedColumnName = "ID")
    @ManyToOne
    private Canales canalId;

    @JoinColumn(name = "IDIOMAS_ID", referencedColumnName = "ID")
    @ManyToOne
    private Idiomas idiomasId;

    @OneToMany(mappedBy = "perfilBaseId")
    private Collection<PerfilesNbl> perfilesNblCollection;
    @JoinColumn(name = "PERFIL_BASE_ID", referencedColumnName = "ID")
    @ManyToOne
    private PerfilesNbl perfilBaseId;

    @Size(max = 2)
    @Column(name = "CODROL")
    private String codigoRol;

    @Transient
    public static final char VALOR_ESTADO_ACTIVO = 'A';

    @Transient
    public static final char VALOR_ESTADO_INACTIVO = 'I';

    public PerfilesNbl() {
    }

    public PerfilesNbl(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(String codigoRol) {
        this.codigoRol = codigoRol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Mstreglasperfil> getMstreglasperfilCollection() {
        return mstreglasperfilCollection;
    }

    public void setMstreglasperfilCollection(Collection<Mstreglasperfil> mstreglasperfilCollection) {
        this.mstreglasperfilCollection = mstreglasperfilCollection;
    }

    @XmlTransient
    public Collection<OperacionesNbl> getOperacionesNblCollection() {
        return operacionesNblCollection;
    }

    public void setOperacionesNblCollection(Collection<OperacionesNbl> operacionesNblCollection) {
        this.operacionesNblCollection = operacionesNblCollection;
    }

    @XmlTransient
    public Collection<PerfilesNblHasParametros> getPerfilesNblHasParametrosCollection() {
        return perfilesNblHasParametrosCollection;
    }

    public void setPerfilesNblHasParametrosCollection(Collection<PerfilesNblHasParametros> perfilesNblHasParametrosCollection) {
        this.perfilesNblHasParametrosCollection = perfilesNblHasParametrosCollection;
    }

    public Estilos getEstilosId() {
        return estilosId;
    }

    public void setEstilosId(Estilos estilosId) {
        this.estilosId = estilosId;
    }

    public Clientes getClientesId() {
        return clientesId;
    }

    public void setClientesId(Clientes clientesId) {
        this.clientesId = clientesId;
    }

    public Ambientes getAmbientesId() {
        return ambientesId;
    }

    public void setAmbientesId(Ambientes ambientesId) {
        this.ambientesId = ambientesId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PerfilesNbl)) {
            return false;
        }
        PerfilesNbl other = (PerfilesNbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.PerfilesNbl[ id=" + id + " ]";
    }

    public Collection<Invitaciones> getInvitacionesCollection() {
        return invitacionesCollection;
    }

    public void setInvitacionesCollection(Collection<Invitaciones> invitacionesCollection) {
        this.invitacionesCollection = invitacionesCollection;
    }

    public TipoPerfilesNbl getTipoPerfilesNblId() {
        return tipoPerfilesNblId;
    }

    public void setTipoPerfilesNblId(TipoPerfilesNbl tipoPerfilesNblId) {
        this.tipoPerfilesNblId = tipoPerfilesNblId;
    }

    public Canales getCanalId() {
        return canalId;
    }

    public void setCanalId(Canales canalId) {
        this.canalId = canalId;
    }

    public Idiomas getIdiomasId() {
        return idiomasId;
    }

    public void setIdiomasId(Idiomas idiomasId) {
        this.idiomasId = idiomasId;
    }

    public Collection<PerfilesNbl> getPerfilesNblCollection() {
        return perfilesNblCollection;
    }

    public void setPerfilesNblCollection(Collection<PerfilesNbl> perfilesNblCollection) {
        this.perfilesNblCollection = perfilesNblCollection;
    }

    public PerfilesNbl getPerfilBaseId() {
        return perfilBaseId;
    }

    public void setPerfilBaseId(PerfilesNbl perfilBaseId) {
        this.perfilBaseId = perfilBaseId;
    }

}
