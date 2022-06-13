package com.sistema.bancario.dto;

import com.sistema.bancario.models.Cliente;
import com.sistema.bancario.models.CuentaId;
import com.sistema.bancario.models.Cuentapk;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class CuentaDTO {

    private Cuentapk cuentapk;

    private Timestamp fdesde;

    private String tipocuenta;

    private BigDecimal saldoinicial;

    private boolean estado;

    private Cliente cliente;

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
