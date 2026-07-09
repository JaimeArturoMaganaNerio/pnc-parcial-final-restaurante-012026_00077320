package com.uca.pncparcialfinalrestaurante.service;

import com.uca.pncparcialfinalrestaurante.entity.Pedido;
import com.uca.pncparcialfinalrestaurante.entity.PedidoDetalle;
import com.uca.pncparcialfinalrestaurante.entity.PedidoEstado;
import com.uca.pncparcialfinalrestaurante.repository.MesaRepository;
import com.uca.pncparcialfinalrestaurante.repository.PedidoRepository;
import com.uca.pncparcialfinalrestaurante.repository.ProductoRepository;
import com.uca.pncparcialfinalrestaurante.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// (Service)
@Service
@AllArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private PedidoRepository pedidoRepository;
    private UserRepository userRepository;
    private MesaRepository mesaRepository;
    private ProductoRepository productoRepository;

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public List<Pedido> findByClienteId(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Pedido> findBySucursalId(Long sucursalId) {
        return pedidoRepository.findByMesaSucursalId(sucursalId);
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    }

    @Override
    public Pedido save(Pedido pedido) {
        if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
            throw new RuntimeException("El pedido requiere cliente");
        }
        if (pedido.getMesa() == null || pedido.getMesa().getId() == null) {
            throw new RuntimeException("El pedido requiere mesa");
        }

        pedido.setCliente(userRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado")));
        pedido.setMesa(mesaRepository.findById(pedido.getMesa().getId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada")));
        pedido.setEstado(PedidoEstado.CREADO);
        pedido.setFecha(LocalDateTime.now());

        if (pedido.getDetalles() != null) {
            for (PedidoDetalle detalle : pedido.getDetalles()) {
                if (detalle.getProducto() == null || detalle.getProducto().getId() == null) {
                    throw new RuntimeException("Cada detalle requiere producto");
                }
                detalle.setProducto(productoRepository.findById(detalle.getProducto().getId())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado")));
                detalle.setPedido(pedido);
            }
        }

        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(Long id, Pedido pedido) {
        Pedido current = findById(id);

        if (pedido.getMesa() != null && pedido.getMesa().getId() != null) {
            current.setMesa(mesaRepository.findById(pedido.getMesa().getId())
                    .orElseThrow(() -> new RuntimeException("Mesa no encontrada")));
        }
        if (pedido.getEstado() != null) {
            current.setEstado(pedido.getEstado());
        }

        return pedidoRepository.save(current);
    }

    @Override
    public void confirmar(Long id) {
        Pedido pedido = findById(id);
        pedido.setEstado(PedidoEstado.CONFIRMADO);
        pedidoRepository.save(pedido);
    }

    @Override
    public void cancelar(Long id) {
        Pedido pedido = findById(id);
        pedido.setEstado(PedidoEstado.CANCELADO);
        pedidoRepository.save(pedido);
    }
}

