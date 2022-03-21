package com.sistema.bancario.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @Column(name = "ccuenta")
    private Long ccuenta;

    @Column(name = "tipocuenta")
    private String tipocuenta;

    @Column(name = "saldoinicial")
    private BigDecimal saldoinicial;

    @Column(name = "estado")
    private boolean estado;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteid")
    private Cliente cliente;

    public Long getCcuenta() {
        return ccuenta;
    }

    public void setCcuenta(Long ccuenta) {
        this.ccuenta = ccuenta;
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
}
