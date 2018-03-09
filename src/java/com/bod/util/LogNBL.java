package com.bod.util;

public enum LogNBL
{
  MIGRAR("modulo.migracion.automatico", "migracion.automatico.txt");
  
  String codigo;
  String nombreArchivo;
  
  private LogNBL(String codigo, String nombreArchivo)
  {
    this.codigo = codigo;
    this.nombreArchivo = nombreArchivo;
  }
  
  public String getCodigo()
  {
    return this.codigo;
  }
  
  public String getNombreArchivo()
  {
    return this.nombreArchivo;
  }
  
  public static String getCodigoLogMonitor()
  {
    return "log.error.monitor";
  }
}
