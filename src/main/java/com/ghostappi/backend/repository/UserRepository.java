package com.ghostappi.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ghostappi.backend.model.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    boolean existsByEmail(String email);
}
