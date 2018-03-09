package com.bod.facade;

import com.bod.model.Mstmedioenvio;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("mstmedioenvioFacade")
@Stateless(name="maMstmedioenvioFacade")
public class MstmedioenvioFacade
  extends AbstractFacade<Mstmedioenvio>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public MstmedioenvioFacade()
  {
    super(Mstmedioenvio.class);
  }
  
  public Mstmedioenvio porCodigo(int cod)
  {
    Mstmedioenvio objeto = null;
    try
    {
      return (Mstmedioenvio)getEntityManager().createQuery("select m from Mstmedioenvio m where m.codmedio=:codigo").setParameter("codigo", Integer.valueOf(cod)).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("ERROR CONSULTANDO Medio de Envio {0} metodo porCodigo()" + e).toUpperCase());
    }
    return null;
  }
}
