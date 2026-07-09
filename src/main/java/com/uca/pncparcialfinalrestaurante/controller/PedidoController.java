package com.uca.pncparcialfinalrestaurante.controller;

import com.uca.pncparcialfinalrestaurante.entity.Pedido;
import com.uca.pncparcialfinalrestaurante.entity.User;
import com.uca.pncparcialfinalrestaurante.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// (Controller)
@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidoController {

    private PedidoService pedidoService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENCARGADO','CLIENTE')")
    public ResponseEntity<List<Pedido>> findAll(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof User usuario)) {
            return ResponseEntity.ok(List.of());
        }

        if (hasRole(authentication, "ROLE_CLIENTE")) {
            return ResponseEntity.ok(usuario.getId() == null
                    ? List.of()
                    : pedidoService.findByClienteId(usuario.getId()));
        }
        if (hasRole(authentication, "ROLE_ENCARGADO")) {
            if (usuario.getSucursal() == null) {
                return ResponseEntity.ok(List.of());
            }
            return ResponseEntity.ok(pedidoService.findBySucursalId(usuario.getSucursal().getId()));
        }

        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR') "
            + "or (hasRole('ENCARGADO') and @pedidoSecurity.esDeSuSucursal(#id, authentication)) "
            + "or (hasRole('CLIENTE') and @pedidoSecurity.esSuPropioPedido(#id, authentication))")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ENCARGADO','CLIENTE')")
    public ResponseEntity<Pedido> save(@RequestBody Pedido pedido, Authentication authentication) {
        // Security: clientes cannot impersonate another user as pedido owner.
        if (hasRole(authentication, "ROLE_CLIENTE")) {
            User usuario = (User) authentication.getPrincipal();
            pedido.setCliente(usuario);
        }
        return ResponseEntity.ok(pedidoService.save(pedido));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR') "
            + "or (hasRole('ENCARGADO') and @pedidoSecurity.esDeSuSucursal(#id, authentication))")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.update(id, pedido));
    }

    // Cancelar un pedido: admin siempre; encargado solo en su sucursal; cliente solo el suyo.
    @PreAuthorize("hasRole('ADMINISTRADOR') "
            + "or (hasRole('ENCARGADO') and @pedidoSecurity.esDeSuSucursal(#id, authentication)) "
            + "or (hasRole('CLIENTE') and @pedidoSecurity.esSuPropioPedido(#id, authentication))")
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        pedidoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

    // Confirmar/modificar un pedido: solo admin o encargado de la sucursal (el cliente no confirma).
    @PreAuthorize("hasRole('ADMINISTRADOR') "
            + "or (hasRole('ENCARGADO') and @pedidoSecurity.esDeSuSucursal(#id, authentication))")
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmar(@PathVariable Long id) {
        pedidoService.confirmar(id);
        return ResponseEntity.noContent().build();
    }

    private boolean hasRole(Authentication authentication, String role) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role::equals);
    }
}


