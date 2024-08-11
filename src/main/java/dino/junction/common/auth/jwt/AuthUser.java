package dino.junction.common.auth.jwt;

import dino.junction.domain.user.entity.Role;
import dino.junction.domain.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
public class AuthUser implements UserDetails, OAuth2User {
    private final Long id;
    private final String email;
    private final Role role;
    private Map<String, Object> attributes;

    public AuthUser(Long id, String email, Role role, Map<String, Object> attributes) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.attributes = attributes;
    }

    public static AuthUser of(User user, Map<String, Object> attributes) {
        return new AuthUser(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                attributes
        );
    }
    public static AuthUser of(User user) {
        return new AuthUser(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                Collections.emptyMap()
        );
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> this.getRole().getKey());
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() { // 계정의 만료 여부
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정의 잠김 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호 만료 여부
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정의 활성화 여부
        return true;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

}