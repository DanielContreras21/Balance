package com.app.balance.service;

import com.app.balance.model.entity.Role;
import com.app.balance.model.entity.User;
import com.app.balance.model.enums.RoleEnum;
import com.app.balance.model.mapper.UserMapper;
import com.app.balance.model.request.UserRequest;
import com.app.balance.model.response.UserResponse;
import com.app.balance.repository.RoleRepository;
import com.app.balance.repository.UserRepository;
import com.app.balance.service.abstraction.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final UserMapper mapper;

    private final RoleRepository roleRepository;

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
        boolean isUserUsernameExist = repository.existsByUsername(request.getUsername());
        boolean isEmailExist = repository.existsByEmail(request.getEmail());
        if (isUserExist){
            User user = repository.findUserById(request.getId());
            if (request.getUsername() != null){
                if (isUserUsernameExist){
                    throw new DuplicateKeyException("El Nombre de usuario no se encuentra disponible");
                } else {
                    user.setUsername(request.getUsername());
                }
            }else {
                user.setUsername(user.getUsername());
            }
            if (request.getEmail() != null){
                if(isEmailExist) {
                    throw new DuplicateKeyException("El correo electrónico no se encuentra disponible");
                } else {
                    user.setEmail(request.getEmail());
                }
            } else {
                user.setEmail(user.getEmail());
            }
            if (request.getName() != null){
                user.setName(request.getName());
            } else {
                user.setName(user.getName());
            }
            user.setPassword(user.getPassword());
            user.setRole(user.getRole());
            user.setId(user.getId());
            user.setCreated(user.getCreated());
            user.setAccountNonExpired(user.isAccountNonExpired());
            user.setAccountNonLocked(user.isAccountNonLocked());
            user.setCredentialsNonExpired(user.isCredentialsNonExpired());
            user.setEnabled(user.isEnabled());

            repository.save(user);
        } else {
            throw new NoSuchElementException("EL usuario no existe");
        }
    }

    @Override
    public void updatePassword(UserRequest request) {
        boolean isUserExist = repository.existsById(request.getId());
        if (isUserExist){
            User user = repository.findUserById(request.getId());

            if (request.getPassword() != null){

                user.setPassword(request.getPassword());

                user.setUsername(user.getUsername());
                user.setEmail(user.getEmail());
                user.setName(user.getName());
                user.setRole(user.getRole());
                user.setId(user.getId());
                user.setCreated(user.getCreated());
                user.setAccountNonExpired(user.isAccountNonExpired());
                user.setAccountNonLocked(user.isAccountNonLocked());
                user.setCredentialsNonExpired(user.isCredentialsNonExpired());
                user.setEnabled(user.isEnabled());

                repository.save(user);
            } else {
                throw new IllegalArgumentException("Ingrese una contraseña Válida");
            }

        } else {
            throw new NoSuchElementException("EL usuario no existe");
        }
    }

    @Override
    public void updateRole(UserRequest request) {
        boolean isUserExist = repository.existsById(request.getId());
        if (isUserExist){
            User user = repository.findUserById(request.getId());

            if (request.getRole() != null){

                Role role = roleRepository.findByName(RoleEnum.valueOf(request.getName())).orElseThrow(() -> new NoSuchElementException("El rol no existe"));

                user.setRole(role);
                user.setUsername(user.getUsername());
                user.setEmail(user.getEmail());
                user.setName(user.getName());
                user.setPassword(user.getPassword());
                user.setId(user.getId());
                user.setCreated(user.getCreated());
                user.setAccountNonExpired(user.isAccountNonExpired());
                user.setAccountNonLocked(user.isAccountNonLocked());
                user.setCredentialsNonExpired(user.isCredentialsNonExpired());
                user.setEnabled(user.isEnabled());

                repository.save(user);
            } else {
                throw new IllegalArgumentException("Ingrese un rol válido");
            }
        } else {
            throw new NoSuchElementException("EL usuario no existe");
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return repository.findAll().stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole().getName().toString(),
                        user.getCreated(),
                        user.isAccountNonExpired(),
                        user.isAccountNonLocked(),
                        user.isCredentialsNonExpired(),
                        user.isEnabled()
                ))
                .collect(Collectors.toList());
    }
}
