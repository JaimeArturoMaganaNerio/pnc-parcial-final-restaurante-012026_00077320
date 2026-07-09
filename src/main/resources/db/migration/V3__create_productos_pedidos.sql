-- V3: Crear tablas de productos, pedidos y detalles (esqueleto)
-- Se completará con definición completa en Fase 1

-- CREATE TABLE producto (
--   id BIGSERIAL PRIMARY KEY,
--   nombre VARCHAR(150) NOT NULL,
--   precio NUMERIC(12,2) NOT NULL
-- );

-- CREATE TABLE pedido (
--   id BIGSERIAL PRIMARY KEY,
--   cliente_id BIGINT NOT NULL,
--   mesa_id BIGINT NOT NULL,
--   estado VARCHAR(20) NOT NULL,
--   fecha TIMESTAMP WITH TIME ZONE
-- );

-- CREATE TABLE pedido_detalle (
--   id BIGSERIAL PRIMARY KEY,
--   pedido_id BIGINT NOT NULL,
--   producto_id BIGINT NOT NULL,
--   cantidad INTEGER NOT NULL
-- );

