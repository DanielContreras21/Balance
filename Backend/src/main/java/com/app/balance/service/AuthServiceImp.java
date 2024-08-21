package com.app.balance.service;

import com.app.balance.model.entity.User;
import com.app.balance.model.exception.UserAlreadyExistException;
import com.app.balance.model.mapper.UserMapper;
import com.app.balance.model.request.LoginRequest;
import com.app.balance.model.request.RegisterRequest;
import com.app.balance.model.response.AuthResponse;
import com.app.balance.model.response.LoginResponse;
import com.app.balance.repository.UserRepository;
import com.app.balance.service.abstraction.AuthService;
import com.app.balance.utils.JwtUtils;
import com.app.balance.utils.UserDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
        boolean isUserExist = repository.existsByEmail(register.getEmail());
        if (!isUserExist){
            if (register.getPassword().equals(register.getConfirmPassword())){
                User user = mapper.dtoRegisterToEntity(register);
                repository.save(user);
                return mapper.entityToDtoRegister(user);
            } else {
                throw new BadCredentialsException("Las Contraseñas no Coinciden");
            }
        }else {
            throw new UserAlreadyExistException("El usuario ya existe");
        }
    }

    @Override
    public LoginResponse login(LoginRequest login) {
        String username = login.getUsername();
        String password = login.getPassword();

        Authentication authentication = this.authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        LoginResponse response = new LoginResponse(username, accessToken);

        return response;
    }

    public Authentication authenticate(String username, String password) {
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);

      if (userDetails == null){
          throw new BadCredentialsException("Nombre de usuario o contraseña incorrectos");
      }
      if (!passwordEncoder.matches(password, userDetails.getPassword())){
          throw new BadCredentialsException("Nombre de usuario o contraseña incorrectos");
      }
      return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public AuthResponse registerSuperUser(RegisterRequest register) {
        boolean isUserExist = repository.existsByEmail(register.getEmail());
        if (!isUserExist){
            User user = mapper.registerSuperUser(register);
            repository.save(user);
            return mapper.entityToDtoRegister(user);
        }else {
            throw new UserAlreadyExistException("El usuario ya existe");
        }
    }
}