package com.uca.pncparcialfinalrestaurante.repository;

import com.uca.pncparcialfinalrestaurante.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

// (Repository)
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

