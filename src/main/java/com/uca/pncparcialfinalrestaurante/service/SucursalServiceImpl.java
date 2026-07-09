package com.uca.pncparcialfinalrestaurante.service;

import com.uca.pncparcialfinalrestaurante.entity.Sucursal;
import com.uca.pncparcialfinalrestaurante.repository.SucursalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// (Service)
@Service
@AllArgsConstructor
public class SucursalServiceImpl implements SucursalService {

    private SucursalRepository sucursalRepository;

    @Override
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal findById(Long id) {
        return sucursalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
    }

    @Override
    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal update(Long id, Sucursal sucursal) {
        Sucursal current = findById(id);
        current.setNombre(sucursal.getNombre());
        current.setDireccion(sucursal.getDireccion());
        return sucursalRepository.save(current);
    }

    @Override
    public void delete(Long id) {
        sucursalRepository.deleteById(id);
    }
}

