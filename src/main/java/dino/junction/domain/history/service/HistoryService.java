package dino.junction.domain.history.service;

import dino.junction.domain.food.dto.FoodResponse;
import dino.junction.domain.food.entity.FoodEntity;
import dino.junction.domain.food.service.FoodService;
import dino.junction.domain.history.entity.HistoryEntity;
import dino.junction.domain.history.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final FoodService foodService;

    // 일일 평균 영양성분 기준치
    private static final double DAILY_PROTEIN = 50.0;
    private static final double DAILY_SODIUM = 1.5;
    private static final double DAILY_FAT = 45.0;

    public Page<HistoryEntity> getHistoryList(String userName) {
        return historyRepository.findAllByUserNameOrderByCreatedDateDesc(userName, PageRequest.of(0, 3));
    }

    public void recordHistory(String userName, String food_name) {
        FoodResponse foodResponse = foodService.getFood(food_name);

        double proteinPercent = (foodResponse.getProteinG() / DAILY_PROTEIN) * 100;
        double sodiumPercent = (foodResponse.getSodiumMg() / DAILY_SODIUM) * 100;
        double fatPercent = (foodResponse.getFatG() / DAILY_FAT) * 100;

        HistoryEntity historyEntity = HistoryEntity.builder()
                .userName(userName)
                .foodName(foodResponse.getFoodName())
                .userName(foodResponse.getProviderName())
                .calories(foodResponse.getCalciumMg())
                .foodWeight(foodResponse.getFoodWeight())
                .carbohydrates(foodResponse.getCarbohydrateG())
                .protein(foodResponse.getProteinG())
                .fat(foodResponse.getFatG())
                .carbohydratesPercent(sodiumPercent)
                .proteinPercent(proteinPercent)
                .fatPercent(fatPercent)
                .build();

    }
}