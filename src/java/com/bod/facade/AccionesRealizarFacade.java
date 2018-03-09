package com.bod.facade;

import com.bod.model.AccionesRealizar;
import com.bod.model.Idiomas;
import com.bod.model.OperacionesNbl;
import com.bod.model.PerfilesNbl;
import com.bod.model.UsuariosNbl;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.joda.time.DateTime;

@Named("accionesRealizarFacade")
@Stateless(name="maAccionesRealizarFacade")
public class AccionesRealizarFacade
  extends AbstractFacade<AccionesRealizar>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public AccionesRealizarFacade()
  {
    super(AccionesRealizar.class);
  }
  
  public List<AccionesRealizar> getAccionesPorRealizar(Long ambiente, PerfilesNbl perfil, UsuariosNbl usuario, Idiomas idioma)
  {
    DateTime dt = new DateTime();
    Date desde = dt.withDayOfWeek(1).withTimeAtStartOfDay().toDate();
    Date hasta = dt.withDayOfWeek(7).withTime(23, 59, 59, 999).toDate();
    
    List<AccionesRealizar> l = getEntityManager().createQuery("select a from AccionesRealizar a where (a.ambienteId.id=1 or a.ambienteId.id=:ambiente) and (a.perfilNblId=:perfil or a.usuarioNblId=:usuario)and (a.estatus='A') and (a.idiomasId.codigoIso='xx' or a.idiomasId=:idioma) and (a.fechaAparicion BETWEEN :fechaAparicionDesde and :fechaAparicionHasta) order by a.fechaAparicion desc").setParameter("ambiente", ambiente).setParameter("perfil", perfil).setParameter("usuario", usuario).setParameter("idioma", idioma).setParameter("fechaAparicionDesde", new Timestamp(desde.getTime()), TemporalType.TIMESTAMP).setParameter("fechaAparicionHasta", new Timestamp(hasta.getTime()), TemporalType.TIMESTAMP).getResultList();
    
    return l;
  }
  
  public List<AccionesRealizar> getAccionesPorRealizarSemanaAnterior(Long ambiente, PerfilesNbl perfil, UsuariosNbl usuario, Idiomas idioma)
  {
    DateTime dt = new DateTime();
    dt = dt.minusWeeks(1);
    Date desde = dt.withDayOfWeek(1).withTimeAtStartOfDay().toDate();
    Date hasta = dt.withDayOfWeek(7).withTime(23, 59, 59, 999).toDate();
    
    List<AccionesRealizar> l = getEntityManager().createQuery("select a from AccionesRealizar a where (a.ambienteId.id=1 or a.ambienteId.id=:ambiente) and (a.perfilNblId=:perfil or a.usuarioNblId=:usuario)and (a.estatus='A') and (a.idiomasId.codigoIso='xx' or a.idiomasId=:idioma) and (a.fechaAparicion BETWEEN :fechaAparicionDesde and :fechaAparicionHasta) order by a.fechaAparicion desc").setParameter("ambiente", ambiente).setParameter("perfil", perfil).setParameter("usuario", usuario).setParameter("idioma", idioma).setParameter("fechaAparicionDesde", new Timestamp(desde.getTime()), TemporalType.TIMESTAMP).setParameter("fechaAparicionHasta", new Timestamp(hasta.getTime()), TemporalType.TIMESTAMP).getResultList();
    
    return l;
  }
  
  public List<AccionesRealizar> searchActionPerformedNotReaded(OperacionesNbl operacion_requerida, OperacionesNbl operacion_solicitada, UsuariosNbl usuario)
  {
    try
    {
      return getEntityManager().createQuery("SELECT a from AccionesRealizar a where a.operacionRequerida=:operacion_requerida and a.operacionSolicitada=:operacion_solicitada and a.usuarioNblId=:usuario and a.flag_lectura  = '1'").setParameter("operacion_requerida", operacion_requerida).setParameter("operacion_solicitada", operacion_solicitada).setParameter("usuario", usuario).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), AccionesRealizarFacade.class.getName(), "ERROR ELIMINANDO LAS ACCIONES POR REALIZAR DEL USUARIO".toUpperCase());
    }
    return null;
  }
  
  public List<AccionesRealizar> obtenerAcciones(Long ambiente, PerfilesNbl perfil, UsuariosNbl usuario, Idiomas idioma)
  {
    List<AccionesRealizar> listaAcciones = null;
    try
    {
      DateTime dt = new DateTime();
      dt = dt.minusWeeks(2);
      Date desde = dt.withDayOfWeek(1).withTimeAtStartOfDay().toDate();
      
      listaAcciones = getEntityManager().createQuery("select a from AccionesRealizar a where (a.ambienteId.id=1 or a.ambienteId.id=:ambiente) and (a.perfilNblId=:perfil or a.usuarioNblId=:usuario)and (a.estatus='A') and (a.idiomasId.codigoIso='xx' or a.idiomasId=:idioma) and a.fechaAparicion <= :fechaAparicion order by a.fechaAparicion desc").setParameter("ambiente", ambiente).setParameter("perfil", perfil).setParameter("usuario", usuario).setParameter("idioma", idioma).setParameter("fechaAparicion", new Timestamp(desde.getTime()), TemporalType.TIMESTAMP).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), AccionesRealizarFacade.class.getName(), ("*** ERROR ENCONTRADO " + e).toUpperCase());
    }
    return listaAcciones;
  }
  
  public boolean marcarLeido(AccionesRealizar accion)
  {
    boolean marcado = true;
    try
    {
      getEntityManager().createNativeQuery("UPDATE ACCIONES_REALIZAR SET FLAG_LECTURA=0 WHERE ID = ?").setParameter(1, accion.getId()).executeUpdate();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ACTUALIZANDO PERFILES ENCONTRADO = {0}" + e).toUpperCase());
    }
    return marcado;
  }
  
  public List<AccionesRealizar> obtenerFiltro(String dato, int primerResultado, int tamanhoPagina, Long ambiente, PerfilesNbl perfil, UsuariosNbl usuario, Idiomas idioma)
  {
    List<AccionesRealizar> resultado = new ArrayList();
    try
    {
      resultado = getEntityManager().createQuery("select a from AccionesRealizar a where (a.ambienteId.id=1 or a.ambienteId.id=:ambiente) and (a.perfilNblId=:perfil or a.usuarioNblId=:usuario)and (a.estatus='A') and (a.idiomasId.codigoIso='xx' or a.idiomasId=:idioma) and (a.descripcion LIKE :descripcionParam or a.titulo LIKE :tituloParam) order by a.fechaAparicion desc").setParameter("ambiente", ambiente).setParameter("perfil", perfil).setParameter("usuario", usuario).setParameter("idioma", idioma).setParameter("descripcionParam", "%" + dato + "%").setParameter("tituloParam", "%" + dato + "%").setFirstResult(primerResultado).setMaxResults(tamanhoPagina).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), AccionesRealizarFacade.class.getName(), ("*** ERROR ENCONTRADO " + e).toUpperCase());
    }
    return resultado;
  }
  
  public int obtenerCantidadRegistrosFiltro(String dato, UsuariosNbl usuarioNblIdParam)
  {
    int count = 0;
    count = ((Number)getEntityManager().createNamedQuery("AccionesRealizar.countByFiltro").setParameter("descripcionParam", "%" + dato + "%").setParameter("tituloParam", "%" + dato + "%").setParameter("usuarioNblIdParam", usuarioNblIdParam).getSingleResult()).intValue();
    
    return count;
  }
  
  public int getNoLeidasTotal(Long ambiente, PerfilesNbl perfil, UsuariosNbl usuario, Idiomas idioma)
  {
    int count = 0;
    try
    {
      count = ((Number)getEntityManager().createQuery("SELECT COUNT(a.id) from AccionesRealizar a where (a.ambienteId.id=1 or a.ambienteId.id=:ambiente) and (a.perfilNblId=:perfil or a.usuarioNblId=:usuario)and (a.estatus='A') and (a.idiomasId.codigoIso='xx' or a.idiomasId=:idioma) and a.flag_lectura = :flag_lectura").setParameter("ambiente", ambiente).setParameter("perfil", perfil).setParameter("usuario", usuario).setParameter("idioma", idioma).setParameter("flag_lectura", Character.valueOf('1')).getSingleResult()).intValue();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), AccionesRealizarFacade.class.getName(), ("*** ERROR ENCONTRADO " + e).toUpperCase());
    }
    return count;
  }
  
  public Long getSequence()
  {
    try
    {
      return Long.valueOf(((BigDecimal)getEntityManager().createNativeQuery("select ACCION_REALIZAR_SEQ.nextval from dual").getSingleResult()).longValue());
    }
    catch (NoResultException e) {}
    return null;
  }
  
  public List<AccionesRealizar> searchActionPerformed(OperacionesNbl operacion_requerida, OperacionesNbl operacion_solicitada, UsuariosNbl usuario)
  {
    try
    {
      return getEntityManager().createQuery("SELECT a from AccionesRealizar a where a.operacionRequerida=:operacion_requerida and a.operacionSolicitada=:operacion_solicitada and a.usuarioNblId=:usuario").setParameter("operacion_requerida", operacion_requerida).setParameter("operacion_solicitada", operacion_solicitada).setParameter("usuario", usuario).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), AccionesRealizarFacade.class.getName(), "ERROR ELIMINANDO LAS ACCIONES POR REALIZAR DEL USUARIO".toUpperCase());
    }
    return null;
  }
  
  public List<AccionesRealizar> porOperacion(OperacionesNbl operacion, UsuariosNbl usuario, String flag)
  {
    try
    {
      Calendar calendarActual = Calendar.getInstance();
      return getEntityManager().createQuery("select a from AccionesRealizar a where a.operacionRequerida=:operacion and a.usuarioNblId=:usuario and a.flag_lectura=:flag and a.fechaAparicion <= :fechaAparicion").setParameter("operacion", operacion).setParameter("usuario", usuario).setParameter("flag", Character.valueOf(flag.charAt(0))).setParameter("fechaAparicion", new Timestamp(calendarActual.getTime().getTime()), TemporalType.TIMESTAMP).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), AccionesRealizarFacade.class.getName(), ("ERROR BUSCANDO ACCIONES POR REALIZAR PARA LA OPERACION" + operacion).toUpperCase());
    }
    return null;
  }
  
  public List<AccionesRealizar> porOperacion(OperacionesNbl operacion, UsuariosNbl usuario, String flag, String nivel)
  {
    try
    {
      Calendar calendarActual = Calendar.getInstance();
      return getEntityManager().createQuery("select a from AccionesRealizar a where a.operacionRequerida=:operacion and a.usuarioNblId=:usuario and a.flag_lectura=:flag and a.nivel=:nivel and a.fechaAparicion <= :fechaAparicion").setParameter("operacion", operacion).setParameter("usuario", usuario).setParameter("flag", Character.valueOf(flag.charAt(0))).setParameter("nivel", Integer.valueOf(Integer.parseInt(nivel))).setParameter("fechaAparicion", new Timestamp(calendarActual.getTime().getTime()), TemporalType.TIMESTAMP).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), AccionesRealizarFacade.class.getName(), ("ERROR BUSCANDO ACCIONES POR REALIZAR PARA LA OPERACION" + operacion).toUpperCase());
    }
    return null;
  }
  
  public List<AccionesRealizar> porOperacionSolicitada(OperacionesNbl operacion, UsuariosNbl usuario, String flag)
  {
    try
    {
      return getEntityManager().createQuery("select a from AccionesRealizar a where a.operacionSolicitada=:operacion and a.usuarioNblId=:usuario and a.flag_lectura=:flag").setParameter("operacion", operacion).setParameter("usuario", usuario).setParameter("flag", Character.valueOf(flag.charAt(0))).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), AccionesRealizarFacade.class.getName(), ("ERROR BUSCANDO ACCIONES POR REALIZAR PARA LA OPERACION" + operacion).toUpperCase());
    }
    return null;
  }
}
