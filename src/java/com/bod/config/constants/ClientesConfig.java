package com.bod.config.constants;

import com.bod.beans.BeanMoneda;
import com.bod.beans.BeansEstadosTDC;
import com.bod.constants.ClientesBaseBean;
import com.bod.facade.MonedasFacade;
import com.bod.facade.MstestadotdcFacade;
import com.bod.facade.ParametrosFacade;
import com.bod.facade.TextosFacade;
import com.bod.model.Monedas;
import com.bod.model.Mstestadotdc;
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

@Named("clientesConfig")
@Stateless(name="maclientesConfigNbl")
public class ClientesConfig
  extends ClientesBaseBean
  implements Serializable
{
  Map<String, Object> configParameter = new HashMap();
  @EJB
  private ParametrosFacade parametrosFacade;
  @EJB
  private MstestadotdcFacade mstestadotdcFacade;
  @EJB
  private MonedasFacade monedasFacade;
  @EJB
  private TextosFacade textosFacade;
  
  public Map<String, Object> init()
  {
    Long ambienteid = null;
    Long idiomaid = null;
    try
    {
      this.configParameter.put(DIR_CODE_SERVICE_WSDL, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL));
      this.configParameter.put(AUTH_SSL_WS, this.parametrosFacade.porCodigo(AUTH_SSL_WS));
      this.configParameter.put(DIR_CODE_PARAM_BANKID, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_BANKID));
      this.configParameter.put(DIR_CODE_PARAM_CHANNELCODE, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_CHANNELCODE));
      this.configParameter.put(DIR_CODE_BANK_ID_05, this.parametrosFacade.porCodigo(DIR_CODE_BANK_ID_05));
      this.configParameter.put(DIR_CODE_PARAM_RECCOUNT_LIMIT, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_RECCOUNT_LIMIT));
      this.configParameter.put(DIR_CODE_PARAM_CURSOR_FORM, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_CURSOR_FORM));
      this.configParameter.put(IDENTIFICACION_DOCUMENTO_PASAPORTE, this.parametrosFacade.porCodigo(IDENTIFICACION_DOCUMENTO_PASAPORTE));
      this.configParameter.put(IDENTIFICACION_MENOR, this.parametrosFacade.porCodigo(IDENTIFICACION_MENOR));
      this.configParameter.put(IDENTIFICACION_JURIDICO, this.parametrosFacade.porCodigo(IDENTIFICACION_JURIDICO));
      this.configParameter.put(IDENTIFICACION_GOBIERNO, this.parametrosFacade.porCodigo(IDENTIFICACION_GOBIERNO));
      this.configParameter.put(IDENTIFICACION_DOCUMENTO_RIF, this.parametrosFacade.porCodigo(IDENTIFICACION_DOCUMENTO_RIF));
      this.configParameter.put(ESTADO_TDC_CASTIGADA, this.textosFacade.porCodigo(ESTADO_TDC_CASTIGADA, ambienteid, idiomaid));
      this.configParameter.put(ESTADOS_TDC, parseEstadoTDCBean(this.mstestadotdcFacade.findAllEstados()));
      this.configParameter.put(MONEDAS, parseToMonedaBean(this.monedasFacade.findAll()));
      this.configParameter.put(USER_WS, this.parametrosFacade.porCodigo(USER_WS));
      this.configParameter.put(PASS_WS, this.parametrosFacade.porCodigo(PASS_WS));
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
  
  public List<BeansEstadosTDC> parseEstadoTDCBean(List<Mstestadotdc> estados)
  {
    Long ambienteid = null;
    Long idiomaid = null;
    
    List<BeansEstadosTDC> listaEstadoTDC2 = new ArrayList();
    String estadoAnt = "";
    StringBuilder codigo = new StringBuilder();
    for (Mstestadotdc listaEstadoTDC1 : estados) {
      if ((listaEstadoTDC1.getCodunificado() == null) || (listaEstadoTDC1.getCodsapf1021() == null) || (listaEstadoTDC1.getCodsapf1024() == null) || 
        (!listaEstadoTDC1.getCodunificado().trim().equals("a")) || (!listaEstadoTDC1.getCodsapf1021().trim().equals("08")) || (!String.valueOf(listaEstadoTDC1.getCodsapf1024()).equals("C")))
      {
        if ((!estadoAnt.equals(listaEstadoTDC1.getDesestado().trim())) && (!estadoAnt.equals("")) && (!codigo.equals("")))
        {
          BeansEstadosTDC beansEstadosTDC = new BeansEstadosTDC();
          beansEstadosTDC.setCodigo(codigo.toString());
          beansEstadosTDC.setDescripcion(estadoAnt);
          listaEstadoTDC2.add(beansEstadosTDC);
          codigo = new StringBuilder();
        }
        else
        {
          codigo.append(listaEstadoTDC1.getCodunificado() == null ? " " : listaEstadoTDC1.getCodunificado());
          codigo.append("-");
          codigo.append(listaEstadoTDC1.getCodsapf1021() == null ? " " : listaEstadoTDC1.getCodsapf1021());
          codigo.append("-");
          codigo.append(listaEstadoTDC1.getCodsapf1024() == null ? " " : listaEstadoTDC1.getCodsapf1024());
          codigo.append(";");
        }
        estadoAnt = listaEstadoTDC1.getDesestado().trim();
        if ((listaEstadoTDC1.getCodunificado() != null) && (listaEstadoTDC1.getCodsapf1021() != null) && (listaEstadoTDC1.getCodsapf1024() != null) && 
          (listaEstadoTDC1.getCodunificado().trim().equals("c")) && (listaEstadoTDC1.getCodsapf1021().trim().equals("01")) && (String.valueOf(listaEstadoTDC1.getCodsapf1024()).equals("R")))
        {
          codigo = new StringBuilder();
          codigo.append(listaEstadoTDC1.getCodunificado() == null ? " " : listaEstadoTDC1.getCodunificado());
          codigo.append("-");
          codigo.append(listaEstadoTDC1.getCodsapf1021() == null ? " " : listaEstadoTDC1.getCodsapf1021());
          codigo.append("-");
          codigo.append(listaEstadoTDC1.getCodsapf1024() == null ? " " : listaEstadoTDC1.getCodsapf1024());
          codigo.append(";");
          
          BeansEstadosTDC beansEstadosTDC = new BeansEstadosTDC();
          beansEstadosTDC.setCodigo(codigo.toString());
          beansEstadosTDC.setDescripcion(estadoAnt);
          beansEstadosTDC.setEstAlerta(listaEstadoTDC1.getEstalerta().charValue());
          listaEstadoTDC2.add(beansEstadosTDC);
        }
      }
    }
    BeansEstadosTDC beansEstadosTDC = new BeansEstadosTDC();
    beansEstadosTDC.setCodigo("a-08-C");
    beansEstadosTDC.setDescripcion(this.textosFacade.porCodigo(ESTADO_TDC_CASTIGADA, ambienteid, idiomaid));
    listaEstadoTDC2.add(beansEstadosTDC);
    
    return listaEstadoTDC2;
  }
}
