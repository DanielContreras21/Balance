package com.app.balance.controller;

import com.app.balance.model.request.RoleRequest;
import com.app.balance.service.abstraction.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    @Autowired
    private final RoleService service;

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody RoleRequest request){
        service.createRole(request);
        return ResponseEntity.ok("Role created");
    }
}
