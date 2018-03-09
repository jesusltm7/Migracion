package com.bod.facade;

import com.bod.model.Clientes;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("clientesFacade")
@Stateless(name="maClientesFacade")
public class ClientesFacade
  extends AbstractFacade<Clientes>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public ClientesFacade()
  {
    super(Clientes.class);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public Clientes porCedula(String tipo, String cedula)
  {
    try
    {
      return (Clientes)getEntityManager().createQuery("select c from Clientes c where c.tipoIdentificacion=:tipo and c.identificacion=:identificacion").setParameter("tipo", tipo).setParameter("identificacion", cedula).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), tipo + cedula, ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public Clientes porId(Long Id)
  {
    Clientes result = null;
    try
    {
      return (Clientes)getEntityManager().createNamedQuery("Clientes.findById").setParameter("id", Id).getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL clientes", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public Clientes finds(Long id)
  {
    try
    {
      return (Clientes)getEntityManager().createNamedQuery("Clientes.findById").setParameter("id", id).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), ClientesFacade.class.getName(), ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public Long getSequence()
  {
    try
    {
      return Long.valueOf(((BigDecimal)getEntityManager().createNativeQuery("select CLIENTES_SEQ.nextval from dual").getSingleResult()).longValue());
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL clientes", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public String getSharedKeySequence()
  {
    try
    {
      return getEntityManager().createNativeQuery("select SHAREDKEY_SEQ.nextval from dual").getSingleResult().toString();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL clientesBankin", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
}
