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
@Table(name = "TMPIBPERFILNBL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IBMapeoAcceso.findAll", query = "SELECT a FROM IBMapeoAcceso a")
})
public class IBMapeoAcceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_TMPIBPERFILNBL")
    private Long id;
    @Column(name = "CODOPCIONIB")
    private String opcionIB;
    @Column(name = "FK_IDOPERACIONBL")
    private int opcionNBL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpcionIB() {
        return opcionIB;
    }

    public void setOpcionIB(String opcionIB) {
        this.opcionIB = opcionIB;
    }

    public int getOpcionNBL() {
        return opcionNBL;
    }

    public void setOpcionNBL(int opcionNBL) {
        this.opcionNBL = opcionNBL;
    }

}
