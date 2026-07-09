package com.uca.pncparcialfinalrestaurante.repository;

import com.uca.pncparcialfinalrestaurante.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

// (Repository)
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

