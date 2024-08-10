package dino.junction.domain.history.entity;

import dino.junction.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "history")
@Getter
@Setter
@NoArgsConstructor
public class HistoryEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "calories", nullable = false)
    private double calories;

    @Column(name = "food_weight", nullable = false)
    private String foodWeight;

    @Column(name = "carbohydrates_g", nullable = false)
    private double carbohydrates;

    @Column(name = "protein_g", nullable = false)
    private double protein;

    @Column(name = "fat_g", nullable = false)
    private double fat;

    @Column(name = "carbohydrates_percent", nullable = false)
    private double carbohydratesPercent;

    @Column(name = "protein_percent", nullable = false)
    private double proteinPercent;

    @Column(name = "fat_percent", nullable = false)
    private double fatPercent;

    @Builder
    public HistoryEntity(Long id, String foodName, String userName, double calories, String foodWeight, double carbohydrates, double protein, double fat, double carbohydratesPercent, double proteinPercent, double fatPercent) {
        this.id = id;
        this.foodName = foodName;
        this.userName = userName;
        this.calories = calories;
        this.foodWeight = foodWeight;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
        this.carbohydratesPercent = carbohydratesPercent;
        this.proteinPercent = proteinPercent;
        this.fatPercent = fatPercent;
    }
}
