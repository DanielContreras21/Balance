package com.app.balance.model.mapper;

import com.app.balance.model.entity.Income;
import com.app.balance.model.request.IncomeRequest;
import com.app.balance.model.response.IncomeResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Builder
@Component
@RequiredArgsConstructor
public class IncomeMapper {

    public Income dtoToEntity(IncomeRequest request){
        return Income.builder()
                .concept(request.getConcept())
                .quantity(request.getQuantity())
                .date(new Date())
                .build();
    }

    public IncomeResponse entitytoDto(Income income) {
        return IncomeResponse.builder()
                .id(income.getId())
                .concept(income.getConcept())
                .quantity(income.getQuantity())
                .date(income.getDate())
                .build();
    }
}