package com.sistema.bancario.service;

import com.sistema.bancario.models.Cliente;
import com.sistema.bancario.models.Cuenta;
import com.sistema.bancario.models.Respuesta;
import com.sistema.bancario.repository.CuentaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CuentaService {

    private static Logger log = LoggerFactory.getLogger(CuentaService.class.getName());

    @Autowired
    CuentaRepository cuentaRepository;

    public Respuesta save(Cuenta cuentaNew){
        Respuesta resp= new Respuesta();
        try{
            if (cuentaNew != null){
                cuentaRepository.save(cuentaNew);
                resp.setCorrecto(true);
                resp.setMensaje("Cuenta Guardada");
                resp.setTitulo("Operación Correcta");
            }else{
                resp.setCorrecto(false);
                resp.setMensaje("No se pudo crear la cuenta");
                resp.setTitulo("Operación Errónea");
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            resp.setCorrecto(false);
            resp.setMensaje("No se pudo guardar la cuenta");
            resp.setTitulo("Operación Errónea");
        }
        return resp;
    }

    public ArrayList<Cuenta> getAll(){
        Cliente cliente = new Cliente();
        return (ArrayList<Cuenta>) cuentaRepository.findAll();
    }
}
