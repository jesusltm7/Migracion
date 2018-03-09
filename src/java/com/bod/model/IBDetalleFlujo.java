package com.bod.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ernesto Cascante
 */
@Entity
@Table(name = "TMPFLUJOSAPROBACIONDETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IBDetalleFlujo.findAll", query = "SELECT a FROM IBDetalleFlujo a"),
    @NamedQuery(name = "IBDetalleFlujo.findAllByNumeroCliente", query = "SELECT a FROM IBDetalleFlujo a WHERE a.numeroCliente = :numeroCliente"),
    @NamedQuery(name = "IBDetalleFlujo.consultarRegistrosAsociados", query = "SELECT a FROM IBDetalleFlujo a WHERE a.numeroCliente = :numeroCliente AND a.tipoOperacion=:tipoOperacion AND a.consecutivoDetalle=:consecutivoDetalle ORDER BY a.codigoSubProceso ASC")
})
public class IBDetalleFlujo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_IDFLUJOAPROBACIONDETALLE")
    private Long id;

    //@Column(name = "CODEMPRESA")
    //private int bancoCliente;
    @Column(name = "NUMCLIENTE")
    private int numeroCliente;
    @Column(name = "OGNREGLA")
    private String origenRegla;
    @Column(name = "CONSECUTIVODETALLE")
    private int consecutivoDetalle;
    @Column(name = "CODSUBPROCESO")
    private String codigoSubProceso;
    @Column(name = "CODTIPOFIRMA")
    private String codigoTipoFirma;
    @Column(name = "VALSUSTITUCION")
    private String valorSustitucionFirma;
    @Column(name = "VALTIPOAUTORIZACION1")
    private String primerTipoAutorizacion;
    @Column(name = "VALTIPOAUTORIZACION2")
    private String segundoTipoAutorizacion;
    @Column(name = "VALTIPOAUTORIZACION3")
    private String tercerTipoAutorizacion;
    @Column(name = "VALTIPOAUTORIZACION4")
    private String cuartoTipoAutorizacion;
    @Column(name = "CODEMPRESA")
    private int codigoEmpresa;
    @Column(name = "TIPOPERACION")
    private String tipoOperacion;
    @Column(name = "NUMUSUARIO1")
    private String nombreUsuarioUno;
    @Column(name = "NUMUSUARIO2")
    private String nombreUsuarioDos;
    @Column(name = "NUMUSUARIO3")
    private String nombreUsuarioTres;
    @Column(name = "NUMUSUARIO4")
    private String nombreUsuarioCuatro;    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public int getConsecutivoDetalle() {
        return consecutivoDetalle;
    }

    public void setConsecutivoDetalle(int consecutivoDetalle) {
        this.consecutivoDetalle = consecutivoDetalle;
    }

    public String getCodigoSubProceso() {
        return codigoSubProceso;
    }

    public void setCodigoSubProceso(String codigoSubProceso) {
        this.codigoSubProceso = codigoSubProceso;
    }

    public String getCodigoTipoFirma() {
        return codigoTipoFirma;
    }

    public void setCodigoTipoFirma(String codigoTipoFirma) {
        this.codigoTipoFirma = codigoTipoFirma;
    }

    public String getValorSustitucionFirma() {
        return valorSustitucionFirma;
    }

    public void setValorSustitucionFirma(String valorSustitucionFirma) {
        this.valorSustitucionFirma = valorSustitucionFirma;
    }

    public String getPrimerTipoAutorizacion() {
        return primerTipoAutorizacion;
    }

    public void setPrimerTipoAutorizacion(String primerTipoAutorizacion) {
        this.primerTipoAutorizacion = primerTipoAutorizacion;
    }

    public String getSegundoTipoAutorizacion() {
        return segundoTipoAutorizacion;
    }

    public void setSegundoTipoAutorizacion(String segundoTipoAutorizacion) {
        this.segundoTipoAutorizacion = segundoTipoAutorizacion;
    }

    public String getTercerTipoAutorizacion() {
        return tercerTipoAutorizacion;
    }

    public void setTercerTipoAutorizacion(String tercerTipoAutorizacion) {
        this.tercerTipoAutorizacion = tercerTipoAutorizacion;
    }

    public String getCuartoTipoAutorizacion() {
        return cuartoTipoAutorizacion;
    }

    public void setCuartoTipoAutorizacion(String cuartoTipoAutorizacion) {
        this.cuartoTipoAutorizacion = cuartoTipoAutorizacion;
    }

    public String getOrigenRegla() {
        return origenRegla;
    }

    public void setOrigenRegla(String origenRegla) {
        this.origenRegla = origenRegla;
    }

}
