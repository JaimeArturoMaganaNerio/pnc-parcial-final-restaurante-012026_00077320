package com.uca.pncparcialfinalrestaurante.service;

import com.uca.pncparcialfinalrestaurante.entity.Mesa;

import java.util.List;

// (Service)
public interface MesaService {
    List<Mesa> findAll();

    Mesa findById(Long id);

    Mesa save(Mesa mesa);

    Mesa update(Long id, Mesa mesa);

    void delete(Long id);
}

