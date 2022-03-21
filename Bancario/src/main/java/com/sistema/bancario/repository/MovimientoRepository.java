package com.sistema.bancario.repository;

import com.sistema.bancario.models.EstadoCuenta;
import com.sistema.bancario.models.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

public interface MovimientoRepository extends CrudRepository<Movimiento, Integer> {

    @Query(value = "SELECT saldo " +
            "FROM public.movimiento WHERE " +
            "    ccuenta = ?1 " +
            "AND cmovimiento= (SELECT MAX(cmovimiento) " +
            "        FROM public.movimiento WHERE " +
            "            ccuenta = ?1) ",nativeQuery = true)
    BigDecimal getSaldo(Long ccuenta);

    @Query(value = "select sum(valor) from public.movimiento where fecha>current_date and tipomovimiento = 'D' and ccuenta = ?1 ",nativeQuery = true)
    BigDecimal getValorRetiro(Long ccuenta);

    @Query(value = "select fecha,tipomovimiento,valor,saldo " +
            "from public.movimiento " +
            "where ccuenta = ?1 and CAST(fecha as date) " +
            "BETWEEN to_date(?2,'yyyy-mm-dd') and to_date(?3,'yyyy-mm-dd') order by cmovimiento ",nativeQuery = true)
    ArrayList<Object[]> getEstadoCuenta(Long ccuenta, String fechaInicio, String fechaFin);

}
