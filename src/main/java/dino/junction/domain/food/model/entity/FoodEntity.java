package dino.junction.domain.food.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "foods")
@Getter
@Setter
@NoArgsConstructor
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_code", nullable = false)
    private String foodCode;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "nutrition_standard_amount")
    private String nutritionStandardAmount;

    @Column(name = "energy_kcal")
    private Double energyKcal;

    @Column(name = "water_g")
    private Double waterG;

    @Column(name = "protein_g")
    private Double proteinG;

    @Column(name = "fat_g")
    private Double fatG;

    @Column(name = "ash_g")
    private Double ashG;

    @Column(name = "carbohydrate_g")
    private Double carbohydrateG;

    @Column(name = "sugar_g")
    private Double sugarG;

    @Column(name = "dietary_fiber_g")
    private Double dietaryFiberG;

    @Column(name = "calcium_mg")
    private Double calciumMg;

    @Column(name = "iron_mg")
    private Double ironMg;

    @Column(name = "phosphorus_mg")
    private Double phosphorusMg;

    @Column(name = "potassium_mg")
    private Double potassiumMg;

    @Column(name = "sodium_mg")
    private Double sodiumMg;

    @Column(name = "vitamin_a_μg_rae")
    private Double vitaminAμgRAE;

    @Column(name = "retinol_μg")
    private Double retinolμg;

    @Column(name = "beta_carotene_μg")
    private Double betaCaroteneμg;

    @Column(name = "thiamine_mg")
    private Double thiamineMg;

    @Column(name = "riboflavin_mg")
    private Double riboflavinMg;

    @Column(name = "niacin_mg")
    private Double niacinMg;

    @Column(name = "vitamin_c_mg")
    private Double vitaminCMg;

    @Column(name = "vitamin_d_μg")
    private Double vitaminDμg;

    @Column(name = "cholesterol_mg")
    private Double cholesterolMg;

    @Column(name = "saturated_fatty_acid_g")
    private Double saturatedFattyAcidG;

    @Column(name = "trans_fatty_acid_g")
    private Double transFattyAcidG;

    @Column(name = "serving_size_reference")
    private String servingSizeReference;

    @Column(name = "food_weight")
    private String foodWeight;

    @Column(name = "product_report_number")
    private String productReportNumber;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Column(name = "provider_name")
    private String providerName;

    @Builder
    public FoodEntity(String foodCode, String foodName, String nutritionStandardAmount, Double energyKcal,
                      Double waterG, Double proteinG, Double fatG, Double ashG, Double carbohydrateG, Double sugarG,
                      Double dietaryFiberG, Double calciumMg, Double ironMg, Double phosphorusMg, Double potassiumMg,
                      Double sodiumMg, Double vitaminAμgRAE, Double retinolμg, Double betaCaroteneμg, Double thiamineMg,
                      Double riboflavinMg, Double niacinMg, Double vitaminCMg, Double vitaminDμg, Double cholesterolMg,
                      Double saturatedFattyAcidG, Double transFattyAcidG, String servingSizeReference, String foodWeight,
                      String productReportNumber, String manufacturerName, String providerName) {
        this.foodCode = foodCode;
        this.foodName = foodName;
        this.nutritionStandardAmount = nutritionStandardAmount;
        this.energyKcal = energyKcal;
        this.waterG = waterG;
        this.proteinG = proteinG;
        this.fatG = fatG;
        this.ashG = ashG;
        this.carbohydrateG = carbohydrateG;
        this.sugarG = sugarG;
        this.dietaryFiberG = dietaryFiberG;
        this.calciumMg = calciumMg;
        this.ironMg = ironMg;
        this.phosphorusMg = phosphorusMg;
        this.potassiumMg = potassiumMg;
        this.sodiumMg = sodiumMg;
        this.vitaminAμgRAE = vitaminAμgRAE;
        this.retinolμg = retinolμg;
        this.betaCaroteneμg = betaCaroteneμg;
        this.thiamineMg = thiamineMg;
        this.riboflavinMg = riboflavinMg;
        this.niacinMg = niacinMg;
        this.vitaminCMg = vitaminCMg;
        this.vitaminDμg = vitaminDμg;
        this.cholesterolMg = cholesterolMg;
        this.saturatedFattyAcidG = saturatedFattyAcidG;
        this.transFattyAcidG = transFattyAcidG;
        this.servingSizeReference = servingSizeReference;
        this.foodWeight = foodWeight;
        this.productReportNumber = productReportNumber;
        this.manufacturerName = manufacturerName;
        this.providerName = providerName;
    }
}
