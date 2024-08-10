package dino.junction.domain.food.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodResponse {
    private Long id;
    private String foodCode;
    private String foodName;
    private String nutritionStandardAmount;
    private Double energyKcal;
    private Double waterG;
    private Double proteinG;
    private Double fatG;
    private Double ashG;
    private Double carbohydrateG;
    private Double sugarG;
    private Double dietaryFiberG;
    private Double calciumMg;
    private Double ironMg;
    private Double phosphorusMg;
    private Double potassiumMg;
    private Double sodiumMg;
    private Double vitaminAμgRAE;
    private Double retinolμg;
    private Double betaCaroteneμg;
    private Double thiamineMg;
    private Double riboflavinMg;
    private Double niacinMg;
    private Double vitaminCMg;
    private Double vitaminDμg;
    private Double cholesterolMg;
    private Double saturatedFattyAcidG;
    private Double transFattyAcidG;
    private String servingSizeReference;
    private String foodWeight;
    private String manufacturerName;
    private String providerName;
    private String keyword;
}
