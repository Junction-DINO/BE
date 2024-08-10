package dino.junction.config.auth.handler;

import dino.junction.config.auth.jwt.AuthUser;
import dino.junction.config.auth.jwt.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    @Value("${react.url}")
    private String reactUrl;

    public String createURI(String accessToken) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);

        return UriComponentsBuilder
                .newInstance()
                .uri(URI.create(reactUrl + "/redirect"))
                .queryParams(queryParams)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        AuthUser oAuth2User = (AuthUser) authentication.getPrincipal();

        String accessToken = tokenProvider.createToken(oAuth2User.getEmail(), oAuth2User.getRole().getKey());

        String uri = createURI(accessToken);
        response.sendRedirect(uri);
    }
}