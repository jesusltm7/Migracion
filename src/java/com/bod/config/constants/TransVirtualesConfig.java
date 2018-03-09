package com.bod.config.constants;

import com.bod.constants.TransVirtualesBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("transVirtualesConfigConfig")
@Stateless(name="maTransVirtualesConfigConfig")
public class TransVirtualesConfig
  extends TransVirtualesBaseBean
  implements Serializable
{
  Map<String, String> configParameter = new HashMap();
  @EJB
  private ParametrosFacade parametrosFacade;
  
  public Map<String, String> init()
  {
    try
    {
      this.configParameter.put(DIR_CODE_SERVICE_WSDL, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL));
      this.configParameter.put(AUTH_SSL_WS, this.parametrosFacade.porCodigo(AUTH_SSL_WS));
      this.configParameter.put(DIR_CODE_PARAM_BANKID, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_BANKID));
      this.configParameter.put(DIR_CODE_PARAM_CHANNELCODE, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_CHANNELCODE));
      this.configParameter.put(DIR_CODE_PARAM_BRANCHID, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_BRANCHID));
      this.configParameter.put(DIR_CODE_PARAM_TELLERID, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_TELLERID));
      this.configParameter.put(ENMASCARA_PREFIJO, this.parametrosFacade.porCodigo(ENMASCARA_PREFIJO));
      this.configParameter.put(ENMASCARA_SUFIJO, this.parametrosFacade.porCodigo(ENMASCARA_SUFIJO));
      this.configParameter.put(ENMASCARA_CARACTER, this.parametrosFacade.porCodigo(ENMASCARA_CARACTER));
      this.configParameter.put(USER_WS, this.parametrosFacade.porCodigo(USER_WS));
      this.configParameter.put(PASS_WS, this.parametrosFacade.porCodigo(PASS_WS));
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Advertencia init transacciones virtuales", e);
    }
    return this.configParameter;
  }
}
