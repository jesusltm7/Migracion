package com.bod.util;

import com.bod.beans.BeanCliente;
import com.bod.beans.IBDetallePerfilAsociado;
import com.bod.facade.ParametrosFacade;
import com.bod.facade.ClientesFacade;
import com.bod.facade.ClientesHasUsuariosNblFacade;
import com.bod.facade.DetLimitesFacade;
import com.bod.facade.DetreglaparametrosFacade;
import com.bod.facade.InvitacionesFacade;
import com.bod.facade.MstmedioenvioFacade;
import com.bod.facade.OperacionesNblFacade;
import com.bod.facade.PerfilNblFacade;
import com.bod.facade.PerfilesHasOperacionesFacade;
import com.bod.facade.TextosFacade;
import com.bod.model.Clientes;
import com.bod.model.Detlimites;
import com.bod.model.Detreglaparametros;
import com.bod.model.Invitaciones;
import com.bod.model.OperacionesNbl;
import com.bod.model.Parametros;
import com.bod.model.PerfilesNbl;
import com.bod.model.PerfilesnblHasOperacionesnbl;
import com.bod.model.PerfilesnblHasOperacionesnblPK;
import com.bod.model.Productos;
import com.bod.model.TipoPerfilesNbl;
import com.bod.facade.TipoPerfilesNblFacade;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceException;

/**
 * Clase encargada de darle utilidad, redistribuible para todo el proyecto del
 * ambito JAVA SERVER FACES
 *
 * @author Yaher Carrillo
 */
public abstract class BodBaseBean {

    @EJB
    private MstmedioenvioFacade medioenvioFacade;

    @EJB
    private ClientesFacade clientesFacade;

    @EJB
    private TextosFacade textosFacade;

    @EJB
    private ParametrosFacade parametrosFacade;

    @EJB
    private OperacionesNblFacade operacionesNblFacade;

    @EJB
    private InvitacionesFacade invitacionesFacade;

    @EJB
    private DetLimitesFacade detLimitesFacade;

    @EJB
    private DetreglaparametrosFacade reglaParametrosFacade;

    /**
     * variable para el manejo de encriptacion / desencriptacion.
     */
    private EncrypterDecrypter encrypterDecrypter;

    /**
     * Id del canal correspondiente a esta aplicación *
     */
    public static long CANAL_ID = 1L;

    public static final String TIPO_IDENTIFICACION_JURIDICA = "tipos.identificacion.juridica";

    @EJB
    private ClientesHasUsuariosNblFacade clientesHasUsuariosNblFacade;

    @EJB
    private PerfilesHasOperacionesFacade perfilesHasOperacionesFacade;

    @EJB
    private PerfilNblFacade perfilNblFacade;

    @EJB
    TipoPerfilesNblFacade tipoPerfilesNblFacade;
    /**
     * Log de sistema
     */
    private static final Logger logger = Logger.getLogger(BodBaseBean.class.getName());

    /**
     * Constante de concatenacion para el llamado de servicios
     */
    public static final String WSDL = "?wsdl";

    /**
     * Esquema BDD Bod
     */
    public static final String SCHEMA_APP = "ABLBOD";

    /**
     * Ruta url utilizada por faces /f/
     */
    public static final String rutaFaces = "/f/";

    /**
     * Variable estatica encargada de mantener la navegabilidad al archivo que
     * se indica, Inicio de Aplicacion
     */
    public static String RETURN_PAGE_INDEX = "/";

    /**
     * Redirector estable de nevegaciones entre paginas, es decir, cuando se
     * navega de una pagina a otra actualiza el URL o en su defecto muestra el
     * url de la pagina donde se esta ubicado
     */
    public static final String REDIRECTOR = "?faces-redirect=true";

    /**
     * Variable estatica encargada de mantener la navegabilidad al archivo que
     * se indica, Inicio de Aplicacion ---> home.xhtml
     */
    public static String NO_PERMISO_PAGE = "sec/home";

    /**
     * Variable estatica encargada de mantener la navegabilidad al archivo que
     * se indica, Inicio de Aplicacion ---> home.xhtml
     */
    public static String REDIRECT_HOME = "/sec/home";
    //public static String RETURN_PRE_CUENTAS = "sec/resumen_cuentas";
    public static String RETURN_PRE_TARJETAS = "sec/resumen_tarjetas";
    public static String RETURN_ASOCIACIONES = "sec/Asociaciones/index";
    public static String RETURN_PRE_FIDEICOMISOS = "sec/Fideicomisos/index";
    public static String RETURN_CONSULTA_DIRECTORIO_GLOBAL = "sec/Directorio/index";
    public static String REDIRECT_ERRORES_ARCHIVO_DIRECTORIO_GLOBAL = "sec/Directorio/errores_directorioglobal";
    public static String REDIRECT_CARGA_ARCHIVO_DIRECTORIO_GLOBAL = "sec/Directorio/carga_directorioglobal";
    public static char ESTADO_GENERICO_ACTIVO = 'A';
    public static char ESTADO_GENERICO_INACTIVO = 'I';

    /**
     * Constante para uso técnico en la tabla CLIENTES_HAS_USUARIOS_NBL con el
     * propósito de identificar un perfil de usuario master en especifico
     */
    public static String CONDICION_FIRMANTE_MA = "MA";

    /**
     * Constante para uso técnico en la tabla CLIENTES_HAS_USUARIOS_NBL con el
     * propósito de identificar un perfil de usuario unico principal en
     */
    public static String CONDICION_FIRMANTE_UP = "UP";

    /**
     * Constante para uso técnico en la tabla CLIENTES_HAS_USUARIOS_NBL con el
     * propósito de identificar un registro de antiguo UP
     *
     */
    public static String CONDICION_FIRMANTE_ANTIGUO_UP = "AU";

    /**
     * Constante para uso técnico en la tabla CLIENTES_HAS_USUARIOS_NBL con el
     * propósito de identificar registro de asociados
     *
     */
    public static String CONDICION_USUARIO_ASOCIADO = "AS";
    /**
     * Constante para uso técnico en la tabla CLIENTES_HAS_USUARIOS_NBL con el
     * propósito de identificar registro de asociados negados por nuevo up
     *
     */
    public static String CONDICION_ASOCIACION_NEGADA = "AN";

    /**
     * Constante para uso técnico en la tabla CLIENTES_HAS_USUARIOS_NBL con el
     * propósito de identificar registro de naturales
     *
     */
    public static String CONDICION_USUARIO_NATURAL = "NA";

    /**
     * Variable estatica encargada de indicar el nombre del archivo de descarga
     * referentes a los creditos de un usuario
     */
    public static String FILE_NAME_CREDITS = "certificadoCredito.pdf";

    /**
     * Path que identica una llave para las direcciones fisicas de los archivos
     * JASPER para realizar reportes
     */
    public static String PATH_NAME_FILE_REPORT = "direccionReporte";

    /**
     * ***VARIABLES DE ACCIONES POR REALIZAR POR DEFECTO***
     */
    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a las preguntas de seguridad
     */
    public static final String ACTION_HABILITAR_ASOCIADOS = "SEND_ENABLE_ASOCCIATE";

    /**
     * Formato para las fechas Dia/Mes/Ano
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String PERFIL_BASE_JURIDICO = "Juridicos";
    private static final String PERFIL_BASE_NATURAL = "Naturales";
    /**
     * Posibles estatus de usuario del nbl
     */
    public static final char ESTATUS_USUARIO_AFILIACION_INICIADA = 'A';
    public static final char ESTATUS_USUARIO_BLOQUEADO = 'B';
    public static final char ESTATUS_USUARIO_USUARIO_TOMADO = 'U';
    public static final char ESTATUS_USUARIO_CONTRASENA_CREADA = 'C';
    public static final char ESTATUS_USUARIO_DESAFILIADO = 'D';
    public static final char ESTATUS_USUARIO_IMAGEN_SELECCIONADA = 'I';
    public static final char ESTATUS_USUARIO_PREGUNTAS_SELECCIONADA = 'P';
    public static final char ESTATUS_USUARIO_REGISTRADO = 'R';
    public static final char ESTATUS_USUARIO_REAFILIADO = 'R';
    public static final char ESTATUS_CLIENTEUSUARIO_ACTIVO = 'A';
    public static final char ESTATUS_CLIENTEUSUARIO_BLOQUEADO = 'B';
    //Se toma de la K en adelante para migracion hasta la M.
    public static final char ESTATUS_MIGRACION_INICIADA = 'K';
    public static final char ESTATUS_MIGRACION_CONTRASENA_CREADA = 'L';
    public static final char ESTATUS_MIGRACION_REGISTRADOSTATECORE = 'M';

    public static final char ESTATUS_INVITACION_PENDIENTE = 'P';
    public static final char ESTATUS_INVITACION_ACTIVA = 'A';
    public static final char ESTATUS_INVITACION_REGENERADA = 'R';
    public static final char ESTATUS_INVITACION_FINALIZADA = 'R';

    public static final BigDecimal ACCION_REALIZAR_NIVEL_INFORMATIVO = BigDecimal.valueOf(1l);
    public static final BigDecimal ACCION_REALIZAR_NIVEL_ALERTA = BigDecimal.valueOf(2l);
    public static final BigDecimal ACCION_REALIZAR_NIVEL_ERROR = BigDecimal.valueOf(3l);

    public static final char ACCION_REALIZAR_LECTURA_LEIDA = '0';
    public static final char ACCION_REALIZAR_LECTURA_NO_LEIDA = '1';
    /**
     * Constante para manejo de perfil inactivo del cliente
     */
    public static final String ESTATUS_CUENTA_CANCELADA = "C";

    /**
     * Constante para manejo de perfil activo del cliente
     */
    public static final String ESTATUS_CUENTA_ACTIVA = "A";

    /**
     * Constante para manejo de perfil activo del cliente
     */
    public static final char ESTATUS_PERFIL_CLIENTE_ACTIVO = 'A';

    /**
     * Constante para manejo de perfil inactivo del cliente
     */
    public static final char ESTATUS_PERFIL_CLIENTE_INACTIVO = 'I';

    /*
     Identificador de directorio global activo
     */
    public static final char DIRECTORIO_GLOBAL_ACTIVO = 'A';
    /**
     * Sufijo de busqueda para los tipos de solicitudes cadivi
     */
    public static final String SUFFIX_SOL_TYPE_CADIVI = "cadivi.tipo.";

    /**
     * Sufijo de busqueda para los estatus de solicitudes cadivi
     */
    public static final String SUFFIX_SOL_STS_CADIVI = "cadivi.sts.";

    /**
     * Sufijo de todos los textos asociados a la llave "sts" para la busqueda de
     * estatus cadivi
     */
    public static final String SUFFIX_CODE_STS_CADIVI = "stc";

    /**
     * Sufijo de todos los textos asociados a la llave "sts" para la busqueda de
     * tipos de estatus cadivi
     */
    public static final String SUFFIX_CODE_TYPE_CADIVI = "tsc";

    /**
     * Sufijo que se adjunta para encriptar datos importantes de usuarios como
     * lo son Numero de Cuenta, TDC entre otros.
     */
    public static final String SUFFIX_CODE_ENCRYPT = "x";

    /**
     * Sufijo que se adjunta para encriptar datos importantes de usuarios como
     * lo son Numero de Cuenta, TDC entre otros.
     */
    public static final String SUFFIX_CODE_IDIOMA_NEUTRO = "xx";

    /**
     * Estatus de busqueda para la consulta del tipo de texto para la busqueda
     * de los estatus disponibles para TDC
     */
    public static final String SUFFIX_CODE_STATUS_TDC = "tdc";

    /**
     * Prefijo se complemento para encontrar los valores de estatus de TDC
     */
    public static final String PREFFIX_CODE_STATUS_TDC = "status.tdc.";
    public static final String PREFFIX_CODE_STATUS_TDC_CANCELADA = "status.tdc.C";
    public static final String PREFFIX_CODE_STATUS_TDC_CASTIGADA = "estado.tdc.castigada";

    /**
     * Sufijo para realizar la consulta de los estatus asociados a una cuenta
     * bancaria
     */
    public static final String SUFFIX_CODE_STATUS_ACCT = "sts.cuentas.acct";

    //public static final String SIFFIX_CODE_MSG_GENERIC_ERROR = "message.general.services";
    public static final String CODE_MSG_GENERIC_ERROR = "error.inesperado";

    /**
     * Etiqueta para la busqueda del tiempo de sesion permitido para la
     * aplicacion NBL
     */
    public static final String SESSION_TIME_NBL = "sesion.tiempo.nbl";

    /**
     * Etiqueta de redireccionamiento a la pagina de inicio
     */
    public static String PAGE_REDIRECT_HOME = "/ext/Login/index";

    /**
     * Etiqueta para la busqueda del valor por defecto de las cantidad de
     * sesiones permitidas de un usuario
     */
    public static final String MULTIPLEX_SESSION_QUANTITY_NBL = "sesion.cantidad.nbl";

    /**
     * Etiqueta para la busqueda del valor por defecto de las cantidad de
     * sesiones permitidas de un usuario
     */
    public static final String ID_SESSION = "idsessionNbl";

    /**
     * Sufijo para la busqueda del ancho de la torta financiera
     */
    public static final String SUFIJO_COD_ANCHO_TORTA = "panel.financiero.torta.ancho";

    /**
     * Sufijo para la busqueda de LA ALTURA de la torta financiera
     */
    public static final String SUFIJO_COD_ALTO_TORTA = "panel.financiero.torta.altura";

    /**
     * Sufijo para la busqueda de la fuente de la torta financiera
     */
    public static final String SUFIJO_COD_FUENTE_TORTA = "panel.financiero.torta.tamano.fuente";

    /**
     * Sufijo para la busqueda de la categoria de texto referente a los tipos de
     * operacion de cuentas de usuarios
     */
    public static final String SUFIJO_CAT_TOPERACION = "toc";

    /**
     * Sufijo para la busqueda de la categoria de texto referente a los tipos de
     * operacion de cuentas de usuarios
     */
    public static final String PREFIX_CANALES_CADIVI = "canales.consumo.cadivi";

    /**
     * Sufijo para la busqueda de la categoria de texto referente a los tipos de
     * operacion de cuentas de usuarios
     */
    public static final String PREFIX_TIPOS_TDC = "tipos.tdc";

    /**
     * Sufijo para la busqueda de la categoria de texto referente a los tipos de
     * operaciones de TDC
     */
    public static final String SUFIJO_CAT_TDC_OPERACIONES = "tdc.operaciones";

    /**
     * Sufijo para la busqueda de la categoria de texto referente a los al
     * estado de asociaciones
     */
    public static final String SUFIJO_ESTADO_ASOCIACIONES = "eia";

    /**
     * Ruta o nombre del servlet encargado de gestionar las descargas de
     * archivos de la aplicacion
     */
    public static final String SRC_SERVLET_DESCARGAS = "/reportes";

    /**
     * Ruta o nombre del servlet encargado de gestionar los OTP
     */
    public static final String SRC_SERVLET_SMS_OTP = "/sendOtps";

    /**
     * Objeto de busqueda de sesion, para la descarga de objetos
     */
    public static final String SRC_OBJ_SESSION = "reporteBean";

    /**
     * Objeto que indica el codigo de banco utlizado por BOD
     */
    public static final String CODE_ID_BANCO = "0116";

    /**
     * Codigo por defecto del estatus de zona segura de los usaurios
     */
    public static final String CODE_DEFAULT_ZONA_SEGURA = "I";

    /*
     * Valor por defecto para equipo de uso frecuente.
     */
    public static final char CODE_DEFAULT_USO_FRECUENTE = 'I';

    /**
     * Constante que represeenta el estado activo de las operaciones que
     * contemplen la seguridad de WBFD
     */
    public static final String WBFD_HABILITADO = "1";

    /**
     * **********************Tipo de perfiles de afiliacion de NBL
     */
    public static final String TIPO_PERFIL_NATURALES = "bod.base.natural";
    public static final String TIPO_PERFIL_JURIDICO = "bod.base.juridico";
    public static final String TIPO_PERFIL_CODIGO_MASTER = "bod.base.asociado.master";
    public static final String CODIGO_PERFIL_REGULAR = "bod.base.asociado.regular";
    public static final String TIPO_PERFIL_ASOCIACION = "bod.base.asociado";

    /**
     * **********************Tipo de perfiles de afiliacion de NBL
     */
    /**
     * **********************Textos de uso para contingencia de WBFD
     */
    public static final String TEXTOS_VACIO_WBFD = "No posee";

    public static final String TELEFONO_VACIO_WBFD = "9999999999";

    /**
     * Constante para codigo de estado de tdc castigada
     */
    public static final String CODIGO_ESTADO_TDC_CASTIGADA = "a-08-C";

    /**
     * Parametros utilizados para ordenar la consulta de pagos offline
     */
    public static final int WS_PAGOS_OFFLINE_CONSULTA_ORDEN_FECHA = 1;

    public static final int WS_PAGOS_OFFLINE_CONSULTA_ORDEN_CANAL = 2;

    public static final int WS_PAGOS_OFFLINE_CONSULTA_ORDEN_CLIENTE = 3;

    public static final int WS_PAGOS_OFFLINE_CONSULTA_ORDEN_PAGO = 4;

    /**
     * Paramtros utilizados para visualizar la consulta de pagos offline
     */
    public static final int WS_PAGOS_OFFLINE_CONSULTA_POR_DEFECTO = 0;

    public static final int WS_PAGOS_OFFLINE_CONSULTA_VISUALIZAR_SUBTOTAL = 1;

    public static final int WS_PAGOS_OFFLINE_CONSULTA_VISUALIZAR_TOTAL = 2;

    public static final int WS_PAGOS_OFFLINE_CONSULTA_VISUALIZAR_DESCENDENTE = 3;

    public static final String WS_PAGOS_OFFLINE_CONSULTA_ACTIVO = "X";

    public static final int CANTIDAD_REGISTROS_LEER = 235;

    public static final int PROCESAR_REGISTROS_DESDE = 1;

    /**
     * ESTADO POSIBLES DE LAS TARJETAS DE CREDITO
     */
    public static final String ALL_STATE = "Todas";

    public static final String STATE_ACTIVE = "Activa";

    public static final String STATE_CANCELLED = "Cancelada";

    public static final String STATE_PUNISHED = "Castigada";

    public static final String STATE_PENDING = "Pendiente por Activar";

    /**
     * ESTADO POSIBLES DE LAS TARJETAS DE CREDITO
     */
    /**
     * Parametros posibles de metodo de entrega de notificaciones NBL.
     */
    public static final int METODO_ENTREGA_SMS = 1;

    public static final int METODO_ENTREGA_CORREO = 2;

    public static final int METODO_ENTREGA_AMBOS = 3;
    /**
     * Parametros posibles de operaciones de NBL.
     */

    public static final int OPERACION_FINANCIERA = 1;

    public static final int OPERACION_NO_FINANCIERA = 2;

    public static final int OPERACION_TIPO_FINANCIERA = 81;

    private static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat FORMAT_DATE_HOUR = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    /**
     * Parametros para identificar el estado de las notificaciones NBL.
     */
    public static final int NOTIFICACION_ACTIVA = 1;

    public static final int NOTIFICACION_INACTIVA = 0;

    public static final int NOTIFICACION_INACTIVA_MURO_INACTIVO = 0;

    public static final int NOTIFICACION_ACTIVA_MURO_ACTIVO = 1;

    public static final int NOTIFICACION_INACTIVA_MURO_ACTIVO = 2;

    public static final int NOTIFICACION_ACTIVA_MURO_INACTIVO = 3;

    public static final int NOTIFICACION_SMS = 1;

    public static final int NOTIFICACION_EMAIL = 2;

    public static final int NOTIFICACION_SMS_EMAIL = 3;

    public static final String NOTIFICACION_NUMERO_REFERENCIA = "NBL00011";

    public static final String NOTIFICACION_CATEGORIA_MBA = "MBA";

    public static final String NOTIFICACION_CATEGORIA_CTA = "CTA";

    public static final String NOTIFICACION_SMS_EXITO = "000";

    public static final String NOTIFICACION_METODO_ENVIO_SMS = "2";

    /**
     * Monto minimo de Notificacion.
     */
    public static final float MONTO_MINIMO = 1000;
    /**
     * Parametros para tipo de transaccion de WBFD.
     */

    public static final String TRANSACCION_DEBITO = "D";

    public static final String TRANSACCION_CREDITO = "C";

    public static final String TRANSACCION_OTRA = "O";

    /**
     * Código que indica una Agrupación de las Transacciones de WBFD.
     */
    public static final String TIPO_TRANSACCION_TRANSFERENCIA = "T";

    public static final String TIPO_TRANSACCION_PAGO_TDC = "P";

    public static final String TIPO_TRANSACCION_PAGO_SERVICIOS = "S";

    public static final String TIPO_TRANSACCION_PAGO_CREDITOS = "C";

    public static final String TIPO_TRANSACCION_PAGO_NOMINA_PROVEEDORES = "N";

    /**
     * Tipo de operacion para Events de WBFD.
     */
    public static final String EVENTS_ACTIVACION_CHEQUERA = "ACT";

    public static final String EVENTS_SUSPENSION_CHEQUE = "SCHK";

    public static final String EVENTS_SUSPENSION_CHEQUERA = "SCHKBK";

    public static final String EVENTS_ACTUALIZACION_CORREO = "EMAIL";

    public static final String EVENTS_ACTUALIZACION_TELEFONO = "CEL";

    /**
     * Código que indica si el pago de tarjetas es para el cliente o un tercero
     * en WBFD.
     */
    public static final char PAGO_TDC_TERCERO = 'S';

    public static final char PAGO_TDC_PROPIO = 'N';

    /**
     * Códigos de Errores del Host del WBFD.
     */
    public static final String ERROR_HOST_WBFD_N = "n";

    public static final String ERROR_HOST_WBFD_O = "o";

    /**
     * Constante que define el fte disposition delete (elimina el archivo de la
     * ruta)
     */
    public static final String FTE_DISPOSITION_DELETE = "delete";
    /**
     * NIVELES DE NOTIFICACIONES
     */
    public static final String ACTION_LEVEL_ONE = "1";

    public static final String ACTION_LEVEL_TWO = "2";

    public static final String ACTION_LEVEL_THREE = "3";

    /**
     * Pagos de Servicios
     */
    public static final String PAGOS_SERVICIOS_DEUDA_TOTAL = "deuda.total";
    public static final String PAGOS_SERVICIOS_IMPORTE_VENCIDO = "importe.vencido";
    public static final String PAGOS_SERVICIOS_OTRO_MONTO = "otro.monto";
    public static final String PAGOS_SERVICIOS_MONTO_FACTURADO = "monto.factura";
    public static final String PAGOS_SERVICIOS_MONTO_VENCIDO = "monto.vencido";
    public static final String PAGOS_SERVICIOS_SALDO_TOTAL = "saldo.total";
    public static final String PAGOS_SERVICIOS_SALDO_VENCIDO = "saldo.vencido";

    public static final String FACTOR_AUTH_SMS = "sms";

    public static final String FACTOR_AUTH_EMAIL = "email";

    public static final String FACTOR_AUTH_EMAIL_AND_SMS = "smsAndEmail";

    /**
     * Catalogos de Etiquetas de Parametros de Transacciones Financieras.
     *
     */
    public static final String ETIQUETA_CUENTA_ORIGEN = "etiqueta.cuenta.origen";
    public static final String ETIQUETA_TDC_ORIGEN = "etiqueta.tdc.origen";
    public static final String ETIQUETA_TIPO_IDENTIFICACION = "etiqueta.tipo.identificacion";
    public static final String ETIQUETA_IDENTIFICACION = "etiqueta.identificacion";
    public static final String ETIQUETA_NOMBRE = "etiqueta.nombre";
    public static final String ETIQUETA_CUENTA_DESTINO = "etiqueta.cuenta.destino";
    public static final String ETIQUETA_NUMERO_CONTRATO = "etiqueta.numero.contrato";
    public static final String ETIQUETA_EMPRESA = "etiqueta.empresa";
    public static final String ETIQUETA_TIPO_IDENTIFICACION_BENEFICIARIO = "etiqueta.tipo.identificacion.beneficiario";
    public static final String ETIQUETA_IDENTIFICACION_BENEFICIARIO = "etiqueta.identificacion.beneficiario";
    public static final String ETIQUETA_NOMBRE_BENEFICIARIO = "etiqueta.nombre.beneficiario";
    public static final String ETIQUETA_CONCEPTO = "etiqueta.concepto";
    public static final String ETIQUETA_MONTO = "etiqueta.monto";
    public static final String ETIQUETA_CUENTA_DESTINO_PROPIA = "etiqueta.cuenta.destino.propia";
    public static final String ETIQUETA_PAGO_PUNTOS = "etiqueta.pago.puntos";
    public static final String ETIQUETA_TIPO_PAGO = "etiqueta.tipo.pago";
    public static final String ETIQUETA_NUMERO_CONFIRMACION = "etiqueta.numero.confirmacion";
    public static final String ETIQUETA_CLASE_PAGO = "etiqueta.clase.pago";
    public static final String ETIQUETA_TDC_DESTINO = "etiqueta.tdc.destino";
    public static final String ETIQUETA_PAGO_CREDITO = "etiqueta.pago.tercero";
    public static final String ETIQUETA_PAGO_TERCERO = "etiqueta.pago.tercero";
    public static final String ETIQUETA_CODIGO_BANCO_ORIGEN = "etiqueta.codigo.banco.origen";
    public static final String ETIQUETA_CODIGO_BANCO_DESTINO = "etiqueta.codigo.banco.destino";
    public static final String ETIQUETA_FECHA_PROGRAMADA = "etiqueta.fecha.programada";
    public static final String ETIQUETA_NUMERO_LOTE = "etiqueta.numero.lote";
    public static final String ETIQUETA_TIPO_PROVEEDOR = "etiqueta.tipo.proveedor";
    public static final String ETIQUETA_TIPO_NOMINA = "etiqueta.tipo.nomina";
    public static final String ETIQUETA_CODIGO_CLASE_PAGO_TDC = "etiqueta.codigo.clase.pago.tdc";
    public static final String ETIQUETA_CODIGO_BANCO_BENEFICIARIO_NOMBRE = "etiqueta.codigo.banco.beneficiario.nombre";
    public static final String ETIQUETA_NUMERO_TELEFONO = "etiqueta.numero.telefono";
    public static final String ETIQUETA_CODIGO_CLASE_PAGO_CREDITO = "etiqueta.codigo.clase.pago.credito";
    public static final String ETIQUETA_CODIGO_TIPO_CREDITO = "etiqueta.codigo.tipo.credito";
    public static final String ETIQUETA_NOMBRE_ARCHIVO = "etiqueta.nombre.archivo";
    public static final String ETIQUETA_RUTA_ARCHIVO = "etiqueta.ruta.archivo";
    public static final String ETIQUETA_CODIGO_OTRO_BANCO = "etiqueta.codigo.otro.banco";
    public static final String ETIQUETA_CUENTA_ANTERIOR_ASOCIADA = "etiqueta.cuenta.anterior.asociada";
    public static final String ETIQUETA_MONTO_COMISION = "etiqueta.monto.comision";
    public static final String ETIQUETA_MONTO_DOLARES_SOLICITADOS = "etiqueta.monto.dolares.solicitado";
    public static final String ETIQUETA_NUMERO_CONTROL = "etiqueta.numero.control";
    public static final String ETIQUETA_TASA_REFERENCIAL = "etiqueta.tasa.referencial";
    public static final String ETIQUETA_MOTIVO_SOLICITUD = "etiqueta.motivo.solicitud";
    public static final String ETIQUETA_CUENTA_CLIENTE = "etiqueta.cuenta.cliente";
    public static final String ETIQUETA_CUENTA_RECAUDADORA = "etiqueta.cuenta.recaudadora";
    public static final String ETIQUETA_FECHA_INICIAL = "etiqueta.fecha.inicial";
    public static final String ETIQUETA_FECHA_FINAL = "etiqueta.fecha.final";
    public static final String ETIQUETA_NUMERO_FACTURA = "etiqueta.numero.factura";
    public static final String ETIQUETA_FECHA_FACTURA = "etiqueta.fecha.factura";
    public static final String ETIQUETA_LOTE_SIP = "etiqueta.lote.sip";
    public static final String ETIQUETA_CANTIDAD_BENEFICIARIOS = "etiqueta.cantidad.registros"; //
    public static final String ETIQUETA_TIPO_IDENTIFICACION_PAGADOR = "etiqueta.tipo.identificacion.pagador";
    public static final String ETIQUETA_IDENTIFICACION_PAGADOR = "etiqueta.identificacion.pagador";
    public static final String ETIQUETA_NOMBRE_PAGADOR = "etiqueta.nombre.pagador";
    public static final String ETIQUETA_FRECUENCIA_PAGO = "etiqueta.frecuencia.pago";
    public static final String ETIQUETA_DIA_PAGO_UNO = "etiqueta.dia.pago.uno";
    public static final String ETIQUETA_REINTENTOS_COBRO_TRANSACCION = "etiqueta.reintentos.cobro.transaccion";
    public static final String ETIQUETA_DIA_PAGO_DOS = "etiqueta.dia.pago.dos";
    public static final String ETIQUETA_NOMBRE_BANCO_PAGADOR = "etiqueta.nombre.banco.pagador";
    public static final String ETIQUETA_MONTO_MAXIMO = "etiqueta.monto.maximo";
    public static final String ETIQUETA_MOTIVO_TRANSACCION = "etiqueta.motivo.transaccion";
    public static final String ETIQUETA_NUMERO_PLANILLA = "etiqueta.numero.planilla";

    /**
     * Catalogos de Etiquetas de Parametros de Solicitudes.
     *
     */
    public static final String ETIQUETA_MOTIVO_REGISTRADO = "etiqueta.motivo.registrado";
    public static final String ETIQUETA_TDD_NUMERO = "etiqueta.tdd.numero";
    public static final String ETIQUETA_CHEQUE = "etiqueta.cheque";
    public static final String ETIQUETA_PRODUCTO_TDD = "etiqueta.producto.tdd";
    public static final String ETIQUETA_PRODUCTO_TDC = "etiqueta.producto.tdc";
    public static final String ETIQUETA_PRODUCTO_CHEQUE = "etiqueta.producto.cheque";
    public static final String ETIQUETA_PRODUCTO_CHEQUERA = "etiqueta.producto.chequera";
    public static final String ETIQUETA_PRODUCTO_CTA_AHORRO = "etiqueta.producto.cuenta.ahorro";
    public static final String ETIQUETA_PRODUCTO_CTA_CORRIENTE = "etiqueta.producto.cuenta.corriente";
    public static final String ETIQUETA_PRODUCTO_DOMICILIACION = "etiqueta.producto.domiciliacion";
    public static final String ETIQUETA_CHEQUE_INICIAL = "etiqueta.cheque.inicial";
    public static final String ETIQUETA_CHEQUE_FINAL = "etiqueta.cheque.final";
    public static final String ETIQUETA_CHEQUE_CUENTA = "etiqueta.cheque.cuenta";
    public static final String ETIQUETA_SOFTTOKEN_RESULTADO_DESCRIPCION = "etiqueta.soft.token.resultado.descripcion";
    public static final String ETIQUETA_SOFTTOKEN_RESULTADO_CODIGO = "etiqueta.soft.token.resultado.codigo";
    public static final String ETIQUETA_SOFTOKEN = "etiqueta.soft.token";
    public static final String ETIQUETA_FECHA_OPERACION = "etiqueta.fecha.operacion";
    public static final String ETIQUETA_SOLICITUD_OBSERVACION = "etiqueta.solicitud.observacion";
    public static final String ETIQUETA_NUMERO_RECLAMO = "etiqueta.numero.reclamo";
    public static final String ETIQUETA_CANAL_ELECTRONICO = "etiqueta.canal.electronico";
    public static final String ETIQUETA_MONTO_ANTERIOR = "etiqueta.monto.anterior";
    public static final String ETIQUETA_MONTO_NUEVO = "etiqueta.monto.nuevo";
    public static final String ETIQUETA_REINTENTOS_COBRO_ANTERIOR = "etiqueta.reintentos.cobro.anterior";
    public static final String ETIQUETA_PRIMER_DIA_APLICAR_ANTERIOR = "etiqueta.primer.dia.aplicacion.anterior";
    public static final String ETIQUETA_FRECUENCIA_ANTERIOR = "etiqueta.frecuencia.anterior";
    public static final String ETIQUETA_FECHA_FACTURA_ANTERIOR = "etiqueta.fecha.factura.anterior";
    public static final String ETIQUETA_NUMERO_FACTURA_ANTERIOR = "etiqueta.numero.factura.anterior";
    public static final String ETIQUETA_REINTENTOS_COBRO = "etiqueta.reintentos.cobro";
    public static final String ETIQUETA_PRIMER_DIA_APLICAR = "etiqueta.primer.dia.aplicacion";
    public static final String ETIQUETA_FRECUENCIA = "etiqueta.frecuencia";
    public static final String ETIQUETA_FECHA_FACTURA_SOLICITUD = "etiqueta.fecha.factura.solicitud";
    public static final String ETIQUETA_NUMERO_FACTURA_SOLICITUD = "etiqueta.numero.factura.solicitud";
    public static final String ETIQUETA_NUMERO_CONTRATO_SOLICITUD = "etiqueta.numero.contrato.solicitud";
    public static final String ETIQUETA_NUMERO_TLF_ANTERIOR = "etiqueta.telefono.anterior";
    public static final String ETIQUETA_NUMERO_TLF_NUEVO = "etiqueta.telefono.nuevo";
    public static final String ETIQUETA_CORREO_ANTERIOR = "etiqueta.correo.anterior";
    public static final String ETIQUETA_CORREO_NUEVO = "etiqueta.correo.nuevo";

    /**
     * Catalogos de Etiquetas de Parametros de Operaciones Externas.
     *
     */
    public static final String ETIQUETA_INICIO_SESSION_FALLIDO = "etiqueta.inicio.sesion.fallido";
    public static final String ETIQUETA_INICIO_SESSION_EXITOSO = "etiqueta.inicio.sesion.exitoso";
    public static final String ETIQUETA_REGENERACION_CONTRASEÑA = "etiqueta.regeneracion.contraseña";
    public static final String ETIQUETA_DESBLOQUEO_USUARIO = "etiqueta.desbloqueo.usuario";
    public static final String ETIQUETA_VALIDACION_ZONA = "etiqueta.validacion.zona";
    public static final String ETIQUETA_AFILIACION_EXITOSA = "etiqueta.afiliacion.exitosa";
    public static final String ETIQUETA_AFILIACION_FALLIDA = "etiqueta.afiliacion.fallida";
    public static final String ETIQUETA_REFERENCIA_BANCARIA = "etiqueta.clase.referencia";
    public static final String ETIQUETA_REFERENCIA_DIRIGIDO = "etiqueta.referencia.dirigido";
    public static final String ETIQUETA_CLASE_REFERENCIA = "etiqueta.clase.referencia";
    public static final String ETIQUETA_TIPO_PRODUCTO = "etiqueta.tipo.producto";
    public static final String ETIQUETA_REFERENCIA_TDC = "etiqueta.referencia.tdc";
    public static final String ETIQUETA_ESTADO_CUENTA_ANIO = "etiqueta.estado.cuenta.anio";
    public static final String ETIQUETA_ESTADO_CUENTA_MES = "etiqueta.estado.cuenta.mes";
    public static final String ETIQUETA_CODIGO_VALIDACION = "etiqueta.codigo.validacion";
    public static final String ETIQUETA_RECUPERACION_USUARIO = "etiqueta.recuperacion.usuario";
    public static final String ETIQUETA_REAFILIACION_USUARIO = "etiqueta.reafiliacion.usuario";
    public static final String ETIQUETA_PARAMETRO_MIGRACION = "cantidad.usuarios.migracion";

    /**
     * TIPO DE PRODUCTO
     *
     */
    public static final String REFERENCIA_PRODUCTO_CTA = "referencia.producto.cta";
    public static final String REFERENCIA_PRODUCTO_TDC = "referencia.producto.tdc";

    /**
     * Categoria de Cuentas y TDC.
     *
     */
    public static final String TIPO_CCTE = "CCTE";      //  Cuenta Corriente.
    public static final String TIPO_CCTI = "CTEI";      //  Cuenta Corriente.
    public static final String TIPO_CAHO = "CAHO";      //  Cuenta de Ahorro.
    public static final String TIPO_TCRE = "TDC";       //  Tárjeta de Crédito.

    /**
     * Constantes donde se determinan los nombres de los objetos y nombres de
     * las tablas en Base de Datos.
     *
     */
    public static final String objetoDirectorioGlobal = "DirectorioGlobal";       //  Objeto DirectorioGlobal.
    public static final String tablaDirectorioGlobal = "DIRECTORIO_GLOBAL";       //  Tabla DIRECTORIO_GLOBAL.
    public static final String objetoProducto = "Productos";       //  Objeto DirectorioGlobal.
    public static final String tablaProductos = "PRODUCTOS";       //  Tabla DIRECTORIO_GLOBAL.

    /**
     * Constantes para las acciones en el registro en la tabla DETBITACORANBL.
     *
     */
    public static final String accionCreacion = "creación";
    public static final String accionEditar = "editar";
    public static final String accionEliminar = "eliminar";

    public static final String PRODUCT_CODE_DEFAULT = "1111111111";
    public static final String PRODUCT_TYPE_CONSOLIDATED = "CC";
    public static final String PRODUCT_TYPE_CUENTAS = "CT";
    public static final String PRODUCT_TYPE_TDC = "TA";

    /**
     * Constantes para error equipo uso frecuente
     */
    public static final String ERROR_REQUERIDO_EQUIPO_FRECUENTE = "equipo.frecuente.campo.requerido";
    public static final String ERROR_EXISTE_EQUIPO_FRECUENTE = "equipo.frecuente.ya.existe";

    /**
     * Constantes para las acciones en el registro en la tabla DETBITACORANBL.
     *
     */
    public static final String SIP_ESTADO_VALIDACION = "1";
    public static final String SIP_ESTADO_VALIDADO = "3";
    public static final String SIP_ESTADO_VALIDADO_ERRORES = "2";
    public static final String SIP_ESTADO_ENPROCESO = "4";
    public static final String SIP_ESTADO_FALLIDO = "5";
    public static final String SIP_ESTADO_SINRESULTADO = "6";
    public static final String SIP_ESTADO_PROCESADO = "7";
    public static final String SIP_ESTADO_CANCELADO = "8";
    public static final String SIP_ESTADO_FLUJO = "9";

    /**
     * NIVELES DE NOTIFICACIONES
     */
    /**
     * Devuelva un objeto con la sesion, de manejo de JSF
     *
     * @return Objeto de sesion
     */
    public static HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Devuelva un objeto con la sesion, de manejo de JSF
     *
     * @return Objeto de sesion
     */
    public static ServletContext getContext() {
        return (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    /**
     * Devuelva un objeto con el request, de manejo de JSF
     *
     * @return Objeto request
     */
    public static HttpServletRequest getRequest() {
        try {
            return (HttpServletRequest) FacesContext.
                    getCurrentInstance().
                    getExternalContext().getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Devuelva un objeto con el response, de manejo de JSF
     *
     * @return Objeto response
     */
    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.
                getCurrentInstance().
                getExternalContext().getResponse();
    }

    /**
     * Redirecciona con un header http
     *
     * @param facesOutcome La página a la que se quiere direccionar
     */
    public static void redirecciona(String facesOutcome) {
        try {
            getResponse().sendRedirect(getRequest().getContextPath() + rutaFaces + facesOutcome + ".xhtml");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        FacesContext.getCurrentInstance().responseComplete();
    }

    /**
     * Redirecciona con un header http
     *
     * @param facesOutcome La página a la que se quiere direccionar
     */
    public static void redireccionaExterna(String facesOutcome) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(getRequest().getContextPath() + rutaFaces + facesOutcome + ".xhtml");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public PerfilesNbl construirPerfilAsociado(PerfilesNbl perfilPadre, List<IBDetallePerfilAsociado> opcionesPerfil, String tipoAprobadorNomina, String tipoAprobadorProveedores, String referenciaPlantilla, String identificacionUnicaPadre) {

        PerfilesNbl perfilAsociadoRetorno = new PerfilesNbl();

        //Verificamos que bean contenga data
        if (opcionesPerfil != null && !opcionesPerfil.isEmpty()) {

            List<Productos> productosAsociados = new ArrayList<>();

            Map<String, List<OperacionesNbl>> mapOperacionesPorProducto = new HashMap();
            Map<String, BigDecimal> mapMontoMaximoPorProducto = new HashMap();
            List<OperacionesNbl> operacionesDelAsociado = new ArrayList<>();

            //Cargamos los mapas con la data de los beans
            for (IBDetallePerfilAsociado opcionPerfil : opcionesPerfil) {
                operacionesDelAsociado.addAll(opcionPerfil.getListaOperacionesAsociadas());
                productosAsociados.add(opcionPerfil.getProductoCliente());
                mapOperacionesPorProducto.put(opcionPerfil.getProductoCliente().getNumero(), opcionPerfil.getListaOperacionesAsociadas());
                mapMontoMaximoPorProducto.put(opcionPerfil.getProductoCliente().getNumero(), opcionPerfil.getLimiteMonto());
            }

            //Eliminamos duplicados 
            if (!operacionesDelAsociado.isEmpty()) {

                Set<OperacionesNbl> setOperacionesNbl = new HashSet<>();

                setOperacionesNbl.addAll(operacionesDelAsociado);
                operacionesDelAsociado.clear();
                operacionesDelAsociado.addAll(setOperacionesNbl);

            }
            TipoPerfilesNbl tipoPerfilRegular = tipoPerfilesNblFacade.ByCodigo(TipoPerfilesNbl.CODIGO_PERFIL_REGULAR);
            //Persistencia del Perfil Plantilla
            PerfilesNbl perfilPlantilla = new PerfilesNbl();
            perfilPlantilla.setTipoPerfilesNblId(tipoPerfilRegular);
            perfilPlantilla.setCodigoRol(null);
            perfilPlantilla.setNombre("PlantillaMigracion");
            perfilPlantilla.setDescripcion("Perfil Plantilla de Migracion");
            perfilPlantilla.setCanalId(perfilPadre.getCanalId());
            perfilPlantilla.setIdiomasId(perfilPadre.getIdiomasId());
            perfilPlantilla.setPerfilBaseId(perfilPadre);
            perfilPlantilla.setClientesId(perfilPadre.getClientesId());

            if (tipoAprobadorNomina != null) {
                OperacionesNbl operacionNomina = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_NOMINA_ACCESO_MODULO);
                OperacionesNbl operacionNominaConsulta = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_NOMINA_CONSULTA);
                OperacionesNbl operacionNominaFlujoAprobacion = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_NOMINA_APROBACION);
                operacionesDelAsociado.add(operacionNomina);
                operacionesDelAsociado.add(operacionNominaConsulta);
                operacionesDelAsociado.add(operacionNominaFlujoAprobacion);
            }
            if (tipoAprobadorProveedores != null) {
                OperacionesNbl operacionProveedores = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_PROVEEDORES_ACCESO_MODULO);
                OperacionesNbl operacionProveedoresConsulta = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_PROVEEDORES_CONSULTA);
                OperacionesNbl operacionProveedoresIndividual = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_PROVEEDORES_INDIVIDUAL);
                OperacionesNbl operacionProveedoresMasivos = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_PROVEEDORES_MASIVOS);
                OperacionesNbl operacionProveedoresAprobacion = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_PROVEEDORES_APROBACION);
                operacionesDelAsociado.add(operacionProveedores);
                operacionesDelAsociado.add(operacionProveedoresConsulta);
                operacionesDelAsociado.add(operacionProveedoresIndividual);
                operacionesDelAsociado.add(operacionProveedoresMasivos);
                operacionesDelAsociado.add(operacionProveedoresAprobacion);

            }

            if (tipoAprobadorNomina != null || tipoAprobadorProveedores != null) {

                OperacionesNbl operacionFlujoAprobar = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_FLUJO_APROBACION);
                operacionesDelAsociado.add(operacionFlujoAprobar);

            }

            Set<OperacionesNbl> setOperacionesNblAsociados = new HashSet<>();
            setOperacionesNblAsociados.addAll(operacionesDelAsociado);
            operacionesDelAsociado.clear();
            operacionesDelAsociado.addAll(setOperacionesNblAsociados);

            perfilPlantilla.setOperacionesNblCollection(operacionesDelAsociado);
            perfilPlantilla.setEstilosId(perfilPadre.getEstilosId());
            perfilPlantilla.setAmbientesId(perfilPadre.getAmbientesId());
            perfilPlantilla.setEstado(perfilPadre.getEstado());

            try {

                perfilNblFacade.create(perfilPlantilla);

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.log(Level.SEVERE, "Error al crear nuevo perfil plantilla migracion", ex);

            }

            if (tipoAprobadorNomina != null) {

                //Insertamos los roles de nomina y proveedores en el perfil plantilla
                PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesNominaPadrePK = new PerfilesnblHasOperacionesnblPK();
                OperacionesNbl operacionNominaModulo = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_NOMINA_ACCESO_MODULO);
                perfilesNblHasOperacionesNominaPadrePK.setOperacionesNblId(operacionNominaModulo.getId());
                perfilesNblHasOperacionesNominaPadrePK.setPerfilesNblId(perfilPlantilla.getId());
                PerfilesnblHasOperacionesnbl perfilesHasOperacionesNominaPadre = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesNominaPadrePK);
                perfilesHasOperacionesNominaPadre.setCodrol(tipoAprobadorNomina);

                try {
                    this.perfilesHasOperacionesFacade.edit(perfilesHasOperacionesNominaPadre);

                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Error al editar perfiles has operaciones", ex);
                }

            }

            if (tipoAprobadorProveedores != null) {

                PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesProveedoresPadrePK = new PerfilesnblHasOperacionesnblPK();
                OperacionesNbl operacionProveedoresModulo = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_PROVEEDORES_ACCESO_MODULO);
                perfilesNblHasOperacionesProveedoresPadrePK.setOperacionesNblId(operacionProveedoresModulo.getId());
                perfilesNblHasOperacionesProveedoresPadrePK.setPerfilesNblId(perfilPlantilla.getId());
                PerfilesnblHasOperacionesnbl perfilesHasOperacionesProveedoresPadre = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesProveedoresPadrePK);
                perfilesHasOperacionesProveedoresPadre.setCodrol(tipoAprobadorProveedores);

                try {
                    this.perfilesHasOperacionesFacade.edit(perfilesHasOperacionesProveedoresPadre);

                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Error al editar perfiles has operaciones", ex);
                }
            }

            //Creamos los limites por cada producto y por cada operacion
            if (!productosAsociados.isEmpty()) {

                for (Productos producto : productosAsociados) {

                    if (mapOperacionesPorProducto.containsKey(producto.getNumero())) {
                        List<OperacionesNbl> tempListOperaciones = mapOperacionesPorProducto.get(producto.getNumero());
                        for (OperacionesNbl operacionDelProducto : tempListOperaciones) {
                            Detlimites limitePlantilla = new Detlimites();
                            limitePlantilla.setFkIdoperacion(operacionDelProducto);
                            if (mapMontoMaximoPorProducto.containsKey(producto.getNumero())) {
                                limitePlantilla.setValmontodiario(mapMontoMaximoPorProducto.get(producto.getNumero()));
                            } else {

                                limitePlantilla.setValmontodiario(null);
                            }

                            limitePlantilla.setFkIdperfil(perfilPlantilla);

                            limitePlantilla.setFkIdproducto(producto);

                            try {

                                detLimitesFacade.create(limitePlantilla);

                            } catch (Exception ex) {

                                logger.log(Level.SEVERE, "Error al crear nuevo limite de producto para el perfil plantilla de migracion", ex);

                            }

                        }

                    }

                }

            }

            String identificacionUnicaAsociadoPerfilMigrado = identificacionUnicaPadre + "PlantillaMigracion" + referenciaPlantilla;
            //Persistencia del perfil del asociado
            PerfilesNbl perfilAsociado = new PerfilesNbl();
            perfilAsociado.setTipoPerfilesNblId(perfilPlantilla.getTipoPerfilesNblId());
            perfilAsociado.setCodigoRol(perfilPlantilla.getCodigoRol());
            perfilAsociado.setNombre(identificacionUnicaAsociadoPerfilMigrado);
            perfilAsociado.setDescripcion("Perfil Asociado de Migracion");
            perfilAsociado.setCanalId(perfilPlantilla.getCanalId());
            perfilAsociado.setIdiomasId(perfilPlantilla.getIdiomasId());
            perfilAsociado.setPerfilBaseId(perfilPlantilla);
            perfilAsociado.setClientesId(perfilPlantilla.getClientesId());
            perfilAsociado.setOperacionesNblCollection(perfilPlantilla.getOperacionesNblCollection());
            perfilAsociado.setEstilosId(perfilPlantilla.getEstilosId());
            perfilAsociado.setAmbientesId(perfilPlantilla.getAmbientesId());
            perfilAsociado.setEstado(perfilPlantilla.getEstado());

            try {
                perfilNblFacade.create(perfilAsociado);
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Error al crear nuevo perfil asociado migracion", ex);
            }

            if (tipoAprobadorNomina != null) {

                PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesNominaPlantillaPK = new PerfilesnblHasOperacionesnblPK();
                OperacionesNbl operacionNomina = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_NOMINA_ACCESO_MODULO);
                perfilesNblHasOperacionesNominaPlantillaPK.setOperacionesNblId(operacionNomina.getId());
                perfilesNblHasOperacionesNominaPlantillaPK.setPerfilesNblId(perfilPlantilla.getId());
                PerfilesnblHasOperacionesnbl perfilesHasOperacionesNominaPlantilla = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesNominaPlantillaPK);
                String rolNominaPlantilla = perfilesHasOperacionesNominaPlantilla.getCodrol();

                //Insertamos el rol de nomina en el perfil de asociado
                PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesNominaAsociadoPK = new PerfilesnblHasOperacionesnblPK();
                OperacionesNbl operacionNominaModulo = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_NOMINA_ACCESO_MODULO);
                perfilesNblHasOperacionesNominaAsociadoPK.setOperacionesNblId(operacionNominaModulo.getId());
                perfilesNblHasOperacionesNominaAsociadoPK.setPerfilesNblId(perfilAsociado.getId());
                PerfilesnblHasOperacionesnbl perfilesHasOperacionesNominaAsociado = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesNominaAsociadoPK);
                perfilesHasOperacionesNominaAsociado.setCodrol(rolNominaPlantilla);

                try {
                    this.perfilesHasOperacionesFacade.edit(perfilesHasOperacionesNominaAsociado);
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Error al editar perfiles has operaciones", ex);
                }
            }

            if (tipoAprobadorProveedores != null) {

                PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesProveedoresPlantillaPK = new PerfilesnblHasOperacionesnblPK();
                OperacionesNbl operacionProveedores = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_PROVEEDORES_ACCESO_MODULO);
                perfilesNblHasOperacionesProveedoresPlantillaPK.setOperacionesNblId(operacionProveedores.getId());
                perfilesNblHasOperacionesProveedoresPlantillaPK.setPerfilesNblId(perfilPlantilla.getId());
                PerfilesnblHasOperacionesnbl perfilesHasOperacionesProveedoresPlantilla = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesProveedoresPlantillaPK);
                String rolProveedoresPlantilla = perfilesHasOperacionesProveedoresPlantilla.getCodrol();

                //Insertamos el rol de proveedores en el perfil de asociado
                PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesProveedoresAsociadoPK = new PerfilesnblHasOperacionesnblPK();
                OperacionesNbl operacionProveedoresModulo = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_PROVEEDORES_ACCESO_MODULO);
                perfilesNblHasOperacionesProveedoresAsociadoPK.setOperacionesNblId(operacionProveedoresModulo.getId());
                perfilesNblHasOperacionesProveedoresAsociadoPK.setPerfilesNblId(perfilAsociado.getId());
                PerfilesnblHasOperacionesnbl perfilesHasOperacionesProveedoresAsociado = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesProveedoresAsociadoPK);
                perfilesHasOperacionesProveedoresAsociado.setCodrol(rolProveedoresPlantilla);

                try {
                    this.perfilesHasOperacionesFacade.edit(perfilesHasOperacionesProveedoresAsociado);

                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Error al editar perfiles has operaciones", ex);
                }

            }

            List<Detlimites> limitesPerfilPlantilla = detLimitesFacade.porPerfil(perfilPlantilla);

            if (limitesPerfilPlantilla != null && !limitesPerfilPlantilla.isEmpty()) {

                for (Detlimites limitePlantilla : limitesPerfilPlantilla) {

                    Detlimites limiteAsociado = new Detlimites();
                    limiteAsociado.setFkIdoperacion(limitePlantilla.getFkIdoperacion());
                    limiteAsociado.setValmontodiario(limitePlantilla.getValmontodiario());
                    limiteAsociado.setFkIdperfil(perfilAsociado);
                    limiteAsociado.setFkIdproducto(limitePlantilla.getFkIdproducto());

                    try {

                        detLimitesFacade.create(limiteAsociado);

                    } catch (Exception ex) {

                        logger.log(Level.SEVERE, "Error al crear nuevo limite de producto para el perfil plantilla de migracion", ex);

                    }

                }
            }

            perfilAsociadoRetorno = perfilAsociado;

        }

        return perfilAsociadoRetorno;
    }

    /**
     * Método utilizado para seleccionar perfil según parametros configurados
     *
     * @param cliente
     * @return
     */
    public PerfilesNbl seleccionarPerfilBase(BeanCliente cliente) throws Exception {

        PerfilesNbl perfil = new PerfilesNbl();
        String tipoPersona = cliente.getTipoIdentificacion();
        String codigoBancario = cliente.getBankCode();
        Detreglaparametros reglasParametros = new Detreglaparametros();
        List<Detreglaparametros> listaDeReglaParametros = new ArrayList<>();

        try {

            if (tipoPersona != null && codigoBancario != null) {

                List<Detreglaparametros> detReglasParametrosAmbiente = new ArrayList<>();

                detReglasParametrosAmbiente = reglaParametrosFacade.porValParametroIdParametro(tipoPersona, Parametros.PARAMETRO_TIPO_PERSONA);

                if (!detReglasParametrosAmbiente.isEmpty()) {

                    Map<Integer, PerfilesNbl> limitePerfilBanca = new HashMap();

                    int codigoIntBanca = Integer.parseInt(codigoBancario);

                    for (Detreglaparametros reglaPorAmbiente : detReglasParametrosAmbiente) {

                        Detreglaparametros reglaPorBanca = new Detreglaparametros();

                        reglaPorBanca = reglaParametrosFacade.porReglaParametro(reglaPorAmbiente.getFkIdreglaperfil(), Parametros.PARAMETRO_CODIGO_BANCA);

                        listaDeReglaParametros.add(reglaPorBanca);

                    }

                    if (!listaDeReglaParametros.isEmpty()) {

                        for (Detreglaparametros reglaParametro : listaDeReglaParametros) {

                            limitePerfilBanca.put(Integer.parseInt(reglaParametro.getValparametro()), reglaParametro.getFkIdreglaperfil().getFkIdperfil());

                        }

                    }

                    int[] limites = new int[detReglasParametrosAmbiente.size()];

                    if (listaDeReglaParametros != null && !listaDeReglaParametros.isEmpty()) {

                        int i = 0;

                        for (Detreglaparametros regla : listaDeReglaParametros) {

                            limites[i] = Integer.parseInt(regla.getValparametro());
                            i++;
                        }

                        Arrays.sort(limites);

                    }

                    int intentos = 0;

                    if (limites != null && limites.length != 0) {

                        PerfilesNbl perfilLimite = new PerfilesNbl();

                        for (int limite : limites) {

                            if (codigoIntBanca < limite) {

                                perfil = limitePerfilBanca.get(limite);
                                break;

                            }

                            intentos++;

                            if (limites.length == intentos) {

                                perfil = limitePerfilBanca.get(limites[0]);

                            }

                        }

                    }

                }

            }

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error al asignar perfil base ", ex);
        }

        return perfil;
    }

    /**
     *
     * @param perfilBase el perfil de banca que le corresponde
     * @param asociacion la asociacion creada en la invitacion
     * @param clientePrincipal el Cliente propietario de la cuenta
     * @return el Perfil creado de el usuario invitado
     */
    public PerfilesNbl crearPerfilUsuarioUnicoInvitado(PerfilesNbl perfilBase, Invitaciones asociacion, Clientes clientePrincipal) {

        String identificacionUsuarioPrincipal = clientePrincipal.getTipoIdentificacion() + clientePrincipal.getIdentificacion();
        String identificacionPlantilla = perfilBase.getNombre();
        String identificacionUsuarioInvitado = asociacion.getTipoIdentificacion() + asociacion.getIdentificacion();
        PerfilesNbl perfilBuscado = perfilNblFacade.porNombre(identificacionUsuarioPrincipal.concat(identificacionPlantilla).concat(identificacionUsuarioInvitado));
        if (perfilBuscado != null) {
            identificacionUsuarioInvitado = identificacionUsuarioInvitado.concat(String.valueOf(BodBaseBean.ESTATUS_USUARIO_REAFILIADO));
        }

        List<Parametros> parametrosOperacionesNoCliente = parametrosFacade.parametrosPorTipo("operaciones.nocliente");
        ArrayList<String> listaCodigosOperacionesNoCliente = new ArrayList<>();
        for (Parametros parametro : parametrosOperacionesNoCliente) {
            listaCodigosOperacionesNoCliente.add(parametro.getValorPorDefecto());
        }
        List<OperacionesNbl> listaOperacionesBasesNoCliente = new ArrayList<>();
        listaOperacionesBasesNoCliente = operacionesNblFacade.listarTiposOperacion(listaCodigosOperacionesNoCliente);
        PerfilesNbl perfilControlDuplicados = perfilNblFacade.porNombrePerfilBase(identificacionUsuarioPrincipal + identificacionPlantilla + identificacionUsuarioInvitado, asociacion.getPerfilId());
        PerfilesNbl perfilInvitadoRetorno = new PerfilesNbl();

        if (perfilControlDuplicados == null) {
            /*Persistencia del Perfil del Invitado*/
            PerfilesNbl perfilInvitado = new PerfilesNbl();
            perfilInvitado.setTipoPerfilesNblId(perfilBase.getTipoPerfilesNblId());
            perfilInvitado.setCodigoRol(perfilBase.getCodigoRol());
            perfilInvitado.setNombre(identificacionUsuarioPrincipal + identificacionPlantilla + identificacionUsuarioInvitado);
            perfilInvitado.setDescripcion("Perfil de Asociado Invitado");
            perfilInvitado.setCanalId(perfilBase.getCanalId());
            perfilInvitado.setIdiomasId(perfilBase.getIdiomasId());
            perfilInvitado.setPerfilBaseId(perfilBase);
            perfilInvitado.setClientesId(asociacion.getClienteId());
            perfilInvitado.setEstilosId(perfilBase.getEstilosId());
            perfilInvitado.setAmbientesId(perfilBase.getAmbientesId());
            perfilInvitado.setEstado(perfilBase.getEstado());

            List<OperacionesNbl> listaOperacionesTotalesInvitado = new ArrayList<>();
            listaOperacionesTotalesInvitado = (List) perfilBase.getOperacionesNblCollection();
            if (!listaOperacionesBasesNoCliente.isEmpty()) {
                listaOperacionesTotalesInvitado.addAll(listaOperacionesBasesNoCliente);
            }
            Set<OperacionesNbl> setOperacionesNbl = new HashSet<>();

            setOperacionesNbl.addAll(listaOperacionesTotalesInvitado);
            listaOperacionesTotalesInvitado.clear();
            listaOperacionesTotalesInvitado.addAll(setOperacionesNbl);
            perfilInvitado.setOperacionesNblCollection(listaOperacionesTotalesInvitado);

            try {
                perfilNblFacade.create(perfilInvitado);
                perfilInvitadoRetorno = perfilInvitado;
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Error al crear nuevo perfil del invitado", ex);
            }

            PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesNominaPadrePK = new PerfilesnblHasOperacionesnblPK();
            OperacionesNbl operacionNomina = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_NOMINA_ACCESO_MODULO);
            perfilesNblHasOperacionesNominaPadrePK.setOperacionesNblId(operacionNomina.getId());
            perfilesNblHasOperacionesNominaPadrePK.setPerfilesNblId(perfilBase.getId());
            PerfilesnblHasOperacionesnbl perfilesHasOperacionesNominaPadre = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesNominaPadrePK);

            PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesNominaNoClientePK = new PerfilesnblHasOperacionesnblPK();
            perfilesNblHasOperacionesNominaNoClientePK.setOperacionesNblId(operacionNomina.getId());
            perfilesNblHasOperacionesNominaNoClientePK.setPerfilesNblId(perfilInvitado.getId());
            PerfilesnblHasOperacionesnbl perfilesHasOperacionesNominaNoCliente = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesNominaNoClientePK);

            if (perfilesHasOperacionesNominaPadre != null && perfilesHasOperacionesNominaNoCliente != null) {

                perfilesHasOperacionesNominaNoCliente.setCodrol(perfilesHasOperacionesNominaPadre.getCodrol());

                try {

                    this.perfilesHasOperacionesFacade.edit(perfilesHasOperacionesNominaNoCliente);

                } catch (Exception ex) {

                    logger.log(Level.SEVERE, "Error al editar perfiles has operaciones", ex);

                }

            }

            PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesProveedoresPadrePK = new PerfilesnblHasOperacionesnblPK();
            OperacionesNbl operacionProveedores = operacionesNblFacade.porCodigo(OperacionesNbl.CODIGO_PAGO_PROVEEDORES_ACCESO_MODULO);
            perfilesNblHasOperacionesProveedoresPadrePK.setOperacionesNblId(operacionProveedores.getId());
            perfilesNblHasOperacionesProveedoresPadrePK.setPerfilesNblId(perfilBase.getId());
            PerfilesnblHasOperacionesnbl perfilesHasOperacionesProveedoresPadre = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesProveedoresPadrePK);

            PerfilesnblHasOperacionesnblPK perfilesNblHasOperacionesProveedoresNoClientePK = new PerfilesnblHasOperacionesnblPK();
            perfilesNblHasOperacionesProveedoresNoClientePK.setOperacionesNblId(operacionProveedores.getId());
            perfilesNblHasOperacionesProveedoresNoClientePK.setPerfilesNblId(perfilInvitado.getId());
            PerfilesnblHasOperacionesnbl perfilesHasOperacionesProveedoresNoCliente = perfilesHasOperacionesFacade.find(perfilesNblHasOperacionesProveedoresNoClientePK);

            if (perfilesHasOperacionesProveedoresPadre != null && perfilesHasOperacionesProveedoresNoCliente != null) {

                perfilesHasOperacionesProveedoresNoCliente.setCodrol(perfilesHasOperacionesProveedoresPadre.getCodrol());

                try {

                    this.perfilesHasOperacionesFacade.edit(perfilesHasOperacionesProveedoresNoCliente);

                } catch (Exception ex) {

                    logger.log(Level.SEVERE, "Error al crear nuevo perfil", ex);

                }

            }

            List<Detlimites> limitesPerfilPlantilla = detLimitesFacade.porPerfil(perfilBase);

            if (limitesPerfilPlantilla != null && !limitesPerfilPlantilla.isEmpty()) {

                for (Detlimites detlimite : limitesPerfilPlantilla) {

                    Detlimites limite = new Detlimites();
                    limite.setFkIdoperacion(detlimite.getFkIdoperacion());
                    limite.setValmontodiario(detlimite.getValmontodiario());
                    limite.setFkIdperfil(perfilInvitado);
                    limite.setFkIdproducto(detlimite.getFkIdproducto());

                    try {

                        detLimitesFacade.create(limite);

                    } catch (Exception ex) {

                        logger.log(Level.SEVERE, "Error al crear nuevos limites por producto para el asociado invitado", ex);

                    }

                }

            }

        } else {
            List<Detlimites> listaLimitesPerfil = new ArrayList<>();
            listaLimitesPerfil = detLimitesFacade.porPerfil(perfilControlDuplicados);
            if (listaLimitesPerfil == null || listaLimitesPerfil.isEmpty()) {
                List<Detlimites> limitesPerfilAsociadoInvitado = detLimitesFacade.porPerfil(perfilBase);
                if (limitesPerfilAsociadoInvitado != null && !limitesPerfilAsociadoInvitado.isEmpty()) {
                    for (Detlimites detlimite : limitesPerfilAsociadoInvitado) {
                        Detlimites limite = new Detlimites();
                        limite.setFkIdoperacion(detlimite.getFkIdoperacion());
                        limite.setValmontodiario(detlimite.getValmontodiario());
                        limite.setFkIdperfil(perfilControlDuplicados);
                        limite.setFkIdproducto(detlimite.getFkIdproducto());
                        try {
                            detLimitesFacade.create(limite);
                        } catch (Exception ex) {
                            logger.log(Level.SEVERE, "Error al crear nuevos limites por producto para el asociado invitado", ex);
                        }
                    }
                }
            }
            perfilInvitadoRetorno = perfilControlDuplicados;
        }
        return perfilInvitadoRetorno;
    }

    /**
     * Metodo utilizado para verificar si el usuario debe afiliarse o
     * reafiliarce
     *
     * @param identificacionCliente identificador de el cliente el cual posee el
     * perfil
     * @param identificacionPerfilBanca identificador de la banca a la que este
     * pertenece
     * @return
     */
    public boolean activarProcesoDeReafiliacion(String identificacionCliente, String identificacionPerfilBanca) {
        boolean response = false;
        //Todo usuario puede poseer una lista de perfiles de usuario unico inactivos
        List<PerfilesNbl> perfilUsuarioUnico = perfilNblFacade.listarPerfilesPorNombre(identificacionPerfilBanca + identificacionCliente);
        if (perfilUsuarioUnico != null && !perfilUsuarioUnico.isEmpty()) {
            response = true;
        }
        return response;
    }

    /**
     * Metodo utilizado para mantener el estatus de reafiliado unicamente en el
     * perfil actual de el usuario
     */
    private void actualizarEstatusDeReafiliacion(String identificacionCliente, String identificacionPerfilBanca, PerfilesNbl perfilActualUsuarioRafiliado) {
        List<PerfilesNbl> perfilUsuarioUnicoReafiliados = perfilNblFacade.listarPerfilesPorNombre(identificacionPerfilBanca + identificacionCliente + BodBaseBean.ESTATUS_USUARIO_REAFILIADO);
        if (perfilUsuarioUnicoReafiliados != null && !perfilUsuarioUnicoReafiliados.isEmpty()) {
            for (PerfilesNbl perfilUsuario : perfilUsuarioUnicoReafiliados) {
                perfilUsuario.setNombre(identificacionPerfilBanca + identificacionCliente);
                perfilNblFacade.edit(perfilUsuario);
            }
        }
        perfilActualUsuarioRafiliado.setNombre(identificacionPerfilBanca + identificacionCliente + BodBaseBean.ESTATUS_USUARIO_REAFILIADO);
        perfilNblFacade.edit(perfilActualUsuarioRafiliado);
    }

    /**
     * Metodo que realiza la creacion de un perfil para el usuario unico el
     * mismo es autentico y no puede tener copias
     *
     * @param perfilBase Perfil Base de Creacion del nuevo perfil
     * @param clienteParam Cliente a asociar al perfil
     * @return Nuevo Perfil de Usuario
     */
    public PerfilesNbl crearPerfilUsuarioUnico(PerfilesNbl perfilBase, Clientes clienteParam) {
        String identificacionCliente = clienteParam.getTipoIdentificacion() + clienteParam.getIdentificacion();
        String identificacionPerfilBanca = perfilBase.getNombre();
        PerfilesNbl perfilUsuarioRetorno = null;
        try {
            PerfilesNbl perfilNuevoUsuarioUnico = crearPerfilUsuario(perfilBase, clienteParam);

            if (activarProcesoDeReafiliacion(identificacionCliente, identificacionPerfilBanca)) {
                actualizarEstatusDeReafiliacion(identificacionCliente, identificacionPerfilBanca, perfilNuevoUsuarioUnico);
                perfilUsuarioRetorno = perfilNuevoUsuarioUnico;
            } else {
                perfilNuevoUsuarioUnico.setNombre(identificacionPerfilBanca + identificacionCliente);
                perfilNblFacade.edit(perfilNuevoUsuarioUnico);
                perfilUsuarioRetorno = perfilNuevoUsuarioUnico;
            }
        } catch (NullPointerException ex) {
            logger.log(Level.SEVERE, null, "ERROR CREANDO EL PERFIL DE USUARIO UNICO EN EL PROCESO DE AFILIACION" + ex);
        }
        return perfilUsuarioRetorno;
    }

    public PerfilesNbl crearPerfilUsuario(PerfilesNbl perfilesNbl, Clientes clienteParam) {
        String identificacionCliente = clienteParam.getIdentificacion();
        String tipoIdentificacionCliente = clienteParam.getTipoIdentificacion();
        PerfilesNbl perfilUsuarioRetorno = new PerfilesNbl();
        PerfilesNbl perfilUsuario = new PerfilesNbl();
        perfilUsuario.setNombre(perfilesNbl.getNombre());
        perfilUsuario.setDescripcion(perfilesNbl.getDescripcion());
        perfilUsuario.setAmbientesId(perfilesNbl.getAmbientesId());
        perfilUsuario.setEstilosId(perfilesNbl.getEstilosId());
        perfilUsuario.setEstado(perfilesNbl.getEstado());
        perfilUsuario.setCanalId(perfilesNbl.getCanalId());
        perfilUsuario.setIdiomasId(perfilesNbl.getIdiomasId());
        perfilUsuario.setPerfilBaseId(perfilesNbl);
        perfilUsuario.setClientesId(clienteParam);
        perfilUsuario.setOperacionesNblCollection(perfilesNbl.getOperacionesNblCollection());
        perfilUsuario.setPerfilesNblHasParametrosCollection(perfilesNbl.getPerfilesNblHasParametrosCollection());
        perfilUsuario.setTipoPerfilesNblId(perfilesNbl.getTipoPerfilesNblId());
        try {
            /**
             * Asignando la secuencia de perfil
             */
            if (perfilUsuario.getId() == null) {
                perfilUsuario.setId(perfilNblFacade.getSequence());
            }
            /**
             * Creacion de usuario unico
             */
            perfilNblFacade.create(perfilUsuario);
            perfilUsuarioRetorno = perfilUsuario;
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, "ERROR CREANDO EL PERFIL DE USUARIO UNICO EN EL PROCESO DE AFILIACION" + e);
        }
        return perfilUsuarioRetorno;
    }

    /**
     * Metodo que realiza la creacion de un perfil para el usuario unico el
     * mismo es autentico y no puede tener copias
     *
     * @param perfilBase Perfil Base de Creacion del nuevo perfil
     * @param clienteParam Cliente a asociar al perfil
     * @param tipoPerfil Tipo de perfil a asociar en el proceso de afiliacion
     * @return Nuevo Perfil de Usuario
     */
    public PerfilesNbl asignarPerfil(PerfilesNbl perfilBase, Invitaciones invitacion) {

        String identificacion = invitacion.getIdentificacion();
        String tipoIdentificacion = invitacion.getTipoIdentificacion();

        //Obtenemos el  cliente asociado 
        Clientes cliente = new Clientes();

        cliente = clientesFacade.porCedula(tipoIdentificacion, identificacion);

        if (cliente == null) {

            cliente = invitacion.getClienteId();

        }

        PerfilesNbl nuevoPerfil = null;

        try {

            nuevoPerfil = new PerfilesNbl();
            nuevoPerfil.setClientesId(cliente);
            nuevoPerfil.setAmbientesId(invitacion.getPerfilId().getAmbientesId());
            nuevoPerfil.setTipoPerfilesNblId(invitacion.getPerfilId().getTipoPerfilesNblId());
            nuevoPerfil.setPerfilBaseId(invitacion.getPerfilId());
            nuevoPerfil.setEstado(invitacion.getPerfilId().getEstado());
            nuevoPerfil.setEstilosId(invitacion.getPerfilId().getEstilosId());
            nuevoPerfil.setIdiomasId(invitacion.getPerfilId().getIdiomasId());
            nuevoPerfil.setNombre(invitacion.getPerfilId().getNombre());
            nuevoPerfil.setDescripcion(invitacion.getPerfilId().getDescripcion());
            nuevoPerfil.setCanalId(invitacion.getPerfilId().getCanalId());
            nuevoPerfil.setOperacionesNblCollection(invitacion.getPerfilId().getOperacionesNblCollection());
            nuevoPerfil.setPerfilesNblHasParametrosCollection(perfilBase.getPerfilesNblHasParametrosCollection());

            perfilNblFacade.create(nuevoPerfil);
            invitacion.setPerfilId(nuevoPerfil);
            invitacionesFacade.edit(invitacion);

            List<Detlimites> limitesPorPerfil = detLimitesFacade.porPerfil(perfilBase);

            if (limitesPorPerfil != null && !limitesPorPerfil.isEmpty()) {

                for (Detlimites limitePorPerfil : limitesPorPerfil) {

                    Detlimites limite = new Detlimites();
                    limite.setFkIdcanal(limitePorPerfil.getFkIdcanal());
                    limite.setValmontodiario(limitePorPerfil.getValmontodiario());
                    limite.setValtransdiario(limitePorPerfil.getValtransdiario());
                    limite.setFkIdambiente(limitePorPerfil.getFkIdambiente());
                    limite.setFkIdperfil(nuevoPerfil);
                    limite.setFkIdoperacion(limitePorPerfil.getFkIdoperacion());
                    limite.setFkIdproducto(limitePorPerfil.getFkIdproducto());
                    limite.setValmontomensual(limitePorPerfil.getValmontomensual());
                    limite.setValtransmensual(limitePorPerfil.getValtransmensual());
                    detLimitesFacade.create(limite);

                }

            }

        } catch (NullPointerException ex) {
            logger.log(Level.SEVERE, null, "ERROR ASIGNANDO EL PERFIL" + ex);
        }

        return nuevoPerfil;
    }

    /**
     * Método para obtener el tipo de documento dado un tipo de identificación
     *
     * @param tipoIdent
     * @return Tipo de documento de identificación
     */
    public String tipoDocumento(String tipoIdent) {
        /**
         * Tipos de Identificación
         */
        String extranjero = parametrosFacade.porCodigo("identificacion.extranjero");
        String formacion = parametrosFacade.porCodigo("identificacion.formacion");
        String gobierno = parametrosFacade.porCodigo("identificacion.gobierno");
        String internacional = parametrosFacade.porCodigo("identificacion.internacional");
        String juridico = parametrosFacade.porCodigo("identificacion.juridico");
        String menor = parametrosFacade.porCodigo("identificacion.menor");
        String pasaporte = parametrosFacade.porCodigo("identificacion.pasaporte");
        String rif = parametrosFacade.porCodigo("identificacion.rif");
        String nacional = parametrosFacade.porCodigo("identificacion.nacional");

        /**
         * Tipos de Documento
         */
        String tipoCedula = parametrosFacade.porCodigo("identificacion.documento.cedula");
        String tipoExtranjero = parametrosFacade.porCodigo("identificacion.documento.extranjero");
        String tipoPasaporte = parametrosFacade.porCodigo("identificacion.documento.pasaporte");
        String tipoRegistro = parametrosFacade.porCodigo("identificacion.documento.registro");
        String tipoRif = parametrosFacade.porCodigo("identificacion.documento.rif");

        if (tipoIdent.equals(nacional) || tipoIdent.equals(menor) || tipoIdent.equals(extranjero)) {
            return tipoCedula;
        }
        if (tipoIdent.equals(gobierno) || tipoIdent.equals(juridico)) {
            return tipoRegistro;
        }
        if (tipoIdent.equals(pasaporte)) {
            return tipoPasaporte;
        }
        if (tipoIdent.equals(rif)) {
            return tipoRif;
        }
        return null;
    }

    /**
     * Método para obtener el tipo de Usuario dependiendo del tipo de
     * identificación
     *
     * @param tipoIdent
     * @return Tipo de Usuario.
     */
    public String tipoUsuario(String tipoIdent) {
        /**
         * Tipos de Identificación
         */
        String extranjero = parametrosFacade.porCodigo("identificacion.extranjero");
        String gobierno = parametrosFacade.porCodigo("identificacion.gobierno");
        String juridico = parametrosFacade.porCodigo("identificacion.juridico");
        String menor = parametrosFacade.porCodigo("identificacion.menor");
        String pasaporte = parametrosFacade.porCodigo("identificacion.pasaporte");
        String rif = parametrosFacade.porCodigo("identificacion.rif");
        String nacional = parametrosFacade.porCodigo("identificacion.nacional");

        /**
         * Tipos de Documento
         */
        String clienteNatural = parametrosFacade.porCodigo("tipo.cliente.natural");
        String clienteJuridico = parametrosFacade.porCodigo("tipo.cliente.juridico");
        String clienteGubernamental = parametrosFacade.porCodigo("tipo.cliente.gobierno");
        String tipoRif = parametrosFacade.porCodigo("tipo.cliente.rif");
        String tipoPasaporte = parametrosFacade.porCodigo("tipo.cliente.pasaporte");
        String tipoExtranjero = parametrosFacade.porCodigo("tipo.cliente.extranjero");

        if (tipoIdent.equals(nacional) || tipoIdent.equals(menor)) {
            return clienteNatural;
        }
        if (tipoIdent.equals(extranjero)) {
            return tipoExtranjero;
        }
        if (tipoIdent.equals(pasaporte)) {
            return tipoPasaporte;
        }
        if (tipoIdent.equals(gobierno)) {
            return clienteGubernamental;
        }
        if (tipoIdent.equals(juridico)) {
            return clienteJuridico;
        }
        if (tipoIdent.equals(rif)) {
            return tipoRif;
        }
        return null;
    }

    /**
     * Metodo que camvierte una fecha normal a gregorian Calendar
     *
     * @param date fecha a transformar
     * @return
     */
    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return xmlCalendar;
    }

    /**
     * Metodo que cambierte una fecha DATE a format XMLGregorianCalendar
     *
     * @param dateFormat
     * @return
     */
    public static XMLGregorianCalendar convertToXmlGregorianWBFD(Date dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String date = sdf.format(dateFormat);
        XMLGregorianCalendar xmlCal;
        try {
            xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
        } catch (DatatypeConfigurationException ex) {
            xmlCal = null;
        }
        return xmlCal;
    }

    /*
     * Convierte un XMLGregorianCalendar a java.util.Date in Java
     */
    public static Date toDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    /**
     *
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Formatea un monto
     *
     * @param value
     * @return
     */
    public static String formatearMonto(Number value) {
        if (value == null) {
            return "";
        }

        DecimalFormatSymbols mySymbols = new DecimalFormatSymbols();
        mySymbols.setDecimalSeparator(',');
        mySymbols.setGroupingSeparator('.');
        DecimalFormat newFormat = new DecimalFormat("#,##0.00", mySymbols);
        // TODO: No podemos usar el doubleValue()
        return newFormat.format(value.doubleValue());
    }

    /**
     *
     * @param value
     * @return
     */
    public static String formatearMonto(BigDecimal value) {
        if (value == null) {
            return "";
        }

        DecimalFormatSymbols mySymbols = new DecimalFormatSymbols();
        mySymbols.setDecimalSeparator(',');
        mySymbols.setGroupingSeparator('.');
        DecimalFormat newFormat = new DecimalFormat("#,##0.00", mySymbols);
        // TODO: No podemos usar el doubleValue()
        return newFormat.format(value);
    }

    /**
     *
     * @param value
     * @return
     */
    public static BigDecimal parseMonto(String value) {
        if (value == null) {
            return null;
        }

        DecimalFormatSymbols mySymbols = new DecimalFormatSymbols();
        mySymbols.setDecimalSeparator(',');
        mySymbols.setGroupingSeparator('.');
        DecimalFormat newFormat = new DecimalFormat("#,##0.00", mySymbols);
        try {
            return new BigDecimal(newFormat.parse(value).toString());

        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(BodBaseBean.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     *
     * @param value
     * @return
     */
    public static String formatearEntero(Number value) {
        if (value == null) {
            return "";
        }

        DecimalFormatSymbols mySymbols = new DecimalFormatSymbols();
        mySymbols.setDecimalSeparator(',');
        mySymbols.setGroupingSeparator('.');
        DecimalFormat newFormat = new DecimalFormat("###0", mySymbols);
        // TODO: No podemos usar el doubleValue()
        return newFormat.format(value.doubleValue());
    }

    /**
     * Formatea la fecha/hora
     *
     * @param value
     * @return
     */
    public static String formatearFecha(Date value) {
        if (value == null) {
            return "";
        }

        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");

        return f.format(value);
    }

    /**
     * Formatea la fecha/hora
     *
     * @param value
     * @return
     */
    public static String formatearHora(Date value) {
        if (value == null) {
            return "";
        }

        SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss a");

        return f.format(value);
    }

    /**
     * Formatea la fecha/hora en String segun el formato recibido cmujica
     *
     * @param value
     * @param formato
     * @return fecha en String formateada
     */
    public static String formatearFechaString(String value, String formato) {
        if (value == null) {
            return "";
        }

        SimpleDateFormat f = new SimpleDateFormat(formato);

        return f.format(value);
    }

    /**
     * Formatea la fecha/hora en String segun el formato recibido cmujica
     *
     * @param value
     * @param formato
     * @return fecha en Date formateada
     */
    public static Date formatearFechaStringADate(String value, String formato) {
        Date fechaSalida = new Date();
        if (value == null) {
            return fechaSalida;
        }
        SimpleDateFormat f = new SimpleDateFormat(formato);
        try {
            fechaSalida = f.parse(value);

        } catch (ParseException e) {
            java.util.logging.Logger.getLogger(BodBaseBean.class
                    .getName()).log(Level.SEVERE, "ERROR AL INTENTAR PARSEAR UNA FECHA", e);
        }
        return fechaSalida;
    }

    /**
     *
     * @param nombre
     * @param operacion
     * @return
     */
    public static String nombreValidoWBFD(String nombre, int operacion) {
        String nombreValido = nombre.replace(",", "");
        if (operacion == 0) {
            if (nombreValido.length() >= 40) {
                nombreValido = nombreValido.substring(0, 39);
            }
        } else if (nombreValido.length() >= 30) {
            nombreValido = nombreValido.substring(0, 29);
        }
        return nombreValido;
    }

    /**
     * Metodo que valida la longitud permitida por WBFD para el finger Print.
     *
     * @param fingerPrint
     * @return fingerPrintValido de menos de 500 caracteres.
     */
    public static String fingerPrintValidoWBFD(String fingerPrint) {
        if (fingerPrint == null) {
            fingerPrint = " - ";
        } else if (fingerPrint.length() >= 500) {
            fingerPrint = fingerPrint.substring(0, 499);
        }
        return fingerPrint;
    }

    /**
     * Metodo que valida la longitud permitida por WBFD para el remoteUserAgent.
     *
     * @param remoteUsrAgent
     * @return remoteUsrAgentValido de menos de 100 caracteres.
     */
    public static String remoteUsrAgentValidoWBFD(String remoteUsrAgent) {
        if (remoteUsrAgent == null) {
            remoteUsrAgent = " - ";
        } else if (remoteUsrAgent.length() >= 100) {
            remoteUsrAgent = remoteUsrAgent.substring(0, 99);
        }
        return remoteUsrAgent;
    }

    /**
     * Se cambia la longitud del nombre, para que sea máx de 35 caracteres, como
     * lo solicita el servicio WSCardTDC.
     *
     * @param nombre
     * @return nombreValido
     */
    public static String nombreValido(String nombre) {
        String nombreValido = nombre.replace(",", "");
        if (nombreValido.length() >= 30) {
            nombreValido = nombreValido.substring(0, 29);
        }
        return nombreValido;
    }

    /**
     *
     * @param serviceName
     */
    protected void armarMensajeRespuestaVacia(String serviceName) {
        Long ambienteid;
        Long idiomaid;
        ambienteid = null;
        idiomaid = null;

        String message = textosFacade.porCodigo("error.servicio.generico", serviceName) + " " + textosFacade.porCodigo("error.servicio.rs.vacia", ambienteid, idiomaid);
        if (null == message || message.isEmpty()) {
            message = "Error en la llamada al servicio " + serviceName + ".  No se obtuvo respuesta.";
        }
        logger.log(Level.SEVERE, "[V] {0}", message);
        throw new WebServiceException(message);
    }

    // convert from internal Java String format -> UTF-8
    public static String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    /**
     *
     * @param serviceName
     */
    protected void armarMensajeRespuestaIncompleta(String serviceName) {
        Long ambienteid;
        Long idiomaid;
        ambienteid = null;
        idiomaid = null;

        String message = textosFacade.porCodigo("error.servicio.generico", serviceName) + " " + textosFacade.porCodigo("error.servicio.rs.errada", ambienteid, idiomaid);
        if (null == message || message.isEmpty()) {
            message = "Error en la llamada al servicio " + serviceName + ".  Se recibio una respuesta incompleta.";
        }
        logger.log(Level.SEVERE, "[I] {0}", message);
        throw new WebServiceException(message);
    }

    /**
     *
     * @param serviceName
     * @param severity
     * @param errorCode
     * @param errorDesc
     */
    protected void armarMensajeRespuestaError(String serviceName, String severity, String errorCode, String errorDesc) {
        Long ambienteid;
        Long idiomaid;
        ambienteid = null;
        idiomaid = null;

        logger.log(Level.WARNING, "Mensajes del servicio ''{0}'': severity = ''{1}''; errorCode = ''{2}''; errorDesc = ''{3}''.", new Object[]{serviceName, severity, errorCode, errorDesc});
        String errorMessage = errorDesc;
        if (errorCode != null && !errorCode.isEmpty()) {
            /**
             * Hasta ahora los errorCode siempre han sido de una de las
             * siguientes formas: (a) 4 digitos, (b) 7 caracteres donde los 4
             * ultimos son los digitos que nos interesan. Asumiremos de momento
             * que el codigo a usar son los ultimos 4 digitos de lo que nos
             * llegue, aunque es posible que esta logica no sea 100% correcta.
             */
            int numChars = errorCode.length();
            errorCode = (numChars > 4) ? errorCode.substring(numChars - 4, numChars) : errorCode;
            errorMessage = textosFacade.porCodigo("error.servicio." + errorCode, ambienteid, idiomaid);
            if (null == errorMessage || errorMessage.isEmpty()) {
                errorMessage = errorDesc;
            }
        }
        String message = textosFacade.porCodigo("error.operacion", ambienteid, idiomaid)
                + (null == errorMessage ? "" : errorMessage);
        logger.log(Level.SEVERE, "[E] {0}", message);
        throw new WebServiceException(message);
    }

    /**
     * Método genera una excepción tipo "WebServiceException" que contiene como
     * mensaje el código PLA0000 de respuesta del servicio.
     *
     * @param serviceName
     * @param severity
     * @param errorCode
     * @param errorDesc
     */
    protected void armarMensajeRespuestaServicio(String serviceName, String severity, String errorCode, String errorDesc) {
        Long ambienteid;
        Long idiomaid;
        ambienteid = null;
        idiomaid = null;

        logger.log(Level.WARNING, "Mensajes del servicio ''{0}'': severity = ''{1}''; errorCode = ''{2}''; errorDesc = ''{3}''.", new Object[]{serviceName, severity, errorCode, errorDesc});
        String errorMessage = errorDesc;
        if (errorCode != null && !errorCode.isEmpty()) {
            /**
             * Hasta ahora los errorCode siempre han sido de una de las
             * siguientes formas: (a) 4 digitos, (b) 7 caracteres donde los 4
             * ultimos son los digitos que nos interesan. Asumiremos de momento
             * que el codigo a usar son los ultimos 4 digitos de lo que nos
             * llegue, aunque es posible que esta logica no sea 100% correcta.
             */
            int numChars = errorCode.length();
            errorCode = (numChars > 4) ? errorCode.substring(numChars - 4, numChars) : errorCode;
            errorMessage = textosFacade.porCodigo("error.servicio." + errorCode, ambienteid, idiomaid);
            if (null == errorMessage || errorMessage.isEmpty()) {
                errorMessage = errorDesc;
            }
        }
        String message = textosFacade.porCodigo("error.operacion", ambienteid, idiomaid)
                + (null == errorMessage ? "" : errorMessage);
        logger.log(Level.SEVERE, "[E] {0}", message);

        throw new WebServiceException(errorCode);
    }

    /**
     *
     * @param serviceName
     * @param e
     */
    protected void armarMensajeRespuestaInesperada(String serviceName, Exception e) {
        String message = textosFacade.porCodigo("error.servicio.generico", serviceName);
        if (null == message || message.isEmpty()) {
            message = "Error en la llamada al servicio " + serviceName + ".";
        }
        logger.log(Level.SEVERE, "[U] " + message, e);
        throw new WebServiceException(message);
    }

    /**
     *
     * @return
     */
    public Date getCurrentDate() {
        return new Date();
    }

    /**
     *
     * @param fechaActual
     * @param fechaAnterior
     * @return
     */
    public long obtenerDiferenciaFecha(Date fechaActual, Date fechaAnterior) {
        long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
        return (fechaActual.getTime() - fechaAnterior.getTime()) / MILLSECS_PER_DAY;
    }
    
     /**
     *
     * @param valor
     * @return
     */
    public String encriptar(String valor) {
        if (encrypterDecrypter == null) {
            encrypterDecrypter = new EncrypterDecrypter("ThisIsASecretKey");
        }
        return encrypterDecrypter.encrypt(valor);
    }

    /**
     *
     * @param valorEncriptado
     * @return
     */
    public String desencriptar(String valorEncriptado) {
        if (encrypterDecrypter == null) {
            encrypterDecrypter = new EncrypterDecrypter("ThisIsASecretKey");
        }
        return encrypterDecrypter.decrypt(valorEncriptado);

    }

    /**
     * ***VARIABLES DE ACCIONES POR REALIZAR POR DEFECTO***
     */
    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a las preguntas de seguridad
     */
    public static final String ACTION_PREGUNTAS_SEGURIDAD = "SEND_QUESTION_SECURITY";

    /**
     * ***VARIABLES DE ACCIONES POR REALIZAR POR DEFECTO***
     */
    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a las preguntas de seguridad
     */
    public static final String ACTION_PREGUNTAS_SEGURIDAD_NUMERIC = "421";

    /**
     * ***VARIABLES DE ACCIONES POR REALIZAR POR DEFECTO***
     */
    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a las preguntas de seguridad
     */
    public static final String ACTION_ASIGNAR_SECUNDARIOS = "SEND_ASOCCIATED";

    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a cambio de clave de usuario
     */
    public static final String ACTION_CAMBIO_CLAVE = "SEND_CHANGE_PASS";
    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a cambio de clave de usuario
     */
    public static final String ACTION_CAMBIO_CLAVE_NUMERIC = "283";

    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a cambio de clave de imagen
     */
    public static final String ACTION_CAMBIO_IMAGEN = "SEND_CHANGE_IMAGE";

    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a cambio de clave de imagen
     */
    public static final String ACTION_CAMBIO_IMAGEN_NUMERIC = "403";

    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a seleccion de factores de autenticacion.
     */
    public static final String ACTION_FACTOR_3 = "SEND_FACTOR3";

    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a seleccion de factores de autenticacion en migración
     */
    public static final String ACTION_FACTOR_3MIGRATION = "FACTOR3_MIGRATION";

    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a seleccion de factores de autenticacion.
     */
    public static final String ACTION_FACTOR_3_NUMERIC = "45521";

    /**
     * Accion Por realizar para identificar las acciones por realizar
     * pertinentes a zona segura
     */
    public static final String ACTION_SAFE_ZONE = "500";

    /**
     * CODIGO QUE RETORNA EL RESPONSE DE AFILIACION DE ESTADO DE CUENTA CUANDO
     * EL CLIENTE NO ESTA AFILIADO
     */
    public static final String RESPONSE_NO_AFILIADO_ESTADO_CUENTA_DIGITAL = "03";

    /**
     * Valor por default para paged en servicio WSCollection
     */
    public static final String PAGED = "0";

    /**
     * inicio de archivos siris
     */
    public static final String RECAUDACION_SIRIS = "SIRIS";

    /**
     * inicio de archivos rim
     */
    public static final String RECAUDACION_RIM = "RIM";

    /**
     * ESTATUS PARA CONTRATOS ACTIVOS SIC
     */
    public static final String ESTATUS_ACTIVO_CONTRATOS_SIC = "AC";

    /**
     * Dispositivo soft token
     */
    public static final String DISPOSITIVO_SOFT_TOKEN = "dispositivo.soft.token";

    /**
     * Dispositivo sms otp
     */
    public static final String DISPOSITIVO_SMS_OTP = "dispositivo.SMS.OTP";

    /**
     * Dispositivo email otp
     */
    public static final String DISPOSITIVO_EMAIL_OTP = "dispositivo.email.OTP";

    /**
     * Dispositivo factor3 token
     */
    public static final String DISPOSITIVO_FACTOR3_TOKEN = "dispositivo.factor3.token";

    /**
     * Estatus que define una afiliación inciada
     */
    public static final char ESTATUS_AFILIACION_INICIADA = 'A';

    /**
     * Estatus que define una afiliacion desafiliada
     */
    public static final char ESTATUS_AFILIACION_DESAFILIADO = 'D';

    public static Funcionalidad Funcionalidad;

    public static enum Funcionalidad {

        AFILIACION_EXITOSA("afiliacion.exitosa", "DetectIDDAO", "afiliacion_5_preguntas", "/ext/Afiliacion/", "com.bod.controller.", "AfiliacionController", "afiliacion.exitosa"),
        AFILIACION_FALLIDA("afiliacion.fallida", "DetectIDDAO", "afiliacion_5_preguntas", "/ext/Afiliacion/", "com.bod.controller.", "AfiliacionController", "afiliacion.fallida"),
        AFILIACION_ASOCIADO_FALLIDA("afiliacion.asociado.fallida", "DetectIDDAO", "afiliacion_3_validacion_positiva", "/ext/Afiliacion/", "com.bod.controller.", "AfiliacionController", "afiliacion.asociacion.cancelada.motivo"),
        SERVICIOS_BOD("solicitudes.servicios.bod", "AccesPolicy", "index", "/sec/Solicitudes/ServiciosBOD/index", "com.bod.controller.", "ServiciosBODController", "servicios.bod.ingreso"),
        CONSULTA_ESTADOS_CUENTA("consulta.estados.cuenta", "AccesPolicy", "index", "/sec/ConsultaEstadosCuenta/index", "com.bod.controller.", "ConsultaEstadosCuentaController", "consulta.estados.cuenta"),
        CONSULTA_ESTADOS_CUENTA_DESCARGA("consulta.estados.cuenta.descarga", "AccesPolicy", "index", "/sec/ConsultaEstadosCuenta/index", "com.bod.controller.", "ConsultaEstadosCuentaController", "consulta.estados.cuenta.descarga"),
        AFILIACION_ESTADOS_CUENTA("afiliacion.cuenta.estado.index", "AccesPolicy", "index", "/sec/AfiliacionEstadoCuenta/index", "com.bod.controller.", "AfiliacionEstadoCuentaController", "afiliacion.cuenta.digital"),
        AFILIACION_BOD_MOVIL("solicitudes.bodmovil.afiliacion", "BodMobileDAO", "index", "/sec/Solicitudes/AfiliacionBodMovil/index", "com.bod.controller.", "AfiliacionBodMovilController", "bodmovil.titulo.aceptacion"),
        REAFILIACION("reafiliacion.usuario", "CriptoDAO", "index", "/ext/Afiliacion/", "com.bod.controller.", "ReafiliacionController", "reafiliacion.usuario"),
        CONFIGURACION_ESTADOS_CUENTA("configuracion.estados.cuenta", "AccesPolicy", "index", "/sec/ConfiguracionEstadosCuenta/index", "com.bod.controller.", "AfiliacionEstadoCuentaController", "configuracion.cuenta.digital"),
        CONFIGURACION_ESTADOS_CUENTA_GUARDADO("configuracion.estados.cuenta", "AccesPolicy", "index", "/sec/ConfiguracionEstadosCuenta/index", "com.bod.controller.", "AfiliacionEstadoCuentaController", "configuracion.cuenta.digital.modificacion"),
        ASOCIACIONES("configurar.asociaciones", "AccesPolicy", "index", "/sec/Asociaciones", "com.bod.controller.", "AsociacionesController", "modulo.asociaciones"),
        ASOCIACIONES_NUEVA_INVITACION("asociaciones.nueva.invitacion", "AccesPolicy", "realizar_invitacion", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.invitar.asociado"),
        ASOCIACIONES_VER_DETALLE_ASOCIADO("asociaciones.ver.detalle.asociado", "AccesPolicy", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.ver.detalle.asociado"),
        ASOCIACIONES_VER_PERFIL_ASOCIADO("asociaciones.ver.perfil.asociado", "PerfilesNbl", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.ver.perfil.asociado"),
        ASOCIACIONES_RECHAZAR_SOLICITUD("asociaciones.rechazar.solicitud", "PerfilesNbl", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.rechazar.solicitud"),
        ASOCIACIONES_ANULAR_SOLICITUD("asociaciones.anular.solicitud", "AsociacionFacade", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.anular.solicitud"),
        ASOCIACIONES_ACEPTAR_SOLICITUD("asociaciones.aceptar.solicitud", "AsociacionFacade", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.aceptar.solicitud"),
        ASOCIACIONES_DESCARGAR_RESUMEN("asociaciones.descargar.resumen", "ReporteBean", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.descargar.resumen"),
        ASOCIACIONES_CANCELAR_SOLICITUD("asociaciones.cancelar.solicitud", "AsociacionFacade", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.cancelar.solicitud"),
        ASOCIACIONES_TERMINAR_ASOCIACION("asociaciones.terminar.solicitud", "AsociacionFacade", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.terminar.solicitud"),
        ASOCIACIONES_ERROR_CODIGO_BT("asociaciones.error.codigo.bt", "AsociacionFacade", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "asociaciones.error.codigo.bt"),
        ASOCIACIONES_ERROR_INTENTOS_CODIGO_BT("asociaciones.error.codigo.bt", "AsociacionFacade", "index", "/sec/Asociaciones/", "com.bod.controller.", "AsociacionesController", "afiliacion.asociacion.cancelada.motivo"),
        AVANCE_EFECTIVO("avance.efectivo.index", "AccesPolicy", "index", "/sec/AvanceEfectivo/", "com.bod.controller", "AvanceEfectivoController", "avance.efectivo"),
        AVANCE_EFECTIVO_EFECTUADO("avance.efectivo.efectuado", "AccesPolicy", "index", "/sec/AvanceEfectivo/", "com.bod.controller", "AvanceEfectivoController", "avance.efectivo.aprobada"),
        AVANCE_EFECTIVO_RECHAZADO("avance.efectivo.efectuado", "AccesPolicy", "index", "/sec/AvanceEfectivo/", "com.bod.controller", "AvanceEfectivoController", "avance.efectivo.desaprobada"),
        TRANSFERENCIAS("transferencias", "AccesPolicy", "index", "/sec/Transferencias/", "com.bod.controller.", "TranferenciasController", "modulo.transferencia"),
        TRANSFERENCIAS_NUEVO_BENEFICIARIO("nuevo.beneficiario.transf", "AccesPolicy", "index", "/sec/Transferencias", "com.bod.controller.", "TranferenciasController", "nuevo.beneficiario.transf"),
        TRANSFERENCIAS_NUEVO_PRODUCTO("transferencia.nuevo.producto", "AccesPolicy", "index", "/sec/Transferencias/index", "com.bod.controller.", "TranferenciasController", "transferencia.nuevo.producto"),
        TRANSFERENCIAS_NUEVO_PRODUCTO_TERCERO("transferencia.nuevo.producto.tercero", "AccesPolicy", "index", "/sec/Transferencias/index", "com.bod.controller.", "TranferenciasController", "transferencia.nuevo.producto.tercero"),
        BLOQUEO_CLIENTE_ASOCIADOS("bloqueo.cliente.asociados", "AsociacionFacade", "index", "/sec/Asociaciones", "com.bod.controller.", "AsociacionesController", "bloqueo.cliente.asociados"),
        BLOQUEO_CLIENTE_ASOCIADOS_WBFD("bloqueo.cliente.asociados", "AsociacionFacade", "index", "/sec/Asociaciones", "com.bod.controller.", "AsociacionesController", "bloqueo.cliente.asociados.wbfd"),
        INGRESOCAMBIOIMAGEN("cambiar.imagen", "AccesPolicy", "cambiar_imagen", "/sec/ConfiguracionSeguridad/cambiar_imagen", "com.bod.controller.", "CambioImagenController", "ingreso.cambiar.imagen"),
        INGRESOCAMBIOIMAGEN_NOCLIENTE("cambiar.imagen.nocliente", "AccesPolicy", "cambio_imagen_nocliente", "/sec/ConfiguracionSeguridad/cambio_imagen_nocliente", "com.bod.controller.", "CambioImagenController", "cambiar.imagen.nocliente"),
        CAMBIOIMAGEN("cambiar.imagen", "AccesPolicy", "cambiar_imagen", "/sec/ConfiguracionSeguridad/cambiar_imagen", "com.bod.controller.", "CambioImagenController", "cambiar.imagen"),
        CAMBIOPREGUNTAS("cambiar.preguntas", "AccesPolicy", "cambio_preguntas", "/sec/ConfiguracionSeguridad/cambio_preguntas", "com.bod.controller.", "PreguntasSeguridadController", "cambio.preguntas"),
        CAMBIOPREGUNTAS_NOCLIENTE("cambiar.preguntas.nocliente", "AccesPolicy", "cambio_preguntas_nocliente", "/sec/ConfiguracionSeguridad/cambio_preguntas_nocliente", "com.bod.controller.", "PreguntasSeguridadController", "cambiar.preguntas.nocliente"),
        PREGUNTAS_SELECCIONADAS("cambiar.preguntas", "AccesPolicy", "afiliacion_5_preguntas", "/ext/Afiliacion/afiliacion_5_preguntas", "com.bod.controller.", "PreguntasSeguridadController", "preguntas.seleccionadas"),
        CAMBIOCLAVE("cambiar.clave", "cambio_preguntas", "CriptoDAO", "/sec/ConfiguracionSeguridad/cambio_preguntas", "com.bod.controller.", "CambioPreguntasController", "cambiar.clave"),
        CAMBIOCLAVE_NOCLIENTE("cambiar.clave.nocliente", "AccesPolicy", "cambio_clave_nocliente", "/sec/ConfiguracionSeguridad/cambio_clave_nocliente", "com.bod.controller.", "CambioClaveController", "cambiar.clave.nocliente"),
        CAMBIARCLAVE("cambiar.clave", "cambio_preguntas", "CriptoDAO", "/sec/ConfiguracionSeguridad/cambio_preguntas", "com.bod.controller.", "CambioPreguntasController", "cambio.clave.exitoso"),
        CREAR_CLAVE("crear.clave", "cambio_preguntas", "CriptoDAO", "/sec/ConfiguracionSeguridad/cambio_preguntas", "com.bod.controller.", "CambioPreguntasController", "crear.clave"),
        REGENERAR_CLAVE("regenerar.clave", "cambio_preguntas", "CriptoDAO", "/sec/ConfiguracionSeguridad/cambio_preguntas", "com.bod.controller.", "CambioPreguntasController", "regenerar.clave"),
        RECUPERACION_CLAVE("recuperacion.usuario", "cambio_preguntas", "CriptoDAO", "/ext/RecuperacionUsuario", "com.bod.controller.", "RecuperacionUsuarioController", "regenerar.clave"),
        CAMBIOFLUJOSAPROBACION("cambiar.flujos.aprobacion", "AccesPolicy", "flujos_aprobacion", "/sec/ConfiguracionSeguridad/flujos_aprobacion", "com.bod.controller.", "FlujosAprobacionController", ""),
        CAMBIOPERFILES("cambiar.perfiles", "AccesPolicy", "perfiles_asociados", "/sec/ConfiguracionSeguridad/perfiles_asociados", "com.bod.controller.", "PerfilesAsociadosController", "cambio.perfil"),
        CONFIGURACION_SEGURIDAD_CREAR_PERFIL("crear.perfil.conf.seguridad", "AccesPolicy", "perfiles_asociados", "/sec/ConfiguracionSeguridad/", "com.bod.controller.", "PerfilesAsociadosController", "crear.perfil.conf.seguridad"),
        ASOCIACIONES_CREAR_PERFIL("crear.perfil.asociaciones", "AccesPolicy", "perfiles_asociados", "/sec/Asociaciones/", "com.bod.controller.", "PerfilesAsociadosController", "crear.perfil.asociaciones"),
        CONFIGURACION_SEGURIDAD_MODIFICAR_PERFIL("modificar.perfil.conf.seguridad", "AccesPolicy", "perfiles_asociados", "/sec/ConfiguracionSeguridad/", "com.bod.controller.", "PerfilesAsociadosController", "modificar.perfil.conf.seguridad"),
        ASOCIACIONES_MODIFICAR_PERFIL("modificar.perfil.asociaciones", "AccesPolicy", "perfiles_asociados", "/sec/Asociaciones/", "com.bod.controller.", "PerfilesAsociadosController", "modificar.perfil.asociaciones"),
        ELIMINAR_PERFIL("eliminar.perfil", "AccesPolicy", "perfiles_asociados", "/sec/ConfiguracionSeguridad/perfiles_asociados", "com.bod.controller.", "PerfilesAsociadosController", "eliminar.perfil"),
        CONFIGURACION_PERFILES_INVITADO("configuracion.perfiles", "AccesPolicy", "configuracion_perfiles", "sec/ConfiguracionPerfiles/configuracion_perfiles", "com.bod.controller", "PerfilesAsociadosController", "configuracion.perfiles"),
        CONFIGURACION_SEGURIDAD_NOCLIENTE("configuracion.seguridad.nocliente", "AccesPolicy", "menu_configuracion_nocliente", "/sec/ConfiguracionSeguridad/menu_configuracion_nocliente", "com.bod.controller", "ConfiguracionSeguridadController", "configuracion.seguridad.nocliente"),
        INVITACIONES_USUARIO_NOCLIENTE("invitaciones.nocliente", "AccesPolicy", "index_usuario_nocliente", "/sec/Asociaciones/index_usuario_nocliente", "com.bod.controller", "AsociacionesController", "invitaciones.nocliente"),
        CONFIGURACION_SEGURIDAD("configuracion.seguridad", "AccesPolicy", "menu_configuracion", "/sec/ConfiguracionSeguridad/menu_configuracion", "com.bod.controller", "ConfiguracionSeguridadController", "configuracion.seguridad.modulo"),
        CONFIGURACION_SEGURIDAD_CAMBIO_CLAVE("cambio.clave.seguridad", "CriptoDAO", "cambio_clave", "/sec/ConfiguracionSeguridad/", "com.bod.controller", "CambioClaveCompController", "cambio.clave.seguridad"),
        CREDITOS("consulta.creditos", "AccesPolicy", "index", "/sec/Creditos/", "com.bod.controller", "CreditsController", "consulta.creditos"),
        CREDITOS_CONSULTA("consulta.creditos", "AccesPolicy", "index", "/sec/Creditos/", "com.bod.controller", "CreditsController", "pre.resumen.creditos"),
        CIERRE_SESION("cierre.sesion", "AccesPolicy", "index", "/ext/Login/", "com.bod.controller", "Login", "cierre.sesion"),
        CIERRE_SESION_WBFD("fin.sesion", "AccesPolicy", "index", "/ext/Login/", "com.bod.controller", "Login", "cierre.sesion.wbfd"),
        DETALLE_CREDITOS("detalle.creditos", "AccesPolicy", "detalle_creditos", "/sec/Creditos/", "com.bod.controller", "CreditsController", "detalle.creditos"),
        DETALLE_RECLAMO("detalle.reclamos", "AccesPolicy", "index", "/sec/Reclamos/", "com.bod.controller", "ReclamoController", "modulo.reclamo.detalle"),
        DESBLOQUEO_USUARIO("desbloqueo.usuario", "ComunesController", "index", "ext/DesbloquearUsuario/", "com.bod.controller", "DesbloquearUsuarioController", "desbloqueo.usuario"),
        DESBLOQUEO_USUARIO_EXITOSO("desbloqueo.usuario", "ComunesController", "index", "ext/DesbloquearUsuario/", "com.bod.controller", "DesbloquearUsuarioController", "desbloqueo.usuario.listo.desbloqueo"),
        CUENTAS("consulta.cuentas", "AccesPolicy", "index", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "cuentas.resumen"),
        CUENTAS_DESCARGA("consulta.cuentas", "AccesPolicy", "index", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "cuentas.resumen.descarga"),
        CUENTAS_DESCARGA_DETALLE_MOVIMIENTO("detalle.cuentas", "AccesPolicy", "index", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "cuentas.descarga.detalle.mov"),
        VER_CUENTA_ASISTENCIA("consulta.cuenta.asistencia", "AccesPolicy", "index", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "consulta.cuenta.asistencia"),
        VER_CUENTA_POS("consulta.cuenta.pos", "AccesPolicy", "index", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "consulta.cuenta.pos"),
        DETALLE_CUENTAS("detalle.cuentas", "AccesPolicy", "detalle_cuentas", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "cuentas.detalle"),
        CONSULTA_POS("consulta.pos", "AccesPolicy", "index", "/sec/LiquidacionEstablecimiento/", "com.bod.controller", "ConsultaPosController", ""),
        CONSULTA_POSTDD("consultaTDD.pos", "AccesPolicy", "index", "/sec/LiquidacionEstablecimiento/", "com.bod.controller", "ConsultaPosTDDController", ""),
        CONSULTA_POSTDC("consultaTDC.pos", "AccesPolicy", "index", "/sec/LiquidacionEstablecimiento/", "com.bod.controller", "ConsultaPosTDCController", ""),
        DIRECTORIO_GLOBAL("consulta.directorioglobal", "AccesPolicy", "index", "/sec/Directorio/", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.ingreso.modulo"),
        DIRECTORIO_GLOBAL_CARGAR_ARCHIVO("directorio.cargar.archivo", "AccesPolicy", "index", "/sec/Directorio/", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.carga.masiva"),
        DIRECTORIO_GLOBAL_NUEVO_PRODUCTO("directorio.nuevo.producto", "AccesPolicy", "index", "/sec/Directorio/index", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.agregar.producto"),
        DIRECTORIO_GLOBAL_EDITAR_PRODUCTO("directorio.editar.producto", "AccesPolicy", "index", "/sec/Directorio/", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.editar.producto"),
        DIRECTORIO_GLOBAL_ELIMINAR_PRODUCTO("directorio.eliminar.producto", "AccesPolicy", "index", "/sec/Directorio/", "com.bod.controller", "DirectorioGlobalController", "directorio.eliminar.producto"),
        DIRECTORIO_GLOBAL_NUEVO_BENEFICIARIO("nuevo.beneficiario.directorio", "DirectorioGlobalFacade", "editar", "/sec/Directorio/editar", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.agregar.beneficiario"),
        DIRECTORIO_GLOBAL_EDITAR_BENEFICIARIO("directorio.editar.beneficiario", "DirectorioGlobalFacade", "index", "/sec/Directorio/", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.editar.beneficiario"),
        DIRECTORIO_GLOBAL_ELIMINAR_BENEFICIARIO("directorio.eliminar.beneficiario", "DirectorioGlobalFacade", "index", "/sec/Directorio/", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.eliminar.beneficiario"),
        DIRECTORIO_GLOBAL_ELIMINAR_BENEFICIARIO_WBFD("directorio.eliminar.beneficiario", "DirectorioGlobalFacade", "index", "/sec/Directorio/", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.eliminar.beneficiario.wbfd"),
        DIRECTORIO_GLOBAL_VACIAR_DIRECTORIO("directorioglobal.vaciar.directorio", "DirectorioGlobalFacade", "index", "/sec/Directorio/", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.eliminar.beneficiario"),
        DIRECTORIO_GLOBAL_DESCARGAR_ARCHIVO_MODELO("directorioglobal.descargar.archivo.modelo", "ReporteBean", "carga_masiva", "/sec/Directorio/", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.descargar.archivo.modelo"),
        DIRECTORIO_GLOBAL_PROCESAR_ARCHIVO("directorioglobal.procesar.archivo", "AccesPolicy", "ReporteBean", "/sec/Directorio/carga_masiva.xhtml", "com.bod.controller", "DirectorioGlobalController", "directorioglobal.procesar.archivo"),
        CADIVI("consulta.divisas", "AccesPolicy", "index", "/sec/Divisas/", "com.bod.controller", "CadiviController", "cadivi.modulo"),
        DETALLE_CADIVI("detalle.cadivi", "AccesPolicy", "detalle_cadivi", "/sec/Divisas/", "com.bod.controller", "CadiviController", "cadivi.detalle"),
        CUENTAS_DIVISAS_DETALLE("consulta.cuentas.divisas", "AccesPolicy", "detallesCuentaDivisas", "/sec/ConsultaCuentasDivisas/", "com.bod.controller", "CuentasDivisasController", "cuentas.divisas.detalles"),
        CUENTAS_DIVISAS_RESUMEN("consulta.divisas", "AccesPolicy", "index", "/sec/ConsultaCuentasDivisas/", "com.bod.controller", "CuentasDivisasController", "cuentas.divisas.resumen"),
        CUENTAS_DIVISAS_RESUMEN_DESCARGA("consulta.divisas", "AccesPolicy", "index", "/sec/ConsultaCuentasDivisas/", "com.bod.controller", "CuentasDivisasController", "cuentas.divisas.resumen.descarga"),
        /*Seccion para el modulo de excepciones de limite*/
        EXCEPCIONES_LIMITES("configurar.excepciones.limites", "AccesPolicy", "index", "/sec/ExcepcionesLimites/", "com.bod.controller", "ExcepcionesLimitesController", "configurar.excepciones.limites"),
        MODIFICAR_EXCEPCIONES_LIMITES("modificar.excepciones.limites", "Excepciones", "crearExcepciones", "/sec/ExcepcionesLimites/", "com.bod.controller", "ExcepcionesLimitesController", "modificar.excepciones.limites"),
        MODIFICAR_EXCEPCIONES_LIMITES_EXITOSO("modificar.excepciones.limites", "Excepciones", "crearExcepciones", "/sec/ExcepcionesLimites/", "com.bod.controller", "ExcepcionesLimitesController", "modificar.excepciones.limites.efectuada"),
        CREAR_EXCEPCIONES_LIMITES("crear.excepciones.limites", "Excepciones", "crearExcepciones", "/sec/ExcepcionesLimites/", "com.bod.controller", "ExcepcionesLimitesController", "crear.excepciones.limites"),
        ELIMINAR_EXCEPCIONES_LIMITES("configurar.excepciones.limites.eliminacion", "Excepciones", "crearExcepciones", "/sec/ExcepcionesLimites/", "com.bod.controller", "ExcepcionesLimitesController", "configurar.excepciones.limites.eliminacion"),
        /*Seccion para el modulo de equipo Frecuente*/
        EQUIPO_USO_FRECUENTE("consultar.equipo.frecuente", "AccesPolicy", "equipoUsoFrecuente", "/sec/ConfiguracionSeguridad/equipoUsoFrecuente/", "com.bod.controller", "EquipoUsoFrecuenteController", "consultar.equipo.frecuente"),
        EQUIPO_USO_FRECUENTE_NOCLIENTE("consultar.equipo.frecuente.nocliente", "AccesPolicy", "equipoUsoFrecuente", "/sec/ConfiguracionSeguridad/equipoUsoFrecuente_nocliente", "com.bod.controller", "EquipoUsoFrecuenteController", "consultar.equipo.frecuente.nocliente"),
        EQUIPO_USO_FRECUENTE_ELIMINAR("equipo.frecuente.eliminar", "AccesPolicy", "equipoUsoFrecuente", "/sec/ConfiguracionSeguridad/equipoUsoFrecuente/", "com.bod.controller", "EquipoUsoFrecuenteController", "equipo.frecuente.eliminar"),
        EQUIPO_USO_FRECUENTE_AGREGAR("equipo.frecuente.agregar", "AccesPolicy", "equipoUsoFrecuente", "/sec/ConfiguracionSeguridad/equipoUsoFrecuente/", "com.bod.controller", "EquipoUsoFrecuenteController", "equipo.frecuente.agregar"),
        /*Seccion para el modulo de fideicomisos*/
        FIDEICOMISOS("consulta.fideicomisos", "AccesPolicy", "index", "/sec/Fideicomisos/index", "com.bod.controller", "FideicomisosController", "consulta.fideicomisos"),
        REFERENCIAS("solicitudes.referencias", "AccesPolicy", "index", "/sec/Solicitudes/Referencias/index", "com.bod.controller", "ReferenciasController", "pre.referencias"),
        DETALLE_FIDEICOMISOS_SMALL("detalle.fideicomisos.small", "AccesPolicy", "resumen_fideicomisos_small", "/sec/Fideicomisos/resumen_fideicomisos_small", "com.bod.controller", "FideicomisosController", "detalle.fideicomisos"),
        DETALLE_FIDEICOMISOS("detalle.fideicomisos", "detalle_fideicomisos", "AccesPolicy", "/sec/Fideicomisos/detalle_fideicomisos", "com.bod.controller", "FideicomisosController", "detalle.fideicomisos"),
        DETALLE_FIDEICOMISOS_PRESTACIONES("detalle.fideicomisos.prestaciones", "AccesPolicy", "detalle_prestaciones", "/sec/Fideicomisos/detalle_prestaciones", "com.bod.controller", "FideicomisosController", "detalle.fideicomisos.prestaciones"),
        DETALLE_FIDEICOMISOS_BENEFICIARIO("detalle.fideicomisos.beneficiario", "AccesPolicy", "detalle_fideicomisos_beneficiario", "/sec/Fideicomisos/detalle_fideicomisos_beneficiario", "com.bod.controller", "FideicomisosController", "detalle.fideicomisos.beneficiario"),
        DETALLE_FIDEICOMISOS_INVERSION_INDIVIDUAL("detalle.fideicomisos.inversion.individual", "AccesPolicy", "detalle_fideicomiso_inversion_individual", "/sec/Fideicomisos/detalle_fideicomiso_inversion_individual", "com.bod.controller", "FideicomisosController", "detalle.fideicomisos.inversion.individual"),
        DETALLE_FIDEICOMISO_COLECTIVO("detalle.fideicomisos.colectivo", "AccesPolicy", "detalle_fideicomiso_colectivo", "/sec/Fideicomisos/detalle_fideicomiso_colectivo", "com.bod.controller", "FideicomisosController", "detalle.fideicomisos.colectivo"),
        DETALLE_FIDEICOMISO_ADMIN("detalle.fideicomisos.administracion", "AccesPolicy", "detalle_fideicomiso_admon", "/sec/Fideicomisos/detalle_fideicomiso_admon", "com.bod.controller", "FideicomisosController", "detalle.fideicomisos.administracion"),
        DETALLE_CARTERA_INVERSION_FIDECOMISO("fideicomisos.detalle.cartera.inversion", "AccesPolicy", "detalle_cartera_inversion", "/sec/Fideicomisos/detalle_cartera_inversion", "com.bod.controller", "FideicomisosController", "fideicomisos.detalle.cartera.inversion"),
        DETALLE_BENEFICIARIO_FIDECOMITENTE("detalle.beneficiario.fidecomitente", "AccesPolicy", "detalle_benef_fidecomitente", "/sec/Fideicomisos/detalle_benef_fidecomitente", "com.bod.controller", "FideicomisosController", "detalle.beneficiario.fidecomitente"),
        CARTERA_INVERSION_FIDEICOMISOS("fideicomisos.cartera.inversion", "AccesPolicy", "cartera_inversion_fideicomisos", "/sec/Fideicomisos/cartera_inversion_fideicomisos", "com.bod.controller", "FideicomisosController", "fideicomisos.cartera.inversion"),
        BALANCE_GENERAL_FIDEICOMISOS("detalle.fideicomisos.balance.general", "AccesPolicy", "balance_general_fideicomisos", "/sec/Fideicomisos/balance_general_fideicomisos", "com.bod.controller", "FideicomisosController", "detalle.fideicomisos.balance.general"),
        /* termina la seccion de fideicomisos*/
        FACTORES_AUTENTICACION("factores.autenticacion", "AccesPolicy", "seguridad_operaciones", "/sec/ConfiguracionSeguridad/seguridad_operaciones", "com.bod.controller", "SeguridadOperacionesController", "factores.autenticacion"),
        FACTORES_AUTENTICACION_NOCLIENTE("factores.autenticacion.nocliente", "AccesPolicy", "seguridad_operaciones_nocliente", "/sec/ConfiguracionSeguridad/seguridad_operaciones_nocliente", "com.bod.controller", "SeguridadOperacionesController", "factores.autenticacion.nocliente"),
        FACTORES_AUTENTICACION_SELECCIONADO("factores.autenticacion", "AccesPolicy", "seguridad_operaciones", "/sec/ConfiguracionSeguridad/seguridad_operaciones", "com.bod.controller", "SeguridadOperacionesController", "factores.autenticacion.elegido"),
        FACTORES_AUTENTICACION_EXITOSO("factores.autenticacion.cambio", "AccesPolicy", "seguridad_operaciones", "/sec/ConfiguracionSeguridad/seguridad_operaciones", "com.bod.controller", "SeguridadOperacionesController", "factores.autenticacion.exitoso"),
        FLUJO_APROBACION("flujo.aprobacion", "AccesPolicy", "index", "/sec/FlujoAprobacion/", "com.bod.controller", "FlujoAprobacionController", "flujo.aprobacion.bitacora.modulo"),
        FLUJO_APROBACION_ASOCIADOS("modulo.aprobacion.asociados", "AccesPolicy", "index", "/sec/FlujoAprobacion/aprobar_asociados", "com.bod.controller", "FlujoAprobacionController", "flujo.aprobacion.bitacora.modulo"),
        NOTIFICACIONES("notificaciones", "AccesPolicy", "index", "/sec/Notificaciones/index", "com.bod.controller", "NotificacionesController", "modulo.notificaciones"),
        NOTIFICACIONES_ENVIO("configurar.notificaciones.nbl", "AccesPolicy", "index", "/sec/Notificaciones/index", "com.bod.controller", "NotificacionesController", "notificaciones.configuracion.envio"),
        NOTIFICACIONES_ENVIO_COMPROBANTE_EXITOSO("configurar.notificaciones.nbl", "AccesPolicy", "comprobante_metodo_envio", "/sec/Notificaciones/comprobante_metodo_envio", "com.bod.controller", "NotificacionesController", "notif.comp.metodo.envio.exito"),
        NOTIFICACIONES_ENVIO_COMPROBANTE_FALLIDO("configurar.notificaciones.nbl", "AccesPolicy", "comprobante_metodo_envio", "/sec/Notificaciones/comprobante_metodo_envio", "com.bod.controller", "NotificacionesController", "notif.comp.metodo.envio.fallido"),
        NOTIFICACIONES_FINANCIERAS("configurar.notificaciones.nbl", "AccesPolicy", "index", "/sec/Notificaciones/index", "com.bod.controller", "NotificacionesController", "notificaciones.configuracion.financieras"),
        NOTIFICACIONES_FINANCIERAS_FALLIDA("configurar.notificaciones.nbl", "AccesPolicy", "index", "/sec/Notificaciones/index", "com.bod.controller", "NotificacionesController", "notif.config.financieras.fallida"),
        NOTIFICACIONES_NO_FINANCIERAS("configurar.notificaciones.nbl", "AccesPolicy", "index", "/sec/Notificaciones/index", "com.bod.controller", "NotificacionesController", "notificaciones.configuracion.no.financieras"),
        NOTIFICACIONES_NO_FINANCIERAS_FALLIDA("configurar.notificaciones.nbl", "AccesPolicy", "index", "/sec/Notificaciones/index", "com.bod.controller", "NotificacionesController", "notif.config.no.financieras.fallida"),
        OPERACIONES("consulta.operaciones", "AccesPolicy", "index", "/sec/Operaciones/", "com.bod.controller", "ConsultaOperacionesController", "consulta.operaciones"),
        PANEL_FINANCIERO("panel.financiero", "AccesPolicy", "panel_financiero2", "/sec/PanelFinanciero/panel_financiero2", "com.bod.controller", "PanelFinancieroController", "modulo.panel.financiero"),
        PANEL_FINANCIERO_DESCARGA_ACTIVOS("panel.financiero", "AccesPolicy", "panel_financiero2", "/sec/PanelFinanciero/panel_financiero2", "com.bod.controller", "PanelFinancieroController", "panel.finaciero.descarga.activos"),
        PANEL_FINANCIERO_DESCARGA_PASIVOS("panel.financiero", "AccesPolicy", "panel_financiero2", "/sec/PanelFinanciero/panel_financiero2", "com.bod.controller", "PanelFinancieroController", "panel.finaciero.descarga.pasivos"),
        PAGOS_CREDITOS("pagos.creditos", "AccesPolicy", "index", "/sec/Pagos/Creditos/", "com.bod.controller", "PagosCreditosController", "pago.creditos"),
        PAGOS_CREDITOS_APROBADO("pagos.creditos.efectuado", "CreditosDAO", "index", "/sec/Pagos/Creditos/", "com.bod.controller", "PagosCreditosController", "pago.creditos.aprobado"),
        PAGOS_CREDITOS_RECHAZO("pagos.creditos.efectuado", "CreditosDAO", "index", "/sec/Pagos/Creditos/", "com.bod.controller", "PagosCreditosController", "pago.creditos.rechazado"),
        PAGOS_CREDITOS_DESCARGA("pagos.creditos.descarga.efectuado", "ReporteBean", "index", "/sec/Pagos/Creditos/", "com.bod.controller", "PagosCreditosController", "pagos.creditos.descarga.efectuado"),
        PAGOS_NOMINA_INGRESO("pagos.nomina.index", "AccesPolicy", "index", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "sip.ingreso"),
        PAGOS_NOMINA_CONSULTA_INGRESO("pagos.nomina.consulta", "AccesPolicy", "pago_nomina_consulta", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "sip.consulta.ingreso"),
        PAGOS_NOMINA_PAGO_IND_INGRESO("pagos.nomina.index", "AccesPolicy", "pago_individual", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "sip.pago.ind.ingreso"),
        PAGOS_NOMINA_PAGO_MASIVO_INGRESO("pagos.nomina.index", "AccesPolicy", "pagos_masivos", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "sip.pago.masivo.ingreso"),
        PAGOS_NOMINA("pagos.nomina.index", "AccesPolicy", "index", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "pago.procesado"),
        PAGOS_NOMINA_INDIVIDUAL("pagos.nomina.individual.efectuado", "AccesPolicy", "pago_individual", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "pagos.nomina.individual"),
        PAGOS_NOMINA_INDIVIDUAL_EXITOSO("pagos.nomina.individual.efectuado", "AccesPolicy", "pago_individual", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "pagos.nomina.individual.exitoso"),
        PAGOS_NOMINA_INDIVIDUAL_FALLIDO("pagos.nomina.individual.efectuado", "AccesPolicy", "pago_individual", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "pagos.nomina.individual.fallido"),
        PAGOS_NOMINA_DESCARGA_INDIVIDUAL_EXITOSO("pagos.nomina.descarga", "ComunesController", "pagos_masivos", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "nomina.individual.descarga.exitoso"),
        PAGOS_NOMINA_DESCARGA_INDIVIDUAL_FALLIDO("pagos.nomina.descarga", "ComunesController", "pagos_masivos", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "nomina.individual.descarga.fallido"),
        PAGOS_NOMINA_MASIVO("pagos.nomina.masivos.efectuado", "AccesPolicy", "pagos_masivos", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "pagos.nomina.masivos"),
        PAGOS_NOMINA_MASIVO_EXITOSO("pagos.nomina.masivos.efectuado", "AccesPolicy", "pagos_masivos", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "pagos.nomina.masivos.exitoso"),
        PAGOS_NOMINA_MASIVO_FALLIDO("pagos.nomina.masivos.efectuado", "AccesPolicy", "pagos_masivos", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "pagos.nomina.masivos.fallido"),
        PAGOS_NOMINA_DESCARGA_MASIVO_EXITOSO("pagos.nomina.descarga", "ComunesController", "pagos_masivos", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "nomina.masivos.descarga.exitoso"),
        PAGOS_NOMINA_DESCARGA_MASIVO_FALLIDO("pagos.nomina.descarga", "ComunesController", "pagos_masivos", "/sec/Pagos/Nomina/", "com.bod.controller", "NominaController", "nomina.masivos.descarga.fallido"),
        PAGOS_NOMINA_DESCARGA_LOTE_EXITOSO("nomina.descarga.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.descarga.lote.exitoso"),
        PAGOS_NOMINA_DESCARGA_LOTE_FALLIDO("nomina.descarga.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.descarga.lote.fallido"),
        PAGOS_NOMINA_DESCARGA_RESUMEN_LOTE_EXITOSO("nomina.resumen.descarga.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.resumen.descarga.lote.exitoso"),
        PAGOS_NOMINA_DESCARGA_RESUMEN_LOTE_FALLIDO("nomina.resumen.descarga.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.resumen.descarga.lote.fallido"),
        NOMINA_DETALLE_LOTE_EXITOSO("nomina.detalle.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.detalle.lote.exitoso"),
        NOMINA_DETALLE_LOTE_FALLIDO("nomina.detalle.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.detalle.lote.fallido"),
        NOMINA_DETALLE_LOTE_DESCARGA_EXITOSO("nomina.detalle.lote.descarga", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.descarga.lote.exitoso"),
        NOMINA_DETALLE_LOTE_DESCARGA_FALLIDO("nomina.detalle.lote.descarga", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.descarga.lote.fallido"),
        NOMINA_COMPROBANTE_LOTE_DESCARGA_EXITOSO("nomina.descarga.comprobante", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.descarga.comprobante.exitoso"),
        NOMINA_COMPROBANTE_LOTE_DESCARGA_FALLIDO("nomina.descarga.comprobante", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.descarga.comprobante.fallido"),
        NOMINA_RESUMEN_EXITOSO("nomina.resumen.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.resumen.lote.exitoso"),
        NOMINA_RESUMEN_FALLIDO("nomina.resumen.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "nomina.resumen.lote.fallido"),
        PAGOS_PROVEEDORES_INGRESO("pagos.proveedores.index", "AccesPolicy", "index", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "sip.proveedores.ingreso"),
        PAGOS_PROVEEDORES_CONSULTA_INGRESO("pagos.proveedores.consulta", "AccesPolicy", "pago_proveedores_consulta", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "sip.proveedores.consulta.ingreso"),
        PAGOS_PROVEEDORES_INDIVIDUAL_INGRESO("pagos.proveedores.individual", "AccesPolicy", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "sip.pago.proveedores.ind.ingreso"),
        PAGOS_PROVEEDORES_MASIVO_INGRESO("pagos.proveedores.masivos", "AccesPolicy", "pagos_masivos", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "sip.pago.proveedores.masivo.ingreso"),
        PAGOS_PROVEEDORES("pagos.proveedores.index", "AccesPolicy", "index", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "pagos.proveedores.modulo"),
        PAGOS_PROVEEDORES_INDIVIDUAL("pagos.proveedores.individual.efectuado", "AccesPolicy", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "pago.procesado"),
        PAGOS_PROVEEDORES_INDIVIDUAL_EXITOSO("pagos.proveedores.individual.efectuado", "AccesPolicy", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "pago.proveedores.individual.exitoso"),
        PAGOS_PROVEEDORES_INDIVIDUAL_FALLIDO("pagos.proveedores.individual.efectuado", "AccesPolicy", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "pago.proveedores.individual.fallido"),
        PAGOS_PROVEEDORES_DESCARGA_INDIVIDUAL_EXITOSO("pagos.proveedores.descarga", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.individual.descarga.exitoso"),
        PAGOS_PROVEEDORES_DESCARGA_INDIVIDUAL_FALLIDO("pagos.proveedores.descarga", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.individual.descarga.fallido"),
        PAGOS_PROVEEDORES_DESCARGA_LOTE_EXITOSO("proveedores.descarga.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.descarga.lote.exitoso"),
        PAGOS_PROVEEDORES_DESCARGA_LOTE_FALLIDO("proveedores.descarga.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.descarga.lote.fallido"),
        PAGOS_PROVEEDORES_DESCARGA_RESUMEN_LOTE_EXITOSO("proveedores.resumen.descarga.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.resumen.descarga.lote.exitoso"),
        PAGOS_PROVEEDORES_DESCARGA_RESUMEN_LOTE_FALLIDO("proveedores.resumen.descarga.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.resumen.descarga.lote.fallido"),
        PROVEEDORES_RESUMEN_EXITOSO("proveedores.resumen.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.resumen.lote.exitoso"),
        PROVEEDORES_RESUMEN_FALLIDO("proveedores.resumen.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.resumen.lote.fallido"),
        PROVEEDORES_DETALLE_LOTE_EXITOSO("proveedores.detalle.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.detalle.lote.exitoso"),
        PROVEEDORES_DETALLE_LOTE_FALLIDO("proveedores.detalle.lote", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.detalle.lote.fallido"),
        PROVEEDORES_DETALLE_LOTE_DESCARGA_EXITOSO("proveedores.detalle.lote.descarga", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.descarga.lote.exitoso"),
        PROVEEDORES_DETALLE_LOTE_DESCARGA_FALLIDO("proveedores.detalle.lote.descarga", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.descarga.lote.fallido"),
        PROVEEDORES_COMPROBANTE_LOTE_DESCARGA_EXITOSO("proveedores.descarga.comprobante", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.descarga.comprobante.exitoso"),
        PROVEEDORES_COMPROBANTE_LOTE_DESCARGA_FALLIDO("proveedores.descarga.comprobante", "ComunesController", "pago_individual", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.descarga.comprobante.fallido"),
        PAGOS_SERVICIOS("pagos.servicios", "AccesPolicy", "index", "/sec/Pagos/Servicios/", "com.bod.controller", "PagosServiciosController", "pagos.servicios"),
        PAGOS_PROVEEDORES_MASIVO("pagos.proveedores.masivos.efectuado", "AccesPolicy", "pagos_masivos", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "pago.procesado"),
        PAGOS_PROVEEDORES_MASIVO_EXITOSO("pagos.proveedores.masivos.efectuado", "AccesPolicy", "pagos_masivos", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "pago.procesado"),
        PAGOS_PROVEEDORES_MASIVO_FALLIDO("pagos.proveedores.masivos.efectuado", "AccesPolicy", "pagos_masivos", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "pago.procesado"),
        PAGOS_PROVEEDORES_DESCARGA_MASIVO_EXITOSO("pagos.proveedores.descarga", "ComunesController", "pagos_masivos", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.masivos.descarga.exitoso"),
        PAGOS_PROVEEDORES_DESCARGA_MASIVO_FALLIDO("pagos.proveedores.descarga", "ComunesController", "pagos_masivos", "/sec/Pagos/Proveedores/", "com.bod.controller", "NominaController", "proveedores.masivos.descarga.fallido"),
        PAGOS_SERVICIOS_APROBADO("pagos.servicios.proceso", "ServiciosDao", "index", "/sec/Pagos/Servicios/", "com.bod.controller", "PagosServiciosController", "pagos.servicios.aprobado"),
        PAGOS_SERVICIOS_RECHAZADO("pagos.servicios.proceso", "ServiciosDao", "index", "/sec/Pagos/Servicios/", "com.bod.controller", "PagosServiciosController", "pagos.servicios.rechazado"),
        PAGOS_SERVICIOS_PENDIENTE("pagos.servicios.proceso", "ServiciosDao", "index", "/sec/Pagos/Servicios/", "com.bod.controller", "PagosServiciosController", "pagos.servicios.pendiente"),
        PAGOS_SERVICIOS_ERROR("pagos.servicios.proceso", "ServiciosDao", "index", "/sec/Pagos/Servicios/", "com.bod.controller", "PagosServiciosController", "pagos.servicios.error"),
        PAGOS_SERVICIOS_NUEVO_PRODUCTO("pagos.servicios.nuevo.producto", "", "index", "/sec/Pagos/Servicios/", "com.bod.controller", "PagosServiciosController", "pagos.servicios.nuevo.producto"),
        PAGOS_SERVICIOS_NUEVO_PRODUCTO_TERCERO("pagos.servicios.nuevo.producto.tercero", "", "index", "/sec/Pagos/Servicios/", "com.bod.controller", "PagosServiciosController", "pagos.servicios.nuevo.producto"),
        PAGOS_SERVICIOS_NUEVO_BENEFICIARIO("pagos.servicios.nuevo.beneficiario", "", "index", "/sec/Pagos/Servicios/", "com.bod.controller", "PagosServiciosController", "pagos.nuevo.beneficiario"),
        PAGOS_TRIBUTOS("pagos.impuestos", "AccesPolicy", "index", "/sec/Pagos/Tributos/", "com.bod.controller", "PagosServiciosController", "pagos.tributos"),
        PAGOS_TRIBUTOS_APROBADO("pagos.impuestos.proceso", "AccesPolicy", "index", "/sec/Pagos/Tributos/", "com.bod.controller", "PagosServiciosController", "pagos.tributos.aprobado"),
        PAGOS_TRIBUTOS_RECHAZADO("pagos.impuestos.proceso", "AccesPolicy", "index", "/sec/Pagos/Tributos/", "com.bod.controller", "PagosServiciosController", "pagos.tributos.rechazado"),
        PAGOS_TRIBUTOS_ERROR("pagos.impuestos.proceso", "AccesPolicy", "index", "/sec/Pagos/Tributos/", "com.bod.controller", "PagosServiciosController", "pagos.tributos.error"),
        PAGOS_TRIBUTOS_NUEVO_PRODUCTO("pagos.impuestos.nuevo.producto", "", "index", "/sec/Pagos/Tributos/", "com.bod.controller", "PagosServiciosController", "pagos.tributos.nuevo.producto"),
        PAGOS_TRIBUTOS_NUEVO_PRODUCTO_TERCERO("pagos.impuestos.nuevo.producto.tercero", "", "index", "/sec/Pagos/Tributos/", "com.bod.controller", "PagosServiciosController", "pagos.tributos.nuevo.producto"),
        PAGOS_TRIBUTOS_NUEVO_BENEFICIARIO("pagos.impuestos.nuevo.beneficiario", "", "index", "/sec/Pagos/Tributos/", "com.bod.controller", "PagosServiciosController", "pagos.nuevo.beneficiario"),
        PAGOS_TDC("pagos.tdc", "AccesPolicy", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", "pre.tdc"),
        PAGOS_TDC_RECHAZO("pagos.tdc", "AccesPolicy", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", "mensaje.rechazo.transaccion"),
        PAGOS_TDC_MISMO_BANCO("pago.tdc.mb", "AccesPolicy", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", ""),
        PAGOS_TDC_NUEVO_BENEFICIARIO("nuevo.beneficiario.pagotdc", "AccesPolicy", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", ""),
        PAGOS_TDC_NUEVO_PRODUCTO("nuevo.producto.pagotdc", "AccesPolicy", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", ""),
        PAGOS_TDC_TERCERO_OB("pago.tercero.tdc.ob", "TdcDAO", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", "pago.tercero.tdc.ob"),
        PAGOS_TDC_TERCERO_MB("pago.tercero.tdc.mb", "TdcDAO", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", "pago.tercero.tdc.mb"),
        PAGOS_TDC_PROPIO_MB("pago.propio.tdc.mb", "TdcDAO", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", "pago.propio.tdc.mb"),
        PAGOS_TDC_PROPIO_OB("pago.propio.tdc.ob", "TdcDAO", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", "pago.propio.tdc.ob"),
        PAGOS_TDC_NUEVO_BENEFICIARIO_GUARDAR("nuevo.beneficiario.pagotdc", "TdcDAO", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", "nuevo.beneficiario.pago.tdc.guardar"),
        PAGOS_TDC_NUEVO_BENEFICIARIO_NO_GUARDAR("nuevo.beneficiario.pagotdc", "TdcDAO", "index", "/sec/Pagos/Tarjetas/", "com.bod.controller", "PagosTdcController", "nuevo.beneficiario.pago.tdc.no.guardar"),
        PERSONALIZACION("personalizacion", "AccesPolicy", "menu_personalizacion", "/sec/Personalizacion/menu_personalizacion", "com.bod.controller", "PersonalizacionController", "modulo.personalizacion"),
        PERSONALIZACION_CAMBIO_AVATAR("personalizacion.datos.usuario", "AccesPolicy", "datos_usuario", "/sec/Personalizacion/datos_usuario", "com.bod.controller", "PersonalizacionController", "personalizacion.cambio.avatar"),
        PERSONALIZACION_USUARIO("personalizacion.datos.usuario", "AccesPolicy", "datos_usuario", "/sec/Personalizacion/datos_usuario", "com.bod.controller", "PersonalizacionController", "personalizacion.datos.usuario"),
        PERSONALIZACION_IDIOMA("personalizacion.idioma", "AccesPolicy", "idioma", "/sec/Personalizacion/idioma", "com.bod.controller", "PersonalizacionController", "personalizacion.datos.idioma"),
        PERSONALIZACION_CAMBIO_IDIOMA_EXITOSO("personalizacion.idioma", "AccesPolicy", "idioma", "/sec/Personalizacion/idioma", "com.bod.controller", "PersonalizacionController", "personalizacion.datos.idioma.exitoso"),
        PERSONALIZACION_CAMBIO_IDIOMA_FALLIDO("personalizacion.idioma", "AccesPolicy", "idioma", "/sec/Personalizacion/idioma", "com.bod.controller", "PersonalizacionController", "personalizacion.datos.idioma.fallido"),
        PERSONALIZACION_PAGINAS("personalizacion.pag_inicio", "AccesPolicy", "pagina_inicio", "/sec/Personalizacion/pagina_inicio", "com.bod.controller", "PersonalizacionController", "personalizacion.pag_inicio"),
        PERSONALIZACION_PAGINAS_CAMBIO_EXITOSO("personalizacion.pag_inicio", "AccesPolicy", "pagina_inicio", "/sec/Personalizacion/pagina_inicio", "com.bod.controller", "PersonalizacionController", "personalizacion.pag_inicio.exitoso"),
        PERSONALIZACION_PAGINAS_CAMBIO_FALLIDO("personalizacion.pag_inicio", "AccesPolicy", "pagina_inicio", "/sec/Personalizacion/pagina_inicio", "com.bod.controller", "PersonalizacionController", "personalizacion.pag_inicio"),
        PERSONALIZACION_ALIAS("personalizacion.alias", "AccesPolicy", "alias_producto", "/sec/Personalizacion/alias_producto", "com.bod.controller", "PersonalizacionController", "personalizacion.alias"),
        PERSONALIZACION_ALIAS_CAMBIO_EXITOSO("personalizacion.alias", "AccesPolicy", "alias_producto", "/sec/Personalizacion/alias_producto", "com.bod.controller", "PersonalizacionController", "personalizacion.alias.exitoso"),
        PERSONALIZACION_ALIAS_CAMBIO_FALLIDO("personalizacion.alias", "AccesPolicy", "alias_producto", "/sec/Personalizacion/alias_producto", "com.bod.controller", "PersonalizacionController", "personalizacion.alias.fallido"),
        RECLAMOS("consulta.reclamos", "AccesPolicy", "index", "/sec/Reclamos/", "com.bod.controller", "ReclamoController", "modulo.reclamo"),
        SOLICITUD_CREAR_RECLAMO_EXITOSO("solicitudes.registro.reclamo", "ReclamoDAO", "detalle_cuentas", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "reclamos.crear.exitoso"),
        SOLICITUD_CREAR_RECLAMO_FALLIDO("solicitudes.registro.reclamo", "ReclamoDAO", "detalle_cuentas", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "reclamos.crear.fallido"),
        SOLICITUD_RECLAMO_DESCARGAR_COMPROBANTE("solicitudes.registro.reclamo", "ReclamoDAO", "confirmar_reclamo", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "reclamos.crear.descargar.comprobante"),
        SOLICITUD_RECLAMO_DESCARGAR_SOLICITUD("solicitudes.registro.reclamo", "ReclamoDAO", "confirmar_reclamo", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "reclamos.crear.descargar.solicitud"),
        SOLICITUD_RECLAMO_DESCARGAR_RECAUDOS("solicitudes.registro.reclamo", "ReclamoDAO", "confirmar_reclamo", "/sec/Cuentas/", "com.bod.controller", "CuentasController", "reclamos.crear.descargar.recaudo"),
        ACTIVACION_CHEQUERA("solicitudes.activacion.chequera", "AccesPolicy", "index", "/sec/Solicitudes/ActivacionChequeras/", "com.bod.controller", "ActivacionChequeraController", "activacion.chequera.transaccion"),
        ACTIVACION_CHEQUERA_EXITOSO("solicitudes.activacion.chequera.efectuado", "AccesPolicy", "index", "/sec/Solicitudes/ActivacionChequeras/", "com.bod.controller", "ActivacionChequeraController", "solicitudes.activacion.chequera.exitoso"),
        ACTIVACION_CHEQUERA_RECHAZADA("solicitudes.activacion.chequera.efectuado", "AccesPolicy", "index", "/sec/Solicitudes/ActivacionChequeras/", "com.bod.controller", "ActivacionChequeraController", "solicitudes.activacion.chequera.rechazada"),
        SUSPENSION_CHEQUERA("solicitudes.suspension.chequera", "AccesPolicy", "index", "/sec/Solicitudes/SuspensionChequeras/", "com.bod.controller", "SuspensionChequeraController", "modulo.suspension.chequera"),
        SUSPENSION_CHEQUERA_EXITOSO("solicitudes.suspension.chequera.ejecutado", "AccesPolicy", "index", "/sec/Solicitudes/SuspensionChequeras/", "com.bod.controller", "SuspensionChequeraController", "solicitudes.suspension.chequera.exitoso"),
        SUSPENSION_CHEQUERA_RECHAZADO("solicitudes.suspension.chequera.ejecutado", "AccesPolicy", "index", "/sec/Solicitudes/SuspensionChequeras/", "com.bod.controller", "SuspensionChequeraController", "solicitudes.suspension.chequera.rechazado"),
        SUSPENSION_CHEQUES("solicitudes.suspension.cheque", "AccesPolicy", "index", "/sec/Solicitudes/SuspensionCheques/", "com.bod.controller", "SuspensionChequeController", "pre.suspension.cheque"),
        SUSPENSION_CHEQUE_EJECUTAR("solicitudes.suspension.cheque.ejecutado", "ChequerasImpl", "index", "/sec/Solicitudes/SuspensionCheques/", "com.bod.controller", "SuspensionChequeController", "solicitudes.suspension.cheque.ejecutado"),
        SUSPENSION_CHEQUE_RECHAZADO("solicitudes.suspension.cheque.ejecutado", "ChequerasImpl", "index", "/sec/Solicitudes/SuspensionCheques/", "com.bod.controller", "SuspensionChequeController", "solicitudes.suspension.cheque.rechazado"),
        SUSPENSION_TDD("solicitudes.suspension.tdd", "AccesPolicy", "index", "/sec/Solicitudes/SuspensionTdd/", "com.bod.controller", "SuspensionTddController", "modulo.suspension.tdd"),
        SUSPENSION_TDD_EXITOSO("solicitudes.suspension.tdd.ejecutado", "AccesPolicy", "index", "/sec/Solicitudes/SuspensionTdd/", "com.bod.controller", "SuspensionTddController", "solicitudes.suspension.tdd.exitoso"),
        SOLICITUD_REFERENCIA_TDC("solicitudes.referencias.efecutado", "TdcDao", "index", "/sec/Tarjetas/", "com.bod.controller", "TdcController", "solicitud.referencia.tdc"),
        SOLICITUD_REFERENCIA_CUENTA("solicitudes.referencias.efecutado", "CuentaDao", "index", "/sec/Tarjetas/", "com.bod.controller", "CuentasController", "solicitud.referencia.cuenta"),
        SOLICITUD_REFERENCIA_CUENTA_DIVISAS("solicitudes.referencias.divisas", "CuentaDao", "index", "/sec/Tarjetas/", "com.bod.controller", "CuentasController", "solicitud.referencia.cuenta.divisas"),
        TARJETAS("consulta.tarjetas", "AccesPolicy", "index", "/sec/Tarjetas/", "com.bod.controller", "TdcController", "tarjetas.credito"),
        TARJETAS_MOVIMIENTO("movimientos.tdc", "TdcDao", "index", "/sec/Tarjetas/", "com.bod.controller", "TdcController", "movimientos.tdc"),
        DETALLE_TARJETAS("detalle.tdc", "AccesPolicy", "detalle_tarjetas", "/sec/Tarjetas/", "com.bod.controller", "TdcController", "detalle.tdc"),
        MEMBERSHIP_REWARDS("membership.rewards.tdc", "AccesPolicy", "membershipRewards", "/sec/Tarjetas/membershipRewards", "com.bod.controller", "TdcController", "membership.rewards.tdc"),
        RECAUDACION("consulta.recaudacion", "AccesPolicy", "menu_recaudacion", "/sec/Recaudacion/menu_recaudacion", "com.bod.controller", "RecaudacionController", "recaudacion.modulo"),
        RECAUDACION_COBRANZA_INTEGRAL("recaudacion.cobranza.integral", "AccesPolicy", "cobranzaIntegral", "/sec/Recaudacion/cobranzaIntegral", "com.bod.controller", "RecaudacionController", "recaudacion.cobranza.integral"),
        RECAUDACION_COBRANZA_INTEGRAL_DESCARGA_CONSULTA("cobranza.integral.descarga.efectuado", "AccesPolicy", "cobranzaIntegral", "/sec/Recaudacion/cobranzaIntegral", "com.bod.controller", "RecaudacionController", "cobranza.integral.descarga.consulta"),
        RECAUDACION_COBRANZA_INTEGRAL_DESCARGA_DETALLE("cobranza.integral.descarga.efectuado", "AccesPolicy", "cobranzaIntegral", "/sec/Recaudacion/cobranzaIntegral", "com.bod.controller", "RecaudacionController", "cobranza.integral.descarga.detalle"),
        RECAUDACION_COBRANZA_INTEGRAL_DESCARGA_ARCHIVO_EXITOSO("cobranza.integral.descarga.efectuado", "AccesPolicy", "cobranzaIntegral", "/sec/Recaudacion/cobranzaIntegral", "com.bod.controller", "RecaudacionController", "cobranza.integral.descarga.archivo.exitoso"),
        RECAUDACION_COBRANZA_INTEGRAL_DESCARGA_ARCHIVO_FALLIDO("cobranza.integral.descarga.efectuado", "AccesPolicy", "cobranzaIntegral", "/sec/Recaudacion/cobranzaIntegral", "com.bod.controller", "RecaudacionController", "cobranza.integral.descarga.archivo.fallido"),
        RECAUDACION_PAGOS_LINEA("recaudacion.pagos.linea", "AccesPolicy", "pagos_linea", "/sec/Recaudacion/pagos_linea", "com.bod.controller", "RecaudacionController", "recaudacion.pagos.linea"),
        RECAUDACION_PAGOS_LINEA_DESCARGA("recaudacion.pagos.linea.descarga.efectuado", "AccesPolicy", "pagos_linea", "/sec/Recaudacion/pagos_linea", "com.bod.controller", "RecaudacionController", "recaudacion.pagos.linea.descarga"),
        DESAFILIACION_BOD_MOVIL("solicitudes.bodmovil.desafiliacion", "AccesPolicy", "index", "/sec/Solicitudes/DesafiliacionBodMovil/index", "com.bod.controller.", "DesafiliacionBodMovilController", "bodmovil.desafiliacion.modulo"),
        MODIFICACION_BOD_MOVIL("solicitudes.bodmovil.modificacion", "BodMobileDAO", "index", "/sec/Solicitudes/ModificacionBodMovil/index", "com.bod.controller", "ModificacionBodMovilController", "bodmovil.titulo.modificacion"),
        CONFIGURAR_NOTIFICACIONES_NBL("configurar.notificaciones.nbl", "AccesPolicy", "index", "/sec/ConfiguracionNotificaciones/index", "com.bod.controller", "NotificacionesNblController", "configurar.notificaciones.nbl"),
        CONFIGURAR_NOTIFICACIONES_NBL_COMPROBANTE_EXITOSO("configurar.notificaciones.nbl", "AccesPolicy", "resultado", "/sec/ConfiguracionNotificaciones/resultado", "com.bod.controller", "NotificacionesNblController", "notif.comp.configuracion.exito"),
        CONFIGURAR_NOTIFICACIONES_NBL_COMPROBANTE_FALLIDO("configurar.notificaciones.nbl", "AccesPolicy", "resultado", "/sec/ConfiguracionNotificaciones/resultado", "com.bod.controller", "NotificacionesNblController", "notif.comp.configuracion.fallido"),
        RECAUDACION_RIG("consulta.recaudacion.rig", "AccesPolicy", "RecaudacionRIG", "/sec/Recaudacion/RecaudacionRIG", "com.bod.controller", "RecaudacionRIGController", "recaudacion.rig"),
        RECAUDACION_RIG_ARCHIVO_DESCARGA_EXITOSO("consulta.recaudacion.rig.descarga.efectuado", "AccesPolicy", "RecaudacionRIG", "/sec/Recaudacion/RecaudacionRIG", "com.bod.controller", "RecaudacionRIGController", "consulta.recaudacion.rig.descarga.exitoso"),
        RECAUDACION_RIG_ARCHIVO_DESCARGA_FALLIDA("consulta.recaudacion.rig.descarga.efectuado", "AccesPolicy", "RecaudacionRIG", "/sec/Recaudacion/RecaudacionRIG", "com.bod.controller", "RecaudacionRIGController", "consulta.recaudacion.rig.descarga.fallido"),
        RECAUDACION_SIRIS("consulta.siris.index", "AccesPolicy", "index", "/sec/Siris/index", "com.bod.controller", "SirisController", "siris.modulo"),
        RECAUDACION_SIRIS_LISTADO_RECAUDACION("consulta.siris.index", "AccesPolicy", "index", "/sec/Siris/index", "com.bod.controller", "SirisController", "siris.listado.recaudacion"),
        DESCARGA_ARCHIVO_SIRIS("consulta.siris.descarga.efectuado", "ReporteBean", "index", "/sec/Siris/index", "com.bod.controller", "SirisController", "consulta.siris.descarga.consulta"),
        DESCARGA_ARCHIVO_SIRIS_CONSULTA_EXITOSO("consulta.siris.descarga.efectuado", "ReporteBean", "index", "/sec/Siris/index", "com.bod.controller", "SirisController", "consulta.siris.descarga.archivo.exitoso"),
        DESCARGA_ARCHIVO_SIRIS_CONSULTA_FALLIDO("consulta.siris.descarga.efectuado", "ReporteBean", "index", "/sec/Siris/index", "com.bod.controller", "SirisController", "consulta.siris.descarga.archivo.fallido"),
        SOFTOKEN("solicitudes.activacion.softtoken", "TokenDAO", "factoresAutenticacionComp", "resources/bodcomp/", "com.bod.controller", "ActivarSoftTokenController", "activacion.softtoken"),
        DESACTIVACION_SOFTOKEN("solicitudes.activacion.softtoken", "TokenDAO", "factoresAutenticacionComp", "resources/bodcomp/", "com.bod.controller", "ActivarSoftTokenController", "desactivacion.softtoken"),
        VALIDACION_OTP_EXITOSA("zona.segura.validada", "RootZonaSegura", "index", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "ZonaSeguraController", "validacion.otp.exitosa"),
        ZONA_SEGURA("root.zona.segura", "RootZonaSegura", "index", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "ZonaSeguraController", "zona.segura.ingreso"),
        ZONA_SEGURA_SOFT_TOKEN("root.zona.segura.soft.token", "ComunesController", "RootZonaSegura", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "OtpsController", "zona.segura.soft.token"),
        ZONA_SEGURA_SMS("root.zona.segura.sms", "ComunesController", "RootZonaSegura", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "OtpsController", "zona.segura.sms"),
        ZONA_SEGURA_EMAIL("root.zona.segura.email", "AccesPolicy", "RootZonaSegura", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "OtpsController", "zona.segura.email"),
        ZONA_SEGURA_TARJETA_COORDENADAS("root.zona.segura.tarjeta", "RootZonaSegura", "index", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "TarjetasCoordenadasController", "zona.segura.tarjeta.coordenadas"),
        ZONA_SEGURA_SOFTTOKEN("root.zona.segura.softoken", "RootZonaSegura", "ZonaSegura", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "SoftTokenController", "zona.segura.softtoken"),
        ZONA_SEGURA_VALIDADA("zona.segura.validada", "RootZonaSegura", "ComunesController", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "SoftTokenController", "zonasegura.validacion"),
        ZONA_SEGURA_INGRESO("root.zona.segura", "RootZonaSegura", "index", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "ZonaSeguraController", "zona.segura.ingreso"),
        ZONA_SEGURA_CANCELA("root.zona.segura.cancela", "RootZonaSegura", "index", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "ZonaSeguraController", "zona.segura.cancela"),
        VALIDACION_POSITIVA_EXITOSA("validacion.positiva", "WSValidacionPositiva", "afiliacion_3_validacion_positiva", "/ext/Afiliacion/afiliacion_3_validacion_positiva", "com.bod.controller", "ValidacionPositivaController", "validacion.positiva.exitosa"),
        IMAGEN_SEGURIDAD("imagen.seguridad.seleccionada", "AccesPolicy", "cambiar_imagen", "/sec/ConfiguracionSeguridad/cambiar_imagen", "com.bod.controller.", "CambioImagenController", "imagen.seguridad"),
        /*Sección para el módulo de SIMADI */
        SIMADI("divisas.simadi", "AccesPolicy", "index", "/sec/Divisas/simadi", "com.bod.controller", "SimadiController", "modulo.simadi"),
        SIMADI_ORDEN_GENERADA("pago.orden.divisa", "AccesPolicy", "index", "/sec/Divisas/simadi", "com.bod.controller", "SimadiController", "modulo.simadi.orden.generada.exito"),
        SIMADI_ORDEN_NO_GENERADA("pago.orden.divisa", "AccesPolicy", "index", "/sec/Divisas/simadi", "com.bod.controller", "SimadiController", "modulo.simadi.orden.generada.fallido"),
        SIMADI_DESCARGA_OFERTA("simadi.descarga.oferta", "AccesPolicy", "index", "/sec/Divisas/simadi", "com.bod.controller", "SimadiController", "modulo.simadi.descarga.oferta.exitoso"),
        SIMADI_DESCARGA_OFERTA_FALLIDO("simadi.descarga.oferta", "AccesPolicy", "index", "/sec/Divisas/simadi", "com.bod.controller", "SimadiController", "modulo.simadi.descarga.oferta.fallido"),
        SIMADI_DESCARGA_COMPROBANTE("simadi.descarga.comprobante", "AccesPolicy", "index", "/sec/Divisas/simadi", "com.bod.controller", "SimadiController", "modulo.simadi.descarga.comprobante.exitoso"),
        SIMADI_DESCARGA_COMPROBANTE_FALLIDO("simadi.descarga.comprobante", "AccesPolicy", "index", "/sec/Divisas/simadi", "com.bod.controller", "SimadiController", "modulo.simadi.descarga.comprobante.fallido"),
        SIMADI_DESCARGA_FORMATO("simadi.descarga.formato", "AccesPolicy", "index", "/sec/Divisas/simadi", "com.bod.controller", "SimadiController", "modulo.simadi.descarga.formato.exitoso"),
        SIMADI_DESCARGA_FORMATO_FALLIDO("simadi.descarga.formato", "AccesPolicy", "index", "/sec/Divisas/simadi", "com.bod.controller", "SimadiController", "modulo.simadi.descarga.formato.fallido"),
        /*Consulta de encuestas*/
        ENCUESTAS("consulta.encuestas", "AccesPolicy", "index", "/sec/Encuestas/", "com.bod.controller", "EncuestasController", "encuestas.modulo"),
        //  TRANSFERENCIA.
        TRANSFERENCIA_MISMO_BANCO("transferencias.mb", "CuentasDAO", "index", "sec/Transferencias/", "com.bod.controller", "TransferenciasController", "transferencia.mismo.banco"),
        TRANSFERENCIA_OTRO_BANCO("transferencias.ob", "CuentasDAO", "index", "sec/Transferencias/", "com.bod.controller", "TransferenciasController", "transferencia.otro.banco"),
        TRANSFERENCIAS_NUEVO_BENEFICIARIO_GUARDAR("nuevo.beneficiario.transf", "TransferenciasDAO", "index", "sec/Transferencias/", "com.bod.controller", "TransferenciasController", "nuevo.beneficiario.transferencia.guardar"),
        TRANSFERENCIAS_NUEVO_BENEFICIARIO_NO_GUARDAR("nuevo.beneficiario.transf", "TransferenciasDAO", "index", "sec/Transferencias/", "com.bod.controller", "TransferenciasController", "nuevo.beneficiario.transferencia.no.guardar"),
        /*Punto de Venta*/
        LIQUIDACION_ESTABLECIMIENTO("liquidacion.establecimiento", "AccesPolicy", "index", "sec/LiquidacionEstablecimiento/", "com.bod.controller", "EstablecimientoController", "liquidacion.establecimiento.ingreso"),
        LIQUIDACION_ESTABLECIMIENTO_MOVIMIENTO("liquidacion.establecimiento", "AccesPolicy", "index", "sec/LiquidacionEstablecimiento/", "com.bod.controller", "EstablecimientoController", "liq.est.ingreso.movimiento"),
        LIQUIDACION_ESTABLECIMIENTO_CONSULTA("liquidacion.establecimiento", "AccesPolicy", "index", "sec/LiquidacionEstablecimiento/", "com.bod.controller", "EstablecimientoController", "liquidacion.establecimiento.consulta"),
        LIQUIDACION_ESTABLECIMIENTO_CONSULTA_MOVIMIENTO("liquidacion.establecimiento", "AccesPolicy", "index_movimiento", "sec/LiquidacionEstablecimiento/", "com.bod.controller", "EstablecimientoController", "liq.est.consulta.movimiento"),
        LIQUIDACION_ESTABLECIMIENTO_CONSULTA_VER_DETALLE("liquidacion.establecimiento", "AccesPolicy", "index", "sec/LiquidacionEstablecimiento/", "com.bod.controller", "EstablecimientoController", "liq.est.consulta.ver.detalle"),
        LIQUIDACION_ESTABLECIMIENTO_DESCARGA_CONSULTA("liquidacion.establecimiento", "AccesPolicy", "index", "sec/LiquidacionEstablecimiento/", "com.bod.controller", "EstablecimientoController", "liquidacion.establecimiento.descarga.consulta"),
        LIQUIDACION_ESTABLECIMIENTO_DESCARGA_CONSULTA_MOVIMIENTO("liquidacion.establecimiento", "AccesPolicy", "index", "sec/LiquidacionEstablecimiento/", "com.bod.controller", "EstablecimientoController", "liq.est.descarga.consulta.movimiento"),
        LIQUIDACION_ESTABLECIMIENTO_DESCARGA_TODAS_CONSULTA("liquidacion.establecimiento", "AccesPolicy", "index", "sec/LiquidacionEstablecimiento/", "com.bod.controller", "EstablecimientoController", "liq.est.descarga.all.consulta"),
        CONSULTA_VIRTUAL_TRN("consultas.virtual.trn", "TransVirtualesDAO", "index", "sec/ConsultaVirtualTrn/", "com.bod.controller", "ConsultaVirtualTrnController", "consulta.virtual.trn"),
        CONSULTA_VIRTUAL_TRN_DESCARGA("consultas.virtual.trn", "TransVirtualesDAO", "index", "sec/ConsultaVirtualTrn/", "com.bod.controller.", "ConsultaVirtualTrnController", "consulta.virtual.trn.descarga"),
        ACTUALIZACION_DATOS_USUARIO("actualizacion.datos.usuario", "ClientesDAO", "index", "/sec/ActualizacionDatosUsuario/", "com.bod.controller", "PersonalizacionController", "descripcion.actualizacion.datos"),
        ACTUALIZACION_DATOS_USUARIO_CORREO_EXITOSO("actualizacion.datos.usuario.efectuado", "ClientesDAO", "index", "/sec/ActualizacionDatosUsuario/", "com.bod.controller", "PersonalizacionController", "personalizacion.datos.usuario.correo"),
        ACTUALIZACION_DATOS_USUARIO_TELEFONO_EXITOSO("actualizacion.datos.usuario.efectuado", "ClientesDAO", "index", "/sec/ActualizacionDatosUsuario/", "com.bod.controller", "PersonalizacionController", "personalizacion.datos.usuario.telefono"),
        ACTUALIZACION_DATOS_USUARIO_CORREO_RECHAZADO("actualizacion.datos.usuario.efectuado", "ClientesDAO", "index", "/sec/ActualizacionDatosUsuario/", "com.bod.controller", "PersonalizacionController", "personalizacion.datos.usuario.correo.rechazo"),
        ACTUALIZACION_DATOS_USUARIO_TELEFONO_RECHAZADO("actualizacion.datos.usuario.efectuado", "ClientesDAO", "index", "/sec/ActualizacionDatosUsuario/", "com.bod.controller", "PersonalizacionController", "personalizacion.datos.usuario.tlf.rechazo"),
        ACTUALIZACION_DATOS_USUARIO_DESCARGA("actualizacion.datos.usuario", "ClientesDAO", "index", "/sec/ActualizacionDatosUsuario/", "com.bod.controller.", "PersonalizacionController", "actualizacion.datos.descarga"),
        FLUJO_APROBACION_ELIMINAR("flujo.aprobacion.eliminar", "Cnfflujos", "edicion", "/sec/FlujoAprobacion/", "com.bod.controller", "FlujoAprobacionController", "flujo.aprobacion.bitacora.eliminar"),
        FLUJO_APROBACION_AGREGAR("flujo.aprobacion.agregar", "Cnfflujos", "edicion", "/sec/FlujoAprobacion/", "com.bod.controller", "FlujoAprobacionController", "flujo.aprobacion.bitacora.agregar"),
        FLUJO_APROBACION_MODIFICAR("flujo.aprobacion.modificar", "Cnfflujos", "edicion", "/sec/FlujoAprobacion/", "com.bod.controller", "FlujoAprobacionController", "flujo.aprobacion.bitacora.modificar"),
        FLUJO_APROBACION_APROBAR("flujo.aprobacion.aprobar", "Cnfflujos", "aprobar", "/sec/FlujoAprobacion/", "com.bod.controller", "FlujoAprobacionController", "flujo.aprobacion.bitacora.aprobar"),
        FLUJO_APROBACION_APROBAR_EXITOSO("flujo.aprobacion.aprobado", "Cnfflujos", "aprobar", "/sec/FlujoAprobacion/", "com.bod.controller", "FlujoAprobacionController", "flujo.aprobacion.exitoso"),
        FLUJO_APROBACION_APROBAR_FALLIDO("flujo.aprobacion.aprobado", "Cnfflujos", "aprobar", "/sec/FlujoAprobacion/", "com.bod.controller", "FlujoAprobacionController", "flujo.aprobacion.fallido"),
        ELIMINAR_PREGUNTAS_SEGURIDAD("eliminar.preguntas.seguridad", "N/A", "aprobar", "/sec/FlujoAprobacion/", "com.bod.controller", "ComunesController", "eliminar.preguntas.seguridad"),
        ELIMINAR_PREGUNTAS_SEGURIDAD_WBFD("eliminar.preguntas.seguridad", "N/A", "aprobar", "/sec/FlujoAprobacion/", "com.bod.controller", "ComunesController", "eliminar.preguntas.seguridad.wbfd"),
        INICIO_SESSION("inicio.sesion.exitoso", "UsuarioNblFacade", "login_6_selecPerfil", "/ext/Login/", "com.bod.controller", "LoginController", "inicio.session"),
        ERROR_SESSION("inicio.sesion.fallido", "UsuarioNblFacade", "login_6_selecPerfil", "/ext/Login/", "com.bod.controller", "LoginController", "error.inicio.session"),
        FIN_SESSION("fin.sesion", "SesionesNblFacade", "interna", "/templates/", "com.bod.controller", "OtpsController", "message.general.session.finish"),
        RECUPERACION_USUARIO("recuperacion.usuario", "ClientesDAO.java", "index", "/ext/RecuperacionUsuario/", "com.bod.controller", "RecuperacionUsuarioController", "regeneracion.usuario.listo"),
        AFILIACION_BOD_MOVIL_EXITOSA("bodmovil.afiliacion.exitosa", "BodMobileDAO", "index", "/sec/Solicitudes/AfiliacionBodMovil/index", "com.bod.controller.", "AfiliacionBodMovilController", "bodmovil.afiliacion.procesada"),
        MODIFICACION_BOD_MOVIL_EXITOSA("bodmovil.modificacion.efectuada", "AccesPolicy", "index", "/sec/Solicitudes/ModificacionBodMovil/index", "com.bod.controller", "ModificacionBodMovilController", "bodmovil.modificacion.procesada"),
        MODIFICACION_BOD_MOVIL_FALLIDO("bodmovil.modificacion.efectuada", "AccesPolicy", "index", "/sec/Solicitudes/ModificacionBodMovil/index", "com.bod.controller", "ModificacionBodMovilController", "bodmovil.modificacion.fallido"),
        TARJETA_COORDENADAS_EXITOSA("tarjetas.coordenadas.exitoso", "DetectIDDAO", "tarjetaCoordenadas", "/resources/bodcomp/", "com.bod.controller", "TarjetaCoordenadasController", "tarjetas.coordenadas.exitoso"),
        TARJETA_COORDENADAS_RECHAZADA("tarjetas.coordenadas.rechazada", "DetectIDDAO", "tarjetaCoordenadas", "/resources/bodcomp/", "com.bod.controller.", "TarjetaCoordenadasController", "tarjetas.coordenadas.rechazada"),
        DESAFILIACION_BOD_MOVIL_EXITOSA("bodmovil.desafiliacion.exitosa", "BodMobileDAO", "index", "/sec/Solicitudes/DesafiliacionBodMovil/index", "com.bod.controller.", "DesafiliacionBodMovilController", "bodmovil.desafiliacion.procesada"),
        DOMICILIACIONES("domiciliaciones", "ClientesDAO.java", "menu_domiciliaciones", "/sec/Pagos/Domiciliaciones/menu_domiciliaciones", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.modulo"),
        DOMICILIACIONES_CONSULTA_DESCARGA_EXITOSO("domiciliaciones.consulta.descarga", "ClientesDAO.java", "menu_domiciliaciones", "/sec/Pagos/Domiciliaciones/menu_domiciliaciones", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.consulta.descarga.exito"),
        DOMICILIACIONES_CONSULTA_DESCARGA_FALLIDO("domiciliaciones.consulta.descarga", "ClientesDAO.java", "menu_domiciliaciones", "/sec/Pagos/Domiciliaciones/menu_domiciliaciones", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.consulta.descarga.fallido"),
        DOMICILIACIONES_PAGO_MASIVO("domiciliaciones.registro.masivo", "ClientesDAO.java", "registro_masivo", "/sec/Pagos/Domiciliaciones/registro_masivo", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.pago.masivo"),
        DOMICILIACIONES_PAGO_MASIVO_CARGA_LOTE_EXITOSO("domiciliaciones.carga.lote.masivo", "ClientesDAO.java", "registro_individual", "/sec/Pagos/Domiciliaciones/registro_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.carga.lote.masivo.exitoso"),
        DOMICILIACIONES_PAGO_MASIVO_CARGA_LOTE_FALLIDO("domiciliaciones.carga.lote.masivo", "ClientesDAO.java", "registro_individual", "/sec/Pagos/Domiciliaciones/registro_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.carga.lote.masivo.fallido"),
        DOMICILIACIONES_PAGO_MASIVO_COMPROBANTE_EXITOSO("domiciliaciones.comprobante.pago.masivo", "ReporteBean.java", "registro_masivo", "/sec/Pagos/Domiciliaciones/registro_masivo", "com.bod.controller", "DomiciliacionesController", "domiciliacion.comprobante.masivo.exitoso"),
        DOMICILIACIONES_PAGO_MASIVO_COMPROBANTE_FALLIDO("domiciliaciones.comprobante.pago.masivo", "ReporteBean.java", "registro_masivo", "/sec/Pagos/Domiciliaciones/registro_masivo", "com.bod.controller", "DomiciliacionesController", "domiciliacion.comprobante.masivo.fallido"),
        DOMICILIACIONES_PAGO_INDIVIDUAL("domiciliaciones.registro.individual", "ClientesDAO.java", "registro_individual", "/sec/Pagos/Domiciliaciones/registro_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.pago.individual"),
        DOMICILIACIONES_PAGO_INDIVIDUAL_CARGA_LOTE_EXITOSO("domiciliaciones.carga.lote.individual", "ClientesDAO.java", "registro_individual", "/sec/Pagos/Domiciliaciones/registro_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.carga.lote.individual.exitoso"),
        DOMICILIACIONES_PAGO_INDIVIDUAL_CARGA_LOTE_FALLIDO("domiciliaciones.carga.lote.individual", "ClientesDAO.java", "registro_individual", "/sec/Pagos/Domiciliaciones/registro_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.carga.lote.individual.fallido"),
        DOMICILIACIONES_PAGO_INDIVIDUAL_COMPROBANTE_EXITOSO("domiciliaciones.comprobante.pago.individual", "ReporteBean.java", "registro_masivo", "/sec/Pagos/Domiciliaciones/registro_masivo", "com.bod.controller", "DomiciliacionesController", "domiciliacion.comprobante.individual.exitoso"),
        DOMICILIACIONES_PAGO_INDIVIDUAL_COMPROBANTE_FALLIDO("domiciliaciones.comprobante.pago.individual", "ReporteBean.java", "registro_masivo", "/sec/Pagos/Domiciliaciones/registro_masivo", "com.bod.controller", "DomiciliacionesController", "domiciliacion.comprobante.individual.fallido"),
        DOMICILIACIONES_MONTO_VARIABLE_INDIVIDUAL("domiciliaciones.menu.montoVarInd", "ClientesDAO.java", "monto_variable_individual", "/sec/Pagos/Domiciliaciones/monto_variable_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.monto.individual"),
        DOMICILIACIONES_ACTUALIZAR_MONTO_VARIABLE_INDIVIDUAL_EXITOSO("domiciliaciones.actualizar.monto.indiv", "ClientesDAO.java", "monto_variable_individual", "/sec/Pagos/Domiciliaciones/monto_variable_individual", "com.bod.controller", "DomiciliacionesController", "domiciliacion.actualizar.monto.indiv.exitoso"),
        DOMICILIACIONES_ACTUALIZAR_MONTO_VARIABLE_INDIVIDUAL_FALLIDO("domiciliaciones.actualizar.monto.indiv", "ClientesDAO.java", "monto_variable_individual", "/sec/Pagos/Domiciliaciones/monto_variable_individual", "com.bod.controller", "DomiciliacionesController", "domiciliacion.actualizar.monto.indiv.fallido"),
        DOMICILIACIONES_MONTO_VARIABLE_INDIVIDUAL_COMPROBANTE_EXITOSO("domiciliaciones.actualizar.monto.comprobante", "ClientesDAO.java", "monto_variable_individual", "/sec/Pagos/Domiciliaciones/monto_variable_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.actualizar.monto.exitoso"),
        DOMICILIACIONES_MONTO_VARIABLE_INDIVIDUAL_COMPROBANTE_FALLIDO("domiciliaciones.actualizar.monto.comprobante", "ClientesDAO.java", "monto_variable_individual", "/sec/Pagos/Domiciliaciones/monto_variable_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.actualizar.monto.fallido"),
        DOMICILIACIONES_MONTO_VARIABLE_MASIVO("domiciliaciones.menu.montoVarMas", "ClientesDAO.java", "monto_variable_masivo", "/sec/Pagos/Domiciliaciones/monto_variable_masivo", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.monto.masivo"),
        DOMICILIACIONES_MONTO_VARIABLE_MASIVO_COMPROBANTE_EXITOSO("domiciliaciones.actualizar.monto.comprobante", "ClientesDAO.java", "monto_variable_individual", "/sec/Pagos/Domiciliaciones/monto_variable_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.actualizar.monto.exitoso"),
        DOMICILIACIONES_MONTO_VARIABLE_MASIVO_COMPROBANTE_FALLIDO("domiciliaciones.actualizar.monto.comprobante", "ClientesDAO.java", "monto_variable_individual", "/sec/Pagos/Domiciliaciones/monto_variable_individual", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.actualizar.monto.fallido"),
        DOMICILIACIONES_ACTUALIZAR_MONTO_VARIABLE_MASIVO_EXITOSO("domiciliaciones.actualizar.monto.masivo", "ClientesDAO.java", "monto_variable_individual", "/sec/Pagos/Domiciliaciones/monto_variable_individual", "com.bod.controller", "DomiciliacionesController", "domiciliacion.actualizar.monto.masivo.exitoso"),
        DOMICILIACIONES_ACTUALIZAR_MONTO_VARIABLE_MASIVO_FALLIDO("domiciliaciones.actualizar.monto.masivo", "ClientesDAO.java", "monto_variable_individual", "/sec/Pagos/Domiciliaciones/monto_variable_individual", "com.bod.controller", "DomiciliacionesController", "domiciliacion.actualizar.monto.masivo.fallido"),
        DOMICILIACIONES_CONSULTA("domiciliaciones.consulta", "ClientesDAO.java", "consulta_domiciliaciones", "/sec/Pagos/Domiciliaciones/consulta_domiciliaciones", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.consulta"),
        DOMICILIACIONES_APROBACION_DESCARGA_EXITOSO("domiciliaciones.descarga.actulizar.monto", "ReporteBean.java", "consulta_domiciliaciones", "/sec/Pagos/Domiciliaciones/consulta_domiciliaciones", "com.bod.controller", "DomiciliacionesController", "domiciliacion.descarga.actualizar.exitoso"),
        DOMICILIACIONES_APROBACION_DESCARGA_FALLIDO("domiciliaciones.descarga.actulizar.monto", "ReporteBean.java", "consulta_domiciliaciones", "/sec/Pagos/Domiciliaciones/consulta_domiciliaciones", "com.bod.controller", "DomiciliacionesController", "domiciliacion.descarga.actualizar.fallido"),
        DOMICILIACIONES_MODELO_DESCARGA_EXITOSO("domiciliaciones.descarga.modelo", "ReporteBean.java", "domiciliaciones", "/sec/Pagos/Domiciliaciones/", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.descarga.modelo.exitoso"),
        DOMICILIACIONES_MODELO_DESCARGA_FALLIDO("domiciliaciones.descarga.modelo", "ReporteBean.java", "domiciliaciones", "/sec/Pagos/Domiciliaciones/", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.descarga.modelo.fallido"),
        DOMICILIACIONES_DESCARGA_ESTADO_COMPROBANTE_EXITOSO("domiciliaciones.estado.comprobante", "ReporteBean.java", "domiciliaciones", "/sec/Pagos/Domiciliaciones/", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.estado.comprobante.exitoso"),
        DOMICILIACIONES_DESCARGA_ESTADO_COMPROBANTE_FALLIDO("domiciliaciones.estado.comprobante", "ReporteBean.java", "domiciliaciones", "/sec/Pagos/Domiciliaciones/", "com.bod.controller", "DomiciliacionesController", "domiciliaciones.estado.comprobante.fallido"),
        DOMICILIACIONES_MODIFICAR_ESTADO_EXITOSO("domiciliacion.modificar.estado", "ReporteBean.java", "domiciliaciones", "/sec/Pagos/Domiciliaciones/", "com.bod.controller", "DomiciliacionesController", "domiciliacion.modificar.estado.exitoso"),
        DOMICILIACIONES_MODIFICAR_ESTADO_FALLIDO("domiciliacion.modificar.estado", "ReporteBean.java", "domiciliaciones", "/sec/Pagos/Domiciliaciones/", "com.bod.controller", "DomiciliacionesController", "domiciliacion.modificar.estado.fallido"),
        FLUJO_APROBACION_COMPROBANTE("flujo.aprobacion.agregar", "Cnfflujos", "edicion", "/sec/FlujoAprobacion/", "com.bod.controller", "FlujoAprobacionController", "flujo.aprobacion.bitacora.comprobante"),
        CODIGO_BT_EFECTUADO("asociaciones.codigo.bt.efectuado", "Invitaciones", "enviada_invitacion", "/sec/Asociaciones/", "com.bod.controller", "AsociacionesController", "codigo.bt.generado"),
        BLOQUEO_USUARIOS_REVERSIBLE("bloqueo.usuarios.reversible", "N/A", "N/A", "N/A", "N/A", "N/A", "mensaje.Bloqueo.intento.fallido.lobby"),
        BLOQUEO_USUARIOS_IRREVERSIBLE("bloqueo.usuarios.irreversible", "N/A", "ingresoZonaSegura", "/ext/DesbloquearUsuario", "com.bod.controller", "DesbloquearUsuarioController", "mensaje.Bloqueo.irreversible.lobby"),
        EQUIPO_NO_FRECUENTE("equipo.no.frecuente", "N/A", "home", "/sec/", "com.bod.controller", "ComunesController", "notif.equipo.no.frecuente"),
        SESIONES_SIMULTANEAS("sesiones.simultaneas", "N/A", "login_5_contrasena", "/ext/Login/", "com.bod.controller", "LoginController", "notif.sesiones.simultaneas"),
        ACTUALIZAR_ESTADO_USUARIO_ZS("login.actualizar.estado.usuario", "RootZonaSegura", "index", "/sec/ZonaSegura/RootZonaSegura", "com.bod.controller", "ZonaSeguraController", "login.actualizar.estado.usuario"),
        CAMBIO_ROL_SIP_MASTER("cambiar.rol.sip.master", "AccesPolicy", "rol_sip_master", "/sec/ConfiguracionSeguridad/rol_sip_master", "com.bod.controller.", "CambioRolSipMasterController", "cambiar.rol.sip.master"),
        CAMBIO_ROL_SIP_MASTER_EFECTUADO("cambiar.rol.sip.master", "AccesPolicy", "rol_sip_master", "/sec/ConfiguracionSeguridad/rol_sip_master", "com.bod.controller.", "CambioRolSipMasterController", "cambiar.rol.sip.master.efectuado");

        private String codigoOperacion;
        private String modelo;
        private String pagina;
        private String directorio;
        private String paquete;
        private String controlador;
        private String descripcion;

        private Funcionalidad(String codigoOperacion, String modelo, String paginaPrincipal, String directorio, String paquete, String controlador, String descripcion) {
            this.codigoOperacion = codigoOperacion;
            this.modelo = modelo;
            this.pagina = paginaPrincipal;
            this.paquete = paquete;
            this.directorio = directorio;
            this.controlador = controlador;
            this.descripcion = descripcion;
        }

        public String getCodigoOperacion() {
            return codigoOperacion;
        }

        public void setCodigoOperacion(String codigoOperacion) {
            this.codigoOperacion = codigoOperacion;
        }

        public String getPaginaPrincipal() {
            return pagina;
        }

        public void setPaginaPrincipal(String paginaPrincipal) {
            this.pagina = paginaPrincipal;
        }

        public String getDirectorio() {
            return directorio;
        }

        public void setDirectorio(String directorio) {
            this.directorio = directorio;
        }

        public String obtenerVista() {
            return directorio + pagina + ".xhtml";
        }

        public String obtenerControlador() {
            return paquete + "." + controlador + ".java";
        }

        public String obtenerModelo() {
            return modelo + ".java";
        }

        public String obtenerMVC() {
            return "MODELO = " + obtenerModelo() + ", VISTA = " + obtenerVista() + ", CONTROLADOR = " + obtenerControlador();
        }

        public String obtenerDescripcionTransaccion() {
            return descripcion;
        }
    }

    public static enum DispositivosFactor3Enum {

        TARJETAS_COORDENADAS("dispositivo.tarjeta.coord"),
        EMAIL_OTP("dispositivo.email.OTP"),
        SMS_OTP("dispositivo.SMS.OTP"),
        SOFT_TOKEN("dispositivo.soft.token"),
        EXITO("exito"),
        NINGUNO("");

        private String codigo;

        DispositivosFactor3Enum(String codigo) {
            this.codigo = codigo;
        }

        public static DispositivosFactor3Enum obtenerDispositivo(String codigoBuscado) {
            if (TARJETAS_COORDENADAS.getCodigo().equals(codigoBuscado)) {
                return TARJETAS_COORDENADAS;
            } else if (SOFT_TOKEN.getCodigo().equals(codigoBuscado)) {
                return SOFT_TOKEN;
            } else if (SMS_OTP.getCodigo().equals(codigoBuscado)) {
                return SMS_OTP;
            } else {
                return EMAIL_OTP;
            }
        }

        public String getCodigo() {
            return codigo;
        }
    }

    /**
     * Método para convertir una fecha XMLGregorianCalendar a formt date
     *
     * @param value
     * @return
     */
    public String fechaXML(XMLGregorianCalendar value) {
        String dateString = "";
        if (value != null) {
            Calendar calendar = value.toGregorianCalendar();

            SimpleDateFormat formatter = FORMAT_DATE;
            formatter.setTimeZone(calendar.getTimeZone());
            dateString = formatter.format(calendar.getTime());
        }
        return dateString;
    }

    /**
     * Método para convertir una fecha XMLGregorianCalendar a formt date
     *
     * @param value
     * @return
     */
    public String fechaHoraXML(XMLGregorianCalendar value) {
        String dateString = "";
        if (value != null) {
            Calendar calendar = value.toGregorianCalendar();

            SimpleDateFormat formatter = FORMAT_DATE_HOUR;
            formatter.setTimeZone(calendar.getTimeZone());
            dateString = formatter.format(calendar.getTime());
        }
        return dateString;
    }

    /**
     * Método para convertir una fecha Date a formt XMLGregorianCalendar
     *
     * @param dateFormat
     * @return
     *
     */
    public static XMLGregorianCalendar convertidorXmlGregoriano(Date dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String date = sdf.format(dateFormat);
        XMLGregorianCalendar xmlCal;
        try {
            xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(date);
        } catch (DatatypeConfigurationException ex) {
            xmlCal = null;
        }
        return xmlCal;
    }

    /**
     * Método para convertir de negativo a positivo valores del tipo BigDecimal.
     *
     * @param valor Número de entrada.
     *
     */
    public static BigDecimal convertidorPositivo(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            return valor.negate();
        } else {
            return valor;
        }

    }

    public static String reemplazarDolar(String input) {
        String sinDolar = input;
        if (sinDolar.contains("$")) {
            sinDolar = sinDolar.replace("$", "Dolares");
        }
        return sinDolar;
    }

    public static String reemplazarAcentos(String input) {
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = input;
        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        return output;
    }

    public static BigDecimal formateadorMontoDecimal(String valor) {
        int longitud = valor.length();
        String decimal = valor.substring(longitud - 2, longitud);
        int entero = Integer.valueOf(valor.substring(0, longitud - 2));

        StringBuilder montoFinal = new StringBuilder();
        montoFinal.append(entero).append(".").append(decimal);

        BigDecimal bigDecimal = new BigDecimal(montoFinal.toString());

        return bigDecimal;
    }

    /**
     * Método encargado para validar si un dato es numérico.
     *
     */
    private static boolean isNumeric(String cadena) {
        Integer numero;
        try {
            numero = Integer.parseInt(cadena);
            if (numero > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Advertencia isNumeric() ERROR EN LA CONVERSIÓN DE DATOS DE STRING A INTEGER", e);
            return false;
        }
    }

}
