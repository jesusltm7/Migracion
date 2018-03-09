package com.bod.config.constants;

import com.bod.constants.WbfdBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("wbfdConfig")
@Stateless(name="maWbfdConfig")
public class WbfdConfig
  extends WbfdBaseBean
  implements Serializable
{
  Map<String, String> configParameter = new HashMap();
  @EJB
  private ParametrosFacade parametrosFacade;
  
  public Map<String, String> init()
  {
    try
    {
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_WBFD_LOGIN, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_WBFD_LOGIN));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_WBFD_EVENTS, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_WBFD_EVENTS));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_WBFD, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_WBFD));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_WBFD_CARD_TDC, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_WBFD_CARD_TDC));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_WBFD_OFFLINE, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_WBFD_OFFLINE));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_WBFD_ONLINE, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_WBFD_ONLINE));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_WBFD_WSSIP, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_WBFD_WSSIP));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_WBFD_LOAD, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_WBFD_LOAD));
      this.configParameter.put(DIR_CODE_PARAM_TELLERID, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_TELLERID));
      this.configParameter.put(DIR_CODE_PARAM_BRANCHID, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_BRANCHID));
      this.configParameter.put(AUTH_SSL_WS, this.parametrosFacade.porCodigo(AUTH_SSL_WS));
      this.configParameter.put(DIR_CODE_PARAM_BANKID, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_BANKID));
      this.configParameter.put(DIR_CODE_PARAM_CHANNELCODE, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_CHANNELCODE));
      this.configParameter.put(DIR_CODE_PARAM_TELLERID_SIP, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_TELLERID_SIP));
      this.configParameter.put(USER_WS, this.parametrosFacade.porCodigo(USER_WS));
      this.configParameter.put(PASS_WS, this.parametrosFacade.porCodigo(PASS_WS));
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Advertencia init wbfd", e);
    }
    return this.configParameter;
  }
}
