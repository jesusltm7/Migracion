package com.bod.facade;

import com.bod.model.IBCabeceraFlujo;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("flujoAprobacionCabeceraIBFacade")
@Stateless(name="maflujoAprobacionCabeceraIBFacade")
public class IBFlujoAprobacionCabeceraFacade
  extends AbstractFacade<IBCabeceraFlujo>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public IBFlujoAprobacionCabeceraFacade()
  {
    super(IBCabeceraFlujo.class);
  }
  
  public IBFlujoAprobacionCabeceraFacade(Class<IBCabeceraFlujo> entityClass)
  {
    super(entityClass);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public List<IBCabeceraFlujo> listarRegistrosMigrar(int numeroCliente)
  {
    try
    {
      TypedQuery<IBCabeceraFlujo> query = this.em.createNamedQuery("IBCabeceraFlujo.findAllByNumeroCliente", IBCabeceraFlujo.class).setParameter("numeroCliente", Integer.valueOf(numeroCliente));
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo listarRegistrosMigrar(int numeroCliente) = {0}", e);
    }
    return null;
  }
}
