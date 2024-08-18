package com.app.balance.controller;

import com.app.balance.model.response.BalanceResponse;
import com.app.balance.service.abstraction.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BalanceController {

    @Autowired
    private final BalanceService service;

    @GetMapping
    public ResponseEntity<BalanceResponse> getBalance() {
        BalanceResponse response = service.getBalance();
        return ResponseEntity.ok(response);
    }
}
