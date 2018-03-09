package com.bod.facade;

import com.bod.model.ExcluidosMigracion;
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

@Named("excluidosMigracionFacade")
@Stateless(name="maExcluidosMigracionFacade")
public class ExcluidosMigracionFacade
  extends AbstractFacade<ExcluidosMigracion>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  @Override
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public ExcluidosMigracionFacade()
  {
    super(ExcluidosMigracion.class);
  }
  
  public boolean porIdentificacion(String tipo, String identificacion)
  {
    try
    {
      String result = null;
      result = (String)getEntityManager().createQuery("SELECT e.id FROM ExcluidosMigracion e WHERE e.tipoIdentificacion = :tipoIdentificacion and e.identificacion = :identificacion").setParameter("tipoIdentificacion", tipo.charAt(0)).setParameter("identificacion", identificacion).getSingleResult();
      
      return result != null;
    }
    catch (NoResultException e)
    {
      Log.getInstance().debug(LogNBL.MIGRAR.getCodigo(), "NBL", "Sin resultados (tabla 'Excluidos_Migracion')");
      return false;
    }
    catch (Exception ex)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error buscando en (tabla 'Excluidos_Migracion')", ex);
    }
    return true;
  }
  
  public ExcluidosMigracion porIdentificacionExcluido(String tipo, String identificacion)
  {
    try
    {
      ExcluidosMigracion result = null;
      return (ExcluidosMigracion)getEntityManager().createQuery("SELECT e.id FROM ExcluidosMigracion e WHERE e.tipoIdentificacion = :tipoIdentificacion and e.identificacion = :identificacion").setParameter("tipoIdentificacion", tipo.charAt(0)).setParameter("identificacion", identificacion).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().debug(LogNBL.MIGRAR.getCodigo(), "MIGRACI�N", "Sin resultados (tabla 'Excluidos_Migracion')");
    }
    return null;
  }
  
  public List<ExcluidosMigracion> porEstadoPendiente()
  {
    List<ExcluidosMigracion> result = new ArrayList();
    try
    {
      result = getEntityManager().createQuery("SELECT e FROM ExcluidosMigracion e WHERE e.estado = :estado AND e.numeroPerfiles > :perfiles").setParameter("estado", 'P').setParameter("perfiles", 0).getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().debug(LogNBL.MIGRAR.getCodigo(), "MIGRACI�N", "Sin resultados (tabla 'Excluidos_Migracion') estado pendiente");
    }
    return result;
  }
  
  public List<ExcluidosMigracion> porEstadoPendiente(int proceso)
  {
    List<ExcluidosMigracion> result = new ArrayList();
    try
    {
      result = getEntityManager().createQuery("SELECT e FROM ExcluidosMigracion e WHERE e.estado = :estado AND e.numeroPerfiles > :perfiles AND e.codigoProceso=:codigoProceso").setParameter("estado", 'P').setParameter("perfiles", 0).setParameter("codigoProceso", proceso).getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().debug(LogNBL.MIGRAR.getCodigo(), "MIGRACI�N", "Sin resultados (tabla 'Excluidos_Migracion') estado pendiente");
    }
    return result;
  }
  
  public List<ExcluidosMigracion> porEstadoPendienteTruncado(int proceso)
  {
    List<ExcluidosMigracion> result = new ArrayList();
    try
    {
      result = getEntityManager().createQuery("SELECT e FROM ExcluidosMigracion e WHERE e.estado = :estado AND e.numeroPerfiles > :perfiles AND e.codigoProceso=:codigoProceso").setParameter("estado", 'P').setParameter("perfiles", 0).setParameter("codigoProceso", proceso).setMaxResults(100).getResultList();
    }
    catch (Exception e)
    {
      Log.getInstance().debug(LogNBL.MIGRAR.getCodigo(), "MIGRACI�N", "Sin resultados (tabla 'Excluidos_Migracion') estado pendiente");
    }
    return result;
  }
}
