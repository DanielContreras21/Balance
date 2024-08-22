package com.app.balance.repository;

import com.app.balance.model.entity.User;
import com.app.balance.model.request.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String name);
    User findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    boolean existsByUsername(String name);
}
