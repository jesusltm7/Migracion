package com.bod.facade;

import com.bod.model.Bancos;
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

@Named("bancosFacade")
@Stateless(name="maBancosFacade")
public class BancosFacade
  extends AbstractFacade<Bancos>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public BancosFacade()
  {
    super(Bancos.class);
  }
  
  public Bancos findByNombre(String nombre)
  {
    try
    {
      return (Bancos)this.em.createNamedQuery("Bancos.findByNombre", Bancos.class).setParameter("nombre", nombre).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), BancosFacade.class.getName(), ("ERROR ENCONTRADO" + ex).toUpperCase());
    }
    return null;
  }
  
  public List<Bancos> findAllOrder()
  {
    Query q = this.em.createNamedQuery("Bancos.findAll");
    
    List<Bancos> bancos = q.getResultList();
    return bancos;
  }
  
  public Bancos porPrefijoCuenta(String numeroCuenta)
  {
    if ((numeroCuenta == null) || (numeroCuenta.length() < 4)) {
      return null;
    }
    try
    {
      String codigo = numeroCuenta.substring(0, 4);
      
      return (Bancos)this.em.createQuery("select b from Bancos b where b.codigo=:codigo").setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), BancosFacade.class.getName(), ("ERROR ENCONTRADO" + ex).toUpperCase());
    }
    return null;
  }
  
  public Bancos porCodigo(String codigo)
  {
    try
    {
      return (Bancos)this.em.createNamedQuery("Bancos.findByCodigo", Bancos.class).setParameter("codigo", codigo).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), BancosFacade.class.getName(), ("ERROR ENCONTRADO" + ex).toUpperCase());
    }
    return null;
  }
}
