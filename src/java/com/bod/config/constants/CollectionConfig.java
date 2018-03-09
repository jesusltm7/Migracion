package com.bod.config.constants;

import com.bod.constants.CollectionBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("collectionConfig")
@Stateless(name="macollectionConfigNbl")
public class CollectionConfig
  extends CollectionBaseBean
  implements Serializable
{
  Map<String, String> configParameter = new HashMap();
  @EJB
  private ParametrosFacade parametrosFacade;
  
  public Map<String, String> init()
  {
    try
    {
      this.configParameter.put("url.servicio.Collection", this.parametrosFacade.porCodigo("url.servicio.Collection"));
      this.configParameter.put("autenticacion.ssl.collection", this.parametrosFacade.porCodigo("autenticacion.ssl.collection"));
      this.configParameter.put(DIR_CODE_PARAM_BANKID, this.parametrosFacade.porCodigo(CollectionBaseBean.DIR_CODE_PARAM_BANKID));
      this.configParameter.put(DIR_CODE_PARAM_CHANNELCODE, this.parametrosFacade.porCodigo(CollectionBaseBean.DIR_CODE_PARAM_CHANNELCODE));
      this.configParameter.put(USER_WS, this.parametrosFacade.porCodigo(USER_WS));
      this.configParameter.put(PASS_WS, this.parametrosFacade.porCodigo(PASS_WS));
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Advertencia init");
    }
    return this.configParameter;
  }
}
