package com.app.balance.service;

import com.app.balance.model.entity.User;
import com.app.balance.model.exception.UserAlreadyExistException;
import com.app.balance.model.mapper.UserMapper;
import com.app.balance.model.request.LoginRequest;
import com.app.balance.model.request.RegisterRequest;
import com.app.balance.model.response.AuthResponse;
import com.app.balance.repository.UserRepository;
import com.app.balance.service.abstraction.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final UserMapper mapper;

    @Override
    public AuthResponse register(RegisterRequest register) {
        boolean isUserExist = repository.existsByEmail(register.getEmail());
        if (!isUserExist){
            User user = mapper.dtoRegisterToEntity(register);
            repository.save(user);
            AuthResponse response = mapper.entityToDtoRegister(user);
            return response;
        }else {
            throw new UserAlreadyExistException("El usuario ya existe");
        }
    }

    @Override
    public AuthResponse login(LoginRequest login) {
        return null;
    }
}