package com.bod.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
//hola
/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "OPCIONES_PERFILES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionesPerfiles.findAll", query = "SELECT o FROM OpcionesPerfiles o"),
    @NamedQuery(name = "OpcionesPerfiles.findById", query = "SELECT o FROM OpcionesPerfiles o WHERE o.id = :id"),
    @NamedQuery(name = "OpcionesPerfiles.findByNombre", query = "SELECT o FROM OpcionesPerfiles o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "OpcionesPerfiles.findByNivel", query = "SELECT o FROM OpcionesPerfiles o WHERE o.nivel = :nivel"),
    @NamedQuery(name = "OpcionesPerfiles.findBssyivel", query = "SELECT o FROM OpcionesPerfiles o WHERE o.nivel = :nivel")})


public class OpcionesPerfiles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Size(max = 70)
    @NotNull
    @Column(name = "NOMBRE")
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "NIVEL")
    private String nivel;

    @Transient
    public static final String NIVEL_OPCION_MASTER = "Master";
    @Transient
    public static final String NIVEL_OPCION_REGULAR = "Regular";

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OpcionesPerfiles)) {
            return false;
        }
        OpcionesPerfiles other = (OpcionesPerfiles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.OpcionesPerfiles[ id=" + id + " ]";
    }

}
