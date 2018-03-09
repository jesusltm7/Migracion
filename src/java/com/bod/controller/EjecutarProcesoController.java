/*
 * Decompiled with CFR 0_124.
 * 
 * Could not load the following classes:
 *  com.bod.beans.BeanCliente
 *  com.bod.beans.BeanCuentas
 *  com.bod.beans.BeanMoneda
 *  com.bod.beans.BeanTdc
 *  com.bod.beans.PosicionConsolidadaBean
 *  com.bod.exception.ServiceException
 *  com.bod.services.dao.ClientesDAO
 *  com.bod.services.dao.DetectIDDAO
 *  com.bod.services.dao.impl.ClientesImpl
 *  com.bod.services.dao.impl.DetectIDImpl
 *  com.bod.util.logger.Log
 *  com.bod.ws.soa.services.WSParty.CountryCode
 *  javax.ejb.EJB
 *  javax.enterprise.context.RequestScoped
 *  javax.faces.context.ExternalContext
 *  javax.faces.context.FacesContext
 *  javax.faces.event.ComponentSystemEvent
 *  javax.inject.Inject
 *  javax.inject.Named
 *  javax.servlet.ServletContext
 */
package com.bod.controller;

import com.bod.beans.BeanCliente;
import com.bod.beans.BeanCuentas;
import com.bod.beans.BeanMoneda;
import com.bod.beans.BeanTdc;
import com.bod.beans.PosicionConsolidadaBean;
import com.bod.config.constants.ClientesConfig;
import com.bod.config.constants.DetectIdConfig;
import com.bod.config.constants.TddConfig;
import com.bod.controller.ParametrosController;
import com.bod.exception.ServiceException;
import com.bod.facade.BancosFacade;
import com.bod.facade.CategoriaProductoFacade;
import com.bod.facade.ClientesFacade;
import com.bod.facade.ClientesHasUsuariosNblFacade;
import com.bod.facade.ClientesInternetBankingFacade;
import com.bod.facade.DirectorioGlobalFacade;
import com.bod.facade.ExcluidosMigracionFacade;
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
import com.bod.facade.TipoPerfilesNblFacade;
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
import com.bod.model.ExcluidosMigracion;
import com.bod.model.IBAutorizacionesSIP;
import com.bod.model.IBCabeceraFlujo;
import com.bod.model.IBDetalleFlujo;
import com.bod.model.IBDirectorioCuenta;
import com.bod.model.IBDirectorioTarjeta;
import com.bod.model.IBPerfilConfiguracionAcceso;
import com.bod.model.IBPerfilCuentas;
import com.bod.model.Idiomas;
import com.bod.model.Monedas;
import com.bod.model.MstOrdenFlujo;
import com.bod.model.Mstmedioenvio;
import com.bod.model.OperacionesNbl;
import com.bod.model.Paises;
import com.bod.model.PerfilesNbl;
import com.bod.model.Productos;
import com.bod.model.UsuariosNbl;
import com.bod.model.UsuariosTomadosAfiliacion;
import com.bod.services.dao.ClientesDAO;
import com.bod.services.dao.DetectIDDAO;
import com.bod.services.dao.impl.ClientesImpl;
import com.bod.services.dao.impl.DetectIDImpl;
import com.bod.util.BodBaseBean;
import com.bod.util.LogNBL;
import com.bod.util.logger.Log;
import com.bod.ws.soa.services.WSParty.CountryCode;
import java.io.InputStream;
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
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

@Named(value="ejecutarProcesoController")
@RequestScoped
public class EjecutarProcesoController
extends BodBaseBean
implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String CODIGO_CANAL = "web";
    private static final String PARAMETRO_NUMERO_PROCESO = "numero.proceso.afiliacion";
    private static final String PARAMETRO_NUMERO_PROCESO_FIRMANTE_JURIDICO = "numero.proceso.afiliacion.firmJur";
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
    private final DetectIDDAO detectId = new DetectIDImpl();
    @EJB
    private DetectIdConfig detectIdConfig;
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
    @EJB
    private ExcluidosMigracionFacade excluidosFacade;
    @Inject
    private IBPerfilCuentaFacade perfilCuentasIBFacade;
    @Inject
    private IBPerfilConfiguracionAccesoFacade perfilConfiguracionAccesoIBFacade;
    @Inject
    private IBMapeoAccesoFacade mapeoAccesoIBFacade;
    @Inject
    IBAutorizacionesSIPFacade autorizacionesSIPIBFacade;
    private ClienteInternetBanking clienteProceso;
    private UsuariosNbl usuarioProceso;
    private ExcluidosMigracion usuarioEnProceso;
    private UsuariosTomadosAfiliacion usuarioTomadoAfiliacion = null;
    String usuarioIngreso;
    boolean usuarioTomado;
    boolean digitarUsuario;
    String textoAyudaClave;
    private BeanCliente clienteCoreBancario;
    private String idProceso;
    private boolean clienteUbicadoPorDatosPropios = false;
    private List<String> listaTiposIdentificacion = null;
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

    public void preCargaIncorporar(ComponentSystemEvent event) {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), this.usuarioIngreso, "Advertencia preCargaIncorporar() Usuario con accion no permitida en incorporar_1");
            EjecutarProcesoController.redirecciona(NO_PERMISO_PAGE);
        }
    }

    public boolean registrarUsuarioSeleccionado() {
        try {
            this.clienteCoreBancario = this.ubicarClienteCoreBancario();
            if ((this.usuarioProceso == null || null == this.usuarioTomadoAfiliacion || this.usuarioTomadoAfiliacion.getLogin() == null) && this.registrarUsuario()) {
                return true;
            }
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), this.usuarioIngreso, "Advertencia registrarUsuarioSeleccionado()", (Throwable)e);
        }
        return false;
    }

    private void actualizarClienteMigrado(ClienteInternetBanking clienteProceso, String estado) throws Exception {
        clienteProceso.setEstadoMigracion(estado);
        clienteProceso.setFechaCambio(Calendar.getInstance().getTime());
        this.clientesMigrarFacade.edit(clienteProceso);
    }

    private boolean registrarUsuario() {
        boolean registrado;
        block12 : {
            registrado = false;
            try {
                if (null == this.usuarioTomadoAfiliacion || this.usuarioTomadoAfiliacion.getLogin() == null) {
                    this.usuarioTomadoAfiliacion = this.registrarUsuarioTomado();
                    this.registrarUsuarioBD(this.clienteProceso);
                    if (this.clienteProceso.esClienteMaster() && this.clienteProceso.esClienteNatural()) {
                        registrado = this.registrarClienteMasterNatural(this.clienteProceso, this.usuarioTomadoAfiliacion);
                        if (registrado) {
                            List<ClienteInternetBanking> listaClientes = this.clientesMigrarFacade.listarAprobadosPorTipoIdYNumero(this.clienteProceso.getTipoIdentificacion(), this.clienteProceso.getNumeroIdentificacion().trim());
                            for (ClienteInternetBanking iter : listaClientes) {
                                if (iter.getNumeroCliente() == this.clienteProceso.getNumeroCliente() || !registrado || !iter.esClienteMaster() || iter.esClienteNatural()) continue;
                                registrado = this.registrarClienteJuridico(iter, null);
                            }
                        }
                    } else if (this.clienteProceso.esClienteMaster() && !this.clienteProceso.esClienteNatural() && (registrado = this.registrarClienteJuridico(this.clienteProceso, this.usuarioTomadoAfiliacion))) {
                        List<ClienteInternetBanking> listaClientes = this.clientesMigrarFacade.listarAprobadosPorTipoIdYNumero(this.clienteProceso.getTipoIdentificacion(), this.clienteProceso.getNumeroIdentificacion().trim());
                        for (ClienteInternetBanking iter : listaClientes) {
                            if (iter.getNumeroCliente() == this.clienteProceso.getNumeroCliente() || !registrado || !iter.esClienteMaster() || iter.esClienteNatural()) continue;
                            registrado = this.registrarClienteJuridico(iter, null);
                        }
                    }
                    if (registrado) {
                        return true;
                    }
                    break block12;
                }
                if (null != this.usuarioProceso) {
                    this.usuarioProceso.setLogin(this.usuarioIngreso);
                }
                if (null != this.usuarioTomadoAfiliacion) {
                    this.usuarioTomadoAfiliacion.setLogin(this.usuarioIngreso);
                    this.usuariosTomadosFacade.edit(this.usuarioTomadoAfiliacion);
                }
            }
            catch (Exception e) {
                Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), this.clienteProceso.getTipoCliente() + this.clienteProceso.getNumeroIdentificacion(), "Advertencia registrarUsuario()", (Throwable)e);
                registrado = false;
            }
        }
        return registrado;
    }

    private BeanCliente ubicarClienteCoreBancario() throws Exception {
        BeanCliente clienteRetorno = null;
        try {
            clienteRetorno = this.daoCoreBancario.buscarCliente(this.clienteProceso.getTipoIdentificacion(), this.clienteProceso.getNumeroIdentificacion(), this.tipoDocumento(this.clienteProceso.getTipoIdentificacion()), this.clienteProceso.getNacionalidad() == null ? this.parametrosFacade.porCodigo("pais.actual.codigo.core") : this.clienteProceso.getNacionalidad().trim(), this.clientesConfig.init());
            this.clienteUbicadoPorDatosPropios = true;
        }
        catch (ServiceException se) {
            try {
                if (se.getMessage().toUpperCase().contains("PLA5725")) {
                    clienteRetorno = this.daoCoreBancario.buscarCliente(String.valueOf(this.clienteProceso.getNumeroCliente()), this.clientesConfig.init());
                } else {
                    Log.getInstance().warn(LogNBL.MIGRAR.getCodigo(), this.clienteProceso.getTipoCliente() + this.clienteProceso.getNumeroIdentificacion(), "Advertencia ubicarClienteCoreBancario()", (Throwable)se);
                }
            }
            catch (Exception e) {
                Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), this.clienteProceso.getTipoCliente() + this.clienteProceso.getNumeroIdentificacion(), "Advertencia ubicarClienteCoreBancario()", (Throwable)e);
                throw e;
            }
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), this.clienteProceso.getTipoCliente() + this.clienteProceso.getNumeroIdentificacion(), "Advertencia ubicarClienteCoreBancario()", (Throwable)e);
            throw e;
        }
        return clienteRetorno;
    }

    private UsuariosTomadosAfiliacion registrarUsuarioTomado() throws Exception {
        UsuariosTomadosAfiliacion usTomado = null;
        try {
            if (null == this.usuarioTomadoAfiliacion) {
                usTomado = this.usuariosTomadosFacade.porIdentificacion(this.clienteProceso.getTipoIdentificacion().trim(), this.clienteProceso.getNumeroIdentificacion());
                if (null == usTomado) {
                    usTomado = new UsuariosTomadosAfiliacion();
                    usTomado.setTipoIdentificacion(Character.valueOf(this.clienteProceso.getTipoIdentificacion().charAt(0)));
                    usTomado.setIdentificacion(this.clienteProceso.getNumeroIdentificacion());
                    usTomado.setIp(null);
                    usTomado.setSharedkey("" + this.clienteProceso.getTipoIdentificacion().trim().charAt(0) + this.clienteProceso.getNumeroIdentificacion().trim());
                    usTomado.setFechaHora(new Date());
                    this.usuariosTomadosFacade.create(usTomado);
                }
            } else {
                usTomado = this.usuarioTomadoAfiliacion;
            }
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), this.clienteProceso.getTipoCliente() + this.clienteProceso.getNumeroIdentificacion(), "Advertencia registrarUsuarioTomado()", (Throwable)e);
            throw e;
        }
        return usTomado;
    }

    private boolean registrarClienteMasterNatural(ClienteInternetBanking clienteParametro, UsuariosTomadosAfiliacion usuarioTomado) {
        boolean registrado = true;
        try {
            Clientes cliente = this.clientesFacade.porCedula(clienteParametro.getTipoIdentificacion().trim(), clienteParametro.getNumeroIdentificacion().trim());
            if (cliente == null) {
                cliente = new Clientes();
                cliente.setTipoIdentificacion(clienteParametro.getTipoIdentificacion().trim());
                cliente.setIdentificacion(clienteParametro.getNumeroIdentificacion().trim());
                if (null != this.clienteCoreBancario && null != this.clienteCoreBancario.getNamePrefix() && this.clienteUbicadoPorDatosPropios) {
                    cliente.setNombre(this.clienteCoreBancario.getFullName().trim());
                } else {
                    cliente.setNombre(clienteParametro.getNombreCliente().trim());
                }
                cliente.setZonaSeguraLogin(Character.valueOf("I".charAt(0)));
                cliente.setCoreId("" + clienteParametro.getNumeroCliente());
                this.clientesFacade.create(cliente);
            }
            if (!(clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("P") || clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("D") || clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("F"))) {
                PerfilesNbl perfilBase = this.seleccionarPerfilBase(this.clienteCoreBancario);
                PerfilesNbl perfilUsuario = this.crearPerfilUsuarioUnico(perfilBase, cliente);
                this.asociarUsuarioCliente(cliente, clienteParametro, perfilUsuario);
            }
            if (!clienteParametro.getValUsuarioMarcado().equals("D")) {
                this.registrarDirectorioGlobal(cliente, clienteParametro);
            }
        }
        catch (Exception e) {
            this.usuarioEnProceso.setEstado(Character.valueOf('I'));
            this.excluidosFacade.edit(this.usuarioEnProceso);
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteParametro.getTipoCliente() + clienteParametro.getNumeroIdentificacion(), "Advertencia registrarClienteMasterNatural()", (Throwable)e);
            registrado = false;
        }
        return registrado;
    }

    private String obtenerNivelAutorizacion(int numeroCliente, String usuarioAsociado) {
        String retorno = "";
        try {
            List<IBAutorizacionesSIP> autorizacionesIB = this.autorizacionesSIPIBFacade.obtenerNivelesAutorizacion(numeroCliente, usuarioAsociado);
            List<String> nivelesAutorizacionNBL = Arrays.asList(this.parametrosFacade.porCodigo("migracion.flujo.aprobacion.tipos.aprobador").split(","));
            int contadorNivelesAsignados = 0;
            for (IBAutorizacionesSIP nivelIB : autorizacionesIB) {
                if (!nivelesAutorizacionNBL.contains(nivelIB.getNivelAutorizacion())) continue;
                retorno = nivelIB.getNivelAutorizacion();
                ++contadorNivelesAsignados;
            }
            if (contadorNivelesAsignados > 1) {
                retorno = "";
            }
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), String.valueOf(numeroCliente), "Advertencia obtenerNivelAutorizacion()", (Throwable)e);
        }
        return retorno;
    }

    private String obtenerNivelAutorizacionNomina(int numeroCliente, String usuarioAsociado) {
        String nivel = null;
        List<IBAutorizacionesSIP> autorizacionesIB = this.autorizacionesSIPIBFacade.obtenerNivelesAutorizacion(numeroCliente, usuarioAsociado);
        if (autorizacionesIB != null && !autorizacionesIB.isEmpty()) {
            block14 : for (IBAutorizacionesSIP autorizacionIB : autorizacionesIB) {
                String nivelAutorizacionTemp;
                String codigoTipoContrato = autorizacionIB.getCodigoTipoContrato();
                if (!codigoTipoContrato.equalsIgnoreCase("NS")) continue;
                switch (nivelAutorizacionTemp = autorizacionIB.getNivelAutorizacion()) {
                    case "A1": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a1");
                        continue block14;
                    }
                    case "A2": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a2");
                        continue block14;
                    }
                    case "A3": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a3");
                        continue block14;
                    }
                    case "A4": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a4");
                        continue block14;
                    }
                    case "SO": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.sol");
                        continue block14;
                    }
                }
                nivel = null;
            }
        }
        return nivel;
    }

    private String obtenerNivelAutorizacionProveedores(int numeroCliente, String usuarioAsociado) {
        String nivel = null;
        List<IBAutorizacionesSIP> autorizacionesIB = this.autorizacionesSIPIBFacade.obtenerNivelesAutorizacion(numeroCliente, usuarioAsociado);
        if (autorizacionesIB != null && !autorizacionesIB.isEmpty()) {
            block14 : for (IBAutorizacionesSIP autorizacionIB : autorizacionesIB) {
                String nivelAutorizacionTemp;
                String codigoTipoContrato = autorizacionIB.getCodigoTipoContrato();
                if (!codigoTipoContrato.equalsIgnoreCase("OS")) continue;
                switch (nivelAutorizacionTemp = autorizacionIB.getNivelAutorizacion()) {
                    case "A1": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a1");
                        continue block14;
                    }
                    case "A2": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a2");
                        continue block14;
                    }
                    case "A3": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a3");
                        continue block14;
                    }
                    case "A4": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.a4");
                        continue block14;
                    }
                    case "SO": {
                        nivel = this.parametrosFacade.porCodigo("flujo.aprobacion.aprobador.nom.prov.sol");
                        continue block14;
                    }
                }
                nivel = null;
            }
        }
        return nivel;
    }

    private ArrayList<Integer> agregarOpcionesPerfilGenerales(LinkedHashMap<String, ArrayList<Integer>> mapaGeneral, int numeroCliente, String usuarioAsociado) throws Exception {
        ArrayList<Integer> listaRetorno = new ArrayList<Integer>();
        List<IBPerfilConfiguracionAcceso> listaOpcionesBase = this.perfilConfiguracionAccesoIBFacade.listarRegistrosMigrar(numeroCliente, usuarioAsociado);
        for (IBPerfilConfiguracionAcceso opcion : listaOpcionesBase) {
            if (opcion.getCreditos() == 'S' && null != mapaGeneral.get("E2M2M2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2M2M2"));
            }
            if (opcion.getTarjetas() == 'S' && null != mapaGeneral.get("E2M3M2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2M3M2"));
            }
            if (opcion.getPagos() == 'S' && null != mapaGeneral.get("E2M4M2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2M4M2"));
            }
            if (opcion.getConsultas() == 'S' && null != mapaGeneral.get("E2M5M2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2M5M2"));
            }
            if (opcion.getCheques() == 'S' && null != mapaGeneral.get("E2M6M2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2M6M2"));
            }
            if (opcion.getDesconocidoUno() == 'S' && null != mapaGeneral.get("E2M7M2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2M7M2"));
            }
            if (opcion.getDesconocidoDos() == 'S' && null != mapaGeneral.get("E2M8M2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2M8M2"));
            }
            if (opcion.getDesconocidoTres() == 'S' && null != mapaGeneral.get("E2M9M2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2M9M2"));
            }
            if (opcion.getDesconocidoCuatro() == 'S' && null != mapaGeneral.get("E2MAM2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2MAM2"));
            }
            if (opcion.getDesconocidoCinco() == 'S' && null != mapaGeneral.get("E2MBM2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2MBM2"));
            }
            if (opcion.getDeconocidoSeis() == 'S' && null != mapaGeneral.get("E2MCM2")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2MCM2"));
            }
            if (opcion.getDesconocidoSiete() != 'S' || null == mapaGeneral.get("E2MPM2")) continue;
            listaRetorno.addAll((Collection)mapaGeneral.get("E2MPM2"));
        }
        return listaRetorno;
    }

    private ArrayList<Integer> agregarPermisosProducto(LinkedHashMap<String, ArrayList<Integer>> mapaGeneral, ArrayList<Integer> permisosAsignados, IBPerfilCuentas perfilCuenta) throws Exception {
        ArrayList<Integer> listaRetorno = new ArrayList<Integer>(permisosAsignados);
        if (null != perfilCuenta.getDebitos() && perfilCuenta.getDebitos().equalsIgnoreCase("S")) {
            if (null != mapaGeneral.get("E2BUDB")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUDB"));
            }
        } else if (null != mapaGeneral.get("E2BUDB")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUDB"));
        }
        if (perfilCuenta.getCreditoCuenta() == 'S') {
            if (null != mapaGeneral.get("E2CRED")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2CRED"));
            }
        } else if (null != mapaGeneral.get("E2CRED")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2CRED"));
        }
        if (perfilCuenta.getNivelSaldos() == 'S') {
            if (null != mapaGeneral.get("E2BUSA")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUSA"));
            }
        } else if (null != mapaGeneral.get("E2BUSA")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUSA"));
        }
        if (perfilCuenta.getNivelDeMovimiento() == 'S') {
            if (null != mapaGeneral.get("E2BUMO")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUMO"));
            }
        } else if (null != mapaGeneral.get("E2BUMO")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUMO"));
        }
        if (perfilCuenta.getNivelDeAuditoria() == 'S') {
            if (null != mapaGeneral.get("E2BUAU")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUAU"));
            }
        } else if (null != mapaGeneral.get("E2BUAU")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUAU"));
        }
        if (perfilCuenta.getNivelDeArchivos() == 'S') {
            if (null != mapaGeneral.get("E2BUAR")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUAR"));
            }
        } else if (null != mapaGeneral.get("E2BUAR")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUAR"));
        }
        if (perfilCuenta.getChequera() == 'S') {
            if (null != mapaGeneral.get("E2BUCH")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUCH"));
            }
        } else if (null != mapaGeneral.get("E2BUCH")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUCH"));
        }
        if (perfilCuenta.getDivisas() == 'S') {
            if (null != mapaGeneral.get("E2BUDI")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUDI"));
            }
        } else if (null != mapaGeneral.get("E2BUDI")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUDI"));
        }
        if (perfilCuenta.getNomina() == 'S') {
            if (null != mapaGeneral.get("E2BUNO")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUNO"));
            }
        } else if (null != mapaGeneral.get("E2BUNO")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUNO"));
        }
        if (perfilCuenta.getOrdenesDePago() == 'S') {
            if (null != mapaGeneral.get("E2BUOP")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUOP"));
            }
        } else if (null != mapaGeneral.get("E2BUOP")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUOP"));
        }
        if (perfilCuenta.getDesconocidoUno() == 'S') {
            if (null != mapaGeneral.get("E2BUOT")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUOT"));
            }
        } else if (null != mapaGeneral.get("E2BUOT")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUOT"));
        }
        if (perfilCuenta.getDesconocidoDos() == 'S') {
            if (null != mapaGeneral.get("E2BUCG")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUCG"));
            }
        } else if (null != mapaGeneral.get("E2BUCG")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUCG"));
        }
        if (perfilCuenta.getDesconocidoTres() == 'S') {
            if (null != mapaGeneral.get("E2BUIN")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUIN"));
            }
        } else if (null != mapaGeneral.get("E2BUIN")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUIN"));
        }
        if (perfilCuenta.getDesconocidoCuatro() == 'S') {
            if (null != mapaGeneral.get("E2BUPA")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BUPA"));
            }
        } else if (null != mapaGeneral.get("E2BUPA")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BUPA"));
        }
        if (perfilCuenta.getDesconocidoCinco() == 'S') {
            if (null != mapaGeneral.get("E2BU01")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BU01"));
            }
        } else if (null != mapaGeneral.get("E2BU01")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BU01"));
        }
        if (perfilCuenta.getDesconocidoSeis() == 'S') {
            if (null != mapaGeneral.get("E2BU02")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BU02"));
            }
        } else if (null != mapaGeneral.get("E2BU02")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BU02"));
        }
        if (perfilCuenta.getDesconocidoSiente() == 'S') {
            if (null != mapaGeneral.get("E2BU03")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BU03"));
            }
        } else if (null != mapaGeneral.get("E2BU03")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BU03"));
        }
        if (perfilCuenta.getDesconocidoOcho() == 'S') {
            if (null != mapaGeneral.get("E2BU04")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BU04"));
            }
        } else if (null != mapaGeneral.get("E2BU04")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BU04"));
        }
        if (perfilCuenta.getDesconocidoNueve() == 'S') {
            if (null != mapaGeneral.get("E2BU05")) {
                listaRetorno.addAll((Collection)mapaGeneral.get("E2BU05"));
            }
        } else if (null != mapaGeneral.get("E2BU05")) {
            listaRetorno.removeAll((Collection)mapaGeneral.get("E2BU05"));
        }
        return listaRetorno;
    }

    public boolean registrarClienteJuridico(ClienteInternetBanking clienteParametro, UsuariosTomadosAfiliacion usuarioTomado) {
        boolean registrado = true;
        try {
            Clientes cliente = null;
            BeanCliente clienteCore = this.daoCoreBancario.buscarCliente(String.valueOf(clienteParametro.getNumeroCliente()), this.clientesConfig.init());
            if (null != clienteCore) {
                cliente = this.clientesFacade.porCedula(clienteCore.getTipoIdentificacion().trim(), clienteCore.getIssuedIdentValue().trim());
                if (cliente == null) {
                    cliente = new Clientes();
                    cliente.setTipoIdentificacion(clienteCore.getTipoIdentificacion().trim());
                    cliente.setIdentificacion(clienteCore.getIssuedIdentValue().trim());
                    cliente.setNombre(clienteCore.getFullName().trim());
                    cliente.setZonaSeguraLogin(Character.valueOf("I".charAt(0)));
                    cliente.setCoreId(String.valueOf(clienteCore.getPartyID().trim()));
                    this.clientesFacade.create(cliente);
                }
            } else {
                registrado = false;
            }
            if (registrado) {
                if (!(clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("P") || clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("D") || clienteParametro.getValUsuarioMarcado().equalsIgnoreCase("F"))) {
                    PerfilesNbl perfilBase = this.seleccionarPerfilBase(clienteCore);
                    PerfilesNbl perfilUsuario = this.crearPerfilUsuarioUnico(perfilBase, cliente);
                    this.asociarUsuarioCliente(cliente, clienteParametro, perfilUsuario);
                }
                if (!clienteParametro.getValUsuarioMarcado().equals("D") && !clienteParametro.getValUsuarioMarcado().equals("F")) {
                    this.registrarDirectorioGlobal(cliente, clienteParametro);
                }
                if (!clienteParametro.getValUsuarioMarcado().equals("F")) {
                    this.registrarFlujoAprobacion(cliente, clienteParametro);
                }
            }
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteParametro.getTipoCliente() + clienteParametro.getNumeroIdentificacion(), "Advertencia registrarClienteJuridico()", (Throwable)e);
            this.usuarioEnProceso.setEstado(Character.valueOf('I'));
            this.excluidosFacade.edit(this.usuarioEnProceso);
            registrado = false;
        }
        return registrado;
    }

    private void registrarUsuarioBD(ClienteInternetBanking clienteIB) throws Exception {
        if (null == this.usuarioProceso) {
            String nacionalidadUsuario = clienteIB.getNacionalidad() == null || clienteIB.getNacionalidad().trim().isEmpty() ? (null != this.clienteCoreBancario.getNacionality() && this.clienteUbicadoPorDatosPropios ? this.clienteCoreBancario.getNacionality() : this.parametrosFacade.porCodigo("pais.actual.codigo.core")) : clienteIB.getNacionalidad();
            Paises pais = this.paisesFacade.findByCodigoCore(nacionalidadUsuario.trim());
            UsuariosNbl usuario = new UsuariosNbl();
            usuario.setTipoIdentificacion(clienteIB.getTipoIdentificacion().trim());
            usuario.setIdentificacion(clienteIB.getNumeroIdentificacion().trim());
            usuario.setPaisId(pais);
            if (null != this.clienteCoreBancario.getNamePrefix() && this.clienteUbicadoPorDatosPropios) {
                usuario.setNombre(this.clienteCoreBancario.getFullName().trim());
            } else {
                usuario.setNombre(clienteIB.getNombreCliente().trim());
            }
            usuario.setEstatus(Character.valueOf('K'));
            usuario.setFechaAfiliacion(Calendar.getInstance().getTime());
            usuario.setIdiomaId(this.idiomasFacade.porCodigo("es"));
            usuario.setBancoId((Bancos)this.bancosFacade.find(1L));
            usuario.setLogin("m+" + clienteIB.getTipoIdentificacion().trim() + clienteIB.getNumeroIdentificacion().trim());
            usuario.setSessionEquipoFrecuente(Character.valueOf('I'));
            StringBuilder sharedKey = new StringBuilder("");
            if (this.clienteUbicadoPorDatosPropios) {
                sharedKey.append(this.clienteCoreBancario.getBankID().trim());
                sharedKey.append(this.clienteCoreBancario.getIssuedIdentType().trim());
                sharedKey.append(this.clienteCoreBancario.getIssuedIdentValue().trim());
                sharedKey.append(this.clienteCoreBancario.getIssuedDocType().trim());
                sharedKey.append(this.clienteCoreBancario.getCodPais().getCountryCodeSource().trim());
            } else {
                sharedKey.append(this.clienteCoreBancario.getBankID().trim());
                sharedKey.append(this.clienteProceso.getTipoIdentificacion().trim());
                sharedKey.append(this.clienteProceso.getNumeroIdentificacion().trim());
                sharedKey.append(this.clienteCoreBancario.getIssuedDocType().trim());
                sharedKey.append(this.clienteCoreBancario.getCodPais().getCountryCodeSource().trim());
            }
            usuario.setSharedkey(sharedKey.toString().trim());
            this.usuarioNBLFacade.create(usuario);
            this.setUsuarioProceso(usuario);
        }
    }

    private void asociarUsuarioCliente(Clientes clienteRegistrado, ClienteInternetBanking clienteIB, PerfilesNbl perfilUsuario) throws Exception {
        Clientes clientesUsuarios = clienteIB.esClienteMaster() && clienteIB.esClienteNatural() ? this.clientesFacade.porCedula(this.clienteProceso.getTipoIdentificacion(), this.clienteProceso.getNumeroIdentificacion()) : this.clientesFacade.porCedula(clienteRegistrado.getTipoIdentificacion(), clienteRegistrado.getIdentificacion());
        boolean found = false;
        if (clientesUsuarios != null && clientesUsuarios.getClientesHasUsuariosNblCollection() != null) {
            for (ClientesHasUsuariosNbl i : clientesUsuarios.getClientesHasUsuariosNblCollection()) {
                if (!Objects.equals(i.getClientes().getId(), clientesUsuarios.getId()) || !Objects.equals(i.getUsuariosNbl().getId(), this.usuarioProceso.getId())) continue;
                found = true;
                break;
            }
        }
        if (!found) {
            ClientesHasUsuariosNbl clienteUsuario = new ClientesHasUsuariosNbl();
            ClientesHasUsuariosNblPK pk = new ClientesHasUsuariosNblPK();
            pk.setClientesId(clienteRegistrado.getId());
            pk.setUsuariosNblId(this.usuarioProceso.getId());
            clienteUsuario.setClientesHasUsuariosNblPK(pk);
            clienteUsuario.setClientes(clienteRegistrado);
            clienteUsuario.setUsuariosNbl(this.usuarioProceso);
            clienteUsuario.setEstatus(Character.valueOf('A'));
            clienteUsuario.setPerfilesNblId(perfilUsuario);
            clienteUsuario.setZonaSegura(0);
            clienteUsuario.setFkIdtipoenvio(this.mediosenvioFacade.porCodigo(3));
            if (clienteRegistrado.getClientesHasUsuariosNblCollection() != null) {
                clienteRegistrado.getClientesHasUsuariosNblCollection().add(clienteUsuario);
            } else {
                ArrayList<ClientesHasUsuariosNbl> clienteUsuarioCollection = new ArrayList<ClientesHasUsuariosNbl>();
                clienteUsuarioCollection.add(clienteUsuario);
                clienteRegistrado.setClientesHasUsuariosNblCollection(clienteUsuarioCollection);
            }
            this.clientesFacade.edit(clienteRegistrado);
        }
        clienteIB.setValUsuarioMarcado("P");
        this.clientesMigrarFacade.edit(clienteIB);
    }

    private void asociarUsuarioCliente(Clientes clienteRegistrado, ClienteInternetBanking clienteIB, PerfilesNbl perfilUsuario, UsuariosNbl usuarioAsociado) throws Exception {
        Clientes clientesUsuarios = clienteIB.esClienteMaster() && clienteIB.esClienteNatural() ? this.clientesFacade.porCedula(this.clienteProceso.getTipoIdentificacion(), this.clienteProceso.getNumeroIdentificacion()) : this.clientesFacade.porCedula(clienteRegistrado.getTipoIdentificacion(), clienteRegistrado.getIdentificacion());
        boolean found = false;
        if (clientesUsuarios != null && clientesUsuarios.getClientesHasUsuariosNblCollection() != null) {
            for (ClientesHasUsuariosNbl i : clientesUsuarios.getClientesHasUsuariosNblCollection()) {
                if (!Objects.equals(i.getClientes().getId(), clientesUsuarios.getId()) || !Objects.equals(i.getUsuariosNbl().getId(), usuarioAsociado.getId())) continue;
                found = true;
                break;
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
            clienteUsuario.setEstatus(Character.valueOf('A'));
            clienteUsuario.setPerfilesNblId(perfilUsuario);
            clienteUsuario.setFkIdtipoenvio(this.mediosenvioFacade.porCodigo(3));
            if (clienteRegistrado.getClientesHasUsuariosNblCollection() != null) {
                clienteRegistrado.getClientesHasUsuariosNblCollection().add(clienteUsuario);
            } else {
                ArrayList<ClientesHasUsuariosNbl> clienteUsuarioCollection = new ArrayList<ClientesHasUsuariosNbl>();
                clienteUsuarioCollection.add(clienteUsuario);
                clienteRegistrado.setClientesHasUsuariosNblCollection(clienteUsuarioCollection);
            }
            this.clientesFacade.edit(clienteRegistrado);
        }
    }

    public ClientesHasUsuariosNbl ubicarClienteHasUsuario() throws Exception {
        UsuariosNbl usuario = this.usuarioNBLFacade.porIdentificacion(this.clienteProceso.getTipoIdentificacion().trim(), this.clienteProceso.getNumeroIdentificacion().trim(), 'D');
        this.setUsuarioProceso(usuario);
        for (ClientesHasUsuariosNbl cliente : usuario.getClientesHasUsuariosNblCollection()) {
            if (!cliente.getClientes().getCoreId().equalsIgnoreCase(String.valueOf(this.clienteProceso.getNumeroCliente()))) continue;
            return cliente;
        }
        return null;
    }

    private void registrarDirectorioGlobal(Clientes clienteRegistrado, ClienteInternetBanking clienteBancaAntigua) throws Exception {
        try {
            //Set llavesMapa;
            //LinkedHashMap mapaBeneficiarios;
            //IBDirectorioCuenta beneficiarioRegistro;
            //ArrayList listaProductosContactos;
            //LinkedHashMap<String, Serializable> mapaDepurado;
            this.registrarDirectorioPropio(clienteRegistrado);
            List<IBDirectorioCuenta> listaBeneficiariosCuenta = this.directorioCuentaIBFacade.listrarRegistrosMigrar(Integer.parseInt(clienteRegistrado.getCoreId().trim()));
            List<IBDirectorioTarjeta> listaBeneficiariosTarjeta = this.directorioTarjetaIBFacade.listarProductosMigrar(Integer.parseInt(clienteRegistrado.getCoreId().trim()));
            LinkedHashMap<String, Bancos> mapaBancos = new LinkedHashMap<String, Bancos>();
            List<String> listaTiposIdValidos = Arrays.asList(this.parametrosFacade.porCodigo("tipos.identificacion").split(","));
            if (null != listaBeneficiariosCuenta && listaBeneficiariosCuenta.size() > 0) {
                LinkedHashMap<BigDecimal, ArrayList<IBDirectorioCuenta>> mapaBeneficiarios = new LinkedHashMap<>();
                LinkedHashMap<String, IBDirectorioCuenta> mapaDepurado = new LinkedHashMap();
                for (IBDirectorioCuenta datoRegistro : listaBeneficiariosCuenta) {
                    mapaDepurado.put(datoRegistro.getCuentaTransaccion(), datoRegistro);
                }
                for (IBDirectorioCuenta registroDirectorioCuenta : mapaDepurado.values()) {
                    if (null != mapaBeneficiarios.get(registroDirectorioCuenta.getIdentificacionBeneficiario())) {
                        ((ArrayList)mapaBeneficiarios.get(registroDirectorioCuenta.getIdentificacionBeneficiario())).add(registroDirectorioCuenta);
                        continue;
                    }
                    ArrayList<IBDirectorioCuenta> listaDirectorioCuenta = new ArrayList<IBDirectorioCuenta>();
                    listaDirectorioCuenta.add(registroDirectorioCuenta);
                    mapaBeneficiarios.put(registroDirectorioCuenta.getIdentificacionBeneficiario(), listaDirectorioCuenta);
                }
                Set<BigDecimal> llavesMapa = mapaBeneficiarios.keySet();
                CategoriaProductos productoCuenta = this.categoriaProductoFacade.porCodigo("cta");
                for (BigDecimal llave : llavesMapa) {
                    try {
                        if (null == llave || llave.compareTo(new BigDecimal(0)) <= 0) continue;
                        ArrayList<IBDirectorioCuenta> listaProductosContactos = mapaBeneficiarios.get(llave);
                        IBDirectorioCuenta beneficiarioRegistro = listaProductosContactos.get(0);
                        boolean continuar = true;
                        if (null == beneficiarioRegistro.getTipoIdentificacionBeneficiario() || !listaTiposIdValidos.contains(beneficiarioRegistro.getTipoIdentificacionBeneficiario().trim())) {
                            continuar = false;
                        }
                        if (!continuar) continue;
                        DirectorioGlobal directorioCliente = this.obtenerDirectorioCliente(beneficiarioRegistro.getTipoIdentificacionBeneficiario(), beneficiarioRegistro.getIdentificacionBeneficiario().toPlainString().trim(), beneficiarioRegistro.getNombreBeneficiario(), clienteRegistrado);
                        for (IBDirectorioCuenta producto : listaProductosContactos) {
                            String codigoBancoProceso = producto.getBancoTransaccion().trim();
                            if (null == codigoBancoProceso || codigoBancoProceso.trim().isEmpty()) {
                                codigoBancoProceso = "0116";
                            }
                            this.procesarProducto(directorioCliente, productoCuenta, mapaBancos, codigoBancoProceso, this.fillZerosLeft(producto.getCuentaTransaccion(), 20));
                        }
                    }
                    catch (Exception e) {
                        Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioGlobal()", (Throwable)e);
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
                        ((ArrayList)mapaBeneficiarios.get(registroDirectorioTarjeta.getIdentificacionBeneficiario())).add(registroDirectorioTarjeta);
                        continue;
                    }
                    ArrayList<IBDirectorioTarjeta> listaDirectorioTarjeta = new ArrayList<IBDirectorioTarjeta>();
                    listaDirectorioTarjeta.add(registroDirectorioTarjeta);
                    mapaBeneficiarios.put(registroDirectorioTarjeta.getIdentificacionBeneficiario(), listaDirectorioTarjeta);
                }
                Set<BigDecimal> llavesMapa = mapaBeneficiarios.keySet();
                CategoriaProductos productoTarjeta = this.categoriaProductoFacade.porCodigo("tdc");
                for (BigDecimal llave : llavesMapa) {
                    try {
                        if (null == llave || llave.compareTo(new BigDecimal(0)) <= 0) continue;
                        ArrayList<IBDirectorioTarjeta> listaProductosContactos = mapaBeneficiarios.get(llave);
                        IBDirectorioTarjeta beneficiarioRegistro = listaProductosContactos.get(0);
                        DirectorioGlobal directorioCliente = this.obtenerDirectorioCliente(beneficiarioRegistro.getTipoIdentificacionBeneficiario(), beneficiarioRegistro.getIdentificacionBeneficiario().toPlainString().trim(), beneficiarioRegistro.getNombreBeneficiario(), clienteRegistrado);
                        for (IBDirectorioTarjeta producto : listaProductosContactos) {
                            String codigoBancoProceso = producto.getCodigoBancoBeneficiario().trim();
                            if (null == codigoBancoProceso || codigoBancoProceso.trim().isEmpty()) {
                                codigoBancoProceso = "0116";
                            }
                            this.procesarProducto(directorioCliente, productoTarjeta, mapaBancos, codigoBancoProceso, producto.getTarjetaBeneficiario());
                        }
                    }
                    catch (Exception e) {
                        Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioGlobal() Error creando cliente en tarjetas", (Throwable)e);
                    }
                }
            }
            clienteBancaAntigua.setValUsuarioMarcado("D");
            this.clientesMigrarFacade.edit(clienteBancaAntigua);
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioGlobal() Error creando el directorio global", (Throwable)e);
            throw e;
        }
    }

    private void registrarDirectorioPropio(Clientes clienteRegistrado) throws Exception {
        try {
            boolean encontrado;
            DirectorioGlobal d = clienteRegistrado.getDirectorioPropio();
            if (d == null) {
                d = new DirectorioGlobal();
                d.setClientesId(clienteRegistrado);
                d.setEstatus(Character.valueOf('A'));
                d.setNombre(clienteRegistrado.getNombre());
                d.setTipoIdentificacion(clienteRegistrado.getTipoIdentificacion());
                d.setIdentificacion(clienteRegistrado.getIdentificacion());
                this.directorioGlobalFacade.create(d);
            }
            PosicionConsolidadaBean posicionConsolidada = this.clientesDao.posicionConsolidada(clienteRegistrado.getCoreId(), this.clientesConfig.init());
            CategoriaProductos tipoCuenta = this.categoriaProductoFacade.porCodigo("cta");
            for (BeanCuentas i : posicionConsolidada.getCuentas()) {
                Monedas moneda = this.monedasFacade.porCodigo(i.getCodigoMoneda().getCodigo());
                encontrado = false;
                boolean estadoActivo = null != i.getAcctStatus() && !i.getAcctStatus().equalsIgnoreCase(this.parametrosFacade.porCodigo("producto.estado.cerrado"));
                Productos valorar = null;
                if (d.getProductosCollection() != null) {
                    for (Productos j : d.getProductosCollection()) {
                        if (!j.getNumero().equals(i.getId())) continue;
                        encontrado = true;
                        if (j.getCodigoMoneda() == null && moneda != null) {
                            j.setCodigoMoneda(moneda);
                            this.productosFacade.edit(j);
                        }
                        valorar = j;
                        break;
                    }
                }
                if (!encontrado && estadoActivo) {
                    Productos p = new Productos();
                    p.setCategoriaProductosId(tipoCuenta);
                    p.setDirectorioGlobalId(d);
                    p.setEstatus(Character.valueOf(i.getAcctStatus().charAt(0)));
                    p.setNumero(i.getId());
                    p.setBancosId(this.usuarioProceso.getBancoId());
                    if (moneda != null) {
                        p.setCodigoMoneda(moneda);
                    }
                    this.productosFacade.create(p);
                    d.getProductosCollection().add(p);
                    continue;
                }
                if (estadoActivo || !encontrado) continue;
                d.getProductosCollection().remove(valorar);
                this.productosFacade.remove(valorar);
            }
            CategoriaProductos tipoTDC = this.categoriaProductoFacade.porCodigo("tdc");
            for (BeanTdc i : posicionConsolidada.getTdcs()) {
                encontrado = false;
                Productos valorar = null;
                if (d.getProductosCollection() != null) {
                    for (Productos j : d.getProductosCollection()) {
                        if (!j.getNumero().equals(i.getCardNum())) continue;
                        encontrado = true;
                        valorar = j;
                        break;
                    }
                }
                if (encontrado) continue;
                Productos p = new Productos();
                p.setCategoriaProductosId(tipoTDC);
                p.setDirectorioGlobalId(d);
                p.setEstatus(Character.valueOf('A'));
                p.setNumero(i.getCardNum());
                p.setBancosId(this.usuarioProceso.getBancoId());
                this.productosFacade.create(p);
                d.getProductosCollection().add(p);
            }
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioPropio() Error creando el directorio global propio", (Throwable)e);
            throw e;
        }
    }

    private void registrarFlujoAprobacion(Clientes clienteRegistrado, ClienteInternetBanking clienteBancaAntigua) throws Exception {
        try {
            List<IBCabeceraFlujo> listaCabecera = this.cabececeraFlujoIBFacade.listarRegistrosMigrar(Integer.parseInt(clienteRegistrado.getCoreId()));
            ClientesHasUsuariosNbl clienteAsociado = null;
            for (ClientesHasUsuariosNbl iter : clienteRegistrado.getClientesHasUsuariosNblCollection()) {
                if (!iter.getClientes().getId().equals(clienteRegistrado.getId()) || !iter.getUsuariosNbl().getId().equals(this.usuarioProceso.getId())) continue;
                clienteAsociado = iter;
                break;
            }
            if (clienteAsociado != null) {
                for (IBCabeceraFlujo iterador : listaCabecera) {
                    String idFlujo;
                    Cnfflujos flujo = new Cnfflujos();
                    flujo.setClientesHasUsuariosNbl(clienteAsociado);
                    flujo.setValmontomin(iterador.getMontoInicial());
                    flujo.setValmontomax(iterador.getMontoFinal());
                    flujo.setVersion(Long.parseLong("1"));
                    flujo.setValestadoflujo(Character.valueOf('A'));
                    if (iterador.getTipoContrato().trim().equals("400")) {
                        idFlujo = "nomina" + iterador.getConsecutivoNumeroContrato() + " Contrato:" + iterador.getTipoContrato() + this.completarZerosIzquierda(iterador.getNumeroContrato().toString(), 11) + this.completarZerosIzquierda(String.valueOf(iterador.getConsecutivoNumeroContrato()), 3);
                        Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getCoreId(), "***** Flujo de firmas a migrar " + idFlujo);
                        flujo.setDesflujo(idFlujo);
                        flujo.setFkIdoperacionnbl(this.operacionesNblFacade.porCodigo(BodBaseBean.Funcionalidad.PAGOS_NOMINA_INDIVIDUAL_EXITOSO.getCodigoOperacion()));
                    } else {
                        idFlujo = "proveedores" + iterador.getConsecutivoNumeroContrato() + " Contrato:" + iterador.getTipoContrato() + this.completarZerosIzquierda(iterador.getNumeroContrato().toString(), 11) + this.completarZerosIzquierda(String.valueOf(iterador.getConsecutivoNumeroContrato()), 3);
                        Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getCoreId(), "***** Flujo de firmas a migrar " + idFlujo);
                        flujo.setDesflujo(idFlujo);
                        flujo.setFkIdoperacionnbl(this.operacionesNblFacade.porCodigo(BodBaseBean.Funcionalidad.PAGOS_PROVEEDORES_INDIVIDUAL_EXITOSO.getCodigoOperacion()));
                    }
                    List<IBDetalleFlujo> listaFlujoAsociado = this.flujoAprobacionDetalleIBFacade.listarRegistrosAsociados(Integer.parseInt(clienteRegistrado.getCoreId()), iterador.getTipoOperacion(), iterador.getConsecutivoDetalle());
                    for (IBDetalleFlujo detalleFlujo : listaFlujoAsociado) {
                        MstOrdenFlujo mstOrdenFlujo;
                        String tipoAutorizacion;
                        Detflujoproceso detFlujoProceso = new Detflujoproceso();
                        detFlujoProceso.setFkIdflujo(flujo);
                        detFlujoProceso.setNumproceso(new BigInteger(detalleFlujo.getCodigoSubProceso().replace("P", "").trim()));
                        detFlujoProceso.setTipofirma(Character.valueOf(detalleFlujo.getCodigoTipoFirma().trim().charAt(0)));
                        detFlujoProceso.setSustitucion(Integer.parseInt(detalleFlujo.getValorSustitucionFirma().replace("N", "0").replace("S", "1")));
                        ArrayList<MstOrdenFlujo> mstOrdenFlujoList = new ArrayList<MstOrdenFlujo>();
                        ArrayList<String> tiposAutorizacionAgregados = new ArrayList<String>();
                        int ordenEjecucion = 1;
                        if (detalleFlujo.getPrimerTipoAutorizacion() != null && !detalleFlujo.getPrimerTipoAutorizacion().trim().isEmpty() && !tiposAutorizacionAgregados.contains(tipoAutorizacion = detalleFlujo.getPrimerTipoAutorizacion().replace("P", "A"))) {
                            tiposAutorizacionAgregados.add(tipoAutorizacion);
                            mstOrdenFlujo = new MstOrdenFlujo();
                            mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(this.rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                            mstOrdenFlujo.setFkIdFlujoProceso(detFlujoProceso);
                            mstOrdenFlujo.setOrdenEjecucion(ordenEjecucion++);
                            mstOrdenFlujo.setRol(tipoAutorizacion);
                            mstOrdenFlujoList.add(mstOrdenFlujo);
                        }
                        if (detalleFlujo.getSegundoTipoAutorizacion() != null && !detalleFlujo.getSegundoTipoAutorizacion().trim().isEmpty() && !tiposAutorizacionAgregados.contains(tipoAutorizacion = detalleFlujo.getSegundoTipoAutorizacion().replace("P", "A"))) {
                            tiposAutorizacionAgregados.add(tipoAutorizacion);
                            mstOrdenFlujo = new MstOrdenFlujo();
                            mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(this.rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                            mstOrdenFlujo.setFkIdFlujoProceso(detFlujoProceso);
                            mstOrdenFlujo.setOrdenEjecucion(ordenEjecucion++);
                            mstOrdenFlujo.setRol(tipoAutorizacion);
                            mstOrdenFlujoList.add(mstOrdenFlujo);
                        }
                        if (detalleFlujo.getTercerTipoAutorizacion() != null && !detalleFlujo.getTercerTipoAutorizacion().trim().isEmpty() && !tiposAutorizacionAgregados.contains(tipoAutorizacion = detalleFlujo.getTercerTipoAutorizacion().replace("P", "A"))) {
                            tiposAutorizacionAgregados.add(tipoAutorizacion);
                            mstOrdenFlujo = new MstOrdenFlujo();
                            mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(this.rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                            mstOrdenFlujo.setFkIdFlujoProceso(detFlujoProceso);
                            mstOrdenFlujo.setOrdenEjecucion(ordenEjecucion++);
                            mstOrdenFlujo.setRol(tipoAutorizacion);
                            mstOrdenFlujoList.add(mstOrdenFlujo);
                        }
                        if (detalleFlujo.getCuartoTipoAutorizacion() != null && !detalleFlujo.getCuartoTipoAutorizacion().trim().isEmpty() && !tiposAutorizacionAgregados.contains(tipoAutorizacion = detalleFlujo.getCuartoTipoAutorizacion().replace("P", "A"))) {
                            tiposAutorizacionAgregados.add(tipoAutorizacion);
                            mstOrdenFlujo = new MstOrdenFlujo();
                            mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(this.rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                            mstOrdenFlujo.setFkIdFlujoProceso(detFlujoProceso);
                            mstOrdenFlujo.setOrdenEjecucion(ordenEjecucion++);
                            mstOrdenFlujo.setRol(tipoAutorizacion);
                            mstOrdenFlujoList.add(mstOrdenFlujo);
                        }
                        detFlujoProceso.setMstOrdenFlujoList(mstOrdenFlujoList);
                        if (flujo.getDetflujoprocesoList() == null) {
                            flujo.setDetflujoprocesoList(new ArrayList<Detflujoproceso>());
                        }
                        flujo.getDetflujoprocesoList().add(detFlujoProceso);
                    }
                    this.flujosFacade.create(flujo);
                    if (!flujo.getFkIdoperacionnbl().getCodigo().contains("nomina") && !flujo.getFkIdoperacionnbl().getCodigo().contains("proveedor")) continue;
                    Cnfflujos flujoEspejo = flujo;
                    flujoEspejo.setPkIdflujo(Long.parseLong(this.rEFFlujoIdFacade.generarREFFlujoId()));
                    for (Detflujoproceso flujoProceso : flujo.getDetflujoprocesoList()) {
                        flujoProceso.setPkIdflujoproceso(Long.parseLong(this.rEFFlujoIdFacade.generarREFFlujoProcesoId()));
                        flujoProceso.setFkIdflujo(flujoEspejo);
                        for (MstOrdenFlujo mstOrdenFlujo : flujoProceso.getMstOrdenFlujoList()) {
                            mstOrdenFlujo.setPkIdOrdenFlujo(Long.parseLong(this.rEFFlujoIdFacade.generarREFOrdenFlujoId()));
                            mstOrdenFlujo.setFkIdFlujoProceso(flujoProceso);
                        }
                    }
                    if (flujo.getFkIdoperacionnbl().getCodigo().contains("nomina")) {
                        flujoEspejo.setFkIdoperacionnbl(flujo.getFkIdoperacionnbl().getCodigo().equals(BodBaseBean.Funcionalidad.PAGOS_NOMINA_INDIVIDUAL_EXITOSO.getCodigoOperacion()) ? this.operacionesNblFacade.porCodigo(BodBaseBean.Funcionalidad.PAGOS_NOMINA_MASIVO_EXITOSO.getCodigoOperacion()) : this.operacionesNblFacade.porCodigo(BodBaseBean.Funcionalidad.PAGOS_NOMINA_INDIVIDUAL_EXITOSO.getCodigoOperacion()));
                    } else if (flujo.getFkIdoperacionnbl().getCodigo().contains("proveedor")) {
                        flujoEspejo.setFkIdoperacionnbl(flujo.getFkIdoperacionnbl().getCodigo().equals(BodBaseBean.Funcionalidad.PAGOS_PROVEEDORES_INDIVIDUAL_EXITOSO.getCodigoOperacion()) ? this.operacionesNblFacade.porCodigo(BodBaseBean.Funcionalidad.PAGOS_PROVEEDORES_MASIVO_EXITOSO.getCodigoOperacion()) : this.operacionesNblFacade.porCodigo(BodBaseBean.Funcionalidad.PAGOS_PROVEEDORES_INDIVIDUAL_EXITOSO.getCodigoOperacion()));
                    }
                    this.flujosFacade.create(flujoEspejo);
                }
            }
            clienteBancaAntigua.setValUsuarioMarcado("F");
            this.clientesMigrarFacade.edit(clienteBancaAntigua);
        }
        catch (Exception e) {
            Log.getInstance().error(LogNBL.MIGRAR.getCodigo(), clienteRegistrado.getTipoIdentificacion() + clienteRegistrado.getIdentificacion(), "Advertencia registrarDirectorioPropio() Error creando el flujo de aprobacion", (Throwable)e);
        }
    }

    public String completarZerosIzquierda(String cadena, int length) {
        String retorno = cadena;
        if (null != cadena) {
            int stringLength = cadena.length();
            int numberZeros = length - stringLength;
            if (numberZeros >= 0) {
                for (int j = 0; j < numberZeros; ++j) {
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
            if (mapaBancos.get(this.fillZerosLeft(bancoProducto, 4)) == null) {
                bancoAsociado = this.bancosFacade.porCodigo(this.fillZerosLeft(bancoProducto, 4));
                mapaBancos.put(bancoProducto.trim(), bancoAsociado);
            } else {
                bancoAsociado = mapaBancos.get(this.fillZerosLeft(bancoProducto, 4));
            }
        }
        if (null != numeroProducto && !numeroProducto.isEmpty() && !this.existeProducto(directorioCliente, numeroProducto)) {
            this.registrarProducto(directorioCliente, categoria, bancoAsociado, numeroProducto);
        }
    }

    private void registrarProducto(DirectorioGlobal directorioCliente, CategoriaProductos categoria, Bancos bancoAsociado, String numeroProducto) throws Exception {
        Productos p = new Productos();
        p.setCategoriaProductosId(categoria);
        p.setDirectorioGlobalId(directorioCliente);
        p.setEstatus(Character.valueOf('A'));
        p.setNumero(numeroProducto);
        p.setBancosId(bancoAsociado);
        this.productosFacade.create(p);
    }

    private boolean existeProducto(DirectorioGlobal directorioCliente, String numeroProducto) throws Exception {
        boolean encontrado = false;
        if (directorioCliente.getProductosCollection() != null) {
            for (Productos j : directorioCliente.getProductosCollection()) {
                if (!j.getNumero().equals(numeroProducto)) continue;
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    private DirectorioGlobal obtenerDirectorioCliente(String tipoIdentificacion, String identificacion, String nombreBeneficiario, Clientes clienteRegistrado) throws Exception {
        DirectorioGlobal directorioCliente = this.directorioGlobalFacade.porIdentificacionYCliente(tipoIdentificacion.trim(), identificacion.trim(), clienteRegistrado);
        if (null == directorioCliente) {
            directorioCliente = new DirectorioGlobal();
            directorioCliente.setAlias(tipoIdentificacion.trim() + identificacion.trim());
            directorioCliente.setClientesId(clienteRegistrado);
            directorioCliente.setEstatus(Character.valueOf('A'));
            directorioCliente.setNombre(nombreBeneficiario);
            directorioCliente.setTipoIdentificacion(tipoIdentificacion.trim());
            directorioCliente.setIdentificacion(identificacion.trim());
            this.directorioGlobalFacade.create(directorioCliente);
        }
        return directorioCliente;
    }

    private String fillZerosLeft(String string, int length) {
        int stringLength = string.length();
        int numberZeros = length - stringLength;
        if (numberZeros >= 0) {
            for (int j = 0; j < numberZeros; ++j) {
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
        return this.clienteCoreBancario;
    }

    public void setClienteCoreBancario(BeanCliente clienteCoreBancario) {
        this.clienteCoreBancario = clienteCoreBancario;
    }

    public List<String> getListaTiposIdentificacion() {
        if (null == this.listaTiposIdentificacion) {
            this.listaTiposIdentificacion = new ArrayList<String>();
            try {
                this.listaTiposIdentificacion = Arrays.asList(this.parametrosFacade.porCodigo("tipos.identificacion").split(","));
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        return this.listaTiposIdentificacion;
    }

    public String getCodigoNacionalidadVzla() {
        return "001";
    }

    public void setCodigoNacionalidadVzla(String codigoNacionalidadVzla) {
        this.codigoNacionalidadVzla = codigoNacionalidadVzla;
    }

    public List<Paises> getListaPaises() {
        if (this.listaPaises == null) {
            this.listaPaises = this.paisesFacade.todos();
        }
        return this.listaPaises;
    }

    public void setListaPaises(List<Paises> listaPaises) {
        this.listaPaises = listaPaises;
    }

    public void setListaTiposIdentificacion(List<String> listaTiposIdentificacion) {
        this.listaTiposIdentificacion = listaTiposIdentificacion;
    }

    public int getParCantPreguntas() {
        return this.parCantPreguntas;
    }

    public void setParCantPreguntas(int parCantPreguntas) {
        this.parCantPreguntas = parCantPreguntas;
    }

    public int getParPersonalPreguntas() {
        return this.parPersonalPreguntas;
    }

    public void setParPersonalPreguntas(int parPersonalPreguntas) {
        this.parPersonalPreguntas = parPersonalPreguntas;
    }

    public Boolean mostrarPreguntasServicioEstatico(int cont) {
        return cont <= this.parCantPreguntas;
    }

    public Boolean mostrarTodasLasPreguntas(int cont) {
        return cont <= this.parCantPreguntas + this.parPersonalPreguntas;
    }

    public int getParVecesSelect() {
        return this.parVecesSelect;
    }

    public void setParVecesSelect(int parVecesSelect) {
        this.parVecesSelect = parVecesSelect;
    }

    public void setUsuarioProceso(UsuariosNbl usuarioProceso) {
        this.usuarioProceso = usuarioProceso;
    }

    public ClienteInternetBanking getClienteProceso() {
        return this.clienteProceso;
    }

    public void setClienteProceso(ClienteInternetBanking clienteProceso) {
        this.clienteProceso = clienteProceso;
    }

    public void limpiar() {
        this.usuarioIngreso = "";
        this.imagen = null;
        this.descripcionImagen = null;
        this.listadoImagenes = null;
        this.rutaImagenes = null;
        this.rutaDetectID = null;
        this.usuarioTomado = false;
        this.setClienteProceso(null);
        this.setUsuarioProceso(null);
        this.usuarioEnProceso = null;
    }

    public boolean migrarUsuario(String tipoIdentificacion, String identificacion, ExcluidosMigracion usuarioEnProceso, String idProceso) {
        this.limpiar();
        this.idProceso = idProceso;
        this.usuarioEnProceso = usuarioEnProceso;
        String llaveError = "";
        String redireccion = "";
        ClienteInternetBanking clienteMigrar = null;
        UsuariosNbl user = this.usuarioFacade.porSoloIdentificacion(tipoIdentificacion, identificacion.trim());
        Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), idProceso + "-> " + tipoIdentificacion + identificacion, "Iniciando proceso de migracion " + this.hashCode());
        List<ClienteInternetBanking> listaClientes = this.clientesMigrarFacade.listarPendientesPorTipoIdYNumero(tipoIdentificacion.trim(), identificacion.trim());
        if (listaClientes.size() > 0) {
            boolean clienteMigrado = false;
            boolean iniciarMigracion = false;
            if (user != null) {
                switch (user.getEstatus().charValue()) {
                    case 'K': {
                        clienteMigrado = false;
                        break;
                    }
                    default: {
                        clienteMigrado = true;
                    }
                }
            }
            if (!clienteMigrado) {
                for (ClienteInternetBanking clienteProcesoIter : listaClientes) {
                    if (!clienteProcesoIter.esClienteMaster() || !clienteProcesoIter.esClienteNatural() || !clienteProcesoIter.getEstadoMigracion().equalsIgnoreCase("A")) continue;
                    clienteMigrar = clienteProcesoIter;
                    break;
                }
                if (clienteMigrar == null) {
                    for (ClienteInternetBanking clienteProcesoIter : listaClientes) {
                        if (!clienteProcesoIter.esClienteMaster() || clienteProcesoIter.esClienteNatural() || !clienteProcesoIter.getEstadoMigracion().equalsIgnoreCase("A")) continue;
                        clienteMigrar = clienteProcesoIter;
                        break;
                    }
                }
                if (clienteMigrar != null) {
                    this.setUsuarioProceso(user);
                    this.setClienteProceso(clienteMigrar);
                    return this.registrarUsuarioSeleccionado();
                }
            } else {
                usuarioEnProceso.setEstado(Character.valueOf('M'));
                this.excluidosFacade.edit(usuarioEnProceso);
                Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), tipoIdentificacion + identificacion, user.getEstatus() + " El cliente ya inicio el proceso de migracion o esta registrado en NBL");
            }
        } else {
            usuarioEnProceso.setEstado(Character.valueOf('S'));
            this.excluidosFacade.edit(usuarioEnProceso);
            Log.getInstance().info(LogNBL.MIGRAR.getCodigo(), tipoIdentificacion + identificacion, "No tiene perfiles por migrar.");
        }
        return false;
    }

    public ExcluidosMigracion getUsuarioEnProceso() {
        return this.usuarioEnProceso;
    }

    public void setUsuarioEnProceso(ExcluidosMigracion usuarioEnProceso) {
        this.usuarioEnProceso = usuarioEnProceso;
    }

    public String printVersion() {
        String version;
        String date;
        try {
            String deliveryDate;
            String appVersion;
            ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
            InputStream inputStream = servletContext.getResourceAsStream("/META-INF/MANIFEST.MF");
            Manifest manifest = new Manifest(inputStream);
            Attributes attributes = manifest.getMainAttributes();
            String wlsAppVersion = attributes.getValue("Weblogic-Application-Version");
            version = null == wlsAppVersion || wlsAppVersion.isEmpty() ? (null == (appVersion = attributes.getValue("Implementation-Version")) || appVersion.isEmpty() ? "DESCONOCIDA" : appVersion) : wlsAppVersion;
            date = deliveryDate = attributes.getValue("Delivery-Date");
        }
        catch (Exception e) {
            version = "VERSION DESPLEGADA : ERROR OBTENIENDO EL VALOR";
            date = "ENTREGA FECHA : ERROR OBTENIENDO EL VALOR";
        }
        return "PROCESO DE MIGRACION DESPLEGADO. VERSION: " + version + " (" + date + ")";
    }
}
