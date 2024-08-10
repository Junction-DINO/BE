package dino.junction.domain.food.repository;

import dino.junction.domain.food.model.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
    List<FoodEntity> findByFoodNameContaining(String foodName);
}
