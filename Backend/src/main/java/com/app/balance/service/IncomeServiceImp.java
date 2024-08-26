package com.app.balance.service;

import com.app.balance.model.entity.Income;
import com.app.balance.model.entity.User;
import com.app.balance.model.mapper.IncomeMapper;
import com.app.balance.model.request.IncomeRequest;
import com.app.balance.model.response.IncomeResponse;
import com.app.balance.model.response.SpentResponse;
import com.app.balance.repository.IncomeRepository;
import com.app.balance.service.abstraction.IncomeService;
import com.app.balance.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
    public IncomeResponse createIncome(IncomeRequest request) {
        User user = currentUser.getCurrentUser();
        Income income = mapper.dtoToEntity(request);
        income.setUser(user);
        repository.save(income);
        IncomeResponse incomeResponse = mapper.entitytoDto(income);
        return incomeResponse;
    }

    @Override
    public List<IncomeResponse> findAllByUser() {
        User user = currentUser.getCurrentUser();
        List<Income> incomes = repository.findByUser(user);
        List<IncomeResponse> incomesResponse = incomes.stream().map(
                mapper::entitytoDto).toList();
        return incomesResponse;
    }

    @Override
    public void updateIncome(IncomeRequest request) {
        boolean isIncomeExist = repository.existsById(request.getId());

        if (isIncomeExist){
            Income income = repository.findIncomeById(request.getId());
            if (request.getConcept() == null){
                income.setConcept(income.getConcept());
            } else {
                income.setConcept(request.getConcept());
            }
            if (request.getQuantity() == null){
                income.setQuantity(income.getQuantity());
            } else {
                income.setQuantity(request.getQuantity());
            }
            income.setId(income.getId());
            income.setUser(income.getUser());
            income.setCreated(income.getCreated());

            repository.save(income);
        }else {
            throw new NoSuchElementException("El ingreso no existe");
        }
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
