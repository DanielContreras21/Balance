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
import org.springframework.security.authentication.BadCredentialsException;
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
        if (!isUserExist){
            if(!isEmailExist){
                if (register.getPassword().equals(register.getConfirmPassword())){
                    if (register.getEmail().equals(register.getConfirmEmail())){
                        User user = mapper.dtoRegisterToEntity(register);
                        repository.save(user);
                        return mapper.entityToDtoRegister(user);
                    } else {
                        throw new IllegalArgumentException("Los correo electrónicos no coinciden");
                    }
                }else {
                    throw new IllegalArgumentException("Las contraseñas no coinciden");
                }
            }else {
                throw new DuplicateKeyException("El correo electrónico no se encuentra disponible");
            }
        }else {
            throw new DuplicateKeyException("El nombre de usuario no se encuentra disponible");
        }
    }

    @Override
    public LoginResponse login(LoginRequest login) {
        String username = login.getUsername();
        String password = login.getPassword();

        if (!username.isBlank()){
            if (!password.isBlank()){
                Authentication authentication = this.authenticate(username, password);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String accessToken = jwtUtils.createToken(authentication);
                LoginResponse response = new LoginResponse(username, accessToken);

                return response;
            }else {
                throw new NoSuchElementException("Ingrese una contraseña válida");
            }
        } else {
            throw new NoSuchElementException("Ingrese un nombre de usuario válido");
        }
    }

    public Authentication authenticate(String username, String password) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);


      if (userDetails == null || !userDetails.equals(userDetails)){
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
        if (!isUserExist){
            if(!isEmailExist){
                if (register.getPassword().equals(register.getConfirmPassword())){
                    if (register.getEmail().equals(register.getConfirmEmail())){
                        User user = mapper.dtoRegisterToEntity(register);
                        repository.save(user);
                        return mapper.entityToDtoRegister(user);
                    } else {
                        throw new IllegalArgumentException("Los correo electrónicos no coinciden");
                    }
                }else {
                    throw new IllegalArgumentException("Las contraseñas no coinciden");
                }
            }else {
                throw new DuplicateKeyException("El correo electrónico no se encuentra disponible");
            }
        }else {
            throw new DuplicateKeyException("El nombre de usuario no se encuentra disponible");
        }
    }
}