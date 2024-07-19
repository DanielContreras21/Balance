package com.app.balance.model.mapper;

import com.app.balance.model.entity.Role;
import com.app.balance.model.entity.User;
import com.app.balance.model.enums.RoleEnum;
import com.app.balance.model.request.RegisterRequest;
import com.app.balance.model.request.UserRequest;
import com.app.balance.model.response.AuthResponse;
import com.app.balance.model.response.UserResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Component
@RequiredArgsConstructor
public class UserMapper {

    @Autowired
    private final PasswordEncoder encoder;

    public AuthResponse entityToDtoRegister(User user){
        return AuthResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();
    }

    public User dtoRegisterToEntity(RegisterRequest register){

        Set<Role> roles = register.getRoles().stream().map(
                role -> Role.builder()
                        .name(RoleEnum.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        return User.builder()
                .name(register.getName())
                .username(register.getUsername())
                .password(encoder.encode(register.getPassword()))
                .email(register.getEmail())
                .roles(roles)
                .build();
    }

    public UserResponse EntityToDtoGet(User user){

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

    public User dtoUpdateToEntity(UserRequest request){

        Set<Role> roles = request.getRoles().stream().map(
                role -> Role.builder()
                        .name(RoleEnum.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        return User.builder()
                .id(request.getId())
                .name(request.getName())
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .email(request.getEmail())
                .roles(roles)
                .build();
    }

}
