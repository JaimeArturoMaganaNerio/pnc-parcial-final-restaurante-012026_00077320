-- V4: Semillas iniciales (roles y admin)
-- Se completará en Fase 1 con inserts que usan contraseña BCrypt

-- INSERT INTO role (name) VALUES ('ROLE_ADMINISTRADOR');
-- INSERT INTO role (name) VALUES ('ROLE_ENCARGADO');
-- INSERT INTO role (name) VALUES ('ROLE_CLIENTE');

-- V4: Semillas iniciales (roles y admin)

INSERT INTO role (name) VALUES ('ROLE_ADMINISTRADOR');
INSERT INTO role (name) VALUES ('ROLE_ENCARGADO');
INSERT INTO role (name) VALUES ('ROLE_CLIENTE');

-- Generar hash BCrypt usando la función crypt de Postgres (pgcrypto). Esto evita almacenar el secreto en texto claro.
-- Requiere la extensión pgcrypto en la base de datos.
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Insertar usuario admin con contraseña 'admin' hasheada con bcrypt
INSERT INTO "user" (username, password) VALUES ('admin', crypt('admin', gen_salt('bf', 10)));

-- Asumimos que los role IDs son 1..3 en el orden insertado; asignar rol administrador al usuario creado
INSERT INTO user_roles (user_id, role_id)
  SELECT u.id, r.id FROM "user" u, role r WHERE u.username = 'admin' AND r.name = 'ROLE_ADMINISTRADOR';

