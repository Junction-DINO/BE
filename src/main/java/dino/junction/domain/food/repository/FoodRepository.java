package dino.junction.domain.food.repository;

import dino.junction.domain.food.entity.FoodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
    Page<FoodEntity> findByFoodNameContaining(String foodName, Pageable pageable);
    Optional<FoodEntity> findByFoodName(String foodName);

    long countByFoodNameContaining(String keyword);
}
