package dino.junction.domain.history.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryResponse {
    private String foodName;
    private String manufacturer;
    private int calories;
    private int servingSize;
    private int carbohydrates;
    private int protein;
    private int fat;
    private int carbohydratesPercent;
    private int proteinPercent;
    private int fatPercent;

    // Optional: You can add a static factory method if needed
    public static HistoryResponse of(String foodName, String manufacturer, int calories, int servingSize,
                                     int carbohydrates, int protein, int fat,
                                     int carbohydratesPercent, int proteinPercent, int fatPercent) {
        return new HistoryResponse(foodName, manufacturer, calories, servingSize, carbohydrates, protein, fat,
                carbohydratesPercent, proteinPercent, fatPercent);
    }
}
