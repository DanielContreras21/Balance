package com.app.balance.controller;

import com.app.balance.model.entity.User;
import com.app.balance.model.request.UserRequest;
import com.app.balance.model.response.UserResponse;
import com.app.balance.service.abstraction.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private final UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> searchById(@Valid @PathVariable Long id){
        UserResponse response = service.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@Valid @RequestBody UserRequest request){
        service.updateUser(request);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@Valid @RequestBody UserRequest request){
        service.updatePassword(request);
        return ResponseEntity.ok("Contraseña actualizada correctamente");
    }

    @PutMapping("/updateRole")
    public ResponseEntity<String> updateRole(@Valid @RequestBody UserRequest request){
        service.updateRole(request);
        return ResponseEntity.ok("Rol actualizado correctamente");
    }

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> response = service.getAllUsers();
        return ResponseEntity.ok(response);
    }
}
