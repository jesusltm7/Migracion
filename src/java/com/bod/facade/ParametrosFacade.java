package com.bod.facade;

import com.bod.model.Parametros;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 *
 * @author Usuario
 */
@Named("parametrosFacade")
@Stateless(name = "maParametrosFacade")
public class ParametrosFacade extends AbstractFacade<Parametros> {

    private static final CacheManager CACHE_MANAGER = CacheManager.getInstance();

    @PersistenceContext(unitName = "maPU")
    private EntityManager em;

    public ParametrosFacade() {
        super(Parametros.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public String porCodigo(String codigo) {
        Parametros p = parametroPorCodigo(codigo);
        if (p != null) {
            return p.getValorPorDefecto();
        } else {
            return "";
        }
    }

    public Parametros parametroPorCodigo(String codigo) {

        try {
            Element elem = getCache().get(codigo);
            if (elem == null) {
                Parametros l;
                l = (Parametros) getEntityManager()
                        .createQuery("select o from Parametros o where o.codigo=:codigo")
                        .setParameter("codigo", codigo)
                        .getSingleResult();
                getCache().put(new Element(codigo, l));
                return l;
            } else {
                return ((Parametros) elem.getObjectValue());
            }

        } catch (Exception e) {
//            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo parametroPorCodigo", e);
            return null;
        }
    }

    public String porCodigoNoAutenticado(String codigo) {
        return porCodigo(codigo);
    }

    /**
     *
     * @param codigoParametro
     * @return descripcion asociada al còdigo parametro
     */
    public String descripcionDe(String codigoParametro) {
        Parametros p = parametroPorCodigo(codigoParametro);
        if (p != null) {
            return p.getDescripcion();
        } else {
            return "";
        }
    }

    public String valorPorCodigo(String codigo) {
        return porCodigo(codigo);
    }

    public String nombrePorValorTipo(String valor, String tipo) {
        try {
            Parametros param = (Parametros) getEntityManager()
                    .createQuery("select o from Parametros o where o.valorPorDefecto=:valor and o.tipoParametroId.codigo=:tipo")
                    .setParameter("valor", valor)
                    .setParameter("tipo", tipo)
                    .getSingleResult();
            return param.getNombre();
        } catch (Exception ex) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo nombrePorValorTipo(String valor, String tipo) = ", ex);
            return "";
        }
    }

    /**
     *
     * @param prefix
     * @param sufix
     * @return
     */
    public List<Parametros> parametrosPorPrefijoSufijo(String prefix, String sufix) {
        try {
            return (List<Parametros>) getEntityManager()
                    .createQuery("select o from Parametros o where o.codigo LIKE :prefix ")
                    .setParameter("prefix", prefix + '%' + sufix)
                    .getResultList();
        } catch (Exception ex) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo parametrosPorPrefijoSufijo(String prefix, String sufix) = ", ex);
            return null;
        }
    }

    public List<Parametros> parametrosPorTipo(String tipo) {
        try {
            return (List<Parametros>) getEntityManager()
                    .createQuery("select o from Parametros o where o.tipoParametroId.codigo=:tipo")
                    .setParameter("tipo", tipo)
                    .getResultList();
        } catch (Exception ex) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error en el metodo parametrosPorTipo(String tipo) = ", ex);
            return null;
        }
    }

    private Ehcache getCache() {
        return CACHE_MANAGER.getEhcache("parametros");
    }
}
