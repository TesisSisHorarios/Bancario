package com.sistema.bancario.repository;

import com.sistema.bancario.models.CuentaId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaIdRepository extends CrudRepository<CuentaId, Long> {
}
