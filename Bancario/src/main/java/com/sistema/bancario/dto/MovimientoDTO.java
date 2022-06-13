package com.sistema.bancario.dto;

import com.sistema.bancario.models.CuentaId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class MovimientoDTO {

    private Integer cmovimiento;

    private Timestamp fecha;

    @NotEmpty(message = "El movmiento debe tener un tipo movimiento: D=Debito o C=Credito")
    @Size(min = 1, max = 1,message = "El movmiento debe tener un tipo movimiento: D=Debito o C=Credito")
    @Pattern(regexp = "[C-D]", message = "El tipo movimiento debe ser: D=Debito o C=Credito")
    private String tipomovimiento;

    @NotEmpty(message = "El movimiento debe tener un valor")
    private BigDecimal valor;

    private BigDecimal saldo;

    @NotEmpty(message = "El movimiento debe tener una cuenta")
    private CuentaId cuenta;

    public Integer getCmovimiento() {
        return cmovimiento;
    }

    public void setCmovimiento(Integer cmovimiento) {
        this.cmovimiento = cmovimiento;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getTipomovimiento() {
        return tipomovimiento;
    }

    public void setTipomovimiento(String tipomovimiento) {
        this.tipomovimiento = tipomovimiento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public CuentaId getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaId cuenta) {
        this.cuenta = cuenta;
    }
}
