package com.app.balance.service.abstraction;

import com.app.balance.model.request.LoginRequest;
import com.app.balance.model.request.RegisterRequest;
import com.app.balance.model.response.AuthResponse;
import com.app.balance.model.response.LoginResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest register);
    LoginResponse login(LoginRequest login);
    AuthResponse registerSuperUser(RegisterRequest register);
}
