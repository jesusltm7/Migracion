/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "MODULOS_USUARIOS_NBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModulosUsuariosNbl.findAll", query = "SELECT m FROM ModulosUsuariosNbl m"),
    @NamedQuery(name = "ModulosUsuariosNbl.findByIdModulo", query = "SELECT m FROM ModulosUsuariosNbl m WHERE m.modulosUsuariosNblPK.idModulo = :idModulo"),
    @NamedQuery(name = "ModulosUsuariosNbl.findByIdUsuarioNbl", query = "SELECT m FROM ModulosUsuariosNbl m WHERE m.modulosUsuariosNblPK.idUsuarioNbl = :idUsuarioNbl"),
    @NamedQuery(name = "ModulosUsuariosNbl.findBySeccion", query = "SELECT m FROM ModulosUsuariosNbl m WHERE m.seccion = :seccion")})
public class ModulosUsuariosNbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModulosUsuariosNblPK modulosUsuariosNblPK;
    @Column(name = "SECCION")
    private Long seccion;
    @JoinColumn(name = "ID_USUARIO_NBL", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UsuariosNbl usuariosNbl;
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Modulos modulos;
   
    @JoinColumn(name = "operaciones_nbl_id", referencedColumnName = "id",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OperacionesNbl operacionesNblId;

    public ModulosUsuariosNbl() {
    }

    public ModulosUsuariosNbl(ModulosUsuariosNblPK modulosUsuariosNblPK) {
        this.modulosUsuariosNblPK = modulosUsuariosNblPK;
    }

    public ModulosUsuariosNbl(long idModulo, long idUsuarioNbl,  long idOperacionesNblId) {
        this.modulosUsuariosNblPK = new ModulosUsuariosNblPK(idModulo, idUsuarioNbl, idOperacionesNblId);
    }

    public ModulosUsuariosNblPK getModulosUsuariosNblPK() {
        return modulosUsuariosNblPK;
    }

    public void setModulosUsuariosNblPK(ModulosUsuariosNblPK modulosUsuariosNblPK) {
        this.modulosUsuariosNblPK = modulosUsuariosNblPK;
    }

    public Long getSeccion() {
        return seccion;
    }

    public void setSeccion(Long seccion) {
        this.seccion = seccion;
    }

    public UsuariosNbl getUsuariosNbl() {
        return usuariosNbl;
    }

    public void setUsuariosNbl(UsuariosNbl usuariosNbl) {
        this.usuariosNbl = usuariosNbl;
    }

    public Modulos getModulos() {
        return modulos;
    }

    public void setModulos(Modulos modulos) {
        this.modulos = modulos;
    }
    
    public OperacionesNbl getOperacionesNblId() {
        return operacionesNblId;
    }

    public void setOperacionesNblId(OperacionesNbl operacionesNblId) {
        this.operacionesNblId = operacionesNblId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modulosUsuariosNblPK != null ? modulosUsuariosNblPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ModulosUsuariosNbl)) {
            return false;
        }
        ModulosUsuariosNbl other = (ModulosUsuariosNbl) object;
        if ((this.modulosUsuariosNblPK == null && other.modulosUsuariosNblPK != null) || (this.modulosUsuariosNblPK != null && !this.modulosUsuariosNblPK.equals(other.modulosUsuariosNblPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.ModulosUsuariosNbl[ modulosUsuariosNblPK=" + modulosUsuariosNblPK + " ]";
    }

}
