package com.sistema.bancario.service;

import com.sistema.bancario.models.Cliente;
import com.sistema.bancario.models.Respuesta;
import com.sistema.bancario.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClienteService {

    private static Logger log = LoggerFactory.getLogger(ClienteService.class.getName());

    @Autowired
    ClienteRepository clienteRepository;

    public Respuesta save(Cliente clienteNew){
        Respuesta resp = new Respuesta();

        try{
            if (clienteNew != null){
                clienteRepository.save(clienteNew);
                resp.setCorrecto(true);
                resp.setMensaje("Cliente Guardado");
                resp.setTitulo("Operación Correcta");
            }else{
                resp.setCorrecto(false);
                resp.setMensaje("No se pudo guardar el cliente");
                resp.setTitulo("Operación Errónea");
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            resp.setCorrecto(false);
            resp.setMensaje("No se pudo guardar el cliente");
            resp.setTitulo("Operación Errónea");
        }

        return resp;
    }

    public ArrayList<Cliente> getAll(){
        Cliente cliente = new Cliente();
        return (ArrayList<Cliente>) clienteRepository.findAll();
    }
}
