package com.bod.config.constants;

import com.bod.constants.ConsultaPosBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("consultaPosConfig")
@Stateless(name="maconsultaPosConfigNbl")
public class ConsultaPosConfig
  extends ConsultaPosBaseBean
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
      this.configParameter.put(DIR_CODE_PARAM_CURSOR_FROM, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_CURSOR_FROM));
      this.configParameter.put(DIR_CODE_PARAM_REC_COUNT_LIMIT, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_REC_COUNT_LIMIT));
      this.configParameter.put(WSPOS_CURSOR_FORM, this.parametrosFacade.porCodigo(WSPOS_CURSOR_FORM));
      this.configParameter.put(WSPOS_RECCOUNTLIMIT, this.parametrosFacade.porCodigo(WSPOS_RECCOUNTLIMIT));
      this.configParameter.put(USER_WS, this.parametrosFacade.porCodigo(USER_WS));
      this.configParameter.put(PASS_WS, this.parametrosFacade.porCodigo(PASS_WS));
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Advertencia init", e);
    }
    return this.configParameter;
  }
}
