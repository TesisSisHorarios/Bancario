package com.sistema.bancario.repository;

import com.sistema.bancario.models.Cuenta;
import com.sistema.bancario.models.Cuentapk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Cuentapk> {
}
