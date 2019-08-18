package com.slash.springboot.vuejs.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.slash.springboot.vuejs.domain.Equipo;
import com.slash.springboot.vuejs.service.EquipoService;

import org.springframework.stereotype.Service;

@Service
public class EquipoServiceImpl implements EquipoService {

    private static AtomicInteger COUNTER_ID = new AtomicInteger(0);
    private static List<Equipo> EQUIPOS;
    private static Stream<String> NOMBRE_EQUIPO = Stream.of(
        "América", "Guadalajara", "Cruz Azul", "Pumas", "Santos", "Toluca", "León");

    public EquipoServiceImpl() {
        EQUIPOS = NOMBRE_EQUIPO
        .map(nombreEquipo -> new Equipo(COUNTER_ID.incrementAndGet(), nombreEquipo))
        .collect(Collectors.toList());
    }

    @Override
	public List<Equipo> findAll() {
        return EQUIPOS;
	}
}