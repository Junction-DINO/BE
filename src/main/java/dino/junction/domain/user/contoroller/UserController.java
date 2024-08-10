package dino.junction.domain.user.contoroller;

import dino.junction.common.model.CommonResponse;
import dino.junction.config.auth.jwt.AuthUser;
import dino.junction.domain.user.dto.UserRequest;
import dino.junction.domain.user.dto.UserResponse;
import dino.junction.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Operation(summary = "자신의 정보 조회")
    @GetMapping("/api/v1/users")
    public CommonResponse<Object> getUser(@AuthenticationPrincipal AuthUser authUser) {
        return CommonResponse.CommonResponseSuccess(userService.getUser(authUser.getId()));
    }

    @Operation(summary = "처음 회원가입시 추가 정보 등록")
    @PostMapping("/api/v1/users")
    public CommonResponse<Object> changeUser(@AuthenticationPrincipal AuthUser authUser, @RequestBody UserRequest userRequest){
        userService.changeUser(authUser.getId(), userRequest);
        return CommonResponse.CommonResponseSuccess(null);
    }
}
