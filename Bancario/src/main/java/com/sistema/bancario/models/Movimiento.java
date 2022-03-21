package com.sistema.bancario.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "movimiento")
public class Movimiento {

    @Id
    @Column(name = "cmovimiento")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer cmovimiento;

    @Column(name = "fecha")
    private Timestamp fecha;

    @Column(name = "tipomovimiento", length = 1)
    private String tipomovimiento;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ccuenta")
    private Cuenta cuenta;

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

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}
