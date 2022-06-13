package com.sistema.bancario.repository;

import com.sistema.bancario.models.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface MovimientoRepository extends CrudRepository<Movimiento, Integer> {

    @Query(value = "SELECT m.saldo " +
            "FROM Movimiento m WHERE " +
            "    m.cuenta.ccuenta = ?1 " +
            "AND m.cmovimiento= (SELECT MAX(mv.cmovimiento) " +
            "        FROM Movimiento mv WHERE " +
            "            mv.cuenta.ccuenta = ?1) ")
    BigDecimal getSaldo(Long ccuenta);

    @Query(value = "select sum(m.valor) from Movimiento m where m.fecha > current_date and m.tipomovimiento = 'D' and m.cuenta.ccuenta = ?1 ")
    BigDecimal getValorRetiro(Long ccuenta);

    @Query(value = "select * " +
            "from public.movimiento " +
            "where ccuenta = ?1 and CAST(fecha as date) " +
            "BETWEEN to_date(?2,'yyyy-mm-dd') and to_date(?3,'yyyy-mm-dd') order by cmovimiento ",nativeQuery = true)
    ArrayList<Movimiento> getEstadoCuenta(Long ccuenta, String fechaInicio, String fechaFin);

}
