package com.app.balance.model.request;

import com.app.balance.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String name;
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String confirmEmail;
}
