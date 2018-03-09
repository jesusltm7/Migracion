package com.bod.facade;

import com.bod.model.Detlimites;
import com.bod.model.OperacionesNbl;
import com.bod.model.PerfilesNbl;
import com.bod.model.Productos;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("detLimitesFacade")
@Stateless(name="maDetLimitesFacade")
public class DetLimitesFacade
  extends AbstractFacade<Detlimites>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public DetLimitesFacade()
  {
    super(Detlimites.class);
  }
  
  public Detlimites porCategoriaProducto(PerfilesNbl fkIdperfil, Productos fkIdproducto)
  {
    Detlimites objeto = null;
    try
    {
      objeto = (Detlimites)getEntityManager().createNamedQuery("Detlimites.findByPerfilesProductos").setParameter("fkIdperfil", fkIdperfil).setParameter("fkIdproducto", fkIdproducto).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), fkIdproducto.getAlias(), "Error ejecutando consulta porCategoriaProducto(PerfilesNbl fkIdperfil, Productos fkIdproducto) = " + e);
    }
    return objeto;
  }
  
  public Detlimites porProductoPerfilyOperacion(PerfilesNbl fkIdperfil, Productos fkIdproducto, OperacionesNbl fkIdoperacion)
  {
    Detlimites objeto = null;
    try
    {
      objeto = (Detlimites)getEntityManager().createNamedQuery("Detlimites.findByProductoPerfilyOperacion").setParameter("fkIdperfil", fkIdperfil).setParameter("fkIdproducto", fkIdproducto).setParameter("fkIdoperacion", fkIdoperacion).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "nbl", "Error ejecutando consulta porProductoPerfilyOperacion(PerfilesNbl fkIdperfil, Productos fkIdproducto,OperacionesNbl fkIdoperacion) = " + e);
    }
    return objeto;
  }
  
  public List<Detlimites> porPerfilProducto(PerfilesNbl fkIdperfil, Productos fkIdproducto)
  {
    List<Detlimites> objeto = null;
    try
    {
      objeto = getEntityManager().createNamedQuery("Detlimites.findByPerfilesProductos").setParameter("fkIdperfil", fkIdperfil).setParameter("fkIdproducto", fkIdproducto).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), fkIdproducto.getNumero() + fkIdperfil.getDescripcion(), "Error ejecutando consulta porPerfilProducto(PerfilesNbl fkIdperfil, Productos fkIdproducto) = " + e);
    }
    return objeto;
  }
  
  public List<Detlimites> porPerfilOperacion(PerfilesNbl fkIdperfil, OperacionesNbl fkIdoperacion)
  {
    List<Detlimites> objeto = null;
    try
    {
      objeto = getEntityManager().createNamedQuery("Detlimites.findByPerfilOperacion").setParameter("fkIdperfil", fkIdperfil).setParameter("fkIdoperacion", fkIdoperacion).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), fkIdoperacion.getNombre() + fkIdoperacion.getCodigo(), "Error ejecutando consulta porPerfilOperacion(PerfilesNbl fkIdperfil, OperacionesNbl fkIdoperacion) = " + e);
    }
    return objeto;
  }
  
  public List<Detlimites> porPerfil(PerfilesNbl fkIdperfil)
  {
    List<Detlimites> objeto = null;
    try
    {
      objeto = getEntityManager().createNamedQuery("Detlimites.findByPerfil").setParameter("fkIdperfil", fkIdperfil).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), fkIdperfil.getDescripcion(), "Error ejecutando consulta porPerfil(PerfilesNbl fkIdperfil) = " + e);
    }
    return objeto;
  }
}
