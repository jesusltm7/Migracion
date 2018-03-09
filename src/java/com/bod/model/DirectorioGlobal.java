package com.bod.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import static com.bod.model.Productos.*;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Usuario
 * @author Gorka Siverio (GS.INT) @ adverweb.com
 */
@Entity
@Table(name = "DIRECTORIO_GLOBAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DirectorioGlobal.findAll", query = "SELECT d FROM DirectorioGlobal d"),
    @NamedQuery(name = "DirectorioGlobal.findById", query = "SELECT d FROM DirectorioGlobal d WHERE d.id = :id"),
    @NamedQuery(name = "DirectorioGlobal.findByAlias", query = "SELECT d FROM DirectorioGlobal d WHERE d.alias = :alias"),
    @NamedQuery(name = "DirectorioGlobal.findByNombre", query = "SELECT d FROM DirectorioGlobal d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DirectorioGlobal.findByEmail", query = "SELECT d FROM DirectorioGlobal d WHERE d.email = :email"),
    @NamedQuery(name = "DirectorioGlobal.findByTelefono", query = "SELECT d FROM DirectorioGlobal d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "DirectorioGlobal.findByEstatus", query = "SELECT d FROM DirectorioGlobal d WHERE d.estatus = :estatus")})
public class DirectorioGlobal implements Serializable, Comparable<DirectorioGlobal> {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DIRECTORIO_GLOBAL_SEQ", sequenceName = "DIRECTORIO_GLOBAL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIRECTORIO_GLOBAL_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 50)
    @Column(name = "ALIAS")
    private String alias;
    @Size(max = 250)
    @Column(name = "NOMBRE")
    private String nombre;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 250)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 45)
    @Column(name = "TELEFONO")
    private String telefono;
    @Column(name = "ESTATUS")
    private Character estatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "directorioGlobalId")
    private List<Productos> productosCollection;
    @JoinColumn(name = "CLIENTES_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Clientes clientesId;
    @Size(max = 45)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Size(max = 1)
    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;

    public DirectorioGlobal() {
    }

    public DirectorioGlobal(Long id) {
        this.id = id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    @XmlTransient
    public Collection<Productos> getProductosCollection() {
        Collections.sort(productosCollection, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Productos) o1).getCategoriaProductosId().getNombre().compareTo(((Productos) o2).getCategoriaProductosId().getNombre());
            }
        });
        return productosCollection;
    }

    public void setProductosCollection(List<Productos> productosCollection) {
        this.productosCollection = productosCollection;
    }

    public Clientes getClientesId() {
        return clientesId;
    }

    public void setClientesId(Clientes clientesId) {
        this.clientesId = clientesId;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DirectorioGlobal)) {
            return false;
        }
        DirectorioGlobal other = (DirectorioGlobal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "com.bod.model.DirectorioGlobal[ id=" + id + " ]";
        String result = "ID: '" + id
                + "', Identificacion: '" + tipoIdentificacion + "-" + identificacion
                + "', estatus: '" + estatus
                + "', nombre: '" + nombre
                + "', alias: '" + alias
                + "', numero: '" + telefono
                + "', email: '" + email
                + "'.";
        if (productosCollection != null && !productosCollection.isEmpty()) {
            for (Productos p : productosCollection) {
                result += "\n>>" + p;
            }
        }
        return result;
    }
    
    
    
    
    public List<Productos> getCuentas() {
        return getProductosPorTipo(CODIGO_PRODUCTO_CUENTA);
    }
    
    
      /**
     * Consulta de todos los productos CUENTAS asociados al cliente que realiza la
     * consulta y asociados al banco BOD.
     * Modify: Carlos Romero
     * @return Listado de Productos
     */

    public List<Productos> getCuentasBod() {
        return getProductosPorTipoPorBanco(CODIGO_PRODUCTO_CUENTA,CODIGO_PRODUCTO_ID_BANCO);
    }
    
     
      /**
     * Consulta de todos los productos CUENTAS asociados al cliente que realiza la
     * consulta asociados al banco BOD y ademas que se encuentren activos
     * Modify: Carlos Romero
     * @return Listado de Productos
     */
    
    public List<Productos> getCuentasBodActivas() {
        return getProductosPorTipoPorBancoActivos(CODIGO_PRODUCTO_CUENTA,CODIGO_PRODUCTO_ID_BANCO,CODIGO_PRODUCTO_ESTATUS_ACTIVO);
    }
    
    
    public List<Productos> getCuentasBodActivasPorNumero(String numeroProducto){
    
    return getProductosPorNumeroPorTipoPorBancoActivos(CODIGO_PRODUCTO_CUENTA,CODIGO_PRODUCTO_ID_BANCO,CODIGO_PRODUCTO_ESTATUS_ACTIVO, numeroProducto);
    
    }
    
    
     public List<Productos> getTarjetasBodActivasPorNumero(String numeroProducto){
    
    return getProductosPorNumeroPorTipoPorBancoActivos(CODIGO_PRODUCTO_TDC,CODIGO_PRODUCTO_ID_BANCO,CODIGO_PRODUCTO_ESTATUS_ACTIVO, numeroProducto);
    
    }

    /**
     * Consulta de todos los productos TDC asociados al cliente que realiza la
     * consulta
     *
     * @return Listado de Productos
     */
    public List<Productos> getTarjetas() {
        return getProductosPorTipo(CODIGO_PRODUCTO_TDC);
    }
    
    
     /**
     * Consulta de todos los productos TDC asociados al cliente que realiza la
     * consulta y asociados al banco BOD.
     * Modify: Carlos Romero
     * @return Listado de Productos
     */
    public List<Productos> getTarjetasBod() {
        return getProductosPorTipoPorBanco(CODIGO_PRODUCTO_TDC,CODIGO_PRODUCTO_ID_BANCO);
    }
    
    /**
     * Consulta de todos los productos TDC asociados al cliente que realiza la
     * consulta  asociados al banco BOD y que ademas se encuentren activos.
     * Modify: Carlos Romero
     * @return Listado de Productos
     */
     public List<Productos> getTarjetasBodActivas() {
        return getProductosPorTipoPorBancoActivos(CODIGO_PRODUCTO_TDC, CODIGO_PRODUCTO_ID_BANCO,CODIGO_PRODUCTO_ESTATUS_ACTIVO);
    }

    /**
     * Consulta de todos los productos Creditos asociados al cliente que realiza
     * la consulta
     *
     * @return Listado de Productos
     */
    public List<Productos> getCreditos() {
        return getProductosPorTipo(CODIGO_PRODUCTO_CREDITO);
    }

    /**
     * Consulta de todos los productos de tipo Fideicomisos asociados al cliente
     * que realiza la consulta
     *
     * @return Listado de Productos tipo Fideicomisos
     */
    public List<Productos> getFideicomisos() {
        return getProductosPorTipo(CODIGO_PRODUCTO_FIDEICOMISO);
    }

    /**
     * Consulta de todos los productos de tipo Servicio asociados al cliente que
     * realiza la consulta
     *
     * @return Listado de Productos tipo Servicio
     */
    public List<Productos> getServicios() {
        return getProductosPorTipo(CODIGO_PRODUCTO_SERVICIO);
    }
    
    /**
     * Consulta de todos los productos de tipo Tributo asociados al cliente que
     * realiza la consulta
     *
     * @return Listado de Productos tipo Tributo
     */
    public List<Productos> getTributos() {
        return getProductosPorTipo(CODIGO_PRODUCTO_TRIBUTO);
    }

    /**
     * Consulta de todos los productos de tipo tipoProducto asociados al cliente
     * que realiza la consulta
     *
     * @param tipoProducto String con el tipo de producto a usar como filtro
     * @return Listado de Productos tipo Servicio
     */
    public List<Productos> getProductosPorTipo(String tipoProducto) {
        ArrayList<Productos> productosFiltrados = new ArrayList<>();
        for (Productos p : getProductosCollection()) {
            if (tipoProducto.equals(p.getCategoriaProductosId().getCodigo())) {
                productosFiltrados.add(p);
            }
        }
        Collections.sort(productosFiltrados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                // No es exacto usar el getEtiquetaNombre, pero de momento sirve
                Productos p1 = (Productos) o1;
                String n1 = p1.getTipoServicio() ? p1.getServicio().getEtiquetaNombre() : p1.getBancosId().getNombre();
                Productos p2 = (Productos) o2;
                String n2 = p2.getTipoServicio() ? p2.getServicio().getEtiquetaNombre() : p2.getBancosId().getNombre();
                return n1.compareTo(n2);
            }
        });
        return productosFiltrados;
    }
    
    public List<Productos> getProductosPorTipoPorBanco(String tipoProducto, String codigoBanco) {
        ArrayList<Productos> productosFiltrados = new ArrayList<>();
        for (Productos p : getProductosCollection()) {
            if (tipoProducto.equals(p.getCategoriaProductosId().getCodigo()) && codigoBanco.equals(p.getBancosId().getCodigo()) ) {
                productosFiltrados.add(p);
            }
        }
        
        Collections.sort(productosFiltrados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                // No es exacto usar el getEtiquetaNombre, pero de momento sirve
                Productos p1 = (Productos) o1;
                String n1 = p1.getTipoServicio() ? p1.getServicio().getEtiquetaNombre() : p1.getBancosId().getNombre();
                Productos p2 = (Productos) o2;
                String n2 = p2.getTipoServicio() ? p2.getServicio().getEtiquetaNombre() : p2.getBancosId().getNombre();
                return n1.compareTo(n2);
            }
        });
        return productosFiltrados;
    }
    
    
    
    
    public List<Productos> getProductosPorTipoPorBancoActivos(String tipoProducto, String codigoBanco, String codigoActivo) {
        ArrayList<Productos> productosFiltrados = new ArrayList<>();
        for (Productos p : getProductosCollection()) {
            if (tipoProducto.equals(p.getCategoriaProductosId().getCodigo()) && codigoBanco.equals(p.getBancosId().getCodigo()) && codigoActivo.equals(p.getEstatus().toString()) ) {
                productosFiltrados.add(p);
            }
        }
        
        Collections.sort(productosFiltrados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                // No es exacto usar el getEtiquetaNombre, pero de momento sirve
                Productos p1 = (Productos) o1;
                String n1 = p1.getTipoServicio() ? p1.getServicio().getEtiquetaNombre() : p1.getBancosId().getNombre();
                Productos p2 = (Productos) o2;
                String n2 = p2.getTipoServicio() ? p2.getServicio().getEtiquetaNombre() : p2.getBancosId().getNombre();
                return n1.compareTo(n2);
            }
        });
        return productosFiltrados;
    }
    
    
    
        
    public List<Productos> getProductosPorNumeroPorTipoPorBancoActivos(String tipoProducto, String codigoBanco, String codigoActivo, String numeroProducto) {
        ArrayList<Productos> productosFiltrados = new ArrayList<>();
        for (Productos p : getProductosCollection()) {
            if (tipoProducto.equals(p.getCategoriaProductosId().getCodigo()) && codigoBanco.equals(p.getBancosId().getCodigo()) && codigoActivo.equals(p.getEstatus().toString()) && numeroProducto.equals(p.getNumero()) ) {
                productosFiltrados.add(p);
            }
        }
        
        Collections.sort(productosFiltrados, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                // No es exacto usar el getEtiquetaNombre, pero de momento sirve
                Productos p1 = (Productos) o1;
                String n1 = p1.getTipoServicio() ? p1.getServicio().getEtiquetaNombre() : p1.getBancosId().getNombre();
                Productos p2 = (Productos) o2;
                String n2 = p2.getTipoServicio() ? p2.getServicio().getEtiquetaNombre() : p2.getBancosId().getNombre();
                return n1.compareTo(n2);
            }
        });
        return productosFiltrados;
    }
    
    
    
    
    
    

    /**
     * Como el toString(), pues.
     *
     * @return String con la data
     */
    public String printInfo() {
        return this.toString();
    }

    @Override
    public int compareTo(DirectorioGlobal o) {
        try {
            return this.id.compareTo(o.getId());                    
        } catch (Exception e) {
            return 0;
        }              
    }
}
