package dino.junction.domain.history.controller;

import dino.junction.common.auth.jwt.AuthUser;
import dino.junction.common.model.CommonResponse;
import dino.junction.domain.history.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/")
    public CommonResponse<Object> getHistoryList(@AuthenticationPrincipal AuthUser authUser) {
        return CommonResponse.CommonResponseSuccess(historyService.getHistoryList(authUser.getName()));
    }

    @PostMapping("/")
    public CommonResponse<Object> recordHistory(@AuthenticationPrincipal AuthUser authUser, String food_name) {
        historyService.recordHistory(authUser.getName(), food_name);
        return CommonResponse.CommonResponseSuccess(null);
    }
}