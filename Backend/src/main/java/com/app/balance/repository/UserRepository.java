package com.app.balance.repository;

import com.app.balance.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String name);
    User findUserById(Long id);
    Optional<User> findUserByUsername(String name);
}
