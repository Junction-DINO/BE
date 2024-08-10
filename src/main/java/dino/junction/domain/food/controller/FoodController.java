package dino.junction.domain.food.controller;

import dino.junction.common.model.CommonResponse;
import dino.junction.domain.food.controller.request.TemplateCreateRequest;
import dino.junction.domain.food.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.aop.support.AopUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/template")
@Slf4j
public class FoodController {
    private final FoodService foodService;
}
