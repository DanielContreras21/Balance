package com.app.balance.model.mapper;

import com.app.balance.model.entity.Permission;
import com.app.balance.model.entity.Role;
import com.app.balance.model.entity.User;
import com.app.balance.model.enums.PermissionEnum;
import com.app.balance.model.enums.RoleEnum;
import com.app.balance.model.request.RegisterRequest;
import com.app.balance.model.request.RoleRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Builder
public class RoleMapper {

    public Role dtoCreateToEntity(RoleRequest request){

        Set<Permission> permissions = request.getPermissions().stream().map(
                        permission -> Permission.builder()
                                .name(PermissionEnum.valueOf(permission))
                                .build())
                .collect(Collectors.toSet());

        return Role.builder()
                .name(request.getName())
                .permissions(permissions)
                .build();
    }
}
