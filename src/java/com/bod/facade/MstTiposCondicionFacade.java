package com.bod.facade;

import com.bod.model.Msttiposcondicion;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("mstTiposCondicionFacade")
@Stateless(name="maMstTiposCondicionFacade")
public class MstTiposCondicionFacade
  extends AbstractFacade<Msttiposcondicion>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public MstTiposCondicionFacade()
  {
    super(Msttiposcondicion.class);
  }
  
  public Msttiposcondicion findByCode(String codcondicion)
  {
    try
    {
      return (Msttiposcondicion)this.em.createNamedQuery("Msttiposcondicion.findByCodcondicion", Msttiposcondicion.class).setParameter("codcondicion", codcondicion).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Advertencia, Registro no encontrado por Facade findByCode(String codcondicion)", ex);
    }
    return null;
  }
}
