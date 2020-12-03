package edu.roadmaps.core.security.permission;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Permission implements GrantedAuthority {
    private String id;
    private String description;

    @Override
    public String getAuthority() {
        return id;
    }
}
