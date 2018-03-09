package com.bod.facade;

import com.bod.model.Idiomas;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("idiomasFacade")
@Stateless(name="maIdiomasFacade")
public class IdiomasFacade
  extends AbstractFacade<Idiomas>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public IdiomasFacade()
  {
    super(Idiomas.class);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public Idiomas porCodigo(String codigo)
  {
    List<Idiomas> l = null;
    try
    {
      l = getEntityManager().createQuery("select m from Idiomas m where m.codigoIso=:codigo order by m.nombre").setParameter("codigo", codigo).getResultList();
      if (l.size() > 0) {
        return (Idiomas)l.get(0);
      }
      return null;
    }
    catch (Exception ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("Error ENCONTRADO {0} " + ex).toUpperCase());
    }
    return null;
  }
  
  public List<Idiomas> getTodos()
  {
    List<Idiomas> l = null;
    try
    {
      return getEntityManager().createQuery("select m from Idiomas m WHERE m.estatus='A' order by m.orden").getResultList();
    }
    catch (Exception ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("Error Encontrado {0} en el metodo getTodos(): " + ex).toUpperCase());
    }
    return null;
  }
  
  public List<Idiomas> getAllRelease(String codigo)
  {
    List<Idiomas> l = null;
    try
    {
      return getEntityManager().createQuery("select m from Idiomas m where NOT m.codigoIso=:codigo order by m.nombre ").setParameter("codigo", codigo).getResultList();
    }
    catch (Exception ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("Error Encontrado {0} en el metodo getAllRelease(): " + ex).toUpperCase());
    }
    return null;
  }
}
