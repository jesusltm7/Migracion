package com.bod.facade;

import com.bod.model.IBAutorizacionesSIP;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("autorizacionesSIPIBFacade")
@Stateless(name="maautorizacionesSIPIBFacade")
public class IBAutorizacionesSIPFacade
  extends AbstractFacade<IBAutorizacionesSIP>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public IBAutorizacionesSIPFacade()
  {
    super(IBAutorizacionesSIP.class);
  }
  
  public IBAutorizacionesSIPFacade(Class<IBAutorizacionesSIP> entityClass)
  {
    super(entityClass);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public List<IBAutorizacionesSIP> obtenerNivelesAutorizacion(int numeroCliente, String usuarioAsociado)
  {
    try
    {
      TypedQuery<IBAutorizacionesSIP> query = this.em.createNamedQuery("IBAutorizacionesSIP.findAllByClienteANDUsuario", IBAutorizacionesSIP.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente)).setParameter("usuarioAsociado", usuarioAsociado);
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo obtenerNivelesAutorizacion(int numeroCliente, String usuarioAsociado) = {0}", e);
    }
    return null;
  }
  
  public List<IBAutorizacionesSIP> obtenerNivelesAutorizacionPorClientes(int numeroCliente)
  {
    try
    {
      TypedQuery<IBAutorizacionesSIP> query = this.em.createNamedQuery("IBAutorizacionesSIP.finByCliente", IBAutorizacionesSIP.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente));
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo obtenerNivelesAutorizacion(int numeroCliente, String usuarioAsociado) = {0}", e);
    }
    return null;
  }
  
  public IBAutorizacionesSIP porTipoContratoNumeroClienteNombreUsuario(int numeroCliente, String codigoTipoContrato, String usuarioAsociado)
  {
    try
    {
      TypedQuery<IBAutorizacionesSIP> query = this.em.createNamedQuery("IBAutorizacionesSIP.finByClienteTipoContratoNombreUsuario", IBAutorizacionesSIP.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente)).setParameter("codigoTipoContrato", codigoTipoContrato).setParameter("usuarioAsociado", usuarioAsociado);
      
      return (IBAutorizacionesSIP)query.getSingleResult();
    }
    catch (NoResultException nr)
    {
      return null;
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo porTipoContratoNumeroClienteNombreUsuario(int numeroCliente, String codigoTipoContrato, String usuarioAsociado) = {0}", e);
    }
    return null;
  }
}
