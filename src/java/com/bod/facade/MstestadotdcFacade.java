package com.bod.facade;

import com.bod.model.Mstestadotdc;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("mstestadotdcFacade")
@Stateless(name="maMstestadotdcFacade")
public class MstestadotdcFacade
  extends AbstractFacade<Mstestadotdc>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  @EJB
  private TextosFacade textosFacade;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public MstestadotdcFacade()
  {
    super(Mstestadotdc.class);
  }
  
  public List<Mstestadotdc> findAllEstados()
  {
    return this.em.createNamedQuery("Mstestadotdc.findAllOrderByDesestado", Mstestadotdc.class).getResultList();
  }
}
