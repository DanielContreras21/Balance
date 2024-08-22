package com.app.balance.service;

import com.app.balance.model.entity.User;
import com.app.balance.model.mapper.UserMapper;
import com.app.balance.model.request.LoginRequest;
import com.app.balance.model.request.RegisterRequest;
import com.app.balance.model.response.AuthResponse;
import com.app.balance.model.response.LoginResponse;
import com.app.balance.repository.UserRepository;
import com.app.balance.service.abstraction.AuthService;
import com.app.balance.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final UserMapper mapper;

    @Autowired
    private final JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest register) {
        boolean isEmailExist = repository.existsByEmail(register.getEmail());
        boolean isUserExist = repository.existsByUsername(register.getUsername());
        if (isUserExist) {
            throw new DuplicateKeyException("El nombre de usuario no se encuentra disponible");
        }
        if(isEmailExist) {
            throw new DuplicateKeyException("El correo electrónico no se encuentra disponible");
        }
        if (!register.getPassword().equals(register.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }
        if (!register.getEmail().equals(register.getConfirmEmail())){
            throw new IllegalArgumentException("Los correo electrónicos no coinciden");
        }
        User user = mapper.dtoRegisterToEntity(register);
        repository.save(user);
        return mapper.entityToDtoRegister(user);
    }

    @Override
    public LoginResponse login(LoginRequest login) {
        String username = login.getUsername();
        String password = login.getPassword();
        boolean isUserExist = repository.existsByUsername(username);

        if (!isUserExist){
            throw new NoSuchElementException("Ingrese un nombre de usuario válido");
        }
        if (username.isBlank()) {
            throw new NoSuchElementException("Ingrese un nombre de usuario válido");
        }
        if (password.isBlank()) {
            throw new NoSuchElementException("Ingrese una contraseña válida");
        }
        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        LoginResponse response = new LoginResponse(username, accessToken);

        return response;
    }

    private Authentication authenticate(String username, String password) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (userDetails == null){
          throw new IllegalArgumentException("Nombre de usuario o contraseña incorrectos");
      }
      if (!passwordEncoder.matches(password, userDetails.getPassword())){
          throw new IllegalArgumentException("Nombre de usuario o contraseña incorrectos");
      }
      return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public AuthResponse registerSuperUser(RegisterRequest register) {
        boolean isEmailExist = repository.existsByEmail(register.getEmail());
        boolean isUserExist = repository.existsByUsername(register.getUsername());
        if (isUserExist) {
            throw new DuplicateKeyException("El nombre de usuario no se encuentra disponible");
        }
        if(isEmailExist) {
            throw new DuplicateKeyException("El correo electrónico no se encuentra disponible");
        }
        if (!register.getPassword().equals(register.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }
        if (!register.getEmail().equals(register.getConfirmEmail())){
            throw new IllegalArgumentException("Los correo electrónicos no coinciden");
        }
        User user = mapper.registerSuperUser(register);
        repository.save(user);
        return mapper.entityToDtoRegister(user);
    }
}