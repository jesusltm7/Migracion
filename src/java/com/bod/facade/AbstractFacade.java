package com.bod.facade;

import com.bod.model.DetTransaccionNbl;
import com.bod.model.MstTransaccionesNbl;
import com.bod.model.Parametros;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public abstract class AbstractFacade<T>
{
  private Class<T> entityClass;
  
  public AbstractFacade(Class<T> entityClass)
  {
    this.entityClass = entityClass;
  }
  
  protected abstract EntityManager getEntityManager();
  
  public void create(T entity)
  {
    try
    {
      getEntityManager().persist(entity);
    }
    catch (ConstraintViolationException c)
    {
      for (ConstraintViolation<? extends Object> v : c.getConstraintViolations())
      {
        Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), entity.toString(), ("***** VP Error Class init: " + v).toUpperCase());
        Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), entity.toString(), ("==>>" + v.getMessage()).toUpperCase());
      }
    }
  }
  
  public void crear(List<DetTransaccionNbl> listDetTransaccionNbl)
  {
    String insertSQL = "INSERT INTO DETTRANSACCIONNBL (PK_IDDETTRANSACCIONNBL, FK_IDTRANSACCIONNBL, ETIQUETA, VALOR, FK_IDPARAMETRO) VALUES(DETTRANSACCIONNBL_SEQ.nextval, ?, ?, ?, ?)";
    
    Connection con = null;
    PreparedStatement preparedStatement = null;
    EntityManagerFactory emf = null;
    EntityManager em = null;
    try
    {
      emf = Persistence.createEntityManagerFactory("nblPU");
      em = emf.createEntityManager();
      em.getTransaction().begin();
      
      con = (Connection)em.unwrap(Connection.class);
      preparedStatement = con.prepareStatement(insertSQL);
      for (DetTransaccionNbl detTransaccionNbl : listDetTransaccionNbl)
      {
        preparedStatement.setLong(1, detTransaccionNbl.getFkIdTransaccionNbl().getPkIdTransaccionNbl().longValue());
        preparedStatement.setString(2, detTransaccionNbl.getEtiqueta());
        preparedStatement.setString(3, detTransaccionNbl.getValor());
        preparedStatement.setLong(4, detTransaccionNbl.getFkIdparametro().getId().longValue());
        preparedStatement.addBatch();
      }
      preparedStatement.executeBatch();
      em.getTransaction().commit();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), this.entityClass.getName(), "ERROR EN PERSISTIENDO REGISTROS MASIVOS EN LA TABLA DETTRANSACCIONNBL", e);
    }
    finally
    {
      close(preparedStatement);
      close(con);
    }
  }
  
  private void close(AutoCloseable ac)
  {
    try
    {
      if (ac != null) {
        ac.close();
      }
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "ERROR CERRANDO CONEXION", e);
    }
  }
  
  public T edit(T entity)
  {
    return (T)getEntityManager().merge(entity);
  }
  
  public void remove(T entity)
  {
    getEntityManager().remove(getEntityManager().merge(entity));
  }
  
  public T find(Object id)
  {
    return (T)getEntityManager().find(this.entityClass, id);
  }
  
  public List<T> findAll()
  {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    cq.select(cq.from(this.entityClass));
    return getEntityManager().createQuery(cq).getResultList();
  }
  
  public List<T> findRange(int[] range)
  {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    cq.select(cq.from(this.entityClass));
    Query q = getEntityManager().createQuery(cq);
    q.setMaxResults(range[1] - range[0] + 1);
    q.setFirstResult(range[0]);
    return q.getResultList();
  }
  
  public int count()
  {
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    Root<T> rt = cq.from(this.entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    Query q = getEntityManager().createQuery(cq);
    return ((Long)q.getSingleResult()).intValue();
  }
  
  protected Path getPath(String field, Root root, Map<String, Join> joins)
  {
    if (field != null)
    {
      From current = root;
      String[] parts = field.split("\\.");
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < parts.length - 1; i++)
      {
        if (i > 0) {
          sb.append(".");
        }
        sb.append(parts[i]);
        Join j = (Join)joins.get(sb.toString());
        if (j == null)
        {
          j = current.join(parts[i], JoinType.LEFT);
          joins.put(sb.toString(), j);
        }
        current = j;
      }
      return current.get(parts[(parts.length - 1)]);
    }
    return null;
  }
  
  protected Predicate buildFiter(Root root, Map<String, Join> joins, Map<String, Object> filters)
  {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    Predicate filterCondition = cb.conjunction();
    if (filters != null) {
      for (Map.Entry<String, Object> filter : filters.entrySet()) {
        if ((filter.getValue() != null) && (!filter.getValue().equals(""))) {
          if ("globalFilter".equals(filter.getKey()))
          {
            String[] globalFields = getGlobalFilterFields();
            
            Predicate p = cb.disjunction();
            if (globalFields != null) {
              for (String i : globalFields)
              {
                Path path = getPath(i, root, joins);
                if (path != null) {
                  if (((String)filter.getKey()).endsWith(".id"))
                  {
                    Predicate p2 = cb.equal(path, filter.getValue());
                    p = cb.or(p2, p);
                  }
                  else if (i.contains("fecha"))
                  {
                    Predicate p2 = cb.like(cb.function("TO_CHAR", String.class, new Expression[] { path, cb.literal("dd/MM/yyyy") }), "%" + filter.getValue().toString().toUpperCase() + "%");
                    p = cb.or(p2, p);
                  }
                  else
                  {
                    Predicate p2 = cb.like(cb.upper(path), "%" + filter.getValue().toString().toUpperCase() + "%");
                    p = cb.or(p2, p);
                  }
                }
              }
            }
            filterCondition = cb.and(filterCondition, p);
          }
          else if ("busquedaGlobal".equals(filter.getKey()))
          {
            String[] globalFields = getGlobalFilterFields();
            Predicate p = cb.disjunction();
            if (globalFields != null)
            {
              String[] words = filter.getValue().toString().split(" ");
              for (String word : words) {
                for (String i : globalFields)
                {
                  Path path = getPath(i, root, joins);
                  if (path != null)
                  {
                    Predicate p2 = cb.like(cb.upper(path), "%" + word.toUpperCase() + "%");
                    p = cb.or(p2, p);
                  }
                }
              }
            }
            filterCondition = cb.and(filterCondition, p);
          }
          else
          {
            Path path = getPath((String)filter.getKey(), root, joins);
            if (path != null) {
              if (((String)filter.getKey()).endsWith(".id")) {
                filterCondition = cb.and(filterCondition, cb.equal(path, filter.getValue()));
              } else {
                filterCondition = cb.and(filterCondition, cb.like(cb.upper(path), "%" + filter.getValue().toString().toUpperCase() + "%"));
              }
            }
          }
        }
      }
    }
    return filterCondition;
  }
  
  public int count(Map<String, Object> filters)
  {
    CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    Root<T> root = cq.from(this.entityClass);
    cq.select(cb.count(root));
    
    Map<String, Join> joins = new HashMap();
    
    Predicate filterCondition = buildFiter(root, joins, filters);
    
    cq.where(filterCondition);
    
    Query q = getEntityManager().createQuery(cq);
    
    return ((Long)q.getSingleResult()).intValue();
  }
  
  public String[] getGlobalFilterFields()
  {
    return new String[0];
  }
}
