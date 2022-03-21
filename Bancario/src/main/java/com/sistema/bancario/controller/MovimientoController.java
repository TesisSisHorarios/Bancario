package com.sistema.bancario.controller;

import com.sistema.bancario.models.EstadoCuenta;
import com.sistema.bancario.models.Respuesta;
import com.sistema.bancario.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/movimientos/")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping(value = "/save")
    public Respuesta save(@RequestParam(value = "ccuenta") Long ccuenta, @RequestParam(value = "valor")BigDecimal valor){
        return movimientoService.save(ccuenta, valor);
    }

    @GetMapping(value = "/estadocuenta")
    public ArrayList<EstadoCuenta> estadocuenta(@RequestParam(value = "ccuenta") Long ccuenta, @RequestParam(value = "fecha1") String fecha1, @RequestParam(value = "fecha2") String fecha2){
        return movimientoService.estadoCuenta(ccuenta,fecha1,fecha2);
    }
}
