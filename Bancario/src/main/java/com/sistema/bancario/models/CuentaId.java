package com.sistema.bancario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cuentaid")
public class CuentaId {

    @Id
    @Column(name = "ccuenta")
    private Long ccuenta;

    public Long getCcuenta() {
        return ccuenta;
    }

    public void setCcuenta(Long ccuenta) {
        this.ccuenta = ccuenta;
    }

    public CuentaId(Long ccuenta) {
        this.ccuenta = ccuenta;
    }

    public CuentaId() {}
}
