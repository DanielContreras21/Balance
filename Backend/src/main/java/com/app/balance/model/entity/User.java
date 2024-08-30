package com.app.balance.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingrese un nombre válido")
    @NotNull(message = "Ingrese un nombre válido")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Ingrese un nombre de usuario válido")
    @NotNull(message = "Ingrese un nombre de usuario válido")
    private String username;

    @NotBlank(message = "Ingrese una contraseña válida")
    @NotNull(message = "Ingrese una contraseña válida")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "Ingrese un correo electrónico válido")
    @NotNull(message = "Ingrese un correo electrónico válido")
    @Email(message = "Ingrese un correo electrónico válido")
    private String email;

    @Column(name = "created")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date created;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled")
    private boolean isEnabled;
}
