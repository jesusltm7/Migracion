package com.bod.config.constants;

import com.bod.constants.StateCoreBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Named("stateCoreConfig")
@Stateless(name="mastateCoreConfigNbl")
public class StateCoreConfig
  extends StateCoreBaseBean
  implements Serializable
{
  Map<String, String> configParameter = new HashMap();
  @EJB
  private ParametrosFacade parametrosFacade;
  
  public Map<String, String> init()
  {
    try
    {
      this.configParameter.put(DIR_CODE_SERVICE_WSDL, this.parametrosFacade.porCodigo(StateCoreBaseBean.DIR_CODE_SERVICE_WSDL));
      this.configParameter.put("autenticacion.ssl.state.core", this.parametrosFacade.porCodigo("autenticacion.ssl.state.core"));
      this.configParameter.put("001", this.parametrosFacade.porCodigo("001"));
      this.configParameter.put("estado.activo.state.core", this.parametrosFacade.porCodigo("estado.activo.state.core"));
      this.configParameter.put("estado.suspendido.state.core", this.parametrosFacade.porCodigo("estado.suspendido.state.core"));
      this.configParameter.put("estado.bloqueado.state.core", this.parametrosFacade.porCodigo("estado.bloqueado.state.core"));
      this.configParameter.put("estado.inhabilitado.state.core", this.parametrosFacade.porCodigo("estado.inhabilitado.state.core"));
      this.configParameter.put("url.servicio.statecorews.channel", this.parametrosFacade.porCodigo("url.servicio.statecorews.channel"));
      this.configParameter.put("url.servicio.statecorews.result", this.parametrosFacade.porCodigo("url.servicio.statecorews.result"));
      this.configParameter.put("wbfd.error.host.p", this.parametrosFacade.porCodigo("wbfd.error.host.p"));
      this.configParameter.put("statecorews.process.afiliacion", this.parametrosFacade.porCodigo("statecorews.process.afiliacion"));
      this.configParameter.put("statecorews.activity.afiliacion", this.parametrosFacade.porCodigo("statecorews.activity.afiliacion"));
      this.configParameter.put("estado.birreversible.state.core", this.parametrosFacade.porCodigo("estado.birreversible.state.core"));
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
