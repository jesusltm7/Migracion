package com.bod.facade;

import com.bod.model.Clientes;
import com.bod.model.ClientesHasUsuariosNbl;
import com.bod.model.Mstmedioenvio;
import com.bod.model.Msttiposcondicion;
import com.bod.model.PerfilesNbl;
import com.bod.model.UsuariosNbl;
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

@Named("clientesHasUsuariosNblFacade")
@Stateless(name="maClientesHasUsuariosNblFacade")
public class ClientesHasUsuariosNblFacade
  extends AbstractFacade<ClientesHasUsuariosNbl>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public ClientesHasUsuariosNblFacade()
  {
    super(ClientesHasUsuariosNbl.class);
  }
  
  public ClientesHasUsuariosNbl porCliente(Clientes cliente)
  {
    try
    {
      return (ClientesHasUsuariosNbl)getEntityManager().createQuery("select c from ClientesHasUsuariosNbl c where c.clientes=:cliente").setParameter("cliente", cliente).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClientesHasUsuariosNbl> porClienteEstadoCondicionLista(Clientes cliente, Character estatus, Msttiposcondicion fkIdtipocondicion)
  {
    try
    {
      return getEntityManager().createQuery("select c from ClientesHasUsuariosNbl c where c.clientes=:cliente and c.estatus = :estatus and c.fkIdtipocondicion = :fkIdtipocondicion").setParameter("cliente", cliente).setParameter("estatus", estatus).setParameter("fkIdtipocondicion", fkIdtipocondicion).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ENCONTRADO porClienteEstadoCondicionLista = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClientesHasUsuariosNbl> porClienteEstadoCondicion(Clientes cliente, Character estatus, Msttiposcondicion fkIdtipocondicion)
  {
    try
    {
      return getEntityManager().createQuery("select c from ClientesHasUsuariosNbl c where c.clientes=:cliente and c.estatus = :estatus and c.fkIdtipocondicion = :fkIdtipocondicion").setParameter("cliente", cliente).setParameter("estatus", estatus).setParameter("fkIdtipocondicion", fkIdtipocondicion).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public ClientesHasUsuariosNbl porClienteUsuario(Clientes cliente, UsuariosNbl user)
  {
    try
    {
      return (ClientesHasUsuariosNbl)getEntityManager().createQuery("select c from ClientesHasUsuariosNbl c where c.clientes=:cliente and c.usuariosNbl=:usuario").setParameter("cliente", cliente).setParameter("usuario", user).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public ClientesHasUsuariosNbl porClienteUsuarioEstado(Clientes cliente, UsuariosNbl user, Character estatus)
  {
    try
    {
      return (ClientesHasUsuariosNbl)getEntityManager().createQuery("select c from ClientesHasUsuariosNbl c where c.clientes=:cliente and c.usuariosNbl=:usuario and c.estatus = :estatus").setParameter("cliente", cliente).setParameter("usuario", user).setParameter("estatus", estatus).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public List<ClientesHasUsuariosNbl> porUsuario(UsuariosNbl user)
  {
    try
    {
      return getEntityManager().createQuery("select c from ClientesHasUsuariosNbl c where c.usuariosNbl=:usuario").setParameter("usuario", user).getResultList();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public Mstmedioenvio envioNotificaciones(Clientes cliente, UsuariosNbl user)
  {
    try
    {
      return (Mstmedioenvio)getEntityManager().createQuery("select c.fkIdtipoenvio from ClientesHasUsuariosNbl c where c.clientes=:cliente and c.usuariosNbl=:usuario").setParameter("cliente", cliente).setParameter("usuario", user).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public void modificarEnvioNotificaciones(Clientes cliente, UsuariosNbl user, Mstmedioenvio idTipoEnvio)
  {
    try
    {
      ClientesHasUsuariosNbl clienteHasUsuario = porClienteUsuario(cliente, user);
      clienteHasUsuario.setFkIdtipoenvio(idTipoEnvio);
      edit(clienteHasUsuario);
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
  }
  
  public ClientesHasUsuariosNbl porPerfil(PerfilesNbl perfilesNbl)
  {
    try
    {
      return (ClientesHasUsuariosNbl)getEntityManager().createNamedQuery("ClientesHasUsuariosNbl.findByPerfilesNblId", ClientesHasUsuariosNbl.class).setParameter("perfilesNblId", perfilesNbl).getSingleResult();
    }
    catch (NoResultException e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ENCONTRADO = {0}" + e).toUpperCase());
    }
    return null;
  }
  
  public void actualizarPerfilesPorUsuario(UsuariosNbl usuariosNbl)
  {
    try
    {
      getEntityManager().createNativeQuery("UPDATE CLIENTES_HAS_USUARIOS_NBL SET ZONASEGURA=0 WHERE USUARIOS_NBL_ID = ?").setParameter(1, usuariosNbl.getId()).executeUpdate();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR ACTUALIZANDO PERFILES ENCONTRADO = {0}" + e).toUpperCase());
    }
  }
  
  public int obtenerCantidadPerfilesActivos(UsuariosNbl usuariosNbl)
  {
    int cantidadPerfiles = 0;
    try
    {
      List<Number> counts = this.em.createNativeQuery("SELECT COUNT(*) counts FROM CLIENTES_HAS_USUARIOS_NBL WHERE ESTATUS='A' and USUARIOS_NBL_ID = ?").setParameter(1, usuariosNbl.getId()).getResultList();
      
      cantidadPerfiles = ((Number)counts.get(0)).intValue();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR CONTANTO PERFILES ACTIVOS ENCONTRADO = {0}" + e).toUpperCase());
    }
    return cantidadPerfiles;
  }
  
  public ClientesHasUsuariosNbl obtenerPerfilDefecto(UsuariosNbl usuariosNbl)
  {
    ClientesHasUsuariosNbl clientesHasUsuariosNbl = null;
    try
    {
      List<ClientesHasUsuariosNbl> perfiles = this.em.createNativeQuery("SELECT * FROM CLIENTES_HAS_USUARIOS_NBL WHERE USUARIOS_NBL_ID = ? AND CLIENTES_ID = ?", ClientesHasUsuariosNbl.class).setParameter(1, usuariosNbl.getId()).setParameter(2, usuariosNbl.getPerfilDef()).getResultList();
      
      clientesHasUsuariosNbl = (ClientesHasUsuariosNbl)perfiles.get(0);
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR CONTANTO PERFILES ACTIVOS ENCONTRADO = {0}" + e).toUpperCase());
    }
    return clientesHasUsuariosNbl;
  }
  
  public ClientesHasUsuariosNbl obtenerPerfilActivo(UsuariosNbl usuariosNbl)
  {
    ClientesHasUsuariosNbl clientesHasUsuariosNbl = null;
    try
    {
      List<ClientesHasUsuariosNbl> perfiles = this.em.createNativeQuery("SELECT * FROM CLIENTES_HAS_USUARIOS_NBL WHERE ESTATUS='A' and USUARIOS_NBL_ID = ?", ClientesHasUsuariosNbl.class).setParameter(1, usuariosNbl.getId()).getResultList();
      
      clientesHasUsuariosNbl = (ClientesHasUsuariosNbl)perfiles.get(0);
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "", ("ERROR CONTANTO PERFILES ACTIVOS ENCONTRADO = {0}" + e).toUpperCase());
    }
    return clientesHasUsuariosNbl;
  }
}
