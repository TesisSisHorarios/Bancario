package com.sistema.bancario.service;

import com.sistema.bancario.models.Cuenta;
import com.sistema.bancario.models.EstadoCuenta;
import com.sistema.bancario.models.Movimiento;
import com.sistema.bancario.models.Respuesta;
import com.sistema.bancario.repository.CuentaRepository;
import com.sistema.bancario.repository.MovimientoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class MovimientoService {

    private static Logger log = LoggerFactory.getLogger(ClienteService.class.getName());

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    public Respuesta save(Long ccuenta, BigDecimal valor){
        Respuesta resp =  new Respuesta();
        boolean correcto = true;
        String mensaje = "";
        if (valor.compareTo(BigDecimal.valueOf(0))==0){
            resp.setCorrecto(false);
            resp.setMensaje("Valor del movimiento debe ser diferente de 0");
            resp.setTitulo("Operación Errónea");
            return resp;
        }
        try{
            if (ccuenta != null){
                Optional<Cuenta> cuenta = cuentaRepository.findById(ccuenta);

                if (cuenta!=null){
                    Movimiento movimiento = new Movimiento();
                    BigDecimal saldo = getSaldo(ccuenta);
                    if (valor.compareTo(BigDecimal.valueOf(0))<0){
                        if (validarRetiroDiario(ccuenta,valor)){
                            if (saldo.compareTo(BigDecimal.valueOf(0))==0){
                                correcto=false;
                                mensaje="Saldo no disponible";
                            }else{
                                debitoCredito(movimiento,valor,cuenta.get(),"D");
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
                        debitoCredito(movimiento,valor,cuenta.get(),"C");
                        movimientoRepository.save(movimiento);

                        correcto=true;
                        mensaje="Transacción realizada correctamente";
                    }
                }else{
                    correcto=false;
                    mensaje="Cuenta no Existe";
                }
            }

        }catch (Exception e){
            log.error(e.getMessage(), e);
            correcto=false;
            mensaje="No se pudo guardar el movimiento";
        } finally {
            resp.setCorrecto(correcto);
            resp.setMensaje(mensaje);
            resp.setTitulo(correcto?"Operación Correcta":"Operación Errónea");
        }
        return resp;
    }

    private void debitoCredito(Movimiento movimiento, BigDecimal valor, Cuenta cuenta,String tipomovimiento){
        BigDecimal saldo = getSaldo(cuenta.getCcuenta());
        saldo = saldo.add(valor);
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        movimiento.setCuenta(cuenta);
        movimiento.setFecha(timestamp);
        movimiento.setSaldo(saldo);
        movimiento.setTipomovimiento(tipomovimiento);
        movimiento.setValor(valor);
        BigDecimal saldoInicial = cuenta.getSaldoinicial();
        if (tipomovimiento.equals("C")&&(saldoInicial==null || saldoInicial.compareTo(BigDecimal.valueOf(0))==0)){
            cuenta.setSaldoinicial(valor);
            cuentaRepository.save(cuenta);
        }

    }

    private BigDecimal getSaldo(Long ccuenta){
        BigDecimal saldo = movimientoRepository.getSaldo(ccuenta);
        return saldo==null? BigDecimal.valueOf(0) :saldo;
    }

    private boolean validarRetiroDiario(Long ccuenta,BigDecimal valor){
        BigDecimal valorRetiro = movimientoRepository.getValorRetiro(ccuenta);
        valorRetiro = valorRetiro==null? BigDecimal.valueOf(0) :valorRetiro;
        valorRetiro = valorRetiro.add(valor);
        valorRetiro= valorRetiro.abs();
        if(valorRetiro.compareTo(BigDecimal.valueOf(1000))>0){
            return false;
        }
        return true;
    }
    public ArrayList<EstadoCuenta> estadoCuenta(Long ccuenta, String fechaInicio, String fechaFin){

        ArrayList<Object[]> resultado = movimientoRepository.getEstadoCuenta(ccuenta,fechaInicio,fechaFin);
        ArrayList<EstadoCuenta> listestadoCuenta = new ArrayList<>();
        for(Object[] obj:resultado){
            EstadoCuenta estadoCuenta = new EstadoCuenta();
            estadoCuenta.setFecha(obj[0].toString());
            estadoCuenta.setTipomovimiento(obj[1].toString());
            estadoCuenta.setValor(obj[2].toString());
            estadoCuenta.setSaldo(obj[3].toString());
            listestadoCuenta.add(estadoCuenta);
        }
        return listestadoCuenta;
    }
}
