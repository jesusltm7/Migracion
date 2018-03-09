/*
 clase descompilada war 4
 */
package com.bod.facade;

import com.bod.facade.AbstractFacade;
import com.bod.model.UsuariosNbl;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named(value = "usuarioNblFacade")
@Stateless(name = "maUsuarioNblFacade")
public class UsuarioNblFacade extends AbstractFacade<UsuariosNbl> {

    @PersistenceContext(unitName = "maPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public UsuarioNblFacade() {
        super(UsuariosNbl.class);
    }

    public UsuariosNbl porIdentificacion(String tipo, String identificacion, char estado) {
        try {
            return (UsuariosNbl)this.getEntityManager().createQuery("select u from UsuariosNbl u where u.tipoIdentificacion=:tipo and u.identificacion=:identificacion and u.estatus NOT IN (:estatus)").setParameter("tipo", (Object)tipo).setParameter("identificacion", (Object)identificacion).setParameter("estatus", (Object)Character.valueOf(estado)).getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
        catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), tipo + "-" + identificacion, "Error consultando en tabla 'USUARIOS_NBL'", (Throwable)e);
            return null;
        }
    }

    public UsuariosNbl porSoloIdentificacion(String tipo, String identificacion) {
        try {
            return (UsuariosNbl)this.getEntityManager().createQuery("select u from UsuariosNbl u where u.tipoIdentificacion=:tipo and u.identificacion=:identificacion").setParameter("tipo", (Object)tipo).setParameter("identificacion", (Object)identificacion).getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public UsuariosNbl porIdentDesafiliado(String tipo, String identificacion, char estado) {
        try {
            Query query = this.getEntityManager().createQuery("select u from UsuariosNbl u where u.tipoIdentificacion=:tipo and u.identificacion=:identificacion and u.estatus IN (:estatus)").setParameter("tipo", (Object)tipo).setParameter("identificacion", (Object)identificacion).setParameter("estatus", (Object)Character.valueOf(estado));
            List result = query.getResultList();
            Iterator i$ = result.iterator();
            if (i$.hasNext()) {
                UsuariosNbl usuariosNbl = (UsuariosNbl)i$.next();
                return usuariosNbl;
            }
        }
        catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), tipo + "-" + identificacion, "Error consultando en tabla 'USUARIOS_NBL'", (Throwable)e);
            return null;
        }
        return null;
    }

    public UsuariosNbl porCoreId(String party) {
        try {
            Iterator i$;
            Query query = this.getEntityManager().createQuery("select u from UsuariosNbl u JOIN u.clientesHasUsuariosNblCollection c where c.clientes.coreId=:party").setParameter("party", (Object)party);
            if (query.getFirstResult() > 0 && (i$ = query.getResultList().iterator()).hasNext()) {
                Object resultList = i$.next();
                return (UsuariosNbl)resultList;
            }
        }
        catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), party, "Error consultando en tabla 'USUARIOS_NBL'", (Throwable)e);
            return null;
        }
        return null;
    }

    public UsuariosNbl porLogin(String login) {
        try {
            return (UsuariosNbl)this.getEntityManager().createQuery("select u from UsuariosNbl u where UPPER(u.login)=:login").setParameter("login", (Object)login.toUpperCase()).getSingleResult();
        }
        catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), login, "Error consultando en tabla 'USUARIOS_NBL'", (Throwable)e);
            return null;
        }
    }

    public UsuariosNbl porSharedKey(String sharedKey, char estado) {
        try {
            return (UsuariosNbl)this.getEntityManager().createQuery("select u from UsuariosNbl u where u.sharedkey=:sharedKey and u.estatus=:estatus").setParameter("sharedKey", (Object)sharedKey).setParameter("estatus", (Object)Character.valueOf(estado)).getSingleResult();
        }
        catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), sharedKey, "Error consultando en tabla 'USUARIOS_NBL'", (Throwable)e);
            return null;
        }
    }

    public boolean loginRegistrado(String login) {
        try {
            return null != (String)this.getEntityManager().createNamedQuery("UsuariosNbl.findLoginByValue").setParameter("value", (Object)login).getSingleResult();
        }
        catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), login, "Error consultando en tabla 'USUARIOS_NBL'", (Throwable)e);
            return false;
        }
    }

    public Long getUserId(String Login) {
        long iduser;
        try {
            Query q = this.em.createNamedQuery("UsuariosNbl.findByLogin").setParameter("login", (Object)Login);
            UsuariosNbl Userestado = (UsuariosNbl)q.getSingleResult();
            iduser = Userestado.getId();
        }
        catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), Login, "No se enconctraron usuarios (tabla 'USUARIOS_NBL')", (Throwable)e);
            iduser = 0L;
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), Login, "Error Gen\u00e9rico (tabla 'USUARIOS_NBL')", (Throwable)e);
            iduser = -1L;
        }
        return iduser;
    }

    public List<Long> getUserIdx(String Login) {
        ArrayList<Long> usuarioLng = new ArrayList<Long>();
        try {
            Query q = this.em.createNamedQuery("UsuariosNbl.findByLogin").setParameter("login", (Object)Login);
            List usuariox = (List)q.getSingleResult();
            for (int x = 0; x < usuariox.size(); ++x) {
                usuarioLng.add(((UsuariosNbl)usuariox.get(x)).getId());
            }
        }
        catch (NoResultException e) {
            long iduser = 0L;
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), Login, "No se enconctraron usuarios (tabla 'USUARIOS_NBL')", (Throwable)e);
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), Login, "Error Gen\u00e9rico (tabla 'USUARIOS_NBL')", (Throwable)e);
            long iduser = -1L;
        }
        return usuarioLng;
    }

    public Long getSequence() {
        try {
            return ((BigDecimal)this.getEntityManager().createNativeQuery("select USUARIOS_NBL_SEQ.nextval from dual").getSingleResult()).longValue();
        }
        catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Sin resultados (tabla 'USUARIOS_NBL')", (Throwable)e);
            return null;
        }
    }

    public List<String> obtenerLoginDisponibles(List<String> listaSolicitudes) {
        ArrayList<String> retorno = new ArrayList<String>();
        try {
            Query q = this.em.createNamedQuery("UsuariosNbl.findLoginByList").setParameter("login", listaSolicitudes);
            List resultadoConsulta = q.getResultList();
            for (String loginActual : listaSolicitudes) {
                if (resultadoConsulta.contains(loginActual)) continue;
                retorno.add(loginActual);
            }
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error Gen\u00e9rico (tabla 'USUARIOS_NBL')", (Throwable)e);
        }
        return retorno;
    }

    public List<UsuariosNbl> obtenerTodos() {
        Query q = this.em.createNamedQuery("UsuariosNbl.findAll");
        List resultadoConsulta = null;
        try {
            resultadoConsulta = q.getResultList();
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error Gen\u00e9rico (tabla 'USUARIOS_NBL')", (Throwable)e);
        }
        return resultadoConsulta;
    }

    public List<String> construirSugerenciasLogin(String nombre, String segundoNombre, String primerApellido, String segundoApellido, String loginDigitadoCliente, int cantidadSugerencias, int cantidadRandom, boolean soloAleatorias) {
        List sugerenciasRetorno = new ArrayList<String>();
        try {
            if (!soloAleatorias) {
                if (!nombre.isEmpty() && !primerApellido.isEmpty()) {
                    sugerenciasRetorno.add((nombre.substring(0, 1) + primerApellido).toLowerCase());
                }
                if (!(nombre.isEmpty() || primerApellido.isEmpty() || segundoNombre.isEmpty())) {
                    sugerenciasRetorno.add((nombre.substring(0, 1) + segundoNombre.substring(0, 1) + primerApellido).toLowerCase());
                }
                if (!(nombre.isEmpty() || primerApellido.isEmpty() || segundoApellido.isEmpty())) {
                    sugerenciasRetorno.add((nombre.substring(0, 1) + primerApellido + segundoApellido.substring(0, 1)).toLowerCase());
                }
                if (!(nombre.isEmpty() || primerApellido.isEmpty() || segundoNombre.isEmpty() || segundoApellido.isEmpty())) {
                    sugerenciasRetorno.add((nombre.substring(0, 1) + segundoNombre + segundoApellido.substring(0, 1)).toLowerCase());
                }
                if (!nombre.isEmpty() && !primerApellido.isEmpty()) {
                    sugerenciasRetorno.add((primerApellido + nombre.substring(0, 1)).toLowerCase());
                }
                if (!(nombre.isEmpty() || primerApellido.isEmpty() || segundoNombre.isEmpty())) {
                    sugerenciasRetorno.add((primerApellido + nombre.substring(0, 1) + segundoNombre.substring(0, 1)).toLowerCase());
                }
                if (!(loginDigitadoCliente.isEmpty() || nombre.isEmpty() || segundoNombre.isEmpty())) {
                    sugerenciasRetorno.add((loginDigitadoCliente + nombre.substring(0, 1) + segundoNombre.substring(0, 1)).toLowerCase());
                }
                if (!nombre.isEmpty() && !primerApellido.isEmpty()) {
                    sugerenciasRetorno.add((nombre + primerApellido).toLowerCase());
                }
                if (!nombre.isEmpty() && !primerApellido.isEmpty()) {
                    sugerenciasRetorno.add((nombre + "." + primerApellido).toLowerCase());
                }
                if (!nombre.isEmpty() && !primerApellido.isEmpty()) {
                    sugerenciasRetorno.add((primerApellido + "." + nombre).toLowerCase());
                }
            }
            if (sugerenciasRetorno.size() > 0) {
                Query q = this.em.createNamedQuery("UsuariosNbl.findLoginByList").setParameter("login", sugerenciasRetorno);
                List resultadoConsulta = q.getResultList();
                sugerenciasRetorno.removeAll(resultadoConsulta);
                sugerenciasRetorno = sugerenciasRetorno.size() >= cantidadSugerencias ? sugerenciasRetorno.subList(0, cantidadSugerencias) : sugerenciasRetorno;
            } else {
                for (int x = 0; x < 10; ++x) {
                    int ram = new Double(Math.random() * (double)cantidadRandom).intValue();
                    String caseEspecial = loginDigitadoCliente + ram;
                    if (sugerenciasRetorno.contains(caseEspecial)) continue;
                    sugerenciasRetorno.add(caseEspecial);
                }
                Query q = this.em.createNamedQuery("UsuariosNbl.findLoginByList").setParameter("login", sugerenciasRetorno);
                List resultadoConsulta = q.getResultList();
                sugerenciasRetorno.removeAll(resultadoConsulta);
                sugerenciasRetorno = sugerenciasRetorno.size() >= cantidadSugerencias ? sugerenciasRetorno.subList(0, cantidadSugerencias) : sugerenciasRetorno;
            }
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error Gen\u00e9rico (tabla 'USUARIOS_NBL')", (Throwable)e);
        }
        return sugerenciasRetorno;
    }

    public UsuariosNbl byAzcriptor_id(String azcriptor_id, Character estatus) {
        try {
            return (UsuariosNbl)this.em.createQuery("SELECT u FROM UsuariosNbl u WHERE u.azcriptor_id =:azcriptor_id AND u.estatus=:estatus").setParameter("azcriptor_id", (Object)azcriptor_id).setParameter("estatus", (Object)estatus).getSingleResult();
        }
        catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error Gen\u00e9rico (tabla 'USUARIOS_NBL')", (Throwable)e);
            return null;
        }
    }
}
