package com.app.balance.service;

import com.app.balance.model.entity.User;
import com.app.balance.model.mapper.UserMapper;
import com.app.balance.model.request.UserRequest;
import com.app.balance.model.response.UserResponse;
import com.app.balance.repository.UserRepository;
import com.app.balance.service.abstraction.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final UserMapper mapper;

    @Override
    public UserResponse getUserById(Long id) {
        boolean isUserExist = repository.existsById(id);
        User user = repository.findUserById(id);
        UserResponse searched = mapper.EntityToDtoGet(user);
        if (isUserExist){
            return searched;
        }else {
            throw new NoSuchElementException("EL usuario no existe");
        }
    }

    @Override
    public void updateUser(UserRequest request) {
        boolean isUserExist = repository.existsById(request.getId());
        if (isUserExist){
            User user = mapper.dtoUpdateToEntity(request);
            repository.save(user);
        } else {
            throw new NoSuchElementException("EL usuario no existe");
        }
    }
}
