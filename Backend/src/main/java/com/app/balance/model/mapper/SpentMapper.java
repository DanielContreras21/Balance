package com.app.balance.model.mapper;

import com.app.balance.model.entity.Income;
import com.app.balance.model.entity.Spent;
import com.app.balance.model.request.IncomeRequest;
import com.app.balance.model.request.SpentRequest;
import com.app.balance.model.response.IncomeResponse;
import com.app.balance.model.response.SpentResponse;
import com.app.balance.utils.DateFormatter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Component
@RequiredArgsConstructor
public class SpentMapper {

    private final DateFormatter dateFormatter;

    public Spent dtoToEntity(SpentRequest request){


        return Spent.builder()
                .concept(request.getConcept())
                .quantity(request.getQuantity())
                .created(new Date())
                .date(dateFormatter.parseDate(request.getDate()))
                .build();
    }

    public SpentResponse entitytoDto(Spent spent) {
        return SpentResponse.builder()
                .id(spent.getId())
                .concept(spent.getConcept())
                .quantity(spent.getQuantity())
                .created(spent.getCreated())
                .date(spent.getDate())
                .build();
    }

}