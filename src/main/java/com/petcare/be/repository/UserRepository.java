package com.petcare.be.repository;

import com.petcare.be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
