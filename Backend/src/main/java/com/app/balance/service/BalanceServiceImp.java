package com.app.balance.service;

import com.app.balance.model.entity.Income;
import com.app.balance.model.entity.Spent;
import com.app.balance.model.response.BalanceResponse;
import com.app.balance.repository.IncomeRepository;
import com.app.balance.repository.SpentRepository;
import com.app.balance.service.abstraction.BalanceService;
import com.app.balance.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceServiceImp implements BalanceService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private SpentRepository spentRepository;

    private final CurrentUser currentUser;

    @Override
    public BalanceResponse getBalance() {
        List<Income> incomes = incomeRepository.findByUser(currentUser.getCurrentUser());
        List<Spent> spents = spentRepository.findByUser(currentUser.getCurrentUser());

        Double totalIncomes = incomes.stream()
                .mapToDouble(Income::getQuantity)
                .sum();

        Double totalSpents = spents.stream()
                .mapToDouble(Spent::getQuantity)
                .sum();

        Double total = totalIncomes - totalSpents;

        BalanceResponse response = BalanceResponse.builder()
                .incomes(incomes)
                .spents(spents)
                .totalIncomes(totalIncomes)
                .totalSpents(totalSpents)
                .total(total)
                .build();
        return response;
    }
}
