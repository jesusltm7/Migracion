package com.bod.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author intec
 */
@Embeddable
public class IBEmbeddedKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ID")
    private int id;
}
