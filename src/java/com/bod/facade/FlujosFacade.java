package com.bod.facade;

import com.bod.model.Clientes;
import com.bod.model.ClientesHasUsuariosNbl;
import com.bod.model.Cnfflujos;
import com.bod.model.OperacionesNbl;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Named("flujosFacade")
@Stateless(name="maFlujosFacade")
public class FlujosFacade
  extends AbstractFacade<Cnfflujos>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public FlujosFacade()
  {
    super(Cnfflujos.class);
  }
  
  public Cnfflujos obtenerId(Long idFlujo)
  {
    try
    {
      Cnfflujos cnfflujos = new Cnfflujos();
      return (Cnfflujos)this.em.createQuery("SELECT c FROM Cnfflujos c WHERE c.pkIdflujo = :pkIdflujo").setParameter("pkIdflujo", idFlujo).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "NO SE CONSIGUIO EL RESULTADO DE LA TABLA CNFFLUJOS = {0}", ex);
    }
    return null;
  }
  
  public List<Cnfflujos> buscarPorEstadoOperacionMonto(ClientesHasUsuariosNbl clientesHasUsuariosNbl, OperacionesNbl operacionNbl, BigDecimal monto, String estado)
  {
    TypedQuery<Cnfflujos> query = this.em.createNamedQuery("Cnfflujos.findByOperacionEstadoMontos", Cnfflujos.class);
    query.setParameter("clientesHasUsuariosNbl", clientesHasUsuariosNbl).setParameter("estado", Character.valueOf(estado.charAt(0))).setParameter("operacionNbl", operacionNbl).setParameter("monto", monto);
    
    List<Cnfflujos> listaFlujos = query.getResultList();
    
    return listaFlujos;
  }
  
  public Cnfflujos obtenerPorCodigo(String valestadoflujo)
  {
    try
    {
      Cnfflujos cnfflujos = new Cnfflujos();
      return (Cnfflujos)this.em.createQuery("SELECT c FROM Cnfflujos c WHERE c.valestadoflujo = :valestadoflujo").setParameter("valestadoflujo", valestadoflujo).getSingleResult();
    }
    catch (NoResultException ex)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "ERROR EN EL RESULTADO DE LA TABLA CNFFLUJOS = {0}", ex);
    }
    return null;
  }
  
  public List<Cnfflujos> buscarPorEstadoOperacion(ClientesHasUsuariosNbl clientesHasUsuariosNbl, OperacionesNbl operacionNbl, String estado)
  {
    TypedQuery<Cnfflujos> query = this.em.createNamedQuery("Cnfflujos.findByOperacionEstado", Cnfflujos.class);
    query.setParameter("clientesHasUsuariosNbl", clientesHasUsuariosNbl).setParameter("estado", Character.valueOf(estado.charAt(0))).setParameter("operacionNbl", operacionNbl);
    
    List<Cnfflujos> listaFlujos = query.getResultList();
    
    return listaFlujos;
  }
  
  public int contarCantidadFlujosContrato(ClientesHasUsuariosNbl clientesHasUsuariosNbl, BigDecimal monto, OperacionesNbl operacionesNbl, String contrato)
  {
    int cantidadFlujos = 0;
    try
    {
      List<Number> counts = this.em.createNativeQuery("SELECT COUNT(*) counts FROM CNFFLUJOS WHERE VALESTADOFLUJO='A' and FK_IDCLIENTE = ? AND FK_IDOPERACIONNBL = ? AND (? BETWEEN VALMONTOMIN AND VALMONTOMAX) AND DESFLUJO LIKE ?").setParameter(1, clientesHasUsuariosNbl.getClientes().getId()).setParameter(2, operacionesNbl.getId()).setParameter(3, monto).setParameter(4, "%" + contrato + "%").getResultList();
      
      cantidadFlujos = ((Number)counts.get(0)).intValue();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "ERROR CONTANDO FLUJOS CONFIGURADOS", e);
    }
    return cantidadFlujos;
  }
}
