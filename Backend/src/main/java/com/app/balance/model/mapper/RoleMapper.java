package com.app.balance.model.mapper;

import com.app.balance.model.entity.Permission;
import com.app.balance.model.entity.Role;
import com.app.balance.model.enums.PermissionEnum;
import com.app.balance.model.request.RoleRequest;
import com.app.balance.repository.PermissionRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Builder
public class RoleMapper {

    @Autowired
    private final PermissionRepository permissionRepository;

    public Role dtoCreateToEntity(RoleRequest request){
        Set<Permission> permissions = request.getPermissions().stream()
                .map(permissionName -> {
                    Optional<Permission> optionalPermission = permissionRepository.findByName(PermissionEnum.valueOf(permissionName));

                    return optionalPermission.orElseGet(() -> Permission.builder()
                            .name(PermissionEnum.valueOf(permissionName))
                            .build());
                })
                .collect(Collectors.toSet());
            return Role.builder()
                    .name(request.getName())
                    .permissions(permissions)
                    .build();

    }
}
