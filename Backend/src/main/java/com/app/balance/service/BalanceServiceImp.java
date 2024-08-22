package com.app.balance.service;

import com.app.balance.model.entity.Income;
import com.app.balance.model.entity.Spent;
import com.app.balance.model.mapper.IncomeMapper;
import com.app.balance.model.mapper.SpentMapper;
import com.app.balance.model.response.BalanceResponse;
import com.app.balance.model.response.IncomeResponse;
import com.app.balance.model.response.SpentResponse;
import com.app.balance.repository.IncomeRepository;
import com.app.balance.repository.SpentRepository;
import com.app.balance.service.abstraction.BalanceService;
import com.app.balance.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceServiceImp implements BalanceService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private SpentRepository spentRepository;

    private final CurrentUser currentUser;

    private final IncomeMapper incomeMapper;

    private final SpentMapper spentMapper;

    @Override
    public BalanceResponse getBalance() {
        List<Income> incomes = incomeRepository.findByUser(currentUser.getCurrentUser());
        List<Spent> spents = spentRepository.findByUser(currentUser.getCurrentUser());

        List<IncomeResponse> incomeResponses = incomes.stream().map(
                incomeMapper::entitytoDto).toList();
        List<SpentResponse> spentResponses = spents.stream().map(
                spentMapper::entitytoDto).toList();


        Double totalIncomes = incomes.stream()
                .mapToDouble(Income::getQuantity)
                .sum();

        Double totalSpents = spents.stream()
                .mapToDouble(Spent::getQuantity)
                .sum();

        Double total = totalIncomes - totalSpents;

        BalanceResponse response = BalanceResponse.builder()
                .incomes(incomeResponses)
                .spents(spentResponses)
                .totalIncomes(totalIncomes)
                .totalSpents(totalSpents)
                .total(total)
                .build();

        return response;
    }
}
