package com.nicoarbelaez.ratebook.endpoint.auth;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
        Optional<Auth> findByEmail(String email);

        Optional<Auth> findByUserId(Long userId);
}
