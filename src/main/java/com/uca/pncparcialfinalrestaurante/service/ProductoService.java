package com.uca.pncparcialfinalrestaurante.service;

import com.uca.pncparcialfinalrestaurante.entity.Producto;

import java.util.List;

// (Service)
public interface ProductoService {
    List<Producto> findAll();

    Producto findById(Long id);

    Producto save(Producto producto);

    Producto update(Long id, Producto producto);

    void delete(Long id);
}

