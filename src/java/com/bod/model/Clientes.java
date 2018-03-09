/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@Table(name = "CLIENTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findById", query = "SELECT c FROM Clientes c WHERE c.id = :id"),
    @NamedQuery(name = "Clientes.findByIdentificacion", query = "SELECT c FROM Clientes c WHERE c.identificacion = :identificacion"),
    @NamedQuery(name = "Clientes.findByNombre", query = "SELECT c FROM Clientes c WHERE c.nombre = :nombre")})
public class Clientes implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;       
    
    @SequenceGenerator(name = "CLIENTES_SEQ", sequenceName = "CLIENTES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTES_SEQ")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "TIPO_IDENTIFICACION")
    private String tipoIdentificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Size(max = 140)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 45)
    @Column(name = "CORE_ID")
    private String coreId;
    
    @Column(name = "ESTATUS_LOGIN_ZONA_SEGURA")
    private Character ZonaSeguraLogin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientes")
    private Collection<ClientesHasUsuariosNbl> clientesHasUsuariosNblCollection;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "clientesId")
    private Collection<PerfilesNbl> perfilesNblCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clientesId")
    private Collection<DirectorioGlobal> directorioGlobalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteId")
    private Collection<Invitaciones> invitacionesCollection;
    
    @Transient
    private Boolean ZonaSegura = false;     
    
    public Clientes() {
    }

    public Clientes(Long id) {
        this.id = id;
    }

    public Clientes(Long id, String identificacion) {
        this.id = id;
        this.identificacion = identificacion;
    }

    public Collection<Invitaciones> getInvitacionesCollection() {
        return invitacionesCollection;
    }

    public void setInvitacionesCollection(Collection<Invitaciones> invitacionesCollection) {
        this.invitacionesCollection = invitacionesCollection;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getCoreId() {
        return coreId;
    }

    public void setCoreId(String coreId) {
        this.coreId = coreId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<ClientesHasUsuariosNbl> getClientesHasUsuariosNblCollection() {
        return clientesHasUsuariosNblCollection;
    }

    public void setClientesHasUsuariosNblCollection(Collection<ClientesHasUsuariosNbl> clientesHasUsuariosNblCollection) {
        this.clientesHasUsuariosNblCollection = clientesHasUsuariosNblCollection;
    }

    @XmlTransient
    public Collection<PerfilesNbl> getPerfilesNblCollection() {
        return perfilesNblCollection;
    }

    public void setPerfilesNblCollection(Collection<PerfilesNbl> perfilesNblCollection) {
        this.perfilesNblCollection = perfilesNblCollection;
    }

    @XmlTransient
    public Collection<DirectorioGlobal> getDirectorioGlobalCollection() {
        return directorioGlobalCollection;
    }

    public void setDirectorioGlobalCollection(Collection<DirectorioGlobal> directorioGlobalCollection) {
        this.directorioGlobalCollection = directorioGlobalCollection;
    }
    
    public DirectorioGlobal getDirectorioPropio(){
        if(getDirectorioGlobalCollection() != null){
            for(DirectorioGlobal d : getDirectorioGlobalCollection()){
                if(d!=null && d.getTipoIdentificacion().equals(getTipoIdentificacion()) && d.getIdentificacion().equals(getIdentificacion())){
                    return d;
                }
            }
        }
        
        return null;
    }
    
    public List<DirectorioGlobal> getDirectorioTerceros(){
        List<DirectorioGlobal> l = new ArrayList<>();
        
        if(getDirectorioGlobalCollection() != null){
            for(DirectorioGlobal d : getDirectorioGlobalCollection()){
                if(d!=null && !d.getTipoIdentificacion().equals(getTipoIdentificacion()) || !d.getIdentificacion().equals(getIdentificacion())){
                    l.add(d);
                }
            }
        }
        
        return l;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bod.model.Clientes[ id=" + id + " ]";
    }   

    /**
     * @return the ZonaSegura
     */
    public Boolean getZonaSegura() {
        return ZonaSegura;
    }

    /**
     * @param ZonaSegura the ZonaSegura to set
     */
    public void setZonaSegura(Boolean ZonaSegura) {
        this.ZonaSegura = ZonaSegura;
    }

    /**
     * @return the ZonaSeguraLogin
     */
    public Character getZonaSeguraLogin() {
        return ZonaSeguraLogin;
    }

    /**
     * @param ZonaSeguraLogin the ZonaSeguraLogin to set
     */
    public void setZonaSeguraLogin(Character ZonaSeguraLogin) {
        this.ZonaSeguraLogin = ZonaSeguraLogin;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
      
    
}
