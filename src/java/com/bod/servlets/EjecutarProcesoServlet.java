/*
 * Decompiled with CFR 0_124.
 * 
 * Could not load the following classes:
 *  com.bod.util.logger.Log
 *  javax.ejb.EJB
 *  javax.inject.Inject
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.bod.servlets;

import com.bod.SingletonController;
import com.bod.controller.EjecutarProcesoController;
import com.bod.controller.EjecutarProcesoControllerV3;
import com.bod.facade.ExcluidosMigracionFacade;
import com.bod.model.ExcluidosMigracion;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EjecutarMigracionServlet", urlPatterns = {"/ejecutarMigracion"})
public class EjecutarProcesoServlet
        extends HttpServlet {

    @EJB
    private ExcluidosMigracionFacade excluidos;
    @Inject
    private EjecutarProcesoController ejecutarProcesoController;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int httpResponseCode = 200;
        int codigoProceso = Integer.parseInt(request.getParameter("codigoProceso"));
        if (!SingletonController.getInstance().esActivo()) {
            SingletonController.getInstance().tomarControl();
            try {
                boolean existenPendientes = false;
                do {
                    List<ExcluidosMigracion> listaExcluidos;
                    existenPendientes = null != (listaExcluidos = this.excluidos.porEstadoPendienteTruncado(codigoProceso)) && listaExcluidos.size() > 0;
                    Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), "MIGRAR", "Proceso " + codigoProceso + " Cantidad de registros pendientes de migrar: " + listaExcluidos.size());
                    int contadorProcesos = 1;
                    for (ExcluidosMigracion usrExcluido : listaExcluidos) {
                        try {
                            Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), usrExcluido.getTipoIdentificacion() + "" + usrExcluido.getIdentificacion(), "Iniciando proceso de migracion");
                            boolean migrarUsuario = this.ejecutarProcesoController.migrarUsuario(usrExcluido.getTipoIdentificacion() + "", usrExcluido.getIdentificacion(), usrExcluido, String.valueOf(contadorProcesos));
                            if (!migrarUsuario) continue;
                            usrExcluido.setEstado(Character.valueOf('E'));
                            this.excluidos.edit(usrExcluido);
                        }
                        catch (Exception e) {
                            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "MIGRAR", "Error capturado.", (Throwable)e);
                        }
                    }
                } while (existenPendientes);
            }
            catch (Exception e) {
                Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "MIGRAR", "Error capturado.", (Throwable)e);
            }
            finally {
                SingletonController.getInstance().liberarControl();
            }
            System.out.println("finaliza servlet");
        } else {
            Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), "MIGRAR", "Existe un proceso en ejecucion");
            httpResponseCode = 405;
        }
        response.setStatus(httpResponseCode);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    public String getServletInfo() {
        return "Short description";
    }

    public class Thread {
        ExcluidosMigracion usrExcluido;
        int idProceso;

        public Thread(ExcluidosMigracion usrExcluido, int idProceso) {
            this.usrExcluido = usrExcluido;
            this.idProceso = idProceso;
        }

        public void procesar() {
            try {
                Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), this.usrExcluido.getTipoIdentificacion() + "" + this.usrExcluido.getIdentificacion(), "Iniciando proceso de migracion");
                boolean migrarUsuario = ejecutarProcesoController.migrarUsuario(this.usrExcluido.getTipoIdentificacion() + "", this.usrExcluido.getIdentificacion(), this.usrExcluido, String.valueOf(this.idProceso));
                if (migrarUsuario) {
                    this.usrExcluido.setEstado(Character.valueOf('E'));
                    excluidos.edit(this.usrExcluido);
                }
            }
            catch (Exception e) {
                Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), "MIGRAR", "Error capturado.", (Throwable)e);
            }
        }
    }

}
