package com.sistema.bancario.dto;

import javax.persistence.Column;
import java.sql.Time;
import java.sql.Timestamp;

public class CuentapkDTO {

    private Integer ccuenta;

    private Timestamp fhasta;

    public Integer getIdcurso() {
        return ccuenta;
    }

    public void setIdcurso(Integer idcurso) {
        this.ccuenta = idcurso;
    }

    public Timestamp getFhasta() {
        return fhasta;
    }

    public void setFhasta(Timestamp fhasta) {
        this.fhasta = fhasta;
    }
}
