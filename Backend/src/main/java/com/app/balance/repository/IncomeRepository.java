package com.app.balance.repository;

import com.app.balance.model.entity.Income;
import com.app.balance.model.entity.Role;
import com.app.balance.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
    List<Income> findByUser(User user);
    Income findIncomeById(Long id);
}
