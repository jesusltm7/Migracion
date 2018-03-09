package com.bod.facade;

import com.bod.model.OperacionesNbl;
import com.bod.model.PerfilesNbl;
import com.bod.model.PerfilesnblHasOperacionesnbl;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("PerfilesHasOperacionesFacade")
@Stateless(name="maPerfilesHasOperacionesFacade")
public class PerfilesHasOperacionesFacade
  extends AbstractFacade<PerfilesnblHasOperacionesnbl>
{
  private static final Logger logger = Logger.getLogger(PerfilesHasOperacionesFacade.class.getName());
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public PerfilesHasOperacionesFacade()
  {
    super(PerfilesnblHasOperacionesnbl.class);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public PerfilesnblHasOperacionesnbl findOperationByProfile(PerfilesNbl perfilesNblId, OperacionesNbl operacionesNblId)
  {
    try
    {
      return (PerfilesnblHasOperacionesnbl)getEntityManager().createNamedQuery("PerfilesnblHasOperacionesnbl.findByPerfilesOperaciones").setParameter("operacionesNblId", operacionesNblId).setParameter("perfilesNblId", perfilesNblId).getSingleResult();
    }
    catch (NoResultException e) {}
    return null;
  }
}
