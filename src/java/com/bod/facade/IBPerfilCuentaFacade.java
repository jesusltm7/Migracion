package com.bod.facade;

import com.bod.model.IBPerfilCuentas;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("perfilCuentasIBFacade")
@Stateless(name="maperfilCuentasIBFacade")
public class IBPerfilCuentaFacade
  extends AbstractFacade<IBPerfilCuentas>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public IBPerfilCuentaFacade()
  {
    super(IBPerfilCuentas.class);
  }
  
  public IBPerfilCuentaFacade(Class<IBPerfilCuentas> entityClass)
  {
    super(entityClass);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public List<IBPerfilCuentas> listarRegistrosMigrar(int numeroCliente, String usuarioAsociado)
  {
    TypedQuery<IBPerfilCuentas> query = null;
    try
    {
      query = this.em.createNamedQuery("IBPerfilCuentas.obtenerRegistrosMigrar", IBPerfilCuentas.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente)).setParameter("usuario", usuarioAsociado);
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("ERROR ENCONTRADO {0} :" + e).toUpperCase());
    }
    return null;
  }
  
  public IBPerfilCuentas cargarPermisosProducto(int numeroCliente, String usuarioAsociado, String producto)
  {
    BigDecimal productoProceso = new BigDecimal(producto);
    TypedQuery<IBPerfilCuentas> query = null;
    try
    {
      query = this.em.createNamedQuery("IBPerfilCuentas.cargarPermisosProducto", IBPerfilCuentas.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente)).setParameter("usuario", usuarioAsociado).setParameter("numeroProducto", productoProceso);
      List<IBPerfilCuentas> opciones = query.getResultList();
      return !opciones.isEmpty() ? (IBPerfilCuentas)opciones.get(0) : null;
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("ERROR ENCONTRADO {0}:" + e).toUpperCase());
    }
    return null;
  }
}
