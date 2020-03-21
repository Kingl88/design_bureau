package by.it.design_bureau.entities;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
@NoArgsConstructor
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
