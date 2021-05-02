package io.roadmaps.core.security;

import io.roadmaps.core.model.security.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

@Data
public class UserDetailsImpl {

    private UUID id;
    private String username;
    private String password;
    private Role role;
    private List<? extends GrantedAuthority> authorities;
}
