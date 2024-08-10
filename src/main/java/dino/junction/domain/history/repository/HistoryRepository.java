package dino.junction.domain.history.repository;

import dino.junction.domain.food.entity.FoodEntity;
import dino.junction.domain.history.entity.HistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    // member name을 받아 가장 최근 조회한 3가지 음식을 조회하는 메서드
    Page<HistoryEntity> findAllByUserNameOrderByCreatedDateDesc(String username, Pageable pageable);
}
