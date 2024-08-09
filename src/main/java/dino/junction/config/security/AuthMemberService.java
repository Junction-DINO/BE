package dino.junction.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AuthMemberService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthMember loadUserByUsername(String phoneNumber) {
        return null;
    }
}
