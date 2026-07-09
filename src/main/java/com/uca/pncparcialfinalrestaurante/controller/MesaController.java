package com.uca.pncparcialfinalrestaurante.controller;

import com.uca.pncparcialfinalrestaurante.entity.Mesa;
import com.uca.pncparcialfinalrestaurante.service.MesaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// (Controller)
@RestController
@RequestMapping("/api/mesas")
@AllArgsConstructor
public class MesaController {

    private MesaService mesaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENCARGADO')")
    public ResponseEntity<List<Mesa>> findAll() {
        return ResponseEntity.ok(mesaService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENCARGADO')")
    public ResponseEntity<Mesa> findById(@PathVariable Long id) {
        return ResponseEntity.ok(mesaService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENCARGADO')")
    public ResponseEntity<Mesa> save(@RequestBody Mesa mesa) {
        return ResponseEntity.ok(mesaService.save(mesa));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENCARGADO')")
    public ResponseEntity<Mesa> update(@PathVariable Long id, @RequestBody Mesa mesa) {
        return ResponseEntity.ok(mesaService.update(id, mesa));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENCARGADO')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

