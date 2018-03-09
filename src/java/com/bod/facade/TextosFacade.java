/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.facade;

import com.bod.model.Textos;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 *
 * @author Usuario
 */
//@Named("textosFacade")
@Stateless(name = "maTextosFacade")
public class TextosFacade extends AbstractFacade<Textos> {

    private static final CacheManager cacheManager = CacheManager.getInstance();

    @PersistenceContext(unitName = "maPU")
    private EntityManager em;

    public TextosFacade() {
        super(Textos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public String porCodigoX(String codigo, String[] parametros) {
        String texto = porCodigo(codigo,null,null);

        if (parametros != null) {
            for (int i = 0; i < parametros.length; i++) {
                if (parametros[i] != null) {
                    texto = texto.replace("$" + (i + 1), parametros[i]);
                }
            }
        }

        return texto;
    }

    public String porCodigo(String codigo, String p1) {
        return this.porCodigoX(codigo, new String[]{p1});
    }

    public String porCodigo2(String codigo, String p1, String p2) {
        return this.porCodigoX(codigo, new String[]{p1, p2});
    }

    public String porCodigo3(String codigo, String p1, String p2, String p3) {
        return this.porCodigoX(codigo, new String[]{p1, p2, p3});
    }

    public String errorServicio(String codigoError) {
        if (codigoError == null || codigoError.trim().isEmpty()) {
            return "";
        }

        String codigo;

        if (codigoError.indexOf("PLA") == 0) {
            codigo = codigoError.substring(3);
        } else {
            codigo = codigoError;
        }

        return porCodigo("error.servicio." + codigo,null,null);
    }

    public String porCodigosUnidos(String prefix, String postfix) {
        return porCodigo(prefix + postfix,null,null);
    }

    /**
     * Obtener listado de texto por tipo de texto
     *
     * @param TipoTexto
     * @return
     */
    public List listaPorTipo(String TipoTexto) {
        Element elem;
        List<Textos> list = null;

        try {
            list = (List<Textos>) getEntityManager()
                    .createQuery("select t from Textos t where t.tipoTextoId.codigo=:codigo")
                    .setParameter("codigo", TipoTexto)
                    .getResultList();
            getCache().put(elem = new Element(TipoTexto, list));
            list = (List<Textos>) elem.getValue();
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en la consulta (tabla Textos)", e);
        }
        return list;
    }

    /**
     *
     * @param tipoTexto
     * @param codigo
     * @return
     */
    public List<Textos> listaPorTipoYCodigo(String tipoTexto, String codigo) {
        Element elem;
        List<Textos> list = null;

        try {
            list = (List<Textos>) getEntityManager()
                    .createQuery("select t from Textos t where t.tipoTextoId.codigo=:tipoTexto and t.codigo LIKE :codigo")
                    .setParameter("tipoTexto", tipoTexto)
                    .setParameter("codigo", codigo + "%")
                    .getResultList();
            getCache().put(elem = new Element(tipoTexto, list));
            list = (List<Textos>) elem.getValue();

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en la consulta (tabla Textos)", e);
        }
        return list;
    }

    /**
     * Listado de textos con el prefijo y sufijo
     *
     * @param prefix
     * @param sufix
     * @return
     */
    public List porPrefijoYsufijo(String prefix, String sufix) {
        List<Textos> l = null;

        Element elem;

        try {

            l = (List<Textos>) getEntityManager()
                    .createQuery("select t from Textos t where t.codigo LIKE :codigo")
                    .setParameter("codigo", prefix + "%" + sufix)
                    .getResultList();
            getCache().put(elem = new Element(prefix, l));

            l = (List<Textos>) elem.getValue();

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en la consulta (tabla Textos)", e);
        }

        return l;
    }

    /**
     * Listado de textos con el mismo prefix
     *
     * @param prefix
     * @param Ambiente
     * @param Idioma
     * @return
     */
    public List porPrefijo(String prefix,Long Ambiente,Long Idioma) {

        Element elem;
        List<Textos> l = null;
        List<Textos> listaTextos = null;
        try {

            l = (List<Textos>) getEntityManager()
                    .createQuery("select t from Textos t where t.codigo LIKE :codigo")
                    .setParameter("codigo", prefix + "%")
                    .getResultList();
            getCache().put(elem = new Element(prefix, l));

            listaTextos = new ArrayList<>();
            if (Ambiente != null) {
                // Buscar el objeto para el idioma y ambiente actual
                if (Idioma != null) {
                    for (Textos i : l) {
                        if (Objects.equals(i.getAmbientesId().getId(), Ambiente)
                                || Objects.equals(i.getIdiomasId().getId(), Idioma)) {
                            listaTextos.add(i);
                        }
                    }
                }

                // Buscar el objeto para el ambiente actual y el idioma neutro
                for (Textos i : l) {
                    if (Objects.equals(i.getAmbientesId().getId(), Ambiente)
                            && i.getIdiomasId().getId() == 1) {
                        listaTextos.add(i);
                    }
                }
            } else {
                // Buscar el objeto para ambiente e idioma neutro
                for (Textos i : l) {
                    if (i.getAmbientesId().getId() == 1
                            && i.getIdiomasId().getId() == 1) {
                        listaTextos.add(i);
                    }
                }
            }
            //l = (List<Textos>) elem.getValue();

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en la consulta (tabla Textos)", e);
        }

        return listaTextos;
    }

    public String porCodigo(String codigo,Long Ambiente,Long Idioma) {

        try {

//        java.sql.Connection connection = em.unwrap(java.sql.Connection.class);
            List<Textos> l;

            Element elem = getCache().get(codigo);

            if (elem == null) {
                l = (List<Textos>) getEntityManager()
                        .createQuery("select t from Textos t where t.codigo=:codigo")
                        .setParameter("codigo", codigo)
                        .getResultList();
                getCache().put(elem = new Element(codigo, l));
            }

            l = (List<Textos>) elem.getValue();

            if (Ambiente != null) {

                // Buscar el objeto para el idioma y ambiente actual
                if (Idioma != null) {
                    for (Textos i : l) {
                        if (Objects.equals(i.getAmbientesId().getId(), Ambiente)
                                || Objects.equals(i.getIdiomasId().getId(), Idioma)) {
                            return i.getTexto();
                        }
                    }
                }

                // Buscar el objeto para el ambiente actual y el idioma neutro
                for (Textos i : l) {
                    if (Objects.equals(i.getAmbientesId().getId(), Ambiente)
                            && i.getIdiomasId().getId() == 1) {
                        return i.getTexto();
                    }
                }
            }

            // Buscar el objeto para ambiente e idioma neutro
            for (Textos i : l) {
                if (i.getAmbientesId().getId() == 1
                        && i.getIdiomasId().getId() == 1) {
                    return i.getTexto();
                }
            }

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error consultando por código (tabla Textos)", e);
        }

        return "";
    }

    /**
     * Class created by joaquin Carrasquero: Se crea para filtrar correctamente
     * los codigos por ambiente y por canal, Consta de tres metodos, el primero
     * busca el codigo filtrando por el ambiente e idioma que exista se
     * encuentre en el dataSesion, si eso falla entonces, entra en el segundo
     * metodo el cual lo busca por el ambiente existente en el dataSesion y el
     * idioma por defecto, si eso falla, entonces lo busca por el ambiente e
     * idioma por defecto los cuales son extraidos del dataSesion.
     *
     * @param codigo
     * @return
     */
//    public String porCodigo(String codigo) {
//
//        try {
//
//            Textos li;
//            if (dataSesion != null) {
//
//                // Buscar el objeto para el idioma y ambiente actual
//                if (dataSesion.getIdioma() != null && dataSesion.getIdioma().getId() != null && dataSesion.getAmbienteId() != null) {
//
//                    li = (Textos) getEntityManager()
//                            .createQuery("select t from Textos t where t.codigo=:codigo and t.ambientesId=:ambienteId and t.idiomasId=:idiomasId")
//                            .setParameter("codigo", codigo)
//                            .setParameter("ambienteId", dataSesion.getAmbiente())
//                            .setParameter("idiomasId", dataSesion.getIdioma())
//                            .getSingleResult();
//
//                    if (li != null) {
//                        return li.getTexto();
//                    }
//                }
//                if (dataSesion.getIdiomaDefecto() != null && dataSesion.getIdiomaDefecto().getId() != null) {
//                    if (dataSesion.getAmbienteId() != null) {
//                        li = (Textos) getEntityManager()
//                                .createQuery("select t from Textos t where t.codigo=:codigo and t.ambientesId=:ambienteId and t.idiomasId=:idiomasId ")
//                                .setParameter("codigo", codigo)
//                                .setParameter("ambienteId", dataSesion.getAmbiente())
//                                .setParameter("idiomasId", dataSesion.getIdiomaDefecto())
//                                .getSingleResult();
//                        if (li != null) {
//                            return li.getTexto();
//                        }
//                    }
//                }
//
//            }
//
//            // Buscar el objeto para ambiente e idioma neutro
//            if (dataSesion.getIdiomaDefecto() != null && dataSesion.getIdiomaDefecto().getId() != null
//                    && dataSesion.getAmbienteDefecto() != null && dataSesion.getAmbienteDefecto().getId() != null) {
//                li = (Textos) getEntityManager()
//                        .createQuery("select t from Textos t where t.codigo=:codigo and t.ambientesId=:ambienteId and t.idiomasId=:idiomasId ")
//                        .setParameter("codigo", codigo)
//                        .setParameter("ambienteId", dataSesion.getAmbienteDefecto())
//                        .setParameter("idiomasId", dataSesion.getIdiomaDefecto())
//                        .getSingleResult();
//                if (li != null) {
//                    return li.getTexto();
//                }
//            }
//
//            return "";
//
//        } catch (Exception e) {
//            System.out.println("The system is going to print void" + e);
//            return "";
//        }
//
//    }
    /**
     * Buscar textos por categoría
     *
     * @param coditoCategoria
     * @param Ambiente
     * @param Idioma
     * @return
     */
    public Map<String, String> porCategoria(String coditoCategoria,Long Ambiente,Long Idioma) {
        Map<String, String> map = new HashMap();

        List<Textos> list = null;

        try {
            list = (List<Textos>) getEntityManager()
                    .createQuery("select t from Textos t where t.tipoTextoId.codigo=:codigo")
                    .setParameter("codigo", coditoCategoria)
                    .getResultList();

            if (Ambiente != null) {
                if (Idioma != null) {
                    // Buscar el objeto para el idioma y ambiente actual                
                    for (Textos i : list) {
                        if (Objects.equals(i.getAmbientesId().getId(), Ambiente)
                                && Objects.equals(i.getIdiomasId().getId(), Idioma)) {
                            map.put(i.getCodigo(), i.getTexto());
                        }
                    }
                }

                // Buscar el objeto para el ambiente actual y el idioma neutro
                for (Textos i : list) {
                    if (Objects.equals(i.getAmbientesId().getId(), Ambiente)
                            && i.getIdiomasId().getId() == 1) {
                        if (!map.containsKey(i.getCodigo())) {
                            map.put(i.getCodigo(), i.getTexto());
                        }
                    }
                }
            }

            // Buscar el objeto para ambiente e idioma neutro
            for (Textos i : list) {
                if (i.getAmbientesId().getId() == 1
                        && i.getIdiomasId().getId() == 1) {
                    if (!map.containsKey(i.getCodigo())) {
                        map.put(i.getCodigo(), i.getTexto());
                    }
                }
            }

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en la consulta (tabla Textos)", e);
        }

        return map;
    }

    /**
     * Metodo qeu realiza la busqueda de los todos los textos asociados a un
     * determinado codigo el cual devuelve los status a manejar en las
     * solicitudes cadivi
     *
     * @return Devuelve un mapa con una llave de referencia y la descripcion del
     * campo
     */
    public Map<String, String> statusCadivi(String fuffixCodeCadivi,Long Ambiente,Long Idioma) {
        //return porCategoria(BodBaseBean.SUFFIX_CODE_STS_CADIVI);
        return porCategoria(fuffixCodeCadivi,Ambiente,Idioma);
    }

    /**
     * Metodo que realiza la busqueda de los todos los textos asociados a un
     * determinado codigo el cual devuelve los tipos de solicitudes cadivi
     *
     * @return Devuelve un mapa con una llave de referencia y la descripcion del
     * campo
     */
    public Map<String, String> tipoSolCadivi(String fuffixCodeCadivi,Long Ambiente,Long Idioma) {
        //return porCategoria(BodBaseBean.SUFFIX_CODE_TYPE_CADIVI);
        return porCategoria(fuffixCodeCadivi,Ambiente,Idioma);
    }

    /**
     * Metodo que realiza la busqueda de todos los estatus que maneja una TDC de
     * un determinado usuario. Visa/Master En blanco = Activa C = Cancelada P =
     * Perdida/Robada R = Retenida
     *
     * Amex En blanco = Activa Cualquier otro valor es cancelada
     *
     * @param fuffixCodeCadivi
     * @param Ambiente
     * @param Idioma
     * @return Estatus TDC
     */
    public Map<String, String> statusTdc(String fuffixStatusTdc,Long Ambiente,Long Idioma) {
        return porCategoria(fuffixStatusTdc,Ambiente,Idioma);
    }

    /**
     * Metodo que realiza la busqueda de todos los estatus que maneja las
     * cuentas bancarias de un usuario en especifico ESTADO DE LA CUENTA A =
     * Cuenta Activa. C = Cuenta Cancelada I = Cuenta Inactiva- 1 D = Cuenta
     * Inactiva- 2 (DURMIENDO) O = Cuenta Controlada E = Embargada T = Acepta
     * Solo Depositos (Bloqueada)
     *
     * @param fuffixCodeCadivi
     * @param Ambiente
     * @param Idioma
     * @return
     */
    public Map<String, String> statusAcct(String fuffixCodeCadivi,Long Ambiente,Long Idioma) {
       // return porCategoria(BodBaseBean.SUFFIX_CODE_STATUS_ACCT);
        return porCategoria(fuffixCodeCadivi,Ambiente,Idioma);
    }

    /**
     * Método que busca los estatus genéricos (A: Activo, I: Inactivo)
     *
     * @param Ambiente
     * @param Idioma
     * @return
     */
    public Map<String, String> statusGenerico(Long Ambiente,Long Idioma) {
        return porCategoria("estatus.generico",Ambiente,Idioma);
    }

    private Ehcache getCache() {
        return cacheManager.getEhcache("textos");
    }

    private Object setParameter(String codigo, String tod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
