package com.bod.controller;

import com.bod.beans.BeanTipoAprobador;
import com.bod.facade.ParametrosFacade;
import com.bod.model.Parametros;
import com.bod.util.LogNBL;
import com.bod.util.ParametrosBaseBean;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Clase que contiene la consulta de los parametros a ser utilizados en el
 * FrontEnd, a nivel de validaciones, tiempo de sesion, etc.
 *
 * @author cfernandez
 */
@Named("parametrosController")
@SessionScoped
public class ParametrosController extends ParametrosBaseBean implements Serializable {

    //vista de ayuda
    private boolean ayudaPrimeraVez = false;
    private boolean ayudaVerificada = false;

    @EJB
    ParametrosFacade parametrosFacade;

    /**
     * Obtiene la longitud minima de identificacion para ser utilizada en los
     * campos de texto Codigo de Parametro= longuitud.minima.identificacion
     *
     * @return
     */
    public int obtenerMinLongNombre() {
        String minLongNombre = MINIMA_LONGITUD_NOMBRE;
        int minLongNomUsuario;
        try {
            minLongNombre = parametrosFacade.porCodigo("longitud.minimo.nombre.usuario");
            if (minLongNombre == null || minLongNombre.isEmpty()) {
                minLongNombre = MINIMA_LONGITUD_NOMBRE;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMinLongNombre, se usara valor por defecto", e);
        }
        minLongNomUsuario = Integer.parseInt(minLongNombre);
        return minLongNomUsuario;
    }

    /**
     * Obtiene la longitud minima de identificacion para ser utilizada en los
     * campos de texto Codigo de Parametro= longuitud.minima.identificacion
     *
     * @return
     */
    public String obtenerMinLongIdentificacion() {
        String minLongIdentificacion = MINIMA_LONGITUD_IDENTIFICACION;
        try {
            minLongIdentificacion = parametrosFacade.porCodigo("longuitud.minima.identificacion");
            if (minLongIdentificacion == null || minLongIdentificacion.isEmpty()) {
                minLongIdentificacion = MINIMA_LONGITUD_IDENTIFICACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMinLongIdentificacion, se usara valor por defecto", e);
        }
        return minLongIdentificacion;
    }

    /**
     * Obtiene la longitud minima de identificacion para ser utilizada en los
     * campos de texto Codigo de Parametro= longuitud.minima.identificacion
     *
     * @return
     */
    public String obtenerMinLongIdentificacionMigracion() {
        String minLongIdentificacionM = MINIMA_LONGITUD_IDENTIFICACION_MIGRACION;
        try {
            minLongIdentificacionM = parametrosFacade.porCodigo("longuitud.minima.identificacion.migracion");
            if (minLongIdentificacionM == null || minLongIdentificacionM.isEmpty()) {
                minLongIdentificacionM = MINIMA_LONGITUD_IDENTIFICACION_MIGRACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMinLongIdentificacionMigracion, se usara valor por defecto", e);
        }
        return minLongIdentificacionM;
    }

    /**
     * Obtiene la longitud minima de nombre de usuario para ser utilizada en los
     * campos de texto Codigo de Parametro= longitud.minimo.nombre.usuario
     *
     * @return
     */
    public String obtenerMinLongNombreUsuario() {
        String minLongNombreUsuario = MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            minLongNombreUsuario = parametrosFacade.porCodigo("longitud.minimo.nombre.usuario");
            if (minLongNombreUsuario == null || minLongNombreUsuario.isEmpty()) {
                minLongNombreUsuario = MINIMA_LONGITUD_NOMBRE_USUARIO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMinLongNombreUsuario, se usara valor por defecto", e);
        }
        return minLongNombreUsuario;
    }

    /**
     * Obtiene la longitud maxima de nombre de usuario para ser utilizada en los
     * campos de texto Codigo de Parametro= longitud.maxima.nombre.usuario
     *
     * @return
     */
    public String obtenerMaxLongNombreUsuario() {
        String maxLongNombreUsuario = MAXIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            maxLongNombreUsuario = parametrosFacade.porCodigo("longitud.maxima.nombre.usuario");
            if (maxLongNombreUsuario == null || maxLongNombreUsuario.isEmpty()) {
                maxLongNombreUsuario = MAXIMA_LONGITUD_NOMBRE_USUARIO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongNombreUsuario, se usara valor por defecto", e);
        }
        return maxLongNombreUsuario;
    }

    /**
     * Obtiene el tiempo de sesion a ser utilizado en el Nbl Codigo de
     * Parametro= sesion.tiempo.nbl
     *
     * @return
     */
    public String obtenerTiempoSesion() {
        String tiempoSesion = TIEMPO_SESION;
        try {
            tiempoSesion = parametrosFacade.porCodigo("sesion.tiempo.nbl");
            if (tiempoSesion == null || tiempoSesion.isEmpty()) {
                tiempoSesion = TIEMPO_SESION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerTiempoSesion, se usara valor por defecto", e);
        }
        return tiempoSesion;

    }

    /**
     * Obtiene el tiempo de sesion a ser utilizado en el Nbl Codigo de
     * Parametro= sesion.tiempo.nbl
     *
     * @return
     */
    public String obtenerTiempoSesionAfiliacion() {
        String tiempoSesion = TIEMPO_SESION_AFILIACION;
        try {
            tiempoSesion = parametrosFacade.porCodigo("sesion.tiempo.afiliacion");
            if (tiempoSesion == null || tiempoSesion.isEmpty()) {
                tiempoSesion = TIEMPO_SESION_AFILIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerTiempoSesionAfiliacion, se usara valor por defecto", e);
        }
        return tiempoSesion;

    }

    /**
     *
     * @param sufijoFuncionalidad sufijo de la funcionalidad que requiera el
     * minimo de longitud para su OTP
     * @return minimo longitud de los codigos OTP
     */
    public String obtenerMinimoLogintudOtp(String sufijoFuncionalidad) {
        String minimo = MINIMA_LONGITUD_OTPS;
        try {
            minimo = parametrosFacade.porCodigo("longitud.minimo.otp." + sufijoFuncionalidad);
            if (minimo == null || minimo.isEmpty()) {
                minimo = MINIMA_LONGITUD_OTPS;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMinimoLogintudOtp, se usara valor por defecto", e);
        }
        return minimo;
    }

    /**
     * Metodo que retorna la longitud máxima del alias del producto en
     * Directorio Global
     *
     * @return longitud máxima del campo alias.
     */
    public String obtenerLongitudAliasProducto() {
        String maximo = DIRECTORIO_ALIAS_PRODUCT;
        try {
            maximo = parametrosFacade.porCodigo("directorio.longitud.producto.alias");
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerLongitudAliasProducto, se usara valor por defecto", e);
        }

        return maximo;
    }

    /**
     * Metodo que retorna la longitud máxima del alias del beneficiario en
     * Directorio Global
     *
     * @return longitud máxima del campo alias.
     */
    public String obtenerLongitudAliasBeneficiario() {
        String maximo = DIRECTORIO_ALIAS_BENEF;
        try {
            maximo = parametrosFacade.porCodigo("directorio.longitud.benef.alias");
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerLongitudAliasBeneficiario, se usara valor por defecto", e);
        }
        return maximo;
    }

    /**
     * Metodo que retorna la longitud máxima del alias del beneficiario en
     * Directorio Global
     *
     * @return longitud máxima del campo alias.
     */
    public String obtenerLongitudEmailBeneficiario() {
        String maximo = DIRECTORIO_LONGITUD_EMAIL;
        try {
            maximo = parametrosFacade.porCodigo("directorio.longitud.email");
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerLongitudEmailBeneficiario, se usara valor por defecto", e);
        }

        return maximo;
    }

    /**
     *
     * @param sufijoFuncionalidad sufijo de la funcionalidad que requiera el
     * minimo de longitud para su OTP
     * @return maximo longitud de los codigos OTP
     */
    public String obtenerMaximoLogintudOtp(String sufijoFuncionalidad) {
        String maximo = MAXIMO_LONGITUD_OTPS;
        try {
            maximo = parametrosFacade.porCodigo("longitud.maximo.otp." + sufijoFuncionalidad);
            if (maximo == null || maximo.isEmpty()) {
                maximo = MAXIMO_LONGITUD_OTPS;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaximoLogintudOtp, se usara valor por defecto", e);
        }
        return maximo;
    }

    /**
     * Obtiene el tiempo de sesion a ser utilizado en el modulo de login Codigo
     * de Parametro= sesion.tiempo.login
     *
     * @return
     */
    public String obtenerTiempoSesionLogin() {
        String tiempoSesionLogin = TIEMPO_SESION_LOGIN;
        try {
            tiempoSesionLogin = parametrosFacade.porCodigo("sesion.tiempo.login");
            if (tiempoSesionLogin == null || tiempoSesionLogin.isEmpty()) {
                tiempoSesionLogin = TIEMPO_SESION_LOGIN;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerTiempoSesionLogin, se usara valor por defecto", e);
        }
        return tiempoSesionLogin;
    }

    /**
     * permite verificar si el tiempo de sesion que viene desde cada pantalla a
     * la plantilla esta correcto para ser usado o es necesario consultar el
     * correspondiente.
     *
     * @param tiempoSesion
     * @return
     */
    public String obtenerTiempoSesion(String tiempoSesion) {
        if (tiempoSesion == null || tiempoSesion.isEmpty()) {
            tiempoSesion = obtenerTiempoSesionAfiliacion();
        }
        return tiempoSesion;
    }

    /**
     * permite verificar si el tiempo de sesion que viene desde cada pantalla a
     * la plantilla esta correcto para ser usado o es necesario consultar el
     * correspondiente.
     *
     * @param tiempoSesionAfiliacion
     * @return
     */
    public String obtenerTiempoSesionAfiliacion(String tiempoSesionAfiliacion) {
        if (tiempoSesionAfiliacion == null || tiempoSesionAfiliacion.isEmpty()) {
            tiempoSesionAfiliacion = obtenerTiempoSesionAfiliacion();
        }
        return tiempoSesionAfiliacion;
    }

    /**
     * Obtiene el tiempo de sesion a ser utilizado en el modulo de reafiliacion
     * de usuario. Codigo de Parametro= sesion.tiempo.reafiliacion
     *
     * @return
     */
    public String obtenerTiempoSesionReafiliacion() {
        String tiempoSesionReafiliacion = TIEMPO_SESION_REAFILIACION;
        try {
            tiempoSesionReafiliacion = parametrosFacade.porCodigo("sesion.tiempo.reafiliacion");
            if (tiempoSesionReafiliacion == null || tiempoSesionReafiliacion.isEmpty()) {
                tiempoSesionReafiliacion = TIEMPO_SESION_REAFILIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerTiempoSesionReafiliacion, se usara valor por defecto", e);
        }
        return tiempoSesionReafiliacion;
    }

    /**
     * Obtiene el tiempo de sesion a ser utilizado en el modulo de desbloqueo de
     * usuario. Codigo de Parametro= sesion.tiempo.desbloqueo
     *
     * @return
     */
    public String obtenerTiempoSesionDesbloqueo() {
        String tiempoSesionReafiliacion = TIEMPO_SESION_DESBLOQUEO;
        try {
            tiempoSesionReafiliacion = parametrosFacade.porCodigo("sesion.tiempo.desbloqueo");
            if (tiempoSesionReafiliacion == null || tiempoSesionReafiliacion.isEmpty()) {
                tiempoSesionReafiliacion = TIEMPO_SESION_DESBLOQUEO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerTiempoSesionDesbloqueo, se usara valor por defecto", e);
        }
        return tiempoSesionReafiliacion;
    }

    /**
     * Obtiene el tiempo de sesion a ser utilizado en el modulo de recuperacion
     * de contrasena Codigo de Parametro= sesion.tiempo.recu.contrasena
     *
     * @return
     */
    public String obtenerTiempoSesionRecuContrasena() {
        String tiempoSesionRecuContrasena = TIEMPO_SESION_RECUPERACION_CONTRASENA;
        try {
            tiempoSesionRecuContrasena = parametrosFacade.porCodigo("sesion.tiempo.recu.contrasena");
            if (tiempoSesionRecuContrasena == null || tiempoSesionRecuContrasena.isEmpty()) {
                tiempoSesionRecuContrasena = TIEMPO_SESION_RECUPERACION_CONTRASENA;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerTiempoSesionRecuContrasena, se usara valor por defecto", e);
        }
        return tiempoSesionRecuContrasena;
    }

    /**
     * Obtiene el tiempo de sesion a ser utilizado en el modulo de recuperacion
     * de usuario Codigo de Parametro= sesion.tiempo.recu.usuario
     *
     * @return
     */
    public String obtenerTiempoSesionRecuUsuario() {
        String tiempoSesionRecuUsuario = TIEMPO_SESION_RECUPERACION_USUARIO;
        try {
            tiempoSesionRecuUsuario = parametrosFacade.porCodigo("sesion.tiempo.recu.usuario");
            if (tiempoSesionRecuUsuario == null || tiempoSesionRecuUsuario.isEmpty()) {
                tiempoSesionRecuUsuario = TIEMPO_SESION_RECUPERACION_USUARIO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerTiempoSesionRecuUsuario, se usara valor por defecto", e);
        }
        return tiempoSesionRecuUsuario;
    }

    /**
     * Obtiene el tiempo de sesion a ser utilizado en el modulo de incorporacion
     * de usuario Codigo de Parametro= sesion.tiempo.incorpora.usuario
     *
     * @return
     */
    public String obtenerTiempoSesionIncorporaUsuario() {
        String tiempoSesionIncorporaUsuario = TIEMPO_SESION_INCORPORACION_USUARIO;
        try {
            tiempoSesionIncorporaUsuario = parametrosFacade.porCodigo("sesion.tiempo.incorpora.usuario");
            if (tiempoSesionIncorporaUsuario == null || tiempoSesionIncorporaUsuario.isEmpty()) {
                tiempoSesionIncorporaUsuario = TIEMPO_SESION_INCORPORACION_USUARIO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerTiempoSesionIncorporaUsuario, se usara valor por defecto", e);
        }
        return tiempoSesionIncorporaUsuario;
    }

    /**
     * Obtiene la maxima longitud permitida para el codigo de invitacion de
     * asociacion / codigo BT
     *
     * @return
     */
    public String obtenerMaxLongCodigoBt() {
        String maxLongCodigoBt = MAX_DIGITO_CODIGO_BT;
        try {
            maxLongCodigoBt = parametrosFacade.porCodigo("cant.digtos.codigo.asociacion");
            if (maxLongCodigoBt == null || maxLongCodigoBt.isEmpty()) {
                maxLongCodigoBt = MAX_DIGITO_CODIGO_BT;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongCodigoBt, se usara valor por defecto", e);
        }
        return maxLongCodigoBt;
    }

    //Metodos para la obtencion de parametros STATECORE K.S. 08-09-2015
    public String obtenerProcesoLogin() {
        String procesoid = PROCESO_LOGIN;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_LOGIN;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoLogin, se usara valor por defecto", e);
        }
        return procesoid;
    }

    //Metodos para la obtencion de parametros STATECORE K.S. 08-09-2015
    public String obtenerProcesorRecuperarUsuario() {
        String procesoid = PROCESO_RECUPERAR_USUARIO;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.recu.usuario.nbl");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_RECUPERAR_USUARIO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesorRecuperarUsuario, se usara valor por defecto", e);
        }
        return procesoid;
    }

    //Metodos para la obtencion de parametros STATECORE K.S. 08-09-2015
    public String obtenerProcesoZonaSeguraSMS() {
        String procesoid = PROCESO_ZONA_SEGURA_SMS;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.zonasegura.sms");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_ZONA_SEGURA_SMS;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoZonaSeguraSMS, se usara valor por defecto", e);
        }
        return procesoid;
    }

    //Metodos para la obtencion de parametros STATECORE K.S. 08-09-2015
    public String obtenerProcesoZonaSeguraEMAIL() {
        String procesoid = PROCESO_ZONA_SEGURA_EMAIL;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.zonasegura.email");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_ZONA_SEGURA_EMAIL;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoZonaSeguraEMAIL, se usara valor por defecto", e);
        }
        return procesoid;
    }

    //Metodos para la obtencion de parametros STATECORE K.S. 08-09-2015
    public String obtenerProcesoZonaSegura() {
        String procesoid = PROCESO_ZONA_SEGURA;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.zonasegura");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_ZONA_SEGURA;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoZonaSegura, se usara valor por defecto", e);
        }
        return procesoid;
    }

    //Metodos para la obtencion de parametros STATECORE K.S. 08-09-2015
    public String obtenerProcesoZonaSeguraTARJETA() {
        String procesoid = PROCESO_ZONA_SEGURA_TARJETA_COORD;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.tarjeta.coordenadas");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_ZONA_SEGURA_TARJETA_COORD;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoZonaSeguraTARJETA, se usara valor por defecto", e);
        }
        return procesoid;
    }

    public String obtenerActividadValidarClave() {
        String Actividadid = ACTIVIDAD_VALIDAR_CLAVE;
        try {
            Actividadid = parametrosFacade.porCodigoNoAutenticado("sesion.actividad.validacion.clave.nbl");
            if (Actividadid == null || Actividadid.isEmpty()) {
                Actividadid = ACTIVIDAD_VALIDAR_CLAVE;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerActividadValidarClave, se usara valor por defecto", e);
        }
        return Actividadid;
    }

    /**
     * Obtiene actividad para proceso login usuario suspendido
     *
     * @return
     */
    public String obtenerActividadZSSuspendido() {
        String Actividadid = "";
        try {
            Actividadid = parametrosFacade.porCodigoNoAutenticado("sesion.actividad.login.suspendido");
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerActividadZSSuspendido, se usara valor por defecto", e);
        }
        return Actividadid;
    }

    /**
     * Metodo para obtener el codigo a enviar a State Core para el bloqueo de
     * los usuarios asocioados de un cliente, solicitado por WBFD.
     *
     * @return
     */
    public String obtenerActividadBloqueoWBFD() {
        String Actividadid = ACTIVIDAD_BLOQUEO_WBFD;
        try {
            Actividadid = parametrosFacade.porCodigoNoAutenticado("wbfd.error.host.p");
            if (Actividadid == null || Actividadid.isEmpty()) {
                Actividadid = ACTIVIDAD_BLOQUEO_WBFD;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerActividadBloqueoWBFD, se usara valor por defecto", e);
        }
        return Actividadid;
    }

    public String obtenerCanalWeb() {
        String Canal = CANAL_WEB;
        try {
            Canal = parametrosFacade.porCodigoNoAutenticado("sesion.canal.nbl");
            if (Canal == null || Canal.isEmpty()) {
                Canal = CANAL_WEB;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerCanalWeb, se usara valor por defecto", e);
        }
        return Canal;
    }

    public String obtenerResultadoFallido() {
        String resultado = RESULTADO_FALLIDO;

        try {
            resultado = parametrosFacade.porCodigoNoAutenticado("sesion.resultado.fallido.nbl");
            if (resultado == null || resultado.isEmpty()) {
                resultado = RESULTADO_FALLIDO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerResultadoFallido, se usara valor por defecto", e);
        }
        return resultado;
    }

    public String obtenerResultadoExitoso() {
        String resultado = RESULTADO_EXITOSO;

        try {
            resultado = parametrosFacade.porCodigoNoAutenticado("sesion.resultado.exitoso.nbl");
            if (resultado == null || resultado.isEmpty()) {
                resultado = RESULTADO_EXITOSO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerResultadoExitoso, se usara valor por defecto", e);
        }
        return resultado;
    }

    public String obtenerActividadValidarOtp() {
        String Actividadid = ACTIVIDAD_VALIDAR_OTP;
        try {
            Actividadid = parametrosFacade.porCodigoNoAutenticado("sesion.actividad.validacion.otp.nbl");
            if (Actividadid == null || Actividadid.isEmpty()) {
                Actividadid = ACTIVIDAD_VALIDAR_OTP;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerActividadValidarOtp, se usara valor por defecto", e);
        }
        return Actividadid; //sesion.proceso.desbloqueo.usuario.nbl
    }

    public String obtenerProcesoDesbloqueoUsuario() {
        String procesoid = PROCESO_DEBLOQUEO_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.desbloqueo.usuario.nbl");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_DEBLOQUEO_USUARIO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoDesbloqueoUsuario, se usara valor por defecto", e);
        }
        return procesoid; //sesion.proceso.afiliacion
    }

    public String obtenerProcesoAfiliacion() {
        String procesoid = PROCESO_AFILIACION_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.afiliacion");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_AFILIACION_USUARIO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoAfiliacion, se usara valor por defecto", e);
        }
        return procesoid; //sesion.proceso.cambio.clave.nbl
    }

    public String obtenerProcesoCambioClave() {
        String procesoid = PROCESO_CAMBIO_CLAVE;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.cambio.clave.nbl");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_CAMBIO_CLAVE;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoCambioClave, se usara valor por defecto", e);
        }
        return procesoid; //sesion.proceso.cambio.clave.nbl
    }

    public String obtenerProcesoCambioImagen() {
        String procesoid = PROCESO_CAMBIO_IMAGEN;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.cambio.clave.nbl");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_CAMBIO_IMAGEN;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoCambioImagen, se usara valor por defecto", e);
        }
        return procesoid; //sesion.proceso.cambio.clave.nbl
    }

    //Metodos para la obtencion de parametros STATECORE K.S. 08-09-2015
    public String obtenerProcesoReafiliacion() {
        String procesoid = PROCESO_REAFILIACION;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.reafiliacion");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_REAFILIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoReafiliacion, se usara valor por defecto", e);
        }
        return procesoid;
    }

    public String obtenerActividadReafiliacion() {
        String procesoid = ACTIVIDAD_REAFILIACION;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.actividad.reafiliacion");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = ACTIVIDAD_REAFILIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerActividadReafiliacion, se usara valor por defecto", e);
        }
        return procesoid;
    }

    public String obtenerProcesoRegenerarClave() {
        String procesoid = PROCESO_REGENERACION_CLAVE;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.regeneracion.clave");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_REGENERACION_CLAVE;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoRegenerarClave, se usara valor por defecto", e);
        }
        return procesoid;
    }

    public String obtenerProcesoAsiacion() {
        String procesoid = PROCESO_ASOCIACION;
        //String procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.login.nbl");
        //String minLongNombreUsuario=MINIMA_LONGITUD_NOMBRE_USUARIO;
        try {
            procesoid = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.asociacion");
            if (procesoid == null || procesoid.isEmpty()) {
                procesoid = PROCESO_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoAsiacion, se usara valor por defecto", e);
        }
        return procesoid;
    }

    private String minLongitudOtps;

    /**
     *
     * @return
     */
    public String getMinLongitudOtps() {
        return minLongitudOtps;
    }

    /**
     *
     * @param minLongitudOtps
     */
    public void setMinLongitudOtps(String minLongitudOtps) {
        this.minLongitudOtps = minLongitudOtps;
    }

    /**
     * Obtiene la longitud minima de registros a mostrar Codigo de
     * Parametro=longitud.minima.lightScrolling.nbl
     *
     * @return
     */
    public String obtenerLightScrolling() {
        String minLongScrolling = MINIMA_LONGITUD_LIGTH_SCROLLING;
        try {
            minLongScrolling = parametrosFacade.porCodigo("longitud.minima.lightScrolling.nbl");
            if (minLongScrolling == null || minLongScrolling.isEmpty()) {
                minLongScrolling = MINIMA_LONGITUD_LIGTH_SCROLLING;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerLightScrolling, se usara valor por defecto", e);
        }
        return minLongScrolling;
    }

    /**
     * Obtiene la maxima longitud permitida para el nombre del invitado
     *
     * @return
     */
    public String obtenerMaxLongNombreInvitado() {
        String maximoLongitud = MAX_LONGITUD_NOMBRE_INVITADO;
        try {
            maximoLongitud = parametrosFacade.porCodigo("asociacion.longitud.maxima.nombre");
            if (maximoLongitud == null || maximoLongitud.isEmpty()) {
                maximoLongitud = MAX_LONGITUD_NOMBRE_INVITADO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongNombreInvitado, se usara valor por defecto", e);
        }
        return maximoLongitud;
    }

    /**
     * Obtiene la maxima longitud permitida para el apellido del invitado
     *
     * @return
     */
    public String obtenerMaxLongApellidoInvitado() {
        String maximoLongitud = MAX_LONGITUD_APELLIDO_INVITADO;
        try {
            maximoLongitud = parametrosFacade.porCodigo("asociacion.longitud.maxima.apellido");
            if (maximoLongitud == null || maximoLongitud.isEmpty()) {
                maximoLongitud = MAX_LONGITUD_APELLIDO_INVITADO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongApellidoInvitado, se usara valor por defecto", e);
        }
        return maximoLongitud;
    }

    /**
     * Obtiene la maxima longitud permitida para el correo del invitado
     *
     * @return
     */
    public String obtenerMaxLongCorreoInvitado() {
        String maximoLongitud = MAX_LONGITUD_CORREO_INVITADO;
        try {
            maximoLongitud = parametrosFacade.porCodigo("asociacion.longitud.maxima.correo");
            if (maximoLongitud == null || maximoLongitud.isEmpty()) {
                maximoLongitud = MAX_LONGITUD_CORREO_INVITADO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongCorreoInvitado, se usara valor por defecto", e);
        }
        return maximoLongitud;
    }

    /**
     * Obtiene la maxima longitud permitida para el celular del invitado
     *
     * @return
     */
    public String obtenerMaxLongCelularInvitado() {
        String maximoLongitud = MAX_LONGITUD_CELULAR_INVITADO;
        try {
            maximoLongitud = parametrosFacade.porCodigo("asociacion.longitud.maxima.celular");
            if (maximoLongitud == null || maximoLongitud.isEmpty()) {
                maximoLongitud = MAX_LONGITUD_CELULAR_INVITADO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongCelularInvitado, se usara valor por defecto", e);
        }
        return maximoLongitud;
    }

    /**
     * Obtiene la maxima longitud permitida para el alias del invitado
     *
     * @return
     */
    public String obtenerMaxLongAliasInvitado() {
        String maximoLongitud = MAX_LONGITUD_ALIAS_INVITADO;
        try {
            maximoLongitud = parametrosFacade.porCodigo("asociacion.longitud.maxima.alias");
            if (maximoLongitud == null || maximoLongitud.isEmpty()) {
                maximoLongitud = MAX_LONGITUD_ALIAS_INVITADO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongAliasInvitado, se usara valor por defecto", e);
        }
        return maximoLongitud;
    }

    /**
     * Obtiene la maxima longitud permitida para la identificacion (V,E) del
     * invitado
     *
     * @return
     */
    public String obtenerMaxLongIdentificacionVariasInvitado() {
        String maximoLongitud = MAX_LONGITUD_IDENTIFICACION_VARIAS_INVITADO;
        try {
            maximoLongitud = parametrosFacade.porCodigo("asociacion.longitud.max.identificacion.varias");
            if (maximoLongitud == null || maximoLongitud.isEmpty()) {
                maximoLongitud = MAX_LONGITUD_IDENTIFICACION_VARIAS_INVITADO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongIdentificacionVariasInvitado, se usara valor por defecto", e);
        }
        return maximoLongitud;
    }

    /**
     * Obtiene la maxima longitud permitida para la identificacion (P) del
     * invitado
     *
     * @return
     */
    public String obtenerMaxLongIdentificacionPasaporteInvitado() {
        String maximoLongitud = MAX_LONGITUD_IDENTIFICACION_PASAPORTE_INVITADO;
        try {
            maximoLongitud = parametrosFacade.porCodigo("asociacion.long.max.identificacion.pasaporte");
            if (maximoLongitud == null || maximoLongitud.isEmpty()) {
                maximoLongitud = MAX_LONGITUD_IDENTIFICACION_PASAPORTE_INVITADO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongIdentificacionPasaporteInvitado, se usara valor por defecto", e);
        }
        return maximoLongitud;
    }

    /**
     * Obtiene el valor para validar autenticaciones de servicios
     *
     * @param codigo
     * @return
     */
    public String obtenerAutenticacionServicios(String codigo) {
        String valorDefectoAutenticacionSSL = VALOR_POR_DEFECTO_VALIDACION_SSL;
        String valorParametroParaAutenticar = parametrosFacade.porCodigo(codigo);

        try {
            if (valorParametroParaAutenticar == null || valorParametroParaAutenticar.isEmpty()) {
                Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                        "Advertencia obtenerAutenticacionServicios, werror de conversion");
                return valorDefectoAutenticacionSSL;
            } else {
                Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                        "Advertencia obtenerAutenticacionServicios, error de conversion");
                return valorParametroParaAutenticar;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerAutenticacionServicios, se usara valor por defecto", e);
        }

        return valorDefectoAutenticacionSSL;
    }

    /**
     * Obtiene la maxima longitud para la generacion del codigo BT.
     *
     * @return
     */
    public String obtenerMaxLongGeneracionCodigoBT() {
        String maximoLongitud = MAX_LONGITUD_GENERACION_CODIGO_BT;
        try {
            maximoLongitud = parametrosFacade.porCodigo("cant.digtos.codigo.asociacion");
            if (maximoLongitud == null || maximoLongitud.isEmpty()) {
                maximoLongitud = MAX_LONGITUD_GENERACION_CODIGO_BT;
            }
            int maximoLong = Integer.parseInt(MAX_LONGITUD_GENERACION_CODIGO_BT);
            try {
                maximoLong = Integer.parseInt(maximoLongitud);
            } catch (Exception e) {
                Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                        "Advertencia obtenerMaxLongGeneracionCodigoBT, se utilizara valor por defecto");

            }
            if (maximoLong > Integer.parseInt(MAX_LONGITUD_GENERACION_CODIGO_BT)) {
                Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                        "Advertencia obtenerMaxLongGeneracionCodigoBT, se utilizara valor por defecto");
                maximoLongitud = MAX_LONGITUD_GENERACION_CODIGO_BT;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongGeneracionCodigoBT, se utilizara valor por defecto", e);
        }
        return maximoLongitud;
    }

    public String obtenerMaxLongIdentificacionInvitadoPorTipo(String tipo) {
        if ("P".equals(tipo)) {
            return obtenerMaxLongIdentificacionPasaporteInvitado();
        } else {
            return obtenerMaxLongIdentificacionVariasInvitado();
        }
    }

    /**
     * Obtiene la maxima longitud permitida al ingresar el codigo de invitacion
     * de asociacion / codigo BT
     *
     * @return
     */
    public String obtenerMaxCodigoInvitacion() {
        String maxLongCodigoInv = MAX_LONGITUD_CODIGO_BT;
        try {
            maxLongCodigoInv = parametrosFacade.porCodigo("longitud.maxima.codigo.asociacion");
            if (maxLongCodigoInv == null || maxLongCodigoInv.isEmpty()) {
                maxLongCodigoInv = MAX_LONGITUD_CODIGO_BT;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxCodigoInvitacion, se utilizara valor por defecto", e);
        }
        return maxLongCodigoInv;
    }

    /**
     * Obtiene la maxima longitud permitida al ingresar un cheque
     *
     * @return
     */
    public String obtenerMaxLengthCheque() {
        String maxLongCheque = MAX_LONGITUD_CHEQUE;
        try {
            maxLongCheque = parametrosFacade.porCodigo("longitud.maxima.cheque");
            if (maxLongCheque == null || maxLongCheque.isEmpty()) {
                maxLongCheque = MAX_LONGITUD_CHEQUE;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLengthCheque, se utilizara valor por defecto", e);
        }
        return maxLongCheque;
    }

    /**
     * Obtiene máximo de dias a consultar atraves de un dateRangePicker
     *
     * @return
     */
    public String obtenerMaxDaysDateRangePicker() {
        String maxDays = MAX_DAYS;
        try {
            maxDays = parametrosFacade.porCodigo("maximo.dias.dateRangePicker");
            if (maxDays == null || maxDays.isEmpty()) {
                maxDays = MAX_DAYS;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxDaysDateRangePicker, se utilizara valor por defecto", e);
        }
        return maxDays;
    }

    /**
     * Obtiene máximo de dias a consultar atraves de un dateRangePicker
     *
     * @return
     */
    public String obtenerMinLongitudImagenSeguridad() {
        String maxDays = MIN_IMAGEN_SEGURIDAD;
        try {
            maxDays = parametrosFacade.porCodigo("longitud.minima.descripcion.imagen.seguridad");
            if (maxDays == null || maxDays.isEmpty()) {
                maxDays = MIN_IMAGEN_SEGURIDAD;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMinLongitudImagenSeguridad, se utilizara valor por defecto", e);
        }
        return maxDays;
    }

    public int obtenerProcesoMigracionActivo() {
        int procesoMigracionActivo = MIGRACION_INACTIVA;
        String migracionActivo = parametrosFacade.porCodigo("proceso.migracion.activo");
        if (null != migracionActivo && !migracionActivo.equals("")) {
            try {
                procesoMigracionActivo = Integer.parseInt(migracionActivo);
                procesoMigracionActivo = (procesoMigracionActivo == MIGRACION_INACTIVA) ? MIGRACION_INACTIVA : MIGRACION_ACTIVA;//Controla que la variable no tenga otro valor para no afectar la vista. 
            } catch (Exception e) {
                procesoMigracionActivo = MIGRACION_INACTIVA;
            }
        }
        return procesoMigracionActivo;
    }

    /**
     * Obtiene el orden del estado de la asociacion
     *
     * @return
     */
    public BigInteger obtenerOrdenEstadoAsociacion(String estado) {
        String orden = null;
        switch (estado) {
            case "P":
                orden = ORDEN_ESTADO_PENDIENTE_ASOCIACION;
                break;
            case "C":
                orden = ORDEN_ESTADO_CANCELADA_ASOCIACION;
                break;
            case "F":
                orden = ORDEN_ESTADO_RECHAZADA_FALLIDO_ASOCIACION;
                break;
            case "I":
                orden = ORDEN_ESTADO_RECHAZADA_ASOCIACION;
                break;
            case "A":
                orden = ORDEN_ESTADO_ACTIVA_ASOCIACION;
                break;
            case "R":
                orden = ORDEN_ESTADO_REGENERADA_ASOCIACION;
                break;
            case "N":
                orden = ORDEN_ESTADO_ANULADA_ASOCIACION;
                break;
            case "T":
                orden = ORDEN_ESTADO_TERMINADA_ASOCIACION;
                break;
        }
        try {
            orden = parametrosFacade.porCodigo("asociacion.orden.estado." + estado);
            if (orden == null || orden.isEmpty()) {
                switch (estado.toString()) {
                    case "P":
                        orden = ORDEN_ESTADO_PENDIENTE_ASOCIACION;
                        break;
                    case "C":
                        orden = ORDEN_ESTADO_CANCELADA_ASOCIACION;
                        break;
                    case "F":
                        orden = ORDEN_ESTADO_RECHAZADA_FALLIDO_ASOCIACION;
                        break;
                    case "I":
                        orden = ORDEN_ESTADO_RECHAZADA_ASOCIACION;
                        break;
                    case "A":
                        orden = ORDEN_ESTADO_ACTIVA_ASOCIACION;
                        break;
                    case "R":
                        orden = ORDEN_ESTADO_REGENERADA_ASOCIACION;
                        break;
                    case "N":
                        orden = ORDEN_ESTADO_ANULADA_ASOCIACION;
                        break;
                    case "T":
                        orden = ORDEN_ESTADO_TERMINADA_ASOCIACION;
                        break;
                }
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerOrdenEstadoAsociacion, se utilizara valor por defecto", e);
        }

        return (new BigInteger(orden));
    }

    /**
     * Obtiene estado asociacion pendiente
     *
     * @return
     */
    public String obtenerEstadoAsociacionPendiente() {
        String estado = ESTADO_PENDIENTE_ASOCIACION;
        try {
            estado = parametrosFacade.porCodigo("asociacion.estado.pendiente");
            if (estado == null || estado.isEmpty()) {
                estado = ESTADO_PENDIENTE_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEstadoAsociacionPendiente, se utilizara valor por defecto", e);
        }
        return estado;
    }

    /**
     * Obtiene estado asociacion anulada
     *
     * @return
     */
    public String obtenerEstadoAsociacionAnulada() {
        String estado = ESTADO_ANULADA_ASOCIACION;
        try {
            estado = parametrosFacade.porCodigo("asociacion.estado.anulada");
            if (estado == null || estado.isEmpty()) {
                estado = ESTADO_ANULADA_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEstadoAsociacionAnulada, se utilizara valor por defecto", e);
        }
        return estado;
    }

    /**
     * Obtiene estado asociacion activa
     *
     * @return
     */
    public String obtenerEstadoAsociacionActiva() {
        String estado = ESTADO_ACTIVA_ASOCIACION;
        try {
            estado = parametrosFacade.porCodigo("asociacion.estado.activa");
            if (estado == null || estado.isEmpty()) {
                estado = ESTADO_ACTIVA_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEstadoAsociacionActiva, se utilizara valor por defecto", e);
        }
        return estado;
    }

    /**
     * Obtiene estado asociacion terminada
     *
     * @return
     */
    public String obtenerEstadoAsociacionTerminada() {
        String estado = ESTADO_TERMINADA_ASOCIACION;
        try {
            estado = parametrosFacade.porCodigo("asociacion.estado.terminada");
            if (estado == null || estado.isEmpty()) {
                estado = ESTADO_TERMINADA_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEstadoAsociacionTerminada, se utilizara valor por defecto", e);
        }
        return estado;
    }

    /**
     * Obtiene estado asociacion cancelada
     *
     * @return
     */
    public String obtenerEstadoAsociacionCancelada() {
        String estado = ESTADO_CANCELADA_ASOCIACION;
        try {
            estado = parametrosFacade.porCodigo("asociacion.estado.cancelada");
            if (estado == null || estado.isEmpty()) {
                estado = ESTADO_CANCELADA_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEstadoAsociacionCancelada, se utilizara valor por defecto", e);
        }
        return estado;
    }

    /**
     * Obtiene estado asociacion rechazada
     *
     * @return
     */
    public String obtenerEstadoAsociacionRechazada() {
        String estado = ESTADO_RECHAZADA_ASOCIACION;
        try {
            estado = parametrosFacade.porCodigo("asociacion.estado.rechazada");
            if (estado == null || estado.isEmpty()) {
                estado = ESTADO_RECHAZADA_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEstadoAsociacionRechazada, se utilizara valor por defecto", e);
        }
        return estado;
    }

    /**
     * Obtiene estado asociacion rechazada
     *
     * @return
     */
    public String obtenerEstadoAsociacionRegenerada() {
        String estado = ESTADO_REGENERADA_ASOCIACION;
        try {
            estado = parametrosFacade.porCodigo("asociacion.estado.regenerada");
            if (estado == null || estado.isEmpty()) {
                estado = ESTADO_REGENERADA_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEstadoAsociacionRegenerada, se utilizara valor por defecto", e);
        }
        return estado;
    }

    /**
     * Obtiene estado asociacion rechazada
     *
     * @return
     */
    public String obtenerEstadoAsociacionRechazadaFallido() {
        String estado = ESTADO_RECHAZADA_FALLIDO_ASOCIACION;
        try {
            estado = parametrosFacade.porCodigo("asociacion.estado.rechazada.fallido");
            if (estado == null || estado.isEmpty()) {
                estado = ESTADO_RECHAZADA_FALLIDO_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEstadoAsociacionRechazadaFallido, se utilizara valor por defecto", e);
        }
        return estado;
    }

    /**
     * Obtiene estado asociacion rechazada
     *
     * @return
     */
    public String obtenerMaxLongBusquedaAsociaciones() {
        String longMax = LONGITUD_MAXIMA_BUSQUEDA_ASOCIACION;
        try {
            longMax = parametrosFacade.porCodigo("asociacion.longitud.maxima.busqueda");
            if (longMax == null || longMax.isEmpty()) {
                longMax = LONGITUD_MAXIMA_BUSQUEDA_ASOCIACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaxLongBusquedaAsociaciones, se utilizara valor por defecto", e);
        }
        return longMax;
    }

    /**
     * Obtiene la mascara utilizada en la configuracion de las preguntas de
     * desafio
     *
     * @return
     */
    public String obtenerMascaraPreguntasDesafio() {
        String mascara = MASCARA_PREGUNTAS_DESAFIO;
        try {
            mascara = parametrosFacade.porCodigo("configuracion.preguntas.mascara");
            if (mascara == null || mascara.isEmpty()) {
                mascara = MASCARA_PREGUNTAS_DESAFIO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMascaraPreguntasDesafio, se utilizara valor por defecto", e);
        }
        return mascara;
    }

    /**
     * Obtiene la longitud maxima de la descripcion de la imagen
     *
     * @return
     */
    public String obtenerLongMaxDescripcionImagen() {
        String longitudMax = LONGITUD_MAXIMA_DESCRIPCION_IMAGEN;
        try {
            longitudMax = parametrosFacade.porCodigo("long.max.descripcion.imagen");
            if (longitudMax == null || longitudMax.isEmpty()) {
                longitudMax = LONGITUD_MAXIMA_DESCRIPCION_IMAGEN;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerLongMaxDescripcionImagen, se utilizara valor por defecto", e);
        }
        return longitudMax;
    }

    /**
     * Obtiene el tipo de documento pasaporte
     *
     * @return
     */
    public String obtenerTipoDocumentoPasaporte() {
        String tipoDocPasaporte = TIPO_DOC_PASAPORTE;
        try {
            tipoDocPasaporte = parametrosFacade.porCodigo("identificacion.documento.pasaporte");
            if (tipoDocPasaporte == null || tipoDocPasaporte.isEmpty()) {
                tipoDocPasaporte = TIPO_DOC_PASAPORTE;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerTipoDocumentoPasaporte, se utilizara valor por defecto", e);
        }
        return tipoDocPasaporte;
    }

    /**
     * Metodo para obtener el parametro de Switch Global de WBFD
     *
     * @return 0 = Inactivo, 1= Activo.
     */
    public int obtenerSwitchWbfd() {
        int switchWBFD = 0;
        try {
            switchWBFD = Integer.parseInt(parametrosFacade.porCodigo("wbfd.switch.nbl"));
        } catch (NumberFormatException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerSwitchWbfd, se utilizara valor por defecto", e);
        }
        return switchWBFD;

    }

    /**
     * Metodo para obtener el parametro de Swith de Events de WBFD
     *
     * @return 0 = Inactivo, 1= Activo.
     */
    public int obtenerSwitchEventsWbfd() {
        int switchWbfdEvents = 0;
        try {
            switchWbfdEvents = Integer.parseInt(parametrosFacade.porCodigoNoAutenticado("wbfd.switch.events"));
        } catch (NumberFormatException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerSwitchEventsWbfd, se utilizara valor por defecto", e);
        }
        return switchWbfdEvents;

    }

    /**
     * Metodo que se encarga de hacer un match de las acciones por realizar de
     * la entidad statecore con respecto a la entidad NBL de forma tal que NBL
     * tenga la capacidad de disparar, las acciones por realizar dictaminadas
     * por el manejador de estados.
     *
     * @param accion accion a ejecutar, codigo a buscar en las operaciones NBL
     * @return accion por realizar a ejecutar, administrable
     */
    public String obtenerAccionRealizarStateCore(String accion) {
        String switchWbfdEvents = "";
        try {
            switchWbfdEvents = parametrosFacade.porCodigoNoAutenticado("accion.realizar.statecore." + accion);
        } catch (NumberFormatException e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerAccionRealizarStateCore, se utilizara valor por defecto", e);
        }
        return switchWbfdEvents;

    }

    public List<BeanTipoAprobador> obtenerListaTipoAprobadoresNominayProveedores() {
        List<BeanTipoAprobador> listAprobador = null;
        try {
            List<Parametros> listaAprobador = parametrosFacade.parametrosPorTipo("tipo.aprobador.nomina.proveedores");
            if (listaAprobador == null || listaAprobador.isEmpty()) {
                List<String> listTipoAprobador = Arrays.asList(ARRAY_TIPO_APROBADOR);

                if (listTipoAprobador != null && !listTipoAprobador.isEmpty()) {
                    BeanTipoAprobador beanTipoAprobador;
                    listAprobador = new ArrayList();
                    for (String aprobador : listTipoAprobador) {
                        beanTipoAprobador = new BeanTipoAprobador();
                        beanTipoAprobador.setCodigo(aprobador);
                        beanTipoAprobador.setDescripcion(aprobador);
                        listAprobador.add(beanTipoAprobador);
                    }
                }
                return listAprobador;
            }
            listAprobador = new ArrayList();
            BeanTipoAprobador beanTipoAprobador;
            for (Parametros parametro : listaAprobador) {
                beanTipoAprobador = new BeanTipoAprobador();
                beanTipoAprobador.setCodigo(parametro.getValorPorDefecto());
                beanTipoAprobador.setDescripcion(parametro.getNombre());
                listAprobador.add(beanTipoAprobador);
            }

        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerListaTipoAprobadoresNominayProveedores, se utilizara valor por defecto", e);
            try {
                List<String> listTipoAprobador = Arrays.asList(ARRAY_TIPO_APROBADOR);

                if (listTipoAprobador != null && !listTipoAprobador.isEmpty()) {
                    BeanTipoAprobador beanTipoAprobador;
                    listAprobador = new ArrayList();
                    for (String aprobador : listTipoAprobador) {
                        beanTipoAprobador = new BeanTipoAprobador();
                        beanTipoAprobador.setCodigo(aprobador);
                        beanTipoAprobador.setDescripcion(aprobador);
                        listAprobador.add(beanTipoAprobador);
                    }
                    return listAprobador;
                }
            } catch (Exception ex) {
                Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                        "Advertencia obtenerListaTipoAprobadoresNominayProveedores, se utilizara valor por defecto", ex);
            }
        }
        return listAprobador;

    }

    public List<BeanTipoAprobador> obtenerListaTipoAprobadores() {
        List<BeanTipoAprobador> listAprobador = null;
        try {
            List<Parametros> listaAprobador = parametrosFacade.parametrosPorTipo("tipo.aprobador");
            if (listaAprobador == null || listaAprobador.isEmpty()) {
                List<String> listTipoAprobador = Arrays.asList(ARRAY_TIPO_APROBADOR);

                if (listTipoAprobador != null && !listTipoAprobador.isEmpty()) {
                    BeanTipoAprobador beanTipoAprobador;
                    listAprobador = new ArrayList();
                    for (String aprobador : listTipoAprobador) {
                        beanTipoAprobador = new BeanTipoAprobador();
                        beanTipoAprobador.setCodigo(aprobador);
                        beanTipoAprobador.setDescripcion(aprobador);
                        listAprobador.add(beanTipoAprobador);
                    }
                }
                return listAprobador;
            }
            listAprobador = new ArrayList();
            BeanTipoAprobador beanTipoAprobador;
            for (Parametros parametro : listaAprobador) {
                beanTipoAprobador = new BeanTipoAprobador();
                beanTipoAprobador.setCodigo(parametro.getValorPorDefecto());
                beanTipoAprobador.setDescripcion(parametro.getNombre());
                listAprobador.add(beanTipoAprobador);
            }

        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerListaTipoAprobadores, se utilizara valor por defecto", e);
            try {
                List<String> listTipoAprobador = Arrays.asList(ARRAY_TIPO_APROBADOR);

                if (listTipoAprobador != null && !listTipoAprobador.isEmpty()) {
                    BeanTipoAprobador beanTipoAprobador;
                    listAprobador = new ArrayList();
                    for (String aprobador : listTipoAprobador) {
                        beanTipoAprobador = new BeanTipoAprobador();
                        beanTipoAprobador.setCodigo(aprobador);
                        beanTipoAprobador.setDescripcion(aprobador);
                        listAprobador.add(beanTipoAprobador);
                    }
                    return listAprobador;
                }
            } catch (Exception ex) {
                Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                        "Advertencia obtenerListaTipoAprobadores, se utilizara valor por defecto", ex);
            }
        }
        return listAprobador;

    }

    public String obtenerDescripcionCodigoAprobador(String codigo) {
        String descripcion = null;

        try {
            List<Parametros> listaTipoAprobador = parametrosFacade.parametrosPorTipo("tipo.aprobador");
            if (listaTipoAprobador == null || listaTipoAprobador.isEmpty()) {
                return codigo;
            }

            for (Parametros parametro : listaTipoAprobador) {
                if (parametro.getValorPorDefecto().equals(codigo)) {
                    descripcion = parametro.getNombre();
                    break;
                }
            }
            if (descripcion == null || descripcion.isEmpty()) {
                return codigo;
            }

        } catch (Exception ex) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerDescripcionCodigoAprobador, se utilizara valor por defecto", ex);
            return codigo;
        }
        return descripcion;
    }

    /**
     * Obtiene la mascara utilizada en la configuracion de las preguntas de
     * desafio
     *
     * @return
     */
    public String obtenerMinimoRespuestasSeguridad() {
        String minimo = MINIMO_RESPUESTA_SEGURIDAD;
        try {
            minimo = parametrosFacade.porCodigo("login.minimo.respuestas.seguridad");
            if (minimo == null || minimo.isEmpty()) {
                minimo = MINIMO_RESPUESTA_SEGURIDAD;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMinimoRespuestasSeguridad, se utilizara valor por defecto", e);
        }
        return minimo;
    }

    /**
     * Obtiene la mascara utilizada en la configuracion de las preguntas de
     * desafio
     *
     * @return
     */
    public String obtenerMaximoTarjetaCoordenadas() {
        String maximo = MAXIMO_TARJETA_COORDENADAS;
        try {
            maximo = parametrosFacade.porCodigo("tarjeta.coordenada.maximo");
            if (maximo == null || maximo.isEmpty()) {
                maximo = MAXIMO_TARJETA_COORDENADAS;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerMaximoTarjetaCoordenadas, se utilizara valor por defecto", e);
        }
        return maximo;
    }

    public String obtenerEmailHost() {
        String emailHost = EMAIL_HOST;

        try {
            emailHost = parametrosFacade.porCodigo("email.host");
            if (emailHost == null || emailHost.isEmpty()) {
                emailHost = EMAIL_HOST;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEmailHost, se utilizara valor por defecto", e);
        }

        return emailHost;
    }

    public String obtenerEmailFrom() {
        String emailFrom = EMAIL_FROM;

        try {
            emailFrom = parametrosFacade.porCodigo("email.from");
            if (emailFrom == null || emailFrom.isEmpty()) {
                emailFrom = EMAIL_FROM;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEmailFrom, se utilizara valor por defecto", e);
        }

        return emailFrom;
    }

    public String obtenerEmailPassword() {
        String emailPassword = EMAIL_PASSWORD;

        try {
            emailPassword = parametrosFacade.porCodigo("email.password");
            if (emailPassword == null || emailPassword.isEmpty()) {
                emailPassword = EMAIL_PASSWORD;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEmailPassword, se utilizara valor por defecto", e);
        }

        return emailPassword;
    }

    public String obtenerEmailPort() {
        String emailPort = EMAIL_PORT;

        try {
            emailPort = parametrosFacade.porCodigo("email.port");
            if (emailPort == null || emailPort.isEmpty()) {
                emailPort = EMAIL_PORT;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerEmailPort, se utilizara valor por defecto", e);
        }

        return emailPort;
    }

    public boolean isAyudaPrimeraVez() {
        if (!ayudaVerificada) {
            ayudaPrimeraVez = Boolean.parseBoolean(parametrosFacade.valorPorCodigo("ayuda.primera.vez"));
            ayudaVerificada = true;
        }
        return ayudaPrimeraVez;
    }

    public void setAyudaPrimeraVez(boolean ayudaPrimeraVez) {
        this.ayudaPrimeraVez = ayudaPrimeraVez;
    }

    public Boolean muestraIconTourAyuda() {
        return Boolean.parseBoolean(parametrosFacade.valorPorCodigo("mostrar.incono.tour.ayuda"));
    }
    
    /**
     * Obtiene la cantidad de preguntas de desafio a realizar al usuario cuando ingresa en un equipo de uso no frecuente
     *
     * @return
     */
    public String obtenerCantPreguntasDesafio() {
        String cantPreguntasDesafio = CANT_PREGUNTAS_DESAFIO;
        try {
            cantPreguntasDesafio = parametrosFacade.porCodigo("cantidad.preguntas.desafio");
            if (cantPreguntasDesafio == null || cantPreguntasDesafio.isEmpty()) {
                cantPreguntasDesafio = CANT_PREGUNTAS_DESAFIO;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerCantPreguntasDesafio, se usara valor por defecto", e);
        }
        return cantPreguntasDesafio;
    }
    
    public String obtenerVersionRecurso(){
        return "v=0.0.6";
    }
    
    public String obtenerActividadMigracionClave() {
        String Actividadid = ACTIVIDAD_CLAVE_MIGRACION;
        try {
            Actividadid = parametrosFacade.porCodigoNoAutenticado("sesion.actividad.migracion.clave.nbl");
            if (Actividadid == null || Actividadid.isEmpty()) {
                Actividadid = ACTIVIDAD_CLAVE_MIGRACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerActividadMigracionClave, se usara valor por defecto", e);
        }
        return Actividadid;
    }
    
    public String obtenerProcesoMigracionClave() {
        String procesoId = PROCESO_CLAVE_MIGRACION;
        try {
            procesoId = parametrosFacade.porCodigoNoAutenticado("sesion.proceso.validacion.clave.nbl");
            if (procesoId == null || procesoId.isEmpty()) {
                procesoId = PROCESO_CLAVE_MIGRACION;
            }
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerProcesoMigracionClave, se usara valor por defecto", e);
        }
        return procesoId;
    }
    
    public String obtenerVersionApp(){
        return "v1.0.0";
    }
    
    public String obtenerUrlBaseRecursos() {
        String urlBase = "";
        try {
            urlBase = parametrosFacade.porCodigo("servidor.recursos.url");
        } catch (Exception e) {
            Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), "PARAMETROS NBL",
                    "Advertencia obtenerUrlBaseRecursos, se usara valor por defecto", e);
        }
        return urlBase;
    }
}
