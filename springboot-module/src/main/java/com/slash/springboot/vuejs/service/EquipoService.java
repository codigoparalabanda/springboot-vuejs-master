package com.slash.springboot.vuejs.service;

import java.util.List;

import com.slash.springboot.vuejs.domain.Equipo;

public interface EquipoService {
    
    public List<Equipo> findAll();
}