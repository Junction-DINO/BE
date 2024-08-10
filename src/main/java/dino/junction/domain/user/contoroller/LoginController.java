package dino.junction.domain.user.contoroller;

import dino.junction.config.auth.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j(topic = "LoginController")
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    @Operation(summary = "kakao login token 발급 경로", description = "/oauth2/authorization/kakao 로그인 성공시 URI를 통해 토큰을 발급 받는다.")
    @GetMapping(value = "/oauth2/code/kakao", produces = "application/json; charset=utf-8")
    public ResponseEntity<TokenResponse> kakaoRedirect(@RequestParam("access_token") String accessToken) {
        log.info("accessToken: {}, refreshToken: {}", accessToken);
        return null;
    }

    @Operation(summary = "google login token 발급 경로", description = "/oauth2/authorization/google 로그인 성공시 URI를 통해 토큰을 발급 받는다.")
    @GetMapping(value = "/oauth2/code/google", produces = "application/json; charset=utf-8")
    public ResponseEntity<TokenResponse> goolgeRedirect(@RequestParam("access_token") String accessToken) {
        log.info("accessToken: {}, refreshToken: {}", accessToken);
        return null;
    }
}
