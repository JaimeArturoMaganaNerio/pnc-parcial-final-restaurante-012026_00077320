-- V3: Crear tablas de productos, pedidos y detalles (esqueleto)
-- Se completará con definición completa en Fase 1

-- V3: Crear tablas de productos, pedidos y detalles

CREATE TABLE producto (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(150) NOT NULL,
  precio NUMERIC(12,2) NOT NULL
);

CREATE TABLE pedido (
  id BIGSERIAL PRIMARY KEY,
  cliente_id BIGINT NOT NULL,
  mesa_id BIGINT NOT NULL,
  estado VARCHAR(20) NOT NULL,
  fecha TIMESTAMP
);

CREATE TABLE pedido_detalle (
  id BIGSERIAL PRIMARY KEY,
  pedido_id BIGINT NOT NULL,
  producto_id BIGINT NOT NULL,
  cantidad INTEGER NOT NULL
);

ALTER TABLE pedido ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES "user"(id);
ALTER TABLE pedido ADD CONSTRAINT fk_pedido_mesa FOREIGN KEY (mesa_id) REFERENCES mesa(id);
ALTER TABLE pedido_detalle ADD CONSTRAINT fk_detalle_pedido FOREIGN KEY (pedido_id) REFERENCES pedido(id);
ALTER TABLE pedido_detalle ADD CONSTRAINT fk_detalle_producto FOREIGN KEY (producto_id) REFERENCES producto(id);

