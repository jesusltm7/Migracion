package com.bod.facade;

import com.bod.model.IBDetalleFlujo;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("flujoAprobacionDetalleIBFacade")
@Stateless(name="maflujoAprobacionDetalleIBFacade")
public class IBFlujoAprobacionDetalleFacade
  extends AbstractFacade<IBDetalleFlujo>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public IBFlujoAprobacionDetalleFacade()
  {
    super(IBDetalleFlujo.class);
  }
  
  public IBFlujoAprobacionDetalleFacade(Class<IBDetalleFlujo> entityClass)
  {
    super(entityClass);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public List<IBDetalleFlujo> listarRegistrosAsociados(int numeroCliente, String tipoOperacion, int consecutivoDetalle)
  {
    TypedQuery<IBDetalleFlujo> query = null;
    try
    {
      query = this.em.createNamedQuery("IBDetalleFlujo.consultarRegistrosAsociados", IBDetalleFlujo.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente)).setParameter("tipoOperacion", tipoOperacion).setParameter("consecutivoDetalle", Integer.valueOf(consecutivoDetalle));
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("ERROR ENCONTRADO {0}: " + e).toUpperCase());
    }
    return null;
  }
}
