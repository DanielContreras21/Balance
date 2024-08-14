package com.app.balance.model.response;

import com.app.balance.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IncomeResponse {
    private Long id;
    private String concept;
    private Double quantity;
    private Date date;
}
