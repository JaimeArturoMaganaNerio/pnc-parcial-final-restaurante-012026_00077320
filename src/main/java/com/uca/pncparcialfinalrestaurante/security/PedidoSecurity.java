package com.uca.pncparcialfinalrestaurante.security;

import com.uca.pncparcialfinalrestaurante.entity.Pedido;
import com.uca.pncparcialfinalrestaurante.entity.User;
import com.uca.pncparcialfinalrestaurante.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

// (Security)
@Component("pedidoSecurity")
@AllArgsConstructor
public class PedidoSecurity {

    private PedidoRepository pedidoRepository;

    // Security: combines role with branch ownership so an Encargado cannot operate outside their own sucursal.
    public boolean esDeSuSucursal(Long pedidoId, Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof User usuario)) {
            return false;
        }
        if (usuario.getSucursal() == null) return false;
        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);
        if (pedido == null || pedido.getMesa() == null || pedido.getMesa().getSucursal() == null) return false;
        Long sucursalDelPedido = pedido.getMesa().getSucursal().getId();
        return sucursalDelPedido.equals(usuario.getSucursal().getId());
    }

    // Security: Cliente can only affect their own orders, blocking cross-user access even with authenticated token.
    public boolean esSuPropioPedido(Long pedidoId, Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof User usuario)) {
            return false;
        }
        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);
        if (pedido == null || pedido.getCliente() == null) return false;
        return pedido.getCliente().getId().equals(usuario.getId());
    }
}

