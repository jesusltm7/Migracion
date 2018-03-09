package com.bod.facade;

import com.bod.beans.BeanServicios;
import com.bod.model.CategoriaProductos;
import com.bod.model.Productos;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("productosFacade")
@Stateless(name="maProductosFacade")
public class ProductosFacade
  extends AbstractFacade<Productos>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public ProductosFacade()
  {
    super(Productos.class);
  }
  
  public Productos loadByNumero(String numero)
  {
    try
    {
      Productos producto = (Productos)this.em.createNamedQuery("Productos.findByNumero").setParameter("numero", numero).getSingleResult();
      if (null == producto)
      {
        Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar el producto ''{0}''.  No se consiguio el objeto apropiado. " + numero);
        return null;
      }
      return producto;
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar el producto '" + numero + "'.  No se obtuvieron resultados.", e);
      return null;
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar el producto '" + numero + "'.  Error indeterminado.", e);
    }
    return null;
  }
  
  public Productos findByNumeroGlobalId(String numero, Integer id)
  {
    try
    {
      Productos producto = (Productos)this.em.createNamedQuery("Productos.findByNumeroDirGlobal").setParameter("numero", numero).setParameter("idDirGlobal", id).getSingleResult();
      if (null == producto)
      {
        Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar el producto ''{0}''.  No se consiguio el objeto apropiado." + numero);
        return null;
      }
      return producto;
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar el producto '" + numero + "'.  No se obtuvieron resultados.", e);
      return null;
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar el producto '" + numero + "'.  Error indeterminado.", e);
    }
    return null;
  }
  
  public Productos getByID(Long id)
  {
    try
    {
      return (Productos)this.em.createNamedQuery("Productos.findById").setParameter("id", id).getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar Productos'" + id + "'.", e);
    }
    return null;
  }
  
  public BeanServicios getByIDSoloServicio(Long id)
  {
    try
    {
      return (BeanServicios)this.em.createQuery("SELECT p.servicio FROM Productos p WHERE p.id = :id").setParameter("id", id).getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar Productos'" + id + "'.", e);
    }
    return null;
  }
  
  public CategoriaProductos getByIDSoloCategoria(Long id)
  {
    try
    {
      return (CategoriaProductos)this.em.createQuery("SELECT p.categoriaProductosId FROM Productos p WHERE p.id = :id").setParameter("id", id).getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar Productos'" + id + "'.", e);
    }
    return null;
  }
  
  public Productos getByNumero(String numero)
  {
    try
    {
      return (Productos)this.em.createNamedQuery("Productos.findByNumero").setParameter("numero", numero).getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar Productos'" + numero + "'.", e);
    }
    return null;
  }
  
  public Long getByNumeroSoloID(String numero)
  {
    try
    {
      return (Long)this.em.createQuery("SELECT p.id FROM Productos p WHERE p.numero = :numero").setParameter("numero", numero).getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error al consultar Productos'" + numero + "'.", e);
    }
    return null;
  }
}
