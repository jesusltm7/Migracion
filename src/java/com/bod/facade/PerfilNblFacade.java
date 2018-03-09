package com.bod.facade;

import com.bod.model.Clientes;
import com.bod.model.PerfilesNbl;
import com.bod.model.TipoPerfilesNbl;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Named("PerfilNblFacade")
@Stateless(name="maPerfilNblFacade")
public class PerfilNblFacade
  extends AbstractFacade<PerfilesNbl>
{
  private static final Logger logger = Logger.getLogger(PerfilNblFacade.class.getName());
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public PerfilNblFacade()
  {
    super(PerfilesNbl.class);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public List<PerfilesNbl> obtenerTodoOrdenadoPorCliente(Clientes clientesId)
  {
    try
    {
      TypedQuery<PerfilesNbl> query = this.em.createNamedQuery("PerfilesNbl.findByCliente", PerfilesNbl.class).setParameter("clientesId", clientesId);
      return query.getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo obtenerTodoOrdenadoPorCliente(Clientes clientesId) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porNombreEstadoLista(String nombre, Character estado)
  {
    try
    {
      return this.em.createNamedQuery("PerfilesNbl.findByNombreEstado").setParameter("nombre", nombre).setParameter("estado", estado).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombreEstado(String nombre,Character estado) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porCliente(Clientes cliente)
  {
    try
    {
      return getEntityManager().createQuery("select u from PerfilesNbl u where u.clientesId=:cliente").setParameter("cliente", cliente).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porCliente(Clientes cliente) = ", e);
    }
    return null;
  }
  
  public PerfilesNbl porClientePerfilBase(Clientes clientesId, PerfilesNbl perfilBaseId)
  {
    try
    {
      return (PerfilesNbl)getEntityManager().createNamedQuery("PerfilesNbl.findByClientePerfilBase").setParameter("clientesId", clientesId).setParameter("perfilBaseId", perfilBaseId).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porClientePerfilBase(Clientes clientesId, PerfilesNbl perfilBaseId) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porClientePerfilBaseLista(Clientes clientesId, PerfilesNbl perfilBaseId)
  {
    try
    {
      return getEntityManager().createNamedQuery("PerfilesNbl.findByClientePerfilBase").setParameter("clientesId", clientesId).setParameter("perfilBaseId", perfilBaseId).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porClientePerfilBaseLista(Clientes clientesId, PerfilesNbl perfilBaseId) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porClientePerfilBaseEstadoLista(Clientes clientesId, PerfilesNbl perfilBaseId, Character estado)
  {
    try
    {
      return getEntityManager().createNamedQuery("PerfilesNbl.findByClientePerfilBaseEstado").setParameter("clientesId", clientesId).setParameter("perfilBaseId", perfilBaseId).setParameter("estado", estado).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porClientePerfilBaseEstadoLista(Clientes clientesId, PerfilesNbl perfilBaseId, Character estado) = ", e);
    }
    return null;
  }
  
  public PerfilesNbl porPerfilBaseEstadoTipo(PerfilesNbl perfilBaseId, TipoPerfilesNbl tipoPerfilesNblId, Character estado)
  {
    try
    {
      return (PerfilesNbl)getEntityManager().createNamedQuery("PerfilesNbl.findByPerfilBaseEstadoTipo").setParameter("perfilBaseId", perfilBaseId).setParameter("tipoPerfilesNblId", tipoPerfilesNblId).setParameter("estado", estado).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porPerfilBaseEstadoTipo(PerfilesNbl perfilBaseId, TipoPerfilesNbl tipoPerfilesNblId, Character estado) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porPerfilBaseEstado(PerfilesNbl perfilBaseId, Character estado)
  {
    try
    {
      return getEntityManager().createNamedQuery("PerfilesNbl.findByPerfilBaseEstado").setParameter("perfilBaseId", perfilBaseId).setParameter("estado", estado).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porPerfilBaseEstado(PerfilesNbl perfilBaseId, Character estado) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porClienteTipoEstadoLista(Clientes clientesId, TipoPerfilesNbl tipoPerfilesNblId, Character estado)
  {
    try
    {
      return getEntityManager().createNamedQuery("PerfilesNbl.findByClienteTipoEstado").setParameter("clientesId", clientesId).setParameter("tipoPerfilesNblId", tipoPerfilesNblId).setParameter("estado", estado).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porClienteTipoEstadoLista(Clientes clientesId, TipoPerfilesNbl tipoPerfilesNblId, Character estado) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porTipoPerfilNbl(TipoPerfilesNbl tipoPerfilesNblId)
  {
    try
    {
      return getEntityManager().createQuery("SELECT p FROM PerfilesNbl p WHERE p.tipoPerfilesNblId = :tipoPerfilesNblId").setParameter("tipoPerfilesNblId", tipoPerfilesNblId).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porTipoPerfilNbl(TipoPerfilesNbl tipoPerfilesNblId) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porTipoPerfilNblPorCliente(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId)
  {
    try
    {
      return getEntityManager().createQuery("SELECT p FROM PerfilesNbl p WHERE p.tipoPerfilesNblId = :tipoPerfilesNblId AND p.clientesId = :clientesId ").setParameter("tipoPerfilesNblId", tipoPerfilesNblId).setParameter("clientesId", clientesId).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porTipoPerfilNblPorCliente(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId) = ", e);
    }
    return null;
  }
  
  public PerfilesNbl porTipoPerfilNblPorClienteSingle(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId)
  {
    try
    {
      return (PerfilesNbl)getEntityManager().createQuery("SELECT p FROM PerfilesNbl p WHERE p.tipoPerfilesNblId = :tipoPerfilesNblId AND p.clientesId = :clientesId ").setParameter("tipoPerfilesNblId", tipoPerfilesNblId).setParameter("clientesId", clientesId).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porTipoPerfilNblPorClienteSingle(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId) = ", e);
    }
    return null;
  }
  
  public PerfilesNbl porTipoPerfilClienteEstadoBaseSingle(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId, Character estado, PerfilesNbl perfilBaseId)
  {
    try
    {
      return (PerfilesNbl)getEntityManager().createQuery("SELECT p FROM PerfilesNbl p WHERE p.tipoPerfilesNblId = :tipoPerfilesNblId AND p.clientesId = :clientesId AND p.estado = :estado AND p.perfilBaseId = :perfilBaseId ").setParameter("tipoPerfilesNblId", tipoPerfilesNblId).setParameter("clientesId", clientesId).setParameter("estado", estado).setParameter("perfilBaseId", perfilBaseId).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porTipoPerfilClienteEstadoBaseSingle(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId, Character estado, PerfilesNbl perfilBaseId) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porTipoPerfilClienteEstadoBaseList(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId, Character estado, PerfilesNbl perfilBaseId)
  {
    try
    {
      return getEntityManager().createQuery("SELECT p FROM PerfilesNbl p WHERE p.tipoPerfilesNblId = :tipoPerfilesNblId AND p.clientesId = :clientesId AND p.estado = :estado AND p.perfilBaseId = :perfilBaseId ").setParameter("tipoPerfilesNblId", tipoPerfilesNblId).setParameter("clientesId", clientesId).setParameter("estado", estado).setParameter("perfilBaseId", perfilBaseId).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porTipoPerfilClienteEstadoBaseList(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId, Character estado, PerfilesNbl perfilBaseId) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porTipoPerfilClienteBaseList(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId, PerfilesNbl perfilBaseId)
  {
    try
    {
      return getEntityManager().createQuery("SELECT p FROM PerfilesNbl p WHERE p.tipoPerfilesNblId = :tipoPerfilesNblId AND p.clientesId = :clientesId  AND p.perfilBaseId = :perfilBaseId ").setParameter("tipoPerfilesNblId", tipoPerfilesNblId).setParameter("clientesId", clientesId).setParameter("perfilBaseId", perfilBaseId).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porTipoPerfilClienteBaseList(TipoPerfilesNbl tipoPerfilesNblId, Clientes clientesId , PerfilesNbl perfilBaseId) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porNombreNblPorCliente(String nombre, Clientes clientesId)
  {
    try
    {
      return getEntityManager().createQuery("SELECT p FROM PerfilesNbl p WHERE p.nombre = :nombre AND p.clientesId = :clientesId ").setParameter("nombre", nombre).setParameter("clientesId", clientesId).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombreNblPorCliente(String nombre, Clientes clientesId) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porNombreNblPorClienteEstado(String nombre, Clientes clientesId, Character estado)
  {
    try
    {
      return getEntityManager().createQuery("SELECT p FROM PerfilesNbl p WHERE UPPER(p.nombre) = :nombre AND p.clientesId = :clientesId AND p.estado = :estado").setParameter("nombre", nombre).setParameter("clientesId", clientesId).setParameter("estado", estado).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombreNblPorCliente(String nombre, Clientes clientesId) = ", e);
    }
    return null;
  }
  
  public PerfilesNbl porNombreNblPorClienteSingle(String nombre, Clientes clientesId)
  {
    try
    {
      return (PerfilesNbl)getEntityManager().createQuery("SELECT p FROM PerfilesNbl p WHERE p.nombre = :nombre AND p.clientesId = :clientesId ").setParameter("nombre", nombre).setParameter("clientesId", clientesId).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombreNblPorClienteSingle(String nombre, Clientes clientesId) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> consultarPorClientePerfilEstado(int first, int pageSize, Map<String, Object> filters, Clientes cliente, PerfilesNbl perfilBase, Character estado)
  {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<PerfilesNbl> q = cb.createQuery(PerfilesNbl.class);
    
    Root<PerfilesNbl> root = q.from(PerfilesNbl.class);
    q.select(root);
    
    Map<String, Join> joins = new HashMap();
    
    Path pathNombre = getPath("nombre", root, joins);
    
    Path pathAlias = getPath("descripcion", root, joins);
    
    q.orderBy(new Order[] { cb.asc(pathNombre) });
    
    Predicate filterCondition = buildFiter(root, joins, filters);
    
    Path pathCliente = getPath("clientesId", root, joins);
    Path pathPerfilBase = getPath("perfilBaseId", root, joins);
    Path pathEstado = getPath("estado", root, joins);
    
    filterCondition = cb.and(new Predicate[] { filterCondition, cb.equal(pathCliente, cliente), cb.equal(pathPerfilBase, perfilBase), cb.equal(pathEstado, estado) });
    
    q.where(filterCondition);
    
    TypedQuery<PerfilesNbl> tq = getEntityManager().createQuery(q);
    if (pageSize > 0) {
      tq.setMaxResults(pageSize);
    }
    if (first >= 0) {
      tq.setFirstResult(first);
    }
    return tq.getResultList();
  }
  
  public String[] getGlobalFilterFields()
  {
    return new String[] { "nombre", "descripcion" };
  }
  
  public Long getSequence()
  {
    try
    {
      return Long.valueOf(((BigDecimal)getEntityManager().createNativeQuery("select PERFILES_NBL_SEQ.nextval from dual").getSingleResult()).longValue());
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo getSequence() = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> porPerfilBase(PerfilesNbl perfilBaseId)
  {
    List<PerfilesNbl> listaPerfilesBase;
    try
    {
      listaPerfilesBase = getEntityManager().createNamedQuery("PerfilesNbl.findByPerfilBase", PerfilesNbl.class).setParameter("perfilBaseId", perfilBaseId).getResultList();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porPerfilBase(PerfilesNbl perfilBaseId) = ", ex);
      listaPerfilesBase = null;
    }
    return listaPerfilesBase;
  }
  
  public PerfilesNbl porNombrePerfilBase(String nombre, PerfilesNbl perfilBaseId)
  {
    PerfilesNbl perfilBaseNombre;
    try
    {
      perfilBaseNombre = (PerfilesNbl)getEntityManager().createNamedQuery("PerfilesNbl.findByNombrePerfilBase", PerfilesNbl.class).setParameter("perfilBaseId", perfilBaseId).setParameter("nombre", nombre).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombrePerfilBase(String nombre, PerfilesNbl perfilBaseId) = ", ex);
      perfilBaseNombre = null;
    }
    return perfilBaseNombre;
  }
  
  public PerfilesNbl porNombrePerfilBaseEstado(String nombre, PerfilesNbl perfilBaseId, Character estado)
  {
    PerfilesNbl perfilBaseNombre;
    try
    {
      perfilBaseNombre = (PerfilesNbl)getEntityManager().createNamedQuery("PerfilesNbl.findByNombrePerfilBaseEstado", PerfilesNbl.class).setParameter("perfilBaseId", perfilBaseId).setParameter("nombre", nombre).setParameter("estado", estado).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombrePerfilBase(String nombre, PerfilesNbl perfilBaseId) = ", ex);
      perfilBaseNombre = null;
    }
    return perfilBaseNombre;
  }
  
  public List<PerfilesNbl> porPerfilBaseTipoPerfil(PerfilesNbl perfilBaseId, TipoPerfilesNbl tipoPerfilesNblId)
  {
    List<PerfilesNbl> listaPerfilesBase;
    try
    {
      listaPerfilesBase = getEntityManager().createNamedQuery("PerfilesNbl.findByPerfilBaseTipoPerfilNbl", PerfilesNbl.class).setParameter("perfilBaseId", perfilBaseId).setParameter("tipoPerfilesNblId", tipoPerfilesNblId).getResultList();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porPerfilBaseTipoPerfil(PerfilesNbl perfilBaseId, TipoPerfilesNbl tipoPerfilesNblId) = ", ex);
      listaPerfilesBase = null;
    }
    return listaPerfilesBase;
  }
  
  public PerfilesNbl porPerfilBaseTipoPerfilSingle(PerfilesNbl perfilBaseId, TipoPerfilesNbl tipoPerfilesNblId)
  {
    PerfilesNbl PerfilesBase;
    try
    {
      PerfilesBase = (PerfilesNbl)getEntityManager().createNamedQuery("PerfilesNbl.findByPerfilBaseTipoPerfilNbl", PerfilesNbl.class).setParameter("perfilBaseId", perfilBaseId).setParameter("tipoPerfilesNblId", tipoPerfilesNblId).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porPerfilBaseTipoPerfilSingle(PerfilesNbl perfilBaseId, TipoPerfilesNbl tipoPerfilesNblId) = ", ex);
      PerfilesBase = null;
    }
    return PerfilesBase;
  }
  
  public PerfilesNbl porNombre(String nombre)
  {
    try
    {
      return (PerfilesNbl)this.em.createNamedQuery("PerfilesNbl.findByNombre").setParameter("nombre", nombre).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombre(String nombre) = ", e);
    }
    return null;
  }
  
  public PerfilesNbl porNombreEstado(String nombre, Character estado)
  {
    try
    {
      return (PerfilesNbl)this.em.createNamedQuery("PerfilesNbl.findByNombreEstado").setParameter("nombre", nombre).setParameter("estado", estado).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombreEstado(String nombre,Character estado) = ", e);
    }
    return null;
  }
  
  public PerfilesNbl porNombrePorCliente(String nombre)
  {
    try
    {
      return (PerfilesNbl)this.em.createNamedQuery("PerfilesNbl.findByNombre").setParameter("nombre", nombre).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo porNombrePorCliente(String nombre) = ", e);
    }
    return null;
  }
  
  public List<PerfilesNbl> listarPerfilesPorNombre(String nombre)
  {
    try
    {
      return this.em.createNamedQuery("PerfilesNbl.findByNombre").setParameter("nombre", nombre).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo ubicarPerfilPorNombre(String nombre) = ", e);
    }
    return null;
  }
  
  public PerfilesNbl ubicarPerfilPorNombre(String nombre)
  {
    try
    {
      return (PerfilesNbl)this.em.createNamedQuery("PerfilesNbl.findByNombre").setParameter("nombre", nombre).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo ubicarPerfilPorNombre(String nombre) = ", e);
    }
    return null;
  }
}
