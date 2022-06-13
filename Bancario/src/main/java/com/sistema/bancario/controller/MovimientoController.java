package com.sistema.bancario.controller;

import com.sistema.bancario.common.EstadoCuenta;
import com.sistema.bancario.common.Respuesta;
import com.sistema.bancario.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/movimientos/")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @PostMapping(value = "/save")
    public Respuesta save(@RequestParam(value = "ccuenta") Long ccuenta, @RequestParam(value = "valor")BigDecimal valor){
        return movimientoService.save(ccuenta, valor);
    }

    @GetMapping(value = "/estadocuenta")
    public List<EstadoCuenta> estadocuenta(@RequestParam(value = "ccuenta") Long ccuenta, @RequestParam(value = "fecha1") String fecha1, @RequestParam(value = "fecha2") String fecha2){
        return movimientoService.estadoCuenta(ccuenta,fecha1,fecha2);
    }
}
