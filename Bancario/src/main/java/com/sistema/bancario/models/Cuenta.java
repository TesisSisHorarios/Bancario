package com.sistema.bancario.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "cuenta")
public class Cuenta {


    @EmbeddedId
    private Cuentapk cuentapk;

    @Column(name = "fdesde", nullable = false)
    private Timestamp fdesde;

    @Column(name = "tipocuenta",nullable = false)
    private String tipocuenta;

    @Column(name = "saldoinicial")
    private BigDecimal saldoinicial;

    @Column(name = "estado",nullable = false)
    private boolean estado;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteid")
    private Cliente cliente;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ccuenta", insertable = false, updatable = false)
    private CuentaId ccuenta;

    public Cuentapk getCuentapk() {
        return cuentapk;
    }

    public void setCuentapk(Cuentapk id) {
        this.cuentapk = id;
    }

    public Timestamp getFdesde() {
        return fdesde;
    }

    public void setFdesde(Timestamp fdesde) {
        this.fdesde = fdesde;
    }

    public String getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public BigDecimal getSaldoinicial() {
        return saldoinicial;
    }

    public void setSaldoinicial(BigDecimal saldoinicial) {
        this.saldoinicial = saldoinicial;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public CuentaId getCcuenta() {
        return ccuenta;
    }

    public void setCcuenta(CuentaId ccuenta) {
        this.ccuenta = ccuenta;
    }
}
