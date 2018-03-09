package com.bod.facade;

import com.bod.model.ClienteInternetBanking;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("clientesInternetBankingFacade")
@Stateless(name="maClientesInternetBankingFacade")
public class ClientesInternetBankingFacade
  extends AbstractFacade<ClienteInternetBanking>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public ClientesInternetBankingFacade()
  {
    super(ClienteInternetBanking.class);
  }
  
  public ClientesInternetBankingFacade(Class<ClienteInternetBanking> entityClass)
  {
    super(entityClass);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public List<ClienteInternetBanking> listarPorTipoIdYNumero(String tipoIdentificacion, String numeroIdentificacion)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findAllByTipoIdAndIdentificacion", ClienteInternetBanking.class).setParameter("tipoId", tipoIdentificacion).setParameter("numeroIdentificacion", numeroIdentificacion);
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), tipoIdentificacion + numeroIdentificacion, ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClienteInternetBanking> listarAprobadosPorTipoIdYNumero(String tipoIdentificacion, String numeroIdentificacion)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findAprobadosByTipoIdAndIdentificacion", ClienteInternetBanking.class).setParameter("tipoId", tipoIdentificacion).setParameter("numeroIdentificacion", numeroIdentificacion);
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), tipoIdentificacion + numeroIdentificacion, ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClienteInternetBanking> listarPendientesPorTipoIdYNumero(String tipoIdentificacion, String numeroIdentificacion)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findPendientesByTipoIdAndIdentificacion", ClienteInternetBanking.class).setParameter("tipoId", tipoIdentificacion).setParameter("numeroIdentificacion", numeroIdentificacion);
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), tipoIdentificacion + numeroIdentificacion, "ERROR ENCONTRADO = {0}" + e);
    }
    return null;
  }
  
  public List<ClienteInternetBanking> listarPorNumeroCliente(int numeroCliente)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findAllByNumeroCliente", ClienteInternetBanking.class).setParameter("clienteID", Integer.valueOf(numeroCliente));
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL clientesinter", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClienteInternetBanking> listarPorNumeroCliente(int numeroCliente, String tipoUsuario)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findAllByNumeroCliente", ClienteInternetBanking.class).setParameter("clienteID", Integer.valueOf(numeroCliente));
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), ClientesInternetBankingFacade.class.getName(), ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClienteInternetBanking> listarUsuariosMaster(int numeroCliente)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findAllByNumeroClienteAndAutorizacion", ClienteInternetBanking.class).setParameter("clienteID", Integer.valueOf(numeroCliente)).setParameter("nivelAutorizacion", "MA");
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), ClientesInternetBankingFacade.class.getName(), ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClienteInternetBanking> listarUsuariosMasterActivos(String nivelAutorizacion, String estadoMigracion)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findAllByAutorizacionAndEstado", ClienteInternetBanking.class).setParameter("nivelAutorizacion", nivelAutorizacion).setParameter("estadoMigracion", estadoMigracion);
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), ClientesInternetBankingFacade.class.getName(), ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClienteInternetBanking> listarUsuariosMaster(String tipoId, String identificacion)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findAllByIdentificacionAndAutorizacion", ClienteInternetBanking.class).setParameter("tipoID", tipoId).setParameter("numeroID", identificacion).setParameter("nivelAutorizacion", "MA");
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), ClientesInternetBankingFacade.class.getName(), ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClienteInternetBanking> listarUsuariosSecundarios(int numeroCliente)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findAllByNumeroClienteAndAutorizacion", ClienteInternetBanking.class).setParameter("clienteID", Integer.valueOf(numeroCliente)).setParameter("nivelAutorizacion", "CO");
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), ClientesInternetBankingFacade.class.getName(), ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public ClienteInternetBanking porIdentificacionNumeroCliente(String tipoIdentificacion, String numeroIdentificacion, int numeroCliente)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findByClienteIdentificacion", ClienteInternetBanking.class).setParameter("tipoIdentificacion", tipoIdentificacion).setParameter("numeroIdentificacion", numeroIdentificacion).setParameter("numeroCliente", Integer.valueOf(numeroCliente));
      return (ClienteInternetBanking)query.getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), ClientesInternetBankingFacade.class.getName(), ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public ClienteInternetBanking porClienteNombreUsuario(int numeroCliente, String login, String estadoMigracion)
  {
    TypedQuery<ClienteInternetBanking> query = null;
    try
    {
      query = this.em.createNamedQuery("ClienteInternetBanking.findAllByNumeroClienteNombreUsuario", ClienteInternetBanking.class).setParameter("clienteID", Integer.valueOf(numeroCliente)).setParameter("login", login).setParameter("estadoMigracion", estadoMigracion);
      return (ClienteInternetBanking)query.getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), ClientesInternetBankingFacade.class.getName(), ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
}
