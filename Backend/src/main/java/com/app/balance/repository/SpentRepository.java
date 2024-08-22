package com.app.balance.repository;

import com.app.balance.model.entity.Spent;
import com.app.balance.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpentRepository extends JpaRepository<Spent, Long> {
    List<Spent> findByUser(User user);
}
