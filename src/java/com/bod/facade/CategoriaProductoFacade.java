package com.bod.facade;

import com.bod.model.CategoriaProductos;
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

@Named("categoriaProductoFacade")
@Stateless(name="maCategoriaProductoFacade")
public class CategoriaProductoFacade
  extends AbstractFacade<CategoriaProductos>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public CategoriaProductoFacade()
  {
    super(CategoriaProductos.class);
  }
  
  public CategoriaProductos porCodigo(String codigo)
  {
    try
    {
      return (CategoriaProductos)this.em.createQuery("select c from CategoriaProductos c where c.codigo=:codigo").setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), codigo, ("ERROR ENCONTRADO = {0}" + ex).toUpperCase());
    }
    return null;
  }
  
  public CategoriaProductos findByNombre(String nombre)
  {
    try
    {
      return (CategoriaProductos)this.em.createNamedQuery("CategoriaProductos.findByNombre", CategoriaProductos.class).setParameter("nombre", nombre).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), nombre, ("ERROR ENCONTRADO = {0}" + ex).toUpperCase());
    }
    return null;
  }
  
  public List<CategoriaProductos> findByEstadoVisibleDirectorio(String estadoCategoria)
  {
    try
    {
      return this.em.createQuery("select c from CategoriaProductos c where c.estadoCategoria=:estadoCategoria", CategoriaProductos.class).setParameter("estadoCategoria", estadoCategoria).getResultList();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), estadoCategoria, ("ERROR ENCONTRADO = {0}" + ex).toUpperCase());
    }
    return null;
  }
}
