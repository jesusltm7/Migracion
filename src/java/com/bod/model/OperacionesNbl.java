
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "OPERACIONES_NBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperacionesNbl.findAll", query = "SELECT o FROM OperacionesNbl o"),
    @NamedQuery(name = "OperacionesNbl.findByBase", query = "SELECT o FROM OperacionesNbl o WHERE o.base = :base"),
    @NamedQuery(name = "OperacionesNbl.findAllByUrlSmall", query = "SELECT o  FROM OperacionesNbl o WHERE o.url_small IS NOT NULL and LOCATE('index_small.xhtml',o.url_small)>0 ORDER BY o.nombre ASC"),
    @NamedQuery(name = "OperacionesNbl.findAllOrderByNombre", query = "SELECT o FROM OperacionesNbl o ORDER BY o.nombre ASC"),
    @NamedQuery(name = "OperacionesNbl.findAllOrderByNombreActivas", query = "SELECT o FROM OperacionesNbl o WHERE o.estatus = 'A' ORDER BY o.nombre ASC"),
    @NamedQuery(name = "OperacionesNbl.findById", query = "SELECT o FROM OperacionesNbl o WHERE o.id = :id"),
    @NamedQuery(name = "OperacionesNbl.findAllByListaId", query = "SELECT o FROM OperacionesNbl o WHERE o.id IN :listaId"),
    @NamedQuery(name = "OperacionesNbl.findByCodigo", query = "SELECT o FROM OperacionesNbl o WHERE o.codigo = :codigo"),
    @NamedQuery(name = "OperacionesNbl.findAllByListaCodigo", query = "SELECT o FROM OperacionesNbl o WHERE o.codigo IN :listaCodigo"),
    @NamedQuery(name = "OperacionesNbl.findByNombre", query = "SELECT o FROM OperacionesNbl o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "OperacionesNbl.findByEquipoValidado", query = "SELECT o FROM OperacionesNbl o WHERE o.equipoValidado = :equipoValidado"),
    @NamedQuery(name = "OperacionesNbl.findByUrl", query = "SELECT o FROM OperacionesNbl o WHERE o.url = :url"),
    @NamedQuery(name = "OperacionesNbl.findByZonaSegura", query = "SELECT o FROM OperacionesNbl o WHERE o.zonaSegura = :zonaSegura"),
    @NamedQuery(name = "OperacionesNbl.findByEnviaNotificaciones", query = "SELECT o FROM OperacionesNbl o WHERE o.enviaNotificaciones = :enviaNotificaciones"),
    @NamedQuery(name = "OperacionesNbl.findByFechaInicial", query = "SELECT o FROM OperacionesNbl o WHERE o.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "OperacionesNbl.findByOpcion", query = "SELECT o FROM OperacionesNbl o WHERE o.fkIdopcion = :fkIdopcion"),
    @NamedQuery(name = "OperacionesNbl.findByFechaFinal", query = "SELECT o FROM OperacionesNbl o WHERE o.fechaFinal = :fechaFinal")})

public class OperacionesNbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 45)
    @Column(name = "CODIGO")
    private String codigo;
    @Size(max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "EQUIPO_VALIDADO")
    private Short equipoValidado;
    @JoinColumn(name = "TIPO_OPERACION_NBL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TipoOperacionNbl tipoOperacionNblId;
    @Size(max = 512)
    @Column(name = "URL")
    private String url;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkIdoperacionbl")
    private List<Detoperacionesnbl> detoperacionesnblList;
    @Transient
    public static final int VALOR_OPERACION_BASE_ACTIVA = 1;
    @Transient
    public static final int VALOR_OPERACION_BASE_NO_ACTIVA = 0;
    @Transient
    public static final String CODIGO_OPERACION_TRANSFERENCIAS = "transferencias";
    @Transient
    public static final String CODIGO_OPERACION_RESUMEN_CUENTAS = "consulta.cuentas";
    @Transient
    public static final String CODIGO_OPERACION_RESUMEN_TARJETAS = "consulta.tarjetas";
    @Transient
    public static final String CODIGO_OPERACION_PAGOS_TDC = "pagos.tdc";

    @Transient
    public static final String CODIGO_OPERACION_PAGO_PROPIO_TDC_MB = "pago.propio.tdc.mb";
    @Transient
    public static final String CODIGO_OPERACION_PAGO_PROPIO_TDC_OB = "pago.propio.tdc.ob";

    @Transient
    public static final String CODIGO_OPERACION_PAGO_TERCERO_TDC_MB = "pago.tercero.tdc.mb";
    @Transient
    public static final String CODIGO_OPERACION_PAGO_TERCERO_TDC_OB = "pago.tercero.tdc.ob";
    @Transient
    public static final String CODIGO_OPERACION_PAGOS_CREDITOS = "pagos.creditos.efectuado";
    @Transient
    public static final String CODIGO_OPERACION_PAGOS_CREDITOS_MODULO = "pagos.creditos";
    @Transient
    public static final String CODIGO_OPERACION_TRANSFERENCIAS_OTROS_BANCOS = "transferencias.ob";
    @Transient
    public static final String CODIGO_OPERACION_TRANSFERENCIAS_MISMO_BANCOS = "transferencias.mb";
    @Transient
    public static final String CODIGO_OPERACION_PAGO_SERVICIOS = "pagos.servicios.proceso";
    @Transient
    public static final String CODIGO_OPERACION_PAGO_SERVICIOS_LIMITES = "pagos.servicios";
    @Transient
    public static final String CODIGO_OPERACION_PAGO_IMPUESTO_LIMITES = "pagos.impuestos";
    @Transient
    public static final String CODIGO_OPERACION_PAGO_IMPUESTO = "pagos.impuestos.proceso";
    @Transient
    public static final String CODIGO_OPERACION_AVANCE_EFECTIVO_TRANSACCION = "avance.efectivo.efectuado";
    @Transient
    public static final String CODIGO_OPERACION_AVANCE_EFECTIVO_MODULO = "avance.efectivo.index";
    @Transient
    public static final String CODIGO_OPERACION_INVITACIONES_NO_CLIENTE = "invitaciones.nocliente";
    @Transient
    public static final String CODIGO_PAGO_NOMINA_INDIVIDUAL = "pagos.nomina.individual.efectuado";
    @Transient
    public static final String CODIGO_PAGO_SERVICIOS = "pagos.servicios.proceso";
    @Transient
    public static final String CODIGO_PAGO_IMPUESTOS = "pagos.impuestos.proceso";
    @Transient
    public static final String CODIGO_PAGO_NOMINA_ACCESO_MODULO = "pagos.nomina.index";
    @Transient
    public static final String CODIGO_PAGO_NOMINA_CONSULTA = "pagos.nomina.consulta";
    @Transient
    public static final String CODIGO_PAGO_NOMINA_APROBACION = "pagos.nomina.flujo.aprobaciones";
    @Transient
    public static final String CODIGO_PAGO_PROVEEDORES_CONSULTA = "pagos.proveedores.consulta";
    @Transient
    public static final String CODIGO_PAGO_PROVEEDORES_INDIVIDUAL = "pagos.proveedores.individual";
    @Transient
    public static final String CODIGO_PAGO_PROVEEDORES_INDIVIDUAL_TRANSACCION = "pagos.proveedores.individual.efectuado";
    @Transient
    public static final String CODIGO_PAGO_PROVEEDORES_MASIVO_TRANSACCION = "pagos.proveedores.masivos.efectuado";
    @Transient
    public static final String CODIGO_PAGO_FLUJO_APROBACION = "flujo.aprobacion.aprobar";
    @Transient
    public static final String CODIGO_PAGO_FLUJO_APROBACION_EXTRA = "modulo.aprobacion.asociados";
    @Transient
    public static final String CODIGO_PAGO_PROVEEDORES_APROBACION = "pagos.proveedores.flujo.aprobaciones";
    @Transient
    public static final String CODIGO_PAGO_PROVEEDORES_MASIVOS = "pagos.proveedores.masivos";
    @Transient
    public static final String CODIGO_PAGO_PROVEEDORES_ACCESO_MODULO = "pagos.proveedores.index";
    @Transient
    public static final String CODIGO_PAGO_NOMINA_MASIVO = "pagos.nomina.masivos.efectuado";
    @Transient
    public static final String CODIGO_OPERACION_CONFIGURACION_SEGURIDAD_NO_CLIENTE = "configuracion.seguridad.nocliente";
    @Transient
    public static final String CODIGO_OPERACION_CONFIGURACION_CAMBIAR_IMAGEN_NO_CLIENTE = "cambiar.imagen.nocliente";
    @Transient
    public static final String CODIGO_OPERACION_CONFIGURACION_CAMBIO_CLAVE_NO_CLIENTE = "cambiar.clave.nocliente";
    @Transient
    public static final String CODIGO_OPERACION_CONFIGURACION_CAMBIO_PREGUNTAS_NO_CLIENTE = "cambiar.preguntas.nocliente";
    @Transient
    public static final String CODIGO_OPERACION_CONFIGURACION_FACTORES_AUTENTICACION_NO_CLIENTE = "factores.autenticacion.nocliente";
    @Transient
    public static final String CODIGO_OPERACION_CONFIGURACION_EQUIPO_USOFRECUENTE_NO_CLIENTE = "consultar.equipo.frecuente.nocliente";
    @Transient
    public static final String CODIGO_OPERACION_AFILIACION_CUENTA_ESTADO = "afiliacion.cuenta.estado.index";
    @Transient
    public static final String CODIGO_CONSULTA_ESTADOS_CUENTA = "consulta.estados.cuenta";

    @Basic(optional = false)
    @NotNull
    @Column(name = "BASE")
    private Long base;

    @NotNull
    @Column(name = "ACCION_SEGURIDAD")
    private Character accion_seguridad;

    public Long getBase() {
        return base;
    }

    public void setBase(Long base) {
        this.base = base;
    }

    /*
     @NotNull
     @Column(name = "ESTATUS")
     private Character estatus;

     @Size(max = 512)
     @Column(name = "URL_SMALL")
     private String urlSmall;
     */
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZONASEGURA")
    private Boolean zonaSegura;
    @Column(name = "NOTIFICACION")
    private Character enviaNotificaciones;
    @Column(name = "FECINICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicial;
    @Column(name = "FECFINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinal;
    @JoinColumn(name = "FK_IDAMBIENTE", referencedColumnName = "ID")
    @ManyToOne
    private Ambientes ambientesId;

//    @JoinTable(name = "perfilesnbl_has_operacionesnbl", joinColumns = {
//        @JoinColumn(name = "operaciones_nbl_id", referencedColumnName = "id")}, inverseJoinColumns = {
//        @JoinColumn(name = "perfiles_nbl_id", referencedColumnName = "id")})
//    @ManyToMany
    @ManyToMany(mappedBy = "operacionesNblCollection")
    private Collection<PerfilesNbl> perfilesNblCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operacionesNbl")
    private Collection<PerfilesnblHasOperacionesnbl> perfilesnblHasOperacionesnblCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "operacionesNblId")
    private Collection<BitacoraNbl> bitacoraNblCollection;
    @JoinColumn(name = "TIPO_OPERACION_NBL_ID", referencedColumnName = "ID")
    @OneToMany(mappedBy = "operacionesNblId")
    private Collection<Menu> menuCollection;
    @Column(name = "ESTATUS")
    private String estatus;
    @Size(max = 512)
    @Column(name = "URL_SMALL")
    private String url_small;
    @Column(name = "GENCOMPROBANTE")
    private Number generaComprobante;
    @JoinColumn(name = "FK_IDOPCION", referencedColumnName = "PK_IDOPCION")
    @ManyToOne
    private Mstopciones fkIdopcion;
    @Column(name = "ALTIMAGEN")
    private Long altimagen;
    @Column(name = "ANCIMAGEN")
    private Long ancimagen;

    @JoinColumn(name = "FK_IDCANAL", referencedColumnName = "ID")
    @ManyToOne
    private Canales fkIdcanal;

    @Transient
    private String url2;

    public OperacionesNbl() {
    }

    public OperacionesNbl(Long id) {
        this.id = id;
    }

    public String getEstatus() {
        return estatus;
    }

    public Long getAltimagen() {
        return altimagen;
    }

    public void setAltimagen(Long altimagen) {
        this.altimagen = altimagen;
    }

    public Long getAncimagen() {
        return ancimagen;
    }

    public void setAncimagen(Long ancimagen) {
        this.ancimagen = ancimagen;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Long getId() {
        return id;
    }

    public Mstopciones getFkIdopcion() {
        return fkIdopcion;
    }

    public void setFkIdopcion(Mstopciones fkIdopcion) {
        this.fkIdopcion = fkIdopcion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getEquipoValidado() {
        return equipoValidado;
    }

    @XmlTransient
    public List<Detoperacionesnbl> getDetoperacionesnblList() {
        return detoperacionesnblList;
    }

    public void setDetoperacionesnblList(List<Detoperacionesnbl> detoperacionesnblList) {
        this.detoperacionesnblList = detoperacionesnblList;
    }

    public void setEquipoValidado(Short equipoValidado) {
        this.equipoValidado = equipoValidado;
    }

    public String getUrl() {
        return url;
    }

    public String getUrl2() {
        return url + "?faces-redirect=true";
    }

    public void setUrl2(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_small() {
        return url_small;
    }

    public void setUrl_small(String url_small) {
        this.url_small = url_small;
    }

    @XmlTransient
    public Collection<PerfilesNbl> getPerfilesNblCollection() {
        return perfilesNblCollection;
    }

    public void setPerfilesNblCollection(Collection<PerfilesNbl> perfilesNblCollection) {
        this.perfilesNblCollection = perfilesNblCollection;
    }

    @XmlTransient
    public Collection<BitacoraNbl> getBitacoraNblCollection() {
        return bitacoraNblCollection;
    }

    public void setBitacoraNblCollection(Collection<BitacoraNbl> bitacoraNblCollection) {
        this.bitacoraNblCollection = bitacoraNblCollection;
    }

    public TipoOperacionNbl getTipoOperacionNblId() {
        return tipoOperacionNblId;
    }

    public void setTipoOperacionNblId(TipoOperacionNbl tipoOperacionNblId) {
        this.tipoOperacionNblId = tipoOperacionNblId;
    }

    @XmlTransient
    public Collection<Menu> getMenuCollection() {
        return menuCollection;
    }

    public void setMenuCollection(Collection<Menu> menuCollection) {
        this.menuCollection = menuCollection;
    }

    public Boolean isZonaSegura() {
        return zonaSegura;
    }

    public void setZonaSegura(Boolean zonaSegura) {
        this.zonaSegura = zonaSegura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Number getGeneraComprobante() {
        return generaComprobante;
    }

    public void setGeneraComprobante(Number generaComprobante) {
        this.generaComprobante = generaComprobante;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OperacionesNbl)) {
            return false;
        }
        OperacionesNbl other = (OperacionesNbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.OperacionesNbl[ id=" + id + " ]";
    }

}
