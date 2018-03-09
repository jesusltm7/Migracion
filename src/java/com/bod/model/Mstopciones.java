/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author intec
 */
@Entity
@Table(name = "MSTOPCIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mstopciones.findAll", query = "SELECT m FROM Mstopciones m"),
    @NamedQuery(name = "Mstopciones.findByPkIdopcion", query = "SELECT m FROM Mstopciones m WHERE m.pkIdopcion = :pkIdopcion"),
    @NamedQuery(name = "Mstopciones.findByValnivel", query = "SELECT m FROM Mstopciones m WHERE m.valnivel = :valnivel"),
    @NamedQuery(name = "Mstopciones.findByValnivelEstado", query = "SELECT m FROM Mstopciones m WHERE m.valnivel = :valnivel AND m.estopcion = :estopcion"),
    @NamedQuery(name = "Mstopciones.findByNomopcion", query = "SELECT m FROM Mstopciones m WHERE m.nomopcion = :nomopcion"),
    @NamedQuery(name = "Mstopciones.findByEstopcion", query = "SELECT m FROM Mstopciones m WHERE m.estopcion = :estopcion")})

public class Mstopciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDOPCION")
    private Long pkIdopcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "VALNIVEL")
    private String valnivel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMOPCION")
    private String nomopcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTOPCION")
    private String estopcion;
    @Transient
    public static final String VALOR_NIVEL_MASTER = "1";
    @Transient
    public static final String VALOR_NIVEL_REGULAR = "2";
    @Transient
    public static final String VALOR_ESTADO_ACTIVO = "1";
    @Transient
    public static final String VALOR_ESTADO_INACTIVO = "0";

    public Mstopciones() {
    }

    public Mstopciones(Long pkIdopcion) {
        this.pkIdopcion = pkIdopcion;
    }

    public Mstopciones(Long pkIdopcion, String valnivel, String nomopcion, String estopcion) {
        this.pkIdopcion = pkIdopcion;
        this.valnivel = valnivel;
        this.nomopcion = nomopcion;
        this.estopcion = estopcion;
    }

    public Long getPkIdopcion() {
        return pkIdopcion;
    }

    public void setPkIdopcion(Long pkIdopcion) {
        this.pkIdopcion = pkIdopcion;
    }

    public String getValnivel() {
        return valnivel;
    }

    public void setValnivel(String valnivel) {
        this.valnivel = valnivel;
    }

    public String getNomopcion() {
        return nomopcion;
    }

    public void setNomopcion(String nomopcion) {
        this.nomopcion = nomopcion;
    }

    public String getEstopcion() {
        return estopcion;
    }

    public void setEstopcion(String estopcion) {
        this.estopcion = estopcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdopcion != null ? pkIdopcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mstopciones)) {
            return false;
        }
        Mstopciones other = (Mstopciones) object;
        if ((this.pkIdopcion == null && other.pkIdopcion != null) || (this.pkIdopcion != null && !this.pkIdopcion.equals(other.pkIdopcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Mstopciones[ pkIdopcion=" + pkIdopcion + " ]";
    }

}
