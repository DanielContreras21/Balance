package com.app.balance.controller;

import com.app.balance.model.request.RoleRequest;
import com.app.balance.service.abstraction.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "Roles")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private final RoleService service;

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody RoleRequest request){
        service.createRole(request);
        return ResponseEntity.ok("Role created");
    }
}
