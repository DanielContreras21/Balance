package com.app.balance.service.abstraction;

import com.app.balance.model.request.UserRequest;
import com.app.balance.model.response.UserResponse;

public interface UserService {
    UserResponse getUserById(Long id);
    void updateUser(UserRequest request);
}
