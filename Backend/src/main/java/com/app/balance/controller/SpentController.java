package com.app.balance.controller;

import com.app.balance.model.request.SpentRequest;
import com.app.balance.model.response.SpentResponse;
import com.app.balance.service.abstraction.SpentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/spents")
@RequiredArgsConstructor
@Tag(name = "Spents")
@CrossOrigin(origins = "*")
public class SpentController {

    @Autowired
    private final SpentService service;

    @PostMapping("/create")
    public ResponseEntity<SpentResponse> createSpent(@RequestBody SpentRequest request){
        SpentResponse response = service.createSpent(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpentResponse> getSpentById(@PathVariable Long id) {
        Optional<SpentResponse> spent = service.findById(id);
        return spent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpent(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully");
    }

    @GetMapping
    public ResponseEntity<List<SpentResponse>> getAllSpentsByUser(){
        List<SpentResponse> response = service.findAllByUser();
        return ResponseEntity.ok(response);
    }
}