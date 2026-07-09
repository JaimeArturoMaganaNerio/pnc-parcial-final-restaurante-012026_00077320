package com.uca.pncparcialfinalrestaurante.repository;

import com.uca.pncparcialfinalrestaurante.entity.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

// (Repository)
public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
}

