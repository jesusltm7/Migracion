package com.bod.facade;

import com.bod.model.IBDirectorioCuenta;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("directorioCuentaIBFacade")
@Stateless(name="madirectorioCuentaIBFacade")
public class IBDirectorioCuentaFacade
  extends AbstractFacade<IBDirectorioCuenta>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public IBDirectorioCuentaFacade()
  {
    super(IBDirectorioCuenta.class);
  }
  
  public IBDirectorioCuentaFacade(Class<IBDirectorioCuenta> entityClass)
  {
    super(entityClass);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public List<IBDirectorioCuenta> listarPorNumeroCliente(int numeroCliente)
  {
    try
    {
      TypedQuery<IBDirectorioCuenta> query = this.em.createNamedQuery("IBDirectorioCuenta.findAllByNumeroCliente", IBDirectorioCuenta.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente));
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo listarPorNumeroCliente(int numeroCliente) = {0}", e);
    }
    return null;
  }
  
  public List<IBDirectorioCuenta> listrarRegistrosMigrar(int numeroCliente)
  {
    try
    {
      TypedQuery<IBDirectorioCuenta> query = this.em.createNamedQuery("IBDirectorioCuenta.obtenerRegistrosMigrar", IBDirectorioCuenta.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente));
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo listrarRegistrosMigrar(int numeroCliente) = {0}", e);
    }
    return null;
  }
}
