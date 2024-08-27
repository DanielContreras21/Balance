package com.app.balance.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpentResponse {
    private Long id;
    private String concept;
    private Double quantity;
    private Date date;
    private Date created;
}
