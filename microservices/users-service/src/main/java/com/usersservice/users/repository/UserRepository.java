package com.usersservice.users.repository;

import com.usersservice.users.model.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Busca un usuario por su email.
     * SQL generado: SELECT * FROM users WHERE email = ?
     */
    Optional<User> findByEmail(String email);
}
