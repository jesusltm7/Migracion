package com.bod.facade;

import com.bod.model.IBMapeoAcceso;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Named("mapeoAccesoIBFacade")
@Stateless(name="mamapeoAccesoIBFacade")
public class IBMapeoAccesoFacade
  extends AbstractFacade<IBMapeoAcceso>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  public IBMapeoAccesoFacade()
  {
    super(IBMapeoAcceso.class);
  }
  
  public IBMapeoAccesoFacade(Class<IBMapeoAcceso> entityClass)
  {
    super(entityClass);
  }
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public LinkedHashMap<String, ArrayList<Integer>> obtenerMapaOpciones()
  {
    LinkedHashMap<String, ArrayList<Integer>> mapaRetorno = new LinkedHashMap();
    TypedQuery<IBMapeoAcceso> query = null;
    try
    {
      query = this.em.createNamedQuery("IBMapeoAcceso.findAll", IBMapeoAcceso.class);
      for (IBMapeoAcceso obj : query.getResultList()) {
        if (mapaRetorno.get(obj.getOpcionIB()) != null)
        {
          ((ArrayList)mapaRetorno.get(obj.getOpcionIB())).add(Integer.valueOf(obj.getOpcionNBL()));
        }
        else
        {
          ArrayList<Integer> listaObjetos = new ArrayList();
          listaObjetos.add(Integer.valueOf(obj.getOpcionNBL()));
          mapaRetorno.put(obj.getOpcionIB(), listaObjetos);
        }
      }
      return mapaRetorno;
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", ("ERROR ENCONTRADO {0} " + e).toUpperCase());
    }
    return null;
  }
}
