package dino.junction.junction;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JacksonTest {
    @Test
    public void FoodJsonRemoveTest() throws IOException {
        // JSON 파일을 로드합니다.
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File("src/main/resources/food_loader.json"));

        // 필요한 필드들만 남기고 나머지 필드 삭제
        for (JsonNode recordNode : rootNode.path("records")) {
            removeUnnecessaryFields((ObjectNode) recordNode);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/food_loader.json"), rootNode);
    }

    private static void removeUnnecessaryFields(ObjectNode objectNode) {
        // 남길 필드들의 이름을 정의합니다.
        String[] requiredFields = {
                "식품코드", "식품명", "영양성분함량기준량", "에너지(kcal)", "수분(g)", "단백질(g)", "지방(g)", "회분(g)",
                "탄수화물(g)", "당류(g)", "식이섬유(g)", "칼슘(mg)", "철(mg)", "인(mg)", "칼륨(mg)", "나트륨(mg)",
                "비타민 A(μg RAE)", "레티놀(μg)", "베타카로틴(μg)", "티아민(mg)", "리보플라빈(mg)", "니아신(mg)",
                "비타민 C(mg)", "비타민 D(μg)", "콜레스테롤(mg)", "포화지방산(g)", "트랜스지방산(g)",
                "1회 섭취참고량", "식품중량", "품목제조보고번호", "제조사명", "제공기관명"
        };

        // 남길 필드들의 이름을 집합으로 만듭니다.
        Set<String> requiredFieldsSet = new HashSet<>(Arrays.asList(requiredFields));

        // 모든 필드를 반복하면서, 필요한 필드 외의 나머지 필드를 삭제합니다.
        Iterator<String> fieldNames = objectNode.fieldNames();
        List<String> fieldsToRemove = new ArrayList<>();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            if (!requiredFieldsSet.contains(fieldName)) {
                fieldsToRemove.add(fieldName);
            }
        }

        // 삭제할 필드들을 objectNode에서 제거합니다.
        for (String fieldToRemove : fieldsToRemove) {
            objectNode.remove(fieldToRemove);
        }
    }
}
