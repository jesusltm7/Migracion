/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bod.servlets;

import com.bod.SingletonController;
import com.bod.controller.EjecutarProcesoControllerV3;
import com.bod.facade.ExcluidosMigracionFacade;
import com.bod.model.ExcluidosMigracion;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EjecutarMigracionServlet", urlPatterns = {"/ejecutarMigracion"})
public class EjecutarProcesoServletV1 extends HttpServlet {
    
    @EJB
    private ExcluidosMigracionFacade excluidos;
    
    @Inject
    private EjecutarProcesoControllerV3 ejecutarProcesoController;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if (!SingletonController.getInstance().esActivo()) {
            SingletonController.getInstance().tomarControl();
            try {
                List<ExcluidosMigracion> listaExcluidos = excluidos.porEstadoPendiente();
                Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), "MIGRAR", "Cantidad de registros pendientes de migrar: " + listaExcluidos.size());
                
                for (ExcluidosMigracion usrExcluido : listaExcluidos) {
                    Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), usrExcluido.getTipoIdentificacion()+""+usrExcluido.getIdentificacion(),"Iniciando proceso de migracion");
                    boolean migrarUsuario = ejecutarProcesoController.migrarUsuario(usrExcluido.getTipoIdentificacion() + "", usrExcluido.getIdentificacion());
                    if (migrarUsuario) {
                        usrExcluido.setEstado('E');
                        excluidos.edit(usrExcluido);
                    }                    
                }
                
            } catch (Exception e) {
                Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "MIGRAR", "Error capturado.", e);
            } finally {
                SingletonController.getInstance().liberarControl();
            }
        } else {
            Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), "MIGRAR", "Existe un proceso en ejecucion");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entre en get");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entre en post");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
