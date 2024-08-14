package com.app.balance.model.mapper;

import com.app.balance.model.entity.Income;
import com.app.balance.model.entity.Spent;
import com.app.balance.model.request.IncomeRequest;
import com.app.balance.model.request.SpentRequest;
import com.app.balance.model.response.IncomeResponse;
import com.app.balance.model.response.SpentResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Builder
@Component
@RequiredArgsConstructor
public class SpentMapper {

    public Spent dtoToEntity(SpentRequest request){
        return Spent.builder()
                .concept(request.getConcept())
                .quantity(request.getQuantity())
                .date(new Date())
                .build();
    }

    public SpentResponse entitytoDto(Spent spent) {
        return SpentResponse.builder()
                .id(spent.getId())
                .concept(spent.getConcept())
                .quantity(spent.getQuantity())
                .date(spent.getDate())
                .build();
    }
}