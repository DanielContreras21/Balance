package com.app.balance.model.response;

import com.app.balance.model.entity.Income;
import com.app.balance.model.entity.Spent;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BalanceResponse {
    private List<Income> incomes;
    private List<Spent> spents;
    private Double totalIncomes;
    private Double totalSpents;
    private Double total;
}
