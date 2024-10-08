package dino.junction.domain.search.service;

import dino.junction.domain.food.dto.FoodResponse;
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
@Transactional(readOnly = true)
public class SearchService {
    private final FoodService foodService;

    @Transactional
    public List<FoodResponse> searchFoodsWithOcr(OcrRequest ocrRequest, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return foodService.searchFoodsByNames(ocrRequest, pageable);
    }

    @Transactional
    public List<FoodResponse> searchFoods(String q, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return foodService.searchFoods(q, pageable);
    }
}
