/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.facade;

import com.bod.model.UsuariosNbl;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Named("usuarioNblFacadeV1")
@Stateless(name = "maUsuarioNblFacadev1")
public class UsuarioNblFacadeV1 extends AbstractFacade<UsuariosNbl> {

    @PersistenceContext(unitName = "maPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioNblFacadeV1() {
        super(UsuariosNbl.class);
    }

    public UsuariosNbl porIdentificacion(String tipo, String identificacion, char estado) {
        try {
            //char estatus = BodBaseBean.ESTATUS_USUARIO_DESAFILIADO;
            return (UsuariosNbl) getEntityManager()
                    .createQuery("select u from UsuariosNbl u where u.tipoIdentificacion=:tipo and u.identificacion=:identificacion and u.estatus NOT IN (:estatus)")
                    .setParameter("tipo", tipo)
                    .setParameter("identificacion", identificacion)
                    .setParameter("estatus", estado)
                    .getSingleResult();
        } catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), tipo + "-" + identificacion, "Error consultando en tabla 'USUARIOS_NBL'", e);
            return null;
        }
    }

    public UsuariosNbl porSoloIdentificacion(String tipo, String identificacion) {
        try {
            return (UsuariosNbl) getEntityManager()
                    .createQuery("select u from UsuariosNbl u where u.tipoIdentificacion=:tipo and u.identificacion=:identificacion")
                    .setParameter("tipo", tipo)
                    .setParameter("identificacion", identificacion)
                    .getSingleResult();
        } catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), tipo + "-" + identificacion, "Error consultando en tabla 'USUARIOS_NBL'", e);
            return null;
        }
    }

    public UsuariosNbl porIdentDesafiliado(String tipo, String identificacion, char estado) {
        try {
            Query query = getEntityManager()
                    .createQuery("select u from UsuariosNbl u where u.tipoIdentificacion=:tipo and u.identificacion=:identificacion and u.estatus IN (:estatus)")
                    .setParameter("tipo", tipo)
                    .setParameter("identificacion", identificacion)
                    .setParameter("estatus", estado);
            List<UsuariosNbl> result = (List<UsuariosNbl>) query.getResultList();

            for (UsuariosNbl usuariosNbl : result) {
                return usuariosNbl;
            }
        } catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), tipo + "-" + identificacion, "Error consultando en tabla 'USUARIOS_NBL'", e);
            return null;
        }
        return null;
    }

    public UsuariosNbl porCoreId(String party) {
        try {
            Query query = getEntityManager()
                    .createQuery("select u from UsuariosNbl u JOIN u.clientesHasUsuariosNblCollection c where c.clientes.coreId=:party")
                    .setParameter("party", party);

            if (query.getFirstResult() > 0) {
                for (Object resultList : query.getResultList()) {
                    return (UsuariosNbl) resultList;
                }
            }
        } catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), party, "Error consultando en tabla 'USUARIOS_NBL'", e);
            return null;
        }
        return null;//
    }

    public UsuariosNbl porLogin(String login) {
        try {
            return (UsuariosNbl) getEntityManager()
                    .createQuery("select u from UsuariosNbl u where UPPER(u.login)=:login")
                    .setParameter("login", login.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), login, "Error consultando en tabla 'USUARIOS_NBL'", e);
            return null;
        }
    }

    /**
     * Metodo que realiza la busqueda de un usaurio NBL a traves del atributo de
     * identifiacion de sharedKey de usaurio
     *
     * @param sharedKey identificador de DetectId de usuario
     * @return Entidad de usuarios
     */
    public UsuariosNbl porSharedKey(String sharedKey, char estado) {
        //BodBaseBean.ESTATUS_USUARIO_REGISTRADO
        try {
            return (UsuariosNbl) getEntityManager()
                    .createQuery("select u from UsuariosNbl u where u.sharedkey=:sharedKey and u.estatus=:estatus")
                    .setParameter("sharedKey", sharedKey)
                    .setParameter("estatus", estado)
                    .getSingleResult();
        } catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), sharedKey, "Error consultando en tabla 'USUARIOS_NBL'", e);
            return null;
        }
    }

    public boolean loginRegistrado(String login) {
        try {
            return (null != (String) (getEntityManager()
                    .createNamedQuery("UsuariosNbl.findLoginByValue")
                    .setParameter("value", login).getSingleResult()));
        } catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), login, "Error consultando en tabla 'USUARIOS_NBL'", e);
            return false;
        }
    }

    public Long getUserId(String Login) {
        long iduser;
        try {
            Query q = em.createNamedQuery("UsuariosNbl.findByLogin")
                    .setParameter("login", Login);

            UsuariosNbl Userestado = (UsuariosNbl) q.getSingleResult();
            iduser = Userestado.getId();

        } catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), Login, "No se enconctraron usuarios (tabla 'USUARIOS_NBL')", e);
            iduser = 0;
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), Login, "Error Genérico (tabla 'USUARIOS_NBL')", e);
            // iduser = -1;
            iduser = -1;
        }

        return iduser;
    }

    public List<Long> getUserIdx(String Login) {
        List<UsuariosNbl> usuariox;
        List<Long> usuarioLng = new ArrayList();
        long iduser;
        try {
            Query q = em.createNamedQuery("UsuariosNbl.findByLogin")
                    .setParameter("login", Login);
            //UsuariosNbl Userestado = (UsuariosNbl) q.getSingleResult();
            usuariox = (List<UsuariosNbl>) q.getSingleResult();
            for (int x = 0; x < usuariox.size(); x++) {

                usuarioLng.add(usuariox.get(x).getId());
            }

        } catch (NoResultException e) {
            iduser = 0;
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), Login, "No se enconctraron usuarios (tabla 'USUARIOS_NBL')", e);

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), Login, "Error Genérico (tabla 'USUARIOS_NBL')", e);
            // iduser = -1;
            iduser = -1;
        }

        return usuarioLng;
    }

    public Long getSequence() {
        try {
            return ((BigDecimal) getEntityManager()
                    .createNativeQuery("select USUARIOS_NBL_SEQ.nextval from dual")
                    .getSingleResult()).longValue();

        } catch (NoResultException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Sin resultados (tabla 'USUARIOS_NBL')", e);
            return null;
        }
    }

    public List<String> obtenerLoginDisponibles(List<String> listaSolicitudes) {
        List<String> retorno = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("UsuariosNbl.findLoginByList")
                    .setParameter("login", listaSolicitudes);
            List<String> resultadoConsulta = (List<String>) q.getResultList();

            for (String loginActual : listaSolicitudes) {
                if (!resultadoConsulta.contains(loginActual)) {
                    retorno.add(loginActual);
                }
            }

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error Genérico (tabla 'USUARIOS_NBL')", e);
        }

        return retorno;
    }

    public List<UsuariosNbl> obtenerTodos() {
        Query q = em.createNamedQuery("UsuariosNbl.findAll");
        List<UsuariosNbl> resultadoConsulta = null;

        try {
            resultadoConsulta = (List<UsuariosNbl>) q.getResultList();

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error Genérico (tabla 'USUARIOS_NBL')", e);
        }

        return resultadoConsulta;
    }

    public List<String> construirSugerenciasLogin(String nombre, String segundoNombre, String primerApellido, String segundoApellido, String loginDigitadoCliente, int cantidadSugerencias, int cantidadRandom, boolean soloAleatorias) {
        List<String> sugerenciasRetorno = new ArrayList<>();

        try {
            //Se envia solo aleatorias true, si no se cuenta con los datos del cliete.
            if (!soloAleatorias) {
                //Caso 1. Primera Letra del nombre, seguida del apellido
                if ((!nombre.isEmpty()) && (!primerApellido.isEmpty())) {
                    sugerenciasRetorno.add((nombre.substring(0, 1) + primerApellido).toLowerCase());
                }
                //Caso 2. Primera Letra del primer nombre,mas primera letra del segundo nombre mas el apellido
                if ((!nombre.isEmpty()) && (!primerApellido.isEmpty()) && (!segundoNombre.isEmpty())) {
                    sugerenciasRetorno.add((nombre.substring(0, 1) + segundoNombre.substring(0, 1) + primerApellido).toLowerCase());
                }
                //Caso 3. Primera regla mas primera letra del segundo apellido                        
                if ((!nombre.isEmpty()) && (!primerApellido.isEmpty()) && (!segundoApellido.isEmpty())) {
                    sugerenciasRetorno.add((nombre.substring(0, 1) + primerApellido + segundoApellido.substring(0, 1)).toLowerCase());
                }
                //Caso 4. Segunda Regla mas primera letra del segundo apellido    
                if ((!nombre.isEmpty()) && (!primerApellido.isEmpty()) && (!segundoNombre.isEmpty()) && (!segundoApellido.isEmpty())) {
                    sugerenciasRetorno.add((nombre.substring(0, 1) + segundoNombre + segundoApellido.substring(0, 1)).toLowerCase());
                }
                //Caso 5. Primera regla con orden de elementos invertidos       
                if ((!nombre.isEmpty()) && (!primerApellido.isEmpty())) {
                    sugerenciasRetorno.add((primerApellido + nombre.substring(0, 1)).toLowerCase());
                }
                //Caso 6. Segunda regla con orden de elementos invertidos
                if ((!nombre.isEmpty()) && (!primerApellido.isEmpty()) && (!segundoNombre.isEmpty())) {
                    sugerenciasRetorno.add((primerApellido + nombre.substring(0, 1) + segundoNombre.substring(0, 1)).toLowerCase());
                }
                //Caso 7. Dato de Entrada mas letra primero nombre mas letra segundo nombre        
                if ((!loginDigitadoCliente.isEmpty()) && (!nombre.isEmpty()) && (!segundoNombre.isEmpty())) {
                    sugerenciasRetorno.add((loginDigitadoCliente + nombre.substring(0, 1) + segundoNombre.substring(0, 1)).toLowerCase());
                }
                //Caso 8. Primer nombre mas primera apellido        
                if ((!nombre.isEmpty()) && (!primerApellido.isEmpty())) {
                    sugerenciasRetorno.add((nombre + primerApellido).toLowerCase());
                }
                //Caso 9. Primer nombre mas punto mas primer apellido        
                if ((!nombre.isEmpty()) && (!primerApellido.isEmpty())) {
                    sugerenciasRetorno.add((nombre + "." + primerApellido).toLowerCase());
                }
                //Caso 10. Primer apellido mas punto mas primer nombre 
                if ((!nombre.isEmpty()) && (!primerApellido.isEmpty())) {
                    sugerenciasRetorno.add((primerApellido + "." + nombre).toLowerCase());
                }
            }
            //Si quedo algun caso vigente se da como bueno. 
            if (sugerenciasRetorno.size() > 0) {
                Query q = em.createNamedQuery("UsuariosNbl.findLoginByList")
                        .setParameter("login", sugerenciasRetorno);
                List<String> resultadoConsulta = (List<String>) q.getResultList();
                sugerenciasRetorno.removeAll(resultadoConsulta);
                sugerenciasRetorno = (sugerenciasRetorno.size() >= cantidadSugerencias) ? sugerenciasRetorno.subList(0, cantidadSugerencias) : sugerenciasRetorno;
            } else {//Se generan sugerencias aleatorias.
                //el valor 10 es dado por la cantidad de reglas aplicadas anteriormente.
                for (int x = 0; x < 10; x++) {
                    int ram = new Double(Math.random() * cantidadRandom).intValue();
                    String caseEspecial = loginDigitadoCliente + ram;
                    if (!sugerenciasRetorno.contains(caseEspecial)) {
                        sugerenciasRetorno.add(caseEspecial);
                    }
                }
                Query q = em.createNamedQuery("UsuariosNbl.findLoginByList")
                        .setParameter("login", sugerenciasRetorno);
                List<String> resultadoConsulta = (List<String>) q.getResultList();
                sugerenciasRetorno.removeAll(resultadoConsulta);
                sugerenciasRetorno = (sugerenciasRetorno.size() >= cantidadSugerencias) ? sugerenciasRetorno.subList(0, cantidadSugerencias) : sugerenciasRetorno;
            }

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error Genérico (tabla 'USUARIOS_NBL')", e);
        }
        return sugerenciasRetorno;
    }

    public UsuariosNbl byAzcriptor_id(String azcriptor_id, Character estatus) {
        try {
            return (UsuariosNbl) em.createQuery("SELECT u FROM UsuariosNbl u WHERE u.azcriptor_id =:azcriptor_id AND u.estatus=:estatus")
                    .setParameter("azcriptor_id", azcriptor_id)
                    .setParameter("estatus", estatus)
                    .getSingleResult();

        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error Genérico (tabla 'USUARIOS_NBL')", e);
            return null;
        }
    }
}
