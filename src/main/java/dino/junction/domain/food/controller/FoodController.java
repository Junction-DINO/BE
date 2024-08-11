package dino.junction.domain.food.controller;

import dino.junction.common.auth.jwt.AuthUser;
import dino.junction.common.model.CommonResponse;
import dino.junction.domain.food.service.FoodService;
import dino.junction.domain.history.repository.HistoryRepository;
import dino.junction.domain.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food")
@Slf4j
public class FoodController {
    private final FoodService foodService;
    private final HistoryService historyService;

    @GetMapping("")
    public CommonResponse<Object> getFood(@RequestParam String foodName) {
        return CommonResponse.CommonResponseSuccess(foodService.getFood(foodName));
    }

    @PostMapping("")
    public CommonResponse<Object> applyFood(@AuthenticationPrincipal AuthUser authUser, @RequestParam String foodName) {
        historyService.recordHistory(authUser.getName(), foodName);
        return CommonResponse.CommonResponseSuccess("success");
    }
}
