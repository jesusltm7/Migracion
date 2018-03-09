package com.bod.facade;

import com.bod.model.Clientes;
import com.bod.model.Invitaciones;
import com.bod.model.PerfilesNbl;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("invitacionesFacade")
@Stateless(name="maInvitacionesFacade")
public class InvitacionesFacade
  extends AbstractFacade<Invitaciones>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public InvitacionesFacade()
  {
    super(Invitaciones.class);
  }
  
  public Invitaciones porPerfil(PerfilesNbl perfilId)
  {
    TypedQuery<Invitaciones> query = this.em.createNamedQuery("Invitaciones.findByPerfil", Invitaciones.class);
    Invitaciones invitacion;
    try
    {
      invitacion = (Invitaciones)query.setParameter("perfilId", perfilId).getSingleResult();
    }
    catch (Exception e)
    {
      invitacion = null;
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), perfilId.getId().toString(), ("Error Inesperado al invitar {0} en el metodo porPerfil(): " + e).toUpperCase());
    }
    return invitacion;
  }
  
  public List<Invitaciones> porIdentificacionCliente(String identificacion, String tipoIdentificacion, Clientes clienteId)
  {
    TypedQuery<Invitaciones> query = this.em.createNamedQuery("Invitaciones.findByIdentificacionCliente", Invitaciones.class);
    List<Invitaciones> invitaciones;
    try
    {
      invitaciones = query.setParameter("identificacion", identificacion).setParameter("tipoIdentificacion", tipoIdentificacion).setParameter("clienteId", clienteId).getResultList();
    }
    catch (Exception e)
    {
      invitaciones = null;
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), null, ("Error Inesperado en el metodo porIdentificacion(): " + e).toUpperCase());
    }
    return invitaciones;
  }
  
  public List<Invitaciones> porPerfilLista(PerfilesNbl perfilId)
  {
    TypedQuery<Invitaciones> query = null;
    try
    {
      query = this.em.createNamedQuery("Invitaciones.findByPerfil", Invitaciones.class);
      return query.setParameter("perfilId", perfilId).getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), perfilId.getId().toString(), ("Error Inesperado al invitar {0} en el metodo porPerfilLista: " + e).toUpperCase());
    }
    return null;
  }
  
  public List<Invitaciones> porPerfilEstado(PerfilesNbl perfilId, Character estado)
  {
    TypedQuery<Invitaciones> query = this.em.createNamedQuery("Invitaciones.findByPerfilEstado", Invitaciones.class);
    List<Invitaciones> listaInvitaciones;
    try
    {
      listaInvitaciones = query.setParameter("perfilId", perfilId).setParameter("estado", estado).getResultList();
    }
    catch (Exception e)
    {
      listaInvitaciones = null;
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), perfilId.getId().toString(), ("Error Inesperado en el metodo porPerfilEstado(): " + e).toUpperCase());
    }
    return listaInvitaciones;
  }
  
  public Invitaciones porPerfilEstadoSingle(PerfilesNbl perfilId, Character estado)
  {
    TypedQuery<Invitaciones> query = this.em.createNamedQuery("Invitaciones.findByPerfilEstado", Invitaciones.class);
    Invitaciones invitacion;
    try
    {
      invitacion = (Invitaciones)query.setParameter("perfilId", perfilId).setParameter("estado", estado).getSingleResult();
    }
    catch (Exception e)
    {
      invitacion = null;
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), perfilId.getId().toString(), ("Error Inesperado en el metodo porPerfilEstadoSingle(): " + e).toUpperCase());
    }
    return invitacion;
  }
  
  public List<Invitaciones> porIdentificacion(String identificacion, String tipoIdentificacion, Character estado)
  {
    TypedQuery<Invitaciones> query = this.em.createNamedQuery("Invitaciones.findByIdentificacionUnicaEstado", Invitaciones.class);
    List<Invitaciones> invitaciones;
    try
    {
      invitaciones = query.setParameter("identificacion", identificacion).setParameter("tipoIdentificacion", tipoIdentificacion).setParameter("estado", estado).getResultList();
    }
    catch (Exception e)
    {
      invitaciones = null;
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), null, ("Error Inesperado en el metodo porIdentificacion(): " + e).toUpperCase());
    }
    return invitaciones;
  }
  
  public List<Invitaciones> porClienteEstado(Clientes clienteId, Character estado)
  {
    TypedQuery<Invitaciones> query = this.em.createNamedQuery("Invitaciones.findByClienteEstado", Invitaciones.class);
    List<Invitaciones> listaInvitaciones;
    try
    {
      listaInvitaciones = query.setParameter("clienteId", clienteId).setParameter("estado", estado).getResultList();
    }
    catch (Exception e)
    {
      listaInvitaciones = null;
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteId.getId().toString(), ("Error Inesperado en el metodo porClienteEstado(): " + e).toUpperCase());
    }
    return listaInvitaciones;
  }
  
  public List<Invitaciones> porClientePerfilEstado(Clientes clienteId, PerfilesNbl perfilId, Character estado)
  {
    TypedQuery<Invitaciones> query = this.em.createNamedQuery("Invitaciones.findByClientePerfilEstado", Invitaciones.class);
    List<Invitaciones> listaInvitaciones;
    try
    {
      listaInvitaciones = query.setParameter("clienteId", clienteId).setParameter("perfilId", perfilId).setParameter("estado", estado).getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteId.getId().toString(), ("Error Inesperado en el metodo porClientePerfilEstado(): " + e).toUpperCase());
      listaInvitaciones = null;
    }
    return listaInvitaciones;
  }
  
  public List<Invitaciones> porEstado(Character estado)
  {
    TypedQuery<Invitaciones> query = this.em.createNamedQuery("Invitaciones.findByEstado", Invitaciones.class);
    List<Invitaciones> listaInvitaciones;
    try
    {
      listaInvitaciones = query.setParameter("estado", estado).getResultList();
    }
    catch (Exception e)
    {
      listaInvitaciones = null;
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("Error Inesperado en el metodo porEstado(): " + e).toUpperCase());
    }
    return listaInvitaciones;
  }
}
