-- V2: Crear tablas de sucursales y mesas (esqueleto)
-- Se completará con definición completa en Fase 1

-- CREATE TABLE sucursal (
--   id BIGSERIAL PRIMARY KEY,
--   nombre VARCHAR(150) NOT NULL,
--   direccion VARCHAR(255)
-- );
-- CREATE TABLE mesa (
--   id BIGSERIAL PRIMARY KEY,
--   numero INTEGER NOT NULL,
--   capacidad INTEGER NOT NULL,
--   estado VARCHAR(20) NOT NULL,
--   sucursal_id BIGINT NOT NULL
-- );

-- V2: Crear tablas de sucursales y mesas

CREATE TABLE sucursal (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(150) NOT NULL,
  direccion VARCHAR(255)
);

CREATE TABLE mesa (
  id BIGSERIAL PRIMARY KEY,
  numero INTEGER NOT NULL,
  capacidad INTEGER NOT NULL,
  estado VARCHAR(20) NOT NULL,
  sucursal_id BIGINT NOT NULL
);

ALTER TABLE mesa ADD CONSTRAINT fk_mesa_sucursal FOREIGN KEY (sucursal_id) REFERENCES sucursal(id);

-- Agregar FK de usuario hacia sucursal ahora que `sucursal` existe
ALTER TABLE "user" ADD CONSTRAINT fk_user_sucursal FOREIGN KEY (sucursal_id) REFERENCES sucursal(id);

