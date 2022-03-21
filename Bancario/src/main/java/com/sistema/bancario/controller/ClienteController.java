package com.sistema.bancario.controller;

import com.sistema.bancario.models.Cliente;
import com.sistema.bancario.models.Respuesta;
import com.sistema.bancario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/cliente/")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public Respuesta save(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @GetMapping(value = "/all")
    public ArrayList<Cliente> getAll(){
        return clienteService.getAll();
    }
}
