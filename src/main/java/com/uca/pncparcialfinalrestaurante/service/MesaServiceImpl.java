package com.uca.pncparcialfinalrestaurante.service;

import com.uca.pncparcialfinalrestaurante.entity.Mesa;
import com.uca.pncparcialfinalrestaurante.repository.MesaRepository;
import com.uca.pncparcialfinalrestaurante.repository.SucursalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// (Service)
@Service
@AllArgsConstructor
public class MesaServiceImpl implements MesaService {

    private MesaRepository mesaRepository;
    private SucursalRepository sucursalRepository;

    @Override
    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    @Override
    public Mesa findById(Long id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
    }

    @Override
    public Mesa save(Mesa mesa) {
        if (mesa.getSucursal() != null && mesa.getSucursal().getId() != null) {
            mesa.setSucursal(sucursalRepository.findById(mesa.getSucursal().getId())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada")));
        }
        return mesaRepository.save(mesa);
    }

    @Override
    public Mesa update(Long id, Mesa mesa) {
        Mesa current = findById(id);
        current.setNumero(mesa.getNumero());
        current.setCapacidad(mesa.getCapacidad());
        current.setEstado(mesa.getEstado());
        if (mesa.getSucursal() != null && mesa.getSucursal().getId() != null) {
            current.setSucursal(sucursalRepository.findById(mesa.getSucursal().getId())
                    .orElseThrow(() -> new RuntimeException("Sucursal no encontrada")));
        }
        return mesaRepository.save(current);
    }

    @Override
    public void delete(Long id) {
        mesaRepository.deleteById(id);
    }
}

