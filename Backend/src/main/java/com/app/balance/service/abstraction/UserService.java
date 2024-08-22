package com.app.balance.service.abstraction;

import com.app.balance.model.request.UserRequest;
import com.app.balance.model.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getUserById(Long id);
    void updateUser(UserRequest request);
    void updatePassword(UserRequest request);
    void updateRole(UserRequest request);
    List<UserResponse> getAllUsers();
}
