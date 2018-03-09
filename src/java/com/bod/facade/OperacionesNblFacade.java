package com.bod.facade;

import com.bod.model.Mstopciones;
import com.bod.model.OperacionesNbl;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Named("operacionesNblFacade")
@Stateless(name="maOperacionesNblFacade")
public class OperacionesNblFacade
  extends AbstractFacade<OperacionesNbl>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public OperacionesNblFacade()
  {
    super(OperacionesNbl.class);
  }
  
  public OperacionesNbl porCodigo(String codigo)
  {
    OperacionesNbl objeto = null;
    try
    {
      objeto = (OperacionesNbl)getEntityManager().createQuery("select o from OperacionesNbl o where o.codigo=:codigo").setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porCodigo(String codigo) = " + e);
    }
    return objeto;
  }
  
  public List<OperacionesNbl> listaPorCodigo(String codigo)
  {
    List<OperacionesNbl> objeto = null;
    try
    {
      objeto = getEntityManager().createQuery("select o from OperacionesNbl o where o.codigo=:codigo").setParameter("codigo", codigo).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo listaPorCodigo(String codigo) = " + e);
    }
    return objeto;
  }
  
  public List<OperacionesNbl> operacionesPorPrefijoSufijo(String prefix, String sufix)
  {
    try
    {
      return getEntityManager().createQuery("select o from OperacionesNbl o where o.codigo LIKE :prefix ").setParameter("prefix", prefix + '%' + sufix).getResultList();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo operacionesPorPrefijoSufijo(String prefix, String sufix) = " + ex);
    }
    return null;
  }
  
  public OperacionesNbl porId(Long id)
  {
    OperacionesNbl objeto = null;
    try
    {
      objeto = (OperacionesNbl)getEntityManager().createQuery("select o from OperacionesNbl o where o.id = :id").setParameter("id", id).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porId(Long id) = " + e);
    }
    return objeto;
  }
  
  public Long idPorCodigo(String codigo)
  {
    Long id = null;
    try
    {
      id = (Long)getEntityManager().createQuery("select o.id from OperacionesNbl o where o.codigo = :codigo").setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo idPorCodigo(String codigo) = " + e);
    }
    return id;
  }
  
  public List<OperacionesNbl> obtenerTodoOrdenadoPorNombre()
  {
    try
    {
      TypedQuery<OperacionesNbl> query = this.em.createNamedQuery("OperacionesNbl.findAllOrderByNombre", OperacionesNbl.class);
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo obtenerTodoOrdenadoPorNombre() = " + e);
    }
    return null;
  }
  
  public List<OperacionesNbl> porOpcion(Mstopciones fkIdopcion)
  {
    List<OperacionesNbl> listaOperacionesNbl;
    try
    {
      listaOperacionesNbl = getEntityManager().createNamedQuery("OperacionesNbl.findByOpcion", OperacionesNbl.class).setParameter("fkIdopcion", fkIdopcion).getResultList();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porOpcion(Mstopciones fkIdopcion) = " + ex);
      listaOperacionesNbl = null;
    }
    return listaOperacionesNbl;
  }
  
  public OperacionesNbl porNombre(String nombre)
  {
    OperacionesNbl OperacionNbl;
    try
    {
      OperacionNbl = (OperacionesNbl)getEntityManager().createNamedQuery("OperacionesNbl.findByNombre", OperacionesNbl.class).setParameter("nombre", nombre).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombre(String nombre) = " + ex);
      OperacionNbl = null;
    }
    return OperacionNbl;
  }
  
  public List<OperacionesNbl> porBase(Long base)
  {
    List<OperacionesNbl> listaOperacionesBaseNbl;
    try
    {
      listaOperacionesBaseNbl = getEntityManager().createNamedQuery("OperacionesNbl.findByBase", OperacionesNbl.class).setParameter("base", base).getResultList();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porBase(Long base) = " + ex);
      listaOperacionesBaseNbl = null;
    }
    return listaOperacionesBaseNbl;
  }
  
  public List<OperacionesNbl> obtenerTodoOrdenadoPorNombreActivas()
  {
    try
    {
      TypedQuery<OperacionesNbl> query = this.em.createNamedQuery("OperacionesNbl.findAllOrderByNombreActivas", OperacionesNbl.class);
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo obtenerTodoOrdenadoPorNombreActivas() = " + e);
    }
    return null;
  }
  
  public List<OperacionesNbl> porTipo(String codigoTipo)
  {
    try
    {
      return getEntityManager().createQuery("select o from OperacionesNbl o where o.tipoOperacionNblId.codigo=:codigo").setParameter("codigo", codigoTipo).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porTipo(String codigoTipo) = " + e);
    }
    return null;
  }
  
  public List<OperacionesNbl> financieras()
  {
    String codigoTipo = "fin";
    Character notificacion = Character.valueOf('S');
    List<OperacionesNbl> l;
    try
    {
      l = getEntityManager().createQuery("select o from OperacionesNbl o where o.tipoOperacionNblId.codigo=:codigo AND o.enviaNotificaciones=:notificacion").setParameter("codigo", codigoTipo).setParameter("notificacion", notificacion).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo financieras() = " + e);
      return null;
    }
    return l;
  }
  
  public List<OperacionesNbl> noFinancieras(String codigoTipo)
  {
    Character notificacion = Character.valueOf('S');
    try
    {
      return getEntityManager().createQuery("select o from OperacionesNbl o where  o.tipoOperacionNblId.codigo!=:codigo AND o.enviaNotificaciones=:notificacion").setParameter("codigo", codigoTipo).setParameter("notificacion", notificacion).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo noFinancieras(String codigoTipo) = " + e);
    }
    return null;
  }
  
  public List<OperacionesNbl> obtenerModulosInicio()
  {
    try
    {
      TypedQuery<OperacionesNbl> query = this.em.createNamedQuery("OperacionesNbl.findAllByUrlSmall", OperacionesNbl.class);
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo obtenerModulosInicio() = " + e);
    }
    return null;
  }
  
  public String obtenerDireccionPrincipal(String funcionalidad, String directorio, String permiso)
  {
    String url = permiso;
    OperacionesNbl operacion = porCodigo(funcionalidad);
    if ((operacion != null) && (operacion.getUrl() != null)) {
      url = operacion.getUrl();
    } else if ((operacion == null) || (operacion.getUrl() == null)) {
      url = directorio;
    }
    return url;
  }
  
  public List<OperacionesNbl> obtenerporidOperacionIB(Long idOperacionib)
  {
    try
    {
      TypedQuery query = this.em.createQuery("SELECT o FROM OperacionesNbl o,Detoperacionesib d \nWHERE d.fkIdoperacionNbl = o.id and d.fkIdoperacionib = :idOperacionib", OperacionesNbl.class);
      
      query.setParameter("idOperacionib", idOperacionib);
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo obtenerporidOperacionIB(Long idOperacionib) = " + e);
    }
    return null;
  }
  
  public List<OperacionesNbl> listarTiposOperacion(ArrayList<String> listaCodigos)
  {
    try
    {
      TypedQuery<OperacionesNbl> query = this.em.createNamedQuery("OperacionesNbl.findAllByListaCodigo", OperacionesNbl.class).setParameter("listaCodigo", listaCodigos);
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo listarTiposOperacion(ArrayList<String> listaCodigos) = " + e);
    }
    return null;
  }
  
  public List<OperacionesNbl> obtenerOperaciones(ArrayList<Integer> listaLlaves)
  {
    List<Long> listaLlavesPrimarias = new ArrayList();
    for (Integer llave : listaLlaves) {
      listaLlavesPrimarias.add(Long.valueOf(llave.longValue()));
    }
    try
    {
      TypedQuery<OperacionesNbl> query = this.em.createNamedQuery("OperacionesNbl.findAllByListaId", OperacionesNbl.class).setParameter("listaId", listaLlavesPrimarias);
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo obtenerOperaciones(ArrayList<Integer> listaLlaves) = " + e);
    }
    return null;
  }
}
