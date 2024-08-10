package dino.junction.domain.search.service;

import dino.junction.domain.food.dto.FoodResponse;
import dino.junction.domain.food.entity.FoodEntity;
import dino.junction.domain.food.service.FoodService;
import dino.junction.domain.ocr.dto.OcrRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final FoodService foodService;

    @Transactional
    public List<FoodResponse> searchFoodsWithOcr(OcrRequest ocrRequest, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return foodService.searchFoodsByNames(ocrRequest, pageable);
    }

    public List<FoodEntity> searchFoods(String q, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return foodService.searchFoods(q, pageable);
    }
}
