package com.app.balance.controller;

import com.app.balance.model.entity.Income;
import com.app.balance.model.request.IncomeRequest;
import com.app.balance.model.response.IncomeResponse;
import com.app.balance.service.abstraction.IncomeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/incomes")
@RequiredArgsConstructor
@Tag(name = "Incomes")
public class IncomeController {

    @Autowired
    private final IncomeService service;

    @PostMapping("/create")
    public ResponseEntity<?> createIncome(@RequestBody IncomeRequest request){
        service.createIncome(request);
        return ResponseEntity.ok("Income created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeResponse> getIncomeById(@PathVariable Long id) {
        Optional<IncomeResponse> income = service.findById(id);
        return income.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully");

    }
}

