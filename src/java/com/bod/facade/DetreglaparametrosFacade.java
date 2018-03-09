package com.bod.facade;

import com.bod.model.Detreglaparametros;
import com.bod.model.Mstreglasperfil;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name="maDetreglaparametrosFacade")
public class DetreglaparametrosFacade
  extends AbstractFacade<Detreglaparametros>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public DetreglaparametrosFacade()
  {
    super(Detreglaparametros.class);
  }
  
  public Detreglaparametros porId(long id)
  {
    try
    {
      return (Detreglaparametros)getEntityManager().createQuery("select u from Detreglaparametros u where u.pkReglaparametro=:id").setParameter("id", Long.valueOf(id)).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar el m�todo porId(long id)" + e);
    }
    return null;
  }
  
  public List<Detreglaparametros> porParametro(String codigo)
  {
    try
    {
      return getEntityManager().createQuery("select u from detReglaParametros u where u.fkIdparametro.codigo=:codigo").setParameter("codigo", codigo).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL" + codigo, "Error al consultar el m�todo porParametro(String codigo)" + e);
    }
    return null;
  }
  
  public List<Detreglaparametros> porValParametroIdParametro(String valparametro, String codigo)
  {
    try
    {
      return getEntityManager().createQuery("SELECT d FROM Detreglaparametros d WHERE d.valparametro = :valparametro AND d.fkIdparametro.codigo = :codigo").setParameter("valparametro", valparametro).setParameter("codigo", codigo).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), valparametro + codigo, "Error al consultar el m�todo porValParametroIdParametro(String valparametro, String codigo)" + e);
    }
    return null;
  }
  
  public Detreglaparametros porReglaParametro(Mstreglasperfil fkIdreglaperfil, String codigo)
  {
    try
    {
      return (Detreglaparametros)getEntityManager().createQuery("SELECT d FROM Detreglaparametros d WHERE d.fkIdreglaperfil = :fkIdreglaperfil AND d.fkIdparametro.codigo = :codigo ORDER BY d.fkIdparametro.codigo ASC ").setParameter("fkIdreglaperfil", fkIdreglaperfil).setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), fkIdreglaperfil.getCodreglaperfil() + codigo, "Error al consultar el m�todo porReglaParametro(Mstreglasperfil fkIdreglaperfil, String codigo)" + e);
    }
    return null;
  }
  
  public List<Detreglaparametros> porRegla(Mstreglasperfil reglaid)
  {
    try
    {
      return getEntityManager().createQuery("select d from Detreglaparametros d where d.fkIdreglaperfil=:reglaid").setParameter("reglaid", reglaid).getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL" + reglaid.getCodreglaperfil(), "Error al consultar el m�todo porRegla(Mstreglasperfil reglaid)" + e);
    }
    return null;
  }
}
