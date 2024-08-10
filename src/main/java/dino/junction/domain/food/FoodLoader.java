package dino.junction.domain.food;

import dino.junction.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FoodLoader implements CommandLineRunner {

    private final FoodService foodService;
    @Override
    public void run(String... args) throws Exception {
        try {
            foodService.saveFoodsFromJson();
            System.out.println("Food data loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load food data.");
        }
    }

}
