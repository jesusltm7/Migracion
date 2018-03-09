package com.bod.facade;

import com.bod.model.Clientes;
import com.bod.model.DirectorioGlobal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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

@Named("directorioGlobalFacade")
@Stateless(name="maDirectorioGlobalFacade")
public class DirectorioGlobalFacade
  extends AbstractFacade<DirectorioGlobal>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public DirectorioGlobalFacade()
  {
    super(DirectorioGlobal.class);
  }
  
  public DirectorioGlobal porIdentificacion(Clientes cliente, String tipoIdentificacion, String identificacion)
  {
    try
    {
      return (DirectorioGlobal)this.em.createQuery("select d from DirectorioGlobal d where d.clientesId=:cliente and d.tipoIdentificacion=:tipoIdentificacion and d.identificacion=:identificacion").setParameter("cliente", cliente).setParameter("tipoIdentificacion", tipoIdentificacion).setParameter("identificacion", identificacion).getSingleResult();
    }
    catch (NoResultException ex) {}
    return null;
  }
  
  public DirectorioGlobal porIdentificacionYCliente(String tipoIdentificacion, String identificacion, Clientes clienteAsociado)
  {
    try
    {
      return (DirectorioGlobal)this.em.createQuery("select d from DirectorioGlobal d where d.clientesId=:cliente and d.tipoIdentificacion=:tipoIdentificacion and d.identificacion=:identificacion").setParameter("cliente", clienteAsociado).setParameter("tipoIdentificacion", tipoIdentificacion).setParameter("identificacion", identificacion).getSingleResult();
    }
    catch (NoResultException ex) {}
    return null;
  }
  
  public List<DirectorioGlobal> clientePorIdentificacion(String tipoIdentificacion, String identificacion)
  {
    try
    {
      return this.em.createQuery("select d from DirectorioGlobal d where d.tipoIdentificacion=:tipoIdentificacion and d.identificacion=:identificacion").setParameter("tipoIdentificacion", tipoIdentificacion).setParameter("identificacion", identificacion).getResultList();
    }
    catch (NoResultException ex) {}
    return null;
  }
  
  public List<DirectorioGlobal> porCliente(Clientes cliente)
  {
    try
    {
      return this.em.createQuery("select d from DirectorioGlobal d where d.clientesId=:cliente").setParameter("cliente", cliente).getResultList();
    }
    catch (NoResultException ex) {}
    return null;
  }
  
  public List<DirectorioGlobal> buscar(int first, int pageSize, Map<String, Object> filters, Clientes cliente)
  {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<DirectorioGlobal> q = cb.createQuery(DirectorioGlobal.class);
    Root<DirectorioGlobal> root = q.from(DirectorioGlobal.class);
    q.select(root);
    
    Map<String, Join> joins = new HashMap();
    
    Path path = getPath("nombre", root, joins);
    
    q.orderBy(new Order[] { cb.asc(path) });
    
    Predicate filterCondition = buildFiter(root, joins, filters);
    
    Path pathCliente = getPath("clientesId", root, joins);
    
    filterCondition = cb.and(filterCondition, cb.equal(pathCliente, cliente));
    
    Path pathTipoId = getPath("tipoIdentificacion", root, joins);
    Path pathId = getPath("identificacion", root, joins);
    
    filterCondition = cb.and(filterCondition, cb.or(cb.notEqual(pathTipoId, cliente.getTipoIdentificacion()), cb.notEqual(pathId, cliente.getIdentificacion())));
    
    q.where(filterCondition);
    
    TypedQuery<DirectorioGlobal> tq = getEntityManager().createQuery(q);
    if (pageSize > 0) {
      tq.setMaxResults(pageSize);
    }
    if (first >= 0) {
      tq.setFirstResult(first);
    }
    List<DirectorioGlobal> resultList = tq.getResultList();
    
    return resultList;
  }
  
  public List<DirectorioGlobal> consultar(int first, int pageSize, Map<String, Object> filters, Clientes cliente, String letraInicialNombreYAlias)
  {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<DirectorioGlobal> q = cb.createQuery(DirectorioGlobal.class);
    
    Root<DirectorioGlobal> root = q.from(DirectorioGlobal.class);
    q.select(root);
    
    Map<String, Join> joins = new HashMap();
    
    Path pathNombre = getPath("nombre", root, joins);
    
    Path pathAlias = getPath("alias", root, joins);
    
    Predicate filterCondition = buildFiter(root, joins, filters);
    
    Path pathCliente = getPath("clientesId", root, joins);
    
    filterCondition = cb.and(filterCondition, cb.equal(pathCliente, cliente));
    if ((letraInicialNombreYAlias != null) && (letraInicialNombreYAlias.trim().length() > 0))
    {
      String letra = letraInicialNombreYAlias.trim().toUpperCase();
      filterCondition = cb.and(filterCondition, cb.or(new Predicate[] { cb.like(cb.trim(cb.upper(pathNombre)), letra + "%"), cb.like(cb.trim(cb.upper(pathNombre)), "% " + letra + "%"), cb.like(cb.trim(cb.upper(pathAlias)), letra + "%"), cb.like(cb.trim(cb.upper(pathAlias)), "% " + letra + "%") }));
    }
    q.where(filterCondition);
    
    TypedQuery<DirectorioGlobal> tq = getEntityManager().createQuery(q);
    if (pageSize > 0) {
      tq.setMaxResults(pageSize);
    }
    if (first >= 0) {
      tq.setFirstResult(first);
    }
    List<DirectorioGlobal> resultList = tq.getResultList();
    
    DirectorioGlobal propio = null;
    for (DirectorioGlobal i : resultList) {
      if ((Objects.equals(i.getTipoIdentificacion(), cliente.getTipoIdentificacion())) && (Objects.equals(i.getIdentificacion(), cliente.getIdentificacion())))
      {
        propio = i;
        break;
      }
    }
    if (propio != null)
    {
      resultList.remove(propio);
      
      resultList.add(0, propio);
    }
    return resultList;
  }
  
  public String[] getGlobalFilterFields()
  {
    return new String[] { "nombre", "alias" };
  }
  
  public int count(Map<String, Object> filters, Clientes cliente, String letraInicialNombreYAlias)
  {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Long> query = cb.createQuery(Long.class);
    Root<DirectorioGlobal> root = query.from(DirectorioGlobal.class);
    query.select(cb.count(root));
    
    Map<String, Join> joins = new HashMap();
    
    Path pathNombre = getPath("nombre", root, joins);
    
    Path pathAlias = getPath("alias", root, joins);
    
    Predicate filterCondition = buildFiter(root, joins, filters);
    
    Path pathCliente = getPath("clientesId", root, joins);
    
    filterCondition = cb.and(filterCondition, cb.equal(pathCliente, cliente));
    if ((letraInicialNombreYAlias != null) && (letraInicialNombreYAlias.trim().length() > 0))
    {
      String letra = letraInicialNombreYAlias.trim().toUpperCase();
      filterCondition = cb.and(filterCondition, cb.or(new Predicate[] { cb.like(cb.trim(cb.upper(pathNombre)), letra + "%"), cb.like(cb.trim(cb.upper(pathNombre)), "% " + letra + "%"), cb.like(cb.trim(cb.upper(pathAlias)), letra + "%"), cb.like(cb.trim(cb.upper(pathAlias)), "% " + letra + "%") }));
    }
    query.where(filterCondition);
    
    long count = ((Long)getEntityManager().createQuery(query).getSingleResult()).longValue();
    
    return (int)count;
  }
  
  public int countBuscar(Map<String, Object> filters, Clientes cliente)
  {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Long> q = cb.createQuery(Long.class);
    Root<DirectorioGlobal> root = q.from(DirectorioGlobal.class);
    q.select(cb.count(root));
    
    Map<String, Join> joins = new HashMap();
    
    Path path = getPath("nombre", root, joins);
    
    q.orderBy(new Order[] { cb.asc(path) });
    
    Predicate filterCondition = buildFiter(root, joins, filters);
    
    Path pathCliente = getPath("clientesId", root, joins);
    
    filterCondition = cb.and(filterCondition, cb.equal(pathCliente, cliente));
    
    Path pathTipoId = getPath("tipoIdentificacion", root, joins);
    Path pathId = getPath("identificacion", root, joins);
    
    filterCondition = cb.and(filterCondition, cb.or(cb.notEqual(pathTipoId, cliente.getTipoIdentificacion()), cb.notEqual(pathId, cliente.getIdentificacion())));
    
    q.where(filterCondition);
    
    long count = ((Long)getEntityManager().createQuery(q).getSingleResult()).longValue();
    
    return (int)count;
  }
}
