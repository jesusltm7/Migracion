package com.bod.facade;

import com.bod.model.Paises;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("paisesFacade")
@Stateless(name="maPaisesFacade")
public class PaisesFacade
  extends AbstractFacade<Paises>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public PaisesFacade()
  {
    super(Paises.class);
  }
  
  public List<Paises> todos()
  {
    try
    {
      return getEntityManager().createQuery("select p from Paises p order by p.nombre asc").getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo todos() = ", e);
    }
    return null;
  }
  
  public List<Paises> todasNacionalidades()
  {
    try
    {
      return getEntityManager().createQuery("select p from Paises p order by p.nacionalidadMasculino asc").getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo todasNacionalidades() = ", e);
    }
    return null;
  }
  
  public Paises findByCodigoCore(String codigoCore)
  {
    try
    {
      return (Paises)getEntityManager().createNamedQuery("Paises.findByCodigoCore").setParameter("codigoCore", codigoCore).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo findByCodigoCore(String codigoCore) = ", e);
    }
    return null;
  }
  
  public Paises findByCodigoCoreWithoutExcCntrlFlow(String codigoCore)
  {
    try
    {
      return (Paises)getEntityManager().createNamedQuery("Paises.findByCodigoCore").setParameter("codigoCore", codigoCore).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo findByCodigoCoreWithoutExcCntrlFlow(String codigoCore) = ", e);
    }
    return null;
  }
}
