package com.bod.facade;

import com.bod.model.Monedas;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("monedasFacade")
@Stateless(name="maMonedasFacade")
public class MonedasFacade
  extends AbstractFacade<Monedas>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public MonedasFacade()
  {
    super(Monedas.class);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public Monedas porCodigo(String codigo)
  {
    try
    {
      return (Monedas)this.em.createQuery("select m from Monedas m where m.codigo=:codigo").setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("Error Inesperado en el metodo moneda(): " + ex).toUpperCase());
    }
    return null;
  }
  
  public String estadoPorCodigo(String codigo)
  {
    try
    {
      return (String)this.em.createQuery("select m.estado from Monedas m where m.codigo=:codigo").setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("Error Inesperado en el metodo estadoPorCodigo(): " + ex).toUpperCase());
    }
    return "";
  }
  
  public String nombrePorCodigo(String codigo)
  {
    try
    {
      return (String)this.em.createQuery("select m.nombre from Monedas m where m.codigo=:codigo").setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("Error Inesperado en el metodo nombrePorCodigo(): " + ex).toUpperCase());
    }
    return "";
  }
}
