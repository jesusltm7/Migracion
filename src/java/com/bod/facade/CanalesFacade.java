package com.bod.facade;

import com.bod.model.Canales;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Named("canalesFacade")
@Stateless(name="maCanalesFacade")
public class CanalesFacade
  extends AbstractFacade<Canales>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public CanalesFacade()
  {
    super(Canales.class);
  }
  
  public List<Canales> obtenerTodoOrdenadoPorNombre()
  {
    TypedQuery<Canales> query = null;
    try
    {
      query = this.em.createNamedQuery("Canales.findAllOrderByNombre", Canales.class);
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL canales", "ERROR ENCONTRADO = {0}".toUpperCase());
    }
    return null;
  }
  
  public List<Canales> obtenerTodosCanalesActivos()
  {
    TypedQuery<Canales> query = null;
    try
    {
      query = this.em.createNamedQuery("Canales.findAllByNombreActive", Canales.class);
      return query.getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL canales", "ERROR ENCONTRADO = {0}".toUpperCase());
    }
    return null;
  }
  
  public Canales porCodigo(String codigo)
  {
    try
    {
      return (Canales)getEntityManager().createQuery("select c from Canales c where c.codigo=:codigo").setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), codigo, ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
}
