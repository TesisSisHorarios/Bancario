package com.sistema.bancario.service;

import com.sistema.bancario.dto.ClienteDTO;
import com.sistema.bancario.models.Cliente;
import com.sistema.bancario.common.Respuesta;
import com.sistema.bancario.repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private static Logger log = LoggerFactory.getLogger(ClienteService.class.getName());

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Respuesta save(ClienteDTO clienteDTO) {
        Respuesta resp = new Respuesta();

        try {
            if (clienteDTO != null) {
                Cliente cliente = mapearEntidad(clienteDTO);
                clienteRepository.save(cliente);
                resp.setCorrecto(true);
                resp.setMensaje("Cliente Guardado");
                resp.setTitulo("Operación Correcta");
            } else {
                resp.setCorrecto(false);
                resp.setMensaje("No se pudo guardar el cliente");
                resp.setTitulo("Operación Errónea");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            resp.setCorrecto(false);
            resp.setMensaje("No se pudo guardar el cliente");
            resp.setTitulo("Operación Errónea");
        }

        return resp;
    }

    public List<ClienteDTO> getAll() {
        List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
        return clientes.stream().map(cliente -> mapearDTO(cliente)).collect(Collectors.toList());
    }

    public Respuesta delete(ClienteDTO clienteDTO) {
        Respuesta resp = new Respuesta();
        try {
            if (clienteDTO != null) {
                Integer cpersona = clienteDTO.getCpersona();
                Optional<Cliente> clienteOptional = clienteRepository.findById(cpersona);
                if(clienteOptional.isPresent()){
                    Cliente cliente = clienteOptional.get();
                    clienteRepository.delete(cliente);
                    resp.setCorrecto(true);
                    resp.setMensaje("Cliente Eliminado");
                    resp.setTitulo("Operación Correcta");
                }else{
                    resp.setCorrecto(true);
                    resp.setMensaje("Cliente no existe");
                    resp.setTitulo("Operación Errónea");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            resp.setCorrecto(false);
            resp.setMensaje("No se pudo eliminar el cliente");
            resp.setTitulo("Operación Errónea");
        }
        return resp;
    }

    public Cliente mapearEntidad(ClienteDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }

    public ClienteDTO mapearDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }
}
