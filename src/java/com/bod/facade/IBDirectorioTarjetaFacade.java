package com.bod.facade;

import com.bod.model.IBDirectorioTarjeta;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("directorioTarjetaIBFacade")
@Stateless(name="madirectorioTarjetaIBFacade")
public class IBDirectorioTarjetaFacade
  extends AbstractFacade<IBDirectorioTarjeta>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public IBDirectorioTarjetaFacade()
  {
    super(IBDirectorioTarjeta.class);
  }
  
  public IBDirectorioTarjetaFacade(Class<IBDirectorioTarjeta> entityClass)
  {
    super(entityClass);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public List<IBDirectorioTarjeta> listarPorNumeroCliente(int numeroCliente)
  {
    try
    {
      TypedQuery<IBDirectorioTarjeta> query = this.em.createNamedQuery("IBDirectorioTarjeta.findAllByNumeroCliente", IBDirectorioTarjeta.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente));
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo listarPorNumeroCliente(int numeroCliente) = {0}", e);
    }
    return null;
  }
  
  public List<IBDirectorioTarjeta> listarProductosMigrar(int numeroCliente)
  {
    try
    {
      TypedQuery<IBDirectorioTarjeta> query = this.em.createNamedQuery("IBDirectorioTarjeta.obtenerRegistrosMigrar", IBDirectorioTarjeta.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente));
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo listarPorNumeroCliente(int numeroCliente) = {0}", e);
    }
    return null;
  }
}
