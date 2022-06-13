package com.sistema.bancario.controller;

import com.sistema.bancario.dto.CuentaDTO;
import com.sistema.bancario.common.Respuesta;
import com.sistema.bancario.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cuenta/")
public class CuentaController {

    @Autowired
    private CuentaService cuentaservice;

    @PostMapping("/save")
    public Respuesta save(@RequestBody CuentaDTO cuenta){
        return cuentaservice.save(cuenta);
    }

    @GetMapping(value = "/all")
    public List<CuentaDTO> getAll(){
        return cuentaservice.getAll();
    }
}
