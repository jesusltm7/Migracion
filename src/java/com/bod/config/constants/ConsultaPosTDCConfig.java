package com.bod.config.constants;

import com.bod.constants.ConsultaPosTDCBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("consultaPosTDCConfig")
@Stateless(name="maconsultaPosTDCConfigNbl")
public class ConsultaPosTDCConfig
  extends ConsultaPosTDCBaseBean
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
      this.configParameter.put(DIR_CODE_PARAM_RECCOUNTLIMIT, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_RECCOUNTLIMIT));
      this.configParameter.put(DIR_CODE_PARAM_CURSORFROM, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_CURSORFROM));
      this.configParameter.put(CAN_REG_LEER_TDC_1, this.parametrosFacade.porCodigo(CAN_REG_LEER_TDC_1));
      this.configParameter.put(CAN_REG_LEER_TDC_2, this.parametrosFacade.porCodigo(CAN_REG_LEER_TDC_2));
      this.configParameter.put(CAN_REG_LEER_TDC_3, this.parametrosFacade.porCodigo(CAN_REG_LEER_TDC_3));
      this.configParameter.put(CAN_REG_LEER_TDC_4, this.parametrosFacade.porCodigo(CAN_REG_LEER_TDC_4));
      this.configParameter.put(CAN_REG_LEER_TDC_5, this.parametrosFacade.porCodigo(CAN_REG_LEER_TDC_5));
      this.configParameter.put(CAN_REG_LEER_TDC_6, this.parametrosFacade.porCodigo(CAN_REG_LEER_TDC_6));
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
