package com.bod.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maury Díaz
 */
@Entity
@Table(name = "DISPOSITIVOS_USUARIOS_NBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DispositivosUsuariosNbl.findAll", query = "SELECT d FROM DispositivosUsuariosNbl d"),
    @NamedQuery(name = "DispositivosUsuariosNbl.findById", query = "SELECT d FROM DispositivosUsuariosNbl d WHERE d.id = :id"),
    @NamedQuery(name = "DispositivosUsuariosNbl.findByEstatus", query = "SELECT d FROM DispositivosUsuariosNbl d WHERE d.estatus = :estatus")})
public class DispositivosUsuariosNbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "DISPOSITIVOS_USUARIOS_NBL_SEQ", sequenceName = "DISPOSITIVOS_USUARIOS_NBL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISPOSITIVOS_USUARIOS_NBL_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTATUS")
    private Character estatus;
    @Column(name = "ESTATUS_ZONA_SEGURA")
    private Character estatus_zona_segura;
    @JoinColumn(name = "USUARIO_NBL_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UsuariosNbl usuarioNblId;
    @JoinColumn(name = "DISPOSITIVO_FACTOR_3_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private DispositivosFactor3 dispositivoFactor3Id;

    public DispositivosUsuariosNbl() {
    }

    public DispositivosUsuariosNbl(Long id) {
        this.id = id;
    }

    public DispositivosUsuariosNbl(Long id, Character estatus) {
        this.id = id;
        this.estatus = estatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getEstatus() {
        return estatus;
    }

    public void setEstatus(Character estatus) {
        this.estatus = estatus;
    }

    public UsuariosNbl getUsuarioNblId() {
        return usuarioNblId;
    }

    public void setUsuarioNblId(UsuariosNbl usuarioNblId) {
        this.usuarioNblId = usuarioNblId;
    }

    public DispositivosFactor3 getDispositivoFactor3Id() {
        return dispositivoFactor3Id;
    }

    public void setDispositivoFactor3Id(DispositivosFactor3 dispositivoFactor3Id) {
        this.dispositivoFactor3Id = dispositivoFactor3Id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DispositivosUsuariosNbl)) {
            return false;
        }
        DispositivosUsuariosNbl other = (DispositivosUsuariosNbl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.entities.DispositivosUsuariosNbl[ id=" + id + " ]";
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

}
