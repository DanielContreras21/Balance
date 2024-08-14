package com.app.balance.service;

import com.app.balance.model.entity.Income;
import com.app.balance.model.entity.User;
import com.app.balance.model.mapper.IncomeMapper;
import com.app.balance.model.request.IncomeRequest;
import com.app.balance.model.response.IncomeResponse;
import com.app.balance.repository.IncomeRepository;
import com.app.balance.service.abstraction.IncomeService;
import com.app.balance.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncomeServiceImp implements IncomeService {

    @Autowired
    private final CurrentUser currentUser;

    @Autowired
    private IncomeRepository repository;

    @Autowired
    private IncomeMapper mapper;

    @Override
    public void createIncome(IncomeRequest request) {
        User user = currentUser.getCurrentUser();
        Income income = mapper.dtoToEntity(request);
        income.setUser(user);
        repository.save(income);
    }

    @Override
    public List<Income> findAllByUser() {
        User user = currentUser.getCurrentUser();
        List<Income> incomes = repository.findByUser(user);
        return incomes;
    }

    @Override
    public Optional<IncomeResponse> findById(Long id) {
        return repository.findById(id)
                .map(mapper::entitytoDto);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
