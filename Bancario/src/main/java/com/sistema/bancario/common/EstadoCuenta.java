package com.sistema.bancario.common;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class EstadoCuenta {

    private Date fecha;

    private String cliente;

    private Long nummerocuenta;

    private String tipo;

    private BigDecimal saldoinicial;

    private boolean estado;

    private BigDecimal movimiento;

    private BigDecimal saldodisponible;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Long getNummerocuenta() {
        return nummerocuenta;
    }

    public void setNummerocuenta(Long nummerocuenta) {
        this.nummerocuenta = nummerocuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public BigDecimal getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(BigDecimal movimiento) {
        this.movimiento = movimiento;
    }

    public BigDecimal getSaldodisponible() {
        return saldodisponible;
    }

    public void setSaldodisponible(BigDecimal saldodisponible) {
        this.saldodisponible = saldodisponible;
    }
}
