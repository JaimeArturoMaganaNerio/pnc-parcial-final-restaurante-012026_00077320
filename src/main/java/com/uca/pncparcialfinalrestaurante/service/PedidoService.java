package com.uca.pncparcialfinalrestaurante.service;

import com.uca.pncparcialfinalrestaurante.entity.Pedido;

import java.util.List;

// (Service)
public interface PedidoService {
    List<Pedido> findAll();

    List<Pedido> findByClienteId(Long clienteId);

    List<Pedido> findBySucursalId(Long sucursalId);

    Pedido findById(Long id);

    Pedido save(Pedido pedido);

    Pedido update(Long id, Pedido pedido);

    void confirmar(Long id);

    void cancelar(Long id);
}

