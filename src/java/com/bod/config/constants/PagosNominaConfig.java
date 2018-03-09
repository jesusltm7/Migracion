package com.bod.config.constants;

import com.bod.constants.PagosNominaBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("pagosNominaConfig")
@Stateless(name="mapagosNominaConfigNbl")
public class PagosNominaConfig
  extends PagosNominaBaseBean
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
      this.configParameter.put(PAGOS_SERVICIOS_SIP_TELLER_ID, this.parametrosFacade.porCodigo(PAGOS_SERVICIOS_SIP_TELLER_ID));
      this.configParameter.put(PAGOS_SERVICIOS_MODE, this.parametrosFacade.porCodigo(PAGOS_SERVICIOS_MODE));
      this.configParameter.put(USER_WS, this.parametrosFacade.porCodigo(USER_WS));
      this.configParameter.put(PASS_WS, this.parametrosFacade.porCodigo(PASS_WS));
      try
      {
        String rellamado = this.parametrosFacade.porCodigo("nomina.rellamado.iteracion");
        rellamado = (null == rellamado) || (rellamado.trim().equalsIgnoreCase("")) ? "15" : rellamado;
        this.configParameter.put(RELLAMADOS_SIP, rellamado);
      }
      catch (Exception e2)
      {
        this.configParameter.put(RELLAMADOS_SIP, "15");
      }
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Advertencia init", e);
    }
    return this.configParameter;
  }
}
