package com.app.balance.model.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BalanceResponse {
    private List<IncomeResponse> incomes;
    private List<SpentResponse> spents;
    private Double totalIncomes;
    private Double totalSpents;
    private Double total;
}
