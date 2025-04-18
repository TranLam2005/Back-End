package com.example.DATK_backend.repository;

import com.example.DATK_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUser extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
    boolean existsByUserName(String userName);
}
