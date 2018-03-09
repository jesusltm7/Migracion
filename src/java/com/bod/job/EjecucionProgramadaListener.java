package com.bod.job;

import com.bod.facade.ParametrosFacade;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import com.bod.util.logger.appender.Appender;
import com.bod.util.logger.appender.FileAppender;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EjecucionProgramadaListener
  implements ServletContextListener
{
  @EJB
  private ParametrosFacade parametrosFacade;
  
  public void contextInitialized(ServletContextEvent pServletContext)
  {
    ServletContext ctx = pServletContext.getServletContext();
    System.out.println("ctx.getContextPath() " + ctx.getContextPath());
    inicializarLog();
  }
  
  private void inicializarLog()
  {
    try
    {
      String rutaArchivosLog = this.parametrosFacade.porCodigo("log.ruta.archivos");
      int cantidadMaximaArchivos = Integer.parseInt(this.parametrosFacade.porCodigo("log.cantidad.maxima.archivos"));
      int pesoMaximoArchivos = Integer.parseInt(this.parametrosFacade.porCodigo("log.peso.maximo.archivo"));
      int modoDebugActivo = Integer.parseInt(this.parametrosFacade.porCodigo("log.debug.activo"));
      int monitorActivo = Integer.parseInt(this.parametrosFacade.porCodigo("log.monitor.activo"));
      int redirectorActivo = Integer.parseInt(this.parametrosFacade.porCodigo("log.activar.consola.activo"));
      int nivelMaximo = Integer.parseInt(this.parametrosFacade.porCodigo("log.nivel.traza.activo"));
      if (modoDebugActivo == 1) {
        Log.getInstance().activarDebug();
      }
      if (monitorActivo == 1) {
        Log.getInstance().activarMonitoreo();
      }
      if (redirectorActivo == 1) {
        Log.getInstance().activarSalidaConsola();
      }
      Log.getInstance().setNivelActivo(nivelMaximo);
      
      List<Appender> listaAppenders = new ArrayList();
      for (LogNBL log : LogNBL.values())
      {
        FileAppender fa = new FileAppender();
        fa.setCodigo(log.getCodigo());
        fa.setRutaArchivo(rutaArchivosLog + obtenerNombreLog(log.getNombreArchivo()));
        fa.setTamanhoMaximoArchivo(pesoMaximoArchivos);
        fa.setCantidadMaximaArchivos(cantidadMaximaArchivos);
        listaAppenders.add(fa);
      }
      Log.getInstance().configure(listaAppenders, this.parametrosFacade.porCodigo("log.formato.traza"));
      Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), "NBL", "El log se inicializo de manera correcta.");
    }
    catch (Exception e)
    {
      System.err.println("IMPOSIBLE INICIAR EL LOG. SE DEBEN REVISAR LOS PARAMETROS");
    }
  }
  
  private String obtenerNombreLog(String nombreLog)
    throws Exception
  {
    String nombreArchivo = "";
    String nombreSinExtension = nombreLog.substring(0, nombreLog.lastIndexOf(".") + 1);
    String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    
    Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
    String ipHost = InetAddress.getLocalHost().getHostAddress();
    Matcher matcher = pattern.matcher(InetAddress.getLocalHost().getHostAddress());
    if (matcher.matches())
    {
      String[] ipDesarrollo = ipHost.split("[.]");
      nombreArchivo = nombreSinExtension + ipDesarrollo[3] + ".txt";
    }
    else
    {
      nombreArchivo = nombreSinExtension + InetAddress.getLocalHost().getHostName() + ".txt";
    }
    return nombreArchivo;
  }
  
  public void contextDestroyed(ServletContextEvent sce) {}
}
