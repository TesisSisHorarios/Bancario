package com.sistema.bancario.controller;

import com.sistema.bancario.dto.ClienteDTO;
import com.sistema.bancario.common.Respuesta;
import com.sistema.bancario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente/")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public Respuesta save(@RequestBody ClienteDTO cliente){
        return clienteService.save(cliente);
    }

    @GetMapping(value = "/all")
    public List<ClienteDTO> getAll(){
        return clienteService.getAll();
    }

    @DeleteMapping("/delete")
    public Respuesta delete(@RequestBody ClienteDTO cliente){
        return clienteService.delete(cliente);
    }
}
