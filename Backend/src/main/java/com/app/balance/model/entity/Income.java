package com.app.balance.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Ingrese un concepto")
    @NotBlank(message = "Ingrese un concepto")
    private String concept;

    @NotNull(message = "Ingrese una cantidad")
    private Double quantity;

    @NotNull(message = "Ingresa una fecha")
    @Column(name = "date")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date date;

    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
