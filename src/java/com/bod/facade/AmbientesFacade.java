package com.bod.facade;

import com.bod.model.Ambientes;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name="maAmbientesFacade")
public class AmbientesFacade
  extends AbstractFacade<Ambientes>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public AmbientesFacade()
  {
    super(Ambientes.class);
  }
  
  public Ambientes porCodigo(String codigo)
  {
    List<Ambientes> l = null;
    try
    {
      l = getEntityManager().createQuery("select o from Ambientes o where o.codigo=:codigo").setParameter("codigo", codigo).getResultList();
      
      return (Ambientes)l.get(0);
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), AmbientesFacade.class.getName(), "ERRORENCONTRADO".toUpperCase());
    }
    return null;
  }
}
