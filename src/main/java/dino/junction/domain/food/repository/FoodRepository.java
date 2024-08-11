package dino.junction.domain.food.repository;

import dino.junction.domain.food.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Page<Food> findByFoodNameContaining(String foodName, Pageable pageable);

    long countByFoodNameContaining(String keyword);

    Optional<Food> findByFoodName(String foodName);
}
