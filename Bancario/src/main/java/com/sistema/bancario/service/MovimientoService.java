package com.sistema.bancario.service;

import com.sistema.bancario.common.EstadoCuenta;
import com.sistema.bancario.common.Respuesta;
import com.sistema.bancario.dto.MovimientoDTO;
import com.sistema.bancario.models.*;
import com.sistema.bancario.repository.CuentaRepository;
import com.sistema.bancario.repository.MovimientoRepository;
import com.sistema.bancario.utils.BancoUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    private static Logger log = LoggerFactory.getLogger(MovimientoService.class.getName());

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    CuentaService cuentaService;

    @Autowired
    private ModelMapper modelMapper;

    private boolean correcto = true;
    private String mensaje = "";

    public Respuesta save(Long ccuenta, BigDecimal valor) {
        Respuesta resp = new Respuesta();

        try {
            if (ccuenta != null) {
                Cuentapk cuentapk = new Cuentapk(ccuenta, BancoUtils.getFhasta());
                Optional<Cuenta> cuentaOptional = cuentaRepository.findById(cuentapk);

                if (cuentaOptional.isPresent()) {
                    Cuenta cuenta = cuentaOptional.get();
                    movimiento(cuenta, valor);
                    /*Movimiento movimiento = new Movimiento();
                    BigDecimal saldo = getSaldo(ccuenta);
                    if (valor.compareTo(BigDecimal.valueOf(0))<0){
                        if (validarRetiroDiario(ccuenta,valor)){
                            if (saldo.compareTo(BigDecimal.valueOf(0))==0){
                                correcto=false;
                                mensaje="Saldo no disponible";
                            }else{
                                debitoCredito(movimiento,valor,cuenta,"D");
                                if(movimiento.getSaldo().compareTo(BigDecimal.valueOf(0))>=0){
                                    movimientoRepository.save(movimiento);
                                    correcto=true;
                                    mensaje="Transacción realizada correctamente";
                                }else{
                                    correcto=false;
                                    mensaje="Saldo no puede ser negativo";
                                }
                            }
                        }else{
                            correcto=false;
                            mensaje="Cupo diario Excedido";
                        }

                    }else {
                        debitoCredito(movimiento,valor,cuenta,"C");
                        movimientoRepository.save(movimiento);

                        correcto=true;
                        mensaje="Transacción realizada correctamente";
                    }*/
                } else {
                    correcto = false;
                    mensaje = "Cuenta no Existe";
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            resp.setCorrecto(correcto);
            resp.setMensaje(mensaje);
            resp.setTitulo(correcto ? "Operación Correcta" : "Operación Errónea");
        }
        return resp;
    }

    private void validarValorCero(BigDecimal valor) throws Exception {
        if (valor.compareTo(BigDecimal.valueOf(0)) == 0) {
            correcto = false;
            mensaje = "Valor del movimiento debe ser diferente de 0";
            throw new Exception("Valor del movimiento debe ser diferente de 0");
        }
    }

    private void movimiento(Cuenta cuenta, BigDecimal valor) throws Exception {
        try {
            Long ccuenta = cuenta.getCuentapk().getCcuenta();
            BigDecimal saldo = getSaldo(ccuenta);
            String tipomovimiento = esDebitoCredito(valor);
            if (tipomovimiento.equals("D") && !validarRetiroDiario(ccuenta, valor)) {
                mensaje = "Cupo diario Excedido";
                throw new Exception("Valor del movimiento debe ser diferente de 0");
            }
            validarCuenta(cuenta);
            validarValorCero(valor);
            validarSaldo(saldo, tipomovimiento, valor);
            Movimiento movimiento = new Movimiento();
            debitoCredito(movimiento, valor, cuenta, tipomovimiento);
            movimientoRepository.save(movimiento);
            mensaje = "Transacción realizada correctamente";
        } catch (Exception e) {
            correcto = false;
            throw new Exception(e.getMessage(),e);
        }
    }

    public void validarSaldo(BigDecimal saldo, String tipomovimiento, BigDecimal valor) throws Exception {
        if (tipomovimiento.equals("D")) {
            BigDecimal absValor = valor.abs();
            if (saldo.compareTo(BigDecimal.valueOf(0)) == 0 || saldo.compareTo(absValor) < 0) {
                mensaje = "Saldo no disponible";
                throw new Exception("Saldo no disponible");
            }
        }
    }

    public void validarCuenta(Cuenta cuenta) throws Exception {
        if (!cuenta.isEstado()) {
            mensaje = "Cuenta Inactiva";
            throw new Exception("Cuenta Inactiva");
        }
    }

    private String esDebitoCredito(BigDecimal valor) {
        return (valor.compareTo(BigDecimal.valueOf(0)) < 0) ? "D" : "C";
    }

    private void debitoCredito(Movimiento movimiento, BigDecimal valor, Cuenta cuenta, String tipomovimiento) {
        BigDecimal saldo = getSaldo(cuenta.getCcuenta().getCcuenta());
        saldo = saldo.add(valor);
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        movimiento.setFecha(timestamp);
        movimiento.setSaldo(saldo);
        movimiento.setTipomovimiento(tipomovimiento);
        movimiento.setValor(valor);
        movimiento.setCuenta(cuenta.getCcuenta());
        BigDecimal saldoInicial = cuenta.getSaldoinicial();
        if (tipomovimiento.equals("C") && (saldoInicial == null || saldoInicial.compareTo(BigDecimal.valueOf(0)) == 0)) {
            Cuenta cuentaUpdate = new Cuenta();
            Cuentapk cuentapk = new Cuentapk(cuenta.getCuentapk().getCcuenta(), BancoUtils.getFhasta());
            cuentaUpdate.setCuentapk(cuentapk);
            cuentaUpdate.setSaldoinicial(valor);
            cuentaUpdate.setSaldoinicial(valor);
            cuentaUpdate.setEstado(cuenta.isEstado());
            cuentaUpdate.setFdesde(BancoUtils.fechaActual());
            cuentaUpdate.setCcuenta(cuenta.getCcuenta());
            cuentaUpdate.setCliente(cuenta.getCliente());
            cuentaUpdate.setTipocuenta(cuenta.getTipocuenta());
            cuentaService.save(cuentaService.mapearDTO(cuentaUpdate));
        }

    }

    private BigDecimal getSaldo(Long ccuenta) {
        BigDecimal saldo = movimientoRepository.getSaldo(ccuenta);
        return saldo == null ? BigDecimal.valueOf(0) : saldo;
    }

    private boolean validarRetiroDiario(Long ccuenta, BigDecimal valor) {
        boolean respuesta = true;
        BigDecimal valorRetiro = movimientoRepository.getValorRetiro(ccuenta);
        valorRetiro = valorRetiro == null ? BigDecimal.valueOf(0) : valorRetiro;
        valorRetiro = valorRetiro.add(valor);
        valorRetiro = valorRetiro.abs();
        if (valorRetiro.compareTo(BigDecimal.valueOf(1000)) > 0) {
            respuesta = false;
        }
        return respuesta;
    }

    public List<EstadoCuenta> estadoCuenta(Long ccuenta, String fechaInicio, String fechaFin) {

        List<Movimiento> resultado = movimientoRepository.getEstadoCuenta(ccuenta, fechaInicio, fechaFin);
        List<EstadoCuenta> listestadoCuenta = new ArrayList<>();
        for (Movimiento obj : resultado) {
            EstadoCuenta estadoCuenta = new EstadoCuenta();
            Cuentapk cuentapk = new Cuentapk(obj.getCuenta().getCcuenta(),BancoUtils.getFhasta());
            Optional<Cuenta> cuentaOptional = cuentaRepository.findById(cuentapk);
            Cuenta cuenta = cuentaOptional.get();
            estadoCuenta.setFecha(new Date(obj.getFecha().getTime()));
            estadoCuenta.setCliente(cuenta.getCliente().getNombre());
            estadoCuenta.setNummerocuenta(obj.getCuenta().getCcuenta());
            estadoCuenta.setTipo(cuenta.getTipocuenta());
            estadoCuenta.setSaldoinicial(cuenta.getSaldoinicial());
            estadoCuenta.setEstado(cuenta.isEstado());
            estadoCuenta.setMovimiento(obj.getValor());
            estadoCuenta.setSaldodisponible(obj.getSaldo());
            listestadoCuenta.add(estadoCuenta);
        }
        return listestadoCuenta;
    }

    public Movimiento mapearEntidad(MovimientoDTO movimientoDTO) {
        return modelMapper.map(movimientoDTO, Movimiento.class);
    }

    public MovimientoDTO mapearDTO(Movimiento movimiento) {
        return modelMapper.map(movimiento, MovimientoDTO.class);
    }
}
