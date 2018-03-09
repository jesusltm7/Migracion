/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.service;

import com.bod.controller.EjecutarProcesoControllerV3;
import com.bod.facade.ClientesInternetBankingFacade;
import com.bod.model.ClienteInternetBanking;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Intec
 */
@WebService(serviceName = "MigracionService")
public class MigracionService {

    @EJB
    private ClientesInternetBankingFacade clientesInternetBankingFacade;
    
    @Inject
    private EjecutarProcesoControllerV3 ejecutarProcesoController;
    
    @WebMethod(operationName = "migrarUsuario")
    public String migrarUsuario(@WebParam(name = "tipoIdentificacion") String tipoIdentificacion, @WebParam(name = "identificacion") String identificacion, @WebParam(name = "numeroCliente") int numeroCliente) {
        ClienteInternetBanking clienteInternetBanking = clientesInternetBankingFacade.porIdentificacionNumeroCliente(tipoIdentificacion, identificacion, numeroCliente);        
        ejecutarProcesoController.setClienteProceso(clienteInternetBanking);
        ejecutarProcesoController.registrarUsuarioSeleccionado();
        
        return "MIGRADO " + clienteInternetBanking.getNombreCliente();
    }
}
