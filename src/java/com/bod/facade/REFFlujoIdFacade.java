package com.bod.facade;

import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name="maREFFlujoIdFacade")
public class REFFlujoIdFacade
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public String generarREFFlujoId()
  {
    try
    {
      return this.em.createNativeQuery("select CNFFLUJOS_SEQ.nextval from dual").getSingleResult().toString();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo generarREFFlujoId() = ", e);
    }
    return null;
  }
  
  public String generarREFOrdenFlujoId()
  {
    try
    {
      return this.em.createNativeQuery("select MSTORDENFLUJO_SEQ.nextval from dual").getSingleResult().toString();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo generarREFOrdenFlujoId() = ", e);
    }
    return null;
  }
  
  public String generarREFFlujoProcesoId()
  {
    try
    {
      return this.em.createNativeQuery("select DETFLUJOPROCESO_SEQ.nextval from dual").getSingleResult().toString();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el m�todo generarREFFlujoProcesoId() = ", e);
    }
    return null;
  }
}
