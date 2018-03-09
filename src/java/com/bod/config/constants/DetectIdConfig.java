package com.bod.config.constants;

import com.bod.constants.DetectIdBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.facade.TextosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("detectIdConfig")
@Stateless(name="madetectIdConfigNbl")
public class DetectIdConfig
  extends DetectIdBaseBean
  implements Serializable
{
  Map<String, String> configParameter = new HashMap();
  @EJB
  private ParametrosFacade parametrosFacade;
  @EJB
  private TextosFacade textosFacade;
  
  public Map<String, String> init()
  {
    Long ambienteid = null;
    Long idiomaid = null;
    try
    {
      this.configParameter.put(DIR_CODE_SERVICE_WSDL, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL));
      this.configParameter.put(AUTH_SSL_WS, this.parametrosFacade.porCodigo(AUTH_SSL_WS));
      this.configParameter.put(AUTH_SSL_WS_TOKEN, this.parametrosFacade.porCodigo(AUTH_SSL_WS_TOKEN));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_ENTISITE, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_ENTISITE));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_CLIENTS, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_CLIENTS));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_BANK_MAIL, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_BANK_MAIL));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_BANK_SMS, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_BANK_SMS));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_BANK_SMS_AND_MAIL, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_BANK_SMS_AND_MAIL));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_BANK_FINGERPRINT, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_BANK_FINGERPRINT));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_CARD_COORDENADAS, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_CARD_COORDENADAS));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_CLIENT_CARD_COORDENADAS, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_CLIENT_CARD_COORDENADAS));
      this.configParameter.put(DIR_CODE_SERVICE_WSDL_TOKEN, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL_TOKEN));
      this.configParameter.put("cantidad.imagenes", this.parametrosFacade.porCodigo("cantidad.imagenes"));
      this.configParameter.put(coordendas_label_titulo1, this.textosFacade.porCodigo(coordendas_label_titulo1, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo2, this.textosFacade.porCodigo(coordendas_label_titulo2, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo3, this.textosFacade.porCodigo(coordendas_label_titulo3, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_1_p_izquierdo, this.textosFacade.porCodigo(coordendas_label_titulo_1_p_izquierdo, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_1_s_izquierdo, this.textosFacade.porCodigo(coordendas_label_titulo_1_s_izquierdo, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_2_p_izquierdo, this.textosFacade.porCodigo(coordendas_label_titulo_2_p_izquierdo, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_2_s_izquierdo, this.textosFacade.porCodigo(coordendas_label_titulo_2_s_izquierdo, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_2_s_izquierdo_2, this.textosFacade.porCodigo(coordendas_label_titulo_2_s_izquierdo_2, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_2_s_izquierdo_3, this.textosFacade.porCodigo(coordendas_label_titulo_2_s_izquierdo_3, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_2_s_izquierdo_4, this.textosFacade.porCodigo(coordendas_label_titulo_2_s_izquierdo_4, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_1_p_derecho, this.textosFacade.porCodigo(coordendas_label_titulo_1_p_derecho, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_1_s_derecho, this.textosFacade.porCodigo(coordendas_label_titulo_1_s_derecho, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_2_s_derecho, this.textosFacade.porCodigo(coordendas_label_titulo_2_s_derecho, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_3_s_derecho, this.textosFacade.porCodigo(coordendas_label_titulo_3_s_derecho, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_4_s_derecho, this.textosFacade.porCodigo(coordendas_label_titulo_4_s_derecho, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_rif, this.textosFacade.porCodigo(coordendas_label_rif, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_banco, this.textosFacade.porCodigo(coordendas_label_banco, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_twitter, this.textosFacade.porCodigo(coordendas_label_twitter, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_instagram, this.textosFacade.porCodigo(coordendas_label_instagram, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_youtube, this.textosFacade.porCodigo(coordendas_label_youtube, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo4, this.textosFacade.porCodigo(coordendas_label_titulo4, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_coor_1, this.textosFacade.porCodigo(coordendas_label_titulo_coor_1, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_coor_2, this.textosFacade.porCodigo(coordendas_label_titulo_coor_2, ambienteid, idiomaid));
      this.configParameter.put(coordendas_label_titulo_coor_3, this.textosFacade.porCodigo(coordendas_label_titulo_coor_3, ambienteid, idiomaid));
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
