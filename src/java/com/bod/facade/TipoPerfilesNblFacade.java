package com.bod.facade;

import com.bod.model.TipoPerfilesNbl;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("tipoPerfilesNblFacade")
@Stateless(name="maTipoPerfilesNblFacade")
public class TipoPerfilesNblFacade
  extends AbstractFacade<TipoPerfilesNbl>
{
  @PersistenceContext(unitName="maPU")
  private EntityManager em;
  
  protected EntityManager getEntityManager()
  {
    return this.em;
  }
  
  public TipoPerfilesNblFacade()
  {
    super(TipoPerfilesNbl.class);
  }
  
  public String[] getGlobalFilterFields()
  {
    return new String[] { "nombre", "descripcion", "modo" };
  }
  
  public TipoPerfilesNbl ByCodigo(String codigo)
  {
    if (codigo == null) {
      throw new IllegalArgumentException("Parametro de entrada invalido");
    }
    TipoPerfilesNbl obj = null;
    try
    {
      obj = (TipoPerfilesNbl)getEntityManager().createQuery("select a from TipoPerfilesNbl a where LOWER(a.codigo)=:codigo").setParameter("codigo", codigo.toLowerCase()).getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error consultando la tabla 'TIPO_PERFILES_NBL'", e);
    }
    return obj;
  }
  
  public TipoPerfilesNbl ByNombre(String nombre)
  {
    if (nombre == null) {
      throw new IllegalArgumentException("Parametro de entrada invalido");
    }
    TipoPerfilesNbl obj = null;
    try
    {
      obj = (TipoPerfilesNbl)getEntityManager().createQuery("select m from TipoPerfilesNbl m where UPPER(m.nombre)=:nombre").setParameter("nombre", nombre.toUpperCase()).getSingleResult();
    }
    catch (Exception e)
    {
      Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "NBL", "Error consultando la tabla 'TIPO_PERFILES_NBL'", e);
    }
    return obj;
  }
}
