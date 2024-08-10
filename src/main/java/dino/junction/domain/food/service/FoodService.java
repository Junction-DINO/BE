package dino.junction.domain.food.service;

import aj.org.objectweb.asm.TypeReference;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dino.junction.domain.food.model.entity.FoodEntity;
import dino.junction.domain.food.controller.request.TemplateCreateRequest;
import dino.junction.domain.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    @Transactional
    public void saveFoods(List<FoodEntity> foods) {
        foodRepository.saveAll(foods);
    }

    public List<FoodEntity> searchFoods(String query) {
        return foodRepository.findByFoodNameContaining(query);
    }

    @Transactional
    public void saveFoodsFromJson() throws IOException {
        Reader reader = new FileReader("./src/main/resources/food_loader.json");
        JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

        JsonArray recordsArray = jsonObject.getAsJsonArray("records");
        List<FoodEntity> foodEntities = new ArrayList<>();

        for (JsonElement element : recordsArray) {
            JsonObject foodJson = element.getAsJsonObject();
            FoodEntity foodEntity = FoodEntity.builder()
                    .foodCode(foodJson.has("식품코드") ? foodJson.get("식품코드").getAsString() : "0")
                    .foodName(foodJson.has("식품명") ? foodJson.get("식품명").getAsString() : "0")
                    .nutritionStandardAmount(foodJson.has("영양성분함량기준량") ? foodJson.get("영양성분함량기준량").getAsString() : "0")
                    .energyKcal(foodJson.has("에너지(kcal)") ? parseDoubleOr0(foodJson.get("에너지(kcal)").getAsString()) : 0)
                    .waterG(foodJson.has("수분(g)") ? parseDoubleOr0(foodJson.get("수분(g)").getAsString()) : 0)
                    .proteinG(foodJson.has("단백질(g)") ? parseDoubleOr0(foodJson.get("단백질(g)").getAsString()) : 0)
                    .fatG(foodJson.has("지방(g)") ? parseDoubleOr0(foodJson.get("지방(g)").getAsString()) : 0)
                    .ashG(foodJson.has("회분(g)") ? parseDoubleOr0(foodJson.get("회분(g)").getAsString()) : 0)
                    .carbohydrateG(foodJson.has("탄수화물(g)") ? parseDoubleOr0(foodJson.get("탄수화물(g)").getAsString()) : 0)
                    .sugarG(foodJson.has("당류(g)") ? parseDoubleOr0(foodJson.get("당류(g)").getAsString()) : 0)
                    .dietaryFiberG(foodJson.has("식이섬유(g)") ? parseDoubleOr0(foodJson.get("식이섬유(g)").getAsString()) : 0)
                    .calciumMg(foodJson.has("칼슘(mg)") ? parseDoubleOr0(foodJson.get("칼슘(mg)").getAsString()) : 0)
                    .ironMg(foodJson.has("철(mg)") ? parseDoubleOr0(foodJson.get("철(mg)").getAsString()) : 0)
                    .phosphorusMg(foodJson.has("인(mg)") ? parseDoubleOr0(foodJson.get("인(mg)").getAsString()) : 0)
                    .potassiumMg(foodJson.has("칼륨(mg)") ? parseDoubleOr0(foodJson.get("칼륨(mg)").getAsString()) : 0)
                    .sodiumMg(foodJson.has("나트륨(mg)") ? parseDoubleOr0(foodJson.get("나트륨(mg)").getAsString()) : 0)
                    .vitaminAμgRAE(foodJson.has("비타민 A(μg RAE)") ? parseDoubleOr0(foodJson.get("비타민 A(μg RAE)").getAsString()) : 0)
                    .retinolμg(foodJson.has("레티놀(μg)") ? parseDoubleOr0(foodJson.get("레티놀(μg)").getAsString()) : 0)
                    .betaCaroteneμg(foodJson.has("베타카로틴(μg)") ? parseDoubleOr0(foodJson.get("베타카로틴(μg)").getAsString()) : 0)
                    .thiamineMg(foodJson.has("티아민(mg)") ? parseDoubleOr0(foodJson.get("티아민(mg)").getAsString()) : 0)
                    .riboflavinMg(foodJson.has("리보플라빈(mg)") ? parseDoubleOr0(foodJson.get("리보플라빈(mg)").getAsString()) : 0)
                    .niacinMg(foodJson.has("니아신(mg)") ? parseDoubleOr0(foodJson.get("니아신(mg)").getAsString()) : 0)
                    .vitaminCMg(foodJson.has("비타민 C(mg)") ? parseDoubleOr0(foodJson.get("비타민 C(mg)").getAsString()) : 0)
                    .vitaminDμg(foodJson.has("비타민 D(μg)") ? parseDoubleOr0(foodJson.get("비타민 D(μg)").getAsString()) : 0)
                    .cholesterolMg(foodJson.has("콜레스테롤(mg)") ? parseDoubleOr0(foodJson.get("콜레스테롤(mg)").getAsString()) : 0)
                    .saturatedFattyAcidG(foodJson.has("포화지방산(g)") ? parseDoubleOr0(foodJson.get("포화지방산(g)").getAsString()) : 0)
                    .transFattyAcidG(foodJson.has("트랜스지방산(g)") ? parseDoubleOr0(foodJson.get("트랜스지방산(g)").getAsString()) : 0)
                    .servingSizeReference(foodJson.has("1회 섭취참고량") ? foodJson.get("1회 섭취참고량").getAsString() : "0")
                    .foodWeight(foodJson.has("식품중량") ? foodJson.get("식품중량").getAsString() : "0")
                    .manufacturerName(foodJson.has("제조사명") ? foodJson.get("제조사명").getAsString() : "0")
                    .providerName(foodJson.has("제공기관명") ? foodJson.get("제공기관명").getAsString() : "0")
                    .build();

            foodEntities.add(foodEntity);
        }
        foodRepository.saveAll(foodEntities);

    }

    private static Double parseDoubleOr0(String value) {
        try {
            return value.isEmpty() ? 0 : Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
