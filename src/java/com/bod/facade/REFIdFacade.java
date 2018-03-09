package com.bod.facade;

import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name="maREFIdFacade")
public class REFIdFacade
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public String generarREFId()
  {
    try
    {
      return this.em.createNativeQuery("select REFID_SEQ.nextval from dual").getSingleResult().toString();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo generarREFFlujoProcesoId() = ", e);
    }
    return null;
  }
}
