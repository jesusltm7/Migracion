package com.bod.controller;

import com.bod.beans.BeanCliente;
import com.bod.beans.BeanCuentas;
import com.bod.beans.BeanTdc;
import com.bod.beans.PosicionConsolidadaBean;
import com.bod.config.constants.ClientesConfig;
import com.bod.config.constants.DetectIdConfig;
import com.bod.config.constants.TddConfig;
import com.bod.exception.ServiceException;
import com.bod.facade.BancosFacade;
import com.bod.facade.CategoriaProductoFacade;
import com.bod.facade.ClientesFacade;
import com.bod.facade.ClientesHasUsuariosNblFacade;
import com.bod.facade.ClientesInternetBankingFacade;
import com.bod.facade.DirectorioGlobalFacade;
import com.bod.facade.FlujosFacade;
import com.bod.facade.IBAutorizacionesSIPFacade;
import com.bod.facade.IBDirectorioCuentaFacade;
import com.bod.facade.IBDirectorioTarjetaFacade;
import com.bod.facade.IBFlujoAprobacionCabeceraFacade;
import com.bod.facade.IBFlujoAprobacionDetalleFacade;
import com.bod.facade.IBMapeoAccesoFacade;
import com.bod.facade.IBPerfilConfiguracionAccesoFacade;
import com.bod.facade.IBPerfilCuentaFacade;
import com.bod.facade.IdiomasFacade;
import com.bod.facade.InvitacionesFacade;
import com.bod.facade.MonedasFacade;
import com.bod.facade.MstmedioenvioFacade;
import com.bod.facade.OperacionesNblFacade;
import com.bod.facade.PaisesFacade;
import com.bod.facade.ParametrosFacade;
import com.bod.facade.PerfilNblFacade;
import com.bod.facade.ProductosFacade;
import com.bod.facade.REFFlujoIdFacade;
import com.bod.facade.TextosFacade;
import com.bod.facade.UsuarioNblFacade;
import com.bod.facade.UsuariosTomadosAfiliacionFacade;
import com.bod.model.Bancos;
import com.bod.model.CategoriaProductos;
import com.bod.model.ClienteInternetBanking;
import com.bod.model.Clientes;
import com.bod.model.ClientesHasUsuariosNbl;
import com.bod.model.ClientesHasUsuariosNblPK;
import com.bod.model.Cnfflujos;
import com.bod.model.Detflujoproceso;
import com.bod.model.DirectorioGlobal;
import com.bod.model.IBAutorizacionesSIP;
import com.bod.model.IBCabeceraFlujo;
import com.bod.model.IBDetalleFlujo;
import com.bod.model.IBDirectorioCuenta;
import com.bod.model.IBDirectorioTarjeta;
import com.bod.model.IBPerfilConfiguracionAcceso;
import com.bod.model.IBPerfilCuentas;
import com.bod.model.Monedas;
import com.bod.model.MstOrdenFlujo;
import com.bod.model.Paises;
import com.bod.model.PerfilesNbl;
import com.bod.model.Productos;
import com.bod.facade.TipoPerfilesNblFacade;
import com.bod.model.ExcluidosMigracion;
import com.bod.model.UsuariosNbl;
import com.bod.model.UsuariosTomadosAfiliacion;
import com.bod.services.dao.ClientesDAO;
import com.bod.services.dao.DetectIDDAO;
import com.bod.services.dao.impl.ClientesImpl;
import com.bod.services.dao.impl.DetectIDImpl;
import com.bod.util.BodBaseBean;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controlador para el proceso de migracion desde la pagina transaccional actual
 * a NBL.
 *
 * @author Ernesto Cascante / Tecno Ware S.A
 */
@Named("ejecutarProcesoController3")
@SessionScoped
public class EjecutarProcesoControllerV3 extends BodBaseBean implements Serializable {

    private static final long serialVersionUID = 1;
    /*Variables para el control de los pasos a ejecutar*/
    private static final String CODIGO_CANAL = "web";

    /**
     * Codigo del proceso de afiliacion para validacion positiva
     */
    private static final String PARAMETRO_NUMERO_PROCESO = "numero.proceso.afiliacion";

    /**
     * Codigo del proceso de afiliacion de firmante juridico para validacion
     * positiva
     */
    private static final String PARAMETRO_NUMERO_PROCESO_FIRMANTE_JURIDICO = "numero.proceso.afiliacion.firmJur";

    /*Declaracion de EJBs && inyeccion*/
    @EJB
    private OperacionesNblFacade operacionesNblFacade;

    @EJB
    private MstmedioenvioFacade mediosenvioFacade;

    @EJB
    ClientesInternetBankingFacade clientesMigrarFacade;

    @EJB
    private ClientesFacade clientesFacade;

    @EJB
    private UsuarioNblFacade usuarioNBLFacade;

    @EJB
    private PaisesFacade paisesFacade;

    @EJB
    private IdiomasFacade idiomasFacade;

    @EJB
    private BancosFacade bancosFacade;

    @EJB
    private TextosFacade textosFacade;

    @EJB
    private MonedasFacade monedasFacade;

    @EJB
    private ParametrosFacade parametrosFacade;

    @EJB
    TipoPerfilesNblFacade tipoPerfilesNblFacade;

    @EJB
    IBDirectorioCuentaFacade directorioCuentaIBFacade;

    @EJB
    IBDirectorioTarjetaFacade directorioTarjetaIBFacade;

    @EJB
    private DirectorioGlobalFacade directorioGlobalFacade;

//    @EJB
    private final DetectIDDAO detectId = new DetectIDImpl();

    /*Controllador de configuracion de detectid*/
    @EJB
    private DetectIdConfig detectIdConfig;

    /**
     * Controlador de parametros para Clientes
     */
    @EJB
    private ClientesConfig clientesConfig;

    @EJB
    private PerfilNblFacade perfilNblFacade;

    @EJB
    private CategoriaProductoFacade categoriaProductoFacade;

    @EJB
    private ProductosFacade productosFacade;

    @EJB
    ClientesHasUsuariosNblFacade clientesHasUsuariosFacade;

    @EJB
    private UsuariosTomadosAfiliacionFacade usuariosTomadosFacade;

    @EJB
    private UsuarioNblFacade usuarioFacade;

    /**
     * Controlador de parametros para WSCardTDD
     */
    @EJB
    private TddConfig tddConfig;
    private final ClientesDAO clientesDao = new ClientesImpl();
    private final ClientesDAO daoCoreBancario = new ClientesImpl();

    @Inject
    private ParametrosController paramController;

    @Inject
    private IBFlujoAprobacionCabeceraFacade cabececeraFlujoIBFacade;

    @Inject
    IBFlujoAprobacionDetalleFacade flujoAprobacionDetalleIBFacade;

    @EJB
    private FlujosFacade flujosFacade;

    @EJB
    REFFlujoIdFacade rEFFlujoIdFacade;

    @EJB
    InvitacionesFacade invitacionesFacade;

    @Inject
    private IBPerfilCuentaFacade perfilCuentasIBFacade;

    @Inject
    private IBPerfilConfiguracionAccesoFacade perfilConfiguracionAccesoIBFacade;

    @Inject
    private IBMapeoAccesoFacade mapeoAccesoIBFacade;

    @Inject
    IBAutorizacionesSIPFacade autorizacionesSIPIBFacade;

    /*
     * Cliente de Internet Banking que esta realizando el proceso de migracion. 
     */
    private ClienteInternetBanking clienteProceso;

    /*
     * Usuario registrado en NBL durante el proceso de migracion. 
     */
    private UsuariosNbl usuarioProceso;

    /*
     * Usuario tomado durante el proceso de afiliacion. Indica que el usuario 
     * esta en proceso de efectuar el proceso de migracion.
     */
    private UsuariosTomadosAfiliacion usuarioTomadoAfiliacion = null;


    /*
     * Username seleccionado por el usuario para utilizar en NBL desde la vista
     * incorporar_1.xhtml.
     */
    String usuarioIngreso;
    /*
     * Marca el usuario como registrado. 
     */
    boolean usuarioTomado;

    boolean digitarUsuario;

    String textoAyudaClave;

    private BeanCliente clienteCoreBancario;

    /*
     * Indica si el cliente core bancario se ubico por cedula y id. 
     */
    private boolean clienteUbicadoPorDatosPropios = false;

    /*
     * Lista de tipos de identificacion para que el usuario elija el suyo
     */
    private List<String> listaTiposIdentificacion = null;

    /*
     * Lista de paises para mostrar a la hora de editar los usuarios secundarios.
     */
    private List<Paises> listaPaises = null;

    private boolean isInvitado;

    private Integer imagen;
    private String descripcionImagen;
    private List<Integer> listadoImagenes;
    private String rutaImagenes;
    private String rutaDetectID;
    private String preguntasAdicionalesJSON;
    private int parVecesSelect;
    private int parCantPreguntas;
    private int parPersonalPreguntas;
    private String codigoNacionalidadVzla;
    private String idProceso;
    private ExcluidosMigracion usuarioEnProceso;

    /**
     * Construye un objeto tipo IncorporarClientesController. Constructor por
     * defecto
     */
    public EjecutarProcesoControllerV3() {
    }

    public void preCargaIncorporar(ComponentSystemEvent event) {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), usuarioIngreso, "Advertencia preCargaIncorporar() Usuario con accion no permitida en incorporar_1");
            redirecciona(NO_PERMISO_PAGE);
        }
    }

    /**
     * Registra el usuario en NBL con el login seleccionado por el usuario. En
     * caso de que el usuario ya se encuentre registrado al momento de invocar
     * este metodo se actualiza el usuarioTomado y el login en NBL.
     *
     * @return Codigo
     */
    public boolean registrarUsuarioSeleccionado() {
        try {
            clienteCoreBancario = ubicarClienteCoreBancario();
            // se verifica si el cliente no tiene usuario tomado
            if (usuarioProceso == null || null == usuarioTomadoAfiliacion || usuarioTomadoAfiliacion.getLogin() == null) {
                if (registrarUsuario()) {
                    return true;
                }
            }
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), usuarioIngreso, "Advertencia registrarUsuarioSeleccionado()", e);
        }
        return false;
    }

    private void actualizarClienteMigrado(ClienteInternetBanking clienteProceso, String estado) throws Exception {
        clienteProceso.setEstadoMigracion(estado);
        clienteProceso.setFechaCambio(Calendar.getInstance().getTime());
        clientesMigrarFacade.edit(clienteProceso);
    }

    /**
     * Registra un usuario en NBL. Incorpora tanto los usuarios master como los
     * usuarios secundarios
     *
     */
    private boolean registrarUsuario() {
        boolean registrado = false;
        try {
            if (null == usuarioTomadoAfiliacion || usuarioTomadoAfiliacion.getLogin() == null) {
                // se toma un usuario provisional si no existe uno
                usuarioTomadoAfiliacion = registrarUsuarioTomado();
                registrarUsuarioBD(clienteProceso);
                if (clienteProceso.esClienteMaster() && clienteProceso.esClienteNatural()) {
                    registrado = registrarClienteMasterNatural(clienteProceso, usuarioTomadoAfiliacion);
                    if (registrado) {//Verifico que el usuario no sea master de una empresa.                    
                        List<ClienteInternetBanking> listaClientes = clientesMigrarFacade.listarAprobadosPorTipoIdYNumero(clienteProceso.getTipoIdentificacion(), clienteProceso.getNumeroIdentificacion().trim());
                        for (ClienteInternetBanking iter : listaClientes) {
                            if (iter.getNumeroCliente() != clienteProceso.getNumeroCliente()) {
                                //Iter es master jurifico.
                                if (registrado) {
                                    if (iter.esClienteMaster() && !iter.esClienteNatural()) {
                                        registrado = registrarClienteJuridico(iter, null);
                                    }
                                }
                            }
                        }
                    }
                } else if (clienteProceso.esClienteMaster() && !clienteProceso.esClienteNatural()) {//Se busca la empresa para crear el cliente
                    registrado = registrarClienteJuridico(clienteProceso, usuarioTomadoAfiliacion);
                    if (registrado) {//Verifico que el usuario no sea master de una empresa.                    
                        List<ClienteInternetBanking> listaClientes = clientesMigrarFacade.listarAprobadosPorTipoIdYNumero(clienteProceso.getTipoIdentificacion(), clienteProceso.getNumeroIdentificacion().trim());
                        for (ClienteInternetBanking iter : listaClientes) {
                            if (iter.getNumeroCliente() != clienteProceso.getNumeroCliente()) {
                                //Iter es master jurifico.
                                if (registrado) {
                                    if (iter.esClienteMaster() && !iter.esClienteNatural()) {
                                        registrado = registrarClienteJuridico(iter, null);
                                    }
                                }
                            }
                        }
                    }
                }
                if (registrado) {
                    return true;
                }
            } else {
                if (null != usuarioProceso) {
                    usuarioProceso.setLogin(usuarioIngreso);
                }
                if (null != usuarioTomadoAfiliacion) {
                    usuarioTomadoAfiliacion.setLogin(usuarioIngreso);
                    usuariosTomadosFacade.edit(usuarioTomadoAfiliacion);
                }
            }
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteProceso.getTipoCliente() + clienteProceso.getNumeroIdentificacion(), "Advertencia registrarUsuario()", e);
            registrado = false;
        }
        return registrado;
    }

    /*
     * Metodo que ubica el cliente que se esta procesando en el core Bancario. 
     * Se busca el cliente en primera intancia por tipo de identificacion, numero de identificacion y nacionalidad. En caso 
     * de que no exista ese dato se busca el cliente por el party al cual esta asociado.
     */
    private BeanCliente ubicarClienteCoreBancario() throws Exception {
        BeanCliente clienteRetorno = null;
        try {
            clienteRetorno = daoCoreBancario.buscarCliente(clienteProceso.getTipoIdentificacion(), clienteProceso.getNumeroIdentificacion(),
                    tipoDocumento(clienteProceso.getTipoIdentificacion()),
                    clienteProceso.getNacionalidad() == null ? parametrosFacade.porCodigo("pais.actual.codigo.core") : clienteProceso.getNacionalidad().trim(), clientesConfig.init());
            clienteUbicadoPorDatosPropios = true;
        } catch (ServiceException se) {
            try {
                if (se.getMessage().toUpperCase().contains("PLA5725")) {
                    clienteRetorno = daoCoreBancario.buscarCliente(String.valueOf(clienteProceso.getNumeroCliente()), clientesConfig.init());
                } else {
                    Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), clienteProceso.getTipoCliente() + clienteProceso.getNumeroIdentificacion(), "Advertencia ubicarClienteCoreBancario()", se);
                }
            } catch (Exception e) {
                Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteProceso.getTipoCliente() + clienteProceso.getNumeroIdentificacion(), "Advertencia ubicarClienteCoreBancario()", e);
                throw e;
            }
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteProceso.getTipoCliente() + clienteProceso.getNumeroIdentificacion(), "Advertencia ubicarClienteCoreBancario()", e);
            throw e;
        }
        return clienteRetorno;
    }

    private UsuariosTomadosAfiliacion registrarUsuarioTomado() throws Exception {
        UsuariosTomadosAfiliacion usTomado = null;
        try {
            if (null == usuarioTomadoAfiliacion) {
                usTomado = usuariosTomadosFacade.porIdentificacion(clienteProceso.getTipoIdentificacion().trim(), clienteProceso.getNumeroIdentificacion());
                if (null == usTomado) {
                    // si no tiene usuario tomado crea uno provisional
                    usTomado = new UsuariosTomadosAfiliacion();
                    usTomado.setTipoIdentificacion(clienteProceso.getTipoIdentificacion().charAt(0));
                    usTomado.setIdentificacion(clienteProceso.getNumeroIdentificacion());
                    usTomado.setIp(null);
                    usTomado.setSharedkey(clienteProceso.getTipoIdentificacion().trim().charAt(0) + clienteProceso.getNumeroIdentificacion().trim());
                    usTomado.setFechaHora(new Date());
                    usuariosTomadosFacade.create(usTomado);
                }
            } else {
                usTomado = usuarioTomadoAfiliacion;
            }
        } catch (Exception e) {
            //logger.error(null, e);
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteProceso.getTipoCliente() + clienteProceso.getNumeroIdentificacion(), "Advertencia ubicarClienteCoreBancario()", e);
            throw e;
        }
        return usTomado;
    }

    private boolean registrarClienteMasterNatural(ClienteInternetBanking clienteParametro, UsuariosTomadosAfiliacion usuarioTomado) {
        boolean registrado = true;
        try {
            Clientes cliente = clientesFacade.porCedula(clienteParametro.getTipoIdentificacion().trim(), clienteParametro.getNumeroIdentificacion().trim());
            if (cliente == null) {
                cliente = new Clientes();
                cliente.setTipoIdentificacion(clienteParametro.getTipoIdentificacion().trim());
                cliente.setIdentificacion(clienteParametro.getNumeroIdentificacion().trim());
                if (null != clienteCoreBancario && null != clienteCoreBancario.getNamePrefix() && clienteUbicadoPorDatosPropios) {
                    cliente.setNombre(clienteCoreBancario.getFullName().trim());
                } else {
                    cliente.setNombre(clienteParametro.getNombreCliente().trim());
                }
                cliente.setZonaSeguraLogin(BodBaseBean.CODE_DEFAULT_ZONA_SEGURA.charAt(0));
                cliente.setCoreId("" + clienteParametro.getNumeroCliente());
                clientesFacade.create(cliente);
            }

            if (!clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("P") && !clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("D") && !clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("F")) {
                PerfilesNbl perfilBase = seleccionarPerfilBase(this.clienteCoreBancario);
                PerfilesNbl perfilUsuario = crearPerfilUsuarioUnico(perfilBase, cliente);
                asociarUsuarioCliente(cliente, clienteParametro, perfilUsuario);
            }
            if (!clienteParametro.getValUsuarioMarcado().equals("D")) {
                registrarDirectorioGlobal(cliente, clienteParametro);
            }
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteParametro.getTipoCliente() + clienteParametro.getNumeroIdentificacion(), "Advertencia registrarClienteMasterNatural()", e);
            registrado = false;
        }
        return registrado;
    }

    private String obtenerNivelAutorizacion(int numeroCliente, String usuarioAsociado) {
        String retorno = "";
        try {
            List<IBAutorizacionesSIP> autorizacionesIB = autorizacionesSIPIBFacade.obtenerNivelesAutorizacion(numeroCliente, usuarioAsociado);
            List<String> nivelesAutorizacionNBL = Arrays.asList(parametrosFacade.porCodigo("migracion.flujo.aprobacion.tipos.aprobador").split(","));
            int contadorNivelesAsignados = 0;
            for (IBAutorizacionesSIP nivelIB : autorizacionesIB) {

                if (nivelesAutorizacionNBL.contains(nivelIB.getNivelAutorizacion())) {
                    retorno = nivelIB.getNivelAutorizacion();
                    contadorNivelesAsignados++;
                }
            }
            if (contadorNivelesAsignados > 1) {
                retorno = "";
            }

        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), String.valueOf(numeroCliente), "Advertencia obtenerNivelAutorizacion()", e);
        }
        return retorno;
    }

    private String obtenerNivelAutorizacionNomina(int numeroCliente, String usuarioAsociado) {

        String nivel = null;

        List<IBAutorizacionesSIP> autorizacionesIB = autorizacionesSIPIBFacade.obtenerNivelesAutorizacion(numeroCliente, usuarioAsociado);
        if (autorizacionesIB != null && !autorizacionesIB.isEmpty()) {
            for (IBAutorizacionesSIP autorizacionIB : autorizacionesIB) {
                //verificamos que el rol pertenesca a la operacion de pagos de nominas utilizando el codigo del tipo de contrato.
                String codigoTipoContrato = autorizacionIB.getCodigoTipoContrato();
                if (codigoTipoContrato.equalsIgnoreCase(IBAutorizacionesSIP.CODIGO_CONTRATO_NOMINA)) {
                    String nivelAutorizacionTemp = autorizacionIB.getNivelAutorizacion();
                    //Manejamos con siwtch case para cubrir las discrepancias entre los roles de la banca vieja y la nueva.
                    switch (nivelAutorizacionTemp) {
                        case "A1":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a1");
                            break;
                        case "A2":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a2");
                            break;
                        case "A3":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a3");
                            break;
                        case "A4":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a4");
                            break;
                        case "SO":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.sol");
                            break;
                        default:
                            nivel = null;
                            break;
                    }
                }
            }
        }
        return nivel;
    }

    private String obtenerNivelAutorizacionProveedores(int numeroCliente, String usuarioAsociado) {
        String nivel = null;
        List<IBAutorizacionesSIP> autorizacionesIB = autorizacionesSIPIBFacade.obtenerNivelesAutorizacion(numeroCliente, usuarioAsociado);
        if (autorizacionesIB != null && !autorizacionesIB.isEmpty()) {
            for (IBAutorizacionesSIP autorizacionIB : autorizacionesIB) {
                String codigoTipoContrato = autorizacionIB.getCodigoTipoContrato();
                //verificamos que el rol pertenesca a la operacion de pagos de nominas utilizando el codigo del tipo de contrato.
                if (codigoTipoContrato.equalsIgnoreCase(IBAutorizacionesSIP.CODIGO_CONTRATO_PROVEEDORES)) {
                    String nivelAutorizacionTemp = autorizacionIB.getNivelAutorizacion();
                    //Manejamos con siwtch case para cubrir las discrepancias entre los roles de la banca vieja y la nueva.
                    switch (nivelAutorizacionTemp) {
                        case "A1":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a1");
                            break;
                        case "A2":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a2");
                            break;
                        case "A3":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a3");
                            break;
                        case "A4":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a4");
                            break;
                        case "SO":
                            nivel = parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.sol");
                            break;
                        default:
                            nivel = null;
                            break;
                    }
                }
            }
        }

        return nivel;
    }

    private ArrayList<Integer> agregarOpcionesPerfilGenerales(LinkedHashMap<String, ArrayList<Integer>> mapaGeneral, int numeroCliente, String usuarioAsociado) throws Exception {
        ArrayList<Integer> listaRetorno = new ArrayList<>();
        List<IBPerfilConfiguracionAcceso> listaOpcionesBase = perfilConfiguracionAccesoIBFacade.listarRegistrosMigrar(numeroCliente, usuarioAsociado);

        for (IBPerfilConfiguracionAcceso opcion : listaOpcionesBase) {
            if (opcion.getCreditos() == 'S') {
                if (null != mapaGeneral.get("E2M2M2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2M2M2"));
                }
            }
            if (opcion.getTarjetas() == 'S') {
                if (null != mapaGeneral.get("E2M3M2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2M3M2"));
                }
            }
            if (opcion.getPagos() == 'S') {
                if (null != mapaGeneral.get("E2M4M2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2M4M2"));
                }
            }
            if (opcion.getConsultas() == 'S') {
                if (null != mapaGeneral.get("E2M5M2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2M5M2"));
                }
            }
            if (opcion.getCheques() == 'S') {
                if (null != mapaGeneral.get("E2M6M2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2M6M2"));
                }
            }
            if (opcion.getDesconocidoUno() == 'S') {
                if (null != mapaGeneral.get("E2M7M2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2M7M2"));
                }
            }
            if (opcion.getDesconocidoDos() == 'S') {
                if (null != mapaGeneral.get("E2M8M2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2M8M2"));
                }
            }
            if (opcion.getDesconocidoTres() == 'S') {
                if (null != mapaGeneral.get("E2M9M2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2M9M2"));
                }
            }
            if (opcion.getDesconocidoCuatro() == 'S') {
                if (null != mapaGeneral.get("E2MAM2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2MAM2"));
                }
            }
            if (opcion.getDesconocidoCinco() == 'S') {
                if (null != mapaGeneral.get("E2MBM2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2MBM2"));
                }
            }
            if (opcion.getDeconocidoSeis() == 'S') {
                if (null != mapaGeneral.get("E2MCM2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2MCM2"));
                }
            }
            if (opcion.getDesconocidoSiete() == 'S') {
                if (null != mapaGeneral.get("E2MPM2")) {
                    listaRetorno.addAll(mapaGeneral.get("E2MPM2"));
                }
            }
        }
        return listaRetorno;
    }

    private ArrayList<Integer> agregarPermisosProducto(LinkedHashMap<String, ArrayList<Integer>> mapaGeneral, ArrayList<Integer> permisosAsignados, IBPerfilCuentas perfilCuenta) throws Exception {
        ArrayList<Integer> listaRetorno = new ArrayList<>(permisosAsignados);
        if (null != perfilCuenta.getDebitos() && perfilCuenta.getDebitos().equalsIgnoreCase("S")) {
            if (null != mapaGeneral.get("E2BUDB")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUDB"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUDB")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUDB"));
            }
        }
        if (perfilCuenta.getCreditoCuenta() == 'S') {
            if (null != mapaGeneral.get("E2CRED")) {
                listaRetorno.addAll(mapaGeneral.get("E2CRED"));
            }
        } else {
            if (null != mapaGeneral.get("E2CRED")) {
                listaRetorno.removeAll(mapaGeneral.get("E2CRED"));
            }
        }
        if (perfilCuenta.getNivelSaldos() == 'S') {
            if (null != mapaGeneral.get("E2BUSA")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUSA"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUSA")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUSA"));
            }
        }
        if (perfilCuenta.getNivelDeMovimiento() == 'S') {
            if (null != mapaGeneral.get("E2BUMO")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUMO"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUMO")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUMO"));
            }
        }
        if (perfilCuenta.getNivelDeAuditoria() == 'S') {
            if (null != mapaGeneral.get("E2BUAU")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUAU"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUAU")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUAU"));
            }
        }
        if (perfilCuenta.getNivelDeArchivos() == 'S') {
            if (null != mapaGeneral.get("E2BUAR")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUAR"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUAR")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUAR"));
            }
        }
        if (perfilCuenta.getChequera() == 'S') {
            if (null != mapaGeneral.get("E2BUCH")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUCH"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUCH")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUCH"));
            }
        }
        if (perfilCuenta.getDivisas() == 'S') {
            if (null != mapaGeneral.get("E2BUDI")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUDI"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUDI")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUDI"));
            }
        }
        if (perfilCuenta.getNomina() == 'S') {
            if (null != mapaGeneral.get("E2BUNO")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUNO"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUNO")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUNO"));
            }
        }
        if (perfilCuenta.getOrdenesDePago() == 'S') {
            if (null != mapaGeneral.get("E2BUOP")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUOP"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUOP")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUOP"));
            }
        }
        if (perfilCuenta.getDesconocidoUno() == 'S') {
            if (null != mapaGeneral.get("E2BUOT")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUOT"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUOT")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUOT"));
            }
        }
        if (perfilCuenta.getDesconocidoDos() == 'S') {
            if (null != mapaGeneral.get("E2BUCG")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUCG"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUCG")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUCG"));
            }
        }
        if (perfilCuenta.getDesconocidoTres() == 'S') {
            if (null != mapaGeneral.get("E2BUIN")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUIN"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUIN")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUIN"));
            }
        }
        if (perfilCuenta.getDesconocidoCuatro() == 'S') {
            if (null != mapaGeneral.get("E2BUPA")) {
                listaRetorno.addAll(mapaGeneral.get("E2BUPA"));
            }
        } else {
            if (null != mapaGeneral.get("E2BUPA")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BUPA"));
            }
        }
        if (perfilCuenta.getDesconocidoCinco() == 'S') {
            if (null != mapaGeneral.get("E2BU01")) {
                listaRetorno.addAll(mapaGeneral.get("E2BU01"));
            }
        } else {
            if (null != mapaGeneral.get("E2BU01")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BU01"));
            }
        }
        if (perfilCuenta.getDesconocidoSeis() == 'S') {
            if (null != mapaGeneral.get("E2BU02")) {
                listaRetorno.addAll(mapaGeneral.get("E2BU02"));
            }
        } else {
            if (null != mapaGeneral.get("E2BU02")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BU02"));
            }
        }
        if (perfilCuenta.getDesconocidoSiente() == 'S') {
            if (null != mapaGeneral.get("E2BU03")) {
                listaRetorno.addAll(mapaGeneral.get("E2BU03"));
            }
        } else {
            if (null != mapaGeneral.get("E2BU03")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BU03"));
            }
        }
        if (perfilCuenta.getDesconocidoOcho() == 'S') {
            if (null != mapaGeneral.get("E2BU04")) {
                listaRetorno.addAll(mapaGeneral.get("E2BU04"));
            }
        } else {
            if (null != mapaGeneral.get("E2BU04")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BU04"));
            }
        }
        if (perfilCuenta.getDesconocidoNueve() == 'S') {
            if (null != mapaGeneral.get("E2BU05")) {
                listaRetorno.addAll(mapaGeneral.get("E2BU05"));
            }
        } else {
            if (null != mapaGeneral.get("E2BU05")) {
                listaRetorno.removeAll(mapaGeneral.get("E2BU05"));
            }
        }
        return listaRetorno;
    }

    public boolean registrarClienteJuridico(ClienteInternetBanking clienteParametro, UsuariosTomadosAfiliacion usuarioTomado) {
        boolean registrado = true;
        try {
            Clientes cliente = null;
            BeanCliente clienteCore = daoCoreBancario.buscarCliente(String.valueOf(clienteParametro.getNumeroCliente()), clientesConfig.init());
            if (null != clienteCore) {
                cliente = clientesFacade.porCedula(clienteCore.getTipoIdentificacion().trim(), clienteCore.getIssuedIdentValue().trim());
                if (cliente == null) {
                    cliente = new Clientes();
                    cliente.setTipoIdentificacion(clienteCore.getTipoIdentificacion().trim());
                    cliente.setIdentificacion(clienteCore.getIssuedIdentValue().trim());
                    cliente.setNombre(clienteCore.getFullName().trim());
                    cliente.setZonaSeguraLogin(BodBaseBean.CODE_DEFAULT_ZONA_SEGURA.charAt(0));
                    cliente.setCoreId(String.valueOf(clienteCore.getPartyID().trim()));
                    clientesFacade.create(cliente);
                }
            } else {
                registrado = false;
            }
            if (registrado) {
                if (!clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("P") && !clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("D") && !clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("F")) {
                    PerfilesNbl perfilBase = seleccionarPerfilBase(clienteCore);
                    PerfilesNbl perfilUsuario = crearPerfilUsuarioUnico(perfilBase, cliente);
                    asociarUsuarioCliente(cliente, clienteParametro, perfilUsuario);
                }
                if (!clienteParametro.getValUsuarioMarcado().equals("D") && !clienteParametro.getValUsuarioMarcado().equals("F")) {
                    registrarDirectorioGlobal(cliente, clienteParametro);
                }
                if (!clienteParametro.getValUsuarioMarcado().equals("F")) {
                    registrarFlujoAprobacion(cliente, clienteParametro);
                }
                //Busque la autorizacion. 
            }
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteParametro.getTipoCliente() + clienteParametro.getNumeroIdentificacion(), "Advertencia registrarClienteJuridico()", e);
            registrado = false;
        }
        return registrado;
    }

    private void registrarUsuarioBD(ClienteInternetBanking clienteIB) throws Exception {
        if (null == usuarioProceso) {
            String nacionalidadUsuario;
            if (clienteIB.getNacionalidad() == null || clienteIB.getNacionalidad().trim().isEmpty()) {
                if (null != clienteCoreBancario.getNacionality() && clienteUbicadoPorDatosPropios) {
                    nacionalidadUsuario = clienteCoreBancario.getNacionality();
                } else {
                    nacionalidadUsuario = parametrosFacade.porCodigo("pais.actual.codigo.core");
                }
            } else {
                nacionalidadUsuario = clienteIB.getNacionalidad();
            }

            Paises pais = paisesFacade.findByCodigoCore(nacionalidadUsuario.trim());
            UsuariosNbl usuario = new UsuariosNbl();
            usuario.setTipoIdentificacion(clienteIB.getTipoIdentificacion().trim());
            usuario.setIdentificacion(clienteIB.getNumeroIdentificacion().trim());
            usuario.setPaisId(pais);
            //Se saca el nombre del usuario. 
            if (null != clienteCoreBancario.getNamePrefix() && clienteUbicadoPorDatosPropios) {
                usuario.setNombre(clienteCoreBancario.getFullName().trim());
            } else {
                usuario.setNombre(clienteIB.getNombreCliente().trim());
            }
            usuario.setEstatus(BodBaseBean.ESTATUS_MIGRACION_INICIADA);
            usuario.setFechaAfiliacion(Calendar.getInstance().getTime());
            usuario.setIdiomaId(idiomasFacade.porCodigo("es"));
            usuario.setBancoId(bancosFacade.find(1L));
            usuario.setLogin("m+" + clienteIB.getTipoIdentificacion().trim() + clienteIB.getNumeroIdentificacion().trim());
            usuario.setSessionEquipoFrecuente(BodBaseBean.CODE_DEFAULT_USO_FRECUENTE);
            StringBuilder sharedKey = new StringBuilder("");
            // se arma un sharedkey
            if (clienteUbicadoPorDatosPropios) {
                sharedKey.append(clienteCoreBancario.getBankID().trim());
                sharedKey.append(clienteCoreBancario.getIssuedIdentType().trim());
                sharedKey.append(clienteCoreBancario.getIssuedIdentValue().trim());
                sharedKey.append(clienteCoreBancario.getIssuedDocType().trim());
                sharedKey.append(clienteCoreBancario.getCodPais().getCountryCodeSource().trim());
            } else {
                sharedKey.append(clienteCoreBancario.getBankID().trim());
                sharedKey.append(clienteProceso.getTipoIdentificacion().trim());
                sharedKey.append(clienteProceso.getNumeroIdentificacion().trim());
                sharedKey.append(clienteCoreBancario.getIssuedDocType().trim());
                sharedKey.append(clienteCoreBancario.getCodPais().getCountryCodeSource().trim());
            }

            usuario.setSharedkey(sharedKey.toString().trim()); //Cambiar sharedKey      
            usuarioNBLFacade.create(usuario);
            setUsuarioProceso(usuario);
        }
    }

    private void asociarUsuarioCliente(Clientes clienteRegistrado, ClienteInternetBanking clienteIB, PerfilesNbl perfilUsuario) throws Exception {
        //Se consulta el usuario del
        Clientes clientesUsuarios;
        if (clienteIB.esClienteMaster() && clienteIB.esClienteNatural()) {
            clientesUsuarios = clientesFacade.porCedula(clienteProceso.getTipoIdentificacion(), clienteProceso.getNumeroIdentificacion());
        } else {
            clientesUsuarios = clientesFacade.porCedula(clienteRegistrado.getTipoIdentificacion(), clienteRegistrado.getIdentificacion());
        }

        boolean found = false;
        if (clientesUsuarios != null) {
            if (clientesUsuarios.getClientesHasUsuariosNblCollection() != null) {
                for (ClientesHasUsuariosNbl i : clientesUsuarios.getClientesHasUsuariosNblCollection()) {
                    if (Objects.equals(i.getClientes().getId(), clientesUsuarios.getId()) && Objects.equals(i.getUsuariosNbl().getId(), usuarioProceso.getId())) {
                        found = true;
                        break;
                    }
                }
            }
        }
        if (!found) {
            ClientesHasUsuariosNbl clienteUsuario = new ClientesHasUsuariosNbl();
            ClientesHasUsuariosNblPK pk = new ClientesHasUsuariosNblPK();
            pk.setClientesId(clienteRegistrado.getId());
            pk.setUsuariosNblId(usuarioProceso.getId());
            clienteUsuario.setClientesHasUsuariosNblPK(pk);
            clienteUsuario.setClientes(clienteRegistrado);
            clienteUsuario.setUsuariosNbl(usuarioProceso);
            clienteUsuario.setEstatus(BodBaseBean.ESTATUS_CLIENTEUSUARIO_ACTIVO);
            clienteUsuario.setPerfilesNblId(perfilUsuario);
            clienteUsuario.setZonaSegura(0);
            clienteUsuario.setFkIdtipoenvio(mediosenvioFacade.porCodigo(BodBaseBean.METODO_ENTREGA_AMBOS));
            if (clienteRegistrado.getClientesHasUsuariosNblCollection() != null) {
                clienteRegistrado.getClientesHasUsuariosNblCollection().add(clienteUsuario);
            } else {
                Collection<ClientesHasUsuariosNbl> clienteUsuarioCollection = new ArrayList<>();
                clienteUsuarioCollection.add(clienteUsuario);
                clienteRegistrado.setClientesHasUsuariosNblCollection(clienteUsuarioCollection);
            }
            clientesFacade.edit(clienteRegistrado);
        }
        clienteIB.setValUsuarioMarcado("P");
        clientesMigrarFacade.edit(clienteIB);
    }

    private void asociarUsuarioCliente(Clientes clienteRegistrado, ClienteInternetBanking clienteIB, PerfilesNbl perfilUsuario, UsuariosNbl usuarioAsociado) throws Exception {
        //Se consulta el usuario del
        Clientes clientesUsuarios;
        if (clienteIB.esClienteMaster() && clienteIB.esClienteNatural()) {
            clientesUsuarios = clientesFacade.porCedula(clienteProceso.getTipoIdentificacion(), clienteProceso.getNumeroIdentificacion());
        } else {
            clientesUsuarios = clientesFacade.porCedula(clienteRegistrado.getTipoIdentificacion(), clienteRegistrado.getIdentificacion());
        }

        boolean found = false;
        if (clientesUsuarios != null) {
            if (clientesUsuarios.getClientesHasUsuariosNblCollection() != null) {
                for (ClientesHasUsuariosNbl i : clientesUsuarios.getClientesHasUsuariosNblCollection()) {
                    if (Objects.equals(i.getClientes().getId(), clientesUsuarios.getId()) && Objects.equals(i.getUsuariosNbl().getId(), usuarioAsociado.getId())) {
                        found = true;
                        break;
                    }
                }
            }
        }
        if (!found) {
            ClientesHasUsuariosNbl clienteUsuario = new ClientesHasUsuariosNbl();
            ClientesHasUsuariosNblPK pk = new ClientesHasUsuariosNblPK();
            pk.setClientesId(clienteRegistrado.getId());
            pk.setUsuariosNblId(usuarioAsociado.getId());
            clienteUsuario.setClientesHasUsuariosNblPK(pk);
            clienteUsuario.setClientes(clienteRegistrado);
            clienteUsuario.setUsuariosNbl(usuarioAsociado);
            clienteUsuario.setEstatus(BodBaseBean.ESTATUS_CLIENTEUSUARIO_ACTIVO);
            clienteUsuario.setPerfilesNblId(perfilUsuario);
            clienteUsuario.setFkIdtipoenvio(mediosenvioFacade.porCodigo(BodBaseBean.METODO_ENTREGA_AMBOS));
            if (clienteRegistrado.getClientesHasUsuariosNblCollection() != null) {
                clienteRegistrado.getClientesHasUsuariosNblCollection().add(clienteUsuario);
            } else {
                Collection<ClientesHasUsuariosNbl> clienteUsuarioCollection = new ArrayList<>();
                clienteUsuarioCollection.add(clienteUsuario);
                clienteRegistrado.setClientesHasUsuariosNblCollection(clienteUsuarioCollection);
            }
            clientesFacade.edit(clienteRegistrado);
        }
    }

    public ClientesHasUsuariosNbl ubicarClienteHasUsuario() throws Exception {
        UsuariosNbl usuario = usuarioNBLFacade.porIdentificacion(clienteProceso.getTipoIdentificacion().trim(), clienteProceso.getNumeroIdentificacion().trim(), BodBaseBean.ESTATUS_USUARIO_DESAFILIADO);
        setUsuarioProceso(usuario);
        for (ClientesHasUsuariosNbl cliente : usuario.getClientesHasUsuariosNblCollection()) {
            if (cliente.getClientes().getCoreId().equalsIgnoreCase(String.valueOf(clienteProceso.getNumeroCliente()))) {
                return cliente;
            }
        }
        return null;
    }

    /*
     * Crea el directorio global del cliente.
     */
    private void registrarDirectorioGlobal(Clientes clienteRegistrado, ClienteInternetBanking clienteBancaAntigua) throws Exception {
        try {
            //Se registra el directorio propio del cliente. Luego los otros beneficiarios. 
            registrarDirectorioPropio(clienteRegistrado);
            // se arma el directorio global con los beneficiarios y las TDC
            List<IBDirectorioCuenta> listaBeneficiariosCuenta = directorioCuentaIBFacade.listrarRegistrosMigrar(Integer.parseInt(clienteRegistrado.getCoreId().trim()));
            List<IBDirectorioTarjeta> listaBeneficiariosTarjeta = directorioTarjetaIBFacade.listarProductosMigrar(Integer.parseInt(clienteRegistrado.getCoreId().trim()));
            LinkedHashMap<String, Bancos> mapaBancos = new LinkedHashMap<>();
            List<String> listaTiposIdValidos = Arrays.asList(parametrosFacade.porCodigo("tipos.identificacion").split(","));
            if (null != listaBeneficiariosCuenta && listaBeneficiariosCuenta.size() > 0) {
                LinkedHashMap<BigDecimal, ArrayList<IBDirectorioCuenta>> mapaBeneficiarios = new LinkedHashMap<>();
                LinkedHashMap<String, IBDirectorioCuenta> mapaDepurado = new LinkedHashMap();
                for (IBDirectorioCuenta datoRegistro : listaBeneficiariosCuenta) {
                    mapaDepurado.put(datoRegistro.getCuentaTransaccion(), datoRegistro);
                }

                for (IBDirectorioCuenta registroDirectorioCuenta : mapaDepurado.values()) {
                    if (null != mapaBeneficiarios.get(registroDirectorioCuenta.getIdentificacionBeneficiario())) {
                        mapaBeneficiarios.get(registroDirectorioCuenta.getIdentificacionBeneficiario()).add(registroDirectorioCuenta);
                    } else {
                        ArrayList<IBDirectorioCuenta> listaDirectorioCuenta = new ArrayList<>();
                        listaDirectorioCuenta.add(registroDirectorioCuenta);
                        mapaBeneficiarios.put(registroDirectorioCuenta.getIdentificacionBeneficiario(), listaDirectorioCuenta);
                    }
                }

                Set<BigDecimal> llavesMapa = mapaBeneficiarios.keySet();
                CategoriaProductos productoCuenta = categoriaProductoFacade.porCodigo("cta");
                for (BigDecimal llave : llavesMapa) {
                    try {
                        if (null != llave && llave.compareTo(new BigDecimal(0)) > 0) {//0 o null indica que el cliente no tiene id y no se puede incorporar al directorio.                        
                            ArrayList<IBDirectorioCuenta> listaProductosContactos = mapaBeneficiarios.get(llave);
                            IBDirectorioCuenta beneficiarioRegistro = listaProductosContactos.get(0);
                            boolean continuar = true;
                            if (null == beneficiarioRegistro.getTipoIdentificacionBeneficiario() || !listaTiposIdValidos.contains(beneficiarioRegistro.getTipoIdentificacionBeneficiario().trim())) {
                                continuar = false;
                            }
                            if (continuar) {
                                DirectorioGlobal directorioCliente = obtenerDirectorioCliente(beneficiarioRegistro.getTipoIdentificacionBeneficiario(), beneficiarioRegistro.getIdentificacionBeneficiario().toPlainString().trim(), beneficiarioRegistro.getNombreBeneficiario(), clienteRegistrado);
                                for (IBDirectorioCuenta producto : listaProductosContactos) {
                                    String codigoBancoProceso = producto.getBancoTransaccion().trim();
                                    if (null == codigoBancoProceso || codigoBancoProceso.trim().isEmpty()) {
                                        codigoBancoProceso = BodBaseBean.CODE_ID_BANCO;
                                    }
                                    procesarProducto(directorioCliente, productoCuenta, mapaBancos, codigoBancoProceso, fillZerosLeft(producto.getCuentaTransaccion(), 20));
                                }
                            }
                        }
                    } catch (Exception e) {
                        Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioGlobal()", e);
                    }
                }
            }

            if (null != listaBeneficiariosTarjeta && listaBeneficiariosTarjeta.size() > 0) {
                LinkedHashMap<BigDecimal, ArrayList<IBDirectorioTarjeta>> mapaBeneficiarios = new LinkedHashMap<>();
                LinkedHashMap<String, IBDirectorioTarjeta> mapaDepurado = new LinkedHashMap();
                for (IBDirectorioTarjeta datoRegistro : listaBeneficiariosTarjeta) {
                    mapaDepurado.put(datoRegistro.getTarjetaBeneficiario(), datoRegistro);
                }

                for (IBDirectorioTarjeta registroDirectorioTarjeta : mapaDepurado.values()) {
                    if (null != mapaBeneficiarios.get(registroDirectorioTarjeta.getIdentificacionBeneficiario())) {
                        mapaBeneficiarios.get(registroDirectorioTarjeta.getIdentificacionBeneficiario()).add(registroDirectorioTarjeta);
                    } else {
                        ArrayList<IBDirectorioTarjeta> listaDirectorioTarjeta = new ArrayList<>();
                        listaDirectorioTarjeta.add(registroDirectorioTarjeta);
                        mapaBeneficiarios.put(registroDirectorioTarjeta.getIdentificacionBeneficiario(), listaDirectorioTarjeta);
                    }
                }
                Set<BigDecimal> llavesMapa = mapaBeneficiarios.keySet();
                CategoriaProductos productoTarjeta = categoriaProductoFacade.porCodigo("tdc");
                for (BigDecimal llave : llavesMapa) {
                    try {
                        if (null != llave && llave.compareTo(new BigDecimal(0)) > 0) {//0 o null indica que el cliente no tiene id y no se puede incorporar al directorio.                        
                            ArrayList<IBDirectorioTarjeta> listaProductosContactos = mapaBeneficiarios.get(llave);
                            IBDirectorioTarjeta beneficiarioRegistro = listaProductosContactos.get(0);
                            DirectorioGlobal directorioCliente = obtenerDirectorioCliente(beneficiarioRegistro.getTipoIdentificacionBeneficiario(), beneficiarioRegistro.getIdentificacionBeneficiario().toPlainString().trim(), beneficiarioRegistro.getNombreBeneficiario(), clienteRegistrado);
                            for (IBDirectorioTarjeta producto : listaProductosContactos) {
                                String codigoBancoProceso = producto.getCodigoBancoBeneficiario().trim();
                                if (null == codigoBancoProceso || codigoBancoProceso.trim().isEmpty()) {
                                    codigoBancoProceso = BodBaseBean.CODE_ID_BANCO;
                                }
                                procesarProducto(directorioCliente, productoTarjeta, mapaBancos, codigoBancoProceso, producto.getTarjetaBeneficiario());
                            }
                        }
                    } catch (Exception e) {
                        Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioGlobal() Error creando cliente en tarjetas", e);
                    }
                }
            }
            clienteBancaAntigua.setValUsuarioMarcado("D");
            clientesMigrarFacade.edit(clienteBancaAntigua);
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioGlobal() Error creando el directorio global", e);
            throw e;
        }
    }

    private void registrarDirectorioPropio(Clientes clienteRegistrado) throws Exception {
        try {
            DirectorioGlobal d = clienteRegistrado.getDirectorioPropio();
            if (d == null) {
                d = new DirectorioGlobal();
                d.setClientesId(clienteRegistrado);
                d.setEstatus(BodBaseBean.ESTATUS_PERFIL_CLIENTE_ACTIVO);
                d.setNombre(clienteRegistrado.getNombre());
                d.setTipoIdentificacion(clienteRegistrado.getTipoIdentificacion());
                d.setIdentificacion(clienteRegistrado.getIdentificacion());
                directorioGlobalFacade.create(d);
            }
            final PosicionConsolidadaBean posicionConsolidada = clientesDao.posicionConsolidada(clienteRegistrado.getCoreId(), clientesConfig.init());
            final CategoriaProductos tipoCuenta = categoriaProductoFacade.porCodigo("cta");

            for (BeanCuentas i : posicionConsolidada.getCuentas()) {
                Monedas moneda = monedasFacade.porCodigo(i.getCodigoMoneda().getCodigo());
                boolean encontrado = false;
                boolean estadoActivo = (null != i.getAcctStatus() && !(i.getAcctStatus().equalsIgnoreCase(parametrosFacade.porCodigo("producto.estado.cerrado"))));
                Productos valorar = null;
                if (d.getProductosCollection() != null) {
                    for (Productos j : d.getProductosCollection()) {
                        if (j.getNumero().equals(i.getId())) {
                            encontrado = true;
                            if (j.getCodigoMoneda() == null) {
                                if (moneda != null) {
                                    j.setCodigoMoneda(moneda);
                                    productosFacade.edit(j);
                                }
                            }
                            valorar = j;
                            break;
                        }
                    }
                }
                if (!encontrado && estadoActivo) {
                    Productos p = new Productos();
                    p.setCategoriaProductosId(tipoCuenta);
                    p.setDirectorioGlobalId(d);
                    p.setEstatus(i.getAcctStatus().charAt(0));
                    p.setNumero(i.getId());
                    p.setBancosId(usuarioProceso.getBancoId());
                    if (moneda != null) {
                        p.setCodigoMoneda(moneda);
                    }
                    productosFacade.create(p);
                    d.getProductosCollection().add(p);
                } else {
                    if (!estadoActivo && encontrado) {
                        d.getProductosCollection().remove(valorar);
                        productosFacade.remove(valorar);
                    }
                }
            }

            final CategoriaProductos tipoTDC = categoriaProductoFacade.porCodigo("tdc");
            for (BeanTdc i : posicionConsolidada.getTdcs()) {
                boolean encontrado = false;
                Productos valorar = null;

                if (d.getProductosCollection() != null) {
                    for (Productos j : d.getProductosCollection()) {
                        if (j.getNumero().equals(i.getCardNum())) {
                            encontrado = true;
                            valorar = j;
                            break;
                        }
                    }
                }

                if (!encontrado) {
                    Productos p = new Productos();
                    p.setCategoriaProductosId(tipoTDC);
                    p.setDirectorioGlobalId(d);
                    p.setEstatus(BodBaseBean.ESTATUS_PERFIL_CLIENTE_ACTIVO);
                    p.setNumero(i.getCardNum());
                    p.setBancosId(usuarioProceso.getBancoId());
                    productosFacade.create(p);
                    d.getProductosCollection().add(p);
                }
            }
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioPropio() Error creando el directorio global propio", e);
            throw e;
        }
    }

    private void registrarFlujoAprobacion(Clientes clienteRegistrado, ClienteInternetBanking clienteBancaAntigua) throws Exception {
        try {
            List<IBCabeceraFlujo> listaCabecera = cabececeraFlujoIBFacade.listarRegistrosMigrar(Integer.parseInt(clienteRegistrado.getCoreId()));

            ClientesHasUsuariosNbl clienteAsociado = null;
            for (ClientesHasUsuariosNbl iter : clienteRegistrado.getClientesHasUsuariosNblCollection()) {
                if (iter.getClientes().getId().equals(clienteRegistrado.getId()) && iter.getUsuariosNbl().getId().equals(usuarioProceso.getId())) {
                    clienteAsociado = iter;
                    break;
                }
            }
            if (clienteAsociado != null) {
                for (IBCabeceraFlujo iterador : listaCabecera) {//Agrego cada flujo
                    Cnfflujos flujo = new Cnfflujos();
                    flujo.setClientesHasUsuariosNbl(clienteAsociado);
                    flujo.setValmontomin(iterador.getMontoInicial());
                    flujo.setValmontomax(iterador.getMontoFinal());
                    flujo.setVersion(Long.parseLong("1"));
                    flujo.setValestadoflujo('A');
                    if (iterador.getTipoContrato().trim().equals("400")) { //Nomina
                        String idFlujo = "nomina" + iterador.getConsecutivoNumeroContrato() + " Contrato:" + iterador.getTipoContrato() + completarZerosIzquierda(iterador.getNumeroContrato().toString(), 11) + completarZerosIzquierda(String.valueOf(iterador.getConsecutivoNumeroContrato()), 3);
                        Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getCoreId(), "***** Flujo de firmas a migrar " + idFlujo);
                        flujo.setDesflujo(idFlujo);
                        flujo.setFkIdoperacionnbl(operacionesNblFacade.porCodigo(Funcionalidad.PAGOS_NOMINA_INDIVIDUAL_EXITOSO.getCodigoOperacion()));
                    } else {
                        String idFlujo = "proveedores" + iterador.getConsecutivoNumeroContrato() + " Contrato:" + iterador.getTipoContrato() + completarZerosIzquierda(iterador.getNumeroContrato().toString(), 11) + completarZerosIzquierda(String.valueOf(iterador.getConsecutivoNumeroContrato()), 3);
                        Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getCoreId(), "***** Flujo de firmas a migrar " + idFlujo);
                        flujo.setDesflujo(idFlujo);
                        flujo.setFkIdoperacionnbl(operacionesNblFacade.porCodigo(Funcionalidad.PAGOS_PROVEEDORES_INDIVIDUAL_EXITOSO.getCodigoOperacion()));
                    }

                    List<IBDetalleFlujo> listaFlujoAsociado = flujoAprobacionDetalleIBFacade.listarRegistrosAsociados(Integer.parseInt(clienteRegistrado.getCoreId()), iterador.getTipoOperacion(), iterador.getConsecutivoDetalle());
                    for (IBDetalleFlujo detalleFlujo : listaFlujoAsociado) {
                        Detflujoproceso detFlujoProceso = new Detflujoproceso();
                        detFlujoProceso.setFkIdflujo(flujo);
                        detFlujoProceso.setNumproceso(new BigInteger(detalleFlujo.getCodigoSubProceso().replace("P", "").trim()));
                        detFlujoProceso.setTipofirma(detalleFlujo.getCodigoTipoFirma().trim().charAt(0));
                        detFlujoProceso.setSustitucion(Integer.parseInt(detalleFlujo.getValorSustitucionFirma().replace("N", "0").replace("S", "1")));

                        List<MstOrdenFlujo> mstOrdenFlujoList = new ArrayList<>();
                        List<String> tiposAutorizacionAgregados = new ArrayList<>();
                        int ordenEjecucion = 1;
                        if (detalleFlujo.getPrimerTipoAutorizacion() != null && !detalleFlujo.getPrimerTipoAutorizacion().trim().isEmpty()) {
                            String tipoAutorizacion = detalleFlujo.getPrimerTipoAutorizacion().replace("P", "A");
                            if (!tiposAutorizacionAgregados.contains(tipoAutorizacion)) {
                                tiposAutorizacionAgregados.add(tipoAutorizacion);
                                MstOrdenFlujo mstOrdenFlujo = new MstOrdenFlujo();
                                mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                                mstOrdenFlujo.setFkIdFlujoProceso(detFlujoProceso);
                                mstOrdenFlujo.setOrdenEjecucion(ordenEjecucion++);
                                mstOrdenFlujo.setRol(tipoAutorizacion);
                                mstOrdenFlujoList.add(mstOrdenFlujo);
                            }
                        }
                        if (detalleFlujo.getSegundoTipoAutorizacion() != null && !detalleFlujo.getSegundoTipoAutorizacion().trim().isEmpty()) {
                            String tipoAutorizacion = detalleFlujo.getSegundoTipoAutorizacion().replace("P", "A");
                            if (!tiposAutorizacionAgregados.contains(tipoAutorizacion)) {
                                tiposAutorizacionAgregados.add(tipoAutorizacion);
                                MstOrdenFlujo mstOrdenFlujo = new MstOrdenFlujo();
                                mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                                mstOrdenFlujo.setFkIdFlujoProceso(detFlujoProceso);
                                mstOrdenFlujo.setOrdenEjecucion(ordenEjecucion++);
                                mstOrdenFlujo.setRol(tipoAutorizacion);
                                mstOrdenFlujoList.add(mstOrdenFlujo);
                            }
                        }
                        if (detalleFlujo.getTercerTipoAutorizacion() != null && !detalleFlujo.getTercerTipoAutorizacion().trim().isEmpty()) {
                            String tipoAutorizacion = detalleFlujo.getTercerTipoAutorizacion().replace("P", "A");
                            if (!tiposAutorizacionAgregados.contains(tipoAutorizacion)) {
                                tiposAutorizacionAgregados.add(tipoAutorizacion);
                                MstOrdenFlujo mstOrdenFlujo = new MstOrdenFlujo();
                                mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                                mstOrdenFlujo.setFkIdFlujoProceso(detFlujoProceso);
                                mstOrdenFlujo.setOrdenEjecucion(ordenEjecucion++);
                                mstOrdenFlujo.setRol(tipoAutorizacion);
                                mstOrdenFlujoList.add(mstOrdenFlujo);
                            }
                        }
                        if (detalleFlujo.getCuartoTipoAutorizacion() != null && !detalleFlujo.getCuartoTipoAutorizacion().trim().isEmpty()) {
                            String tipoAutorizacion = detalleFlujo.getCuartoTipoAutorizacion().replace("P", "A");
                            if (!tiposAutorizacionAgregados.contains(tipoAutorizacion)) {
                                tiposAutorizacionAgregados.add(tipoAutorizacion);
                                MstOrdenFlujo mstOrdenFlujo = new MstOrdenFlujo();
                                mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                                mstOrdenFlujo.setFkIdFlujoProceso(detFlujoProceso);
                                mstOrdenFlujo.setOrdenEjecucion(ordenEjecucion++);
                                mstOrdenFlujo.setRol(tipoAutorizacion);
                                mstOrdenFlujoList.add(mstOrdenFlujo);
                            }
                        }
                        detFlujoProceso.setMstOrdenFlujoList(mstOrdenFlujoList);
                        if (flujo.getDetflujoprocesoList() == null) {
                            flujo.setDetflujoprocesoList(new ArrayList<Detflujoproceso>());
                        }
                        flujo.getDetflujoprocesoList().add(detFlujoProceso);
                    }
                    flujosFacade.create(flujo);

                    if (flujo.getFkIdoperacionnbl().getCodigo().contains("nomina") || flujo.getFkIdoperacionnbl().getCodigo().contains("proveedor")) {
                        Cnfflujos flujoEspejo = flujo;
                        flujoEspejo.setPkIdflujo(Long.parseLong(rEFFlujoIdFacade.generarREFFlujoId()));
                        for (Detflujoproceso flujoProceso : flujo.getDetflujoprocesoList()) {
                            flujoProceso.setPkIdflujoproceso(Long.parseLong(rEFFlujoIdFacade.generarREFFlujoProcesoId()));
                            flujoProceso.setFkIdflujo(flujoEspejo);
                            for (MstOrdenFlujo mstOrdenFlujo : flujoProceso.getMstOrdenFlujoList()) {
                                mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                                mstOrdenFlujo.setFkIdFlujoProceso(flujoProceso);
                            }
                        }
                        if (flujo.getFkIdoperacionnbl().getCodigo().contains("nomina")) {
                            flujoEspejo.setFkIdoperacionnbl(flujo.getFkIdoperacionnbl().getCodigo().equals(Funcionalidad.PAGOS_NOMINA_INDIVIDUAL_EXITOSO.getCodigoOperacion()) ? operacionesNblFacade.porCodigo(Funcionalidad.PAGOS_NOMINA_MASIVO_EXITOSO.getCodigoOperacion()) : operacionesNblFacade.porCodigo(Funcionalidad.PAGOS_NOMINA_INDIVIDUAL_EXITOSO.getCodigoOperacion()));
                        } else if (flujo.getFkIdoperacionnbl().getCodigo().contains("proveedor")) {
                            flujoEspejo.setFkIdoperacionnbl(flujo.getFkIdoperacionnbl().getCodigo().equals(Funcionalidad.PAGOS_PROVEEDORES_INDIVIDUAL_EXITOSO.getCodigoOperacion()) ? operacionesNblFacade.porCodigo(Funcionalidad.PAGOS_PROVEEDORES_MASIVO_EXITOSO.getCodigoOperacion()) : operacionesNblFacade.porCodigo(Funcionalidad.PAGOS_PROVEEDORES_INDIVIDUAL_EXITOSO.getCodigoOperacion()));
                        }

                        flujosFacade.create(flujoEspejo);
                    }
                }
            }
            clienteBancaAntigua.setValUsuarioMarcado("F");
            clientesMigrarFacade.edit(clienteBancaAntigua);
        } catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioPropio() Error creando el flujo de aprobacion", e);
        }
    }

    public String completarZerosIzquierda(String cadena, int length) {
        String retorno = cadena;
        if (null != cadena) {
            int stringLength = cadena.length();
            int numberZeros = length - stringLength;
            if (numberZeros >= 0) {
                for (int j = 0; j < numberZeros; j++) {
                    retorno = "0" + retorno;
                }
            } else {
                return cadena.substring(0, length);
            }
        }
        return retorno;
    }

    private void procesarProducto(DirectorioGlobal directorioCliente, CategoriaProductos categoria, LinkedHashMap<String, Bancos> mapaBancos, String bancoProducto, String numeroProducto) throws Exception {
        Bancos bancoAsociado = null;
        if (null != bancoProducto && !bancoProducto.trim().isEmpty()) {
            if (mapaBancos.get(fillZerosLeft(bancoProducto, 4)) == null) {
                bancoAsociado = bancosFacade.porCodigo(fillZerosLeft(bancoProducto, 4));
                mapaBancos.put(bancoProducto.trim(), bancoAsociado);
            } else {
                bancoAsociado = mapaBancos.get(fillZerosLeft(bancoProducto, 4));
            }
        }
        if (null != numeroProducto && !numeroProducto.isEmpty()) {
            if (!existeProducto(directorioCliente, numeroProducto)) {
                registrarProducto(directorioCliente, categoria, bancoAsociado, numeroProducto);
            }
        }
    }

    private void registrarProducto(DirectorioGlobal directorioCliente, CategoriaProductos categoria, Bancos bancoAsociado, String numeroProducto) throws Exception {
        Productos p = new Productos();
        p.setCategoriaProductosId(categoria);
        p.setDirectorioGlobalId(directorioCliente);
        p.setEstatus('A');
        p.setNumero(numeroProducto);
        p.setBancosId(bancoAsociado);
        productosFacade.create(p);
    }

    private boolean existeProducto(DirectorioGlobal directorioCliente, String numeroProducto) throws Exception {
        boolean encontrado = false;
        if (directorioCliente.getProductosCollection() != null) {
            for (Productos j : directorioCliente.getProductosCollection()) {
                if (j.getNumero().equals(numeroProducto)) {
                    encontrado = true;
                    break;
                }
            }
        }
        return encontrado;
    }

    private DirectorioGlobal obtenerDirectorioCliente(String tipoIdentificacion, String identificacion, String nombreBeneficiario, Clientes clienteRegistrado) throws Exception {
        DirectorioGlobal directorioCliente = directorioGlobalFacade.porIdentificacionYCliente(tipoIdentificacion.trim(), identificacion.trim(), clienteRegistrado);
        if (null == directorioCliente) {//Si no es nulo ya el directorio para ese cliente se creo.
            directorioCliente = new DirectorioGlobal();
            directorioCliente.setAlias(tipoIdentificacion.trim() + identificacion.trim());
            directorioCliente.setClientesId(clienteRegistrado);
            directorioCliente.setEstatus(BodBaseBean.DIRECTORIO_GLOBAL_ACTIVO);
            directorioCliente.setNombre(nombreBeneficiario);
            directorioCliente.setTipoIdentificacion(tipoIdentificacion.trim());
            directorioCliente.setIdentificacion(identificacion.trim());
            directorioGlobalFacade.create(directorioCliente);
        }
        return directorioCliente;
    }

    private String fillZerosLeft(String string, int length) {
        int stringLength = string.length();
        int numberZeros = length - stringLength;
        if (numberZeros >= 0) {
            for (int j = 0; j < numberZeros; j++) {
                string = "0" + string;
            }
        } else {
            return string;
        }
        return string;
    }

    public void setDigitarUsuario(boolean digitarUsuario) {
        this.digitarUsuario = digitarUsuario;
    }

    public BeanCliente getClienteCoreBancario() {
        return clienteCoreBancario;
    }

    public void setClienteCoreBancario(BeanCliente clienteCoreBancario) {
        this.clienteCoreBancario = clienteCoreBancario;
    }

    public List<String> getListaTiposIdentificacion() {
        if (null == listaTiposIdentificacion) {
            listaTiposIdentificacion = new ArrayList<>();
            try {
                listaTiposIdentificacion = Arrays.asList(parametrosFacade.porCodigo("tipos.identificacion").split(","));
            } catch (Exception e) {

            }
        }
        return listaTiposIdentificacion;
    }

    public String getCodigoNacionalidadVzla() {
        return "001";//Quemado ya que este codigo no va a cambiar durante la migracion.
    }

    public void setCodigoNacionalidadVzla(String codigoNacionalidadVzla) {
        this.codigoNacionalidadVzla = codigoNacionalidadVzla;
    }

    public List<Paises> getListaPaises() {
        if (listaPaises == null) {
            listaPaises = paisesFacade.todos();
        }
        return listaPaises;
    }

    public void setListaPaises(List<Paises> listaPaises) {
        this.listaPaises = listaPaises;
    }

    public void setListaTiposIdentificacion(List<String> listaTiposIdentificacion) {
        this.listaTiposIdentificacion = listaTiposIdentificacion;
    }

    public int getParCantPreguntas() {
        return parCantPreguntas;
    }

    public void setParCantPreguntas(int parCantPreguntas) {
        this.parCantPreguntas = parCantPreguntas;
    }

    public int getParPersonalPreguntas() {
        return parPersonalPreguntas;
    }

    public void setParPersonalPreguntas(int parPersonalPreguntas) {
        this.parPersonalPreguntas = parPersonalPreguntas;
    }

    public Boolean mostrarPreguntasServicioEstatico(int cont) {
        return (cont <= this.parCantPreguntas);
    }

    public Boolean mostrarTodasLasPreguntas(int cont) {
        return cont <= (this.parCantPreguntas + this.parPersonalPreguntas);
    }

    public int getParVecesSelect() {
        return parVecesSelect;
    }

    public void setParVecesSelect(int parVecesSelect) {
        this.parVecesSelect = parVecesSelect;
    }

    public void setUsuarioProceso(UsuariosNbl usuarioProceso) {
        this.usuarioProceso = usuarioProceso;
    }

    public ClienteInternetBanking getClienteProceso() {
        return clienteProceso;
    }

    public void setClienteProceso(ClienteInternetBanking clienteProceso) {
        this.clienteProceso = clienteProceso;
    }

    public void limpiar() {
        this.usuarioIngreso = "";
        imagen = null;
        descripcionImagen = null;
        listadoImagenes = null;
        rutaImagenes = null;
        rutaDetectID = null;
        usuarioTomado = false;
        setClienteProceso(null);
        setUsuarioProceso(null);
    }

    public boolean migrarUsuario(String tipoIdentificacion, String identificacion) {
        limpiar();
        String llaveError = "";
        String redireccion = "";
        ClienteInternetBanking clienteMigrar = null;
        // crea un usuario con los datos del cliente a migrar
        UsuariosNbl user = usuarioFacade.porSoloIdentificacion(tipoIdentificacion, identificacion.trim());
       // busca a lista de usuarios a migrar del cliente
        List<ClienteInternetBanking> listaClientes = clientesMigrarFacade.listarPendientesPorTipoIdYNumero(tipoIdentificacion.trim(), identificacion.trim());
        if (listaClientes.size() > 0) {
            boolean clienteMigrado = false;
            boolean iniciarMigracion = false;
            if (user != null) {
                switch (user.getEstatus()) {
                    case BodBaseBean.ESTATUS_MIGRACION_INICIADA:
                        clienteMigrado = false;
                        break;
                    default:
                        clienteMigrado = true;
                        break;
                }
            }
            // busca datos del cliente tanto natural como juridco
            if (!clienteMigrado) {
                //Se busca si es un cliente master natural el que esta pendiente de migrar. 
                for (ClienteInternetBanking clienteProcesoIter : listaClientes) {
                    //Se migran los master natural.
                    if (clienteProcesoIter.esClienteMaster() && clienteProcesoIter.esClienteNatural() && clienteProcesoIter.getEstadoMigracion().equalsIgnoreCase("A")) {
                        clienteMigrar = clienteProcesoIter;
                        break;
                    }
                }
                if (clienteMigrar == null) {
                    //Se busca si es un cliente master juridico el que esta pendiente de migrar. 
                    for (ClienteInternetBanking clienteProcesoIter : listaClientes) {
                        //Se migran los master juridicos.
                        if (clienteProcesoIter.esClienteMaster() && !clienteProcesoIter.esClienteNatural() && clienteProcesoIter.getEstadoMigracion().equalsIgnoreCase("A")) {
                            clienteMigrar = clienteProcesoIter;
                            break;
                        }
                    }
                }
                if (clienteMigrar != null) {
                    setUsuarioProceso(user);
                    setClienteProceso(clienteMigrar);
                    // una vez armada la data del cliente para migrar se envia a registrar
                    return registrarUsuarioSeleccionado();
                }
            }
        } else {
            Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), tipoIdentificacion + identificacion, "No tiene perfiles por migrar.");
        }
        return false;
    }
    
   
}
