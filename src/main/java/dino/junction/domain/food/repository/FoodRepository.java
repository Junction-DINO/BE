package dino.junction.domain.food.repository;

import dino.junction.domain.food.entity.FoodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
    Page<FoodEntity> findByFoodNameContaining(String foodName, Pageable pageable);

    long countByFoodNameContaining(String keyword);
}
