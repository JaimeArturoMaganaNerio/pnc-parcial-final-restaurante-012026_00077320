package com.uca.pncparcialfinalrestaurante.service;

import com.uca.pncparcialfinalrestaurante.entity.Sucursal;

import java.util.List;

// (Service)
public interface SucursalService {
    List<Sucursal> findAll();

    Sucursal findById(Long id);

    Sucursal save(Sucursal sucursal);

    Sucursal update(Long id, Sucursal sucursal);

    void delete(Long id);
}

