package com.bod.facade;

import com.bod.model.IBPerfilConfiguracionAcceso;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("perfilConfiguracionAccesoIBFacade")
@Stateless(name="maperfilConfiguracionAccesoIBFacade")
public class IBPerfilConfiguracionAccesoFacade
  extends AbstractFacade<IBPerfilConfiguracionAcceso>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public IBPerfilConfiguracionAccesoFacade()
  {
    super(IBPerfilConfiguracionAcceso.class);
  }
  
  public IBPerfilConfiguracionAccesoFacade(Class<IBPerfilConfiguracionAcceso> entityClass)
  {
    super(entityClass);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public List<IBPerfilConfiguracionAcceso> listarRegistrosMigrar(int numeroCliente, String usuarioAsociado)
  {
    TypedQuery<IBPerfilConfiguracionAcceso> query = null;
    try
    {
      query = this.em.createNamedQuery("IBPerfilConfiguracionAcceso.obtenerRegistrosMigrar", IBPerfilConfiguracionAcceso.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente)).setParameter("usuario", usuarioAsociado);
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("ERROR ENCONTRADO {0}:" + e).toUpperCase());
    }
    return null;
  }
}
