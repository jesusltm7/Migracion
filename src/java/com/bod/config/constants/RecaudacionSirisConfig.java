package com.bod.config.constants;

import com.bod.constants.RecaudacionSirisBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("recaudacionSirisConfig")
@Stateless(name="marecaudacionSirisConfigNbl")
public class RecaudacionSirisConfig
  extends RecaudacionSirisBaseBean
  implements Serializable
{
  Map<String, String> configParameter = new HashMap();
  @EJB
  private ParametrosFacade parametrosFacade;
  
  public Map<String, String> init()
  {
    try
    {
      this.configParameter.put(DIR_CODE_SERVICE_WSDL, this.parametrosFacade.porCodigo(RecaudacionSirisBaseBean.DIR_CODE_SERVICE_WSDL));
      this.configParameter.put(AUTENTICACION_SSL_SIRIS, this.parametrosFacade.porCodigo(RecaudacionSirisBaseBean.AUTENTICACION_SSL_SIRIS));
      this.configParameter.put(DIR_CODE_PARAM_BANKID, this.parametrosFacade.porCodigo(RecaudacionSirisBaseBean.DIR_CODE_PARAM_BANKID));
      this.configParameter.put(DIR_CODE_PARAM_CHANNELCODE, this.parametrosFacade.porCodigo(RecaudacionSirisBaseBean.DIR_CODE_PARAM_CHANNELCODE));
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
