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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mariesther
 */
@Entity
@Table(name = "PERFILESNBL_HAS_OPERACIONESNBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilesnblHasOperacionesnbl.findAll", query = "SELECT p FROM PerfilesnblHasOperacionesnbl p"),
    @NamedQuery(name = "PerfilesnblHasOperacionesnbl.findByPerfilesNblId", query = "SELECT p FROM PerfilesnblHasOperacionesnbl p WHERE p.perfilesnblHasOperacionesnblPK.perfilesNblId = :perfilesNblId"),
    @NamedQuery(name = "PerfilesnblHasOperacionesnbl.findByPerfilesOperaciones", query = "SELECT p FROM PerfilesnblHasOperacionesnbl p WHERE p.perfilesnblHasOperacionesnblPK.perfilesNblId = :perfilesNblId and p.perfilesnblHasOperacionesnblPK.operacionesNblId = :operacionesNblId"),
    @NamedQuery(name = "PerfilesnblHasOperacionesnbl.findByOperacionesNblId", query = "SELECT p FROM PerfilesnblHasOperacionesnbl p WHERE p.perfilesnblHasOperacionesnblPK.operacionesNblId = :operacionesNblId")})
public class PerfilesnblHasOperacionesnbl implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PerfilesnblHasOperacionesnblPK perfilesnblHasOperacionesnblPK;
    @JoinColumn(name = "PERFILES_NBL_ID", referencedColumnName = "ID", insertable = false, updatable = false)

    @ManyToOne(optional = false)
    private PerfilesNbl perfilesNbl;
    @JoinColumn(name = "OPERACIONES_NBL_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OperacionesNbl operacionesNbl;
    @Column(name = "CODROL")
    private String codrol;

    public PerfilesnblHasOperacionesnbl() {
    }

    public PerfilesnblHasOperacionesnbl(PerfilesnblHasOperacionesnblPK perfilesnblHasOperacionesnblPK) {

        this.perfilesnblHasOperacionesnblPK = perfilesnblHasOperacionesnblPK;

    }

    public PerfilesnblHasOperacionesnbl(long perfilesNblId, long operacionesNblId) {
        this.perfilesnblHasOperacionesnblPK = new PerfilesnblHasOperacionesnblPK(perfilesNblId, operacionesNblId);
    }

    public PerfilesnblHasOperacionesnblPK getPerfilesnblHasOperacionesnblPK() {
        return perfilesnblHasOperacionesnblPK;
    }

    public void setPerfilesnblHasOperacionesnblPK(PerfilesnblHasOperacionesnblPK perfilesnblHasOperacionesnblPK) {
        this.perfilesnblHasOperacionesnblPK = perfilesnblHasOperacionesnblPK;
    }

    public String getCodrol() {
        return codrol;
    }

    public void setCodrol(String codrol) {
        this.codrol = codrol;
    }

    public PerfilesNbl getPerfilesNbl() {
        return perfilesNbl;
    }

    public void setPerfilesNbl(PerfilesNbl perfilesNbl) {
        this.perfilesNbl = perfilesNbl;
    }

    public OperacionesNbl getOperacionesNbl() {
        return operacionesNbl;
    }

    public void setOperacionesNbl(OperacionesNbl operacionesNbl) {
        this.operacionesNbl = operacionesNbl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilesnblHasOperacionesnblPK != null ? perfilesnblHasOperacionesnblPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PerfilesnblHasOperacionesnbl)) {
            return false;
        }
        PerfilesnblHasOperacionesnbl other = (PerfilesnblHasOperacionesnbl) object;
        if ((this.perfilesnblHasOperacionesnblPK == null && other.perfilesnblHasOperacionesnblPK != null) || (this.perfilesnblHasOperacionesnblPK != null && !this.perfilesnblHasOperacionesnblPK.equals(other.perfilesnblHasOperacionesnblPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.PerfilesnblHasOperacionesnbl[ perfilesnblHasOperacionesnblPK=" + perfilesnblHasOperacionesnblPK + " ]";
    }

}
