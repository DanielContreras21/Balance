package com.app.balance.utils;

import com.app.balance.model.entity.User;
import com.app.balance.model.exception.UserNotExistException;
import com.app.balance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUser {

    @Autowired
    private final UserRepository repository;

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails){
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }

        if (username == null){
            throw new RuntimeException("Usuario not identified");
        }

        User user = repository.findUserByUsername(username).orElseThrow(() -> new UserNotExistException( "User not found"));

        return user;
    }
}