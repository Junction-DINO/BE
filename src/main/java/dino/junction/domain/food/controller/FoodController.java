package dino.junction.domain.food.controller;

import dino.junction.common.model.CommonResponse;
import dino.junction.config.auth.jwt.AuthUser;
import dino.junction.domain.food.controller.request.TemplateCreateRequest;
import dino.junction.domain.food.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.aop.support.AopUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food")
@Slf4j
public class FoodController {
    private final FoodService foodService;

    @GetMapping("")
    public CommonResponse<Object> getFood(@RequestParam String foodName) {
        return CommonResponse.CommonResponseSuccess(foodService.getFood(foodName));
    }

    @PostMapping("")
    public CommonResponse<Object> applyFood(@AuthenticationPrincipal AuthUser authUser, @RequestParam String foodName) {
        foodService.applyFood(authUser.getName(), foodName);
        return CommonResponse.CommonResponseSuccess("success");
    }
}
