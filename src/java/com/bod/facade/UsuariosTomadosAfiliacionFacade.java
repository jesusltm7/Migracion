package com.bod.facade;

import com.bod.model.UsuariosTomadosAfiliacion;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("usuariosTomadosAfiliacionFacade")
@Stateless(name="maUsuariosTomadosAfiliacionFacade")
public class UsuariosTomadosAfiliacionFacade
  extends AbstractFacade<UsuariosTomadosAfiliacion>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public UsuariosTomadosAfiliacionFacade()
  {
    super(UsuariosTomadosAfiliacion.class);
  }
  
  public UsuariosTomadosAfiliacion porLogin(String login)
  {
    try
    {
      return (UsuariosTomadosAfiliacion)getEntityManager().createQuery("select u from UsuariosTomadosAfiliacion u where UPPER(u.login)=:login").setParameter("login", login.toUpperCase()).getSingleResult();
    }
    catch (NoResultException e) {}
    return null;
  }
  
  public UsuariosTomadosAfiliacion porIdentificacion(String tipo, String identificacion)
  {
    try
    {
      return (UsuariosTomadosAfiliacion)getEntityManager().createQuery("select u from UsuariosTomadosAfiliacion u where u.tipoIdentificacion=:tipo and u.identificacion=:identificacion").setParameter("tipo", Character.valueOf(tipo.charAt(0))).setParameter("identificacion", identificacion).getSingleResult();
    }
    catch (NoResultException e) {}
    return null;
  }
  
  public Long getSequence()
  {
    try
    {
      return Long.valueOf(((BigDecimal)getEntityManager().createNativeQuery("select USUARIOS_TOMADOS_A_SEQ.nextval from dual").getSingleResult()).longValue());
    }
    catch (NoResultException e) {}
    return null;
  }
}
