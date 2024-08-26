package com.app.balance.service.abstraction;

import com.app.balance.model.request.IncomeRequest;
import com.app.balance.model.response.IncomeResponse;

import java.util.List;
import java.util.Optional;

public interface IncomeService {
    void deleteById(Long id);
    Optional<IncomeResponse> findById(Long id);
    IncomeResponse createIncome(IncomeRequest request);
    List<IncomeResponse> findAllByUser();
    void updateIncome(IncomeRequest request);
}
