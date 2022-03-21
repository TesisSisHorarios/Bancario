package com.sistema.bancario.repository;

import com.sistema.bancario.models.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface CuentaRepository extends CrudRepository<Cuenta, Long> {
}
