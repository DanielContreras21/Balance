package com.app.balance.service.abstraction;

import com.app.balance.model.request.LoginRequest;
import com.app.balance.model.request.RegisterRequest;
import com.app.balance.model.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest register);
    AuthResponse login(LoginRequest login);
}
