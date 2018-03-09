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
@Table(name = "TMP_E2USAPFCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IBPerfilConfiguracionAcceso.findAll", query = "SELECT a FROM IBPerfilConfiguracionAcceso a"),
    @NamedQuery(name = "IBPerfilConfiguracionAcceso.findAllByNumeroCliente", query = "SELECT a FROM IBPerfilConfiguracionAcceso a WHERE a.numeroCliente=:numeroCliente"),
    @NamedQuery(name = "IBPerfilConfiguracionAcceso.obtenerRegistrosMigrar", query = "SELECT a FROM IBPerfilConfiguracionAcceso a WHERE a.numeroCliente=:numeroCliente AND a.usuario=:usuario")
})
public class IBPerfilConfiguracionAcceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_E2USAPFCT")
    private Long id;
    @Column(name = "E2EMPR")
    private int codigoEmpresa;
    @Column(name = "E2CUSC")
    private int numeroCliente;
    @Column(name = "E2USM2")
    private String usuario;
    @Column(name = "E2M2M2")
    private char creditos;
    @Column(name = "E2M3M2")
    private char tarjetas;
    @Column(name = "E2M4M2")
    private char pagos;
    @Column(name = "E2M5M2")
    private char consultas;
    @Column(name = "E2M6M2")
    private char cheques;
    @Column(name = "E2M7M2")
    private char desconocidoUno;
    @Column(name = "E2M8M2")
    private char desconocidoDos;
    @Column(name = "E2M9M2")
    private char desconocidoTres;
    @Column(name = "E2MAM2")
    private char desconocidoCuatro;
    @Column(name = "E2MBM2")
    private char desconocidoCinco;
    @Column(name = "E2MCM2")
    private char deconocidoSeis;
    @Column(name = "E2MPM2")
    private char desconocidoSiete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public char getCreditos() {
        return creditos;
    }

    public void setCreditos(char creditos) {
        this.creditos = creditos;
    }

    public char getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(char tarjetas) {
        this.tarjetas = tarjetas;
    }

    public char getPagos() {
        return pagos;
    }

    public void setPagos(char pagos) {
        this.pagos = pagos;
    }

    public char getConsultas() {
        return consultas;
    }

    public void setConsultas(char consultas) {
        this.consultas = consultas;
    }

    public char getCheques() {
        return cheques;
    }

    public void setCheques(char cheques) {
        this.cheques = cheques;
    }

    public char getDesconocidoUno() {
        return desconocidoUno;
    }

    public void setDesconocidoUno(char desconocidoUno) {
        this.desconocidoUno = desconocidoUno;
    }

    public char getDesconocidoDos() {
        return desconocidoDos;
    }

    public void setDesconocidoDos(char desconocidoDos) {
        this.desconocidoDos = desconocidoDos;
    }

    public char getDesconocidoTres() {
        return desconocidoTres;
    }

    public void setDesconocidoTres(char desconocidoTres) {
        this.desconocidoTres = desconocidoTres;
    }

    public char getDesconocidoCuatro() {
        return desconocidoCuatro;
    }

    public void setDesconocidoCuatro(char desconocidoCuatro) {
        this.desconocidoCuatro = desconocidoCuatro;
    }

    public char getDesconocidoCinco() {
        return desconocidoCinco;
    }

    public void setDesconocidoCinco(char desconocidoCinco) {
        this.desconocidoCinco = desconocidoCinco;
    }

    public char getDeconocidoSeis() {
        return deconocidoSeis;
    }

    public void setDeconocidoSeis(char deconocidoSeis) {
        this.deconocidoSeis = deconocidoSeis;
    }

    public char getDesconocidoSiete() {
        return desconocidoSiete;
    }

    public void setDesconocidoSiete(char desconocidoSiete) {
        this.desconocidoSiete = desconocidoSiete;
    }
    
    

}
