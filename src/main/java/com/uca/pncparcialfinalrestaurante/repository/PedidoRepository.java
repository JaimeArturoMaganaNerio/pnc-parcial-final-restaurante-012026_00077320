package com.uca.pncparcialfinalrestaurante.repository;

import com.uca.pncparcialfinalrestaurante.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// (Repository)
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	List<Pedido> findByClienteId(Long clienteId);

	List<Pedido> findByMesaSucursalId(Long sucursalId);
}

