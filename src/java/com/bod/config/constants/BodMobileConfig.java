package com.bod.config.constants;

import com.bod.constants.BodMobileBaseBean;
import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Configuracion inicial para el consumo y uso del caso de uso
 * de BodMobile
 * 
 * @author Yelitza Hernández
 */
@Named("bodMobileConfig")
@Stateless(name="mabodMobileConfigNbl")
public class BodMobileConfig   extends BodMobileBaseBean
  implements Serializable{
    
     /**
     * Mapa de configuracion a inyectar
     */
    Map<String, String> configParameter = new HashMap();
    
     /**
     * Objeto de configuracion para elementos de texto y otros parametros de
     * configuracion
     */
    @EJB
    private ParametrosFacade parametrosFacade;
    
    /**
     * Inicializando valores predeterminados de WS
     * @return Mapa de Configuraciones
     */    
    public Map<String, String> init() {
        try {
             
            this.configParameter.put(DIR_CODE_SERVICE_WSDL, this.parametrosFacade.porCodigo(DIR_CODE_SERVICE_WSDL));
            this.configParameter.put(AUTH_SSL_WS, this.parametrosFacade.porCodigo(AUTH_SSL_WS));
            this.configParameter.put(DIR_CODE_PARAM_BANKID, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_BANKID));
            this.configParameter.put(DIR_CODE_PARAM_CHANNELCODE, this.parametrosFacade.porCodigo(DIR_CODE_PARAM_CHANNELCODE));
            this.configParameter.put(USER_WS, this.parametrosFacade.porCodigo(USER_WS));
            this.configParameter.put(PASS_WS, this.parametrosFacade.porCodigo(PASS_WS));
            
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "NBL", "Advertencia init" , e);
        }        
        return configParameter;
    }
    
}
