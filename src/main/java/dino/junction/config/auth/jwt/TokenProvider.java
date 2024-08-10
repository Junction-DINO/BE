package dino.junction.config.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider {
    private final CustomUserDetailsService userDetailsService;

    private static final String AUTHORITIES_KEY = "role";
    private static final String EMAIL_KEY = "email";

    @Value("${jwt.access-token-valid-time-in-milliseconds}")
    private Long ACCESS_TOKEN_VALID_TIME;
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    private static Key signingKey;

    public TokenProvider(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        byte[] secretKeyBytes = Decoders.BASE64.decode(SECRET_KEY);
        signingKey = Keys.hmacShaKeyFor(secretKeyBytes);
    }


    @Transactional
    public String createToken(String email, String authorities) {
        String accessToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS512")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_TIME))
                .setSubject("access-token")
                .claim(EMAIL_KEY, email)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .compact();

        return accessToken;
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Authentication getAuthentication(String token) {
        String email = getClaims(token).get(EMAIL_KEY).toString();
        AuthUser authUser = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(authUser, "", authUser.getAuthorities());
    }

    public long getTokenExpirationTime(String token) {
        return getClaims(token).getExpiration().getTime();
    }

    public boolean validateAccessToken(String accessToken) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(accessToken);
            return true;
        } catch(ExpiredJwtException e) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}