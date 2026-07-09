-- V1: Crear tablas de usuarios y roles (esqueleto)
-- Se completará con definición completa en Fase 1

-- Example placeholders:
-- V1: Crear tablas de usuarios y roles

CREATE TABLE role (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE "user" (
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  sucursal_id BIGINT
);

CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id)
);



