package com.bod.model;

import com.bod.util.BodBaseBean;
import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maury Díaz
 */
@Entity
@Table(name = "DISPOSITIVOS_FACTOR_3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DispositivosFactor3.findAll", query = "SELECT d FROM DispositivosFactor3 d"),
    @NamedQuery(name = "DispositivosFactor3.findById", query = "SELECT d FROM DispositivosFactor3 d WHERE d.id = :id"),
    @NamedQuery(name = "DispositivosFactor3.findByCodigo", query = "SELECT d FROM DispositivosFactor3 d WHERE d.codigo = :codigo"),
    @NamedQuery(name = "DispositivosFactor3.findByNombre", query = "SELECT d FROM DispositivosFactor3 d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DispositivosFactor3.findByEstatus", query = "SELECT d FROM DispositivosFactor3 d WHERE d.estatus = :estatus")})
public class DispositivosFactor3 extends BodBaseBean implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DISPOSITIVOS_F_3_SEQ", sequenceName = "DISPOSITIVOS_F_3_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISPOSITIVOS_F_3_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 140)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS")
    private Character estatus;
    @Column(name = "ESTATUS_ZONA_SEGURA")
    private Character estatus_zona_segura;
    @OneToMany
    @JoinColumn(name = "ID", referencedColumnName = "DISPOSITIVO_FACTOR_3_ID")
    private Collection<DispositivosUsuariosNbl> dispositivosUsuariosNblCollection;
    
    @Transient
    private boolean seleccion;

    public DispositivosFactor3() {
    }

    public DispositivosFactor3(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<DispositivosUsuariosNbl> getDispositivosUsuariosNblCollection() {
        return dispositivosUsuariosNblCollection;
    }

    public void setDispositivosUsuariosNblCollection(Collection<DispositivosUsuariosNbl> dispositivosUsuariosNblCollection) {
        this.dispositivosUsuariosNblCollection = dispositivosUsuariosNblCollection;
    }

    public DispositivosFactor3(Long id, String codigo, String nombre, Character estatus, boolean seleccion) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.estatus = estatus;
        this.seleccion = seleccion;
    }

    public Long getId() {
        return id;
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

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }
    
    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DispositivosFactor3)) {
            return false;
        }
        DispositivosFactor3 other = (DispositivosFactor3) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.entities.DispositivosFactor3[ id=" + id + " ]";
    }

    /**
     * @return the estatus_zona_segura
     */
    public Character getEstatus_zona_segura() {
        return estatus_zona_segura;
    }

    /**
     * @param estatus_zona_segura the estatus_zona_segura to set
     */
    public void setEstatus_zona_segura(Character estatus_zona_segura) {
        this.estatus_zona_segura = estatus_zona_segura;
    }
    
    public boolean obtenerEstatus(){
        return (Character.valueOf(ESTADO_GENERICO_ACTIVO).equals(this.estatus));
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    

}
