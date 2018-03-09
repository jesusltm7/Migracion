package com.bod.config.constants;

import com.bod.beans.BeanMoneda;
import com.bod.constants.CuentasBaseBean;
import com.bod.facade.MonedasFacade;
import com.bod.facade.ParametrosFacade;
import com.bod.model.Monedas;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("cuentasConfig")
@Stateless(name="macuentasConfigNbl")
public class CuentasConfig
  extends CuentasBaseBean
  implements Serializable
{
  Map<String, Object> configParameter = new HashMap();
  @EJB
  private ParametrosFacade parametrosFacade;
  @EJB
  private MonedasFacade monedasFacade;
  
  public Map<String, Object> init()
  {
    try
    {
      this.configParameter.put(DIR_CODE_SERVICE_WSDL, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL));
      this.configParameter.put(AUTH_SSL_WS, this.parametrosFacade.porCodigo(AUTH_SSL_WS));
      this.configParameter.put(DIR_CODE_PARAM_BANKID, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_BANKID));
      this.configParameter.put(DIR_CODE_PARAM_CHANNELCODE, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_CHANNELCODE));
      this.configParameter.put("detalle.cuenta.cant.dia", this.parametrosFacade.porCodigo("detalle.cuenta.cant.dia"));
      this.configParameter.put("acct.validar.maximo.cuentas", this.parametrosFacade.porCodigo("acct.validar.maximo.cuentas"));
      this.configParameter.put("monedas", parseToMonedaBean(this.monedasFacade.findAll()));
      this.configParameter.put(USER_WS, this.parametrosFacade.porCodigo(USER_WS));
      this.configParameter.put(PASS_WS, this.parametrosFacade.porCodigo(PASS_WS));
      this.configParameter.put(ENMASCARA_PREFIJO, this.parametrosFacade.porCodigo(ENMASCARA_PREFIJO));
      this.configParameter.put(ENMASCARA_SUFIJO, this.parametrosFacade.porCodigo(ENMASCARA_SUFIJO));
      this.configParameter.put(ENMASCARA_CARACTER, this.parametrosFacade.porCodigo(ENMASCARA_CARACTER));
    }
    catch (Exception e)
    {
      Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Advertencia init", e);
    }
    return this.configParameter;
  }
  
  public List<BeanMoneda> parseToMonedaBean(List<Monedas> monedas)
  {
    List<BeanMoneda> listBeanMonedas = new ArrayList();
    for (Monedas moneda : monedas)
    {
      BeanMoneda bean = new BeanMoneda();
      
      bean.setId(moneda.getId());
      bean.setCodigo(moneda.getCodigo());
      bean.setNombre(moneda.getNombre());
      bean.setDescripcion(moneda.getDescripcion());
      
      listBeanMonedas.add(bean);
    }
    return listBeanMonedas;
  }
}
