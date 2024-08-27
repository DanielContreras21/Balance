package com.app.balance.model.mapper;

import com.app.balance.model.entity.Income;
import com.app.balance.model.request.IncomeRequest;
import com.app.balance.model.response.IncomeResponse;
import com.app.balance.utils.DateFormatter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Builder
@Component
@RequiredArgsConstructor
public class IncomeMapper {

    private final DateFormatter dateFormatter;

    public Income dtoToEntity(IncomeRequest request){
        return Income.builder()
                .concept(request.getConcept())
                .quantity(request.getQuantity())
                .created(new Date())
                .date(dateFormatter.parseDate(request.getDate()))
                .build();
    }

    public IncomeResponse entitytoDto(Income income) {
        return IncomeResponse.builder()
                .id(income.getId())
                .concept(income.getConcept())
                .quantity(income.getQuantity())
                .created(income.getCreated())
                .date(income.getDate())
                .build();
    }
}
