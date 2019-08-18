package com.slash.springboot.vuejs.controller;

import java.util.List;

import com.slash.springboot.vuejs.domain.Equipo;
import com.slash.springboot.vuejs.service.EquipoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/equipo")
public class EquipoController {
    
    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<Equipo>> getEquipos() {
        List<Equipo> equipos = equipoService.findAll();

        return new ResponseEntity<>(equipos, HttpStatus.OK);
    }
}