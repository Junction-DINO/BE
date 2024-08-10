package dino.junction.domain.food;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dino.junction.domain.food.model.entity.FoodEntity;
import dino.junction.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FoodLoader implements CommandLineRunner {

    private final FoodService foodService;
    @Override
    public void run(String... args) throws Exception {
        String jsonFilePath = "src/";  // 실제 JSON 파일 경로로 변경하세요
        try {
            foodService.saveFoodsFromJson(jsonFilePath);
            System.out.println("Food data loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load food data.");
        }
    }

}
