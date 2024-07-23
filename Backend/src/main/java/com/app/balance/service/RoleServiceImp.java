package com.app.balance.service;

import com.app.balance.model.entity.Role;
import com.app.balance.model.mapper.RoleMapper;
import com.app.balance.model.request.RoleRequest;
import com.app.balance.repository.RoleRepository;
import com.app.balance.service.abstraction.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private final RoleRepository repository;

    @Autowired
    private final RoleMapper mapper;

    @Override
    public void createRole(RoleRequest request) {
        Role role = mapper.dtoCreateToEntity(request);
        repository.save(role);
    }
}
