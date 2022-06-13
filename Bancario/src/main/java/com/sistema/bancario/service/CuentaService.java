package com.sistema.bancario.service;

import com.sistema.bancario.common.Respuesta;
import com.sistema.bancario.dto.CuentaDTO;
import com.sistema.bancario.models.*;
import com.sistema.bancario.repository.CuentaIdRepository;
import com.sistema.bancario.repository.CuentaRepository;
import com.sistema.bancario.utils.BancoUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sistema.bancario.utils.BancoUtils.*;

@Service
public class CuentaService {

    private static Logger log = LoggerFactory.getLogger(CuentaService.class.getName());

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    CuentaIdRepository cuentaIdRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Respuesta save(CuentaDTO cuentadto){
        Respuesta resp= new Respuesta();
        try{
            if (cuentadto != null){
                Cuentapk pk = new Cuentapk(cuentadto.getCcuenta().getCcuenta(),BancoUtils.getFhasta());
                Optional<Cuenta> cuentaOp = cuentaRepository.findById(pk);
                if(!cuentaOp.isPresent()){
                    saveCuentaId(cuentadto.getCcuenta().getCcuenta());
                    Cuentapk cuentapk= new Cuentapk(cuentadto.getCcuenta().getCcuenta(), BancoUtils.getFhasta());
                    Cuenta cuenta = mapearEntidad(cuentadto);
                    cuenta.setCuentapk(cuentapk);
                    cuenta.setFdesde(fechaActual());
                    cuenta.setSaldoinicial(BigDecimal.ZERO);
                    cuentaRepository.save(cuenta);
                    resp.setCorrecto(true);
                    resp.setMensaje("Cuenta Creada");
                    resp.setTitulo("Operación Correcta");
                }else {
                    resp = modificar(cuentaOp.get(),mapearEntidad(cuentadto));
                }

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

    public void saveCuentaId(Long ccuenta) {
        CuentaId cuentaId = new CuentaId(ccuenta);
        cuentaIdRepository.save(cuentaId);
    }

    public List<CuentaDTO> getAll(){
        List<Cuenta> cuentas = (List<Cuenta>)cuentaRepository.findAll();
        return  cuentas.stream().map(cuenta -> mapearDTO(cuenta)).collect(Collectors.toList());
    }

    public Respuesta modificar(Cuenta cuentamod, Cuenta cuentanueva){
        Respuesta resp = new Respuesta();
        try {
            if(cuentamod.getCliente().getCpersona().compareTo(cuentanueva.getCliente().getCpersona())!=0){
                resp.setCorrecto(false);
                resp.setMensaje("No se puede cambiar el cliente ");
                resp.setTitulo("Operación Correcta");
                return resp;
            }
            caducarCuenta(cuentamod);
            Cuentapk cuentapk = new Cuentapk(cuentamod.getCuentapk().getCcuenta(), BancoUtils.getFhasta());
            cuentanueva.setCuentapk(cuentapk);
            cuentanueva.setFdesde(fechaActual());
            cuentaRepository.save(cuentanueva);
            resp.setCorrecto(true);
            resp.setMensaje("Cuenta Modificada");
            resp.setTitulo("Operación Correcta");

        }catch (Exception e){
            log.error(e.getMessage(), e);
            resp.setCorrecto(false);
            resp.setMensaje("No se pudo guardar la cuenta");
            resp.setTitulo("Operación Errónea");
        }
        return resp;
    }

    public void caducarCuenta(Cuenta cuentacaducar){
        cuentaRepository.delete(cuentacaducar);
        Long ccuentapk = cuentacaducar.getCcuenta().getCcuenta();
        Cuentapk pk = new Cuentapk(ccuentapk, BancoUtils.fechaActual());
        cuentacaducar.setCuentapk(pk);
        cuentaRepository.save(cuentacaducar);
    }

    public Cuenta mapearEntidad(CuentaDTO cuentaDTO){
        return modelMapper.map(cuentaDTO, Cuenta.class);
    }

    public CuentaDTO mapearDTO(Cuenta cuenta){
        return modelMapper.map(cuenta, CuentaDTO.class);
    }
}
