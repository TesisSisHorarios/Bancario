package com.sistema.bancario.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class Cuentapk  implements Serializable {

    @Column(name = "ccuenta", nullable = false)
    private Long ccuenta;
    @Column(name = "fhasta", nullable = false)
    private Timestamp fhasta;

    public Long getCcuenta() {
        return ccuenta;
    }

    public void setCcuenta(Long ccuenta) {
        this.ccuenta = ccuenta;
    }

    public Timestamp getFhasta() {
        return fhasta;
    }

    public void setFhasta(Timestamp fhasta) {
        this.fhasta = fhasta;
    }

    public Cuentapk(Long ccuenta, Timestamp fhasta) {
        this.ccuenta = ccuenta;
        this.fhasta = fhasta;
    }

    public Cuentapk() {
    }
}
