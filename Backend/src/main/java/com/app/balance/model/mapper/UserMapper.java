package com.app.balance.model.mapper;

import com.app.balance.model.entity.Role;
import com.app.balance.model.entity.User;
import com.app.balance.model.enums.RoleEnum;
import com.app.balance.model.request.RegisterRequest;
import com.app.balance.model.request.UserRequest;
import com.app.balance.model.response.AuthResponse;
import com.app.balance.model.response.UserResponse;
import com.app.balance.repository.RoleRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Builder
@Component
@RequiredArgsConstructor
public class UserMapper {

    @Autowired
    private final PasswordEncoder encoder;

    @Autowired
    private final RoleRepository roleRepository;

    public AuthResponse entityToDtoRegister(User user){
        return AuthResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public User dtoRegisterToEntity(RegisterRequest register){
        Role role = roleRepository.findByName(RoleEnum.valueOf("USER")).orElseThrow();

        return User.builder()
                .name(register.getName())
                .username(register.getUsername())
                .password(encoder.encode(register.getPassword()))
                .email(register.getEmail())
                .role(role)
                .created(new Date())
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();
    }

    public User registerSuperUser(RegisterRequest register){
        Role role = roleRepository.findByName(RoleEnum.valueOf("DEVELOPER")).orElseThrow();

        return User.builder()
                .name(register.getName())
                .username(register.getUsername())
                .password(encoder.encode(register.getPassword()))
                .email(register.getEmail())
                .role(role)
                .created(new Date())
                .isEnabled(true)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .build();
    }

    public UserResponse EntityToDtoGet(User user){

        String role = user.getRole().toString();

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(role)
                .isEnabled(user.isEnabled())
                .isAccountNonLocked(user.isAccountNonLocked())
                .isCredentialsNonExpired(user.isCredentialsNonExpired())
                .isAccountNonExpired(user.isAccountNonExpired())
                .build();
    }
}
