package com.uca.pncparcialfinalrestaurante.repository;

import com.uca.pncparcialfinalrestaurante.entity.Role;
import com.uca.pncparcialfinalrestaurante.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// (Repository)
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}

