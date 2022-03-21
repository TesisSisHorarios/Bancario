package com.sistema.bancario.controller;

import com.sistema.bancario.models.Cuenta;
import com.sistema.bancario.models.Respuesta;
import com.sistema.bancario.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/cuenta/")
public class CuentaController {

    @Autowired
    private CuentaService cuentaservice;

    @PostMapping("/save")
    public Respuesta save(@RequestBody Cuenta cuenta){
        return cuentaservice.save(cuenta);
    }

    @GetMapping(value = "/all")
    public ArrayList<Cuenta> getAll(){
        return cuentaservice.getAll();
    }
}
