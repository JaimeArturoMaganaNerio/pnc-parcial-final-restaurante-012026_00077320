package com.uca.pncparcialfinalrestaurante.repository;

import com.uca.pncparcialfinalrestaurante.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// (Repository)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

