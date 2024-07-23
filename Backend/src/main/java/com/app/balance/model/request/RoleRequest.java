package com.app.balance.model.request;

import com.app.balance.model.entity.Permission;
import com.app.balance.model.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequest {
    private RoleEnum name;
    private Set<String> permissions;
}
